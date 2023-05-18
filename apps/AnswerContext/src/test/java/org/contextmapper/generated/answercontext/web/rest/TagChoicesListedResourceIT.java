package org.contextmapper.generated.answercontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.answercontext.IntegrationTest;
import org.contextmapper.generated.answercontext.domain.TagChoicesListed;
import org.contextmapper.generated.answercontext.repository.TagChoicesListedRepository;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListedDTO;
import org.contextmapper.generated.answercontext.service.mapper.TagChoicesListedMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TagChoicesListedResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TagChoicesListedResourceIT {

    private static final String ENTITY_API_URL = "/api/tag-choices-listeds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagChoicesListedRepository tagChoicesListedRepository;

    @Autowired
    private TagChoicesListedMapper tagChoicesListedMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagChoicesListedMockMvc;

    private TagChoicesListed tagChoicesListed;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagChoicesListed createEntity(EntityManager em) {
        TagChoicesListed tagChoicesListed = new TagChoicesListed();
        return tagChoicesListed;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagChoicesListed createUpdatedEntity(EntityManager em) {
        TagChoicesListed tagChoicesListed = new TagChoicesListed();
        return tagChoicesListed;
    }

    @BeforeEach
    public void initTest() {
        tagChoicesListed = createEntity(em);
    }

    @Test
    @Transactional
    void createTagChoicesListed() throws Exception {
        int databaseSizeBeforeCreate = tagChoicesListedRepository.findAll().size();
        // Create the TagChoicesListed
        TagChoicesListedDTO tagChoicesListedDTO = tagChoicesListedMapper.toDto(tagChoicesListed);
        restTagChoicesListedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagChoicesListedDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TagChoicesListed in the database
        List<TagChoicesListed> tagChoicesListedList = tagChoicesListedRepository.findAll();
        assertThat(tagChoicesListedList).hasSize(databaseSizeBeforeCreate + 1);
        TagChoicesListed testTagChoicesListed = tagChoicesListedList.get(tagChoicesListedList.size() - 1);
    }

    @Test
    @Transactional
    void createTagChoicesListedWithExistingId() throws Exception {
        // Create the TagChoicesListed with an existing ID
        tagChoicesListed.setId(1L);
        TagChoicesListedDTO tagChoicesListedDTO = tagChoicesListedMapper.toDto(tagChoicesListed);

        int databaseSizeBeforeCreate = tagChoicesListedRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTagChoicesListedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagChoicesListedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesListed in the database
        List<TagChoicesListed> tagChoicesListedList = tagChoicesListedRepository.findAll();
        assertThat(tagChoicesListedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTagChoicesListeds() throws Exception {
        // Initialize the database
        tagChoicesListedRepository.saveAndFlush(tagChoicesListed);

        // Get all the tagChoicesListedList
        restTagChoicesListedMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagChoicesListed.getId().intValue())));
    }

    @Test
    @Transactional
    void getTagChoicesListed() throws Exception {
        // Initialize the database
        tagChoicesListedRepository.saveAndFlush(tagChoicesListed);

        // Get the tagChoicesListed
        restTagChoicesListedMockMvc
            .perform(get(ENTITY_API_URL_ID, tagChoicesListed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tagChoicesListed.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTagChoicesListed() throws Exception {
        // Get the tagChoicesListed
        restTagChoicesListedMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTagChoicesListed() throws Exception {
        // Initialize the database
        tagChoicesListedRepository.saveAndFlush(tagChoicesListed);

        int databaseSizeBeforeUpdate = tagChoicesListedRepository.findAll().size();

        // Update the tagChoicesListed
        TagChoicesListed updatedTagChoicesListed = tagChoicesListedRepository.findById(tagChoicesListed.getId()).get();
        // Disconnect from session so that the updates on updatedTagChoicesListed are not directly saved in db
        em.detach(updatedTagChoicesListed);
        TagChoicesListedDTO tagChoicesListedDTO = tagChoicesListedMapper.toDto(updatedTagChoicesListed);

        restTagChoicesListedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tagChoicesListedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListedDTO))
            )
            .andExpect(status().isOk());

        // Validate the TagChoicesListed in the database
        List<TagChoicesListed> tagChoicesListedList = tagChoicesListedRepository.findAll();
        assertThat(tagChoicesListedList).hasSize(databaseSizeBeforeUpdate);
        TagChoicesListed testTagChoicesListed = tagChoicesListedList.get(tagChoicesListedList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingTagChoicesListed() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListedRepository.findAll().size();
        tagChoicesListed.setId(count.incrementAndGet());

        // Create the TagChoicesListed
        TagChoicesListedDTO tagChoicesListedDTO = tagChoicesListedMapper.toDto(tagChoicesListed);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagChoicesListedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tagChoicesListedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesListed in the database
        List<TagChoicesListed> tagChoicesListedList = tagChoicesListedRepository.findAll();
        assertThat(tagChoicesListedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTagChoicesListed() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListedRepository.findAll().size();
        tagChoicesListed.setId(count.incrementAndGet());

        // Create the TagChoicesListed
        TagChoicesListedDTO tagChoicesListedDTO = tagChoicesListedMapper.toDto(tagChoicesListed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagChoicesListedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesListed in the database
        List<TagChoicesListed> tagChoicesListedList = tagChoicesListedRepository.findAll();
        assertThat(tagChoicesListedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTagChoicesListed() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListedRepository.findAll().size();
        tagChoicesListed.setId(count.incrementAndGet());

        // Create the TagChoicesListed
        TagChoicesListedDTO tagChoicesListedDTO = tagChoicesListedMapper.toDto(tagChoicesListed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagChoicesListedMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagChoicesListedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TagChoicesListed in the database
        List<TagChoicesListed> tagChoicesListedList = tagChoicesListedRepository.findAll();
        assertThat(tagChoicesListedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTagChoicesListedWithPatch() throws Exception {
        // Initialize the database
        tagChoicesListedRepository.saveAndFlush(tagChoicesListed);

        int databaseSizeBeforeUpdate = tagChoicesListedRepository.findAll().size();

        // Update the tagChoicesListed using partial update
        TagChoicesListed partialUpdatedTagChoicesListed = new TagChoicesListed();
        partialUpdatedTagChoicesListed.setId(tagChoicesListed.getId());

        restTagChoicesListedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTagChoicesListed.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTagChoicesListed))
            )
            .andExpect(status().isOk());

        // Validate the TagChoicesListed in the database
        List<TagChoicesListed> tagChoicesListedList = tagChoicesListedRepository.findAll();
        assertThat(tagChoicesListedList).hasSize(databaseSizeBeforeUpdate);
        TagChoicesListed testTagChoicesListed = tagChoicesListedList.get(tagChoicesListedList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateTagChoicesListedWithPatch() throws Exception {
        // Initialize the database
        tagChoicesListedRepository.saveAndFlush(tagChoicesListed);

        int databaseSizeBeforeUpdate = tagChoicesListedRepository.findAll().size();

        // Update the tagChoicesListed using partial update
        TagChoicesListed partialUpdatedTagChoicesListed = new TagChoicesListed();
        partialUpdatedTagChoicesListed.setId(tagChoicesListed.getId());

        restTagChoicesListedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTagChoicesListed.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTagChoicesListed))
            )
            .andExpect(status().isOk());

        // Validate the TagChoicesListed in the database
        List<TagChoicesListed> tagChoicesListedList = tagChoicesListedRepository.findAll();
        assertThat(tagChoicesListedList).hasSize(databaseSizeBeforeUpdate);
        TagChoicesListed testTagChoicesListed = tagChoicesListedList.get(tagChoicesListedList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingTagChoicesListed() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListedRepository.findAll().size();
        tagChoicesListed.setId(count.incrementAndGet());

        // Create the TagChoicesListed
        TagChoicesListedDTO tagChoicesListedDTO = tagChoicesListedMapper.toDto(tagChoicesListed);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagChoicesListedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tagChoicesListedDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesListed in the database
        List<TagChoicesListed> tagChoicesListedList = tagChoicesListedRepository.findAll();
        assertThat(tagChoicesListedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTagChoicesListed() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListedRepository.findAll().size();
        tagChoicesListed.setId(count.incrementAndGet());

        // Create the TagChoicesListed
        TagChoicesListedDTO tagChoicesListedDTO = tagChoicesListedMapper.toDto(tagChoicesListed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagChoicesListedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesListed in the database
        List<TagChoicesListed> tagChoicesListedList = tagChoicesListedRepository.findAll();
        assertThat(tagChoicesListedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTagChoicesListed() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListedRepository.findAll().size();
        tagChoicesListed.setId(count.incrementAndGet());

        // Create the TagChoicesListed
        TagChoicesListedDTO tagChoicesListedDTO = tagChoicesListedMapper.toDto(tagChoicesListed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagChoicesListedMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TagChoicesListed in the database
        List<TagChoicesListed> tagChoicesListedList = tagChoicesListedRepository.findAll();
        assertThat(tagChoicesListedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTagChoicesListed() throws Exception {
        // Initialize the database
        tagChoicesListedRepository.saveAndFlush(tagChoicesListed);

        int databaseSizeBeforeDelete = tagChoicesListedRepository.findAll().size();

        // Delete the tagChoicesListed
        restTagChoicesListedMockMvc
            .perform(delete(ENTITY_API_URL_ID, tagChoicesListed.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TagChoicesListed> tagChoicesListedList = tagChoicesListedRepository.findAll();
        assertThat(tagChoicesListedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
