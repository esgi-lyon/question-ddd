package org.contextmapper.generated.sendquestioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.sendquestioncontext.IntegrationTest;
import org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionsCommand;
import org.contextmapper.generated.sendquestioncontext.repository.PrepareQuestionsCommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PrepareQuestionsCommandResourceHandler} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PrepareQuestionsCommandResourceHandlerIT {

    private static final Integer DEFAULT_TAG_TO_PREPARE_QUESTIONS = 1;
    private static final Integer UPDATED_TAG_TO_PREPARE_QUESTIONS = 2;

    private static final String ENTITY_API_URL = "/api/prepare-questions-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PrepareQuestionsCommandRepository prepareQuestionsCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrepareQuestionsCommandMockMvc;

    private PrepareQuestionsCommand prepareQuestionsCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrepareQuestionsCommand createEntity(EntityManager em) {
        PrepareQuestionsCommand prepareQuestionsCommand = new PrepareQuestionsCommand()
            .tagToPrepareQuestions(DEFAULT_TAG_TO_PREPARE_QUESTIONS);
        return prepareQuestionsCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrepareQuestionsCommand createUpdatedEntity(EntityManager em) {
        PrepareQuestionsCommand prepareQuestionsCommand = new PrepareQuestionsCommand()
            .tagToPrepareQuestions(UPDATED_TAG_TO_PREPARE_QUESTIONS);
        return prepareQuestionsCommand;
    }

    @BeforeEach
    public void initTest() {
        prepareQuestionsCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createPrepareQuestionsCommand() throws Exception {
        int databaseSizeBeforeCreate = prepareQuestionsCommandRepository.findAll().size();
        // Create the PrepareQuestionsCommand
        restPrepareQuestionsCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionsCommand))
            )
            .andExpect(status().isCreated());

        // Validate the PrepareQuestionsCommand in the database
        List<PrepareQuestionsCommand> prepareQuestionsCommandList = prepareQuestionsCommandRepository.findAll();
        assertThat(prepareQuestionsCommandList).hasSize(databaseSizeBeforeCreate + 1);
        PrepareQuestionsCommand testPrepareQuestionsCommand = prepareQuestionsCommandList.get(prepareQuestionsCommandList.size() - 1);
        assertThat(testPrepareQuestionsCommand.getTagToPrepareQuestions()).isEqualTo(DEFAULT_TAG_TO_PREPARE_QUESTIONS);
    }

    @Test
    @Transactional
    void createPrepareQuestionsCommandWithExistingId() throws Exception {
        // Create the PrepareQuestionsCommand with an existing ID
        prepareQuestionsCommand.setId(1L);

        int databaseSizeBeforeCreate = prepareQuestionsCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrepareQuestionsCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionsCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrepareQuestionsCommand in the database
        List<PrepareQuestionsCommand> prepareQuestionsCommandList = prepareQuestionsCommandRepository.findAll();
        assertThat(prepareQuestionsCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPrepareQuestionsCommands() throws Exception {
        // Initialize the database
        prepareQuestionsCommandRepository.saveAndFlush(prepareQuestionsCommand);

        // Get all the prepareQuestionsCommandList
        restPrepareQuestionsCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prepareQuestionsCommand.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagToPrepareQuestions").value(hasItem(DEFAULT_TAG_TO_PREPARE_QUESTIONS)));
    }

    @Test
    @Transactional
    void getPrepareQuestionsCommand() throws Exception {
        // Initialize the database
        prepareQuestionsCommandRepository.saveAndFlush(prepareQuestionsCommand);

        // Get the prepareQuestionsCommand
        restPrepareQuestionsCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, prepareQuestionsCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prepareQuestionsCommand.getId().intValue()))
            .andExpect(jsonPath("$.tagToPrepareQuestions").value(DEFAULT_TAG_TO_PREPARE_QUESTIONS));
    }

    @Test
    @Transactional
    void getNonExistingPrepareQuestionsCommand() throws Exception {
        // Get the prepareQuestionsCommand
        restPrepareQuestionsCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPrepareQuestionsCommand() throws Exception {
        // Initialize the database
        prepareQuestionsCommandRepository.saveAndFlush(prepareQuestionsCommand);

        int databaseSizeBeforeUpdate = prepareQuestionsCommandRepository.findAll().size();

        // Update the prepareQuestionsCommand
        PrepareQuestionsCommand updatedPrepareQuestionsCommand = prepareQuestionsCommandRepository
            .findById(prepareQuestionsCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedPrepareQuestionsCommand are not directly saved in db
        em.detach(updatedPrepareQuestionsCommand);
        updatedPrepareQuestionsCommand.tagToPrepareQuestions(UPDATED_TAG_TO_PREPARE_QUESTIONS);

        restPrepareQuestionsCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPrepareQuestionsCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPrepareQuestionsCommand))
            )
            .andExpect(status().isOk());

        // Validate the PrepareQuestionsCommand in the database
        List<PrepareQuestionsCommand> prepareQuestionsCommandList = prepareQuestionsCommandRepository.findAll();
        assertThat(prepareQuestionsCommandList).hasSize(databaseSizeBeforeUpdate);
        PrepareQuestionsCommand testPrepareQuestionsCommand = prepareQuestionsCommandList.get(prepareQuestionsCommandList.size() - 1);
        assertThat(testPrepareQuestionsCommand.getTagToPrepareQuestions()).isEqualTo(UPDATED_TAG_TO_PREPARE_QUESTIONS);
    }

    @Test
    @Transactional
    void putNonExistingPrepareQuestionsCommand() throws Exception {
        int databaseSizeBeforeUpdate = prepareQuestionsCommandRepository.findAll().size();
        prepareQuestionsCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrepareQuestionsCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, prepareQuestionsCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionsCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrepareQuestionsCommand in the database
        List<PrepareQuestionsCommand> prepareQuestionsCommandList = prepareQuestionsCommandRepository.findAll();
        assertThat(prepareQuestionsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPrepareQuestionsCommand() throws Exception {
        int databaseSizeBeforeUpdate = prepareQuestionsCommandRepository.findAll().size();
        prepareQuestionsCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrepareQuestionsCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionsCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrepareQuestionsCommand in the database
        List<PrepareQuestionsCommand> prepareQuestionsCommandList = prepareQuestionsCommandRepository.findAll();
        assertThat(prepareQuestionsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPrepareQuestionsCommand() throws Exception {
        int databaseSizeBeforeUpdate = prepareQuestionsCommandRepository.findAll().size();
        prepareQuestionsCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrepareQuestionsCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionsCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PrepareQuestionsCommand in the database
        List<PrepareQuestionsCommand> prepareQuestionsCommandList = prepareQuestionsCommandRepository.findAll();
        assertThat(prepareQuestionsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePrepareQuestionsCommandWithPatch() throws Exception {
        // Initialize the database
        prepareQuestionsCommandRepository.saveAndFlush(prepareQuestionsCommand);

        int databaseSizeBeforeUpdate = prepareQuestionsCommandRepository.findAll().size();

        // Update the prepareQuestionsCommand using partial update
        PrepareQuestionsCommand partialUpdatedPrepareQuestionsCommand = new PrepareQuestionsCommand();
        partialUpdatedPrepareQuestionsCommand.setId(prepareQuestionsCommand.getId());

        partialUpdatedPrepareQuestionsCommand.tagToPrepareQuestions(UPDATED_TAG_TO_PREPARE_QUESTIONS);

        restPrepareQuestionsCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPrepareQuestionsCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPrepareQuestionsCommand))
            )
            .andExpect(status().isOk());

        // Validate the PrepareQuestionsCommand in the database
        List<PrepareQuestionsCommand> prepareQuestionsCommandList = prepareQuestionsCommandRepository.findAll();
        assertThat(prepareQuestionsCommandList).hasSize(databaseSizeBeforeUpdate);
        PrepareQuestionsCommand testPrepareQuestionsCommand = prepareQuestionsCommandList.get(prepareQuestionsCommandList.size() - 1);
        assertThat(testPrepareQuestionsCommand.getTagToPrepareQuestions()).isEqualTo(UPDATED_TAG_TO_PREPARE_QUESTIONS);
    }

    @Test
    @Transactional
    void fullUpdatePrepareQuestionsCommandWithPatch() throws Exception {
        // Initialize the database
        prepareQuestionsCommandRepository.saveAndFlush(prepareQuestionsCommand);

        int databaseSizeBeforeUpdate = prepareQuestionsCommandRepository.findAll().size();

        // Update the prepareQuestionsCommand using partial update
        PrepareQuestionsCommand partialUpdatedPrepareQuestionsCommand = new PrepareQuestionsCommand();
        partialUpdatedPrepareQuestionsCommand.setId(prepareQuestionsCommand.getId());

        partialUpdatedPrepareQuestionsCommand.tagToPrepareQuestions(UPDATED_TAG_TO_PREPARE_QUESTIONS);

        restPrepareQuestionsCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPrepareQuestionsCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPrepareQuestionsCommand))
            )
            .andExpect(status().isOk());

        // Validate the PrepareQuestionsCommand in the database
        List<PrepareQuestionsCommand> prepareQuestionsCommandList = prepareQuestionsCommandRepository.findAll();
        assertThat(prepareQuestionsCommandList).hasSize(databaseSizeBeforeUpdate);
        PrepareQuestionsCommand testPrepareQuestionsCommand = prepareQuestionsCommandList.get(prepareQuestionsCommandList.size() - 1);
        assertThat(testPrepareQuestionsCommand.getTagToPrepareQuestions()).isEqualTo(UPDATED_TAG_TO_PREPARE_QUESTIONS);
    }

    @Test
    @Transactional
    void patchNonExistingPrepareQuestionsCommand() throws Exception {
        int databaseSizeBeforeUpdate = prepareQuestionsCommandRepository.findAll().size();
        prepareQuestionsCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrepareQuestionsCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, prepareQuestionsCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionsCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrepareQuestionsCommand in the database
        List<PrepareQuestionsCommand> prepareQuestionsCommandList = prepareQuestionsCommandRepository.findAll();
        assertThat(prepareQuestionsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPrepareQuestionsCommand() throws Exception {
        int databaseSizeBeforeUpdate = prepareQuestionsCommandRepository.findAll().size();
        prepareQuestionsCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrepareQuestionsCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionsCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrepareQuestionsCommand in the database
        List<PrepareQuestionsCommand> prepareQuestionsCommandList = prepareQuestionsCommandRepository.findAll();
        assertThat(prepareQuestionsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPrepareQuestionsCommand() throws Exception {
        int databaseSizeBeforeUpdate = prepareQuestionsCommandRepository.findAll().size();
        prepareQuestionsCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrepareQuestionsCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionsCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PrepareQuestionsCommand in the database
        List<PrepareQuestionsCommand> prepareQuestionsCommandList = prepareQuestionsCommandRepository.findAll();
        assertThat(prepareQuestionsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePrepareQuestionsCommand() throws Exception {
        // Initialize the database
        prepareQuestionsCommandRepository.saveAndFlush(prepareQuestionsCommand);

        int databaseSizeBeforeDelete = prepareQuestionsCommandRepository.findAll().size();

        // Delete the prepareQuestionsCommand
        restPrepareQuestionsCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, prepareQuestionsCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PrepareQuestionsCommand> prepareQuestionsCommandList = prepareQuestionsCommandRepository.findAll();
        assertThat(prepareQuestionsCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
