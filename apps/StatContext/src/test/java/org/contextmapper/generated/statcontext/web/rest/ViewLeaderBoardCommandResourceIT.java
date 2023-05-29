package org.contextmapper.generated.statcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.statcontext.IntegrationTest;
import org.contextmapper.generated.statcontext.domain.ViewLeaderBoardCommand;
import org.contextmapper.generated.statcontext.repository.ViewLeaderBoardCommandRepository;
import org.contextmapper.generated.statcontext.service.dto.ViewLeaderBoardCommandDTO;
import org.contextmapper.generated.statcontext.service.mapper.ViewLeaderBoardCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ViewLeaderBoardCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ViewLeaderBoardCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/view-leader-board-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ViewLeaderBoardCommandRepository viewLeaderBoardCommandRepository;

    @Autowired
    private ViewLeaderBoardCommandMapper viewLeaderBoardCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restViewLeaderBoardCommandMockMvc;

    private ViewLeaderBoardCommand viewLeaderBoardCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewLeaderBoardCommand createEntity(EntityManager em) {
        ViewLeaderBoardCommand viewLeaderBoardCommand = new ViewLeaderBoardCommand();
        return viewLeaderBoardCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewLeaderBoardCommand createUpdatedEntity(EntityManager em) {
        ViewLeaderBoardCommand viewLeaderBoardCommand = new ViewLeaderBoardCommand();
        return viewLeaderBoardCommand;
    }

    @BeforeEach
    public void initTest() {
        viewLeaderBoardCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createViewLeaderBoardCommand() throws Exception {
        int databaseSizeBeforeCreate = viewLeaderBoardCommandRepository.findAll().size();
        // Create the ViewLeaderBoardCommand
        ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO = viewLeaderBoardCommandMapper.toDto(viewLeaderBoardCommand);
        restViewLeaderBoardCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewLeaderBoardCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ViewLeaderBoardCommand in the database
        List<ViewLeaderBoardCommand> viewLeaderBoardCommandList = viewLeaderBoardCommandRepository.findAll();
        assertThat(viewLeaderBoardCommandList).hasSize(databaseSizeBeforeCreate + 1);
        ViewLeaderBoardCommand testViewLeaderBoardCommand = viewLeaderBoardCommandList.get(viewLeaderBoardCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createViewLeaderBoardCommandWithExistingId() throws Exception {
        // Create the ViewLeaderBoardCommand with an existing ID
        viewLeaderBoardCommand.setId(1L);
        ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO = viewLeaderBoardCommandMapper.toDto(viewLeaderBoardCommand);

        int databaseSizeBeforeCreate = viewLeaderBoardCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restViewLeaderBoardCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewLeaderBoardCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewLeaderBoardCommand in the database
        List<ViewLeaderBoardCommand> viewLeaderBoardCommandList = viewLeaderBoardCommandRepository.findAll();
        assertThat(viewLeaderBoardCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllViewLeaderBoardCommands() throws Exception {
        // Initialize the database
        viewLeaderBoardCommandRepository.saveAndFlush(viewLeaderBoardCommand);

        // Get all the viewLeaderBoardCommandList
        restViewLeaderBoardCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viewLeaderBoardCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getViewLeaderBoardCommand() throws Exception {
        // Initialize the database
        viewLeaderBoardCommandRepository.saveAndFlush(viewLeaderBoardCommand);

        // Get the viewLeaderBoardCommand
        restViewLeaderBoardCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, viewLeaderBoardCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(viewLeaderBoardCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingViewLeaderBoardCommand() throws Exception {
        // Get the viewLeaderBoardCommand
        restViewLeaderBoardCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingViewLeaderBoardCommand() throws Exception {
        // Initialize the database
        viewLeaderBoardCommandRepository.saveAndFlush(viewLeaderBoardCommand);

        int databaseSizeBeforeUpdate = viewLeaderBoardCommandRepository.findAll().size();

        // Update the viewLeaderBoardCommand
        ViewLeaderBoardCommand updatedViewLeaderBoardCommand = viewLeaderBoardCommandRepository
            .findById(viewLeaderBoardCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedViewLeaderBoardCommand are not directly saved in db
        em.detach(updatedViewLeaderBoardCommand);
        ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO = viewLeaderBoardCommandMapper.toDto(updatedViewLeaderBoardCommand);

        restViewLeaderBoardCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewLeaderBoardCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewLeaderBoardCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the ViewLeaderBoardCommand in the database
        List<ViewLeaderBoardCommand> viewLeaderBoardCommandList = viewLeaderBoardCommandRepository.findAll();
        assertThat(viewLeaderBoardCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewLeaderBoardCommand testViewLeaderBoardCommand = viewLeaderBoardCommandList.get(viewLeaderBoardCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingViewLeaderBoardCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewLeaderBoardCommandRepository.findAll().size();
        viewLeaderBoardCommand.setId(count.incrementAndGet());

        // Create the ViewLeaderBoardCommand
        ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO = viewLeaderBoardCommandMapper.toDto(viewLeaderBoardCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewLeaderBoardCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewLeaderBoardCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewLeaderBoardCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewLeaderBoardCommand in the database
        List<ViewLeaderBoardCommand> viewLeaderBoardCommandList = viewLeaderBoardCommandRepository.findAll();
        assertThat(viewLeaderBoardCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchViewLeaderBoardCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewLeaderBoardCommandRepository.findAll().size();
        viewLeaderBoardCommand.setId(count.incrementAndGet());

        // Create the ViewLeaderBoardCommand
        ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO = viewLeaderBoardCommandMapper.toDto(viewLeaderBoardCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewLeaderBoardCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewLeaderBoardCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewLeaderBoardCommand in the database
        List<ViewLeaderBoardCommand> viewLeaderBoardCommandList = viewLeaderBoardCommandRepository.findAll();
        assertThat(viewLeaderBoardCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamViewLeaderBoardCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewLeaderBoardCommandRepository.findAll().size();
        viewLeaderBoardCommand.setId(count.incrementAndGet());

        // Create the ViewLeaderBoardCommand
        ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO = viewLeaderBoardCommandMapper.toDto(viewLeaderBoardCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewLeaderBoardCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewLeaderBoardCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewLeaderBoardCommand in the database
        List<ViewLeaderBoardCommand> viewLeaderBoardCommandList = viewLeaderBoardCommandRepository.findAll();
        assertThat(viewLeaderBoardCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateViewLeaderBoardCommandWithPatch() throws Exception {
        // Initialize the database
        viewLeaderBoardCommandRepository.saveAndFlush(viewLeaderBoardCommand);

        int databaseSizeBeforeUpdate = viewLeaderBoardCommandRepository.findAll().size();

        // Update the viewLeaderBoardCommand using partial update
        ViewLeaderBoardCommand partialUpdatedViewLeaderBoardCommand = new ViewLeaderBoardCommand();
        partialUpdatedViewLeaderBoardCommand.setId(viewLeaderBoardCommand.getId());

        restViewLeaderBoardCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewLeaderBoardCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewLeaderBoardCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewLeaderBoardCommand in the database
        List<ViewLeaderBoardCommand> viewLeaderBoardCommandList = viewLeaderBoardCommandRepository.findAll();
        assertThat(viewLeaderBoardCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewLeaderBoardCommand testViewLeaderBoardCommand = viewLeaderBoardCommandList.get(viewLeaderBoardCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateViewLeaderBoardCommandWithPatch() throws Exception {
        // Initialize the database
        viewLeaderBoardCommandRepository.saveAndFlush(viewLeaderBoardCommand);

        int databaseSizeBeforeUpdate = viewLeaderBoardCommandRepository.findAll().size();

        // Update the viewLeaderBoardCommand using partial update
        ViewLeaderBoardCommand partialUpdatedViewLeaderBoardCommand = new ViewLeaderBoardCommand();
        partialUpdatedViewLeaderBoardCommand.setId(viewLeaderBoardCommand.getId());

        restViewLeaderBoardCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewLeaderBoardCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewLeaderBoardCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewLeaderBoardCommand in the database
        List<ViewLeaderBoardCommand> viewLeaderBoardCommandList = viewLeaderBoardCommandRepository.findAll();
        assertThat(viewLeaderBoardCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewLeaderBoardCommand testViewLeaderBoardCommand = viewLeaderBoardCommandList.get(viewLeaderBoardCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingViewLeaderBoardCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewLeaderBoardCommandRepository.findAll().size();
        viewLeaderBoardCommand.setId(count.incrementAndGet());

        // Create the ViewLeaderBoardCommand
        ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO = viewLeaderBoardCommandMapper.toDto(viewLeaderBoardCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewLeaderBoardCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, viewLeaderBoardCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewLeaderBoardCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewLeaderBoardCommand in the database
        List<ViewLeaderBoardCommand> viewLeaderBoardCommandList = viewLeaderBoardCommandRepository.findAll();
        assertThat(viewLeaderBoardCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchViewLeaderBoardCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewLeaderBoardCommandRepository.findAll().size();
        viewLeaderBoardCommand.setId(count.incrementAndGet());

        // Create the ViewLeaderBoardCommand
        ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO = viewLeaderBoardCommandMapper.toDto(viewLeaderBoardCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewLeaderBoardCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewLeaderBoardCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewLeaderBoardCommand in the database
        List<ViewLeaderBoardCommand> viewLeaderBoardCommandList = viewLeaderBoardCommandRepository.findAll();
        assertThat(viewLeaderBoardCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamViewLeaderBoardCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewLeaderBoardCommandRepository.findAll().size();
        viewLeaderBoardCommand.setId(count.incrementAndGet());

        // Create the ViewLeaderBoardCommand
        ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO = viewLeaderBoardCommandMapper.toDto(viewLeaderBoardCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewLeaderBoardCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewLeaderBoardCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewLeaderBoardCommand in the database
        List<ViewLeaderBoardCommand> viewLeaderBoardCommandList = viewLeaderBoardCommandRepository.findAll();
        assertThat(viewLeaderBoardCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteViewLeaderBoardCommand() throws Exception {
        // Initialize the database
        viewLeaderBoardCommandRepository.saveAndFlush(viewLeaderBoardCommand);

        int databaseSizeBeforeDelete = viewLeaderBoardCommandRepository.findAll().size();

        // Delete the viewLeaderBoardCommand
        restViewLeaderBoardCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, viewLeaderBoardCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ViewLeaderBoardCommand> viewLeaderBoardCommandList = viewLeaderBoardCommandRepository.findAll();
        assertThat(viewLeaderBoardCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
