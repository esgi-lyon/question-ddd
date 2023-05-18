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
import org.contextmapper.generated.answercontext.domain.AnswerSubmitted;
import org.contextmapper.generated.answercontext.repository.AnswerSubmittedRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmittedDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerSubmittedMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AnswerSubmittedResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnswerSubmittedResourceIT {

    private static final String ENTITY_API_URL = "/api/answer-submitteds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnswerSubmittedRepository answerSubmittedRepository;

    @Autowired
    private AnswerSubmittedMapper answerSubmittedMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnswerSubmittedMockMvc;

    private AnswerSubmitted answerSubmitted;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSubmitted createEntity(EntityManager em) {
        AnswerSubmitted answerSubmitted = new AnswerSubmitted();
        return answerSubmitted;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSubmitted createUpdatedEntity(EntityManager em) {
        AnswerSubmitted answerSubmitted = new AnswerSubmitted();
        return answerSubmitted;
    }

    @BeforeEach
    public void initTest() {
        answerSubmitted = createEntity(em);
    }

    @Test
    @Transactional
    void createAnswerSubmitted() throws Exception {
        int databaseSizeBeforeCreate = answerSubmittedRepository.findAll().size();
        // Create the AnswerSubmitted
        AnswerSubmittedDTO answerSubmittedDTO = answerSubmittedMapper.toDto(answerSubmitted);
        restAnswerSubmittedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerSubmittedDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AnswerSubmitted in the database
        List<AnswerSubmitted> answerSubmittedList = answerSubmittedRepository.findAll();
        assertThat(answerSubmittedList).hasSize(databaseSizeBeforeCreate + 1);
        AnswerSubmitted testAnswerSubmitted = answerSubmittedList.get(answerSubmittedList.size() - 1);
    }

    @Test
    @Transactional
    void createAnswerSubmittedWithExistingId() throws Exception {
        // Create the AnswerSubmitted with an existing ID
        answerSubmitted.setId(1L);
        AnswerSubmittedDTO answerSubmittedDTO = answerSubmittedMapper.toDto(answerSubmitted);

        int databaseSizeBeforeCreate = answerSubmittedRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnswerSubmittedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerSubmittedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmitted in the database
        List<AnswerSubmitted> answerSubmittedList = answerSubmittedRepository.findAll();
        assertThat(answerSubmittedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAnswerSubmitteds() throws Exception {
        // Initialize the database
        answerSubmittedRepository.saveAndFlush(answerSubmitted);

        // Get all the answerSubmittedList
        restAnswerSubmittedMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answerSubmitted.getId().intValue())));
    }

    @Test
    @Transactional
    void getAnswerSubmitted() throws Exception {
        // Initialize the database
        answerSubmittedRepository.saveAndFlush(answerSubmitted);

        // Get the answerSubmitted
        restAnswerSubmittedMockMvc
            .perform(get(ENTITY_API_URL_ID, answerSubmitted.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(answerSubmitted.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAnswerSubmitted() throws Exception {
        // Get the answerSubmitted
        restAnswerSubmittedMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAnswerSubmitted() throws Exception {
        // Initialize the database
        answerSubmittedRepository.saveAndFlush(answerSubmitted);

        int databaseSizeBeforeUpdate = answerSubmittedRepository.findAll().size();

        // Update the answerSubmitted
        AnswerSubmitted updatedAnswerSubmitted = answerSubmittedRepository.findById(answerSubmitted.getId()).get();
        // Disconnect from session so that the updates on updatedAnswerSubmitted are not directly saved in db
        em.detach(updatedAnswerSubmitted);
        AnswerSubmittedDTO answerSubmittedDTO = answerSubmittedMapper.toDto(updatedAnswerSubmitted);

        restAnswerSubmittedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, answerSubmittedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmittedDTO))
            )
            .andExpect(status().isOk());

        // Validate the AnswerSubmitted in the database
        List<AnswerSubmitted> answerSubmittedList = answerSubmittedRepository.findAll();
        assertThat(answerSubmittedList).hasSize(databaseSizeBeforeUpdate);
        AnswerSubmitted testAnswerSubmitted = answerSubmittedList.get(answerSubmittedList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingAnswerSubmitted() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmittedRepository.findAll().size();
        answerSubmitted.setId(count.incrementAndGet());

        // Create the AnswerSubmitted
        AnswerSubmittedDTO answerSubmittedDTO = answerSubmittedMapper.toDto(answerSubmitted);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnswerSubmittedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, answerSubmittedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmittedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmitted in the database
        List<AnswerSubmitted> answerSubmittedList = answerSubmittedRepository.findAll();
        assertThat(answerSubmittedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAnswerSubmitted() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmittedRepository.findAll().size();
        answerSubmitted.setId(count.incrementAndGet());

        // Create the AnswerSubmitted
        AnswerSubmittedDTO answerSubmittedDTO = answerSubmittedMapper.toDto(answerSubmitted);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerSubmittedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmittedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmitted in the database
        List<AnswerSubmitted> answerSubmittedList = answerSubmittedRepository.findAll();
        assertThat(answerSubmittedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAnswerSubmitted() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmittedRepository.findAll().size();
        answerSubmitted.setId(count.incrementAndGet());

        // Create the AnswerSubmitted
        AnswerSubmittedDTO answerSubmittedDTO = answerSubmittedMapper.toDto(answerSubmitted);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerSubmittedMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerSubmittedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnswerSubmitted in the database
        List<AnswerSubmitted> answerSubmittedList = answerSubmittedRepository.findAll();
        assertThat(answerSubmittedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAnswerSubmittedWithPatch() throws Exception {
        // Initialize the database
        answerSubmittedRepository.saveAndFlush(answerSubmitted);

        int databaseSizeBeforeUpdate = answerSubmittedRepository.findAll().size();

        // Update the answerSubmitted using partial update
        AnswerSubmitted partialUpdatedAnswerSubmitted = new AnswerSubmitted();
        partialUpdatedAnswerSubmitted.setId(answerSubmitted.getId());

        restAnswerSubmittedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnswerSubmitted.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnswerSubmitted))
            )
            .andExpect(status().isOk());

        // Validate the AnswerSubmitted in the database
        List<AnswerSubmitted> answerSubmittedList = answerSubmittedRepository.findAll();
        assertThat(answerSubmittedList).hasSize(databaseSizeBeforeUpdate);
        AnswerSubmitted testAnswerSubmitted = answerSubmittedList.get(answerSubmittedList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateAnswerSubmittedWithPatch() throws Exception {
        // Initialize the database
        answerSubmittedRepository.saveAndFlush(answerSubmitted);

        int databaseSizeBeforeUpdate = answerSubmittedRepository.findAll().size();

        // Update the answerSubmitted using partial update
        AnswerSubmitted partialUpdatedAnswerSubmitted = new AnswerSubmitted();
        partialUpdatedAnswerSubmitted.setId(answerSubmitted.getId());

        restAnswerSubmittedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnswerSubmitted.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnswerSubmitted))
            )
            .andExpect(status().isOk());

        // Validate the AnswerSubmitted in the database
        List<AnswerSubmitted> answerSubmittedList = answerSubmittedRepository.findAll();
        assertThat(answerSubmittedList).hasSize(databaseSizeBeforeUpdate);
        AnswerSubmitted testAnswerSubmitted = answerSubmittedList.get(answerSubmittedList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingAnswerSubmitted() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmittedRepository.findAll().size();
        answerSubmitted.setId(count.incrementAndGet());

        // Create the AnswerSubmitted
        AnswerSubmittedDTO answerSubmittedDTO = answerSubmittedMapper.toDto(answerSubmitted);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnswerSubmittedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, answerSubmittedDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmittedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmitted in the database
        List<AnswerSubmitted> answerSubmittedList = answerSubmittedRepository.findAll();
        assertThat(answerSubmittedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAnswerSubmitted() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmittedRepository.findAll().size();
        answerSubmitted.setId(count.incrementAndGet());

        // Create the AnswerSubmitted
        AnswerSubmittedDTO answerSubmittedDTO = answerSubmittedMapper.toDto(answerSubmitted);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerSubmittedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmittedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmitted in the database
        List<AnswerSubmitted> answerSubmittedList = answerSubmittedRepository.findAll();
        assertThat(answerSubmittedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAnswerSubmitted() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmittedRepository.findAll().size();
        answerSubmitted.setId(count.incrementAndGet());

        // Create the AnswerSubmitted
        AnswerSubmittedDTO answerSubmittedDTO = answerSubmittedMapper.toDto(answerSubmitted);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerSubmittedMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmittedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnswerSubmitted in the database
        List<AnswerSubmitted> answerSubmittedList = answerSubmittedRepository.findAll();
        assertThat(answerSubmittedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAnswerSubmitted() throws Exception {
        // Initialize the database
        answerSubmittedRepository.saveAndFlush(answerSubmitted);

        int databaseSizeBeforeDelete = answerSubmittedRepository.findAll().size();

        // Delete the answerSubmitted
        restAnswerSubmittedMockMvc
            .perform(delete(ENTITY_API_URL_ID, answerSubmitted.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AnswerSubmitted> answerSubmittedList = answerSubmittedRepository.findAll();
        assertThat(answerSubmittedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
