package org.contextmapper.generated.questioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.questioncontext.IntegrationTest;
import org.contextmapper.generated.questioncontext.domain.ValidateResourceTagLinkage;
import org.contextmapper.generated.questioncontext.repository.ValidateResourceTagLinkageRepository;
import org.contextmapper.generated.questioncontext.service.dto.ValidateResourceTagLinkageDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ValidateResourceTagLinkageMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ValidateResourceTagLinkageResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ValidateResourceTagLinkageResourceIT {

    private static final String ENTITY_API_URL = "/api/validate-resource-tag-linkages";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ValidateResourceTagLinkageRepository validateResourceTagLinkageRepository;

    @Autowired
    private ValidateResourceTagLinkageMapper validateResourceTagLinkageMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restValidateResourceTagLinkageMockMvc;

    private ValidateResourceTagLinkage validateResourceTagLinkage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ValidateResourceTagLinkage createEntity(EntityManager em) {
        ValidateResourceTagLinkage validateResourceTagLinkage = new ValidateResourceTagLinkage();
        return validateResourceTagLinkage;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ValidateResourceTagLinkage createUpdatedEntity(EntityManager em) {
        ValidateResourceTagLinkage validateResourceTagLinkage = new ValidateResourceTagLinkage();
        return validateResourceTagLinkage;
    }

    @BeforeEach
    public void initTest() {
        validateResourceTagLinkage = createEntity(em);
    }

    @Test
    @Transactional
    void createValidateResourceTagLinkage() throws Exception {
        int databaseSizeBeforeCreate = validateResourceTagLinkageRepository.findAll().size();
        // Create the ValidateResourceTagLinkage
        ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO = validateResourceTagLinkageMapper.toDto(validateResourceTagLinkage);
        restValidateResourceTagLinkageMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ValidateResourceTagLinkage in the database
        List<ValidateResourceTagLinkage> validateResourceTagLinkageList = validateResourceTagLinkageRepository.findAll();
        assertThat(validateResourceTagLinkageList).hasSize(databaseSizeBeforeCreate + 1);
        ValidateResourceTagLinkage testValidateResourceTagLinkage = validateResourceTagLinkageList.get(
            validateResourceTagLinkageList.size() - 1
        );
    }

    @Test
    @Transactional
    void createValidateResourceTagLinkageWithExistingId() throws Exception {
        // Create the ValidateResourceTagLinkage with an existing ID
        validateResourceTagLinkage.setId(1L);
        ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO = validateResourceTagLinkageMapper.toDto(validateResourceTagLinkage);

        int databaseSizeBeforeCreate = validateResourceTagLinkageRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restValidateResourceTagLinkageMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateResourceTagLinkage in the database
        List<ValidateResourceTagLinkage> validateResourceTagLinkageList = validateResourceTagLinkageRepository.findAll();
        assertThat(validateResourceTagLinkageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllValidateResourceTagLinkages() throws Exception {
        // Initialize the database
        validateResourceTagLinkageRepository.saveAndFlush(validateResourceTagLinkage);

        // Get all the validateResourceTagLinkageList
        restValidateResourceTagLinkageMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(validateResourceTagLinkage.getId().intValue())));
    }

    @Test
    @Transactional
    void getValidateResourceTagLinkage() throws Exception {
        // Initialize the database
        validateResourceTagLinkageRepository.saveAndFlush(validateResourceTagLinkage);

        // Get the validateResourceTagLinkage
        restValidateResourceTagLinkageMockMvc
            .perform(get(ENTITY_API_URL_ID, validateResourceTagLinkage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(validateResourceTagLinkage.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingValidateResourceTagLinkage() throws Exception {
        // Get the validateResourceTagLinkage
        restValidateResourceTagLinkageMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingValidateResourceTagLinkage() throws Exception {
        // Initialize the database
        validateResourceTagLinkageRepository.saveAndFlush(validateResourceTagLinkage);

        int databaseSizeBeforeUpdate = validateResourceTagLinkageRepository.findAll().size();

        // Update the validateResourceTagLinkage
        ValidateResourceTagLinkage updatedValidateResourceTagLinkage = validateResourceTagLinkageRepository
            .findById(validateResourceTagLinkage.getId())
            .get();
        // Disconnect from session so that the updates on updatedValidateResourceTagLinkage are not directly saved in db
        em.detach(updatedValidateResourceTagLinkage);
        ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO = validateResourceTagLinkageMapper.toDto(
            updatedValidateResourceTagLinkage
        );

        restValidateResourceTagLinkageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, validateResourceTagLinkageDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageDTO))
            )
            .andExpect(status().isOk());

        // Validate the ValidateResourceTagLinkage in the database
        List<ValidateResourceTagLinkage> validateResourceTagLinkageList = validateResourceTagLinkageRepository.findAll();
        assertThat(validateResourceTagLinkageList).hasSize(databaseSizeBeforeUpdate);
        ValidateResourceTagLinkage testValidateResourceTagLinkage = validateResourceTagLinkageList.get(
            validateResourceTagLinkageList.size() - 1
        );
    }

    @Test
    @Transactional
    void putNonExistingValidateResourceTagLinkage() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageRepository.findAll().size();
        validateResourceTagLinkage.setId(count.incrementAndGet());

        // Create the ValidateResourceTagLinkage
        ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO = validateResourceTagLinkageMapper.toDto(validateResourceTagLinkage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restValidateResourceTagLinkageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, validateResourceTagLinkageDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateResourceTagLinkage in the database
        List<ValidateResourceTagLinkage> validateResourceTagLinkageList = validateResourceTagLinkageRepository.findAll();
        assertThat(validateResourceTagLinkageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchValidateResourceTagLinkage() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageRepository.findAll().size();
        validateResourceTagLinkage.setId(count.incrementAndGet());

        // Create the ValidateResourceTagLinkage
        ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO = validateResourceTagLinkageMapper.toDto(validateResourceTagLinkage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restValidateResourceTagLinkageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateResourceTagLinkage in the database
        List<ValidateResourceTagLinkage> validateResourceTagLinkageList = validateResourceTagLinkageRepository.findAll();
        assertThat(validateResourceTagLinkageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamValidateResourceTagLinkage() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageRepository.findAll().size();
        validateResourceTagLinkage.setId(count.incrementAndGet());

        // Create the ValidateResourceTagLinkage
        ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO = validateResourceTagLinkageMapper.toDto(validateResourceTagLinkage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restValidateResourceTagLinkageMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ValidateResourceTagLinkage in the database
        List<ValidateResourceTagLinkage> validateResourceTagLinkageList = validateResourceTagLinkageRepository.findAll();
        assertThat(validateResourceTagLinkageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateValidateResourceTagLinkageWithPatch() throws Exception {
        // Initialize the database
        validateResourceTagLinkageRepository.saveAndFlush(validateResourceTagLinkage);

        int databaseSizeBeforeUpdate = validateResourceTagLinkageRepository.findAll().size();

        // Update the validateResourceTagLinkage using partial update
        ValidateResourceTagLinkage partialUpdatedValidateResourceTagLinkage = new ValidateResourceTagLinkage();
        partialUpdatedValidateResourceTagLinkage.setId(validateResourceTagLinkage.getId());

        restValidateResourceTagLinkageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedValidateResourceTagLinkage.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedValidateResourceTagLinkage))
            )
            .andExpect(status().isOk());

        // Validate the ValidateResourceTagLinkage in the database
        List<ValidateResourceTagLinkage> validateResourceTagLinkageList = validateResourceTagLinkageRepository.findAll();
        assertThat(validateResourceTagLinkageList).hasSize(databaseSizeBeforeUpdate);
        ValidateResourceTagLinkage testValidateResourceTagLinkage = validateResourceTagLinkageList.get(
            validateResourceTagLinkageList.size() - 1
        );
    }

    @Test
    @Transactional
    void fullUpdateValidateResourceTagLinkageWithPatch() throws Exception {
        // Initialize the database
        validateResourceTagLinkageRepository.saveAndFlush(validateResourceTagLinkage);

        int databaseSizeBeforeUpdate = validateResourceTagLinkageRepository.findAll().size();

        // Update the validateResourceTagLinkage using partial update
        ValidateResourceTagLinkage partialUpdatedValidateResourceTagLinkage = new ValidateResourceTagLinkage();
        partialUpdatedValidateResourceTagLinkage.setId(validateResourceTagLinkage.getId());

        restValidateResourceTagLinkageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedValidateResourceTagLinkage.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedValidateResourceTagLinkage))
            )
            .andExpect(status().isOk());

        // Validate the ValidateResourceTagLinkage in the database
        List<ValidateResourceTagLinkage> validateResourceTagLinkageList = validateResourceTagLinkageRepository.findAll();
        assertThat(validateResourceTagLinkageList).hasSize(databaseSizeBeforeUpdate);
        ValidateResourceTagLinkage testValidateResourceTagLinkage = validateResourceTagLinkageList.get(
            validateResourceTagLinkageList.size() - 1
        );
    }

    @Test
    @Transactional
    void patchNonExistingValidateResourceTagLinkage() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageRepository.findAll().size();
        validateResourceTagLinkage.setId(count.incrementAndGet());

        // Create the ValidateResourceTagLinkage
        ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO = validateResourceTagLinkageMapper.toDto(validateResourceTagLinkage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restValidateResourceTagLinkageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, validateResourceTagLinkageDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateResourceTagLinkage in the database
        List<ValidateResourceTagLinkage> validateResourceTagLinkageList = validateResourceTagLinkageRepository.findAll();
        assertThat(validateResourceTagLinkageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchValidateResourceTagLinkage() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageRepository.findAll().size();
        validateResourceTagLinkage.setId(count.incrementAndGet());

        // Create the ValidateResourceTagLinkage
        ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO = validateResourceTagLinkageMapper.toDto(validateResourceTagLinkage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restValidateResourceTagLinkageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateResourceTagLinkage in the database
        List<ValidateResourceTagLinkage> validateResourceTagLinkageList = validateResourceTagLinkageRepository.findAll();
        assertThat(validateResourceTagLinkageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamValidateResourceTagLinkage() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageRepository.findAll().size();
        validateResourceTagLinkage.setId(count.incrementAndGet());

        // Create the ValidateResourceTagLinkage
        ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO = validateResourceTagLinkageMapper.toDto(validateResourceTagLinkage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restValidateResourceTagLinkageMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ValidateResourceTagLinkage in the database
        List<ValidateResourceTagLinkage> validateResourceTagLinkageList = validateResourceTagLinkageRepository.findAll();
        assertThat(validateResourceTagLinkageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteValidateResourceTagLinkage() throws Exception {
        // Initialize the database
        validateResourceTagLinkageRepository.saveAndFlush(validateResourceTagLinkage);

        int databaseSizeBeforeDelete = validateResourceTagLinkageRepository.findAll().size();

        // Delete the validateResourceTagLinkage
        restValidateResourceTagLinkageMockMvc
            .perform(delete(ENTITY_API_URL_ID, validateResourceTagLinkage.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ValidateResourceTagLinkage> validateResourceTagLinkageList = validateResourceTagLinkageRepository.findAll();
        assertThat(validateResourceTagLinkageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
