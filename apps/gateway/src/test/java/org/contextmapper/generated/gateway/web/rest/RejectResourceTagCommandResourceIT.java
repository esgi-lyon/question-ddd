package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.RejectResourceTagCommand;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.RejectResourceTagCommandRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link RejectResourceTagCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class RejectResourceTagCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/reject-resource-tag-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RejectResourceTagCommandRepository rejectResourceTagCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private RejectResourceTagCommand rejectResourceTagCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RejectResourceTagCommand createEntity(EntityManager em) {
        RejectResourceTagCommand rejectResourceTagCommand = new RejectResourceTagCommand();
        return rejectResourceTagCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RejectResourceTagCommand createUpdatedEntity(EntityManager em) {
        RejectResourceTagCommand rejectResourceTagCommand = new RejectResourceTagCommand();
        return rejectResourceTagCommand;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(RejectResourceTagCommand.class).block();
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
        rejectResourceTagCommand = createEntity(em);
    }

    @Test
    void createRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeCreate = rejectResourceTagCommandRepository.findAll().collectList().block().size();
        // Create the RejectResourceTagCommand
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll().collectList().block();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeCreate + 1);
        RejectResourceTagCommand testRejectResourceTagCommand = rejectResourceTagCommandList.get(rejectResourceTagCommandList.size() - 1);
    }

    @Test
    void createRejectResourceTagCommandWithExistingId() throws Exception {
        // Create the RejectResourceTagCommand with an existing ID
        rejectResourceTagCommand.setId(1L);

        int databaseSizeBeforeCreate = rejectResourceTagCommandRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll().collectList().block();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllRejectResourceTagCommandsAsStream() {
        // Initialize the database
        rejectResourceTagCommandRepository.save(rejectResourceTagCommand).block();

        List<RejectResourceTagCommand> rejectResourceTagCommandList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(RejectResourceTagCommand.class)
            .getResponseBody()
            .filter(rejectResourceTagCommand::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(rejectResourceTagCommandList).isNotNull();
        assertThat(rejectResourceTagCommandList).hasSize(1);
        RejectResourceTagCommand testRejectResourceTagCommand = rejectResourceTagCommandList.get(0);
    }

    @Test
    void getAllRejectResourceTagCommands() {
        // Initialize the database
        rejectResourceTagCommandRepository.save(rejectResourceTagCommand).block();

        // Get all the rejectResourceTagCommandList
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
            .value(hasItem(rejectResourceTagCommand.getId().intValue()));
    }

    @Test
    void getRejectResourceTagCommand() {
        // Initialize the database
        rejectResourceTagCommandRepository.save(rejectResourceTagCommand).block();

        // Get the rejectResourceTagCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, rejectResourceTagCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(rejectResourceTagCommand.getId().intValue()));
    }

    @Test
    void getNonExistingRejectResourceTagCommand() {
        // Get the rejectResourceTagCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingRejectResourceTagCommand() throws Exception {
        // Initialize the database
        rejectResourceTagCommandRepository.save(rejectResourceTagCommand).block();

        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().collectList().block().size();

        // Update the rejectResourceTagCommand
        RejectResourceTagCommand updatedRejectResourceTagCommand = rejectResourceTagCommandRepository
            .findById(rejectResourceTagCommand.getId())
            .block();

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedRejectResourceTagCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedRejectResourceTagCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll().collectList().block();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
        RejectResourceTagCommand testRejectResourceTagCommand = rejectResourceTagCommandList.get(rejectResourceTagCommandList.size() - 1);
    }

    @Test
    void putNonExistingRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().collectList().block().size();
        rejectResourceTagCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, rejectResourceTagCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll().collectList().block();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().collectList().block().size();
        rejectResourceTagCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll().collectList().block();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().collectList().block().size();
        rejectResourceTagCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll().collectList().block();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateRejectResourceTagCommandWithPatch() throws Exception {
        // Initialize the database
        rejectResourceTagCommandRepository.save(rejectResourceTagCommand).block();

        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().collectList().block().size();

        // Update the rejectResourceTagCommand using partial update
        RejectResourceTagCommand partialUpdatedRejectResourceTagCommand = new RejectResourceTagCommand();
        partialUpdatedRejectResourceTagCommand.setId(rejectResourceTagCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedRejectResourceTagCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedRejectResourceTagCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll().collectList().block();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
        RejectResourceTagCommand testRejectResourceTagCommand = rejectResourceTagCommandList.get(rejectResourceTagCommandList.size() - 1);
    }

    @Test
    void fullUpdateRejectResourceTagCommandWithPatch() throws Exception {
        // Initialize the database
        rejectResourceTagCommandRepository.save(rejectResourceTagCommand).block();

        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().collectList().block().size();

        // Update the rejectResourceTagCommand using partial update
        RejectResourceTagCommand partialUpdatedRejectResourceTagCommand = new RejectResourceTagCommand();
        partialUpdatedRejectResourceTagCommand.setId(rejectResourceTagCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedRejectResourceTagCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedRejectResourceTagCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll().collectList().block();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
        RejectResourceTagCommand testRejectResourceTagCommand = rejectResourceTagCommandList.get(rejectResourceTagCommandList.size() - 1);
    }

    @Test
    void patchNonExistingRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().collectList().block().size();
        rejectResourceTagCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, rejectResourceTagCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll().collectList().block();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().collectList().block().size();
        rejectResourceTagCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll().collectList().block();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().collectList().block().size();
        rejectResourceTagCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll().collectList().block();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteRejectResourceTagCommand() {
        // Initialize the database
        rejectResourceTagCommandRepository.save(rejectResourceTagCommand).block();

        int databaseSizeBeforeDelete = rejectResourceTagCommandRepository.findAll().collectList().block().size();

        // Delete the rejectResourceTagCommand
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, rejectResourceTagCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll().collectList().block();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
