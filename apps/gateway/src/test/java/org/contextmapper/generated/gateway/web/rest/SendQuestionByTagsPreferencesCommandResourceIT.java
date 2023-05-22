package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.SendQuestionByTagsPreferencesCommand;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.SendQuestionByTagsPreferencesCommandRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link SendQuestionByTagsPreferencesCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SendQuestionByTagsPreferencesCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/send-question-by-tags-preferences-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SendQuestionByTagsPreferencesCommandRepository sendQuestionByTagsPreferencesCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SendQuestionByTagsPreferencesCommand createEntity(EntityManager em) {
        SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand = new SendQuestionByTagsPreferencesCommand();
        return sendQuestionByTagsPreferencesCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SendQuestionByTagsPreferencesCommand createUpdatedEntity(EntityManager em) {
        SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand = new SendQuestionByTagsPreferencesCommand();
        return sendQuestionByTagsPreferencesCommand;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SendQuestionByTagsPreferencesCommand.class).block();
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
        sendQuestionByTagsPreferencesCommand = createEntity(em);
    }

    @Test
    void createSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeCreate = sendQuestionByTagsPreferencesCommandRepository.findAll().collectList().block().size();
        // Create the SendQuestionByTagsPreferencesCommand
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeCreate + 1);
        SendQuestionByTagsPreferencesCommand testSendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommandList.get(
            sendQuestionByTagsPreferencesCommandList.size() - 1
        );
    }

    @Test
    void createSendQuestionByTagsPreferencesCommandWithExistingId() throws Exception {
        // Create the SendQuestionByTagsPreferencesCommand with an existing ID
        sendQuestionByTagsPreferencesCommand.setId(1L);

        int databaseSizeBeforeCreate = sendQuestionByTagsPreferencesCommandRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSendQuestionByTagsPreferencesCommandsAsStream() {
        // Initialize the database
        sendQuestionByTagsPreferencesCommandRepository.save(sendQuestionByTagsPreferencesCommand).block();

        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(SendQuestionByTagsPreferencesCommand.class)
            .getResponseBody()
            .filter(sendQuestionByTagsPreferencesCommand::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(sendQuestionByTagsPreferencesCommandList).isNotNull();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(1);
        SendQuestionByTagsPreferencesCommand testSendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommandList.get(0);
    }

    @Test
    void getAllSendQuestionByTagsPreferencesCommands() {
        // Initialize the database
        sendQuestionByTagsPreferencesCommandRepository.save(sendQuestionByTagsPreferencesCommand).block();

        // Get all the sendQuestionByTagsPreferencesCommandList
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
            .value(hasItem(sendQuestionByTagsPreferencesCommand.getId().intValue()));
    }

    @Test
    void getSendQuestionByTagsPreferencesCommand() {
        // Initialize the database
        sendQuestionByTagsPreferencesCommandRepository.save(sendQuestionByTagsPreferencesCommand).block();

        // Get the sendQuestionByTagsPreferencesCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, sendQuestionByTagsPreferencesCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(sendQuestionByTagsPreferencesCommand.getId().intValue()));
    }

    @Test
    void getNonExistingSendQuestionByTagsPreferencesCommand() {
        // Get the sendQuestionByTagsPreferencesCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSendQuestionByTagsPreferencesCommand() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesCommandRepository.save(sendQuestionByTagsPreferencesCommand).block();

        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().collectList().block().size();

        // Update the sendQuestionByTagsPreferencesCommand
        SendQuestionByTagsPreferencesCommand updatedSendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommandRepository
            .findById(sendQuestionByTagsPreferencesCommand.getId())
            .block();

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedSendQuestionByTagsPreferencesCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedSendQuestionByTagsPreferencesCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
        SendQuestionByTagsPreferencesCommand testSendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommandList.get(
            sendQuestionByTagsPreferencesCommandList.size() - 1
        );
    }

    @Test
    void putNonExistingSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().collectList().block().size();
        sendQuestionByTagsPreferencesCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, sendQuestionByTagsPreferencesCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().collectList().block().size();
        sendQuestionByTagsPreferencesCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().collectList().block().size();
        sendQuestionByTagsPreferencesCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSendQuestionByTagsPreferencesCommandWithPatch() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesCommandRepository.save(sendQuestionByTagsPreferencesCommand).block();

        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().collectList().block().size();

        // Update the sendQuestionByTagsPreferencesCommand using partial update
        SendQuestionByTagsPreferencesCommand partialUpdatedSendQuestionByTagsPreferencesCommand = new SendQuestionByTagsPreferencesCommand();
        partialUpdatedSendQuestionByTagsPreferencesCommand.setId(sendQuestionByTagsPreferencesCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSendQuestionByTagsPreferencesCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSendQuestionByTagsPreferencesCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
        SendQuestionByTagsPreferencesCommand testSendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommandList.get(
            sendQuestionByTagsPreferencesCommandList.size() - 1
        );
    }

    @Test
    void fullUpdateSendQuestionByTagsPreferencesCommandWithPatch() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesCommandRepository.save(sendQuestionByTagsPreferencesCommand).block();

        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().collectList().block().size();

        // Update the sendQuestionByTagsPreferencesCommand using partial update
        SendQuestionByTagsPreferencesCommand partialUpdatedSendQuestionByTagsPreferencesCommand = new SendQuestionByTagsPreferencesCommand();
        partialUpdatedSendQuestionByTagsPreferencesCommand.setId(sendQuestionByTagsPreferencesCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSendQuestionByTagsPreferencesCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSendQuestionByTagsPreferencesCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
        SendQuestionByTagsPreferencesCommand testSendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommandList.get(
            sendQuestionByTagsPreferencesCommandList.size() - 1
        );
    }

    @Test
    void patchNonExistingSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().collectList().block().size();
        sendQuestionByTagsPreferencesCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, sendQuestionByTagsPreferencesCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().collectList().block().size();
        sendQuestionByTagsPreferencesCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().collectList().block().size();
        sendQuestionByTagsPreferencesCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSendQuestionByTagsPreferencesCommand() {
        // Initialize the database
        sendQuestionByTagsPreferencesCommandRepository.save(sendQuestionByTagsPreferencesCommand).block();

        int databaseSizeBeforeDelete = sendQuestionByTagsPreferencesCommandRepository.findAll().collectList().block().size();

        // Delete the sendQuestionByTagsPreferencesCommand
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, sendQuestionByTagsPreferencesCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
