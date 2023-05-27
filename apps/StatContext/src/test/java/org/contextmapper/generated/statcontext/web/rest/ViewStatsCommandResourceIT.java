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
import org.contextmapper.generated.statcontext.domain.ViewStatsCommand;
import org.contextmapper.generated.statcontext.repository.ViewStatsCommandRepository;
import org.contextmapper.generated.statcontext.service.dto.ViewStatsCommandDTO;
import org.contextmapper.generated.statcontext.service.mapper.ViewStatsCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ViewStatsCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ViewStatsCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/view-stats-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ViewStatsCommandRepository viewStatsCommandRepository;

    @Autowired
    private ViewStatsCommandMapper viewStatsCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restViewStatsCommandMockMvc;

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

    @BeforeEach
    public void initTest() {
        viewStatsCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createViewStatsCommand() throws Exception {
        int databaseSizeBeforeCreate = viewStatsCommandRepository.findAll().size();
        // Create the ViewStatsCommand
        ViewStatsCommandDTO viewStatsCommandDTO = viewStatsCommandMapper.toDto(viewStatsCommand);
        restViewStatsCommandMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(viewStatsCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeCreate + 1);
        ViewStatsCommand testViewStatsCommand = viewStatsCommandList.get(viewStatsCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createViewStatsCommandWithExistingId() throws Exception {
        // Create the ViewStatsCommand with an existing ID
        viewStatsCommand.setId(1L);
        ViewStatsCommandDTO viewStatsCommandDTO = viewStatsCommandMapper.toDto(viewStatsCommand);

        int databaseSizeBeforeCreate = viewStatsCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restViewStatsCommandMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(viewStatsCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllViewStatsCommands() throws Exception {
        // Initialize the database
        viewStatsCommandRepository.saveAndFlush(viewStatsCommand);

        // Get all the viewStatsCommandList
        restViewStatsCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viewStatsCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getViewStatsCommand() throws Exception {
        // Initialize the database
        viewStatsCommandRepository.saveAndFlush(viewStatsCommand);

        // Get the viewStatsCommand
        restViewStatsCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, viewStatsCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(viewStatsCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingViewStatsCommand() throws Exception {
        // Get the viewStatsCommand
        restViewStatsCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingViewStatsCommand() throws Exception {
        // Initialize the database
        viewStatsCommandRepository.saveAndFlush(viewStatsCommand);

        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().size();

        // Update the viewStatsCommand
        ViewStatsCommand updatedViewStatsCommand = viewStatsCommandRepository.findById(viewStatsCommand.getId()).get();
        // Disconnect from session so that the updates on updatedViewStatsCommand are not directly saved in db
        em.detach(updatedViewStatsCommand);
        ViewStatsCommandDTO viewStatsCommandDTO = viewStatsCommandMapper.toDto(updatedViewStatsCommand);

        restViewStatsCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewStatsCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewStatsCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewStatsCommand testViewStatsCommand = viewStatsCommandList.get(viewStatsCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingViewStatsCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().size();
        viewStatsCommand.setId(count.incrementAndGet());

        // Create the ViewStatsCommand
        ViewStatsCommandDTO viewStatsCommandDTO = viewStatsCommandMapper.toDto(viewStatsCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewStatsCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewStatsCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewStatsCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchViewStatsCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().size();
        viewStatsCommand.setId(count.incrementAndGet());

        // Create the ViewStatsCommand
        ViewStatsCommandDTO viewStatsCommandDTO = viewStatsCommandMapper.toDto(viewStatsCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewStatsCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewStatsCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamViewStatsCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().size();
        viewStatsCommand.setId(count.incrementAndGet());

        // Create the ViewStatsCommand
        ViewStatsCommandDTO viewStatsCommandDTO = viewStatsCommandMapper.toDto(viewStatsCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewStatsCommandMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(viewStatsCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateViewStatsCommandWithPatch() throws Exception {
        // Initialize the database
        viewStatsCommandRepository.saveAndFlush(viewStatsCommand);

        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().size();

        // Update the viewStatsCommand using partial update
        ViewStatsCommand partialUpdatedViewStatsCommand = new ViewStatsCommand();
        partialUpdatedViewStatsCommand.setId(viewStatsCommand.getId());

        restViewStatsCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewStatsCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewStatsCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewStatsCommand testViewStatsCommand = viewStatsCommandList.get(viewStatsCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateViewStatsCommandWithPatch() throws Exception {
        // Initialize the database
        viewStatsCommandRepository.saveAndFlush(viewStatsCommand);

        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().size();

        // Update the viewStatsCommand using partial update
        ViewStatsCommand partialUpdatedViewStatsCommand = new ViewStatsCommand();
        partialUpdatedViewStatsCommand.setId(viewStatsCommand.getId());

        restViewStatsCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewStatsCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewStatsCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewStatsCommand testViewStatsCommand = viewStatsCommandList.get(viewStatsCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingViewStatsCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().size();
        viewStatsCommand.setId(count.incrementAndGet());

        // Create the ViewStatsCommand
        ViewStatsCommandDTO viewStatsCommandDTO = viewStatsCommandMapper.toDto(viewStatsCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewStatsCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, viewStatsCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewStatsCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchViewStatsCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().size();
        viewStatsCommand.setId(count.incrementAndGet());

        // Create the ViewStatsCommand
        ViewStatsCommandDTO viewStatsCommandDTO = viewStatsCommandMapper.toDto(viewStatsCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewStatsCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewStatsCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamViewStatsCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsCommandRepository.findAll().size();
        viewStatsCommand.setId(count.incrementAndGet());

        // Create the ViewStatsCommand
        ViewStatsCommandDTO viewStatsCommandDTO = viewStatsCommandMapper.toDto(viewStatsCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewStatsCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewStatsCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewStatsCommand in the database
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteViewStatsCommand() throws Exception {
        // Initialize the database
        viewStatsCommandRepository.saveAndFlush(viewStatsCommand);

        int databaseSizeBeforeDelete = viewStatsCommandRepository.findAll().size();

        // Delete the viewStatsCommand
        restViewStatsCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, viewStatsCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ViewStatsCommand> viewStatsCommandList = viewStatsCommandRepository.findAll();
        assertThat(viewStatsCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
