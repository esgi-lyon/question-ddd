package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.CheckAnswerCommand;
import org.contextmapper.generated.gateway.repository.CheckAnswerCommandRepository;
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
 * Integration tests for the {@link CheckAnswerCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CheckAnswerCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/check-answer-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CheckAnswerCommandRepository checkAnswerCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CheckAnswerCommand checkAnswerCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CheckAnswerCommand createEntity(EntityManager em) {
        CheckAnswerCommand checkAnswerCommand = new CheckAnswerCommand();
        return checkAnswerCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CheckAnswerCommand createUpdatedEntity(EntityManager em) {
        CheckAnswerCommand checkAnswerCommand = new CheckAnswerCommand();
        return checkAnswerCommand;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CheckAnswerCommand.class).block();
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
        checkAnswerCommand = createEntity(em);
    }

    @Test
    void createCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeCreate = checkAnswerCommandRepository.findAll().collectList().block().size();
        // Create the CheckAnswerCommand
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll().collectList().block();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeCreate + 1);
        CheckAnswerCommand testCheckAnswerCommand = checkAnswerCommandList.get(checkAnswerCommandList.size() - 1);
    }

    @Test
    void createCheckAnswerCommandWithExistingId() throws Exception {
        // Create the CheckAnswerCommand with an existing ID
        checkAnswerCommand.setId(1L);

        int databaseSizeBeforeCreate = checkAnswerCommandRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll().collectList().block();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCheckAnswerCommandsAsStream() {
        // Initialize the database
        checkAnswerCommandRepository.save(checkAnswerCommand).block();

        List<CheckAnswerCommand> checkAnswerCommandList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(CheckAnswerCommand.class)
            .getResponseBody()
            .filter(checkAnswerCommand::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(checkAnswerCommandList).isNotNull();
        assertThat(checkAnswerCommandList).hasSize(1);
        CheckAnswerCommand testCheckAnswerCommand = checkAnswerCommandList.get(0);
    }

    @Test
    void getAllCheckAnswerCommands() {
        // Initialize the database
        checkAnswerCommandRepository.save(checkAnswerCommand).block();

        // Get all the checkAnswerCommandList
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
            .value(hasItem(checkAnswerCommand.getId().intValue()));
    }

    @Test
    void getCheckAnswerCommand() {
        // Initialize the database
        checkAnswerCommandRepository.save(checkAnswerCommand).block();

        // Get the checkAnswerCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, checkAnswerCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(checkAnswerCommand.getId().intValue()));
    }

    @Test
    void getNonExistingCheckAnswerCommand() {
        // Get the checkAnswerCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCheckAnswerCommand() throws Exception {
        // Initialize the database
        checkAnswerCommandRepository.save(checkAnswerCommand).block();

        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().collectList().block().size();

        // Update the checkAnswerCommand
        CheckAnswerCommand updatedCheckAnswerCommand = checkAnswerCommandRepository.findById(checkAnswerCommand.getId()).block();

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedCheckAnswerCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedCheckAnswerCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll().collectList().block();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
        CheckAnswerCommand testCheckAnswerCommand = checkAnswerCommandList.get(checkAnswerCommandList.size() - 1);
    }

    @Test
    void putNonExistingCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().collectList().block().size();
        checkAnswerCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, checkAnswerCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll().collectList().block();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().collectList().block().size();
        checkAnswerCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll().collectList().block();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().collectList().block().size();
        checkAnswerCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll().collectList().block();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCheckAnswerCommandWithPatch() throws Exception {
        // Initialize the database
        checkAnswerCommandRepository.save(checkAnswerCommand).block();

        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().collectList().block().size();

        // Update the checkAnswerCommand using partial update
        CheckAnswerCommand partialUpdatedCheckAnswerCommand = new CheckAnswerCommand();
        partialUpdatedCheckAnswerCommand.setId(checkAnswerCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCheckAnswerCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCheckAnswerCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll().collectList().block();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
        CheckAnswerCommand testCheckAnswerCommand = checkAnswerCommandList.get(checkAnswerCommandList.size() - 1);
    }

    @Test
    void fullUpdateCheckAnswerCommandWithPatch() throws Exception {
        // Initialize the database
        checkAnswerCommandRepository.save(checkAnswerCommand).block();

        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().collectList().block().size();

        // Update the checkAnswerCommand using partial update
        CheckAnswerCommand partialUpdatedCheckAnswerCommand = new CheckAnswerCommand();
        partialUpdatedCheckAnswerCommand.setId(checkAnswerCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCheckAnswerCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCheckAnswerCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll().collectList().block();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
        CheckAnswerCommand testCheckAnswerCommand = checkAnswerCommandList.get(checkAnswerCommandList.size() - 1);
    }

    @Test
    void patchNonExistingCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().collectList().block().size();
        checkAnswerCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, checkAnswerCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll().collectList().block();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().collectList().block().size();
        checkAnswerCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll().collectList().block();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().collectList().block().size();
        checkAnswerCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll().collectList().block();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCheckAnswerCommand() {
        // Initialize the database
        checkAnswerCommandRepository.save(checkAnswerCommand).block();

        int databaseSizeBeforeDelete = checkAnswerCommandRepository.findAll().collectList().block().size();

        // Delete the checkAnswerCommand
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, checkAnswerCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll().collectList().block();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
