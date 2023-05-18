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
import org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluation;
import org.contextmapper.generated.evaluationcontext.repository.AwardPointForEvaluationRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardPointForEvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AwardPointForEvaluationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AwardPointForEvaluationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AwardPointForEvaluationResourceIT {

    private static final String ENTITY_API_URL = "/api/award-point-for-evaluations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AwardPointForEvaluationRepository awardPointForEvaluationRepository;

    @Autowired
    private AwardPointForEvaluationMapper awardPointForEvaluationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAwardPointForEvaluationMockMvc;

    private AwardPointForEvaluation awardPointForEvaluation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AwardPointForEvaluation createEntity(EntityManager em) {
        AwardPointForEvaluation awardPointForEvaluation = new AwardPointForEvaluation();
        return awardPointForEvaluation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AwardPointForEvaluation createUpdatedEntity(EntityManager em) {
        AwardPointForEvaluation awardPointForEvaluation = new AwardPointForEvaluation();
        return awardPointForEvaluation;
    }

    @BeforeEach
    public void initTest() {
        awardPointForEvaluation = createEntity(em);
    }

    @Test
    @Transactional
    void createAwardPointForEvaluation() throws Exception {
        int databaseSizeBeforeCreate = awardPointForEvaluationRepository.findAll().size();
        // Create the AwardPointForEvaluation
        AwardPointForEvaluationDTO awardPointForEvaluationDTO = awardPointForEvaluationMapper.toDto(awardPointForEvaluation);
        restAwardPointForEvaluationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AwardPointForEvaluation in the database
        List<AwardPointForEvaluation> awardPointForEvaluationList = awardPointForEvaluationRepository.findAll();
        assertThat(awardPointForEvaluationList).hasSize(databaseSizeBeforeCreate + 1);
        AwardPointForEvaluation testAwardPointForEvaluation = awardPointForEvaluationList.get(awardPointForEvaluationList.size() - 1);
    }

    @Test
    @Transactional
    void createAwardPointForEvaluationWithExistingId() throws Exception {
        // Create the AwardPointForEvaluation with an existing ID
        awardPointForEvaluation.setId(1L);
        AwardPointForEvaluationDTO awardPointForEvaluationDTO = awardPointForEvaluationMapper.toDto(awardPointForEvaluation);

        int databaseSizeBeforeCreate = awardPointForEvaluationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAwardPointForEvaluationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardPointForEvaluation in the database
        List<AwardPointForEvaluation> awardPointForEvaluationList = awardPointForEvaluationRepository.findAll();
        assertThat(awardPointForEvaluationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAwardPointForEvaluations() throws Exception {
        // Initialize the database
        awardPointForEvaluationRepository.saveAndFlush(awardPointForEvaluation);

        // Get all the awardPointForEvaluationList
        restAwardPointForEvaluationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(awardPointForEvaluation.getId().intValue())));
    }

    @Test
    @Transactional
    void getAwardPointForEvaluation() throws Exception {
        // Initialize the database
        awardPointForEvaluationRepository.saveAndFlush(awardPointForEvaluation);

        // Get the awardPointForEvaluation
        restAwardPointForEvaluationMockMvc
            .perform(get(ENTITY_API_URL_ID, awardPointForEvaluation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(awardPointForEvaluation.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAwardPointForEvaluation() throws Exception {
        // Get the awardPointForEvaluation
        restAwardPointForEvaluationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAwardPointForEvaluation() throws Exception {
        // Initialize the database
        awardPointForEvaluationRepository.saveAndFlush(awardPointForEvaluation);

        int databaseSizeBeforeUpdate = awardPointForEvaluationRepository.findAll().size();

        // Update the awardPointForEvaluation
        AwardPointForEvaluation updatedAwardPointForEvaluation = awardPointForEvaluationRepository
            .findById(awardPointForEvaluation.getId())
            .get();
        // Disconnect from session so that the updates on updatedAwardPointForEvaluation are not directly saved in db
        em.detach(updatedAwardPointForEvaluation);
        AwardPointForEvaluationDTO awardPointForEvaluationDTO = awardPointForEvaluationMapper.toDto(updatedAwardPointForEvaluation);

        restAwardPointForEvaluationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, awardPointForEvaluationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationDTO))
            )
            .andExpect(status().isOk());

        // Validate the AwardPointForEvaluation in the database
        List<AwardPointForEvaluation> awardPointForEvaluationList = awardPointForEvaluationRepository.findAll();
        assertThat(awardPointForEvaluationList).hasSize(databaseSizeBeforeUpdate);
        AwardPointForEvaluation testAwardPointForEvaluation = awardPointForEvaluationList.get(awardPointForEvaluationList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingAwardPointForEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationRepository.findAll().size();
        awardPointForEvaluation.setId(count.incrementAndGet());

        // Create the AwardPointForEvaluation
        AwardPointForEvaluationDTO awardPointForEvaluationDTO = awardPointForEvaluationMapper.toDto(awardPointForEvaluation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAwardPointForEvaluationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, awardPointForEvaluationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardPointForEvaluation in the database
        List<AwardPointForEvaluation> awardPointForEvaluationList = awardPointForEvaluationRepository.findAll();
        assertThat(awardPointForEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAwardPointForEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationRepository.findAll().size();
        awardPointForEvaluation.setId(count.incrementAndGet());

        // Create the AwardPointForEvaluation
        AwardPointForEvaluationDTO awardPointForEvaluationDTO = awardPointForEvaluationMapper.toDto(awardPointForEvaluation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAwardPointForEvaluationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardPointForEvaluation in the database
        List<AwardPointForEvaluation> awardPointForEvaluationList = awardPointForEvaluationRepository.findAll();
        assertThat(awardPointForEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAwardPointForEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationRepository.findAll().size();
        awardPointForEvaluation.setId(count.incrementAndGet());

        // Create the AwardPointForEvaluation
        AwardPointForEvaluationDTO awardPointForEvaluationDTO = awardPointForEvaluationMapper.toDto(awardPointForEvaluation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAwardPointForEvaluationMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AwardPointForEvaluation in the database
        List<AwardPointForEvaluation> awardPointForEvaluationList = awardPointForEvaluationRepository.findAll();
        assertThat(awardPointForEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAwardPointForEvaluationWithPatch() throws Exception {
        // Initialize the database
        awardPointForEvaluationRepository.saveAndFlush(awardPointForEvaluation);

        int databaseSizeBeforeUpdate = awardPointForEvaluationRepository.findAll().size();

        // Update the awardPointForEvaluation using partial update
        AwardPointForEvaluation partialUpdatedAwardPointForEvaluation = new AwardPointForEvaluation();
        partialUpdatedAwardPointForEvaluation.setId(awardPointForEvaluation.getId());

        restAwardPointForEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAwardPointForEvaluation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAwardPointForEvaluation))
            )
            .andExpect(status().isOk());

        // Validate the AwardPointForEvaluation in the database
        List<AwardPointForEvaluation> awardPointForEvaluationList = awardPointForEvaluationRepository.findAll();
        assertThat(awardPointForEvaluationList).hasSize(databaseSizeBeforeUpdate);
        AwardPointForEvaluation testAwardPointForEvaluation = awardPointForEvaluationList.get(awardPointForEvaluationList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateAwardPointForEvaluationWithPatch() throws Exception {
        // Initialize the database
        awardPointForEvaluationRepository.saveAndFlush(awardPointForEvaluation);

        int databaseSizeBeforeUpdate = awardPointForEvaluationRepository.findAll().size();

        // Update the awardPointForEvaluation using partial update
        AwardPointForEvaluation partialUpdatedAwardPointForEvaluation = new AwardPointForEvaluation();
        partialUpdatedAwardPointForEvaluation.setId(awardPointForEvaluation.getId());

        restAwardPointForEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAwardPointForEvaluation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAwardPointForEvaluation))
            )
            .andExpect(status().isOk());

        // Validate the AwardPointForEvaluation in the database
        List<AwardPointForEvaluation> awardPointForEvaluationList = awardPointForEvaluationRepository.findAll();
        assertThat(awardPointForEvaluationList).hasSize(databaseSizeBeforeUpdate);
        AwardPointForEvaluation testAwardPointForEvaluation = awardPointForEvaluationList.get(awardPointForEvaluationList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingAwardPointForEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationRepository.findAll().size();
        awardPointForEvaluation.setId(count.incrementAndGet());

        // Create the AwardPointForEvaluation
        AwardPointForEvaluationDTO awardPointForEvaluationDTO = awardPointForEvaluationMapper.toDto(awardPointForEvaluation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAwardPointForEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, awardPointForEvaluationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardPointForEvaluation in the database
        List<AwardPointForEvaluation> awardPointForEvaluationList = awardPointForEvaluationRepository.findAll();
        assertThat(awardPointForEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAwardPointForEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationRepository.findAll().size();
        awardPointForEvaluation.setId(count.incrementAndGet());

        // Create the AwardPointForEvaluation
        AwardPointForEvaluationDTO awardPointForEvaluationDTO = awardPointForEvaluationMapper.toDto(awardPointForEvaluation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAwardPointForEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardPointForEvaluation in the database
        List<AwardPointForEvaluation> awardPointForEvaluationList = awardPointForEvaluationRepository.findAll();
        assertThat(awardPointForEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAwardPointForEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationRepository.findAll().size();
        awardPointForEvaluation.setId(count.incrementAndGet());

        // Create the AwardPointForEvaluation
        AwardPointForEvaluationDTO awardPointForEvaluationDTO = awardPointForEvaluationMapper.toDto(awardPointForEvaluation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAwardPointForEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AwardPointForEvaluation in the database
        List<AwardPointForEvaluation> awardPointForEvaluationList = awardPointForEvaluationRepository.findAll();
        assertThat(awardPointForEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAwardPointForEvaluation() throws Exception {
        // Initialize the database
        awardPointForEvaluationRepository.saveAndFlush(awardPointForEvaluation);

        int databaseSizeBeforeDelete = awardPointForEvaluationRepository.findAll().size();

        // Delete the awardPointForEvaluation
        restAwardPointForEvaluationMockMvc
            .perform(delete(ENTITY_API_URL_ID, awardPointForEvaluation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AwardPointForEvaluation> awardPointForEvaluationList = awardPointForEvaluationRepository.findAll();
        assertThat(awardPointForEvaluationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
