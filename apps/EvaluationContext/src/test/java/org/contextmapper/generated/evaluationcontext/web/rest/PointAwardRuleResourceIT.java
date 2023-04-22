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
import org.contextmapper.generated.evaluationcontext.domain.PointAwardRule;
import org.contextmapper.generated.evaluationcontext.repository.PointAwardRuleRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.PointAwardRuleDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.PointAwardRuleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PointAwardRuleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PointAwardRuleResourceIT {

    private static final Integer DEFAULT_SCORE_EVOLUTION = 1;
    private static final Integer UPDATED_SCORE_EVOLUTION = 2;

    private static final String ENTITY_API_URL = "/api/point-award-rules";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PointAwardRuleRepository pointAwardRuleRepository;

    @Autowired
    private PointAwardRuleMapper pointAwardRuleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPointAwardRuleMockMvc;

    private PointAwardRule pointAwardRule;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PointAwardRule createEntity(EntityManager em) {
        PointAwardRule pointAwardRule = new PointAwardRule().scoreEvolution(DEFAULT_SCORE_EVOLUTION);
        return pointAwardRule;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PointAwardRule createUpdatedEntity(EntityManager em) {
        PointAwardRule pointAwardRule = new PointAwardRule().scoreEvolution(UPDATED_SCORE_EVOLUTION);
        return pointAwardRule;
    }

    @BeforeEach
    public void initTest() {
        pointAwardRule = createEntity(em);
    }

    @Test
    @Transactional
    void createPointAwardRule() throws Exception {
        int databaseSizeBeforeCreate = pointAwardRuleRepository.findAll().size();
        // Create the PointAwardRule
        PointAwardRuleDTO pointAwardRuleDTO = pointAwardRuleMapper.toDto(pointAwardRule);
        restPointAwardRuleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pointAwardRuleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PointAwardRule in the database
        List<PointAwardRule> pointAwardRuleList = pointAwardRuleRepository.findAll();
        assertThat(pointAwardRuleList).hasSize(databaseSizeBeforeCreate + 1);
        PointAwardRule testPointAwardRule = pointAwardRuleList.get(pointAwardRuleList.size() - 1);
        assertThat(testPointAwardRule.getScoreEvolution()).isEqualTo(DEFAULT_SCORE_EVOLUTION);
    }

    @Test
    @Transactional
    void createPointAwardRuleWithExistingId() throws Exception {
        // Create the PointAwardRule with an existing ID
        pointAwardRule.setId(1L);
        PointAwardRuleDTO pointAwardRuleDTO = pointAwardRuleMapper.toDto(pointAwardRule);

        int databaseSizeBeforeCreate = pointAwardRuleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPointAwardRuleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pointAwardRuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PointAwardRule in the database
        List<PointAwardRule> pointAwardRuleList = pointAwardRuleRepository.findAll();
        assertThat(pointAwardRuleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPointAwardRules() throws Exception {
        // Initialize the database
        pointAwardRuleRepository.saveAndFlush(pointAwardRule);

        // Get all the pointAwardRuleList
        restPointAwardRuleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pointAwardRule.getId().intValue())))
            .andExpect(jsonPath("$.[*].scoreEvolution").value(hasItem(DEFAULT_SCORE_EVOLUTION)));
    }

    @Test
    @Transactional
    void getPointAwardRule() throws Exception {
        // Initialize the database
        pointAwardRuleRepository.saveAndFlush(pointAwardRule);

        // Get the pointAwardRule
        restPointAwardRuleMockMvc
            .perform(get(ENTITY_API_URL_ID, pointAwardRule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pointAwardRule.getId().intValue()))
            .andExpect(jsonPath("$.scoreEvolution").value(DEFAULT_SCORE_EVOLUTION));
    }

    @Test
    @Transactional
    void getNonExistingPointAwardRule() throws Exception {
        // Get the pointAwardRule
        restPointAwardRuleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPointAwardRule() throws Exception {
        // Initialize the database
        pointAwardRuleRepository.saveAndFlush(pointAwardRule);

        int databaseSizeBeforeUpdate = pointAwardRuleRepository.findAll().size();

        // Update the pointAwardRule
        PointAwardRule updatedPointAwardRule = pointAwardRuleRepository.findById(pointAwardRule.getId()).get();
        // Disconnect from session so that the updates on updatedPointAwardRule are not directly saved in db
        em.detach(updatedPointAwardRule);
        updatedPointAwardRule.scoreEvolution(UPDATED_SCORE_EVOLUTION);
        PointAwardRuleDTO pointAwardRuleDTO = pointAwardRuleMapper.toDto(updatedPointAwardRule);

        restPointAwardRuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pointAwardRuleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pointAwardRuleDTO))
            )
            .andExpect(status().isOk());

        // Validate the PointAwardRule in the database
        List<PointAwardRule> pointAwardRuleList = pointAwardRuleRepository.findAll();
        assertThat(pointAwardRuleList).hasSize(databaseSizeBeforeUpdate);
        PointAwardRule testPointAwardRule = pointAwardRuleList.get(pointAwardRuleList.size() - 1);
        assertThat(testPointAwardRule.getScoreEvolution()).isEqualTo(UPDATED_SCORE_EVOLUTION);
    }

    @Test
    @Transactional
    void putNonExistingPointAwardRule() throws Exception {
        int databaseSizeBeforeUpdate = pointAwardRuleRepository.findAll().size();
        pointAwardRule.setId(count.incrementAndGet());

        // Create the PointAwardRule
        PointAwardRuleDTO pointAwardRuleDTO = pointAwardRuleMapper.toDto(pointAwardRule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPointAwardRuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pointAwardRuleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pointAwardRuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PointAwardRule in the database
        List<PointAwardRule> pointAwardRuleList = pointAwardRuleRepository.findAll();
        assertThat(pointAwardRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPointAwardRule() throws Exception {
        int databaseSizeBeforeUpdate = pointAwardRuleRepository.findAll().size();
        pointAwardRule.setId(count.incrementAndGet());

        // Create the PointAwardRule
        PointAwardRuleDTO pointAwardRuleDTO = pointAwardRuleMapper.toDto(pointAwardRule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPointAwardRuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pointAwardRuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PointAwardRule in the database
        List<PointAwardRule> pointAwardRuleList = pointAwardRuleRepository.findAll();
        assertThat(pointAwardRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPointAwardRule() throws Exception {
        int databaseSizeBeforeUpdate = pointAwardRuleRepository.findAll().size();
        pointAwardRule.setId(count.incrementAndGet());

        // Create the PointAwardRule
        PointAwardRuleDTO pointAwardRuleDTO = pointAwardRuleMapper.toDto(pointAwardRule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPointAwardRuleMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pointAwardRuleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PointAwardRule in the database
        List<PointAwardRule> pointAwardRuleList = pointAwardRuleRepository.findAll();
        assertThat(pointAwardRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePointAwardRuleWithPatch() throws Exception {
        // Initialize the database
        pointAwardRuleRepository.saveAndFlush(pointAwardRule);

        int databaseSizeBeforeUpdate = pointAwardRuleRepository.findAll().size();

        // Update the pointAwardRule using partial update
        PointAwardRule partialUpdatedPointAwardRule = new PointAwardRule();
        partialUpdatedPointAwardRule.setId(pointAwardRule.getId());

        restPointAwardRuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPointAwardRule.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPointAwardRule))
            )
            .andExpect(status().isOk());

        // Validate the PointAwardRule in the database
        List<PointAwardRule> pointAwardRuleList = pointAwardRuleRepository.findAll();
        assertThat(pointAwardRuleList).hasSize(databaseSizeBeforeUpdate);
        PointAwardRule testPointAwardRule = pointAwardRuleList.get(pointAwardRuleList.size() - 1);
        assertThat(testPointAwardRule.getScoreEvolution()).isEqualTo(DEFAULT_SCORE_EVOLUTION);
    }

    @Test
    @Transactional
    void fullUpdatePointAwardRuleWithPatch() throws Exception {
        // Initialize the database
        pointAwardRuleRepository.saveAndFlush(pointAwardRule);

        int databaseSizeBeforeUpdate = pointAwardRuleRepository.findAll().size();

        // Update the pointAwardRule using partial update
        PointAwardRule partialUpdatedPointAwardRule = new PointAwardRule();
        partialUpdatedPointAwardRule.setId(pointAwardRule.getId());

        partialUpdatedPointAwardRule.scoreEvolution(UPDATED_SCORE_EVOLUTION);

        restPointAwardRuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPointAwardRule.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPointAwardRule))
            )
            .andExpect(status().isOk());

        // Validate the PointAwardRule in the database
        List<PointAwardRule> pointAwardRuleList = pointAwardRuleRepository.findAll();
        assertThat(pointAwardRuleList).hasSize(databaseSizeBeforeUpdate);
        PointAwardRule testPointAwardRule = pointAwardRuleList.get(pointAwardRuleList.size() - 1);
        assertThat(testPointAwardRule.getScoreEvolution()).isEqualTo(UPDATED_SCORE_EVOLUTION);
    }

    @Test
    @Transactional
    void patchNonExistingPointAwardRule() throws Exception {
        int databaseSizeBeforeUpdate = pointAwardRuleRepository.findAll().size();
        pointAwardRule.setId(count.incrementAndGet());

        // Create the PointAwardRule
        PointAwardRuleDTO pointAwardRuleDTO = pointAwardRuleMapper.toDto(pointAwardRule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPointAwardRuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pointAwardRuleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pointAwardRuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PointAwardRule in the database
        List<PointAwardRule> pointAwardRuleList = pointAwardRuleRepository.findAll();
        assertThat(pointAwardRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPointAwardRule() throws Exception {
        int databaseSizeBeforeUpdate = pointAwardRuleRepository.findAll().size();
        pointAwardRule.setId(count.incrementAndGet());

        // Create the PointAwardRule
        PointAwardRuleDTO pointAwardRuleDTO = pointAwardRuleMapper.toDto(pointAwardRule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPointAwardRuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pointAwardRuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PointAwardRule in the database
        List<PointAwardRule> pointAwardRuleList = pointAwardRuleRepository.findAll();
        assertThat(pointAwardRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPointAwardRule() throws Exception {
        int databaseSizeBeforeUpdate = pointAwardRuleRepository.findAll().size();
        pointAwardRule.setId(count.incrementAndGet());

        // Create the PointAwardRule
        PointAwardRuleDTO pointAwardRuleDTO = pointAwardRuleMapper.toDto(pointAwardRule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPointAwardRuleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pointAwardRuleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PointAwardRule in the database
        List<PointAwardRule> pointAwardRuleList = pointAwardRuleRepository.findAll();
        assertThat(pointAwardRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePointAwardRule() throws Exception {
        // Initialize the database
        pointAwardRuleRepository.saveAndFlush(pointAwardRule);

        int databaseSizeBeforeDelete = pointAwardRuleRepository.findAll().size();

        // Delete the pointAwardRule
        restPointAwardRuleMockMvc
            .perform(delete(ENTITY_API_URL_ID, pointAwardRule.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PointAwardRule> pointAwardRuleList = pointAwardRuleRepository.findAll();
        assertThat(pointAwardRuleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
