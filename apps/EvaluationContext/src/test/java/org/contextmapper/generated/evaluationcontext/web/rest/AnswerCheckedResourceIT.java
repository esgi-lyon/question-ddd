package org.contextmapper.generated.evaluationcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.evaluationcontext.IntegrationTest;
import org.contextmapper.generated.evaluationcontext.domain.AnswerChecked;
import org.contextmapper.generated.evaluationcontext.repository.AnswerCheckedRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AnswerCheckedDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AnswerCheckedMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AnswerCheckedResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnswerCheckedResourceIT {

    private static final String ENTITY_API_URL = "/api/answer-checkeds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnswerCheckedRepository answerCheckedRepository;

    @Autowired
    private AnswerCheckedMapper answerCheckedMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnswerCheckedMockMvc;

    private AnswerChecked answerChecked;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerChecked createEntity(EntityManager em) {
        AnswerChecked answerChecked = new AnswerChecked();
        return answerChecked;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerChecked createUpdatedEntity(EntityManager em) {
        AnswerChecked answerChecked = new AnswerChecked();
        return answerChecked;
    }

    @BeforeEach
    public void initTest() {
        answerChecked = createEntity(em);
    }

    @Test
    @Transactional
    void createAnswerChecked() throws Exception {
        int databaseSizeBeforeCreate = answerCheckedRepository.findAll().size();
        // Create the AnswerChecked
        AnswerCheckedDTO answerCheckedDTO = answerCheckedMapper.toDto(answerChecked);
        restAnswerCheckedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerCheckedDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AnswerChecked in the database
        List<AnswerChecked> answerCheckedList = answerCheckedRepository.findAll();
        assertThat(answerCheckedList).hasSize(databaseSizeBeforeCreate + 1);
        AnswerChecked testAnswerChecked = answerCheckedList.get(answerCheckedList.size() - 1);
    }

    @Test
    @Transactional
    void createAnswerCheckedWithExistingId() throws Exception {
        // Create the AnswerChecked with an existing ID
        answerChecked.setId(1L);
        AnswerCheckedDTO answerCheckedDTO = answerCheckedMapper.toDto(answerChecked);

        int databaseSizeBeforeCreate = answerCheckedRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnswerCheckedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerCheckedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerChecked in the database
        List<AnswerChecked> answerCheckedList = answerCheckedRepository.findAll();
        assertThat(answerCheckedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAnswerCheckeds() throws Exception {
        // Initialize the database
        answerCheckedRepository.saveAndFlush(answerChecked);

        // Get all the answerCheckedList
        restAnswerCheckedMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answerChecked.getId().intValue())));
    }

    @Test
    @Transactional
    void getAnswerChecked() throws Exception {
        // Initialize the database
        answerCheckedRepository.saveAndFlush(answerChecked);

        // Get the answerChecked
        restAnswerCheckedMockMvc
            .perform(get(ENTITY_API_URL_ID, answerChecked.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(answerChecked.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAnswerChecked() throws Exception {
        // Get the answerChecked
        restAnswerCheckedMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAnswerChecked() throws Exception {
        // Initialize the database
        answerCheckedRepository.saveAndFlush(answerChecked);

        int databaseSizeBeforeUpdate = answerCheckedRepository.findAll().size();

        // Update the answerChecked
        AnswerChecked updatedAnswerChecked = answerCheckedRepository.findById(answerChecked.getId()).get();
        // Disconnect from session so that the updates on updatedAnswerChecked are not directly saved in db
        em.detach(updatedAnswerChecked);
        AnswerCheckedDTO answerCheckedDTO = answerCheckedMapper.toDto(updatedAnswerChecked);

        restAnswerCheckedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, answerCheckedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerCheckedDTO))
            )
            .andExpect(status().isOk());

        // Validate the AnswerChecked in the database
        List<AnswerChecked> answerCheckedList = answerCheckedRepository.findAll();
        assertThat(answerCheckedList).hasSize(databaseSizeBeforeUpdate);
        AnswerChecked testAnswerChecked = answerCheckedList.get(answerCheckedList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingAnswerChecked() throws Exception {
        int databaseSizeBeforeUpdate = answerCheckedRepository.findAll().size();
        answerChecked.setId(count.incrementAndGet());

        // Create the AnswerChecked
        AnswerCheckedDTO answerCheckedDTO = answerCheckedMapper.toDto(answerChecked);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnswerCheckedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, answerCheckedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerCheckedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerChecked in the database
        List<AnswerChecked> answerCheckedList = answerCheckedRepository.findAll();
        assertThat(answerCheckedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAnswerChecked() throws Exception {
        int databaseSizeBeforeUpdate = answerCheckedRepository.findAll().size();
        answerChecked.setId(count.incrementAndGet());

        // Create the AnswerChecked
        AnswerCheckedDTO answerCheckedDTO = answerCheckedMapper.toDto(answerChecked);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerCheckedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerCheckedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerChecked in the database
        List<AnswerChecked> answerCheckedList = answerCheckedRepository.findAll();
        assertThat(answerCheckedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAnswerChecked() throws Exception {
        int databaseSizeBeforeUpdate = answerCheckedRepository.findAll().size();
        answerChecked.setId(count.incrementAndGet());

        // Create the AnswerChecked
        AnswerCheckedDTO answerCheckedDTO = answerCheckedMapper.toDto(answerChecked);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerCheckedMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerCheckedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnswerChecked in the database
        List<AnswerChecked> answerCheckedList = answerCheckedRepository.findAll();
        assertThat(answerCheckedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAnswerCheckedWithPatch() throws Exception {
        // Initialize the database
        answerCheckedRepository.saveAndFlush(answerChecked);

        int databaseSizeBeforeUpdate = answerCheckedRepository.findAll().size();

        // Update the answerChecked using partial update
        AnswerChecked partialUpdatedAnswerChecked = new AnswerChecked();
        partialUpdatedAnswerChecked.setId(answerChecked.getId());

        restAnswerCheckedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnswerChecked.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnswerChecked))
            )
            .andExpect(status().isOk());

        // Validate the AnswerChecked in the database
        List<AnswerChecked> answerCheckedList = answerCheckedRepository.findAll();
        assertThat(answerCheckedList).hasSize(databaseSizeBeforeUpdate);
        AnswerChecked testAnswerChecked = answerCheckedList.get(answerCheckedList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateAnswerCheckedWithPatch() throws Exception {
        // Initialize the database
        answerCheckedRepository.saveAndFlush(answerChecked);

        int databaseSizeBeforeUpdate = answerCheckedRepository.findAll().size();

        // Update the answerChecked using partial update
        AnswerChecked partialUpdatedAnswerChecked = new AnswerChecked();
        partialUpdatedAnswerChecked.setId(answerChecked.getId());

        restAnswerCheckedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnswerChecked.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnswerChecked))
            )
            .andExpect(status().isOk());

        // Validate the AnswerChecked in the database
        List<AnswerChecked> answerCheckedList = answerCheckedRepository.findAll();
        assertThat(answerCheckedList).hasSize(databaseSizeBeforeUpdate);
        AnswerChecked testAnswerChecked = answerCheckedList.get(answerCheckedList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingAnswerChecked() throws Exception {
        int databaseSizeBeforeUpdate = answerCheckedRepository.findAll().size();
        answerChecked.setId(count.incrementAndGet());

        // Create the AnswerChecked
        AnswerCheckedDTO answerCheckedDTO = answerCheckedMapper.toDto(answerChecked);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnswerCheckedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, answerCheckedDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerCheckedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerChecked in the database
        List<AnswerChecked> answerCheckedList = answerCheckedRepository.findAll();
        assertThat(answerCheckedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAnswerChecked() throws Exception {
        int databaseSizeBeforeUpdate = answerCheckedRepository.findAll().size();
        answerChecked.setId(count.incrementAndGet());

        // Create the AnswerChecked
        AnswerCheckedDTO answerCheckedDTO = answerCheckedMapper.toDto(answerChecked);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerCheckedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerCheckedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerChecked in the database
        List<AnswerChecked> answerCheckedList = answerCheckedRepository.findAll();
        assertThat(answerCheckedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAnswerChecked() throws Exception {
        int databaseSizeBeforeUpdate = answerCheckedRepository.findAll().size();
        answerChecked.setId(count.incrementAndGet());

        // Create the AnswerChecked
        AnswerCheckedDTO answerCheckedDTO = answerCheckedMapper.toDto(answerChecked);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerCheckedMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerCheckedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnswerChecked in the database
        List<AnswerChecked> answerCheckedList = answerCheckedRepository.findAll();
        assertThat(answerCheckedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAnswerChecked() throws Exception {
        // Initialize the database
        answerCheckedRepository.saveAndFlush(answerChecked);

        int databaseSizeBeforeDelete = answerCheckedRepository.findAll().size();

        // Delete the answerChecked
        restAnswerCheckedMockMvc
            .perform(delete(ENTITY_API_URL_ID, answerChecked.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AnswerChecked> answerCheckedList = answerCheckedRepository.findAll();
        assertThat(answerCheckedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
