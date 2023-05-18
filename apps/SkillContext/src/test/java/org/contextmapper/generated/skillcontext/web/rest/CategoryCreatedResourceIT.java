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
import org.contextmapper.generated.skillcontext.domain.CategoryCreated;
import org.contextmapper.generated.skillcontext.repository.CategoryCreatedRepository;
import org.contextmapper.generated.skillcontext.service.dto.CategoryCreatedDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CategoryCreatedMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CategoryCreatedResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CategoryCreatedResourceIT {

    private static final String ENTITY_API_URL = "/api/category-createds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CategoryCreatedRepository categoryCreatedRepository;

    @Autowired
    private CategoryCreatedMapper categoryCreatedMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategoryCreatedMockMvc;

    private CategoryCreated categoryCreated;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryCreated createEntity(EntityManager em) {
        CategoryCreated categoryCreated = new CategoryCreated();
        return categoryCreated;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryCreated createUpdatedEntity(EntityManager em) {
        CategoryCreated categoryCreated = new CategoryCreated();
        return categoryCreated;
    }

    @BeforeEach
    public void initTest() {
        categoryCreated = createEntity(em);
    }

    @Test
    @Transactional
    void createCategoryCreated() throws Exception {
        int databaseSizeBeforeCreate = categoryCreatedRepository.findAll().size();
        // Create the CategoryCreated
        CategoryCreatedDTO categoryCreatedDTO = categoryCreatedMapper.toDto(categoryCreated);
        restCategoryCreatedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(categoryCreatedDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CategoryCreated in the database
        List<CategoryCreated> categoryCreatedList = categoryCreatedRepository.findAll();
        assertThat(categoryCreatedList).hasSize(databaseSizeBeforeCreate + 1);
        CategoryCreated testCategoryCreated = categoryCreatedList.get(categoryCreatedList.size() - 1);
    }

    @Test
    @Transactional
    void createCategoryCreatedWithExistingId() throws Exception {
        // Create the CategoryCreated with an existing ID
        categoryCreated.setId(1L);
        CategoryCreatedDTO categoryCreatedDTO = categoryCreatedMapper.toDto(categoryCreated);

        int databaseSizeBeforeCreate = categoryCreatedRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoryCreatedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(categoryCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategoryCreated in the database
        List<CategoryCreated> categoryCreatedList = categoryCreatedRepository.findAll();
        assertThat(categoryCreatedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCategoryCreateds() throws Exception {
        // Initialize the database
        categoryCreatedRepository.saveAndFlush(categoryCreated);

        // Get all the categoryCreatedList
        restCategoryCreatedMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoryCreated.getId().intValue())));
    }

    @Test
    @Transactional
    void getCategoryCreated() throws Exception {
        // Initialize the database
        categoryCreatedRepository.saveAndFlush(categoryCreated);

        // Get the categoryCreated
        restCategoryCreatedMockMvc
            .perform(get(ENTITY_API_URL_ID, categoryCreated.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categoryCreated.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCategoryCreated() throws Exception {
        // Get the categoryCreated
        restCategoryCreatedMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCategoryCreated() throws Exception {
        // Initialize the database
        categoryCreatedRepository.saveAndFlush(categoryCreated);

        int databaseSizeBeforeUpdate = categoryCreatedRepository.findAll().size();

        // Update the categoryCreated
        CategoryCreated updatedCategoryCreated = categoryCreatedRepository.findById(categoryCreated.getId()).get();
        // Disconnect from session so that the updates on updatedCategoryCreated are not directly saved in db
        em.detach(updatedCategoryCreated);
        CategoryCreatedDTO categoryCreatedDTO = categoryCreatedMapper.toDto(updatedCategoryCreated);

        restCategoryCreatedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, categoryCreatedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(categoryCreatedDTO))
            )
            .andExpect(status().isOk());

        // Validate the CategoryCreated in the database
        List<CategoryCreated> categoryCreatedList = categoryCreatedRepository.findAll();
        assertThat(categoryCreatedList).hasSize(databaseSizeBeforeUpdate);
        CategoryCreated testCategoryCreated = categoryCreatedList.get(categoryCreatedList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCategoryCreated() throws Exception {
        int databaseSizeBeforeUpdate = categoryCreatedRepository.findAll().size();
        categoryCreated.setId(count.incrementAndGet());

        // Create the CategoryCreated
        CategoryCreatedDTO categoryCreatedDTO = categoryCreatedMapper.toDto(categoryCreated);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoryCreatedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, categoryCreatedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(categoryCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategoryCreated in the database
        List<CategoryCreated> categoryCreatedList = categoryCreatedRepository.findAll();
        assertThat(categoryCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCategoryCreated() throws Exception {
        int databaseSizeBeforeUpdate = categoryCreatedRepository.findAll().size();
        categoryCreated.setId(count.incrementAndGet());

        // Create the CategoryCreated
        CategoryCreatedDTO categoryCreatedDTO = categoryCreatedMapper.toDto(categoryCreated);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCategoryCreatedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(categoryCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategoryCreated in the database
        List<CategoryCreated> categoryCreatedList = categoryCreatedRepository.findAll();
        assertThat(categoryCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCategoryCreated() throws Exception {
        int databaseSizeBeforeUpdate = categoryCreatedRepository.findAll().size();
        categoryCreated.setId(count.incrementAndGet());

        // Create the CategoryCreated
        CategoryCreatedDTO categoryCreatedDTO = categoryCreatedMapper.toDto(categoryCreated);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCategoryCreatedMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(categoryCreatedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CategoryCreated in the database
        List<CategoryCreated> categoryCreatedList = categoryCreatedRepository.findAll();
        assertThat(categoryCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCategoryCreatedWithPatch() throws Exception {
        // Initialize the database
        categoryCreatedRepository.saveAndFlush(categoryCreated);

        int databaseSizeBeforeUpdate = categoryCreatedRepository.findAll().size();

        // Update the categoryCreated using partial update
        CategoryCreated partialUpdatedCategoryCreated = new CategoryCreated();
        partialUpdatedCategoryCreated.setId(categoryCreated.getId());

        restCategoryCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCategoryCreated.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCategoryCreated))
            )
            .andExpect(status().isOk());

        // Validate the CategoryCreated in the database
        List<CategoryCreated> categoryCreatedList = categoryCreatedRepository.findAll();
        assertThat(categoryCreatedList).hasSize(databaseSizeBeforeUpdate);
        CategoryCreated testCategoryCreated = categoryCreatedList.get(categoryCreatedList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCategoryCreatedWithPatch() throws Exception {
        // Initialize the database
        categoryCreatedRepository.saveAndFlush(categoryCreated);

        int databaseSizeBeforeUpdate = categoryCreatedRepository.findAll().size();

        // Update the categoryCreated using partial update
        CategoryCreated partialUpdatedCategoryCreated = new CategoryCreated();
        partialUpdatedCategoryCreated.setId(categoryCreated.getId());

        restCategoryCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCategoryCreated.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCategoryCreated))
            )
            .andExpect(status().isOk());

        // Validate the CategoryCreated in the database
        List<CategoryCreated> categoryCreatedList = categoryCreatedRepository.findAll();
        assertThat(categoryCreatedList).hasSize(databaseSizeBeforeUpdate);
        CategoryCreated testCategoryCreated = categoryCreatedList.get(categoryCreatedList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCategoryCreated() throws Exception {
        int databaseSizeBeforeUpdate = categoryCreatedRepository.findAll().size();
        categoryCreated.setId(count.incrementAndGet());

        // Create the CategoryCreated
        CategoryCreatedDTO categoryCreatedDTO = categoryCreatedMapper.toDto(categoryCreated);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoryCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, categoryCreatedDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(categoryCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategoryCreated in the database
        List<CategoryCreated> categoryCreatedList = categoryCreatedRepository.findAll();
        assertThat(categoryCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCategoryCreated() throws Exception {
        int databaseSizeBeforeUpdate = categoryCreatedRepository.findAll().size();
        categoryCreated.setId(count.incrementAndGet());

        // Create the CategoryCreated
        CategoryCreatedDTO categoryCreatedDTO = categoryCreatedMapper.toDto(categoryCreated);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCategoryCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(categoryCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategoryCreated in the database
        List<CategoryCreated> categoryCreatedList = categoryCreatedRepository.findAll();
        assertThat(categoryCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCategoryCreated() throws Exception {
        int databaseSizeBeforeUpdate = categoryCreatedRepository.findAll().size();
        categoryCreated.setId(count.incrementAndGet());

        // Create the CategoryCreated
        CategoryCreatedDTO categoryCreatedDTO = categoryCreatedMapper.toDto(categoryCreated);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCategoryCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(categoryCreatedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CategoryCreated in the database
        List<CategoryCreated> categoryCreatedList = categoryCreatedRepository.findAll();
        assertThat(categoryCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCategoryCreated() throws Exception {
        // Initialize the database
        categoryCreatedRepository.saveAndFlush(categoryCreated);

        int databaseSizeBeforeDelete = categoryCreatedRepository.findAll().size();

        // Delete the categoryCreated
        restCategoryCreatedMockMvc
            .perform(delete(ENTITY_API_URL_ID, categoryCreated.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategoryCreated> categoryCreatedList = categoryCreatedRepository.findAll();
        assertThat(categoryCreatedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
