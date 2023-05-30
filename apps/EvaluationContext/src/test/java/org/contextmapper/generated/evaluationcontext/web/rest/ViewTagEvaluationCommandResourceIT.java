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
import org.contextmapper.generated.evaluationcontext.domain.ViewTagEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.ViewTagEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.ViewTagEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.ViewTagEvaluationCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ViewTagEvaluationCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ViewTagEvaluationCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/view-tag-evaluation-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ViewTagEvaluationCommandRepository viewTagEvaluationCommandRepository;

    @Autowired
    private ViewTagEvaluationCommandMapper viewTagEvaluationCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restViewTagEvaluationCommandMockMvc;

    private ViewTagEvaluationCommand viewTagEvaluationCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewTagEvaluationCommand createEntity(EntityManager em) {
        ViewTagEvaluationCommand viewTagEvaluationCommand = new ViewTagEvaluationCommand();
        return viewTagEvaluationCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewTagEvaluationCommand createUpdatedEntity(EntityManager em) {
        ViewTagEvaluationCommand viewTagEvaluationCommand = new ViewTagEvaluationCommand();
        return viewTagEvaluationCommand;
    }

    @BeforeEach
    public void initTest() {
        viewTagEvaluationCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createViewTagEvaluationCommand() throws Exception {
        int databaseSizeBeforeCreate = viewTagEvaluationCommandRepository.findAll().size();
        // Create the ViewTagEvaluationCommand
        ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO = viewTagEvaluationCommandMapper.toDto(viewTagEvaluationCommand);
        restViewTagEvaluationCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewTagEvaluationCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ViewTagEvaluationCommand in the database
        List<ViewTagEvaluationCommand> viewTagEvaluationCommandList = viewTagEvaluationCommandRepository.findAll();
        assertThat(viewTagEvaluationCommandList).hasSize(databaseSizeBeforeCreate + 1);
        ViewTagEvaluationCommand testViewTagEvaluationCommand = viewTagEvaluationCommandList.get(viewTagEvaluationCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createViewTagEvaluationCommandWithExistingId() throws Exception {
        // Create the ViewTagEvaluationCommand with an existing ID
        viewTagEvaluationCommand.setId(1L);
        ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO = viewTagEvaluationCommandMapper.toDto(viewTagEvaluationCommand);

        int databaseSizeBeforeCreate = viewTagEvaluationCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restViewTagEvaluationCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewTagEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewTagEvaluationCommand in the database
        List<ViewTagEvaluationCommand> viewTagEvaluationCommandList = viewTagEvaluationCommandRepository.findAll();
        assertThat(viewTagEvaluationCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllViewTagEvaluationCommands() throws Exception {
        // Initialize the database
        viewTagEvaluationCommandRepository.saveAndFlush(viewTagEvaluationCommand);

        // Get all the viewTagEvaluationCommandList
        restViewTagEvaluationCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viewTagEvaluationCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getViewTagEvaluationCommand() throws Exception {
        // Initialize the database
        viewTagEvaluationCommandRepository.saveAndFlush(viewTagEvaluationCommand);

        // Get the viewTagEvaluationCommand
        restViewTagEvaluationCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, viewTagEvaluationCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(viewTagEvaluationCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingViewTagEvaluationCommand() throws Exception {
        // Get the viewTagEvaluationCommand
        restViewTagEvaluationCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingViewTagEvaluationCommand() throws Exception {
        // Initialize the database
        viewTagEvaluationCommandRepository.saveAndFlush(viewTagEvaluationCommand);

        int databaseSizeBeforeUpdate = viewTagEvaluationCommandRepository.findAll().size();

        // Update the viewTagEvaluationCommand
        ViewTagEvaluationCommand updatedViewTagEvaluationCommand = viewTagEvaluationCommandRepository
            .findById(viewTagEvaluationCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedViewTagEvaluationCommand are not directly saved in db
        em.detach(updatedViewTagEvaluationCommand);
        ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO = viewTagEvaluationCommandMapper.toDto(updatedViewTagEvaluationCommand);

        restViewTagEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewTagEvaluationCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewTagEvaluationCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the ViewTagEvaluationCommand in the database
        List<ViewTagEvaluationCommand> viewTagEvaluationCommandList = viewTagEvaluationCommandRepository.findAll();
        assertThat(viewTagEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewTagEvaluationCommand testViewTagEvaluationCommand = viewTagEvaluationCommandList.get(viewTagEvaluationCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingViewTagEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewTagEvaluationCommandRepository.findAll().size();
        viewTagEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewTagEvaluationCommand
        ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO = viewTagEvaluationCommandMapper.toDto(viewTagEvaluationCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewTagEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewTagEvaluationCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewTagEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewTagEvaluationCommand in the database
        List<ViewTagEvaluationCommand> viewTagEvaluationCommandList = viewTagEvaluationCommandRepository.findAll();
        assertThat(viewTagEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchViewTagEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewTagEvaluationCommandRepository.findAll().size();
        viewTagEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewTagEvaluationCommand
        ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO = viewTagEvaluationCommandMapper.toDto(viewTagEvaluationCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewTagEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewTagEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewTagEvaluationCommand in the database
        List<ViewTagEvaluationCommand> viewTagEvaluationCommandList = viewTagEvaluationCommandRepository.findAll();
        assertThat(viewTagEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamViewTagEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewTagEvaluationCommandRepository.findAll().size();
        viewTagEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewTagEvaluationCommand
        ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO = viewTagEvaluationCommandMapper.toDto(viewTagEvaluationCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewTagEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewTagEvaluationCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewTagEvaluationCommand in the database
        List<ViewTagEvaluationCommand> viewTagEvaluationCommandList = viewTagEvaluationCommandRepository.findAll();
        assertThat(viewTagEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateViewTagEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        viewTagEvaluationCommandRepository.saveAndFlush(viewTagEvaluationCommand);

        int databaseSizeBeforeUpdate = viewTagEvaluationCommandRepository.findAll().size();

        // Update the viewTagEvaluationCommand using partial update
        ViewTagEvaluationCommand partialUpdatedViewTagEvaluationCommand = new ViewTagEvaluationCommand();
        partialUpdatedViewTagEvaluationCommand.setId(viewTagEvaluationCommand.getId());

        restViewTagEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewTagEvaluationCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewTagEvaluationCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewTagEvaluationCommand in the database
        List<ViewTagEvaluationCommand> viewTagEvaluationCommandList = viewTagEvaluationCommandRepository.findAll();
        assertThat(viewTagEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewTagEvaluationCommand testViewTagEvaluationCommand = viewTagEvaluationCommandList.get(viewTagEvaluationCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateViewTagEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        viewTagEvaluationCommandRepository.saveAndFlush(viewTagEvaluationCommand);

        int databaseSizeBeforeUpdate = viewTagEvaluationCommandRepository.findAll().size();

        // Update the viewTagEvaluationCommand using partial update
        ViewTagEvaluationCommand partialUpdatedViewTagEvaluationCommand = new ViewTagEvaluationCommand();
        partialUpdatedViewTagEvaluationCommand.setId(viewTagEvaluationCommand.getId());

        restViewTagEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewTagEvaluationCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewTagEvaluationCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewTagEvaluationCommand in the database
        List<ViewTagEvaluationCommand> viewTagEvaluationCommandList = viewTagEvaluationCommandRepository.findAll();
        assertThat(viewTagEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewTagEvaluationCommand testViewTagEvaluationCommand = viewTagEvaluationCommandList.get(viewTagEvaluationCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingViewTagEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewTagEvaluationCommandRepository.findAll().size();
        viewTagEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewTagEvaluationCommand
        ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO = viewTagEvaluationCommandMapper.toDto(viewTagEvaluationCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewTagEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, viewTagEvaluationCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewTagEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewTagEvaluationCommand in the database
        List<ViewTagEvaluationCommand> viewTagEvaluationCommandList = viewTagEvaluationCommandRepository.findAll();
        assertThat(viewTagEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchViewTagEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewTagEvaluationCommandRepository.findAll().size();
        viewTagEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewTagEvaluationCommand
        ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO = viewTagEvaluationCommandMapper.toDto(viewTagEvaluationCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewTagEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewTagEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewTagEvaluationCommand in the database
        List<ViewTagEvaluationCommand> viewTagEvaluationCommandList = viewTagEvaluationCommandRepository.findAll();
        assertThat(viewTagEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamViewTagEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewTagEvaluationCommandRepository.findAll().size();
        viewTagEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewTagEvaluationCommand
        ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO = viewTagEvaluationCommandMapper.toDto(viewTagEvaluationCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewTagEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewTagEvaluationCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewTagEvaluationCommand in the database
        List<ViewTagEvaluationCommand> viewTagEvaluationCommandList = viewTagEvaluationCommandRepository.findAll();
        assertThat(viewTagEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteViewTagEvaluationCommand() throws Exception {
        // Initialize the database
        viewTagEvaluationCommandRepository.saveAndFlush(viewTagEvaluationCommand);

        int databaseSizeBeforeDelete = viewTagEvaluationCommandRepository.findAll().size();

        // Delete the viewTagEvaluationCommand
        restViewTagEvaluationCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, viewTagEvaluationCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ViewTagEvaluationCommand> viewTagEvaluationCommandList = viewTagEvaluationCommandRepository.findAll();
        assertThat(viewTagEvaluationCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
