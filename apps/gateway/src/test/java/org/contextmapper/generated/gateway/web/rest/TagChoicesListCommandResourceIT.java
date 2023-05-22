package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.TagChoicesListCommand;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.TagChoicesListCommandRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link TagChoicesListCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class TagChoicesListCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/tag-choices-list-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagChoicesListCommandRepository tagChoicesListCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private TagChoicesListCommand tagChoicesListCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagChoicesListCommand createEntity(EntityManager em) {
        TagChoicesListCommand tagChoicesListCommand = new TagChoicesListCommand();
        return tagChoicesListCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagChoicesListCommand createUpdatedEntity(EntityManager em) {
        TagChoicesListCommand tagChoicesListCommand = new TagChoicesListCommand();
        return tagChoicesListCommand;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(TagChoicesListCommand.class).block();
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
        tagChoicesListCommand = createEntity(em);
    }

    @Test
    void createTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeCreate = tagChoicesListCommandRepository.findAll().collectList().block().size();
        // Create the TagChoicesListCommand
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tagChoicesListCommand))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll().collectList().block();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeCreate + 1);
        TagChoicesListCommand testTagChoicesListCommand = tagChoicesListCommandList.get(tagChoicesListCommandList.size() - 1);
    }

    @Test
    void createTagChoicesListCommandWithExistingId() throws Exception {
        // Create the TagChoicesListCommand with an existing ID
        tagChoicesListCommand.setId(1L);

        int databaseSizeBeforeCreate = tagChoicesListCommandRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tagChoicesListCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll().collectList().block();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllTagChoicesListCommandsAsStream() {
        // Initialize the database
        tagChoicesListCommandRepository.save(tagChoicesListCommand).block();

        List<TagChoicesListCommand> tagChoicesListCommandList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(TagChoicesListCommand.class)
            .getResponseBody()
            .filter(tagChoicesListCommand::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(tagChoicesListCommandList).isNotNull();
        assertThat(tagChoicesListCommandList).hasSize(1);
        TagChoicesListCommand testTagChoicesListCommand = tagChoicesListCommandList.get(0);
    }

    @Test
    void getAllTagChoicesListCommands() {
        // Initialize the database
        tagChoicesListCommandRepository.save(tagChoicesListCommand).block();

        // Get all the tagChoicesListCommandList
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
            .value(hasItem(tagChoicesListCommand.getId().intValue()));
    }

    @Test
    void getTagChoicesListCommand() {
        // Initialize the database
        tagChoicesListCommandRepository.save(tagChoicesListCommand).block();

        // Get the tagChoicesListCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, tagChoicesListCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(tagChoicesListCommand.getId().intValue()));
    }

    @Test
    void getNonExistingTagChoicesListCommand() {
        // Get the tagChoicesListCommand
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingTagChoicesListCommand() throws Exception {
        // Initialize the database
        tagChoicesListCommandRepository.save(tagChoicesListCommand).block();

        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().collectList().block().size();

        // Update the tagChoicesListCommand
        TagChoicesListCommand updatedTagChoicesListCommand = tagChoicesListCommandRepository
            .findById(tagChoicesListCommand.getId())
            .block();

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedTagChoicesListCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedTagChoicesListCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll().collectList().block();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
        TagChoicesListCommand testTagChoicesListCommand = tagChoicesListCommandList.get(tagChoicesListCommandList.size() - 1);
    }

    @Test
    void putNonExistingTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().collectList().block().size();
        tagChoicesListCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, tagChoicesListCommand.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tagChoicesListCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll().collectList().block();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().collectList().block().size();
        tagChoicesListCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tagChoicesListCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll().collectList().block();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().collectList().block().size();
        tagChoicesListCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tagChoicesListCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll().collectList().block();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateTagChoicesListCommandWithPatch() throws Exception {
        // Initialize the database
        tagChoicesListCommandRepository.save(tagChoicesListCommand).block();

        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().collectList().block().size();

        // Update the tagChoicesListCommand using partial update
        TagChoicesListCommand partialUpdatedTagChoicesListCommand = new TagChoicesListCommand();
        partialUpdatedTagChoicesListCommand.setId(tagChoicesListCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedTagChoicesListCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedTagChoicesListCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll().collectList().block();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
        TagChoicesListCommand testTagChoicesListCommand = tagChoicesListCommandList.get(tagChoicesListCommandList.size() - 1);
    }

    @Test
    void fullUpdateTagChoicesListCommandWithPatch() throws Exception {
        // Initialize the database
        tagChoicesListCommandRepository.save(tagChoicesListCommand).block();

        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().collectList().block().size();

        // Update the tagChoicesListCommand using partial update
        TagChoicesListCommand partialUpdatedTagChoicesListCommand = new TagChoicesListCommand();
        partialUpdatedTagChoicesListCommand.setId(tagChoicesListCommand.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedTagChoicesListCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedTagChoicesListCommand))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll().collectList().block();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
        TagChoicesListCommand testTagChoicesListCommand = tagChoicesListCommandList.get(tagChoicesListCommandList.size() - 1);
    }

    @Test
    void patchNonExistingTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().collectList().block().size();
        tagChoicesListCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, tagChoicesListCommand.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tagChoicesListCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll().collectList().block();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().collectList().block().size();
        tagChoicesListCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tagChoicesListCommand))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll().collectList().block();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().collectList().block().size();
        tagChoicesListCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tagChoicesListCommand))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll().collectList().block();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteTagChoicesListCommand() {
        // Initialize the database
        tagChoicesListCommandRepository.save(tagChoicesListCommand).block();

        int databaseSizeBeforeDelete = tagChoicesListCommandRepository.findAll().collectList().block().size();

        // Delete the tagChoicesListCommand
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, tagChoicesListCommand.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll().collectList().block();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
