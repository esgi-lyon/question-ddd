package org.contextmapper.generated.statcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.statcontext.IntegrationTest;
import org.contextmapper.generated.statcontext.domain.TagStatsViewed;
import org.contextmapper.generated.statcontext.repository.TagStatsViewedRepository;
import org.contextmapper.generated.statcontext.service.dto.TagStatsViewedDTO;
import org.contextmapper.generated.statcontext.service.mapper.TagStatsViewedMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TagStatsViewedResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TagStatsViewedResourceIT {

    private static final String ENTITY_API_URL = "/api/tag-stats-vieweds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagStatsViewedRepository tagStatsViewedRepository;

    @Autowired
    private TagStatsViewedMapper tagStatsViewedMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagStatsViewedMockMvc;

    private TagStatsViewed tagStatsViewed;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagStatsViewed createEntity(EntityManager em) {
        TagStatsViewed tagStatsViewed = new TagStatsViewed();
        return tagStatsViewed;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagStatsViewed createUpdatedEntity(EntityManager em) {
        TagStatsViewed tagStatsViewed = new TagStatsViewed();
        return tagStatsViewed;
    }

    @BeforeEach
    public void initTest() {
        tagStatsViewed = createEntity(em);
    }

    @Test
    @Transactional
    void createTagStatsViewed() throws Exception {
        int databaseSizeBeforeCreate = tagStatsViewedRepository.findAll().size();
        // Create the TagStatsViewed
        TagStatsViewedDTO tagStatsViewedDTO = tagStatsViewedMapper.toDto(tagStatsViewed);
        restTagStatsViewedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagStatsViewedDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TagStatsViewed in the database
        List<TagStatsViewed> tagStatsViewedList = tagStatsViewedRepository.findAll();
        assertThat(tagStatsViewedList).hasSize(databaseSizeBeforeCreate + 1);
        TagStatsViewed testTagStatsViewed = tagStatsViewedList.get(tagStatsViewedList.size() - 1);
    }

    @Test
    @Transactional
    void createTagStatsViewedWithExistingId() throws Exception {
        // Create the TagStatsViewed with an existing ID
        tagStatsViewed.setId(1L);
        TagStatsViewedDTO tagStatsViewedDTO = tagStatsViewedMapper.toDto(tagStatsViewed);

        int databaseSizeBeforeCreate = tagStatsViewedRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTagStatsViewedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagStatsViewed in the database
        List<TagStatsViewed> tagStatsViewedList = tagStatsViewedRepository.findAll();
        assertThat(tagStatsViewedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTagStatsVieweds() throws Exception {
        // Initialize the database
        tagStatsViewedRepository.saveAndFlush(tagStatsViewed);

        // Get all the tagStatsViewedList
        restTagStatsViewedMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagStatsViewed.getId().intValue())));
    }

    @Test
    @Transactional
    void getTagStatsViewed() throws Exception {
        // Initialize the database
        tagStatsViewedRepository.saveAndFlush(tagStatsViewed);

        // Get the tagStatsViewed
        restTagStatsViewedMockMvc
            .perform(get(ENTITY_API_URL_ID, tagStatsViewed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tagStatsViewed.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTagStatsViewed() throws Exception {
        // Get the tagStatsViewed
        restTagStatsViewedMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTagStatsViewed() throws Exception {
        // Initialize the database
        tagStatsViewedRepository.saveAndFlush(tagStatsViewed);

        int databaseSizeBeforeUpdate = tagStatsViewedRepository.findAll().size();

        // Update the tagStatsViewed
        TagStatsViewed updatedTagStatsViewed = tagStatsViewedRepository.findById(tagStatsViewed.getId()).get();
        // Disconnect from session so that the updates on updatedTagStatsViewed are not directly saved in db
        em.detach(updatedTagStatsViewed);
        TagStatsViewedDTO tagStatsViewedDTO = tagStatsViewedMapper.toDto(updatedTagStatsViewed);

        restTagStatsViewedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tagStatsViewedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagStatsViewedDTO))
            )
            .andExpect(status().isOk());

        // Validate the TagStatsViewed in the database
        List<TagStatsViewed> tagStatsViewedList = tagStatsViewedRepository.findAll();
        assertThat(tagStatsViewedList).hasSize(databaseSizeBeforeUpdate);
        TagStatsViewed testTagStatsViewed = tagStatsViewedList.get(tagStatsViewedList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingTagStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = tagStatsViewedRepository.findAll().size();
        tagStatsViewed.setId(count.incrementAndGet());

        // Create the TagStatsViewed
        TagStatsViewedDTO tagStatsViewedDTO = tagStatsViewedMapper.toDto(tagStatsViewed);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagStatsViewedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tagStatsViewedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagStatsViewed in the database
        List<TagStatsViewed> tagStatsViewedList = tagStatsViewedRepository.findAll();
        assertThat(tagStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTagStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = tagStatsViewedRepository.findAll().size();
        tagStatsViewed.setId(count.incrementAndGet());

        // Create the TagStatsViewed
        TagStatsViewedDTO tagStatsViewedDTO = tagStatsViewedMapper.toDto(tagStatsViewed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagStatsViewedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagStatsViewed in the database
        List<TagStatsViewed> tagStatsViewedList = tagStatsViewedRepository.findAll();
        assertThat(tagStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTagStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = tagStatsViewedRepository.findAll().size();
        tagStatsViewed.setId(count.incrementAndGet());

        // Create the TagStatsViewed
        TagStatsViewedDTO tagStatsViewedDTO = tagStatsViewedMapper.toDto(tagStatsViewed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagStatsViewedMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagStatsViewedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TagStatsViewed in the database
        List<TagStatsViewed> tagStatsViewedList = tagStatsViewedRepository.findAll();
        assertThat(tagStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTagStatsViewedWithPatch() throws Exception {
        // Initialize the database
        tagStatsViewedRepository.saveAndFlush(tagStatsViewed);

        int databaseSizeBeforeUpdate = tagStatsViewedRepository.findAll().size();

        // Update the tagStatsViewed using partial update
        TagStatsViewed partialUpdatedTagStatsViewed = new TagStatsViewed();
        partialUpdatedTagStatsViewed.setId(tagStatsViewed.getId());

        restTagStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTagStatsViewed.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTagStatsViewed))
            )
            .andExpect(status().isOk());

        // Validate the TagStatsViewed in the database
        List<TagStatsViewed> tagStatsViewedList = tagStatsViewedRepository.findAll();
        assertThat(tagStatsViewedList).hasSize(databaseSizeBeforeUpdate);
        TagStatsViewed testTagStatsViewed = tagStatsViewedList.get(tagStatsViewedList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateTagStatsViewedWithPatch() throws Exception {
        // Initialize the database
        tagStatsViewedRepository.saveAndFlush(tagStatsViewed);

        int databaseSizeBeforeUpdate = tagStatsViewedRepository.findAll().size();

        // Update the tagStatsViewed using partial update
        TagStatsViewed partialUpdatedTagStatsViewed = new TagStatsViewed();
        partialUpdatedTagStatsViewed.setId(tagStatsViewed.getId());

        restTagStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTagStatsViewed.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTagStatsViewed))
            )
            .andExpect(status().isOk());

        // Validate the TagStatsViewed in the database
        List<TagStatsViewed> tagStatsViewedList = tagStatsViewedRepository.findAll();
        assertThat(tagStatsViewedList).hasSize(databaseSizeBeforeUpdate);
        TagStatsViewed testTagStatsViewed = tagStatsViewedList.get(tagStatsViewedList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingTagStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = tagStatsViewedRepository.findAll().size();
        tagStatsViewed.setId(count.incrementAndGet());

        // Create the TagStatsViewed
        TagStatsViewedDTO tagStatsViewedDTO = tagStatsViewedMapper.toDto(tagStatsViewed);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tagStatsViewedDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagStatsViewed in the database
        List<TagStatsViewed> tagStatsViewedList = tagStatsViewedRepository.findAll();
        assertThat(tagStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTagStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = tagStatsViewedRepository.findAll().size();
        tagStatsViewed.setId(count.incrementAndGet());

        // Create the TagStatsViewed
        TagStatsViewedDTO tagStatsViewedDTO = tagStatsViewedMapper.toDto(tagStatsViewed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagStatsViewed in the database
        List<TagStatsViewed> tagStatsViewedList = tagStatsViewedRepository.findAll();
        assertThat(tagStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTagStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = tagStatsViewedRepository.findAll().size();
        tagStatsViewed.setId(count.incrementAndGet());

        // Create the TagStatsViewed
        TagStatsViewedDTO tagStatsViewedDTO = tagStatsViewedMapper.toDto(tagStatsViewed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagStatsViewedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TagStatsViewed in the database
        List<TagStatsViewed> tagStatsViewedList = tagStatsViewedRepository.findAll();
        assertThat(tagStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTagStatsViewed() throws Exception {
        // Initialize the database
        tagStatsViewedRepository.saveAndFlush(tagStatsViewed);

        int databaseSizeBeforeDelete = tagStatsViewedRepository.findAll().size();

        // Delete the tagStatsViewed
        restTagStatsViewedMockMvc
            .perform(delete(ENTITY_API_URL_ID, tagStatsViewed.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TagStatsViewed> tagStatsViewedList = tagStatsViewedRepository.findAll();
        assertThat(tagStatsViewedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
