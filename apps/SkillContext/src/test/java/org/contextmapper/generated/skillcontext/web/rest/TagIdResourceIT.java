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
import org.contextmapper.generated.skillcontext.domain.TagId;
import org.contextmapper.generated.skillcontext.repository.TagIdRepository;
import org.contextmapper.generated.skillcontext.service.dto.TagIdDTO;
import org.contextmapper.generated.skillcontext.service.mapper.TagIdMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TagIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TagIdResourceIT {

    private static final Integer DEFAULT_TAG_ID = 1;
    private static final Integer UPDATED_TAG_ID = 2;

    private static final String ENTITY_API_URL = "/api/tag-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagIdRepository tagIdRepository;

    @Autowired
    private TagIdMapper tagIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagIdMockMvc;

    private TagId tagId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagId createEntity(EntityManager em) {
        TagId tagId = new TagId().tagId(DEFAULT_TAG_ID);
        return tagId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagId createUpdatedEntity(EntityManager em) {
        TagId tagId = new TagId().tagId(UPDATED_TAG_ID);
        return tagId;
    }

    @BeforeEach
    public void initTest() {
        tagId = createEntity(em);
    }

    @Test
    @Transactional
    void createTagId() throws Exception {
        int databaseSizeBeforeCreate = tagIdRepository.findAll().size();
        // Create the TagId
        TagIdDTO tagIdDTO = tagIdMapper.toDto(tagId);
        restTagIdMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagIdDTO)))
            .andExpect(status().isCreated());

        // Validate the TagId in the database
        List<TagId> tagIdList = tagIdRepository.findAll();
        assertThat(tagIdList).hasSize(databaseSizeBeforeCreate + 1);
        TagId testTagId = tagIdList.get(tagIdList.size() - 1);
        assertThat(testTagId.getTagId()).isEqualTo(DEFAULT_TAG_ID);
    }

    @Test
    @Transactional
    void createTagIdWithExistingId() throws Exception {
        // Create the TagId with an existing ID
        tagId.setId(1L);
        TagIdDTO tagIdDTO = tagIdMapper.toDto(tagId);

        int databaseSizeBeforeCreate = tagIdRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTagIdMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagIdDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TagId in the database
        List<TagId> tagIdList = tagIdRepository.findAll();
        assertThat(tagIdList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTagIds() throws Exception {
        // Initialize the database
        tagIdRepository.saveAndFlush(tagId);

        // Get all the tagIdList
        restTagIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagId.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID)));
    }

    @Test
    @Transactional
    void getTagId() throws Exception {
        // Initialize the database
        tagIdRepository.saveAndFlush(tagId);

        // Get the tagId
        restTagIdMockMvc
            .perform(get(ENTITY_API_URL_ID, tagId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tagId.getId().intValue()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID));
    }

    @Test
    @Transactional
    void getNonExistingTagId() throws Exception {
        // Get the tagId
        restTagIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTagId() throws Exception {
        // Initialize the database
        tagIdRepository.saveAndFlush(tagId);

        int databaseSizeBeforeUpdate = tagIdRepository.findAll().size();

        // Update the tagId
        TagId updatedTagId = tagIdRepository.findById(tagId.getId()).get();
        // Disconnect from session so that the updates on updatedTagId are not directly saved in db
        em.detach(updatedTagId);
        updatedTagId.tagId(UPDATED_TAG_ID);
        TagIdDTO tagIdDTO = tagIdMapper.toDto(updatedTagId);

        restTagIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tagIdDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagIdDTO))
            )
            .andExpect(status().isOk());

        // Validate the TagId in the database
        List<TagId> tagIdList = tagIdRepository.findAll();
        assertThat(tagIdList).hasSize(databaseSizeBeforeUpdate);
        TagId testTagId = tagIdList.get(tagIdList.size() - 1);
        assertThat(testTagId.getTagId()).isEqualTo(UPDATED_TAG_ID);
    }

    @Test
    @Transactional
    void putNonExistingTagId() throws Exception {
        int databaseSizeBeforeUpdate = tagIdRepository.findAll().size();
        tagId.setId(count.incrementAndGet());

        // Create the TagId
        TagIdDTO tagIdDTO = tagIdMapper.toDto(tagId);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tagIdDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagId in the database
        List<TagId> tagIdList = tagIdRepository.findAll();
        assertThat(tagIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTagId() throws Exception {
        int databaseSizeBeforeUpdate = tagIdRepository.findAll().size();
        tagId.setId(count.incrementAndGet());

        // Create the TagId
        TagIdDTO tagIdDTO = tagIdMapper.toDto(tagId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagId in the database
        List<TagId> tagIdList = tagIdRepository.findAll();
        assertThat(tagIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTagId() throws Exception {
        int databaseSizeBeforeUpdate = tagIdRepository.findAll().size();
        tagId.setId(count.incrementAndGet());

        // Create the TagId
        TagIdDTO tagIdDTO = tagIdMapper.toDto(tagId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagIdMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagIdDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TagId in the database
        List<TagId> tagIdList = tagIdRepository.findAll();
        assertThat(tagIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTagIdWithPatch() throws Exception {
        // Initialize the database
        tagIdRepository.saveAndFlush(tagId);

        int databaseSizeBeforeUpdate = tagIdRepository.findAll().size();

        // Update the tagId using partial update
        TagId partialUpdatedTagId = new TagId();
        partialUpdatedTagId.setId(tagId.getId());

        partialUpdatedTagId.tagId(UPDATED_TAG_ID);

        restTagIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTagId.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTagId))
            )
            .andExpect(status().isOk());

        // Validate the TagId in the database
        List<TagId> tagIdList = tagIdRepository.findAll();
        assertThat(tagIdList).hasSize(databaseSizeBeforeUpdate);
        TagId testTagId = tagIdList.get(tagIdList.size() - 1);
        assertThat(testTagId.getTagId()).isEqualTo(UPDATED_TAG_ID);
    }

    @Test
    @Transactional
    void fullUpdateTagIdWithPatch() throws Exception {
        // Initialize the database
        tagIdRepository.saveAndFlush(tagId);

        int databaseSizeBeforeUpdate = tagIdRepository.findAll().size();

        // Update the tagId using partial update
        TagId partialUpdatedTagId = new TagId();
        partialUpdatedTagId.setId(tagId.getId());

        partialUpdatedTagId.tagId(UPDATED_TAG_ID);

        restTagIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTagId.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTagId))
            )
            .andExpect(status().isOk());

        // Validate the TagId in the database
        List<TagId> tagIdList = tagIdRepository.findAll();
        assertThat(tagIdList).hasSize(databaseSizeBeforeUpdate);
        TagId testTagId = tagIdList.get(tagIdList.size() - 1);
        assertThat(testTagId.getTagId()).isEqualTo(UPDATED_TAG_ID);
    }

    @Test
    @Transactional
    void patchNonExistingTagId() throws Exception {
        int databaseSizeBeforeUpdate = tagIdRepository.findAll().size();
        tagId.setId(count.incrementAndGet());

        // Create the TagId
        TagIdDTO tagIdDTO = tagIdMapper.toDto(tagId);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tagIdDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagId in the database
        List<TagId> tagIdList = tagIdRepository.findAll();
        assertThat(tagIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTagId() throws Exception {
        int databaseSizeBeforeUpdate = tagIdRepository.findAll().size();
        tagId.setId(count.incrementAndGet());

        // Create the TagId
        TagIdDTO tagIdDTO = tagIdMapper.toDto(tagId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagId in the database
        List<TagId> tagIdList = tagIdRepository.findAll();
        assertThat(tagIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTagId() throws Exception {
        int databaseSizeBeforeUpdate = tagIdRepository.findAll().size();
        tagId.setId(count.incrementAndGet());

        // Create the TagId
        TagIdDTO tagIdDTO = tagIdMapper.toDto(tagId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagIdMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tagIdDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TagId in the database
        List<TagId> tagIdList = tagIdRepository.findAll();
        assertThat(tagIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTagId() throws Exception {
        // Initialize the database
        tagIdRepository.saveAndFlush(tagId);

        int databaseSizeBeforeDelete = tagIdRepository.findAll().size();

        // Delete the tagId
        restTagIdMockMvc
            .perform(delete(ENTITY_API_URL_ID, tagId.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TagId> tagIdList = tagIdRepository.findAll();
        assertThat(tagIdList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
