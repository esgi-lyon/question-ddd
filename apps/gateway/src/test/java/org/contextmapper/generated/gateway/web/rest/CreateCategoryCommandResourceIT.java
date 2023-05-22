package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.CreateCategoryCommand;
import org.contextmapper.generated.gateway.repository.CreateCategoryCommandRepository;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link CreateCategoryCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CreateCategoryCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/create-category-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateCategoryCommandRepository createCategoryCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CreateCategoryCommand createCategoryCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateCategoryCommand createEntity(EntityManager em) {
        CreateCategoryCommand createCategoryCommand = new CreateCategoryCommand();
        return createCategoryCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateCategoryCommand createUpdatedEntity(EntityManager em) {
        CreateCategoryCommand createCategoryCommand = new CreateCategoryCommand();
        return createCategoryCommand;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CreateCategoryCommand.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntities(em);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        createCategoryCommand = createEntity(em);
    }

    @Test
    void createCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeCreate = createCategoryCommandRepository.findAll().collectList().block().size();
        // Create the CreateCategoryCommand
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createCategoryCommand))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll().collectList().block();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeCreate + 1);
        CreateCategoryCommand testCreateCategoryCommand = createCategoryCommandList.get(createCategoryCommandList.size() - 1);
    }

    @Test
    void createCreateCategoryCommandWithExistingId() throws Exception {
        // Create the CreateCategoryCommand with an existing ID
        createCategoryCommand.setId(1L);

        int databaseSizeBeforeCreate = createCategoryCommandRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createCategoryCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll().collectList().block();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCreateCategoryCommandsAsStream() {
        // Initialize the database
        createCategoryCommandRepository.save(createCategoryCommand).block();

        List<CreateCategoryCommand> createCategoryCommandList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(CreateCategoryCommand.class)
            .getResponseBody()
            .filter(createCategoryCommand::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(createCategoryCommandList).isNotNull();
        assertThat(createCategoryCommandList).hasSize(1);
        CreateCategoryCommand testCreateCategoryCommand = createCategoryCommandList.get(0);
    }

    @Test
    void getAllCreateCategoryCommands() {
        // Initialize the database
        createCategoryCommandRepository.save(createCategoryCommand).block();

        // Get all the createCategoryCommandList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(createCategoryCommand.getId().intValue()));
    }

    @Test
    void getCreateCategoryCommand() {
        // Initialize the database
        createCategoryCommandRepository.save(createCategoryCommand).block();

        // Get the createCategoryCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, createCategoryCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(createCategoryCommand.getId().intValue()));
    }

    @Test
    void getNonExistingCreateCategoryCommand() {
        // Get the createCategoryCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCreateCategoryCommand() throws Exception {
        // Initialize the database
        createCategoryCommandRepository.save(createCategoryCommand).block();

        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().collectList().block().size();

        // Update the createCategoryCommand
        CreateCategoryCommand updatedCreateCategoryCommand = createCategoryCommandRepository
            .findById(createCategoryCommand.getId())
            .block();

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedCreateCategoryCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedCreateCategoryCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll().collectList().block();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateCategoryCommand testCreateCategoryCommand = createCategoryCommandList.get(createCategoryCommandList.size() - 1);
    }

    @Test
    void putNonExistingCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().collectList().block().size();
        createCategoryCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, createCategoryCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createCategoryCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll().collectList().block();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().collectList().block().size();
        createCategoryCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createCategoryCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll().collectList().block();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().collectList().block().size();
        createCategoryCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createCategoryCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll().collectList().block();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCreateCategoryCommandWithPatch() throws Exception {
        // Initialize the database
        createCategoryCommandRepository.save(createCategoryCommand).block();

        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().collectList().block().size();

        // Update the createCategoryCommand using partial update
        CreateCategoryCommand partialUpdatedCreateCategoryCommand = new CreateCategoryCommand();
        partialUpdatedCreateCategoryCommand.setId(createCategoryCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreateCategoryCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateCategoryCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll().collectList().block();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateCategoryCommand testCreateCategoryCommand = createCategoryCommandList.get(createCategoryCommandList.size() - 1);
    }

    @Test
    void fullUpdateCreateCategoryCommandWithPatch() throws Exception {
        // Initialize the database
        createCategoryCommandRepository.save(createCategoryCommand).block();

        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().collectList().block().size();

        // Update the createCategoryCommand using partial update
        CreateCategoryCommand partialUpdatedCreateCategoryCommand = new CreateCategoryCommand();
        partialUpdatedCreateCategoryCommand.setId(createCategoryCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreateCategoryCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateCategoryCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll().collectList().block();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateCategoryCommand testCreateCategoryCommand = createCategoryCommandList.get(createCategoryCommandList.size() - 1);
    }

    @Test
    void patchNonExistingCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().collectList().block().size();
        createCategoryCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, createCategoryCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createCategoryCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll().collectList().block();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().collectList().block().size();
        createCategoryCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createCategoryCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll().collectList().block();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().collectList().block().size();
        createCategoryCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createCategoryCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll().collectList().block();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCreateCategoryCommand() {
        // Initialize the database
        createCategoryCommandRepository.save(createCategoryCommand).block();

        int databaseSizeBeforeDelete = createCategoryCommandRepository.findAll().collectList().block().size();

        // Delete the createCategoryCommand
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, createCategoryCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll().collectList().block();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
