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
import org.contextmapper.generated.evaluationcontext.domain.CheckAnswer;
import org.contextmapper.generated.evaluationcontext.repository.CheckAnswerRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.CheckAnswerDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.CheckAnswerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CheckAnswerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CheckAnswerResourceIT {

    private static final String ENTITY_API_URL = "/api/check-answers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CheckAnswerRepository checkAnswerRepository;

    @Autowired
    private CheckAnswerMapper checkAnswerMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCheckAnswerMockMvc;

    private CheckAnswer checkAnswer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CheckAnswer createEntity(EntityManager em) {
        CheckAnswer checkAnswer = new CheckAnswer();
        return checkAnswer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CheckAnswer createUpdatedEntity(EntityManager em) {
        CheckAnswer checkAnswer = new CheckAnswer();
        return checkAnswer;
    }

    @BeforeEach
    public void initTest() {
        checkAnswer = createEntity(em);
    }

    @Test
    @Transactional
    void createCheckAnswer() throws Exception {
        int databaseSizeBeforeCreate = checkAnswerRepository.findAll().size();
        // Create the CheckAnswer
        CheckAnswerDTO checkAnswerDTO = checkAnswerMapper.toDto(checkAnswer);
        restCheckAnswerMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(checkAnswerDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CheckAnswer in the database
        List<CheckAnswer> checkAnswerList = checkAnswerRepository.findAll();
        assertThat(checkAnswerList).hasSize(databaseSizeBeforeCreate + 1);
        CheckAnswer testCheckAnswer = checkAnswerList.get(checkAnswerList.size() - 1);
    }

    @Test
    @Transactional
    void createCheckAnswerWithExistingId() throws Exception {
        // Create the CheckAnswer with an existing ID
        checkAnswer.setId(1L);
        CheckAnswerDTO checkAnswerDTO = checkAnswerMapper.toDto(checkAnswer);

        int databaseSizeBeforeCreate = checkAnswerRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCheckAnswerMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(checkAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckAnswer in the database
        List<CheckAnswer> checkAnswerList = checkAnswerRepository.findAll();
        assertThat(checkAnswerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCheckAnswers() throws Exception {
        // Initialize the database
        checkAnswerRepository.saveAndFlush(checkAnswer);

        // Get all the checkAnswerList
        restCheckAnswerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(checkAnswer.getId().intValue())));
    }

    @Test
    @Transactional
    void getCheckAnswer() throws Exception {
        // Initialize the database
        checkAnswerRepository.saveAndFlush(checkAnswer);

        // Get the checkAnswer
        restCheckAnswerMockMvc
            .perform(get(ENTITY_API_URL_ID, checkAnswer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(checkAnswer.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCheckAnswer() throws Exception {
        // Get the checkAnswer
        restCheckAnswerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCheckAnswer() throws Exception {
        // Initialize the database
        checkAnswerRepository.saveAndFlush(checkAnswer);

        int databaseSizeBeforeUpdate = checkAnswerRepository.findAll().size();

        // Update the checkAnswer
        CheckAnswer updatedCheckAnswer = checkAnswerRepository.findById(checkAnswer.getId()).get();
        // Disconnect from session so that the updates on updatedCheckAnswer are not directly saved in db
        em.detach(updatedCheckAnswer);
        CheckAnswerDTO checkAnswerDTO = checkAnswerMapper.toDto(updatedCheckAnswer);

        restCheckAnswerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, checkAnswerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(checkAnswerDTO))
            )
            .andExpect(status().isOk());

        // Validate the CheckAnswer in the database
        List<CheckAnswer> checkAnswerList = checkAnswerRepository.findAll();
        assertThat(checkAnswerList).hasSize(databaseSizeBeforeUpdate);
        CheckAnswer testCheckAnswer = checkAnswerList.get(checkAnswerList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCheckAnswer() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerRepository.findAll().size();
        checkAnswer.setId(count.incrementAndGet());

        // Create the CheckAnswer
        CheckAnswerDTO checkAnswerDTO = checkAnswerMapper.toDto(checkAnswer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCheckAnswerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, checkAnswerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(checkAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckAnswer in the database
        List<CheckAnswer> checkAnswerList = checkAnswerRepository.findAll();
        assertThat(checkAnswerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCheckAnswer() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerRepository.findAll().size();
        checkAnswer.setId(count.incrementAndGet());

        // Create the CheckAnswer
        CheckAnswerDTO checkAnswerDTO = checkAnswerMapper.toDto(checkAnswer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckAnswerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(checkAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckAnswer in the database
        List<CheckAnswer> checkAnswerList = checkAnswerRepository.findAll();
        assertThat(checkAnswerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCheckAnswer() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerRepository.findAll().size();
        checkAnswer.setId(count.incrementAndGet());

        // Create the CheckAnswer
        CheckAnswerDTO checkAnswerDTO = checkAnswerMapper.toDto(checkAnswer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckAnswerMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(checkAnswerDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CheckAnswer in the database
        List<CheckAnswer> checkAnswerList = checkAnswerRepository.findAll();
        assertThat(checkAnswerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCheckAnswerWithPatch() throws Exception {
        // Initialize the database
        checkAnswerRepository.saveAndFlush(checkAnswer);

        int databaseSizeBeforeUpdate = checkAnswerRepository.findAll().size();

        // Update the checkAnswer using partial update
        CheckAnswer partialUpdatedCheckAnswer = new CheckAnswer();
        partialUpdatedCheckAnswer.setId(checkAnswer.getId());

        restCheckAnswerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCheckAnswer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCheckAnswer))
            )
            .andExpect(status().isOk());

        // Validate the CheckAnswer in the database
        List<CheckAnswer> checkAnswerList = checkAnswerRepository.findAll();
        assertThat(checkAnswerList).hasSize(databaseSizeBeforeUpdate);
        CheckAnswer testCheckAnswer = checkAnswerList.get(checkAnswerList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCheckAnswerWithPatch() throws Exception {
        // Initialize the database
        checkAnswerRepository.saveAndFlush(checkAnswer);

        int databaseSizeBeforeUpdate = checkAnswerRepository.findAll().size();

        // Update the checkAnswer using partial update
        CheckAnswer partialUpdatedCheckAnswer = new CheckAnswer();
        partialUpdatedCheckAnswer.setId(checkAnswer.getId());

        restCheckAnswerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCheckAnswer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCheckAnswer))
            )
            .andExpect(status().isOk());

        // Validate the CheckAnswer in the database
        List<CheckAnswer> checkAnswerList = checkAnswerRepository.findAll();
        assertThat(checkAnswerList).hasSize(databaseSizeBeforeUpdate);
        CheckAnswer testCheckAnswer = checkAnswerList.get(checkAnswerList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCheckAnswer() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerRepository.findAll().size();
        checkAnswer.setId(count.incrementAndGet());

        // Create the CheckAnswer
        CheckAnswerDTO checkAnswerDTO = checkAnswerMapper.toDto(checkAnswer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCheckAnswerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, checkAnswerDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(checkAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckAnswer in the database
        List<CheckAnswer> checkAnswerList = checkAnswerRepository.findAll();
        assertThat(checkAnswerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCheckAnswer() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerRepository.findAll().size();
        checkAnswer.setId(count.incrementAndGet());

        // Create the CheckAnswer
        CheckAnswerDTO checkAnswerDTO = checkAnswerMapper.toDto(checkAnswer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckAnswerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(checkAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckAnswer in the database
        List<CheckAnswer> checkAnswerList = checkAnswerRepository.findAll();
        assertThat(checkAnswerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCheckAnswer() throws Exception {
        int databaseSizeBeforeUpdate = checkAnswerRepository.findAll().size();
        checkAnswer.setId(count.incrementAndGet());

        // Create the CheckAnswer
        CheckAnswerDTO checkAnswerDTO = checkAnswerMapper.toDto(checkAnswer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckAnswerMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(checkAnswerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CheckAnswer in the database
        List<CheckAnswer> checkAnswerList = checkAnswerRepository.findAll();
        assertThat(checkAnswerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCheckAnswer() throws Exception {
        // Initialize the database
        checkAnswerRepository.saveAndFlush(checkAnswer);

        int databaseSizeBeforeDelete = checkAnswerRepository.findAll().size();

        // Delete the checkAnswer
        restCheckAnswerMockMvc
            .perform(delete(ENTITY_API_URL_ID, checkAnswer.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CheckAnswer> checkAnswerList = checkAnswerRepository.findAll();
        assertThat(checkAnswerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
