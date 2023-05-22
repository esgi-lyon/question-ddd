package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.AwardPointForEvaluationCommand;
import org.contextmapper.generated.gateway.repository.AwardPointForEvaluationCommandRepository;
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
 * Integration tests for the {@link AwardPointForEvaluationCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AwardPointForEvaluationCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/award-point-for-evaluation-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AwardPointForEvaluationCommandRepository awardPointForEvaluationCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private AwardPointForEvaluationCommand awardPointForEvaluationCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AwardPointForEvaluationCommand createEntity(EntityManager em) {
        AwardPointForEvaluationCommand awardPointForEvaluationCommand = new AwardPointForEvaluationCommand();
        return awardPointForEvaluationCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AwardPointForEvaluationCommand createUpdatedEntity(EntityManager em) {
        AwardPointForEvaluationCommand awardPointForEvaluationCommand = new AwardPointForEvaluationCommand();
        return awardPointForEvaluationCommand;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(AwardPointForEvaluationCommand.class).block();
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
        awardPointForEvaluationCommand = createEntity(em);
    }

    @Test
    void createAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeCreate = awardPointForEvaluationCommandRepository.findAll().collectList().block().size();
        // Create the AwardPointForEvaluationCommand
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommand))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeCreate + 1);
        AwardPointForEvaluationCommand testAwardPointForEvaluationCommand = awardPointForEvaluationCommandList.get(
            awardPointForEvaluationCommandList.size() - 1
        );
    }

    @Test
    void createAwardPointForEvaluationCommandWithExistingId() throws Exception {
        // Create the AwardPointForEvaluationCommand with an existing ID
        awardPointForEvaluationCommand.setId(1L);

        int databaseSizeBeforeCreate = awardPointForEvaluationCommandRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllAwardPointForEvaluationCommandsAsStream() {
        // Initialize the database
        awardPointForEvaluationCommandRepository.save(awardPointForEvaluationCommand).block();

        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(AwardPointForEvaluationCommand.class)
            .getResponseBody()
            .filter(awardPointForEvaluationCommand::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(awardPointForEvaluationCommandList).isNotNull();
        assertThat(awardPointForEvaluationCommandList).hasSize(1);
        AwardPointForEvaluationCommand testAwardPointForEvaluationCommand = awardPointForEvaluationCommandList.get(0);
    }

    @Test
    void getAllAwardPointForEvaluationCommands() {
        // Initialize the database
        awardPointForEvaluationCommandRepository.save(awardPointForEvaluationCommand).block();

        // Get all the awardPointForEvaluationCommandList
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
            .value(hasItem(awardPointForEvaluationCommand.getId().intValue()));
    }

    @Test
    void getAwardPointForEvaluationCommand() {
        // Initialize the database
        awardPointForEvaluationCommandRepository.save(awardPointForEvaluationCommand).block();

        // Get the awardPointForEvaluationCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, awardPointForEvaluationCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(awardPointForEvaluationCommand.getId().intValue()));
    }

    @Test
    void getNonExistingAwardPointForEvaluationCommand() {
        // Get the awardPointForEvaluationCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingAwardPointForEvaluationCommand() throws Exception {
        // Initialize the database
        awardPointForEvaluationCommandRepository.save(awardPointForEvaluationCommand).block();

        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().collectList().block().size();

        // Update the awardPointForEvaluationCommand
        AwardPointForEvaluationCommand updatedAwardPointForEvaluationCommand = awardPointForEvaluationCommandRepository
            .findById(awardPointForEvaluationCommand.getId())
            .block();

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedAwardPointForEvaluationCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedAwardPointForEvaluationCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        AwardPointForEvaluationCommand testAwardPointForEvaluationCommand = awardPointForEvaluationCommandList.get(
            awardPointForEvaluationCommandList.size() - 1
        );
    }

    @Test
    void putNonExistingAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().collectList().block().size();
        awardPointForEvaluationCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, awardPointForEvaluationCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().collectList().block().size();
        awardPointForEvaluationCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().collectList().block().size();
        awardPointForEvaluationCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAwardPointForEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        awardPointForEvaluationCommandRepository.save(awardPointForEvaluationCommand).block();

        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().collectList().block().size();

        // Update the awardPointForEvaluationCommand using partial update
        AwardPointForEvaluationCommand partialUpdatedAwardPointForEvaluationCommand = new AwardPointForEvaluationCommand();
        partialUpdatedAwardPointForEvaluationCommand.setId(awardPointForEvaluationCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAwardPointForEvaluationCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAwardPointForEvaluationCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        AwardPointForEvaluationCommand testAwardPointForEvaluationCommand = awardPointForEvaluationCommandList.get(
            awardPointForEvaluationCommandList.size() - 1
        );
    }

    @Test
    void fullUpdateAwardPointForEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        awardPointForEvaluationCommandRepository.save(awardPointForEvaluationCommand).block();

        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().collectList().block().size();

        // Update the awardPointForEvaluationCommand using partial update
        AwardPointForEvaluationCommand partialUpdatedAwardPointForEvaluationCommand = new AwardPointForEvaluationCommand();
        partialUpdatedAwardPointForEvaluationCommand.setId(awardPointForEvaluationCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAwardPointForEvaluationCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAwardPointForEvaluationCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        AwardPointForEvaluationCommand testAwardPointForEvaluationCommand = awardPointForEvaluationCommandList.get(
            awardPointForEvaluationCommandList.size() - 1
        );
    }

    @Test
    void patchNonExistingAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().collectList().block().size();
        awardPointForEvaluationCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, awardPointForEvaluationCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().collectList().block().size();
        awardPointForEvaluationCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().collectList().block().size();
        awardPointForEvaluationCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAwardPointForEvaluationCommand() {
        // Initialize the database
        awardPointForEvaluationCommandRepository.save(awardPointForEvaluationCommand).block();

        int databaseSizeBeforeDelete = awardPointForEvaluationCommandRepository.findAll().collectList().block().size();

        // Delete the awardPointForEvaluationCommand
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, awardPointForEvaluationCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
