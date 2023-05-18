package org.contextmapper.generated.skillcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.skillcontext.IntegrationTest;
import org.contextmapper.generated.skillcontext.domain.CreateCategory;
import org.contextmapper.generated.skillcontext.repository.CreateCategoryRepository;
import org.contextmapper.generated.skillcontext.service.dto.CreateCategoryDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CreateCategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreateCategoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreateCategoryResourceIT {

    private static final String ENTITY_API_URL = "/api/create-categories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateCategoryRepository createCategoryRepository;

    @Autowired
    private CreateCategoryMapper createCategoryMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreateCategoryMockMvc;

    private CreateCategory createCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateCategory createEntity(EntityManager em) {
        CreateCategory createCategory = new CreateCategory();
        return createCategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateCategory createUpdatedEntity(EntityManager em) {
        CreateCategory createCategory = new CreateCategory();
        return createCategory;
    }

    @BeforeEach
    public void initTest() {
        createCategory = createEntity(em);
    }

    @Test
    @Transactional
    void createCreateCategory() throws Exception {
        int databaseSizeBeforeCreate = createCategoryRepository.findAll().size();
        // Create the CreateCategory
        CreateCategoryDTO createCategoryDTO = createCategoryMapper.toDto(createCategory);
        restCreateCategoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createCategoryDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CreateCategory in the database
        List<CreateCategory> createCategoryList = createCategoryRepository.findAll();
        assertThat(createCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        CreateCategory testCreateCategory = createCategoryList.get(createCategoryList.size() - 1);
    }

    @Test
    @Transactional
    void createCreateCategoryWithExistingId() throws Exception {
        // Create the CreateCategory with an existing ID
        createCategory.setId(1L);
        CreateCategoryDTO createCategoryDTO = createCategoryMapper.toDto(createCategory);

        int databaseSizeBeforeCreate = createCategoryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreateCategoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateCategory in the database
        List<CreateCategory> createCategoryList = createCategoryRepository.findAll();
        assertThat(createCategoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCreateCategories() throws Exception {
        // Initialize the database
        createCategoryRepository.saveAndFlush(createCategory);

        // Get all the createCategoryList
        restCreateCategoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(createCategory.getId().intValue())));
    }

    @Test
    @Transactional
    void getCreateCategory() throws Exception {
        // Initialize the database
        createCategoryRepository.saveAndFlush(createCategory);

        // Get the createCategory
        restCreateCategoryMockMvc
            .perform(get(ENTITY_API_URL_ID, createCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(createCategory.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCreateCategory() throws Exception {
        // Get the createCategory
        restCreateCategoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCreateCategory() throws Exception {
        // Initialize the database
        createCategoryRepository.saveAndFlush(createCategory);

        int databaseSizeBeforeUpdate = createCategoryRepository.findAll().size();

        // Update the createCategory
        CreateCategory updatedCreateCategory = createCategoryRepository.findById(createCategory.getId()).get();
        // Disconnect from session so that the updates on updatedCreateCategory are not directly saved in db
        em.detach(updatedCreateCategory);
        CreateCategoryDTO createCategoryDTO = createCategoryMapper.toDto(updatedCreateCategory);

        restCreateCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createCategoryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryDTO))
            )
            .andExpect(status().isOk());

        // Validate the CreateCategory in the database
        List<CreateCategory> createCategoryList = createCategoryRepository.findAll();
        assertThat(createCategoryList).hasSize(databaseSizeBeforeUpdate);
        CreateCategory testCreateCategory = createCategoryList.get(createCategoryList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCreateCategory() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryRepository.findAll().size();
        createCategory.setId(count.incrementAndGet());

        // Create the CreateCategory
        CreateCategoryDTO createCategoryDTO = createCategoryMapper.toDto(createCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createCategoryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateCategory in the database
        List<CreateCategory> createCategoryList = createCategoryRepository.findAll();
        assertThat(createCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCreateCategory() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryRepository.findAll().size();
        createCategory.setId(count.incrementAndGet());

        // Create the CreateCategory
        CreateCategoryDTO createCategoryDTO = createCategoryMapper.toDto(createCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateCategory in the database
        List<CreateCategory> createCategoryList = createCategoryRepository.findAll();
        assertThat(createCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCreateCategory() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryRepository.findAll().size();
        createCategory.setId(count.incrementAndGet());

        // Create the CreateCategory
        CreateCategoryDTO createCategoryDTO = createCategoryMapper.toDto(createCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateCategoryMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createCategoryDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateCategory in the database
        List<CreateCategory> createCategoryList = createCategoryRepository.findAll();
        assertThat(createCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCreateCategoryWithPatch() throws Exception {
        // Initialize the database
        createCategoryRepository.saveAndFlush(createCategory);

        int databaseSizeBeforeUpdate = createCategoryRepository.findAll().size();

        // Update the createCategory using partial update
        CreateCategory partialUpdatedCreateCategory = new CreateCategory();
        partialUpdatedCreateCategory.setId(createCategory.getId());

        restCreateCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateCategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateCategory))
            )
            .andExpect(status().isOk());

        // Validate the CreateCategory in the database
        List<CreateCategory> createCategoryList = createCategoryRepository.findAll();
        assertThat(createCategoryList).hasSize(databaseSizeBeforeUpdate);
        CreateCategory testCreateCategory = createCategoryList.get(createCategoryList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCreateCategoryWithPatch() throws Exception {
        // Initialize the database
        createCategoryRepository.saveAndFlush(createCategory);

        int databaseSizeBeforeUpdate = createCategoryRepository.findAll().size();

        // Update the createCategory using partial update
        CreateCategory partialUpdatedCreateCategory = new CreateCategory();
        partialUpdatedCreateCategory.setId(createCategory.getId());

        restCreateCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateCategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateCategory))
            )
            .andExpect(status().isOk());

        // Validate the CreateCategory in the database
        List<CreateCategory> createCategoryList = createCategoryRepository.findAll();
        assertThat(createCategoryList).hasSize(databaseSizeBeforeUpdate);
        CreateCategory testCreateCategory = createCategoryList.get(createCategoryList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCreateCategory() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryRepository.findAll().size();
        createCategory.setId(count.incrementAndGet());

        // Create the CreateCategory
        CreateCategoryDTO createCategoryDTO = createCategoryMapper.toDto(createCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, createCategoryDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateCategory in the database
        List<CreateCategory> createCategoryList = createCategoryRepository.findAll();
        assertThat(createCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCreateCategory() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryRepository.findAll().size();
        createCategory.setId(count.incrementAndGet());

        // Create the CreateCategory
        CreateCategoryDTO createCategoryDTO = createCategoryMapper.toDto(createCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateCategory in the database
        List<CreateCategory> createCategoryList = createCategoryRepository.findAll();
        assertThat(createCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCreateCategory() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryRepository.findAll().size();
        createCategory.setId(count.incrementAndGet());

        // Create the CreateCategory
        CreateCategoryDTO createCategoryDTO = createCategoryMapper.toDto(createCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateCategory in the database
        List<CreateCategory> createCategoryList = createCategoryRepository.findAll();
        assertThat(createCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCreateCategory() throws Exception {
        // Initialize the database
        createCategoryRepository.saveAndFlush(createCategory);

        int databaseSizeBeforeDelete = createCategoryRepository.findAll().size();

        // Delete the createCategory
        restCreateCategoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, createCategory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CreateCategory> createCategoryList = createCategoryRepository.findAll();
        assertThat(createCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
