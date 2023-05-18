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
import org.contextmapper.generated.statcontext.domain.ViewStats;
import org.contextmapper.generated.statcontext.repository.ViewStatsRepository;
import org.contextmapper.generated.statcontext.service.dto.ViewStatsDTO;
import org.contextmapper.generated.statcontext.service.mapper.ViewStatsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ViewStatsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ViewStatsResourceIT {

    private static final String ENTITY_API_URL = "/api/view-stats";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ViewStatsRepository viewStatsRepository;

    @Autowired
    private ViewStatsMapper viewStatsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restViewStatsMockMvc;

    private ViewStats viewStats;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewStats createEntity(EntityManager em) {
        ViewStats viewStats = new ViewStats();
        return viewStats;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewStats createUpdatedEntity(EntityManager em) {
        ViewStats viewStats = new ViewStats();
        return viewStats;
    }

    @BeforeEach
    public void initTest() {
        viewStats = createEntity(em);
    }

    @Test
    @Transactional
    void createViewStats() throws Exception {
        int databaseSizeBeforeCreate = viewStatsRepository.findAll().size();
        // Create the ViewStats
        ViewStatsDTO viewStatsDTO = viewStatsMapper.toDto(viewStats);
        restViewStatsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(viewStatsDTO)))
            .andExpect(status().isCreated());

        // Validate the ViewStats in the database
        List<ViewStats> viewStatsList = viewStatsRepository.findAll();
        assertThat(viewStatsList).hasSize(databaseSizeBeforeCreate + 1);
        ViewStats testViewStats = viewStatsList.get(viewStatsList.size() - 1);
    }

    @Test
    @Transactional
    void createViewStatsWithExistingId() throws Exception {
        // Create the ViewStats with an existing ID
        viewStats.setId(1L);
        ViewStatsDTO viewStatsDTO = viewStatsMapper.toDto(viewStats);

        int databaseSizeBeforeCreate = viewStatsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restViewStatsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(viewStatsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ViewStats in the database
        List<ViewStats> viewStatsList = viewStatsRepository.findAll();
        assertThat(viewStatsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllViewStats() throws Exception {
        // Initialize the database
        viewStatsRepository.saveAndFlush(viewStats);

        // Get all the viewStatsList
        restViewStatsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viewStats.getId().intValue())));
    }

    @Test
    @Transactional
    void getViewStats() throws Exception {
        // Initialize the database
        viewStatsRepository.saveAndFlush(viewStats);

        // Get the viewStats
        restViewStatsMockMvc
            .perform(get(ENTITY_API_URL_ID, viewStats.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(viewStats.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingViewStats() throws Exception {
        // Get the viewStats
        restViewStatsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingViewStats() throws Exception {
        // Initialize the database
        viewStatsRepository.saveAndFlush(viewStats);

        int databaseSizeBeforeUpdate = viewStatsRepository.findAll().size();

        // Update the viewStats
        ViewStats updatedViewStats = viewStatsRepository.findById(viewStats.getId()).get();
        // Disconnect from session so that the updates on updatedViewStats are not directly saved in db
        em.detach(updatedViewStats);
        ViewStatsDTO viewStatsDTO = viewStatsMapper.toDto(updatedViewStats);

        restViewStatsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewStatsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewStatsDTO))
            )
            .andExpect(status().isOk());

        // Validate the ViewStats in the database
        List<ViewStats> viewStatsList = viewStatsRepository.findAll();
        assertThat(viewStatsList).hasSize(databaseSizeBeforeUpdate);
        ViewStats testViewStats = viewStatsList.get(viewStatsList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingViewStats() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsRepository.findAll().size();
        viewStats.setId(count.incrementAndGet());

        // Create the ViewStats
        ViewStatsDTO viewStatsDTO = viewStatsMapper.toDto(viewStats);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewStatsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewStatsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewStats in the database
        List<ViewStats> viewStatsList = viewStatsRepository.findAll();
        assertThat(viewStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchViewStats() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsRepository.findAll().size();
        viewStats.setId(count.incrementAndGet());

        // Create the ViewStats
        ViewStatsDTO viewStatsDTO = viewStatsMapper.toDto(viewStats);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewStatsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewStats in the database
        List<ViewStats> viewStatsList = viewStatsRepository.findAll();
        assertThat(viewStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamViewStats() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsRepository.findAll().size();
        viewStats.setId(count.incrementAndGet());

        // Create the ViewStats
        ViewStatsDTO viewStatsDTO = viewStatsMapper.toDto(viewStats);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewStatsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(viewStatsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewStats in the database
        List<ViewStats> viewStatsList = viewStatsRepository.findAll();
        assertThat(viewStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateViewStatsWithPatch() throws Exception {
        // Initialize the database
        viewStatsRepository.saveAndFlush(viewStats);

        int databaseSizeBeforeUpdate = viewStatsRepository.findAll().size();

        // Update the viewStats using partial update
        ViewStats partialUpdatedViewStats = new ViewStats();
        partialUpdatedViewStats.setId(viewStats.getId());

        restViewStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewStats.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewStats))
            )
            .andExpect(status().isOk());

        // Validate the ViewStats in the database
        List<ViewStats> viewStatsList = viewStatsRepository.findAll();
        assertThat(viewStatsList).hasSize(databaseSizeBeforeUpdate);
        ViewStats testViewStats = viewStatsList.get(viewStatsList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateViewStatsWithPatch() throws Exception {
        // Initialize the database
        viewStatsRepository.saveAndFlush(viewStats);

        int databaseSizeBeforeUpdate = viewStatsRepository.findAll().size();

        // Update the viewStats using partial update
        ViewStats partialUpdatedViewStats = new ViewStats();
        partialUpdatedViewStats.setId(viewStats.getId());

        restViewStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewStats.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewStats))
            )
            .andExpect(status().isOk());

        // Validate the ViewStats in the database
        List<ViewStats> viewStatsList = viewStatsRepository.findAll();
        assertThat(viewStatsList).hasSize(databaseSizeBeforeUpdate);
        ViewStats testViewStats = viewStatsList.get(viewStatsList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingViewStats() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsRepository.findAll().size();
        viewStats.setId(count.incrementAndGet());

        // Create the ViewStats
        ViewStatsDTO viewStatsDTO = viewStatsMapper.toDto(viewStats);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, viewStatsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewStats in the database
        List<ViewStats> viewStatsList = viewStatsRepository.findAll();
        assertThat(viewStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchViewStats() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsRepository.findAll().size();
        viewStats.setId(count.incrementAndGet());

        // Create the ViewStats
        ViewStatsDTO viewStatsDTO = viewStatsMapper.toDto(viewStats);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewStats in the database
        List<ViewStats> viewStatsList = viewStatsRepository.findAll();
        assertThat(viewStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamViewStats() throws Exception {
        int databaseSizeBeforeUpdate = viewStatsRepository.findAll().size();
        viewStats.setId(count.incrementAndGet());

        // Create the ViewStats
        ViewStatsDTO viewStatsDTO = viewStatsMapper.toDto(viewStats);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewStatsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(viewStatsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewStats in the database
        List<ViewStats> viewStatsList = viewStatsRepository.findAll();
        assertThat(viewStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteViewStats() throws Exception {
        // Initialize the database
        viewStatsRepository.saveAndFlush(viewStats);

        int databaseSizeBeforeDelete = viewStatsRepository.findAll().size();

        // Delete the viewStats
        restViewStatsMockMvc
            .perform(delete(ENTITY_API_URL_ID, viewStats.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ViewStats> viewStatsList = viewStatsRepository.findAll();
        assertThat(viewStatsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
