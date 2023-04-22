package org.contextmapper.generated.evaluationcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.evaluationcontext.IntegrationTest;
import org.contextmapper.generated.evaluationcontext.domain.CheckAnswerCommand;
import org.contextmapper.generated.evaluationcontext.repository.CheckAnswerCommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CheckAnswerCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
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
    private MockMvc restCheckAnswerCommandMockMvc;

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

    @BeforeEach
    public void initTest() {
        checkAnswerCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeCreate = checkAnswerCommandRepository.findAll().size();
        // Create the CheckAnswerCommand
        restCheckAnswerCommandMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            )
            .andExpect(status().isCreated());

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeCreate + 1);
        CheckAnswerCommand testCheckAnswerCommand = checkAnswerCommandList.get(checkAnswerCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createCheckAnswerCommandWithExistingId() throws Exception {
        // Create the CheckAnswerCommand with an existing ID
        checkAnswerCommand.setId(1L);

        int databaseSizeBeforeCreate = checkAnswerCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCheckAnswerCommandMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCheckAnswerCommands() throws Exception {
        // Initialize the database
        checkAnswerCommandRepository.saveAndFlush(checkAnswerCommand);

        // Get all the checkAnswerCommandList
        restCheckAnswerCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(checkAnswerCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getCheckAnswerCommand() throws Exception {
        // Initialize the database
        checkAnswerCommandRepository.saveAndFlush(checkAnswerCommand);

        // Get the checkAnswerCommand
        restCheckAnswerCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, checkAnswerCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(checkAnswerCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCheckAnswerCommand() throws Exception {
        // Get the checkAnswerCommand
        restCheckAnswerCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCheckAnswerCommand() throws Exception {
        // Initialize the database
        checkAnswerCommandRepository.saveAndFlush(checkAnswerCommand);

        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().size();

        // Update the checkAnswerCommand
        CheckAnswerCommand updatedCheckAnswerCommand = checkAnswerCommandRepository.findById(checkAnswerCommand.getId()).get();
        // Disconnect from session so that the updates on updatedCheckAnswerCommand are not directly saved in db
        em.detach(updatedCheckAnswerCommand);

        restCheckAnswerCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCheckAnswerCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCheckAnswerCommand))
            )
            .andExpect(status().isOk());

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
        CheckAnswerCommand testCheckAnswerCommand = checkAnswerCommandList.get(checkAnswerCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().size();
        checkAnswerCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCheckAnswerCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, checkAnswerCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().size();
        checkAnswerCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckAnswerCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().size();
        checkAnswerCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckAnswerCommandMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCheckAnswerCommandWithPatch() throws Exception {
        // Initialize the database
        checkAnswerCommandRepository.saveAndFlush(checkAnswerCommand);

        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().size();

        // Update the checkAnswerCommand using partial update
        CheckAnswerCommand partialUpdatedCheckAnswerCommand = new CheckAnswerCommand();
        partialUpdatedCheckAnswerCommand.setId(checkAnswerCommand.getId());

        restCheckAnswerCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCheckAnswerCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCheckAnswerCommand))
            )
            .andExpect(status().isOk());

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
        CheckAnswerCommand testCheckAnswerCommand = checkAnswerCommandList.get(checkAnswerCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCheckAnswerCommandWithPatch() throws Exception {
        // Initialize the database
        checkAnswerCommandRepository.saveAndFlush(checkAnswerCommand);

        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().size();

        // Update the checkAnswerCommand using partial update
        CheckAnswerCommand partialUpdatedCheckAnswerCommand = new CheckAnswerCommand();
        partialUpdatedCheckAnswerCommand.setId(checkAnswerCommand.getId());

        restCheckAnswerCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCheckAnswerCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCheckAnswerCommand))
            )
            .andExpect(status().isOk());

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
        CheckAnswerCommand testCheckAnswerCommand = checkAnswerCommandList.get(checkAnswerCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().size();
        checkAnswerCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCheckAnswerCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, checkAnswerCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().size();
        checkAnswerCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckAnswerCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCheckAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerCommandRepository.findAll().size();
        checkAnswerCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckAnswerCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(checkAnswerCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CheckAnswerCommand in the database
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCheckAnswerCommand() throws Exception {
        // Initialize the database
        checkAnswerCommandRepository.saveAndFlush(checkAnswerCommand);

        int databaseSizeBeforeDelete = checkAnswerCommandRepository.findAll().size();

        // Delete the checkAnswerCommand
        restCheckAnswerCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, checkAnswerCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CheckAnswerCommand> checkAnswerCommandList = checkAnswerCommandRepository.findAll();
        assertThat(checkAnswerCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
