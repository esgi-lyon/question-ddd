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
import org.contextmapper.generated.statcontext.domain.EvaluationStats;
import org.contextmapper.generated.statcontext.repository.EvaluationStatsRepository;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatsDTO;
import org.contextmapper.generated.statcontext.service.mapper.EvaluationStatsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EvaluationStatsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EvaluationStatsResourceIT {

    private static final String ENTITY_API_URL = "/api/evaluation-stats";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvaluationStatsRepository evaluationStatsRepository;

    @Autowired
    private EvaluationStatsMapper evaluationStatsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEvaluationStatsMockMvc;

    private EvaluationStats evaluationStats;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationStats createEntity(EntityManager em) {
        EvaluationStats evaluationStats = new EvaluationStats();
        return evaluationStats;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationStats createUpdatedEntity(EntityManager em) {
        EvaluationStats evaluationStats = new EvaluationStats();
        return evaluationStats;
    }

    @BeforeEach
    public void initTest() {
        evaluationStats = createEntity(em);
    }

    @Test
    @Transactional
    void createEvaluationStats() throws Exception {
        int databaseSizeBeforeCreate = evaluationStatsRepository.findAll().size();
        // Create the EvaluationStats
        EvaluationStatsDTO evaluationStatsDTO = evaluationStatsMapper.toDto(evaluationStats);
        restEvaluationStatsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(evaluationStatsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the EvaluationStats in the database
        List<EvaluationStats> evaluationStatsList = evaluationStatsRepository.findAll();
        assertThat(evaluationStatsList).hasSize(databaseSizeBeforeCreate + 1);
        EvaluationStats testEvaluationStats = evaluationStatsList.get(evaluationStatsList.size() - 1);
    }

    @Test
    @Transactional
    void createEvaluationStatsWithExistingId() throws Exception {
        // Create the EvaluationStats with an existing ID
        evaluationStats.setId(1L);
        EvaluationStatsDTO evaluationStatsDTO = evaluationStatsMapper.toDto(evaluationStats);

        int databaseSizeBeforeCreate = evaluationStatsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEvaluationStatsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(evaluationStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationStats in the database
        List<EvaluationStats> evaluationStatsList = evaluationStatsRepository.findAll();
        assertThat(evaluationStatsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEvaluationStats() throws Exception {
        // Initialize the database
        evaluationStatsRepository.saveAndFlush(evaluationStats);

        // Get all the evaluationStatsList
        restEvaluationStatsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evaluationStats.getId().intValue())));
    }

    @Test
    @Transactional
    void getEvaluationStats() throws Exception {
        // Initialize the database
        evaluationStatsRepository.saveAndFlush(evaluationStats);

        // Get the evaluationStats
        restEvaluationStatsMockMvc
            .perform(get(ENTITY_API_URL_ID, evaluationStats.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(evaluationStats.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEvaluationStats() throws Exception {
        // Get the evaluationStats
        restEvaluationStatsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEvaluationStats() throws Exception {
        // Initialize the database
        evaluationStatsRepository.saveAndFlush(evaluationStats);

        int databaseSizeBeforeUpdate = evaluationStatsRepository.findAll().size();

        // Update the evaluationStats
        EvaluationStats updatedEvaluationStats = evaluationStatsRepository.findById(evaluationStats.getId()).get();
        // Disconnect from session so that the updates on updatedEvaluationStats are not directly saved in db
        em.detach(updatedEvaluationStats);
        EvaluationStatsDTO evaluationStatsDTO = evaluationStatsMapper.toDto(updatedEvaluationStats);

        restEvaluationStatsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, evaluationStatsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(evaluationStatsDTO))
            )
            .andExpect(status().isOk());

        // Validate the EvaluationStats in the database
        List<EvaluationStats> evaluationStatsList = evaluationStatsRepository.findAll();
        assertThat(evaluationStatsList).hasSize(databaseSizeBeforeUpdate);
        EvaluationStats testEvaluationStats = evaluationStatsList.get(evaluationStatsList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingEvaluationStats() throws Exception {
        int databaseSizeBeforeUpdate = evaluationStatsRepository.findAll().size();
        evaluationStats.setId(count.incrementAndGet());

        // Create the EvaluationStats
        EvaluationStatsDTO evaluationStatsDTO = evaluationStatsMapper.toDto(evaluationStats);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEvaluationStatsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, evaluationStatsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(evaluationStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationStats in the database
        List<EvaluationStats> evaluationStatsList = evaluationStatsRepository.findAll();
        assertThat(evaluationStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEvaluationStats() throws Exception {
        int databaseSizeBeforeUpdate = evaluationStatsRepository.findAll().size();
        evaluationStats.setId(count.incrementAndGet());

        // Create the EvaluationStats
        EvaluationStatsDTO evaluationStatsDTO = evaluationStatsMapper.toDto(evaluationStats);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvaluationStatsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(evaluationStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationStats in the database
        List<EvaluationStats> evaluationStatsList = evaluationStatsRepository.findAll();
        assertThat(evaluationStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEvaluationStats() throws Exception {
        int databaseSizeBeforeUpdate = evaluationStatsRepository.findAll().size();
        evaluationStats.setId(count.incrementAndGet());

        // Create the EvaluationStats
        EvaluationStatsDTO evaluationStatsDTO = evaluationStatsMapper.toDto(evaluationStats);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvaluationStatsMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(evaluationStatsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EvaluationStats in the database
        List<EvaluationStats> evaluationStatsList = evaluationStatsRepository.findAll();
        assertThat(evaluationStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEvaluationStatsWithPatch() throws Exception {
        // Initialize the database
        evaluationStatsRepository.saveAndFlush(evaluationStats);

        int databaseSizeBeforeUpdate = evaluationStatsRepository.findAll().size();

        // Update the evaluationStats using partial update
        EvaluationStats partialUpdatedEvaluationStats = new EvaluationStats();
        partialUpdatedEvaluationStats.setId(evaluationStats.getId());

        restEvaluationStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEvaluationStats.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEvaluationStats))
            )
            .andExpect(status().isOk());

        // Validate the EvaluationStats in the database
        List<EvaluationStats> evaluationStatsList = evaluationStatsRepository.findAll();
        assertThat(evaluationStatsList).hasSize(databaseSizeBeforeUpdate);
        EvaluationStats testEvaluationStats = evaluationStatsList.get(evaluationStatsList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateEvaluationStatsWithPatch() throws Exception {
        // Initialize the database
        evaluationStatsRepository.saveAndFlush(evaluationStats);

        int databaseSizeBeforeUpdate = evaluationStatsRepository.findAll().size();

        // Update the evaluationStats using partial update
        EvaluationStats partialUpdatedEvaluationStats = new EvaluationStats();
        partialUpdatedEvaluationStats.setId(evaluationStats.getId());

        restEvaluationStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEvaluationStats.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEvaluationStats))
            )
            .andExpect(status().isOk());

        // Validate the EvaluationStats in the database
        List<EvaluationStats> evaluationStatsList = evaluationStatsRepository.findAll();
        assertThat(evaluationStatsList).hasSize(databaseSizeBeforeUpdate);
        EvaluationStats testEvaluationStats = evaluationStatsList.get(evaluationStatsList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingEvaluationStats() throws Exception {
        int databaseSizeBeforeUpdate = evaluationStatsRepository.findAll().size();
        evaluationStats.setId(count.incrementAndGet());

        // Create the EvaluationStats
        EvaluationStatsDTO evaluationStatsDTO = evaluationStatsMapper.toDto(evaluationStats);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEvaluationStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, evaluationStatsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(evaluationStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationStats in the database
        List<EvaluationStats> evaluationStatsList = evaluationStatsRepository.findAll();
        assertThat(evaluationStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEvaluationStats() throws Exception {
        int databaseSizeBeforeUpdate = evaluationStatsRepository.findAll().size();
        evaluationStats.setId(count.incrementAndGet());

        // Create the EvaluationStats
        EvaluationStatsDTO evaluationStatsDTO = evaluationStatsMapper.toDto(evaluationStats);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvaluationStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(evaluationStatsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationStats in the database
        List<EvaluationStats> evaluationStatsList = evaluationStatsRepository.findAll();
        assertThat(evaluationStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEvaluationStats() throws Exception {
        int databaseSizeBeforeUpdate = evaluationStatsRepository.findAll().size();
        evaluationStats.setId(count.incrementAndGet());

        // Create the EvaluationStats
        EvaluationStatsDTO evaluationStatsDTO = evaluationStatsMapper.toDto(evaluationStats);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvaluationStatsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(evaluationStatsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EvaluationStats in the database
        List<EvaluationStats> evaluationStatsList = evaluationStatsRepository.findAll();
        assertThat(evaluationStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEvaluationStats() throws Exception {
        // Initialize the database
        evaluationStatsRepository.saveAndFlush(evaluationStats);

        int databaseSizeBeforeDelete = evaluationStatsRepository.findAll().size();

        // Delete the evaluationStats
        restEvaluationStatsMockMvc
            .perform(delete(ENTITY_API_URL_ID, evaluationStats.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EvaluationStats> evaluationStatsList = evaluationStatsRepository.findAll();
        assertThat(evaluationStatsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
