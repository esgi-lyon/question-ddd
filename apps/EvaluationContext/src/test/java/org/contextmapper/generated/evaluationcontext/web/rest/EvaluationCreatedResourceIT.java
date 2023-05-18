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
import org.contextmapper.generated.evaluationcontext.domain.EvaluationCreated;
import org.contextmapper.generated.evaluationcontext.repository.EvaluationCreatedRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationCreatedDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationCreatedMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EvaluationCreatedResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EvaluationCreatedResourceIT {

    private static final String ENTITY_API_URL = "/api/evaluation-createds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvaluationCreatedRepository evaluationCreatedRepository;

    @Autowired
    private EvaluationCreatedMapper evaluationCreatedMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEvaluationCreatedMockMvc;

    private EvaluationCreated evaluationCreated;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationCreated createEntity(EntityManager em) {
        EvaluationCreated evaluationCreated = new EvaluationCreated();
        return evaluationCreated;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationCreated createUpdatedEntity(EntityManager em) {
        EvaluationCreated evaluationCreated = new EvaluationCreated();
        return evaluationCreated;
    }

    @BeforeEach
    public void initTest() {
        evaluationCreated = createEntity(em);
    }

    @Test
    @Transactional
    void createEvaluationCreated() throws Exception {
        int databaseSizeBeforeCreate = evaluationCreatedRepository.findAll().size();
        // Create the EvaluationCreated
        EvaluationCreatedDTO evaluationCreatedDTO = evaluationCreatedMapper.toDto(evaluationCreated);
        restEvaluationCreatedMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(evaluationCreatedDTO))
            )
            .andExpect(status().isCreated());

        // Validate the EvaluationCreated in the database
        List<EvaluationCreated> evaluationCreatedList = evaluationCreatedRepository.findAll();
        assertThat(evaluationCreatedList).hasSize(databaseSizeBeforeCreate + 1);
        EvaluationCreated testEvaluationCreated = evaluationCreatedList.get(evaluationCreatedList.size() - 1);
    }

    @Test
    @Transactional
    void createEvaluationCreatedWithExistingId() throws Exception {
        // Create the EvaluationCreated with an existing ID
        evaluationCreated.setId(1L);
        EvaluationCreatedDTO evaluationCreatedDTO = evaluationCreatedMapper.toDto(evaluationCreated);

        int databaseSizeBeforeCreate = evaluationCreatedRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEvaluationCreatedMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(evaluationCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationCreated in the database
        List<EvaluationCreated> evaluationCreatedList = evaluationCreatedRepository.findAll();
        assertThat(evaluationCreatedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEvaluationCreateds() throws Exception {
        // Initialize the database
        evaluationCreatedRepository.saveAndFlush(evaluationCreated);

        // Get all the evaluationCreatedList
        restEvaluationCreatedMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evaluationCreated.getId().intValue())));
    }

    @Test
    @Transactional
    void getEvaluationCreated() throws Exception {
        // Initialize the database
        evaluationCreatedRepository.saveAndFlush(evaluationCreated);

        // Get the evaluationCreated
        restEvaluationCreatedMockMvc
            .perform(get(ENTITY_API_URL_ID, evaluationCreated.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(evaluationCreated.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEvaluationCreated() throws Exception {
        // Get the evaluationCreated
        restEvaluationCreatedMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEvaluationCreated() throws Exception {
        // Initialize the database
        evaluationCreatedRepository.saveAndFlush(evaluationCreated);

        int databaseSizeBeforeUpdate = evaluationCreatedRepository.findAll().size();

        // Update the evaluationCreated
        EvaluationCreated updatedEvaluationCreated = evaluationCreatedRepository.findById(evaluationCreated.getId()).get();
        // Disconnect from session so that the updates on updatedEvaluationCreated are not directly saved in db
        em.detach(updatedEvaluationCreated);
        EvaluationCreatedDTO evaluationCreatedDTO = evaluationCreatedMapper.toDto(updatedEvaluationCreated);

        restEvaluationCreatedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, evaluationCreatedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(evaluationCreatedDTO))
            )
            .andExpect(status().isOk());

        // Validate the EvaluationCreated in the database
        List<EvaluationCreated> evaluationCreatedList = evaluationCreatedRepository.findAll();
        assertThat(evaluationCreatedList).hasSize(databaseSizeBeforeUpdate);
        EvaluationCreated testEvaluationCreated = evaluationCreatedList.get(evaluationCreatedList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingEvaluationCreated() throws Exception {
        int databaseSizeBeforeUpdate = evaluationCreatedRepository.findAll().size();
        evaluationCreated.setId(count.incrementAndGet());

        // Create the EvaluationCreated
        EvaluationCreatedDTO evaluationCreatedDTO = evaluationCreatedMapper.toDto(evaluationCreated);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEvaluationCreatedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, evaluationCreatedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(evaluationCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationCreated in the database
        List<EvaluationCreated> evaluationCreatedList = evaluationCreatedRepository.findAll();
        assertThat(evaluationCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEvaluationCreated() throws Exception {
        int databaseSizeBeforeUpdate = evaluationCreatedRepository.findAll().size();
        evaluationCreated.setId(count.incrementAndGet());

        // Create the EvaluationCreated
        EvaluationCreatedDTO evaluationCreatedDTO = evaluationCreatedMapper.toDto(evaluationCreated);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvaluationCreatedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(evaluationCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationCreated in the database
        List<EvaluationCreated> evaluationCreatedList = evaluationCreatedRepository.findAll();
        assertThat(evaluationCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEvaluationCreated() throws Exception {
        int databaseSizeBeforeUpdate = evaluationCreatedRepository.findAll().size();
        evaluationCreated.setId(count.incrementAndGet());

        // Create the EvaluationCreated
        EvaluationCreatedDTO evaluationCreatedDTO = evaluationCreatedMapper.toDto(evaluationCreated);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvaluationCreatedMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(evaluationCreatedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EvaluationCreated in the database
        List<EvaluationCreated> evaluationCreatedList = evaluationCreatedRepository.findAll();
        assertThat(evaluationCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEvaluationCreatedWithPatch() throws Exception {
        // Initialize the database
        evaluationCreatedRepository.saveAndFlush(evaluationCreated);

        int databaseSizeBeforeUpdate = evaluationCreatedRepository.findAll().size();

        // Update the evaluationCreated using partial update
        EvaluationCreated partialUpdatedEvaluationCreated = new EvaluationCreated();
        partialUpdatedEvaluationCreated.setId(evaluationCreated.getId());

        restEvaluationCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEvaluationCreated.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEvaluationCreated))
            )
            .andExpect(status().isOk());

        // Validate the EvaluationCreated in the database
        List<EvaluationCreated> evaluationCreatedList = evaluationCreatedRepository.findAll();
        assertThat(evaluationCreatedList).hasSize(databaseSizeBeforeUpdate);
        EvaluationCreated testEvaluationCreated = evaluationCreatedList.get(evaluationCreatedList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateEvaluationCreatedWithPatch() throws Exception {
        // Initialize the database
        evaluationCreatedRepository.saveAndFlush(evaluationCreated);

        int databaseSizeBeforeUpdate = evaluationCreatedRepository.findAll().size();

        // Update the evaluationCreated using partial update
        EvaluationCreated partialUpdatedEvaluationCreated = new EvaluationCreated();
        partialUpdatedEvaluationCreated.setId(evaluationCreated.getId());

        restEvaluationCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEvaluationCreated.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEvaluationCreated))
            )
            .andExpect(status().isOk());

        // Validate the EvaluationCreated in the database
        List<EvaluationCreated> evaluationCreatedList = evaluationCreatedRepository.findAll();
        assertThat(evaluationCreatedList).hasSize(databaseSizeBeforeUpdate);
        EvaluationCreated testEvaluationCreated = evaluationCreatedList.get(evaluationCreatedList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingEvaluationCreated() throws Exception {
        int databaseSizeBeforeUpdate = evaluationCreatedRepository.findAll().size();
        evaluationCreated.setId(count.incrementAndGet());

        // Create the EvaluationCreated
        EvaluationCreatedDTO evaluationCreatedDTO = evaluationCreatedMapper.toDto(evaluationCreated);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEvaluationCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, evaluationCreatedDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(evaluationCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationCreated in the database
        List<EvaluationCreated> evaluationCreatedList = evaluationCreatedRepository.findAll();
        assertThat(evaluationCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEvaluationCreated() throws Exception {
        int databaseSizeBeforeUpdate = evaluationCreatedRepository.findAll().size();
        evaluationCreated.setId(count.incrementAndGet());

        // Create the EvaluationCreated
        EvaluationCreatedDTO evaluationCreatedDTO = evaluationCreatedMapper.toDto(evaluationCreated);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvaluationCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(evaluationCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationCreated in the database
        List<EvaluationCreated> evaluationCreatedList = evaluationCreatedRepository.findAll();
        assertThat(evaluationCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEvaluationCreated() throws Exception {
        int databaseSizeBeforeUpdate = evaluationCreatedRepository.findAll().size();
        evaluationCreated.setId(count.incrementAndGet());

        // Create the EvaluationCreated
        EvaluationCreatedDTO evaluationCreatedDTO = evaluationCreatedMapper.toDto(evaluationCreated);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvaluationCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(evaluationCreatedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EvaluationCreated in the database
        List<EvaluationCreated> evaluationCreatedList = evaluationCreatedRepository.findAll();
        assertThat(evaluationCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEvaluationCreated() throws Exception {
        // Initialize the database
        evaluationCreatedRepository.saveAndFlush(evaluationCreated);

        int databaseSizeBeforeDelete = evaluationCreatedRepository.findAll().size();

        // Delete the evaluationCreated
        restEvaluationCreatedMockMvc
            .perform(delete(ENTITY_API_URL_ID, evaluationCreated.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EvaluationCreated> evaluationCreatedList = evaluationCreatedRepository.findAll();
        assertThat(evaluationCreatedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
