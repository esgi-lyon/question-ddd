package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.CreateTagCommand;
import org.contextmapper.generated.gateway.repository.CreateTagCommandRepository;
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
 * Integration tests for the {@link CreateTagCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CreateTagCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/create-tag-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateTagCommandRepository createTagCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CreateTagCommand createTagCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateTagCommand createEntity(EntityManager em) {
        CreateTagCommand createTagCommand = new CreateTagCommand();
        return createTagCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateTagCommand createUpdatedEntity(EntityManager em) {
        CreateTagCommand createTagCommand = new CreateTagCommand();
        return createTagCommand;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CreateTagCommand.class).block();
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
        createTagCommand = createEntity(em);
    }

    @Test
    void createCreateTagCommand() throws Exception {
        int databaseSizeBeforeCreate = createTagCommandRepository.findAll().collectList().block().size();
        // Create the CreateTagCommand
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createTagCommand))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll().collectList().block();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeCreate + 1);
        CreateTagCommand testCreateTagCommand = createTagCommandList.get(createTagCommandList.size() - 1);
    }

    @Test
    void createCreateTagCommandWithExistingId() throws Exception {
        // Create the CreateTagCommand with an existing ID
        createTagCommand.setId(1L);

        int databaseSizeBeforeCreate = createTagCommandRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createTagCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll().collectList().block();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCreateTagCommandsAsStream() {
        // Initialize the database
        createTagCommandRepository.save(createTagCommand).block();

        List<CreateTagCommand> createTagCommandList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(CreateTagCommand.class)
            .getResponseBody()
            .filter(createTagCommand::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(createTagCommandList).isNotNull();
        assertThat(createTagCommandList).hasSize(1);
        CreateTagCommand testCreateTagCommand = createTagCommandList.get(0);
    }

    @Test
    void getAllCreateTagCommands() {
        // Initialize the database
        createTagCommandRepository.save(createTagCommand).block();

        // Get all the createTagCommandList
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
            .value(hasItem(createTagCommand.getId().intValue()));
    }

    @Test
    void getCreateTagCommand() {
        // Initialize the database
        createTagCommandRepository.save(createTagCommand).block();

        // Get the createTagCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, createTagCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(createTagCommand.getId().intValue()));
    }

    @Test
    void getNonExistingCreateTagCommand() {
        // Get the createTagCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCreateTagCommand() throws Exception {
        // Initialize the database
        createTagCommandRepository.save(createTagCommand).block();

        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().collectList().block().size();

        // Update the createTagCommand
        CreateTagCommand updatedCreateTagCommand = createTagCommandRepository.findById(createTagCommand.getId()).block();

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedCreateTagCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedCreateTagCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll().collectList().block();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateTagCommand testCreateTagCommand = createTagCommandList.get(createTagCommandList.size() - 1);
    }

    @Test
    void putNonExistingCreateTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().collectList().block().size();
        createTagCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, createTagCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createTagCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll().collectList().block();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCreateTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().collectList().block().size();
        createTagCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createTagCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll().collectList().block();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCreateTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().collectList().block().size();
        createTagCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createTagCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll().collectList().block();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCreateTagCommandWithPatch() throws Exception {
        // Initialize the database
        createTagCommandRepository.save(createTagCommand).block();

        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().collectList().block().size();

        // Update the createTagCommand using partial update
        CreateTagCommand partialUpdatedCreateTagCommand = new CreateTagCommand();
        partialUpdatedCreateTagCommand.setId(createTagCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreateTagCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateTagCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll().collectList().block();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateTagCommand testCreateTagCommand = createTagCommandList.get(createTagCommandList.size() - 1);
    }

    @Test
    void fullUpdateCreateTagCommandWithPatch() throws Exception {
        // Initialize the database
        createTagCommandRepository.save(createTagCommand).block();

        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().collectList().block().size();

        // Update the createTagCommand using partial update
        CreateTagCommand partialUpdatedCreateTagCommand = new CreateTagCommand();
        partialUpdatedCreateTagCommand.setId(createTagCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreateTagCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateTagCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll().collectList().block();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateTagCommand testCreateTagCommand = createTagCommandList.get(createTagCommandList.size() - 1);
    }

    @Test
    void patchNonExistingCreateTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().collectList().block().size();
        createTagCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, createTagCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createTagCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll().collectList().block();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCreateTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().collectList().block().size();
        createTagCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createTagCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll().collectList().block();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCreateTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().collectList().block().size();
        createTagCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createTagCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll().collectList().block();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCreateTagCommand() {
        // Initialize the database
        createTagCommandRepository.save(createTagCommand).block();

        int databaseSizeBeforeDelete = createTagCommandRepository.findAll().collectList().block().size();

        // Delete the createTagCommand
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, createTagCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll().collectList().block();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
