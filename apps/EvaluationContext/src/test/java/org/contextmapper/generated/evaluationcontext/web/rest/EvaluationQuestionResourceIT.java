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
import org.contextmapper.generated.evaluationcontext.domain.EvaluationQuestion;
import org.contextmapper.generated.evaluationcontext.repository.EvaluationQuestionRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationQuestionDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationQuestionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EvaluationQuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EvaluationQuestionResourceIT {

    private static final Long DEFAULT_QUESTION_ID = 1L;
    private static final Long UPDATED_QUESTION_ID = 2L;

    private static final String ENTITY_API_URL = "/api/evaluation-questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvaluationQuestionRepository evaluationQuestionRepository;

    @Autowired
    private EvaluationQuestionMapper evaluationQuestionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEvaluationQuestionMockMvc;

    private EvaluationQuestion evaluationQuestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationQuestion createEntity(EntityManager em) {
        EvaluationQuestion evaluationQuestion = new EvaluationQuestion().questionId(DEFAULT_QUESTION_ID);
        return evaluationQuestion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationQuestion createUpdatedEntity(EntityManager em) {
        EvaluationQuestion evaluationQuestion = new EvaluationQuestion().questionId(UPDATED_QUESTION_ID);
        return evaluationQuestion;
    }

    @BeforeEach
    public void initTest() {
        evaluationQuestion = createEntity(em);
    }

    @Test
    @Transactional
    void getAllEvaluationQuestions() throws Exception {
        // Initialize the database
        evaluationQuestionRepository.saveAndFlush(evaluationQuestion);

        // Get all the evaluationQuestionList
        restEvaluationQuestionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evaluationQuestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].questionId").value(hasItem(DEFAULT_QUESTION_ID.intValue())));
    }

    @Test
    @Transactional
    void getEvaluationQuestion() throws Exception {
        // Initialize the database
        evaluationQuestionRepository.saveAndFlush(evaluationQuestion);

        // Get the evaluationQuestion
        restEvaluationQuestionMockMvc
            .perform(get(ENTITY_API_URL_ID, evaluationQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(evaluationQuestion.getId().intValue()))
            .andExpect(jsonPath("$.questionId").value(DEFAULT_QUESTION_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEvaluationQuestion() throws Exception {
        // Get the evaluationQuestion
        restEvaluationQuestionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
