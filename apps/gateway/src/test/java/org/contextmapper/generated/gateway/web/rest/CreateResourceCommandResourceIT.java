package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.CreateResourceCommand;
import org.contextmapper.generated.gateway.repository.CreateResourceCommandRepository;
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
 * Integration tests for the {@link CreateResourceCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CreateResourceCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/create-resource-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateResourceCommandRepository createResourceCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CreateResourceCommand createResourceCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateResourceCommand createEntity(EntityManager em) {
        CreateResourceCommand createResourceCommand = new CreateResourceCommand();
        return createResourceCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateResourceCommand createUpdatedEntity(EntityManager em) {
        CreateResourceCommand createResourceCommand = new CreateResourceCommand();
        return createResourceCommand;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CreateResourceCommand.class).block();
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
        createResourceCommand = createEntity(em);
    }

    @Test
    void createCreateResourceCommand() throws Exception {
        int databaseSizeBeforeCreate = createResourceCommandRepository.findAll().collectList().block().size();
        // Create the CreateResourceCommand
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createResourceCommand))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll().collectList().block();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeCreate + 1);
        CreateResourceCommand testCreateResourceCommand = createResourceCommandList.get(createResourceCommandList.size() - 1);
    }

    @Test
    void createCreateResourceCommandWithExistingId() throws Exception {
        // Create the CreateResourceCommand with an existing ID
        createResourceCommand.setId(1L);

        int databaseSizeBeforeCreate = createResourceCommandRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createResourceCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll().collectList().block();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCreateResourceCommandsAsStream() {
        // Initialize the database
        createResourceCommandRepository.save(createResourceCommand).block();

        List<CreateResourceCommand> createResourceCommandList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(CreateResourceCommand.class)
            .getResponseBody()
            .filter(createResourceCommand::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(createResourceCommandList).isNotNull();
        assertThat(createResourceCommandList).hasSize(1);
        CreateResourceCommand testCreateResourceCommand = createResourceCommandList.get(0);
    }

    @Test
    void getAllCreateResourceCommands() {
        // Initialize the database
        createResourceCommandRepository.save(createResourceCommand).block();

        // Get all the createResourceCommandList
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
            .value(hasItem(createResourceCommand.getId().intValue()));
    }

    @Test
    void getCreateResourceCommand() {
        // Initialize the database
        createResourceCommandRepository.save(createResourceCommand).block();

        // Get the createResourceCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, createResourceCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(createResourceCommand.getId().intValue()));
    }

    @Test
    void getNonExistingCreateResourceCommand() {
        // Get the createResourceCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCreateResourceCommand() throws Exception {
        // Initialize the database
        createResourceCommandRepository.save(createResourceCommand).block();

        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().collectList().block().size();

        // Update the createResourceCommand
        CreateResourceCommand updatedCreateResourceCommand = createResourceCommandRepository
            .findById(createResourceCommand.getId())
            .block();

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedCreateResourceCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedCreateResourceCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll().collectList().block();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateResourceCommand testCreateResourceCommand = createResourceCommandList.get(createResourceCommandList.size() - 1);
    }

    @Test
    void putNonExistingCreateResourceCommand() throws Exception {
        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().collectList().block().size();
        createResourceCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, createResourceCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createResourceCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll().collectList().block();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCreateResourceCommand() throws Exception {
        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().collectList().block().size();
        createResourceCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createResourceCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll().collectList().block();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCreateResourceCommand() throws Exception {
        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().collectList().block().size();
        createResourceCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createResourceCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll().collectList().block();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCreateResourceCommandWithPatch() throws Exception {
        // Initialize the database
        createResourceCommandRepository.save(createResourceCommand).block();

        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().collectList().block().size();

        // Update the createResourceCommand using partial update
        CreateResourceCommand partialUpdatedCreateResourceCommand = new CreateResourceCommand();
        partialUpdatedCreateResourceCommand.setId(createResourceCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreateResourceCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateResourceCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll().collectList().block();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateResourceCommand testCreateResourceCommand = createResourceCommandList.get(createResourceCommandList.size() - 1);
    }

    @Test
    void fullUpdateCreateResourceCommandWithPatch() throws Exception {
        // Initialize the database
        createResourceCommandRepository.save(createResourceCommand).block();

        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().collectList().block().size();

        // Update the createResourceCommand using partial update
        CreateResourceCommand partialUpdatedCreateResourceCommand = new CreateResourceCommand();
        partialUpdatedCreateResourceCommand.setId(createResourceCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreateResourceCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateResourceCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll().collectList().block();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateResourceCommand testCreateResourceCommand = createResourceCommandList.get(createResourceCommandList.size() - 1);
    }

    @Test
    void patchNonExistingCreateResourceCommand() throws Exception {
        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().collectList().block().size();
        createResourceCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, createResourceCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createResourceCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll().collectList().block();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCreateResourceCommand() throws Exception {
        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().collectList().block().size();
        createResourceCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createResourceCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll().collectList().block();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCreateResourceCommand() throws Exception {
        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().collectList().block().size();
        createResourceCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createResourceCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll().collectList().block();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCreateResourceCommand() {
        // Initialize the database
        createResourceCommandRepository.save(createResourceCommand).block();

        int databaseSizeBeforeDelete = createResourceCommandRepository.findAll().collectList().block().size();

        // Delete the createResourceCommand
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, createResourceCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll().collectList().block();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
