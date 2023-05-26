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
import org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionCommand;
import org.contextmapper.generated.sendquestioncontext.repository.PrepareQuestionCommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PrepareQuestionCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PrepareQuestionCommandResourceIT {

    private static final Integer DEFAULT_RESOURCE_ID = 1;
    private static final Integer UPDATED_RESOURCE_ID = 2;

    private static final String ENTITY_API_URL = "/api/prepare-question-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PrepareQuestionCommandRepository prepareQuestionCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrepareQuestionCommandMockMvc;

    private PrepareQuestionCommand prepareQuestionCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrepareQuestionCommand createEntity(EntityManager em) {
        PrepareQuestionCommand prepareQuestionCommand = new PrepareQuestionCommand().resourceId(DEFAULT_RESOURCE_ID);
        return prepareQuestionCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrepareQuestionCommand createUpdatedEntity(EntityManager em) {
        PrepareQuestionCommand prepareQuestionCommand = new PrepareQuestionCommand().resourceId(UPDATED_RESOURCE_ID);
        return prepareQuestionCommand;
    }

    @BeforeEach
    public void initTest() {
        prepareQuestionCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createPrepareQuestionCommand() throws Exception {
        int databaseSizeBeforeCreate = prepareQuestionCommandRepository.findAll().size();
        // Create the PrepareQuestionCommand
        restPrepareQuestionCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionCommand))
            )
            .andExpect(status().isCreated());

        // Validate the PrepareQuestionCommand in the database
        List<PrepareQuestionCommand> prepareQuestionCommandList = prepareQuestionCommandRepository.findAll();
        assertThat(prepareQuestionCommandList).hasSize(databaseSizeBeforeCreate + 1);
        PrepareQuestionCommand testPrepareQuestionCommand = prepareQuestionCommandList.get(prepareQuestionCommandList.size() - 1);
        assertThat(testPrepareQuestionCommand.getResourceId()).isEqualTo(DEFAULT_RESOURCE_ID);
    }

    @Test
    @Transactional
    void createPrepareQuestionCommandWithExistingId() throws Exception {
        // Create the PrepareQuestionCommand with an existing ID
        prepareQuestionCommand.setId(1L);

        int databaseSizeBeforeCreate = prepareQuestionCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrepareQuestionCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrepareQuestionCommand in the database
        List<PrepareQuestionCommand> prepareQuestionCommandList = prepareQuestionCommandRepository.findAll();
        assertThat(prepareQuestionCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPrepareQuestionCommands() throws Exception {
        // Initialize the database
        prepareQuestionCommandRepository.saveAndFlush(prepareQuestionCommand);

        // Get all the prepareQuestionCommandList
        restPrepareQuestionCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prepareQuestionCommand.getId().intValue())))
            .andExpect(jsonPath("$.[*].resourceId").value(hasItem(DEFAULT_RESOURCE_ID)));
    }

    @Test
    @Transactional
    void getPrepareQuestionCommand() throws Exception {
        // Initialize the database
        prepareQuestionCommandRepository.saveAndFlush(prepareQuestionCommand);

        // Get the prepareQuestionCommand
        restPrepareQuestionCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, prepareQuestionCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prepareQuestionCommand.getId().intValue()))
            .andExpect(jsonPath("$.resourceId").value(DEFAULT_RESOURCE_ID));
    }

    @Test
    @Transactional
    void getNonExistingPrepareQuestionCommand() throws Exception {
        // Get the prepareQuestionCommand
        restPrepareQuestionCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPrepareQuestionCommand() throws Exception {
        // Initialize the database
        prepareQuestionCommandRepository.saveAndFlush(prepareQuestionCommand);

        int databaseSizeBeforeUpdate = prepareQuestionCommandRepository.findAll().size();

        // Update the prepareQuestionCommand
        PrepareQuestionCommand updatedPrepareQuestionCommand = prepareQuestionCommandRepository
            .findById(prepareQuestionCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedPrepareQuestionCommand are not directly saved in db
        em.detach(updatedPrepareQuestionCommand);
        updatedPrepareQuestionCommand.resourceId(UPDATED_RESOURCE_ID);

        restPrepareQuestionCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPrepareQuestionCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPrepareQuestionCommand))
            )
            .andExpect(status().isOk());

        // Validate the PrepareQuestionCommand in the database
        List<PrepareQuestionCommand> prepareQuestionCommandList = prepareQuestionCommandRepository.findAll();
        assertThat(prepareQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
        PrepareQuestionCommand testPrepareQuestionCommand = prepareQuestionCommandList.get(prepareQuestionCommandList.size() - 1);
        assertThat(testPrepareQuestionCommand.getResourceId()).isEqualTo(UPDATED_RESOURCE_ID);
    }

    @Test
    @Transactional
    void putNonExistingPrepareQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = prepareQuestionCommandRepository.findAll().size();
        prepareQuestionCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrepareQuestionCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, prepareQuestionCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrepareQuestionCommand in the database
        List<PrepareQuestionCommand> prepareQuestionCommandList = prepareQuestionCommandRepository.findAll();
        assertThat(prepareQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPrepareQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = prepareQuestionCommandRepository.findAll().size();
        prepareQuestionCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrepareQuestionCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrepareQuestionCommand in the database
        List<PrepareQuestionCommand> prepareQuestionCommandList = prepareQuestionCommandRepository.findAll();
        assertThat(prepareQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPrepareQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = prepareQuestionCommandRepository.findAll().size();
        prepareQuestionCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrepareQuestionCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PrepareQuestionCommand in the database
        List<PrepareQuestionCommand> prepareQuestionCommandList = prepareQuestionCommandRepository.findAll();
        assertThat(prepareQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePrepareQuestionCommandWithPatch() throws Exception {
        // Initialize the database
        prepareQuestionCommandRepository.saveAndFlush(prepareQuestionCommand);

        int databaseSizeBeforeUpdate = prepareQuestionCommandRepository.findAll().size();

        // Update the prepareQuestionCommand using partial update
        PrepareQuestionCommand partialUpdatedPrepareQuestionCommand = new PrepareQuestionCommand();
        partialUpdatedPrepareQuestionCommand.setId(prepareQuestionCommand.getId());

        partialUpdatedPrepareQuestionCommand.resourceId(UPDATED_RESOURCE_ID);

        restPrepareQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPrepareQuestionCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPrepareQuestionCommand))
            )
            .andExpect(status().isOk());

        // Validate the PrepareQuestionCommand in the database
        List<PrepareQuestionCommand> prepareQuestionCommandList = prepareQuestionCommandRepository.findAll();
        assertThat(prepareQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
        PrepareQuestionCommand testPrepareQuestionCommand = prepareQuestionCommandList.get(prepareQuestionCommandList.size() - 1);
        assertThat(testPrepareQuestionCommand.getResourceId()).isEqualTo(UPDATED_RESOURCE_ID);
    }

    @Test
    @Transactional
    void fullUpdatePrepareQuestionCommandWithPatch() throws Exception {
        // Initialize the database
        prepareQuestionCommandRepository.saveAndFlush(prepareQuestionCommand);

        int databaseSizeBeforeUpdate = prepareQuestionCommandRepository.findAll().size();

        // Update the prepareQuestionCommand using partial update
        PrepareQuestionCommand partialUpdatedPrepareQuestionCommand = new PrepareQuestionCommand();
        partialUpdatedPrepareQuestionCommand.setId(prepareQuestionCommand.getId());

        partialUpdatedPrepareQuestionCommand.resourceId(UPDATED_RESOURCE_ID);

        restPrepareQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPrepareQuestionCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPrepareQuestionCommand))
            )
            .andExpect(status().isOk());

        // Validate the PrepareQuestionCommand in the database
        List<PrepareQuestionCommand> prepareQuestionCommandList = prepareQuestionCommandRepository.findAll();
        assertThat(prepareQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
        PrepareQuestionCommand testPrepareQuestionCommand = prepareQuestionCommandList.get(prepareQuestionCommandList.size() - 1);
        assertThat(testPrepareQuestionCommand.getResourceId()).isEqualTo(UPDATED_RESOURCE_ID);
    }

    @Test
    @Transactional
    void patchNonExistingPrepareQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = prepareQuestionCommandRepository.findAll().size();
        prepareQuestionCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrepareQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, prepareQuestionCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrepareQuestionCommand in the database
        List<PrepareQuestionCommand> prepareQuestionCommandList = prepareQuestionCommandRepository.findAll();
        assertThat(prepareQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPrepareQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = prepareQuestionCommandRepository.findAll().size();
        prepareQuestionCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrepareQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrepareQuestionCommand in the database
        List<PrepareQuestionCommand> prepareQuestionCommandList = prepareQuestionCommandRepository.findAll();
        assertThat(prepareQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPrepareQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = prepareQuestionCommandRepository.findAll().size();
        prepareQuestionCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrepareQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(prepareQuestionCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PrepareQuestionCommand in the database
        List<PrepareQuestionCommand> prepareQuestionCommandList = prepareQuestionCommandRepository.findAll();
        assertThat(prepareQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePrepareQuestionCommand() throws Exception {
        // Initialize the database
        prepareQuestionCommandRepository.saveAndFlush(prepareQuestionCommand);

        int databaseSizeBeforeDelete = prepareQuestionCommandRepository.findAll().size();

        // Delete the prepareQuestionCommand
        restPrepareQuestionCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, prepareQuestionCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PrepareQuestionCommand> prepareQuestionCommandList = prepareQuestionCommandRepository.findAll();
        assertThat(prepareQuestionCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
