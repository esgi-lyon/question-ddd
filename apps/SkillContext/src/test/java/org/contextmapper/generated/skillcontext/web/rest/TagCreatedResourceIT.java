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
import org.contextmapper.generated.skillcontext.domain.TagCreated;
import org.contextmapper.generated.skillcontext.repository.TagCreatedRepository;
import org.contextmapper.generated.skillcontext.service.dto.TagCreatedDTO;
import org.contextmapper.generated.skillcontext.service.mapper.TagCreatedMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TagCreatedResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TagCreatedResourceIT {

    private static final String ENTITY_API_URL = "/api/tag-createds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagCreatedRepository tagCreatedRepository;

    @Autowired
    private TagCreatedMapper tagCreatedMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagCreatedMockMvc;

    private TagCreated tagCreated;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagCreated createEntity(EntityManager em) {
        TagCreated tagCreated = new TagCreated();
        return tagCreated;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagCreated createUpdatedEntity(EntityManager em) {
        TagCreated tagCreated = new TagCreated();
        return tagCreated;
    }

    @BeforeEach
    public void initTest() {
        tagCreated = createEntity(em);
    }

    @Test
    @Transactional
    void createTagCreated() throws Exception {
        int databaseSizeBeforeCreate = tagCreatedRepository.findAll().size();
        // Create the TagCreated
        TagCreatedDTO tagCreatedDTO = tagCreatedMapper.toDto(tagCreated);
        restTagCreatedMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagCreatedDTO)))
            .andExpect(status().isCreated());

        // Validate the TagCreated in the database
        List<TagCreated> tagCreatedList = tagCreatedRepository.findAll();
        assertThat(tagCreatedList).hasSize(databaseSizeBeforeCreate + 1);
        TagCreated testTagCreated = tagCreatedList.get(tagCreatedList.size() - 1);
    }

    @Test
    @Transactional
    void createTagCreatedWithExistingId() throws Exception {
        // Create the TagCreated with an existing ID
        tagCreated.setId(1L);
        TagCreatedDTO tagCreatedDTO = tagCreatedMapper.toDto(tagCreated);

        int databaseSizeBeforeCreate = tagCreatedRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTagCreatedMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagCreatedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TagCreated in the database
        List<TagCreated> tagCreatedList = tagCreatedRepository.findAll();
        assertThat(tagCreatedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTagCreateds() throws Exception {
        // Initialize the database
        tagCreatedRepository.saveAndFlush(tagCreated);

        // Get all the tagCreatedList
        restTagCreatedMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagCreated.getId().intValue())));
    }

    @Test
    @Transactional
    void getTagCreated() throws Exception {
        // Initialize the database
        tagCreatedRepository.saveAndFlush(tagCreated);

        // Get the tagCreated
        restTagCreatedMockMvc
            .perform(get(ENTITY_API_URL_ID, tagCreated.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tagCreated.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTagCreated() throws Exception {
        // Get the tagCreated
        restTagCreatedMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTagCreated() throws Exception {
        // Initialize the database
        tagCreatedRepository.saveAndFlush(tagCreated);

        int databaseSizeBeforeUpdate = tagCreatedRepository.findAll().size();

        // Update the tagCreated
        TagCreated updatedTagCreated = tagCreatedRepository.findById(tagCreated.getId()).get();
        // Disconnect from session so that the updates on updatedTagCreated are not directly saved in db
        em.detach(updatedTagCreated);
        TagCreatedDTO tagCreatedDTO = tagCreatedMapper.toDto(updatedTagCreated);

        restTagCreatedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tagCreatedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagCreatedDTO))
            )
            .andExpect(status().isOk());

        // Validate the TagCreated in the database
        List<TagCreated> tagCreatedList = tagCreatedRepository.findAll();
        assertThat(tagCreatedList).hasSize(databaseSizeBeforeUpdate);
        TagCreated testTagCreated = tagCreatedList.get(tagCreatedList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingTagCreated() throws Exception {
        int databaseSizeBeforeUpdate = tagCreatedRepository.findAll().size();
        tagCreated.setId(count.incrementAndGet());

        // Create the TagCreated
        TagCreatedDTO tagCreatedDTO = tagCreatedMapper.toDto(tagCreated);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagCreatedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tagCreatedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagCreated in the database
        List<TagCreated> tagCreatedList = tagCreatedRepository.findAll();
        assertThat(tagCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTagCreated() throws Exception {
        int databaseSizeBeforeUpdate = tagCreatedRepository.findAll().size();
        tagCreated.setId(count.incrementAndGet());

        // Create the TagCreated
        TagCreatedDTO tagCreatedDTO = tagCreatedMapper.toDto(tagCreated);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagCreatedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagCreated in the database
        List<TagCreated> tagCreatedList = tagCreatedRepository.findAll();
        assertThat(tagCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTagCreated() throws Exception {
        int databaseSizeBeforeUpdate = tagCreatedRepository.findAll().size();
        tagCreated.setId(count.incrementAndGet());

        // Create the TagCreated
        TagCreatedDTO tagCreatedDTO = tagCreatedMapper.toDto(tagCreated);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagCreatedMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagCreatedDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TagCreated in the database
        List<TagCreated> tagCreatedList = tagCreatedRepository.findAll();
        assertThat(tagCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTagCreatedWithPatch() throws Exception {
        // Initialize the database
        tagCreatedRepository.saveAndFlush(tagCreated);

        int databaseSizeBeforeUpdate = tagCreatedRepository.findAll().size();

        // Update the tagCreated using partial update
        TagCreated partialUpdatedTagCreated = new TagCreated();
        partialUpdatedTagCreated.setId(tagCreated.getId());

        restTagCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTagCreated.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTagCreated))
            )
            .andExpect(status().isOk());

        // Validate the TagCreated in the database
        List<TagCreated> tagCreatedList = tagCreatedRepository.findAll();
        assertThat(tagCreatedList).hasSize(databaseSizeBeforeUpdate);
        TagCreated testTagCreated = tagCreatedList.get(tagCreatedList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateTagCreatedWithPatch() throws Exception {
        // Initialize the database
        tagCreatedRepository.saveAndFlush(tagCreated);

        int databaseSizeBeforeUpdate = tagCreatedRepository.findAll().size();

        // Update the tagCreated using partial update
        TagCreated partialUpdatedTagCreated = new TagCreated();
        partialUpdatedTagCreated.setId(tagCreated.getId());

        restTagCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTagCreated.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTagCreated))
            )
            .andExpect(status().isOk());

        // Validate the TagCreated in the database
        List<TagCreated> tagCreatedList = tagCreatedRepository.findAll();
        assertThat(tagCreatedList).hasSize(databaseSizeBeforeUpdate);
        TagCreated testTagCreated = tagCreatedList.get(tagCreatedList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingTagCreated() throws Exception {
        int databaseSizeBeforeUpdate = tagCreatedRepository.findAll().size();
        tagCreated.setId(count.incrementAndGet());

        // Create the TagCreated
        TagCreatedDTO tagCreatedDTO = tagCreatedMapper.toDto(tagCreated);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tagCreatedDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagCreated in the database
        List<TagCreated> tagCreatedList = tagCreatedRepository.findAll();
        assertThat(tagCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTagCreated() throws Exception {
        int databaseSizeBeforeUpdate = tagCreatedRepository.findAll().size();
        tagCreated.setId(count.incrementAndGet());

        // Create the TagCreated
        TagCreatedDTO tagCreatedDTO = tagCreatedMapper.toDto(tagCreated);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagCreatedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagCreated in the database
        List<TagCreated> tagCreatedList = tagCreatedRepository.findAll();
        assertThat(tagCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTagCreated() throws Exception {
        int databaseSizeBeforeUpdate = tagCreatedRepository.findAll().size();
        tagCreated.setId(count.incrementAndGet());

        // Create the TagCreated
        TagCreatedDTO tagCreatedDTO = tagCreatedMapper.toDto(tagCreated);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagCreatedMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tagCreatedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TagCreated in the database
        List<TagCreated> tagCreatedList = tagCreatedRepository.findAll();
        assertThat(tagCreatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTagCreated() throws Exception {
        // Initialize the database
        tagCreatedRepository.saveAndFlush(tagCreated);

        int databaseSizeBeforeDelete = tagCreatedRepository.findAll().size();

        // Delete the tagCreated
        restTagCreatedMockMvc
            .perform(delete(ENTITY_API_URL_ID, tagCreated.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TagCreated> tagCreatedList = tagCreatedRepository.findAll();
        assertThat(tagCreatedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
