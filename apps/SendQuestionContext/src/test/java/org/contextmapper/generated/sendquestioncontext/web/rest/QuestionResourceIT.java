package org.contextmapper.generated.sendquestioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.sendquestioncontext.IntegrationTest;
import org.contextmapper.generated.sendquestioncontext.domain.Question;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionResourceIT {

    private static final String ENTITY_API_URL = "/api/questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionMockMvc;

    private Question question;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Question createEntity(EntityManager em) {
        Question question = new Question();
        return question;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Question createUpdatedEntity(EntityManager em) {
        Question question = new Question();
        return question;
    }

    @BeforeEach
    public void initTest() {
        question = createEntity(em);
    }

    @Test
    @Transactional
    void createQuestion() throws Exception {
        int databaseSizeBeforeCreate = questionRepository.findAll().size();
        // Create the Question
        QuestionDTO questionDTO = questionMapper.toDto(question);
        restQuestionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isCreated());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeCreate + 1);
        Question testQuestion = questionList.get(questionList.size() - 1);
    }

    @Test
    @Transactional
    void createQuestionWithExistingId() throws Exception {
        // Create the Question with an existing ID
        question.setId(1L);
        QuestionDTO questionDTO = questionMapper.toDto(question);

        int databaseSizeBeforeCreate = questionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQuestions() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        // Get all the questionList
        restQuestionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(question.getId().intValue())));
    }

    @Test
    @Transactional
    void getQuestion() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        // Get the question
        restQuestionMockMvc
            .perform(get(ENTITY_API_URL_ID, question.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(question.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingQuestion() throws Exception {
        // Get the question
        restQuestionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQuestion() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        int databaseSizeBeforeUpdate = questionRepository.findAll().size();

        // Update the question
        Question updatedQuestion = questionRepository.findById(question.getId()).get();
        // Disconnect from session so that the updates on updatedQuestion are not directly saved in db
        em.detach(updatedQuestion);
        QuestionDTO questionDTO = questionMapper.toDto(updatedQuestion);

        restQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
        Question testQuestion = questionList.get(questionList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingQuestion() throws Exception {
        int databaseSizeBeforeUpdate = questionRepository.findAll().size();
        question.setId(count.incrementAndGet());

        // Create the Question
        QuestionDTO questionDTO = questionMapper.toDto(question);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQuestion() throws Exception {
        int databaseSizeBeforeUpdate = questionRepository.findAll().size();
        question.setId(count.incrementAndGet());

        // Create the Question
        QuestionDTO questionDTO = questionMapper.toDto(question);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQuestion() throws Exception {
        int databaseSizeBeforeUpdate = questionRepository.findAll().size();
        question.setId(count.incrementAndGet());

        // Create the Question
        QuestionDTO questionDTO = questionMapper.toDto(question);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQuestionWithPatch() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        int databaseSizeBeforeUpdate = questionRepository.findAll().size();

        // Update the question using partial update
        Question partialUpdatedQuestion = new Question();
        partialUpdatedQuestion.setId(question.getId());

        restQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestion))
            )
            .andExpect(status().isOk());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
        Question testQuestion = questionList.get(questionList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateQuestionWithPatch() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        int databaseSizeBeforeUpdate = questionRepository.findAll().size();

        // Update the question using partial update
        Question partialUpdatedQuestion = new Question();
        partialUpdatedQuestion.setId(question.getId());

        restQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestion))
            )
            .andExpect(status().isOk());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
        Question testQuestion = questionList.get(questionList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingQuestion() throws Exception {
        int databaseSizeBeforeUpdate = questionRepository.findAll().size();
        question.setId(count.incrementAndGet());

        // Create the Question
        QuestionDTO questionDTO = questionMapper.toDto(question);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, questionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQuestion() throws Exception {
        int databaseSizeBeforeUpdate = questionRepository.findAll().size();
        question.setId(count.incrementAndGet());

        // Create the Question
        QuestionDTO questionDTO = questionMapper.toDto(question);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQuestion() throws Exception {
        int databaseSizeBeforeUpdate = questionRepository.findAll().size();
        question.setId(count.incrementAndGet());

        // Create the Question
        QuestionDTO questionDTO = questionMapper.toDto(question);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(questionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQuestion() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        int databaseSizeBeforeDelete = questionRepository.findAll().size();

        // Delete the question
        restQuestionMockMvc
            .perform(delete(ENTITY_API_URL_ID, question.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
