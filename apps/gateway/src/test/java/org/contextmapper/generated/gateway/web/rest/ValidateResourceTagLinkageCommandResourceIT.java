package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.ValidateResourceTagLinkageCommand;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.ValidateResourceTagLinkageCommandRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link ValidateResourceTagLinkageCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ValidateResourceTagLinkageCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/validate-resource-tag-linkage-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ValidateResourceTagLinkageCommandRepository validateResourceTagLinkageCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ValidateResourceTagLinkageCommand createEntity(EntityManager em) {
        ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand = new ValidateResourceTagLinkageCommand();
        return validateResourceTagLinkageCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ValidateResourceTagLinkageCommand createUpdatedEntity(EntityManager em) {
        ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand = new ValidateResourceTagLinkageCommand();
        return validateResourceTagLinkageCommand;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ValidateResourceTagLinkageCommand.class).block();
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
        validateResourceTagLinkageCommand = createEntity(em);
    }

    @Test
    void createValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeCreate = validateResourceTagLinkageCommandRepository.findAll().collectList().block().size();
        // Create the ValidateResourceTagLinkageCommand
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommand))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeCreate + 1);
        ValidateResourceTagLinkageCommand testValidateResourceTagLinkageCommand = validateResourceTagLinkageCommandList.get(
            validateResourceTagLinkageCommandList.size() - 1
        );
    }

    @Test
    void createValidateResourceTagLinkageCommandWithExistingId() throws Exception {
        // Create the ValidateResourceTagLinkageCommand with an existing ID
        validateResourceTagLinkageCommand.setId(1L);

        int databaseSizeBeforeCreate = validateResourceTagLinkageCommandRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllValidateResourceTagLinkageCommandsAsStream() {
        // Initialize the database
        validateResourceTagLinkageCommandRepository.save(validateResourceTagLinkageCommand).block();

        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(ValidateResourceTagLinkageCommand.class)
            .getResponseBody()
            .filter(validateResourceTagLinkageCommand::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(validateResourceTagLinkageCommandList).isNotNull();
        assertThat(validateResourceTagLinkageCommandList).hasSize(1);
        ValidateResourceTagLinkageCommand testValidateResourceTagLinkageCommand = validateResourceTagLinkageCommandList.get(0);
    }

    @Test
    void getAllValidateResourceTagLinkageCommands() {
        // Initialize the database
        validateResourceTagLinkageCommandRepository.save(validateResourceTagLinkageCommand).block();

        // Get all the validateResourceTagLinkageCommandList
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
            .value(hasItem(validateResourceTagLinkageCommand.getId().intValue()));
    }

    @Test
    void getValidateResourceTagLinkageCommand() {
        // Initialize the database
        validateResourceTagLinkageCommandRepository.save(validateResourceTagLinkageCommand).block();

        // Get the validateResourceTagLinkageCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, validateResourceTagLinkageCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(validateResourceTagLinkageCommand.getId().intValue()));
    }

    @Test
    void getNonExistingValidateResourceTagLinkageCommand() {
        // Get the validateResourceTagLinkageCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingValidateResourceTagLinkageCommand() throws Exception {
        // Initialize the database
        validateResourceTagLinkageCommandRepository.save(validateResourceTagLinkageCommand).block();

        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().collectList().block().size();

        // Update the validateResourceTagLinkageCommand
        ValidateResourceTagLinkageCommand updatedValidateResourceTagLinkageCommand = validateResourceTagLinkageCommandRepository
            .findById(validateResourceTagLinkageCommand.getId())
            .block();

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedValidateResourceTagLinkageCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedValidateResourceTagLinkageCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
        ValidateResourceTagLinkageCommand testValidateResourceTagLinkageCommand = validateResourceTagLinkageCommandList.get(
            validateResourceTagLinkageCommandList.size() - 1
        );
    }

    @Test
    void putNonExistingValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().collectList().block().size();
        validateResourceTagLinkageCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, validateResourceTagLinkageCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().collectList().block().size();
        validateResourceTagLinkageCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().collectList().block().size();
        validateResourceTagLinkageCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateValidateResourceTagLinkageCommandWithPatch() throws Exception {
        // Initialize the database
        validateResourceTagLinkageCommandRepository.save(validateResourceTagLinkageCommand).block();

        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().collectList().block().size();

        // Update the validateResourceTagLinkageCommand using partial update
        ValidateResourceTagLinkageCommand partialUpdatedValidateResourceTagLinkageCommand = new ValidateResourceTagLinkageCommand();
        partialUpdatedValidateResourceTagLinkageCommand.setId(validateResourceTagLinkageCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedValidateResourceTagLinkageCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedValidateResourceTagLinkageCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
        ValidateResourceTagLinkageCommand testValidateResourceTagLinkageCommand = validateResourceTagLinkageCommandList.get(
            validateResourceTagLinkageCommandList.size() - 1
        );
    }

    @Test
    void fullUpdateValidateResourceTagLinkageCommandWithPatch() throws Exception {
        // Initialize the database
        validateResourceTagLinkageCommandRepository.save(validateResourceTagLinkageCommand).block();

        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().collectList().block().size();

        // Update the validateResourceTagLinkageCommand using partial update
        ValidateResourceTagLinkageCommand partialUpdatedValidateResourceTagLinkageCommand = new ValidateResourceTagLinkageCommand();
        partialUpdatedValidateResourceTagLinkageCommand.setId(validateResourceTagLinkageCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedValidateResourceTagLinkageCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedValidateResourceTagLinkageCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
        ValidateResourceTagLinkageCommand testValidateResourceTagLinkageCommand = validateResourceTagLinkageCommandList.get(
            validateResourceTagLinkageCommandList.size() - 1
        );
    }

    @Test
    void patchNonExistingValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().collectList().block().size();
        validateResourceTagLinkageCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, validateResourceTagLinkageCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().collectList().block().size();
        validateResourceTagLinkageCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().collectList().block().size();
        validateResourceTagLinkageCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteValidateResourceTagLinkageCommand() {
        // Initialize the database
        validateResourceTagLinkageCommandRepository.save(validateResourceTagLinkageCommand).block();

        int databaseSizeBeforeDelete = validateResourceTagLinkageCommandRepository.findAll().collectList().block().size();

        // Delete the validateResourceTagLinkageCommand
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, validateResourceTagLinkageCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository
            .findAll()
            .collectList()
            .block();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
