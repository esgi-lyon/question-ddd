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
import org.contextmapper.generated.answercontext.domain.AnswerSubmit;
import org.contextmapper.generated.answercontext.repository.AnswerSubmitRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmitDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerSubmitMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AnswerSubmitResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnswerSubmitResourceIT {

    private static final String ENTITY_API_URL = "/api/answer-submits";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnswerSubmitRepository answerSubmitRepository;

    @Autowired
    private AnswerSubmitMapper answerSubmitMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnswerSubmitMockMvc;

    private AnswerSubmit answerSubmit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSubmit createEntity(EntityManager em) {
        AnswerSubmit answerSubmit = new AnswerSubmit();
        return answerSubmit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSubmit createUpdatedEntity(EntityManager em) {
        AnswerSubmit answerSubmit = new AnswerSubmit();
        return answerSubmit;
    }

    @BeforeEach
    public void initTest() {
        answerSubmit = createEntity(em);
    }

    @Test
    @Transactional
    void createAnswerSubmit() throws Exception {
        int databaseSizeBeforeCreate = answerSubmitRepository.findAll().size();
        // Create the AnswerSubmit
        AnswerSubmitDTO answerSubmitDTO = answerSubmitMapper.toDto(answerSubmit);
        restAnswerSubmitMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerSubmitDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AnswerSubmit in the database
        List<AnswerSubmit> answerSubmitList = answerSubmitRepository.findAll();
        assertThat(answerSubmitList).hasSize(databaseSizeBeforeCreate + 1);
        AnswerSubmit testAnswerSubmit = answerSubmitList.get(answerSubmitList.size() - 1);
    }

    @Test
    @Transactional
    void createAnswerSubmitWithExistingId() throws Exception {
        // Create the AnswerSubmit with an existing ID
        answerSubmit.setId(1L);
        AnswerSubmitDTO answerSubmitDTO = answerSubmitMapper.toDto(answerSubmit);

        int databaseSizeBeforeCreate = answerSubmitRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnswerSubmitMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerSubmitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmit in the database
        List<AnswerSubmit> answerSubmitList = answerSubmitRepository.findAll();
        assertThat(answerSubmitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAnswerSubmits() throws Exception {
        // Initialize the database
        answerSubmitRepository.saveAndFlush(answerSubmit);

        // Get all the answerSubmitList
        restAnswerSubmitMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answerSubmit.getId().intValue())));
    }

    @Test
    @Transactional
    void getAnswerSubmit() throws Exception {
        // Initialize the database
        answerSubmitRepository.saveAndFlush(answerSubmit);

        // Get the answerSubmit
        restAnswerSubmitMockMvc
            .perform(get(ENTITY_API_URL_ID, answerSubmit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(answerSubmit.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAnswerSubmit() throws Exception {
        // Get the answerSubmit
        restAnswerSubmitMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAnswerSubmit() throws Exception {
        // Initialize the database
        answerSubmitRepository.saveAndFlush(answerSubmit);

        int databaseSizeBeforeUpdate = answerSubmitRepository.findAll().size();

        // Update the answerSubmit
        AnswerSubmit updatedAnswerSubmit = answerSubmitRepository.findById(answerSubmit.getId()).get();
        // Disconnect from session so that the updates on updatedAnswerSubmit are not directly saved in db
        em.detach(updatedAnswerSubmit);
        AnswerSubmitDTO answerSubmitDTO = answerSubmitMapper.toDto(updatedAnswerSubmit);

        restAnswerSubmitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, answerSubmitDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitDTO))
            )
            .andExpect(status().isOk());

        // Validate the AnswerSubmit in the database
        List<AnswerSubmit> answerSubmitList = answerSubmitRepository.findAll();
        assertThat(answerSubmitList).hasSize(databaseSizeBeforeUpdate);
        AnswerSubmit testAnswerSubmit = answerSubmitList.get(answerSubmitList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingAnswerSubmit() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitRepository.findAll().size();
        answerSubmit.setId(count.incrementAndGet());

        // Create the AnswerSubmit
        AnswerSubmitDTO answerSubmitDTO = answerSubmitMapper.toDto(answerSubmit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnswerSubmitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, answerSubmitDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmit in the database
        List<AnswerSubmit> answerSubmitList = answerSubmitRepository.findAll();
        assertThat(answerSubmitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAnswerSubmit() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitRepository.findAll().size();
        answerSubmit.setId(count.incrementAndGet());

        // Create the AnswerSubmit
        AnswerSubmitDTO answerSubmitDTO = answerSubmitMapper.toDto(answerSubmit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerSubmitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmit in the database
        List<AnswerSubmit> answerSubmitList = answerSubmitRepository.findAll();
        assertThat(answerSubmitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAnswerSubmit() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitRepository.findAll().size();
        answerSubmit.setId(count.incrementAndGet());

        // Create the AnswerSubmit
        AnswerSubmitDTO answerSubmitDTO = answerSubmitMapper.toDto(answerSubmit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerSubmitMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerSubmitDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnswerSubmit in the database
        List<AnswerSubmit> answerSubmitList = answerSubmitRepository.findAll();
        assertThat(answerSubmitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAnswerSubmitWithPatch() throws Exception {
        // Initialize the database
        answerSubmitRepository.saveAndFlush(answerSubmit);

        int databaseSizeBeforeUpdate = answerSubmitRepository.findAll().size();

        // Update the answerSubmit using partial update
        AnswerSubmit partialUpdatedAnswerSubmit = new AnswerSubmit();
        partialUpdatedAnswerSubmit.setId(answerSubmit.getId());

        restAnswerSubmitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnswerSubmit.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnswerSubmit))
            )
            .andExpect(status().isOk());

        // Validate the AnswerSubmit in the database
        List<AnswerSubmit> answerSubmitList = answerSubmitRepository.findAll();
        assertThat(answerSubmitList).hasSize(databaseSizeBeforeUpdate);
        AnswerSubmit testAnswerSubmit = answerSubmitList.get(answerSubmitList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateAnswerSubmitWithPatch() throws Exception {
        // Initialize the database
        answerSubmitRepository.saveAndFlush(answerSubmit);

        int databaseSizeBeforeUpdate = answerSubmitRepository.findAll().size();

        // Update the answerSubmit using partial update
        AnswerSubmit partialUpdatedAnswerSubmit = new AnswerSubmit();
        partialUpdatedAnswerSubmit.setId(answerSubmit.getId());

        restAnswerSubmitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnswerSubmit.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnswerSubmit))
            )
            .andExpect(status().isOk());

        // Validate the AnswerSubmit in the database
        List<AnswerSubmit> answerSubmitList = answerSubmitRepository.findAll();
        assertThat(answerSubmitList).hasSize(databaseSizeBeforeUpdate);
        AnswerSubmit testAnswerSubmit = answerSubmitList.get(answerSubmitList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingAnswerSubmit() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitRepository.findAll().size();
        answerSubmit.setId(count.incrementAndGet());

        // Create the AnswerSubmit
        AnswerSubmitDTO answerSubmitDTO = answerSubmitMapper.toDto(answerSubmit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnswerSubmitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, answerSubmitDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmit in the database
        List<AnswerSubmit> answerSubmitList = answerSubmitRepository.findAll();
        assertThat(answerSubmitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAnswerSubmit() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitRepository.findAll().size();
        answerSubmit.setId(count.incrementAndGet());

        // Create the AnswerSubmit
        AnswerSubmitDTO answerSubmitDTO = answerSubmitMapper.toDto(answerSubmit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerSubmitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmit in the database
        List<AnswerSubmit> answerSubmitList = answerSubmitRepository.findAll();
        assertThat(answerSubmitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAnswerSubmit() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitRepository.findAll().size();
        answerSubmit.setId(count.incrementAndGet());

        // Create the AnswerSubmit
        AnswerSubmitDTO answerSubmitDTO = answerSubmitMapper.toDto(answerSubmit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerSubmitMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnswerSubmit in the database
        List<AnswerSubmit> answerSubmitList = answerSubmitRepository.findAll();
        assertThat(answerSubmitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAnswerSubmit() throws Exception {
        // Initialize the database
        answerSubmitRepository.saveAndFlush(answerSubmit);

        int databaseSizeBeforeDelete = answerSubmitRepository.findAll().size();

        // Delete the answerSubmit
        restAnswerSubmitMockMvc
            .perform(delete(ENTITY_API_URL_ID, answerSubmit.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AnswerSubmit> answerSubmitList = answerSubmitRepository.findAll();
        assertThat(answerSubmitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
