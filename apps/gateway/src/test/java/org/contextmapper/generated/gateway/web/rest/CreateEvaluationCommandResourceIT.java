package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.CreateEvaluationCommand;
import org.contextmapper.generated.gateway.repository.CreateEvaluationCommandRepository;
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
 * Integration tests for the {@link CreateEvaluationCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CreateEvaluationCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/create-evaluation-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateEvaluationCommandRepository createEvaluationCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CreateEvaluationCommand createEvaluationCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateEvaluationCommand createEntity(EntityManager em) {
        CreateEvaluationCommand createEvaluationCommand = new CreateEvaluationCommand();
        return createEvaluationCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateEvaluationCommand createUpdatedEntity(EntityManager em) {
        CreateEvaluationCommand createEvaluationCommand = new CreateEvaluationCommand();
        return createEvaluationCommand;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CreateEvaluationCommand.class).block();
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
        createEvaluationCommand = createEntity(em);
    }

    @Test
    void createCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeCreate = createEvaluationCommandRepository.findAll().collectList().block().size();
        // Create the CreateEvaluationCommand
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createEvaluationCommand))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll().collectList().block();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeCreate + 1);
        CreateEvaluationCommand testCreateEvaluationCommand = createEvaluationCommandList.get(createEvaluationCommandList.size() - 1);
    }

    @Test
    void createCreateEvaluationCommandWithExistingId() throws Exception {
        // Create the CreateEvaluationCommand with an existing ID
        createEvaluationCommand.setId(1L);

        int databaseSizeBeforeCreate = createEvaluationCommandRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createEvaluationCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll().collectList().block();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCreateEvaluationCommandsAsStream() {
        // Initialize the database
        createEvaluationCommandRepository.save(createEvaluationCommand).block();

        List<CreateEvaluationCommand> createEvaluationCommandList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(CreateEvaluationCommand.class)
            .getResponseBody()
            .filter(createEvaluationCommand::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(createEvaluationCommandList).isNotNull();
        assertThat(createEvaluationCommandList).hasSize(1);
        CreateEvaluationCommand testCreateEvaluationCommand = createEvaluationCommandList.get(0);
    }

    @Test
    void getAllCreateEvaluationCommands() {
        // Initialize the database
        createEvaluationCommandRepository.save(createEvaluationCommand).block();

        // Get all the createEvaluationCommandList
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
            .value(hasItem(createEvaluationCommand.getId().intValue()));
    }

    @Test
    void getCreateEvaluationCommand() {
        // Initialize the database
        createEvaluationCommandRepository.save(createEvaluationCommand).block();

        // Get the createEvaluationCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, createEvaluationCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(createEvaluationCommand.getId().intValue()));
    }

    @Test
    void getNonExistingCreateEvaluationCommand() {
        // Get the createEvaluationCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCreateEvaluationCommand() throws Exception {
        // Initialize the database
        createEvaluationCommandRepository.save(createEvaluationCommand).block();

        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().collectList().block().size();

        // Update the createEvaluationCommand
        CreateEvaluationCommand updatedCreateEvaluationCommand = createEvaluationCommandRepository
            .findById(createEvaluationCommand.getId())
            .block();

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedCreateEvaluationCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedCreateEvaluationCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll().collectList().block();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateEvaluationCommand testCreateEvaluationCommand = createEvaluationCommandList.get(createEvaluationCommandList.size() - 1);
    }

    @Test
    void putNonExistingCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().collectList().block().size();
        createEvaluationCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, createEvaluationCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createEvaluationCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll().collectList().block();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().collectList().block().size();
        createEvaluationCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createEvaluationCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll().collectList().block();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().collectList().block().size();
        createEvaluationCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createEvaluationCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll().collectList().block();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCreateEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        createEvaluationCommandRepository.save(createEvaluationCommand).block();

        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().collectList().block().size();

        // Update the createEvaluationCommand using partial update
        CreateEvaluationCommand partialUpdatedCreateEvaluationCommand = new CreateEvaluationCommand();
        partialUpdatedCreateEvaluationCommand.setId(createEvaluationCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreateEvaluationCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateEvaluationCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll().collectList().block();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateEvaluationCommand testCreateEvaluationCommand = createEvaluationCommandList.get(createEvaluationCommandList.size() - 1);
    }

    @Test
    void fullUpdateCreateEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        createEvaluationCommandRepository.save(createEvaluationCommand).block();

        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().collectList().block().size();

        // Update the createEvaluationCommand using partial update
        CreateEvaluationCommand partialUpdatedCreateEvaluationCommand = new CreateEvaluationCommand();
        partialUpdatedCreateEvaluationCommand.setId(createEvaluationCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreateEvaluationCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateEvaluationCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll().collectList().block();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateEvaluationCommand testCreateEvaluationCommand = createEvaluationCommandList.get(createEvaluationCommandList.size() - 1);
    }

    @Test
    void patchNonExistingCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().collectList().block().size();
        createEvaluationCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, createEvaluationCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createEvaluationCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll().collectList().block();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().collectList().block().size();
        createEvaluationCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createEvaluationCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll().collectList().block();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().collectList().block().size();
        createEvaluationCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createEvaluationCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll().collectList().block();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCreateEvaluationCommand() {
        // Initialize the database
        createEvaluationCommandRepository.save(createEvaluationCommand).block();

        int databaseSizeBeforeDelete = createEvaluationCommandRepository.findAll().collectList().block().size();

        // Delete the createEvaluationCommand
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, createEvaluationCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll().collectList().block();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
