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
import org.contextmapper.generated.skillcontext.domain.CategoryId;
import org.contextmapper.generated.skillcontext.repository.CategoryIdRepository;
import org.contextmapper.generated.skillcontext.service.dto.CategoryIdDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CategoryIdMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CategoryIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CategoryIdResourceIT {

    private static final Integer DEFAULT_CATEGORY_ID = 1;
    private static final Integer UPDATED_CATEGORY_ID = 2;

    private static final String ENTITY_API_URL = "/api/category-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CategoryIdRepository categoryIdRepository;

    @Autowired
    private CategoryIdMapper categoryIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategoryIdMockMvc;

    private CategoryId categoryId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryId createEntity(EntityManager em) {
        CategoryId categoryId = new CategoryId().categoryId(DEFAULT_CATEGORY_ID);
        return categoryId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryId createUpdatedEntity(EntityManager em) {
        CategoryId categoryId = new CategoryId().categoryId(UPDATED_CATEGORY_ID);
        return categoryId;
    }

    @BeforeEach
    public void initTest() {
        categoryId = createEntity(em);
    }

    @Test
    @Transactional
    void createCategoryId() throws Exception {
        int databaseSizeBeforeCreate = categoryIdRepository.findAll().size();
        // Create the CategoryId
        CategoryIdDTO categoryIdDTO = categoryIdMapper.toDto(categoryId);
        restCategoryIdMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(categoryIdDTO)))
            .andExpect(status().isCreated());

        // Validate the CategoryId in the database
        List<CategoryId> categoryIdList = categoryIdRepository.findAll();
        assertThat(categoryIdList).hasSize(databaseSizeBeforeCreate + 1);
        CategoryId testCategoryId = categoryIdList.get(categoryIdList.size() - 1);
        assertThat(testCategoryId.getCategoryId()).isEqualTo(DEFAULT_CATEGORY_ID);
    }

    @Test
    @Transactional
    void createCategoryIdWithExistingId() throws Exception {
        // Create the CategoryId with an existing ID
        categoryId.setId(1L);
        CategoryIdDTO categoryIdDTO = categoryIdMapper.toDto(categoryId);

        int databaseSizeBeforeCreate = categoryIdRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoryIdMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(categoryIdDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategoryId in the database
        List<CategoryId> categoryIdList = categoryIdRepository.findAll();
        assertThat(categoryIdList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCategoryIds() throws Exception {
        // Initialize the database
        categoryIdRepository.saveAndFlush(categoryId);

        // Get all the categoryIdList
        restCategoryIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoryId.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryId").value(hasItem(DEFAULT_CATEGORY_ID)));
    }

    @Test
    @Transactional
    void getCategoryId() throws Exception {
        // Initialize the database
        categoryIdRepository.saveAndFlush(categoryId);

        // Get the categoryId
        restCategoryIdMockMvc
            .perform(get(ENTITY_API_URL_ID, categoryId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categoryId.getId().intValue()))
            .andExpect(jsonPath("$.categoryId").value(DEFAULT_CATEGORY_ID));
    }

    @Test
    @Transactional
    void getNonExistingCategoryId() throws Exception {
        // Get the categoryId
        restCategoryIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCategoryId() throws Exception {
        // Initialize the database
        categoryIdRepository.saveAndFlush(categoryId);

        int databaseSizeBeforeUpdate = categoryIdRepository.findAll().size();

        // Update the categoryId
        CategoryId updatedCategoryId = categoryIdRepository.findById(categoryId.getId()).get();
        // Disconnect from session so that the updates on updatedCategoryId are not directly saved in db
        em.detach(updatedCategoryId);
        updatedCategoryId.categoryId(UPDATED_CATEGORY_ID);
        CategoryIdDTO categoryIdDTO = categoryIdMapper.toDto(updatedCategoryId);

        restCategoryIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, categoryIdDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(categoryIdDTO))
            )
            .andExpect(status().isOk());

        // Validate the CategoryId in the database
        List<CategoryId> categoryIdList = categoryIdRepository.findAll();
        assertThat(categoryIdList).hasSize(databaseSizeBeforeUpdate);
        CategoryId testCategoryId = categoryIdList.get(categoryIdList.size() - 1);
        assertThat(testCategoryId.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);
    }

    @Test
    @Transactional
    void putNonExistingCategoryId() throws Exception {
        int databaseSizeBeforeUpdate = categoryIdRepository.findAll().size();
        categoryId.setId(count.incrementAndGet());

        // Create the CategoryId
        CategoryIdDTO categoryIdDTO = categoryIdMapper.toDto(categoryId);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoryIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, categoryIdDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(categoryIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategoryId in the database
        List<CategoryId> categoryIdList = categoryIdRepository.findAll();
        assertThat(categoryIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCategoryId() throws Exception {
        int databaseSizeBeforeUpdate = categoryIdRepository.findAll().size();
        categoryId.setId(count.incrementAndGet());

        // Create the CategoryId
        CategoryIdDTO categoryIdDTO = categoryIdMapper.toDto(categoryId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCategoryIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(categoryIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategoryId in the database
        List<CategoryId> categoryIdList = categoryIdRepository.findAll();
        assertThat(categoryIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCategoryId() throws Exception {
        int databaseSizeBeforeUpdate = categoryIdRepository.findAll().size();
        categoryId.setId(count.incrementAndGet());

        // Create the CategoryId
        CategoryIdDTO categoryIdDTO = categoryIdMapper.toDto(categoryId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCategoryIdMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(categoryIdDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CategoryId in the database
        List<CategoryId> categoryIdList = categoryIdRepository.findAll();
        assertThat(categoryIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCategoryIdWithPatch() throws Exception {
        // Initialize the database
        categoryIdRepository.saveAndFlush(categoryId);

        int databaseSizeBeforeUpdate = categoryIdRepository.findAll().size();

        // Update the categoryId using partial update
        CategoryId partialUpdatedCategoryId = new CategoryId();
        partialUpdatedCategoryId.setId(categoryId.getId());

        partialUpdatedCategoryId.categoryId(UPDATED_CATEGORY_ID);

        restCategoryIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCategoryId.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCategoryId))
            )
            .andExpect(status().isOk());

        // Validate the CategoryId in the database
        List<CategoryId> categoryIdList = categoryIdRepository.findAll();
        assertThat(categoryIdList).hasSize(databaseSizeBeforeUpdate);
        CategoryId testCategoryId = categoryIdList.get(categoryIdList.size() - 1);
        assertThat(testCategoryId.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);
    }

    @Test
    @Transactional
    void fullUpdateCategoryIdWithPatch() throws Exception {
        // Initialize the database
        categoryIdRepository.saveAndFlush(categoryId);

        int databaseSizeBeforeUpdate = categoryIdRepository.findAll().size();

        // Update the categoryId using partial update
        CategoryId partialUpdatedCategoryId = new CategoryId();
        partialUpdatedCategoryId.setId(categoryId.getId());

        partialUpdatedCategoryId.categoryId(UPDATED_CATEGORY_ID);

        restCategoryIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCategoryId.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCategoryId))
            )
            .andExpect(status().isOk());

        // Validate the CategoryId in the database
        List<CategoryId> categoryIdList = categoryIdRepository.findAll();
        assertThat(categoryIdList).hasSize(databaseSizeBeforeUpdate);
        CategoryId testCategoryId = categoryIdList.get(categoryIdList.size() - 1);
        assertThat(testCategoryId.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);
    }

    @Test
    @Transactional
    void patchNonExistingCategoryId() throws Exception {
        int databaseSizeBeforeUpdate = categoryIdRepository.findAll().size();
        categoryId.setId(count.incrementAndGet());

        // Create the CategoryId
        CategoryIdDTO categoryIdDTO = categoryIdMapper.toDto(categoryId);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoryIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, categoryIdDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(categoryIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategoryId in the database
        List<CategoryId> categoryIdList = categoryIdRepository.findAll();
        assertThat(categoryIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCategoryId() throws Exception {
        int databaseSizeBeforeUpdate = categoryIdRepository.findAll().size();
        categoryId.setId(count.incrementAndGet());

        // Create the CategoryId
        CategoryIdDTO categoryIdDTO = categoryIdMapper.toDto(categoryId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCategoryIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(categoryIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategoryId in the database
        List<CategoryId> categoryIdList = categoryIdRepository.findAll();
        assertThat(categoryIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCategoryId() throws Exception {
        int databaseSizeBeforeUpdate = categoryIdRepository.findAll().size();
        categoryId.setId(count.incrementAndGet());

        // Create the CategoryId
        CategoryIdDTO categoryIdDTO = categoryIdMapper.toDto(categoryId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCategoryIdMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(categoryIdDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CategoryId in the database
        List<CategoryId> categoryIdList = categoryIdRepository.findAll();
        assertThat(categoryIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCategoryId() throws Exception {
        // Initialize the database
        categoryIdRepository.saveAndFlush(categoryId);

        int databaseSizeBeforeDelete = categoryIdRepository.findAll().size();

        // Delete the categoryId
        restCategoryIdMockMvc
            .perform(delete(ENTITY_API_URL_ID, categoryId.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategoryId> categoryIdList = categoryIdRepository.findAll();
        assertThat(categoryIdList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
