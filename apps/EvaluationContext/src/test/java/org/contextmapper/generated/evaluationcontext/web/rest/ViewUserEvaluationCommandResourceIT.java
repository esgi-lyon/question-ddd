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
import org.contextmapper.generated.evaluationcontext.domain.ViewUserEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.ViewUserEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.ViewUserEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.ViewUserEvaluationCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ViewUserEvaluationCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ViewUserEvaluationCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/view-user-evaluation-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ViewUserEvaluationCommandRepository viewUserEvaluationCommandRepository;

    @Autowired
    private ViewUserEvaluationCommandMapper viewUserEvaluationCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restViewUserEvaluationCommandMockMvc;

    private ViewUserEvaluationCommand viewUserEvaluationCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewUserEvaluationCommand createEntity(EntityManager em) {
        ViewUserEvaluationCommand viewUserEvaluationCommand = new ViewUserEvaluationCommand();
        return viewUserEvaluationCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewUserEvaluationCommand createUpdatedEntity(EntityManager em) {
        ViewUserEvaluationCommand viewUserEvaluationCommand = new ViewUserEvaluationCommand();
        return viewUserEvaluationCommand;
    }

    @BeforeEach
    public void initTest() {
        viewUserEvaluationCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createViewUserEvaluationCommand() throws Exception {
        int databaseSizeBeforeCreate = viewUserEvaluationCommandRepository.findAll().size();
        // Create the ViewUserEvaluationCommand
        ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO = viewUserEvaluationCommandMapper.toDto(viewUserEvaluationCommand);
        restViewUserEvaluationCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewUserEvaluationCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ViewUserEvaluationCommand in the database
        List<ViewUserEvaluationCommand> viewUserEvaluationCommandList = viewUserEvaluationCommandRepository.findAll();
        assertThat(viewUserEvaluationCommandList).hasSize(databaseSizeBeforeCreate + 1);
        ViewUserEvaluationCommand testViewUserEvaluationCommand = viewUserEvaluationCommandList.get(
            viewUserEvaluationCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void createViewUserEvaluationCommandWithExistingId() throws Exception {
        // Create the ViewUserEvaluationCommand with an existing ID
        viewUserEvaluationCommand.setId(1L);
        ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO = viewUserEvaluationCommandMapper.toDto(viewUserEvaluationCommand);

        int databaseSizeBeforeCreate = viewUserEvaluationCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restViewUserEvaluationCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewUserEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewUserEvaluationCommand in the database
        List<ViewUserEvaluationCommand> viewUserEvaluationCommandList = viewUserEvaluationCommandRepository.findAll();
        assertThat(viewUserEvaluationCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllViewUserEvaluationCommands() throws Exception {
        // Initialize the database
        viewUserEvaluationCommandRepository.saveAndFlush(viewUserEvaluationCommand);

        // Get all the viewUserEvaluationCommandList
        restViewUserEvaluationCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viewUserEvaluationCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getViewUserEvaluationCommand() throws Exception {
        // Initialize the database
        viewUserEvaluationCommandRepository.saveAndFlush(viewUserEvaluationCommand);

        // Get the viewUserEvaluationCommand
        restViewUserEvaluationCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, viewUserEvaluationCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(viewUserEvaluationCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingViewUserEvaluationCommand() throws Exception {
        // Get the viewUserEvaluationCommand
        restViewUserEvaluationCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingViewUserEvaluationCommand() throws Exception {
        // Initialize the database
        viewUserEvaluationCommandRepository.saveAndFlush(viewUserEvaluationCommand);

        int databaseSizeBeforeUpdate = viewUserEvaluationCommandRepository.findAll().size();

        // Update the viewUserEvaluationCommand
        ViewUserEvaluationCommand updatedViewUserEvaluationCommand = viewUserEvaluationCommandRepository
            .findById(viewUserEvaluationCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedViewUserEvaluationCommand are not directly saved in db
        em.detach(updatedViewUserEvaluationCommand);
        ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO = viewUserEvaluationCommandMapper.toDto(updatedViewUserEvaluationCommand);

        restViewUserEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewUserEvaluationCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewUserEvaluationCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the ViewUserEvaluationCommand in the database
        List<ViewUserEvaluationCommand> viewUserEvaluationCommandList = viewUserEvaluationCommandRepository.findAll();
        assertThat(viewUserEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewUserEvaluationCommand testViewUserEvaluationCommand = viewUserEvaluationCommandList.get(
            viewUserEvaluationCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void putNonExistingViewUserEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewUserEvaluationCommandRepository.findAll().size();
        viewUserEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewUserEvaluationCommand
        ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO = viewUserEvaluationCommandMapper.toDto(viewUserEvaluationCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewUserEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewUserEvaluationCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewUserEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewUserEvaluationCommand in the database
        List<ViewUserEvaluationCommand> viewUserEvaluationCommandList = viewUserEvaluationCommandRepository.findAll();
        assertThat(viewUserEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchViewUserEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewUserEvaluationCommandRepository.findAll().size();
        viewUserEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewUserEvaluationCommand
        ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO = viewUserEvaluationCommandMapper.toDto(viewUserEvaluationCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewUserEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewUserEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewUserEvaluationCommand in the database
        List<ViewUserEvaluationCommand> viewUserEvaluationCommandList = viewUserEvaluationCommandRepository.findAll();
        assertThat(viewUserEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamViewUserEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewUserEvaluationCommandRepository.findAll().size();
        viewUserEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewUserEvaluationCommand
        ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO = viewUserEvaluationCommandMapper.toDto(viewUserEvaluationCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewUserEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewUserEvaluationCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewUserEvaluationCommand in the database
        List<ViewUserEvaluationCommand> viewUserEvaluationCommandList = viewUserEvaluationCommandRepository.findAll();
        assertThat(viewUserEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateViewUserEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        viewUserEvaluationCommandRepository.saveAndFlush(viewUserEvaluationCommand);

        int databaseSizeBeforeUpdate = viewUserEvaluationCommandRepository.findAll().size();

        // Update the viewUserEvaluationCommand using partial update
        ViewUserEvaluationCommand partialUpdatedViewUserEvaluationCommand = new ViewUserEvaluationCommand();
        partialUpdatedViewUserEvaluationCommand.setId(viewUserEvaluationCommand.getId());

        restViewUserEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewUserEvaluationCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewUserEvaluationCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewUserEvaluationCommand in the database
        List<ViewUserEvaluationCommand> viewUserEvaluationCommandList = viewUserEvaluationCommandRepository.findAll();
        assertThat(viewUserEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewUserEvaluationCommand testViewUserEvaluationCommand = viewUserEvaluationCommandList.get(
            viewUserEvaluationCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void fullUpdateViewUserEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        viewUserEvaluationCommandRepository.saveAndFlush(viewUserEvaluationCommand);

        int databaseSizeBeforeUpdate = viewUserEvaluationCommandRepository.findAll().size();

        // Update the viewUserEvaluationCommand using partial update
        ViewUserEvaluationCommand partialUpdatedViewUserEvaluationCommand = new ViewUserEvaluationCommand();
        partialUpdatedViewUserEvaluationCommand.setId(viewUserEvaluationCommand.getId());

        restViewUserEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewUserEvaluationCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewUserEvaluationCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewUserEvaluationCommand in the database
        List<ViewUserEvaluationCommand> viewUserEvaluationCommandList = viewUserEvaluationCommandRepository.findAll();
        assertThat(viewUserEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewUserEvaluationCommand testViewUserEvaluationCommand = viewUserEvaluationCommandList.get(
            viewUserEvaluationCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void patchNonExistingViewUserEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewUserEvaluationCommandRepository.findAll().size();
        viewUserEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewUserEvaluationCommand
        ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO = viewUserEvaluationCommandMapper.toDto(viewUserEvaluationCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewUserEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, viewUserEvaluationCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewUserEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewUserEvaluationCommand in the database
        List<ViewUserEvaluationCommand> viewUserEvaluationCommandList = viewUserEvaluationCommandRepository.findAll();
        assertThat(viewUserEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchViewUserEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewUserEvaluationCommandRepository.findAll().size();
        viewUserEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewUserEvaluationCommand
        ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO = viewUserEvaluationCommandMapper.toDto(viewUserEvaluationCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewUserEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewUserEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewUserEvaluationCommand in the database
        List<ViewUserEvaluationCommand> viewUserEvaluationCommandList = viewUserEvaluationCommandRepository.findAll();
        assertThat(viewUserEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamViewUserEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewUserEvaluationCommandRepository.findAll().size();
        viewUserEvaluationCommand.setId(count.incrementAndGet());

        // Create the ViewUserEvaluationCommand
        ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO = viewUserEvaluationCommandMapper.toDto(viewUserEvaluationCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewUserEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewUserEvaluationCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewUserEvaluationCommand in the database
        List<ViewUserEvaluationCommand> viewUserEvaluationCommandList = viewUserEvaluationCommandRepository.findAll();
        assertThat(viewUserEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteViewUserEvaluationCommand() throws Exception {
        // Initialize the database
        viewUserEvaluationCommandRepository.saveAndFlush(viewUserEvaluationCommand);

        int databaseSizeBeforeDelete = viewUserEvaluationCommandRepository.findAll().size();

        // Delete the viewUserEvaluationCommand
        restViewUserEvaluationCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, viewUserEvaluationCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ViewUserEvaluationCommand> viewUserEvaluationCommandList = viewUserEvaluationCommandRepository.findAll();
        assertThat(viewUserEvaluationCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
