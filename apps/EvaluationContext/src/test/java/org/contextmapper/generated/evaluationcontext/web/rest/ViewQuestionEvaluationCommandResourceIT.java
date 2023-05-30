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
import org.contextmapper.generated.evaluationcontext.domain.ViewQuestionEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.ViewQuestionEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.ViewQuestionEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.ViewQuestionEvaluationCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ViewQuestionEvaluationCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ViewQuestionEvaluationCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/view-question-evaluation-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ViewQuestionEvaluationCommandRepository viewQuestionEvaluationCommandRepository;

    @Autowired
    private ViewQuestionEvaluationCommandMapper viewQuestionEvaluationCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restViewQuestionEvaluationCommandMockMvc;

    private ViewQuestionEvaluationCommand viewQuestionEvaluationCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewQuestionEvaluationCommand createEntity(EntityManager em) {
        ViewQuestionEvaluationCommand viewQuestionEvaluationCommand = new ViewQuestionEvaluationCommand();
        return viewQuestionEvaluationCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewQuestionEvaluationCommand createUpdatedEntity(EntityManager em) {
        ViewQuestionEvaluationCommand viewQuestionEvaluationCommand = new ViewQuestionEvaluationCommand();
        return viewQuestionEvaluationCommand;
    }

    @BeforeEach
    public void initTest() {
        viewQuestionEvaluationCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createViewQuestionEvaluationCommand() throws Exception {
        int databaseSizeBeforeCreate = viewQuestionEvaluationCommandRepository.findAll().size();
        // Create the ViewQuestionEvaluationCommand
        ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO = viewQuestionEvaluationCommandMapper.toDto(
            viewQuestionEvaluationCommand
        );
        restViewQuestionEvaluationCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewQuestionEvaluationCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ViewQuestionEvaluationCommand in the database
        List<ViewQuestionEvaluationCommand> viewQuestionEvaluationCommandList = viewQuestionEvaluationCommandRepository.findAll();
        assertThat(viewQuestionEvaluationCommandList).hasSize(databaseSizeBeforeCreate + 1);
        ViewQuestionEvaluationCommand testViewQuestionEvaluationCommand = viewQuestionEvaluationCommandList.get(
            viewQuestionEvaluationCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void createViewQuestionEvaluationCommandWithExistingId() throws Exception {
        // Create the ViewQuestionEvaluationCommand with an existing ID
        viewQuestionEvaluationCommand.setId(1L);
        ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO = viewQuestionEvaluationCommandMapper.toDto(
            viewQuestionEvaluationCommand
        );

        int databaseSizeBeforeCreate = viewQuestionEvaluationCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restViewQuestionEvaluationCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewQuestionEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewQuestionEvaluationCommand in the database
        List<ViewQuestionEvaluationCommand> viewQuestionEvaluationCommandList = viewQuestionEvaluationCommandRepository.findAll();
        assertThat(viewQuestionEvaluationCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllViewQuestionEvaluationCommands() throws Exception {
        // Initialize the database
        viewQuestionEvaluationCommandRepository.saveAndFlush(viewQuestionEvaluationCommand);

        // Get all the viewQuestionEvaluationCommandList
        restViewQuestionEvaluationCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viewQuestionEvaluationCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getViewQuestionEvaluationCommand() throws Exception {
        // Initialize the database
        viewQuestionEvaluationCommandRepository.saveAndFlush(viewQuestionEvaluationCommand);

        // Get the viewQuestionEvaluationCommand
        restViewQuestionEvaluationCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, viewQuestionEvaluationCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(viewQuestionEvaluationCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingViewQuestionEvaluationCommand() throws Exception {
        // Get the viewQuestionEvaluationCommand
        restViewQuestionEvaluationCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingViewQuestionEvaluationCommand() throws Exception {
        // Initialize the database
        viewQuestionEvaluationCommandRepository.saveAndFlush(viewQuestionEvaluationCommand);

        int databaseSizeBeforeUpdate = viewQuestionEvaluationCommandRepository.findAll().size();

        // Update the viewQuestionEvaluationCommand
        ViewQuestionEvaluationCommand updatedViewQuestionEvaluationCommand = viewQuestionEvaluationCommandRepository
            .findById(viewQuestionEvaluationCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedViewQuestionEvaluationCommand are not directly saved in db
        em.detach(updatedViewQuestionEvaluationCommand);
        ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO = viewQuestionEvaluationCommandMapper.toDto(
            updatedViewQuestionEvaluationCommand
        );

        restViewQuestionEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewQuestionEvaluationCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewQuestionEvaluationCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the ViewQuestionEvaluationCommand in the database
        List<ViewQuestionEvaluationCommand> viewQuestionEvaluationCommandList = viewQuestionEvaluationCommandRepository.findAll();
        assertThat(viewQuestionEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewQuestionEvaluationCommand testViewQuestionEvaluationCommand = viewQuestionEvaluationCommandList.get(
            viewQuestionEvaluationCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void putNonExistingViewQuestionEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewQuestionEvaluationCommandRepository.findAll().size();
        viewQuestionEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewQuestionEvaluationCommand
        ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO = viewQuestionEvaluationCommandMapper.toDto(
            viewQuestionEvaluationCommand
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewQuestionEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewQuestionEvaluationCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewQuestionEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewQuestionEvaluationCommand in the database
        List<ViewQuestionEvaluationCommand> viewQuestionEvaluationCommandList = viewQuestionEvaluationCommandRepository.findAll();
        assertThat(viewQuestionEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchViewQuestionEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewQuestionEvaluationCommandRepository.findAll().size();
        viewQuestionEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewQuestionEvaluationCommand
        ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO = viewQuestionEvaluationCommandMapper.toDto(
            viewQuestionEvaluationCommand
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewQuestionEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewQuestionEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewQuestionEvaluationCommand in the database
        List<ViewQuestionEvaluationCommand> viewQuestionEvaluationCommandList = viewQuestionEvaluationCommandRepository.findAll();
        assertThat(viewQuestionEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamViewQuestionEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewQuestionEvaluationCommandRepository.findAll().size();
        viewQuestionEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewQuestionEvaluationCommand
        ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO = viewQuestionEvaluationCommandMapper.toDto(
            viewQuestionEvaluationCommand
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewQuestionEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewQuestionEvaluationCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewQuestionEvaluationCommand in the database
        List<ViewQuestionEvaluationCommand> viewQuestionEvaluationCommandList = viewQuestionEvaluationCommandRepository.findAll();
        assertThat(viewQuestionEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateViewQuestionEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        viewQuestionEvaluationCommandRepository.saveAndFlush(viewQuestionEvaluationCommand);

        int databaseSizeBeforeUpdate = viewQuestionEvaluationCommandRepository.findAll().size();

        // Update the viewQuestionEvaluationCommand using partial update
        ViewQuestionEvaluationCommand partialUpdatedViewQuestionEvaluationCommand = new ViewQuestionEvaluationCommand();
        partialUpdatedViewQuestionEvaluationCommand.setId(viewQuestionEvaluationCommand.getId());

        restViewQuestionEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewQuestionEvaluationCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewQuestionEvaluationCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewQuestionEvaluationCommand in the database
        List<ViewQuestionEvaluationCommand> viewQuestionEvaluationCommandList = viewQuestionEvaluationCommandRepository.findAll();
        assertThat(viewQuestionEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewQuestionEvaluationCommand testViewQuestionEvaluationCommand = viewQuestionEvaluationCommandList.get(
            viewQuestionEvaluationCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void fullUpdateViewQuestionEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        viewQuestionEvaluationCommandRepository.saveAndFlush(viewQuestionEvaluationCommand);

        int databaseSizeBeforeUpdate = viewQuestionEvaluationCommandRepository.findAll().size();

        // Update the viewQuestionEvaluationCommand using partial update
        ViewQuestionEvaluationCommand partialUpdatedViewQuestionEvaluationCommand = new ViewQuestionEvaluationCommand();
        partialUpdatedViewQuestionEvaluationCommand.setId(viewQuestionEvaluationCommand.getId());

        restViewQuestionEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewQuestionEvaluationCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewQuestionEvaluationCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewQuestionEvaluationCommand in the database
        List<ViewQuestionEvaluationCommand> viewQuestionEvaluationCommandList = viewQuestionEvaluationCommandRepository.findAll();
        assertThat(viewQuestionEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewQuestionEvaluationCommand testViewQuestionEvaluationCommand = viewQuestionEvaluationCommandList.get(
            viewQuestionEvaluationCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void patchNonExistingViewQuestionEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewQuestionEvaluationCommandRepository.findAll().size();
        viewQuestionEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewQuestionEvaluationCommand
        ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO = viewQuestionEvaluationCommandMapper.toDto(
            viewQuestionEvaluationCommand
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewQuestionEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, viewQuestionEvaluationCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewQuestionEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewQuestionEvaluationCommand in the database
        List<ViewQuestionEvaluationCommand> viewQuestionEvaluationCommandList = viewQuestionEvaluationCommandRepository.findAll();
        assertThat(viewQuestionEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchViewQuestionEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewQuestionEvaluationCommandRepository.findAll().size();
        viewQuestionEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewQuestionEvaluationCommand
        ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO = viewQuestionEvaluationCommandMapper.toDto(
            viewQuestionEvaluationCommand
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewQuestionEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewQuestionEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewQuestionEvaluationCommand in the database
        List<ViewQuestionEvaluationCommand> viewQuestionEvaluationCommandList = viewQuestionEvaluationCommandRepository.findAll();
        assertThat(viewQuestionEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamViewQuestionEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewQuestionEvaluationCommandRepository.findAll().size();
        viewQuestionEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewQuestionEvaluationCommand
        ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO = viewQuestionEvaluationCommandMapper.toDto(
            viewQuestionEvaluationCommand
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewQuestionEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewQuestionEvaluationCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewQuestionEvaluationCommand in the database
        List<ViewQuestionEvaluationCommand> viewQuestionEvaluationCommandList = viewQuestionEvaluationCommandRepository.findAll();
        assertThat(viewQuestionEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteViewQuestionEvaluationCommand() throws Exception {
        // Initialize the database
        viewQuestionEvaluationCommandRepository.saveAndFlush(viewQuestionEvaluationCommand);

        int databaseSizeBeforeDelete = viewQuestionEvaluationCommandRepository.findAll().size();

        // Delete the viewQuestionEvaluationCommand
        restViewQuestionEvaluationCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, viewQuestionEvaluationCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ViewQuestionEvaluationCommand> viewQuestionEvaluationCommandList = viewQuestionEvaluationCommandRepository.findAll();
        assertThat(viewQuestionEvaluationCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
