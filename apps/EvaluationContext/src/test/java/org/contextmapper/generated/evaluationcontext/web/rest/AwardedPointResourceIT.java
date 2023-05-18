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
import org.contextmapper.generated.evaluationcontext.domain.AwardedPoint;
import org.contextmapper.generated.evaluationcontext.repository.AwardedPointRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardedPointDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AwardedPointMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AwardedPointResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AwardedPointResourceIT {

    private static final String ENTITY_API_URL = "/api/awarded-points";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AwardedPointRepository awardedPointRepository;

    @Autowired
    private AwardedPointMapper awardedPointMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAwardedPointMockMvc;

    private AwardedPoint awardedPoint;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AwardedPoint createEntity(EntityManager em) {
        AwardedPoint awardedPoint = new AwardedPoint();
        return awardedPoint;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AwardedPoint createUpdatedEntity(EntityManager em) {
        AwardedPoint awardedPoint = new AwardedPoint();
        return awardedPoint;
    }

    @BeforeEach
    public void initTest() {
        awardedPoint = createEntity(em);
    }

    @Test
    @Transactional
    void createAwardedPoint() throws Exception {
        int databaseSizeBeforeCreate = awardedPointRepository.findAll().size();
        // Create the AwardedPoint
        AwardedPointDTO awardedPointDTO = awardedPointMapper.toDto(awardedPoint);
        restAwardedPointMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(awardedPointDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AwardedPoint in the database
        List<AwardedPoint> awardedPointList = awardedPointRepository.findAll();
        assertThat(awardedPointList).hasSize(databaseSizeBeforeCreate + 1);
        AwardedPoint testAwardedPoint = awardedPointList.get(awardedPointList.size() - 1);
    }

    @Test
    @Transactional
    void createAwardedPointWithExistingId() throws Exception {
        // Create the AwardedPoint with an existing ID
        awardedPoint.setId(1L);
        AwardedPointDTO awardedPointDTO = awardedPointMapper.toDto(awardedPoint);

        int databaseSizeBeforeCreate = awardedPointRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAwardedPointMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(awardedPointDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardedPoint in the database
        List<AwardedPoint> awardedPointList = awardedPointRepository.findAll();
        assertThat(awardedPointList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAwardedPoints() throws Exception {
        // Initialize the database
        awardedPointRepository.saveAndFlush(awardedPoint);

        // Get all the awardedPointList
        restAwardedPointMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(awardedPoint.getId().intValue())));
    }

    @Test
    @Transactional
    void getAwardedPoint() throws Exception {
        // Initialize the database
        awardedPointRepository.saveAndFlush(awardedPoint);

        // Get the awardedPoint
        restAwardedPointMockMvc
            .perform(get(ENTITY_API_URL_ID, awardedPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(awardedPoint.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAwardedPoint() throws Exception {
        // Get the awardedPoint
        restAwardedPointMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAwardedPoint() throws Exception {
        // Initialize the database
        awardedPointRepository.saveAndFlush(awardedPoint);

        int databaseSizeBeforeUpdate = awardedPointRepository.findAll().size();

        // Update the awardedPoint
        AwardedPoint updatedAwardedPoint = awardedPointRepository.findById(awardedPoint.getId()).get();
        // Disconnect from session so that the updates on updatedAwardedPoint are not directly saved in db
        em.detach(updatedAwardedPoint);
        AwardedPointDTO awardedPointDTO = awardedPointMapper.toDto(updatedAwardedPoint);

        restAwardedPointMockMvc
            .perform(
                put(ENTITY_API_URL_ID, awardedPointDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardedPointDTO))
            )
            .andExpect(status().isOk());

        // Validate the AwardedPoint in the database
        List<AwardedPoint> awardedPointList = awardedPointRepository.findAll();
        assertThat(awardedPointList).hasSize(databaseSizeBeforeUpdate);
        AwardedPoint testAwardedPoint = awardedPointList.get(awardedPointList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingAwardedPoint() throws Exception {
        int databaseSizeBeforeUpdate = awardedPointRepository.findAll().size();
        awardedPoint.setId(count.incrementAndGet());

        // Create the AwardedPoint
        AwardedPointDTO awardedPointDTO = awardedPointMapper.toDto(awardedPoint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAwardedPointMockMvc
            .perform(
                put(ENTITY_API_URL_ID, awardedPointDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardedPointDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardedPoint in the database
        List<AwardedPoint> awardedPointList = awardedPointRepository.findAll();
        assertThat(awardedPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAwardedPoint() throws Exception {
        int databaseSizeBeforeUpdate = awardedPointRepository.findAll().size();
        awardedPoint.setId(count.incrementAndGet());

        // Create the AwardedPoint
        AwardedPointDTO awardedPointDTO = awardedPointMapper.toDto(awardedPoint);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAwardedPointMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardedPointDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardedPoint in the database
        List<AwardedPoint> awardedPointList = awardedPointRepository.findAll();
        assertThat(awardedPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAwardedPoint() throws Exception {
        int databaseSizeBeforeUpdate = awardedPointRepository.findAll().size();
        awardedPoint.setId(count.incrementAndGet());

        // Create the AwardedPoint
        AwardedPointDTO awardedPointDTO = awardedPointMapper.toDto(awardedPoint);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAwardedPointMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(awardedPointDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AwardedPoint in the database
        List<AwardedPoint> awardedPointList = awardedPointRepository.findAll();
        assertThat(awardedPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAwardedPointWithPatch() throws Exception {
        // Initialize the database
        awardedPointRepository.saveAndFlush(awardedPoint);

        int databaseSizeBeforeUpdate = awardedPointRepository.findAll().size();

        // Update the awardedPoint using partial update
        AwardedPoint partialUpdatedAwardedPoint = new AwardedPoint();
        partialUpdatedAwardedPoint.setId(awardedPoint.getId());

        restAwardedPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAwardedPoint.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAwardedPoint))
            )
            .andExpect(status().isOk());

        // Validate the AwardedPoint in the database
        List<AwardedPoint> awardedPointList = awardedPointRepository.findAll();
        assertThat(awardedPointList).hasSize(databaseSizeBeforeUpdate);
        AwardedPoint testAwardedPoint = awardedPointList.get(awardedPointList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateAwardedPointWithPatch() throws Exception {
        // Initialize the database
        awardedPointRepository.saveAndFlush(awardedPoint);

        int databaseSizeBeforeUpdate = awardedPointRepository.findAll().size();

        // Update the awardedPoint using partial update
        AwardedPoint partialUpdatedAwardedPoint = new AwardedPoint();
        partialUpdatedAwardedPoint.setId(awardedPoint.getId());

        restAwardedPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAwardedPoint.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAwardedPoint))
            )
            .andExpect(status().isOk());

        // Validate the AwardedPoint in the database
        List<AwardedPoint> awardedPointList = awardedPointRepository.findAll();
        assertThat(awardedPointList).hasSize(databaseSizeBeforeUpdate);
        AwardedPoint testAwardedPoint = awardedPointList.get(awardedPointList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingAwardedPoint() throws Exception {
        int databaseSizeBeforeUpdate = awardedPointRepository.findAll().size();
        awardedPoint.setId(count.incrementAndGet());

        // Create the AwardedPoint
        AwardedPointDTO awardedPointDTO = awardedPointMapper.toDto(awardedPoint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAwardedPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, awardedPointDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(awardedPointDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardedPoint in the database
        List<AwardedPoint> awardedPointList = awardedPointRepository.findAll();
        assertThat(awardedPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAwardedPoint() throws Exception {
        int databaseSizeBeforeUpdate = awardedPointRepository.findAll().size();
        awardedPoint.setId(count.incrementAndGet());

        // Create the AwardedPoint
        AwardedPointDTO awardedPointDTO = awardedPointMapper.toDto(awardedPoint);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAwardedPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(awardedPointDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardedPoint in the database
        List<AwardedPoint> awardedPointList = awardedPointRepository.findAll();
        assertThat(awardedPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAwardedPoint() throws Exception {
        int databaseSizeBeforeUpdate = awardedPointRepository.findAll().size();
        awardedPoint.setId(count.incrementAndGet());

        // Create the AwardedPoint
        AwardedPointDTO awardedPointDTO = awardedPointMapper.toDto(awardedPoint);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAwardedPointMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(awardedPointDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AwardedPoint in the database
        List<AwardedPoint> awardedPointList = awardedPointRepository.findAll();
        assertThat(awardedPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAwardedPoint() throws Exception {
        // Initialize the database
        awardedPointRepository.saveAndFlush(awardedPoint);

        int databaseSizeBeforeDelete = awardedPointRepository.findAll().size();

        // Delete the awardedPoint
        restAwardedPointMockMvc
            .perform(delete(ENTITY_API_URL_ID, awardedPoint.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AwardedPoint> awardedPointList = awardedPointRepository.findAll();
        assertThat(awardedPointList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
