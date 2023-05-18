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
import org.contextmapper.generated.answercontext.domain.TagChoicesList;
import org.contextmapper.generated.answercontext.repository.TagChoicesListRepository;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListDTO;
import org.contextmapper.generated.answercontext.service.mapper.TagChoicesListMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TagChoicesListResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TagChoicesListResourceIT {

    private static final String ENTITY_API_URL = "/api/tag-choices-lists";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagChoicesListRepository tagChoicesListRepository;

    @Autowired
    private TagChoicesListMapper tagChoicesListMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagChoicesListMockMvc;

    private TagChoicesList tagChoicesList;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagChoicesList createEntity(EntityManager em) {
        TagChoicesList tagChoicesList = new TagChoicesList();
        return tagChoicesList;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagChoicesList createUpdatedEntity(EntityManager em) {
        TagChoicesList tagChoicesList = new TagChoicesList();
        return tagChoicesList;
    }

    @BeforeEach
    public void initTest() {
        tagChoicesList = createEntity(em);
    }

    @Test
    @Transactional
    void createTagChoicesList() throws Exception {
        int databaseSizeBeforeCreate = tagChoicesListRepository.findAll().size();
        // Create the TagChoicesList
        TagChoicesListDTO tagChoicesListDTO = tagChoicesListMapper.toDto(tagChoicesList);
        restTagChoicesListMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagChoicesListDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TagChoicesList in the database
        List<TagChoicesList> tagChoicesListList = tagChoicesListRepository.findAll();
        assertThat(tagChoicesListList).hasSize(databaseSizeBeforeCreate + 1);
        TagChoicesList testTagChoicesList = tagChoicesListList.get(tagChoicesListList.size() - 1);
    }

    @Test
    @Transactional
    void createTagChoicesListWithExistingId() throws Exception {
        // Create the TagChoicesList with an existing ID
        tagChoicesList.setId(1L);
        TagChoicesListDTO tagChoicesListDTO = tagChoicesListMapper.toDto(tagChoicesList);

        int databaseSizeBeforeCreate = tagChoicesListRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTagChoicesListMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagChoicesListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesList in the database
        List<TagChoicesList> tagChoicesListList = tagChoicesListRepository.findAll();
        assertThat(tagChoicesListList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTagChoicesLists() throws Exception {
        // Initialize the database
        tagChoicesListRepository.saveAndFlush(tagChoicesList);

        // Get all the tagChoicesListList
        restTagChoicesListMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagChoicesList.getId().intValue())));
    }

    @Test
    @Transactional
    void getTagChoicesList() throws Exception {
        // Initialize the database
        tagChoicesListRepository.saveAndFlush(tagChoicesList);

        // Get the tagChoicesList
        restTagChoicesListMockMvc
            .perform(get(ENTITY_API_URL_ID, tagChoicesList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tagChoicesList.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTagChoicesList() throws Exception {
        // Get the tagChoicesList
        restTagChoicesListMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTagChoicesList() throws Exception {
        // Initialize the database
        tagChoicesListRepository.saveAndFlush(tagChoicesList);

        int databaseSizeBeforeUpdate = tagChoicesListRepository.findAll().size();

        // Update the tagChoicesList
        TagChoicesList updatedTagChoicesList = tagChoicesListRepository.findById(tagChoicesList.getId()).get();
        // Disconnect from session so that the updates on updatedTagChoicesList are not directly saved in db
        em.detach(updatedTagChoicesList);
        TagChoicesListDTO tagChoicesListDTO = tagChoicesListMapper.toDto(updatedTagChoicesList);

        restTagChoicesListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tagChoicesListDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListDTO))
            )
            .andExpect(status().isOk());

        // Validate the TagChoicesList in the database
        List<TagChoicesList> tagChoicesListList = tagChoicesListRepository.findAll();
        assertThat(tagChoicesListList).hasSize(databaseSizeBeforeUpdate);
        TagChoicesList testTagChoicesList = tagChoicesListList.get(tagChoicesListList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingTagChoicesList() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListRepository.findAll().size();
        tagChoicesList.setId(count.incrementAndGet());

        // Create the TagChoicesList
        TagChoicesListDTO tagChoicesListDTO = tagChoicesListMapper.toDto(tagChoicesList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagChoicesListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tagChoicesListDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesList in the database
        List<TagChoicesList> tagChoicesListList = tagChoicesListRepository.findAll();
        assertThat(tagChoicesListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTagChoicesList() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListRepository.findAll().size();
        tagChoicesList.setId(count.incrementAndGet());

        // Create the TagChoicesList
        TagChoicesListDTO tagChoicesListDTO = tagChoicesListMapper.toDto(tagChoicesList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagChoicesListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesList in the database
        List<TagChoicesList> tagChoicesListList = tagChoicesListRepository.findAll();
        assertThat(tagChoicesListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTagChoicesList() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListRepository.findAll().size();
        tagChoicesList.setId(count.incrementAndGet());

        // Create the TagChoicesList
        TagChoicesListDTO tagChoicesListDTO = tagChoicesListMapper.toDto(tagChoicesList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagChoicesListMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tagChoicesListDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TagChoicesList in the database
        List<TagChoicesList> tagChoicesListList = tagChoicesListRepository.findAll();
        assertThat(tagChoicesListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTagChoicesListWithPatch() throws Exception {
        // Initialize the database
        tagChoicesListRepository.saveAndFlush(tagChoicesList);

        int databaseSizeBeforeUpdate = tagChoicesListRepository.findAll().size();

        // Update the tagChoicesList using partial update
        TagChoicesList partialUpdatedTagChoicesList = new TagChoicesList();
        partialUpdatedTagChoicesList.setId(tagChoicesList.getId());

        restTagChoicesListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTagChoicesList.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTagChoicesList))
            )
            .andExpect(status().isOk());

        // Validate the TagChoicesList in the database
        List<TagChoicesList> tagChoicesListList = tagChoicesListRepository.findAll();
        assertThat(tagChoicesListList).hasSize(databaseSizeBeforeUpdate);
        TagChoicesList testTagChoicesList = tagChoicesListList.get(tagChoicesListList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateTagChoicesListWithPatch() throws Exception {
        // Initialize the database
        tagChoicesListRepository.saveAndFlush(tagChoicesList);

        int databaseSizeBeforeUpdate = tagChoicesListRepository.findAll().size();

        // Update the tagChoicesList using partial update
        TagChoicesList partialUpdatedTagChoicesList = new TagChoicesList();
        partialUpdatedTagChoicesList.setId(tagChoicesList.getId());

        restTagChoicesListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTagChoicesList.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTagChoicesList))
            )
            .andExpect(status().isOk());

        // Validate the TagChoicesList in the database
        List<TagChoicesList> tagChoicesListList = tagChoicesListRepository.findAll();
        assertThat(tagChoicesListList).hasSize(databaseSizeBeforeUpdate);
        TagChoicesList testTagChoicesList = tagChoicesListList.get(tagChoicesListList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingTagChoicesList() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListRepository.findAll().size();
        tagChoicesList.setId(count.incrementAndGet());

        // Create the TagChoicesList
        TagChoicesListDTO tagChoicesListDTO = tagChoicesListMapper.toDto(tagChoicesList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagChoicesListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tagChoicesListDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesList in the database
        List<TagChoicesList> tagChoicesListList = tagChoicesListRepository.findAll();
        assertThat(tagChoicesListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTagChoicesList() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListRepository.findAll().size();
        tagChoicesList.setId(count.incrementAndGet());

        // Create the TagChoicesList
        TagChoicesListDTO tagChoicesListDTO = tagChoicesListMapper.toDto(tagChoicesList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagChoicesListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesList in the database
        List<TagChoicesList> tagChoicesListList = tagChoicesListRepository.findAll();
        assertThat(tagChoicesListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTagChoicesList() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListRepository.findAll().size();
        tagChoicesList.setId(count.incrementAndGet());

        // Create the TagChoicesList
        TagChoicesListDTO tagChoicesListDTO = tagChoicesListMapper.toDto(tagChoicesList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagChoicesListMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TagChoicesList in the database
        List<TagChoicesList> tagChoicesListList = tagChoicesListRepository.findAll();
        assertThat(tagChoicesListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTagChoicesList() throws Exception {
        // Initialize the database
        tagChoicesListRepository.saveAndFlush(tagChoicesList);

        int databaseSizeBeforeDelete = tagChoicesListRepository.findAll().size();

        // Delete the tagChoicesList
        restTagChoicesListMockMvc
            .perform(delete(ENTITY_API_URL_ID, tagChoicesList.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TagChoicesList> tagChoicesListList = tagChoicesListRepository.findAll();
        assertThat(tagChoicesListList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
