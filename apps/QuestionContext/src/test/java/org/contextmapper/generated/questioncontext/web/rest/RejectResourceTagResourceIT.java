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
import org.contextmapper.generated.questioncontext.domain.RejectResourceTag;
import org.contextmapper.generated.questioncontext.repository.RejectResourceTagRepository;
import org.contextmapper.generated.questioncontext.service.dto.RejectResourceTagDTO;
import org.contextmapper.generated.questioncontext.service.mapper.RejectResourceTagMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RejectResourceTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RejectResourceTagResourceIT {

    private static final String ENTITY_API_URL = "/api/reject-resource-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RejectResourceTagRepository rejectResourceTagRepository;

    @Autowired
    private RejectResourceTagMapper rejectResourceTagMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRejectResourceTagMockMvc;

    private RejectResourceTag rejectResourceTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RejectResourceTag createEntity(EntityManager em) {
        RejectResourceTag rejectResourceTag = new RejectResourceTag();
        return rejectResourceTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RejectResourceTag createUpdatedEntity(EntityManager em) {
        RejectResourceTag rejectResourceTag = new RejectResourceTag();
        return rejectResourceTag;
    }

    @BeforeEach
    public void initTest() {
        rejectResourceTag = createEntity(em);
    }

    @Test
    @Transactional
    void createRejectResourceTag() throws Exception {
        int databaseSizeBeforeCreate = rejectResourceTagRepository.findAll().size();
        // Create the RejectResourceTag
        RejectResourceTagDTO rejectResourceTagDTO = rejectResourceTagMapper.toDto(rejectResourceTag);
        restRejectResourceTagMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RejectResourceTag in the database
        List<RejectResourceTag> rejectResourceTagList = rejectResourceTagRepository.findAll();
        assertThat(rejectResourceTagList).hasSize(databaseSizeBeforeCreate + 1);
        RejectResourceTag testRejectResourceTag = rejectResourceTagList.get(rejectResourceTagList.size() - 1);
    }

    @Test
    @Transactional
    void createRejectResourceTagWithExistingId() throws Exception {
        // Create the RejectResourceTag with an existing ID
        rejectResourceTag.setId(1L);
        RejectResourceTagDTO rejectResourceTagDTO = rejectResourceTagMapper.toDto(rejectResourceTag);

        int databaseSizeBeforeCreate = rejectResourceTagRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRejectResourceTagMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectResourceTag in the database
        List<RejectResourceTag> rejectResourceTagList = rejectResourceTagRepository.findAll();
        assertThat(rejectResourceTagList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRejectResourceTags() throws Exception {
        // Initialize the database
        rejectResourceTagRepository.saveAndFlush(rejectResourceTag);

        // Get all the rejectResourceTagList
        restRejectResourceTagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rejectResourceTag.getId().intValue())));
    }

    @Test
    @Transactional
    void getRejectResourceTag() throws Exception {
        // Initialize the database
        rejectResourceTagRepository.saveAndFlush(rejectResourceTag);

        // Get the rejectResourceTag
        restRejectResourceTagMockMvc
            .perform(get(ENTITY_API_URL_ID, rejectResourceTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rejectResourceTag.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingRejectResourceTag() throws Exception {
        // Get the rejectResourceTag
        restRejectResourceTagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRejectResourceTag() throws Exception {
        // Initialize the database
        rejectResourceTagRepository.saveAndFlush(rejectResourceTag);

        int databaseSizeBeforeUpdate = rejectResourceTagRepository.findAll().size();

        // Update the rejectResourceTag
        RejectResourceTag updatedRejectResourceTag = rejectResourceTagRepository.findById(rejectResourceTag.getId()).get();
        // Disconnect from session so that the updates on updatedRejectResourceTag are not directly saved in db
        em.detach(updatedRejectResourceTag);
        RejectResourceTagDTO rejectResourceTagDTO = rejectResourceTagMapper.toDto(updatedRejectResourceTag);

        restRejectResourceTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rejectResourceTagDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagDTO))
            )
            .andExpect(status().isOk());

        // Validate the RejectResourceTag in the database
        List<RejectResourceTag> rejectResourceTagList = rejectResourceTagRepository.findAll();
        assertThat(rejectResourceTagList).hasSize(databaseSizeBeforeUpdate);
        RejectResourceTag testRejectResourceTag = rejectResourceTagList.get(rejectResourceTagList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingRejectResourceTag() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagRepository.findAll().size();
        rejectResourceTag.setId(count.incrementAndGet());

        // Create the RejectResourceTag
        RejectResourceTagDTO rejectResourceTagDTO = rejectResourceTagMapper.toDto(rejectResourceTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRejectResourceTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rejectResourceTagDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectResourceTag in the database
        List<RejectResourceTag> rejectResourceTagList = rejectResourceTagRepository.findAll();
        assertThat(rejectResourceTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRejectResourceTag() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagRepository.findAll().size();
        rejectResourceTag.setId(count.incrementAndGet());

        // Create the RejectResourceTag
        RejectResourceTagDTO rejectResourceTagDTO = rejectResourceTagMapper.toDto(rejectResourceTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRejectResourceTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectResourceTag in the database
        List<RejectResourceTag> rejectResourceTagList = rejectResourceTagRepository.findAll();
        assertThat(rejectResourceTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRejectResourceTag() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagRepository.findAll().size();
        rejectResourceTag.setId(count.incrementAndGet());

        // Create the RejectResourceTag
        RejectResourceTagDTO rejectResourceTagDTO = rejectResourceTagMapper.toDto(rejectResourceTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRejectResourceTagMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rejectResourceTagDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RejectResourceTag in the database
        List<RejectResourceTag> rejectResourceTagList = rejectResourceTagRepository.findAll();
        assertThat(rejectResourceTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRejectResourceTagWithPatch() throws Exception {
        // Initialize the database
        rejectResourceTagRepository.saveAndFlush(rejectResourceTag);

        int databaseSizeBeforeUpdate = rejectResourceTagRepository.findAll().size();

        // Update the rejectResourceTag using partial update
        RejectResourceTag partialUpdatedRejectResourceTag = new RejectResourceTag();
        partialUpdatedRejectResourceTag.setId(rejectResourceTag.getId());

        restRejectResourceTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRejectResourceTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRejectResourceTag))
            )
            .andExpect(status().isOk());

        // Validate the RejectResourceTag in the database
        List<RejectResourceTag> rejectResourceTagList = rejectResourceTagRepository.findAll();
        assertThat(rejectResourceTagList).hasSize(databaseSizeBeforeUpdate);
        RejectResourceTag testRejectResourceTag = rejectResourceTagList.get(rejectResourceTagList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateRejectResourceTagWithPatch() throws Exception {
        // Initialize the database
        rejectResourceTagRepository.saveAndFlush(rejectResourceTag);

        int databaseSizeBeforeUpdate = rejectResourceTagRepository.findAll().size();

        // Update the rejectResourceTag using partial update
        RejectResourceTag partialUpdatedRejectResourceTag = new RejectResourceTag();
        partialUpdatedRejectResourceTag.setId(rejectResourceTag.getId());

        restRejectResourceTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRejectResourceTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRejectResourceTag))
            )
            .andExpect(status().isOk());

        // Validate the RejectResourceTag in the database
        List<RejectResourceTag> rejectResourceTagList = rejectResourceTagRepository.findAll();
        assertThat(rejectResourceTagList).hasSize(databaseSizeBeforeUpdate);
        RejectResourceTag testRejectResourceTag = rejectResourceTagList.get(rejectResourceTagList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingRejectResourceTag() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagRepository.findAll().size();
        rejectResourceTag.setId(count.incrementAndGet());

        // Create the RejectResourceTag
        RejectResourceTagDTO rejectResourceTagDTO = rejectResourceTagMapper.toDto(rejectResourceTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRejectResourceTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rejectResourceTagDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectResourceTag in the database
        List<RejectResourceTag> rejectResourceTagList = rejectResourceTagRepository.findAll();
        assertThat(rejectResourceTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRejectResourceTag() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagRepository.findAll().size();
        rejectResourceTag.setId(count.incrementAndGet());

        // Create the RejectResourceTag
        RejectResourceTagDTO rejectResourceTagDTO = rejectResourceTagMapper.toDto(rejectResourceTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRejectResourceTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectResourceTag in the database
        List<RejectResourceTag> rejectResourceTagList = rejectResourceTagRepository.findAll();
        assertThat(rejectResourceTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRejectResourceTag() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagRepository.findAll().size();
        rejectResourceTag.setId(count.incrementAndGet());

        // Create the RejectResourceTag
        RejectResourceTagDTO rejectResourceTagDTO = rejectResourceTagMapper.toDto(rejectResourceTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRejectResourceTagMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RejectResourceTag in the database
        List<RejectResourceTag> rejectResourceTagList = rejectResourceTagRepository.findAll();
        assertThat(rejectResourceTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRejectResourceTag() throws Exception {
        // Initialize the database
        rejectResourceTagRepository.saveAndFlush(rejectResourceTag);

        int databaseSizeBeforeDelete = rejectResourceTagRepository.findAll().size();

        // Delete the rejectResourceTag
        restRejectResourceTagMockMvc
            .perform(delete(ENTITY_API_URL_ID, rejectResourceTag.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RejectResourceTag> rejectResourceTagList = rejectResourceTagRepository.findAll();
        assertThat(rejectResourceTagList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
