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
import org.contextmapper.generated.skillcontext.domain.CreateTag;
import org.contextmapper.generated.skillcontext.repository.CreateTagRepository;
import org.contextmapper.generated.skillcontext.service.dto.CreateTagDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CreateTagMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreateTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreateTagResourceIT {

    private static final String ENTITY_API_URL = "/api/create-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateTagRepository createTagRepository;

    @Autowired
    private CreateTagMapper createTagMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreateTagMockMvc;

    private CreateTag createTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateTag createEntity(EntityManager em) {
        CreateTag createTag = new CreateTag();
        return createTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateTag createUpdatedEntity(EntityManager em) {
        CreateTag createTag = new CreateTag();
        return createTag;
    }

    @BeforeEach
    public void initTest() {
        createTag = createEntity(em);
    }

    @Test
    @Transactional
    void createCreateTag() throws Exception {
        int databaseSizeBeforeCreate = createTagRepository.findAll().size();
        // Create the CreateTag
        CreateTagDTO createTagDTO = createTagMapper.toDto(createTag);
        restCreateTagMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createTagDTO)))
            .andExpect(status().isCreated());

        // Validate the CreateTag in the database
        List<CreateTag> createTagList = createTagRepository.findAll();
        assertThat(createTagList).hasSize(databaseSizeBeforeCreate + 1);
        CreateTag testCreateTag = createTagList.get(createTagList.size() - 1);
    }

    @Test
    @Transactional
    void createCreateTagWithExistingId() throws Exception {
        // Create the CreateTag with an existing ID
        createTag.setId(1L);
        CreateTagDTO createTagDTO = createTagMapper.toDto(createTag);

        int databaseSizeBeforeCreate = createTagRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreateTagMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createTagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CreateTag in the database
        List<CreateTag> createTagList = createTagRepository.findAll();
        assertThat(createTagList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCreateTags() throws Exception {
        // Initialize the database
        createTagRepository.saveAndFlush(createTag);

        // Get all the createTagList
        restCreateTagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(createTag.getId().intValue())));
    }

    @Test
    @Transactional
    void getCreateTag() throws Exception {
        // Initialize the database
        createTagRepository.saveAndFlush(createTag);

        // Get the createTag
        restCreateTagMockMvc
            .perform(get(ENTITY_API_URL_ID, createTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(createTag.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCreateTag() throws Exception {
        // Get the createTag
        restCreateTagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCreateTag() throws Exception {
        // Initialize the database
        createTagRepository.saveAndFlush(createTag);

        int databaseSizeBeforeUpdate = createTagRepository.findAll().size();

        // Update the createTag
        CreateTag updatedCreateTag = createTagRepository.findById(createTag.getId()).get();
        // Disconnect from session so that the updates on updatedCreateTag are not directly saved in db
        em.detach(updatedCreateTag);
        CreateTagDTO createTagDTO = createTagMapper.toDto(updatedCreateTag);

        restCreateTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createTagDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createTagDTO))
            )
            .andExpect(status().isOk());

        // Validate the CreateTag in the database
        List<CreateTag> createTagList = createTagRepository.findAll();
        assertThat(createTagList).hasSize(databaseSizeBeforeUpdate);
        CreateTag testCreateTag = createTagList.get(createTagList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCreateTag() throws Exception {
        int databaseSizeBeforeUpdate = createTagRepository.findAll().size();
        createTag.setId(count.incrementAndGet());

        // Create the CreateTag
        CreateTagDTO createTagDTO = createTagMapper.toDto(createTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createTagDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateTag in the database
        List<CreateTag> createTagList = createTagRepository.findAll();
        assertThat(createTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCreateTag() throws Exception {
        int databaseSizeBeforeUpdate = createTagRepository.findAll().size();
        createTag.setId(count.incrementAndGet());

        // Create the CreateTag
        CreateTagDTO createTagDTO = createTagMapper.toDto(createTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateTag in the database
        List<CreateTag> createTagList = createTagRepository.findAll();
        assertThat(createTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCreateTag() throws Exception {
        int databaseSizeBeforeUpdate = createTagRepository.findAll().size();
        createTag.setId(count.incrementAndGet());

        // Create the CreateTag
        CreateTagDTO createTagDTO = createTagMapper.toDto(createTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateTagMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createTagDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateTag in the database
        List<CreateTag> createTagList = createTagRepository.findAll();
        assertThat(createTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCreateTagWithPatch() throws Exception {
        // Initialize the database
        createTagRepository.saveAndFlush(createTag);

        int databaseSizeBeforeUpdate = createTagRepository.findAll().size();

        // Update the createTag using partial update
        CreateTag partialUpdatedCreateTag = new CreateTag();
        partialUpdatedCreateTag.setId(createTag.getId());

        restCreateTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateTag))
            )
            .andExpect(status().isOk());

        // Validate the CreateTag in the database
        List<CreateTag> createTagList = createTagRepository.findAll();
        assertThat(createTagList).hasSize(databaseSizeBeforeUpdate);
        CreateTag testCreateTag = createTagList.get(createTagList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCreateTagWithPatch() throws Exception {
        // Initialize the database
        createTagRepository.saveAndFlush(createTag);

        int databaseSizeBeforeUpdate = createTagRepository.findAll().size();

        // Update the createTag using partial update
        CreateTag partialUpdatedCreateTag = new CreateTag();
        partialUpdatedCreateTag.setId(createTag.getId());

        restCreateTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateTag))
            )
            .andExpect(status().isOk());

        // Validate the CreateTag in the database
        List<CreateTag> createTagList = createTagRepository.findAll();
        assertThat(createTagList).hasSize(databaseSizeBeforeUpdate);
        CreateTag testCreateTag = createTagList.get(createTagList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCreateTag() throws Exception {
        int databaseSizeBeforeUpdate = createTagRepository.findAll().size();
        createTag.setId(count.incrementAndGet());

        // Create the CreateTag
        CreateTagDTO createTagDTO = createTagMapper.toDto(createTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, createTagDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateTag in the database
        List<CreateTag> createTagList = createTagRepository.findAll();
        assertThat(createTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCreateTag() throws Exception {
        int databaseSizeBeforeUpdate = createTagRepository.findAll().size();
        createTag.setId(count.incrementAndGet());

        // Create the CreateTag
        CreateTagDTO createTagDTO = createTagMapper.toDto(createTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateTag in the database
        List<CreateTag> createTagList = createTagRepository.findAll();
        assertThat(createTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCreateTag() throws Exception {
        int databaseSizeBeforeUpdate = createTagRepository.findAll().size();
        createTag.setId(count.incrementAndGet());

        // Create the CreateTag
        CreateTagDTO createTagDTO = createTagMapper.toDto(createTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateTagMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(createTagDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateTag in the database
        List<CreateTag> createTagList = createTagRepository.findAll();
        assertThat(createTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCreateTag() throws Exception {
        // Initialize the database
        createTagRepository.saveAndFlush(createTag);

        int databaseSizeBeforeDelete = createTagRepository.findAll().size();

        // Delete the createTag
        restCreateTagMockMvc
            .perform(delete(ENTITY_API_URL_ID, createTag.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CreateTag> createTagList = createTagRepository.findAll();
        assertThat(createTagList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
