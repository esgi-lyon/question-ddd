package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.AnswerSubmitCommand;
import org.contextmapper.generated.gateway.repository.AnswerSubmitCommandRepository;
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
 * Integration tests for the {@link AnswerSubmitCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AnswerSubmitCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/answer-submit-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnswerSubmitCommandRepository answerSubmitCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private AnswerSubmitCommand answerSubmitCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSubmitCommand createEntity(EntityManager em) {
        AnswerSubmitCommand answerSubmitCommand = new AnswerSubmitCommand();
        return answerSubmitCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSubmitCommand createUpdatedEntity(EntityManager em) {
        AnswerSubmitCommand answerSubmitCommand = new AnswerSubmitCommand();
        return answerSubmitCommand;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(AnswerSubmitCommand.class).block();
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
        answerSubmitCommand = createEntity(em);
    }

    @Test
    void createAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeCreate = answerSubmitCommandRepository.findAll().collectList().block().size();
        // Create the AnswerSubmitCommand
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(answerSubmitCommand))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll().collectList().block();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeCreate + 1);
        AnswerSubmitCommand testAnswerSubmitCommand = answerSubmitCommandList.get(answerSubmitCommandList.size() - 1);
    }

    @Test
    void createAnswerSubmitCommandWithExistingId() throws Exception {
        // Create the AnswerSubmitCommand with an existing ID
        answerSubmitCommand.setId(1L);

        int databaseSizeBeforeCreate = answerSubmitCommandRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(answerSubmitCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll().collectList().block();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllAnswerSubmitCommandsAsStream() {
        // Initialize the database
        answerSubmitCommandRepository.save(answerSubmitCommand).block();

        List<AnswerSubmitCommand> answerSubmitCommandList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(AnswerSubmitCommand.class)
            .getResponseBody()
            .filter(answerSubmitCommand::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(answerSubmitCommandList).isNotNull();
        assertThat(answerSubmitCommandList).hasSize(1);
        AnswerSubmitCommand testAnswerSubmitCommand = answerSubmitCommandList.get(0);
    }

    @Test
    void getAllAnswerSubmitCommands() {
        // Initialize the database
        answerSubmitCommandRepository.save(answerSubmitCommand).block();

        // Get all the answerSubmitCommandList
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
            .value(hasItem(answerSubmitCommand.getId().intValue()));
    }

    @Test
    void getAnswerSubmitCommand() {
        // Initialize the database
        answerSubmitCommandRepository.save(answerSubmitCommand).block();

        // Get the answerSubmitCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, answerSubmitCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(answerSubmitCommand.getId().intValue()));
    }

    @Test
    void getNonExistingAnswerSubmitCommand() {
        // Get the answerSubmitCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingAnswerSubmitCommand() throws Exception {
        // Initialize the database
        answerSubmitCommandRepository.save(answerSubmitCommand).block();

        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().collectList().block().size();

        // Update the answerSubmitCommand
        AnswerSubmitCommand updatedAnswerSubmitCommand = answerSubmitCommandRepository.findById(answerSubmitCommand.getId()).block();

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedAnswerSubmitCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedAnswerSubmitCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll().collectList().block();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
        AnswerSubmitCommand testAnswerSubmitCommand = answerSubmitCommandList.get(answerSubmitCommandList.size() - 1);
    }

    @Test
    void putNonExistingAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().collectList().block().size();
        answerSubmitCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, answerSubmitCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(answerSubmitCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll().collectList().block();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().collectList().block().size();
        answerSubmitCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(answerSubmitCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll().collectList().block();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().collectList().block().size();
        answerSubmitCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(answerSubmitCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll().collectList().block();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAnswerSubmitCommandWithPatch() throws Exception {
        // Initialize the database
        answerSubmitCommandRepository.save(answerSubmitCommand).block();

        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().collectList().block().size();

        // Update the answerSubmitCommand using partial update
        AnswerSubmitCommand partialUpdatedAnswerSubmitCommand = new AnswerSubmitCommand();
        partialUpdatedAnswerSubmitCommand.setId(answerSubmitCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAnswerSubmitCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAnswerSubmitCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll().collectList().block();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
        AnswerSubmitCommand testAnswerSubmitCommand = answerSubmitCommandList.get(answerSubmitCommandList.size() - 1);
    }

    @Test
    void fullUpdateAnswerSubmitCommandWithPatch() throws Exception {
        // Initialize the database
        answerSubmitCommandRepository.save(answerSubmitCommand).block();

        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().collectList().block().size();

        // Update the answerSubmitCommand using partial update
        AnswerSubmitCommand partialUpdatedAnswerSubmitCommand = new AnswerSubmitCommand();
        partialUpdatedAnswerSubmitCommand.setId(answerSubmitCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAnswerSubmitCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAnswerSubmitCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll().collectList().block();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
        AnswerSubmitCommand testAnswerSubmitCommand = answerSubmitCommandList.get(answerSubmitCommandList.size() - 1);
    }

    @Test
    void patchNonExistingAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().collectList().block().size();
        answerSubmitCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, answerSubmitCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(answerSubmitCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll().collectList().block();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().collectList().block().size();
        answerSubmitCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(answerSubmitCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll().collectList().block();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().collectList().block().size();
        answerSubmitCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(answerSubmitCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll().collectList().block();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAnswerSubmitCommand() {
        // Initialize the database
        answerSubmitCommandRepository.save(answerSubmitCommand).block();

        int databaseSizeBeforeDelete = answerSubmitCommandRepository.findAll().collectList().block().size();

        // Delete the answerSubmitCommand
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, answerSubmitCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll().collectList().block();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
