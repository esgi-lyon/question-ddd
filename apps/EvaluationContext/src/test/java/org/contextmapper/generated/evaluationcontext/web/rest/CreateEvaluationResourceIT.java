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
import org.contextmapper.generated.evaluationcontext.domain.CreateEvaluation;
import org.contextmapper.generated.evaluationcontext.repository.CreateEvaluationRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.CreateEvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.CreateEvaluationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreateEvaluationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreateEvaluationResourceIT {

    private static final String ENTITY_API_URL = "/api/create-evaluations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateEvaluationRepository createEvaluationRepository;

    @Autowired
    private CreateEvaluationMapper createEvaluationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreateEvaluationMockMvc;

    private CreateEvaluation createEvaluation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateEvaluation createEntity(EntityManager em) {
        CreateEvaluation createEvaluation = new CreateEvaluation();
        return createEvaluation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateEvaluation createUpdatedEntity(EntityManager em) {
        CreateEvaluation createEvaluation = new CreateEvaluation();
        return createEvaluation;
    }

    @BeforeEach
    public void initTest() {
        createEvaluation = createEntity(em);
    }

    @Test
    @Transactional
    void createCreateEvaluation() throws Exception {
        int databaseSizeBeforeCreate = createEvaluationRepository.findAll().size();
        // Create the CreateEvaluation
        CreateEvaluationDTO createEvaluationDTO = createEvaluationMapper.toDto(createEvaluation);
        restCreateEvaluationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createEvaluationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CreateEvaluation in the database
        List<CreateEvaluation> createEvaluationList = createEvaluationRepository.findAll();
        assertThat(createEvaluationList).hasSize(databaseSizeBeforeCreate + 1);
        CreateEvaluation testCreateEvaluation = createEvaluationList.get(createEvaluationList.size() - 1);
    }

    @Test
    @Transactional
    void createCreateEvaluationWithExistingId() throws Exception {
        // Create the CreateEvaluation with an existing ID
        createEvaluation.setId(1L);
        CreateEvaluationDTO createEvaluationDTO = createEvaluationMapper.toDto(createEvaluation);

        int databaseSizeBeforeCreate = createEvaluationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreateEvaluationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createEvaluationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateEvaluation in the database
        List<CreateEvaluation> createEvaluationList = createEvaluationRepository.findAll();
        assertThat(createEvaluationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCreateEvaluations() throws Exception {
        // Initialize the database
        createEvaluationRepository.saveAndFlush(createEvaluation);

        // Get all the createEvaluationList
        restCreateEvaluationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(createEvaluation.getId().intValue())));
    }

    @Test
    @Transactional
    void getCreateEvaluation() throws Exception {
        // Initialize the database
        createEvaluationRepository.saveAndFlush(createEvaluation);

        // Get the createEvaluation
        restCreateEvaluationMockMvc
            .perform(get(ENTITY_API_URL_ID, createEvaluation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(createEvaluation.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCreateEvaluation() throws Exception {
        // Get the createEvaluation
        restCreateEvaluationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCreateEvaluation() throws Exception {
        // Initialize the database
        createEvaluationRepository.saveAndFlush(createEvaluation);

        int databaseSizeBeforeUpdate = createEvaluationRepository.findAll().size();

        // Update the createEvaluation
        CreateEvaluation updatedCreateEvaluation = createEvaluationRepository.findById(createEvaluation.getId()).get();
        // Disconnect from session so that the updates on updatedCreateEvaluation are not directly saved in db
        em.detach(updatedCreateEvaluation);
        CreateEvaluationDTO createEvaluationDTO = createEvaluationMapper.toDto(updatedCreateEvaluation);

        restCreateEvaluationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createEvaluationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationDTO))
            )
            .andExpect(status().isOk());

        // Validate the CreateEvaluation in the database
        List<CreateEvaluation> createEvaluationList = createEvaluationRepository.findAll();
        assertThat(createEvaluationList).hasSize(databaseSizeBeforeUpdate);
        CreateEvaluation testCreateEvaluation = createEvaluationList.get(createEvaluationList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCreateEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationRepository.findAll().size();
        createEvaluation.setId(count.incrementAndGet());

        // Create the CreateEvaluation
        CreateEvaluationDTO createEvaluationDTO = createEvaluationMapper.toDto(createEvaluation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateEvaluationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createEvaluationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateEvaluation in the database
        List<CreateEvaluation> createEvaluationList = createEvaluationRepository.findAll();
        assertThat(createEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCreateEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationRepository.findAll().size();
        createEvaluation.setId(count.incrementAndGet());

        // Create the CreateEvaluation
        CreateEvaluationDTO createEvaluationDTO = createEvaluationMapper.toDto(createEvaluation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateEvaluationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateEvaluation in the database
        List<CreateEvaluation> createEvaluationList = createEvaluationRepository.findAll();
        assertThat(createEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCreateEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationRepository.findAll().size();
        createEvaluation.setId(count.incrementAndGet());

        // Create the CreateEvaluation
        CreateEvaluationDTO createEvaluationDTO = createEvaluationMapper.toDto(createEvaluation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateEvaluationMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createEvaluationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateEvaluation in the database
        List<CreateEvaluation> createEvaluationList = createEvaluationRepository.findAll();
        assertThat(createEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCreateEvaluationWithPatch() throws Exception {
        // Initialize the database
        createEvaluationRepository.saveAndFlush(createEvaluation);

        int databaseSizeBeforeUpdate = createEvaluationRepository.findAll().size();

        // Update the createEvaluation using partial update
        CreateEvaluation partialUpdatedCreateEvaluation = new CreateEvaluation();
        partialUpdatedCreateEvaluation.setId(createEvaluation.getId());

        restCreateEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateEvaluation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateEvaluation))
            )
            .andExpect(status().isOk());

        // Validate the CreateEvaluation in the database
        List<CreateEvaluation> createEvaluationList = createEvaluationRepository.findAll();
        assertThat(createEvaluationList).hasSize(databaseSizeBeforeUpdate);
        CreateEvaluation testCreateEvaluation = createEvaluationList.get(createEvaluationList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCreateEvaluationWithPatch() throws Exception {
        // Initialize the database
        createEvaluationRepository.saveAndFlush(createEvaluation);

        int databaseSizeBeforeUpdate = createEvaluationRepository.findAll().size();

        // Update the createEvaluation using partial update
        CreateEvaluation partialUpdatedCreateEvaluation = new CreateEvaluation();
        partialUpdatedCreateEvaluation.setId(createEvaluation.getId());

        restCreateEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateEvaluation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateEvaluation))
            )
            .andExpect(status().isOk());

        // Validate the CreateEvaluation in the database
        List<CreateEvaluation> createEvaluationList = createEvaluationRepository.findAll();
        assertThat(createEvaluationList).hasSize(databaseSizeBeforeUpdate);
        CreateEvaluation testCreateEvaluation = createEvaluationList.get(createEvaluationList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCreateEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationRepository.findAll().size();
        createEvaluation.setId(count.incrementAndGet());

        // Create the CreateEvaluation
        CreateEvaluationDTO createEvaluationDTO = createEvaluationMapper.toDto(createEvaluation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, createEvaluationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateEvaluation in the database
        List<CreateEvaluation> createEvaluationList = createEvaluationRepository.findAll();
        assertThat(createEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCreateEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationRepository.findAll().size();
        createEvaluation.setId(count.incrementAndGet());

        // Create the CreateEvaluation
        CreateEvaluationDTO createEvaluationDTO = createEvaluationMapper.toDto(createEvaluation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateEvaluation in the database
        List<CreateEvaluation> createEvaluationList = createEvaluationRepository.findAll();
        assertThat(createEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCreateEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationRepository.findAll().size();
        createEvaluation.setId(count.incrementAndGet());

        // Create the CreateEvaluation
        CreateEvaluationDTO createEvaluationDTO = createEvaluationMapper.toDto(createEvaluation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateEvaluation in the database
        List<CreateEvaluation> createEvaluationList = createEvaluationRepository.findAll();
        assertThat(createEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCreateEvaluation() throws Exception {
        // Initialize the database
        createEvaluationRepository.saveAndFlush(createEvaluation);

        int databaseSizeBeforeDelete = createEvaluationRepository.findAll().size();

        // Delete the createEvaluation
        restCreateEvaluationMockMvc
            .perform(delete(ENTITY_API_URL_ID, createEvaluation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CreateEvaluation> createEvaluationList = createEvaluationRepository.findAll();
        assertThat(createEvaluationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
