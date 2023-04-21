package org.contextmapper.generated.sendquestioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.sendquestioncontext.IntegrationTest;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionSentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionSentResourceIT {

    private static final LocalDate DEFAULT_SENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_VIEWED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VIEWED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ANSWERED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ANSWERED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/question-sents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionSentRepository questionSentRepository;

    @Autowired
    private QuestionSentMapper questionSentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionSentMockMvc;

    private QuestionSent questionSent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSent createEntity(EntityManager em) {
        QuestionSent questionSent = new QuestionSent()
            .sentDate(DEFAULT_SENT_DATE)
            .viewedDate(DEFAULT_VIEWED_DATE)
            .answeredDate(DEFAULT_ANSWERED_DATE);
        return questionSent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSent createUpdatedEntity(EntityManager em) {
        QuestionSent questionSent = new QuestionSent()
            .sentDate(UPDATED_SENT_DATE)
            .viewedDate(UPDATED_VIEWED_DATE)
            .answeredDate(UPDATED_ANSWERED_DATE);
        return questionSent;
    }

    @BeforeEach
    public void initTest() {
        questionSent = createEntity(em);
    }

    @Test
    @Transactional
    void createQuestionSent() throws Exception {
        int databaseSizeBeforeCreate = questionSentRepository.findAll().size();
        // Create the QuestionSent
        QuestionSentDTO questionSentDTO = questionSentMapper.toDto(questionSent);
        restQuestionSentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionSentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the QuestionSent in the database
        List<QuestionSent> questionSentList = questionSentRepository.findAll();
        assertThat(questionSentList).hasSize(databaseSizeBeforeCreate + 1);
        QuestionSent testQuestionSent = questionSentList.get(questionSentList.size() - 1);
        assertThat(testQuestionSent.getSentDate()).isEqualTo(DEFAULT_SENT_DATE);
        assertThat(testQuestionSent.getViewedDate()).isEqualTo(DEFAULT_VIEWED_DATE);
        assertThat(testQuestionSent.getAnsweredDate()).isEqualTo(DEFAULT_ANSWERED_DATE);
    }

    @Test
    @Transactional
    void createQuestionSentWithExistingId() throws Exception {
        // Create the QuestionSent with an existing ID
        questionSent.setId(1L);
        QuestionSentDTO questionSentDTO = questionSentMapper.toDto(questionSent);

        int databaseSizeBeforeCreate = questionSentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionSentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionSentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionSent in the database
        List<QuestionSent> questionSentList = questionSentRepository.findAll();
        assertThat(questionSentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQuestionSents() throws Exception {
        // Initialize the database
        questionSentRepository.saveAndFlush(questionSent);

        // Get all the questionSentList
        restQuestionSentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionSent.getId().intValue())))
            .andExpect(jsonPath("$.[*].sentDate").value(hasItem(DEFAULT_SENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].viewedDate").value(hasItem(DEFAULT_VIEWED_DATE.toString())))
            .andExpect(jsonPath("$.[*].answeredDate").value(hasItem(DEFAULT_ANSWERED_DATE.toString())));
    }

    @Test
    @Transactional
    void getQuestionSent() throws Exception {
        // Initialize the database
        questionSentRepository.saveAndFlush(questionSent);

        // Get the questionSent
        restQuestionSentMockMvc
            .perform(get(ENTITY_API_URL_ID, questionSent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionSent.getId().intValue()))
            .andExpect(jsonPath("$.sentDate").value(DEFAULT_SENT_DATE.toString()))
            .andExpect(jsonPath("$.viewedDate").value(DEFAULT_VIEWED_DATE.toString()))
            .andExpect(jsonPath("$.answeredDate").value(DEFAULT_ANSWERED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingQuestionSent() throws Exception {
        // Get the questionSent
        restQuestionSentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQuestionSent() throws Exception {
        // Initialize the database
        questionSentRepository.saveAndFlush(questionSent);

        int databaseSizeBeforeUpdate = questionSentRepository.findAll().size();

        // Update the questionSent
        QuestionSent updatedQuestionSent = questionSentRepository.findById(questionSent.getId()).get();
        // Disconnect from session so that the updates on updatedQuestionSent are not directly saved in db
        em.detach(updatedQuestionSent);
        updatedQuestionSent.sentDate(UPDATED_SENT_DATE).viewedDate(UPDATED_VIEWED_DATE).answeredDate(UPDATED_ANSWERED_DATE);
        QuestionSentDTO questionSentDTO = questionSentMapper.toDto(updatedQuestionSent);

        restQuestionSentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionSentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionSentDTO))
            )
            .andExpect(status().isOk());

        // Validate the QuestionSent in the database
        List<QuestionSent> questionSentList = questionSentRepository.findAll();
        assertThat(questionSentList).hasSize(databaseSizeBeforeUpdate);
        QuestionSent testQuestionSent = questionSentList.get(questionSentList.size() - 1);
        assertThat(testQuestionSent.getSentDate()).isEqualTo(UPDATED_SENT_DATE);
        assertThat(testQuestionSent.getViewedDate()).isEqualTo(UPDATED_VIEWED_DATE);
        assertThat(testQuestionSent.getAnsweredDate()).isEqualTo(UPDATED_ANSWERED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingQuestionSent() throws Exception {
        int databaseSizeBeforeUpdate = questionSentRepository.findAll().size();
        questionSent.setId(count.incrementAndGet());

        // Create the QuestionSent
        QuestionSentDTO questionSentDTO = questionSentMapper.toDto(questionSent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionSentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionSentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionSentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionSent in the database
        List<QuestionSent> questionSentList = questionSentRepository.findAll();
        assertThat(questionSentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQuestionSent() throws Exception {
        int databaseSizeBeforeUpdate = questionSentRepository.findAll().size();
        questionSent.setId(count.incrementAndGet());

        // Create the QuestionSent
        QuestionSentDTO questionSentDTO = questionSentMapper.toDto(questionSent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionSentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionSentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionSent in the database
        List<QuestionSent> questionSentList = questionSentRepository.findAll();
        assertThat(questionSentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQuestionSent() throws Exception {
        int databaseSizeBeforeUpdate = questionSentRepository.findAll().size();
        questionSent.setId(count.incrementAndGet());

        // Create the QuestionSent
        QuestionSentDTO questionSentDTO = questionSentMapper.toDto(questionSent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionSentMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionSentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuestionSent in the database
        List<QuestionSent> questionSentList = questionSentRepository.findAll();
        assertThat(questionSentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQuestionSentWithPatch() throws Exception {
        // Initialize the database
        questionSentRepository.saveAndFlush(questionSent);

        int databaseSizeBeforeUpdate = questionSentRepository.findAll().size();

        // Update the questionSent using partial update
        QuestionSent partialUpdatedQuestionSent = new QuestionSent();
        partialUpdatedQuestionSent.setId(questionSent.getId());

        partialUpdatedQuestionSent.answeredDate(UPDATED_ANSWERED_DATE);

        restQuestionSentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestionSent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestionSent))
            )
            .andExpect(status().isOk());

        // Validate the QuestionSent in the database
        List<QuestionSent> questionSentList = questionSentRepository.findAll();
        assertThat(questionSentList).hasSize(databaseSizeBeforeUpdate);
        QuestionSent testQuestionSent = questionSentList.get(questionSentList.size() - 1);
        assertThat(testQuestionSent.getSentDate()).isEqualTo(DEFAULT_SENT_DATE);
        assertThat(testQuestionSent.getViewedDate()).isEqualTo(DEFAULT_VIEWED_DATE);
        assertThat(testQuestionSent.getAnsweredDate()).isEqualTo(UPDATED_ANSWERED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateQuestionSentWithPatch() throws Exception {
        // Initialize the database
        questionSentRepository.saveAndFlush(questionSent);

        int databaseSizeBeforeUpdate = questionSentRepository.findAll().size();

        // Update the questionSent using partial update
        QuestionSent partialUpdatedQuestionSent = new QuestionSent();
        partialUpdatedQuestionSent.setId(questionSent.getId());

        partialUpdatedQuestionSent.sentDate(UPDATED_SENT_DATE).viewedDate(UPDATED_VIEWED_DATE).answeredDate(UPDATED_ANSWERED_DATE);

        restQuestionSentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestionSent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestionSent))
            )
            .andExpect(status().isOk());

        // Validate the QuestionSent in the database
        List<QuestionSent> questionSentList = questionSentRepository.findAll();
        assertThat(questionSentList).hasSize(databaseSizeBeforeUpdate);
        QuestionSent testQuestionSent = questionSentList.get(questionSentList.size() - 1);
        assertThat(testQuestionSent.getSentDate()).isEqualTo(UPDATED_SENT_DATE);
        assertThat(testQuestionSent.getViewedDate()).isEqualTo(UPDATED_VIEWED_DATE);
        assertThat(testQuestionSent.getAnsweredDate()).isEqualTo(UPDATED_ANSWERED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingQuestionSent() throws Exception {
        int databaseSizeBeforeUpdate = questionSentRepository.findAll().size();
        questionSent.setId(count.incrementAndGet());

        // Create the QuestionSent
        QuestionSentDTO questionSentDTO = questionSentMapper.toDto(questionSent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionSentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, questionSentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionSentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionSent in the database
        List<QuestionSent> questionSentList = questionSentRepository.findAll();
        assertThat(questionSentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQuestionSent() throws Exception {
        int databaseSizeBeforeUpdate = questionSentRepository.findAll().size();
        questionSent.setId(count.incrementAndGet());

        // Create the QuestionSent
        QuestionSentDTO questionSentDTO = questionSentMapper.toDto(questionSent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionSentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionSentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionSent in the database
        List<QuestionSent> questionSentList = questionSentRepository.findAll();
        assertThat(questionSentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQuestionSent() throws Exception {
        int databaseSizeBeforeUpdate = questionSentRepository.findAll().size();
        questionSent.setId(count.incrementAndGet());

        // Create the QuestionSent
        QuestionSentDTO questionSentDTO = questionSentMapper.toDto(questionSent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionSentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionSentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuestionSent in the database
        List<QuestionSent> questionSentList = questionSentRepository.findAll();
        assertThat(questionSentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQuestionSent() throws Exception {
        // Initialize the database
        questionSentRepository.saveAndFlush(questionSent);

        int databaseSizeBeforeDelete = questionSentRepository.findAll().size();

        // Delete the questionSent
        restQuestionSentMockMvc
            .perform(delete(ENTITY_API_URL_ID, questionSent.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QuestionSent> questionSentList = questionSentRepository.findAll();
        assertThat(questionSentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
