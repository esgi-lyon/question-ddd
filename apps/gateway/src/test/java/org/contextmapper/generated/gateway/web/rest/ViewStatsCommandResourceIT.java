package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.ViewStatsCommand;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.ViewStatsCommandRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link ViewStatsCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ViewStatsCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/view-stats-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ViewStatsCommandRepository viewStatsCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ViewStatsCommand viewStatsCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewStatsCommand createEntity(EntityManager em) {
        ViewStatsCommand viewStatsCommand = new ViewStatsCommand();
        return viewStatsCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewStatsCommand createUpdatedEntity(EntityManager em) {
        ViewStatsCommand viewStatsCommand = new ViewStatsCommand();
        return viewStatsCommand;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ViewStatsCommand.class).block();
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
        viewStatsCommand = createEntity(em);
    }

    @Test
    void createViewStatsCommand() throws Exception {
        int databaseSizeBeforeCreate = viewStatsCommandRepository.findAll().collectList().block().size();
        // Create the ViewStatsCommand
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(viewStatsCommand))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll().collectList().block();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeCreate + 1);
        ViewStatsCommand testViewStatsCommand = viewStatsCommandList.get(viewStatsCommandList.size() - 1);
    }

    @Test
    void createViewStatsCommandWithExistingId() throws Exception {
        // Create the ViewStatsCommand with an existing ID
        viewStatsCommand.setId(1L);

        int databaseSizeBeforeCreate = viewStatsCommandRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(viewStatsCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll().collectList().block();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllViewStatsCommandsAsStream() {
        // Initialize the database
        viewStatsCommandRepository.save(viewStatsCommand).block();

        List<ViewStatsCommand> viewStatsCommandList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(ViewStatsCommand.class)
            .getResponseBody()
            .filter(viewStatsCommand::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(viewStatsCommandList).isNotNull();
        assertThat(viewStatsCommandList).hasSize(1);
        ViewStatsCommand testViewStatsCommand = viewStatsCommandList.get(0);
    }

    @Test
    void getAllViewStatsCommands() {
        // Initialize the database
        viewStatsCommandRepository.save(viewStatsCommand).block();

        // Get all the viewStatsCommandList
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
            .value(hasItem(viewStatsCommand.getId().intValue()));
    }

    @Test
    void getViewStatsCommand() {
        // Initialize the database
        viewStatsCommandRepository.save(viewStatsCommand).block();

        // Get the viewStatsCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, viewStatsCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(viewStatsCommand.getId().intValue()));
    }

    @Test
    void getNonExistingViewStatsCommand() {
        // Get the viewStatsCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingViewStatsCommand() throws Exception {
        // Initialize the database
        viewStatsCommandRepository.save(viewStatsCommand).block();

        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().collectList().block().size();

        // Update the viewStatsCommand
        ViewStatsCommand updatedViewStatsCommand = viewStatsCommandRepository.findById(viewStatsCommand.getId()).block();

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedViewStatsCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedViewStatsCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll().collectList().block();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewStatsCommand testViewStatsCommand = viewStatsCommandList.get(viewStatsCommandList.size() - 1);
    }

    @Test
    void putNonExistingViewStatsCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().collectList().block().size();
        viewStatsCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, viewStatsCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(viewStatsCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll().collectList().block();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchViewStatsCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().collectList().block().size();
        viewStatsCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(viewStatsCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll().collectList().block();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamViewStatsCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().collectList().block().size();
        viewStatsCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(viewStatsCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll().collectList().block();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateViewStatsCommandWithPatch() throws Exception {
        // Initialize the database
        viewStatsCommandRepository.save(viewStatsCommand).block();

        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().collectList().block().size();

        // Update the viewStatsCommand using partial update
        ViewStatsCommand partialUpdatedViewStatsCommand = new ViewStatsCommand();
        partialUpdatedViewStatsCommand.setId(viewStatsCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedViewStatsCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedViewStatsCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll().collectList().block();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewStatsCommand testViewStatsCommand = viewStatsCommandList.get(viewStatsCommandList.size() - 1);
    }

    @Test
    void fullUpdateViewStatsCommandWithPatch() throws Exception {
        // Initialize the database
        viewStatsCommandRepository.save(viewStatsCommand).block();

        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().collectList().block().size();

        // Update the viewStatsCommand using partial update
        ViewStatsCommand partialUpdatedViewStatsCommand = new ViewStatsCommand();
        partialUpdatedViewStatsCommand.setId(viewStatsCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedViewStatsCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedViewStatsCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll().collectList().block();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewStatsCommand testViewStatsCommand = viewStatsCommandList.get(viewStatsCommandList.size() - 1);
    }

    @Test
    void patchNonExistingViewStatsCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().collectList().block().size();
        viewStatsCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, viewStatsCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(viewStatsCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll().collectList().block();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchViewStatsCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().collectList().block().size();
        viewStatsCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(viewStatsCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll().collectList().block();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamViewStatsCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().collectList().block().size();
        viewStatsCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(viewStatsCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll().collectList().block();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteViewStatsCommand() {
        // Initialize the database
        viewStatsCommandRepository.save(viewStatsCommand).block();

        int databaseSizeBeforeDelete = viewStatsCommandRepository.findAll().collectList().block().size();

        // Delete the viewStatsCommand
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, viewStatsCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll().collectList().block();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
