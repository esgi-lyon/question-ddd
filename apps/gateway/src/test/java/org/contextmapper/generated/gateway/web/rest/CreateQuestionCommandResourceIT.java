package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.CreateQuestionCommand;
import org.contextmapper.generated.gateway.repository.CreateQuestionCommandRepository;
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
 * Integration tests for the {@link CreateQuestionCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CreateQuestionCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/create-question-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateQuestionCommandRepository createQuestionCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CreateQuestionCommand createQuestionCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateQuestionCommand createEntity(EntityManager em) {
        CreateQuestionCommand createQuestionCommand = new CreateQuestionCommand();
        return createQuestionCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateQuestionCommand createUpdatedEntity(EntityManager em) {
        CreateQuestionCommand createQuestionCommand = new CreateQuestionCommand();
        return createQuestionCommand;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CreateQuestionCommand.class).block();
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
        createQuestionCommand = createEntity(em);
    }

    @Test
    void createCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeCreate = createQuestionCommandRepository.findAll().collectList().block().size();
        // Create the CreateQuestionCommand
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll().collectList().block();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeCreate + 1);
        CreateQuestionCommand testCreateQuestionCommand = createQuestionCommandList.get(createQuestionCommandList.size() - 1);
    }

    @Test
    void createCreateQuestionCommandWithExistingId() throws Exception {
        // Create the CreateQuestionCommand with an existing ID
        createQuestionCommand.setId(1L);

        int databaseSizeBeforeCreate = createQuestionCommandRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll().collectList().block();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCreateQuestionCommandsAsStream() {
        // Initialize the database
        createQuestionCommandRepository.save(createQuestionCommand).block();

        List<CreateQuestionCommand> createQuestionCommandList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(CreateQuestionCommand.class)
            .getResponseBody()
            .filter(createQuestionCommand::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(createQuestionCommandList).isNotNull();
        assertThat(createQuestionCommandList).hasSize(1);
        CreateQuestionCommand testCreateQuestionCommand = createQuestionCommandList.get(0);
    }

    @Test
    void getAllCreateQuestionCommands() {
        // Initialize the database
        createQuestionCommandRepository.save(createQuestionCommand).block();

        // Get all the createQuestionCommandList
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
            .value(hasItem(createQuestionCommand.getId().intValue()));
    }

    @Test
    void getCreateQuestionCommand() {
        // Initialize the database
        createQuestionCommandRepository.save(createQuestionCommand).block();

        // Get the createQuestionCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, createQuestionCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(createQuestionCommand.getId().intValue()));
    }

    @Test
    void getNonExistingCreateQuestionCommand() {
        // Get the createQuestionCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCreateQuestionCommand() throws Exception {
        // Initialize the database
        createQuestionCommandRepository.save(createQuestionCommand).block();

        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().collectList().block().size();

        // Update the createQuestionCommand
        CreateQuestionCommand updatedCreateQuestionCommand = createQuestionCommandRepository
            .findById(createQuestionCommand.getId())
            .block();

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedCreateQuestionCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedCreateQuestionCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll().collectList().block();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateQuestionCommand testCreateQuestionCommand = createQuestionCommandList.get(createQuestionCommandList.size() - 1);
    }

    @Test
    void putNonExistingCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().collectList().block().size();
        createQuestionCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, createQuestionCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll().collectList().block();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().collectList().block().size();
        createQuestionCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll().collectList().block();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().collectList().block().size();
        createQuestionCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll().collectList().block();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCreateQuestionCommandWithPatch() throws Exception {
        // Initialize the database
        createQuestionCommandRepository.save(createQuestionCommand).block();

        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().collectList().block().size();

        // Update the createQuestionCommand using partial update
        CreateQuestionCommand partialUpdatedCreateQuestionCommand = new CreateQuestionCommand();
        partialUpdatedCreateQuestionCommand.setId(createQuestionCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreateQuestionCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateQuestionCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll().collectList().block();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateQuestionCommand testCreateQuestionCommand = createQuestionCommandList.get(createQuestionCommandList.size() - 1);
    }

    @Test
    void fullUpdateCreateQuestionCommandWithPatch() throws Exception {
        // Initialize the database
        createQuestionCommandRepository.save(createQuestionCommand).block();

        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().collectList().block().size();

        // Update the createQuestionCommand using partial update
        CreateQuestionCommand partialUpdatedCreateQuestionCommand = new CreateQuestionCommand();
        partialUpdatedCreateQuestionCommand.setId(createQuestionCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreateQuestionCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateQuestionCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll().collectList().block();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateQuestionCommand testCreateQuestionCommand = createQuestionCommandList.get(createQuestionCommandList.size() - 1);
    }

    @Test
    void patchNonExistingCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().collectList().block().size();
        createQuestionCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, createQuestionCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll().collectList().block();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().collectList().block().size();
        createQuestionCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll().collectList().block();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().collectList().block().size();
        createQuestionCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll().collectList().block();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCreateQuestionCommand() {
        // Initialize the database
        createQuestionCommandRepository.save(createQuestionCommand).block();

        int databaseSizeBeforeDelete = createQuestionCommandRepository.findAll().collectList().block().size();

        // Delete the createQuestionCommand
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, createQuestionCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll().collectList().block();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
