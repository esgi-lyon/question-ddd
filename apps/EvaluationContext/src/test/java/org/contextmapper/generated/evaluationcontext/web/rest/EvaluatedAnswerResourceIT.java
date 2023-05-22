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
import org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer;
import org.contextmapper.generated.evaluationcontext.repository.EvaluatedAnswerRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluatedAnswerDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluatedAnswerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EvaluatedAnswerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EvaluatedAnswerResourceIT {

    private static final Long DEFAULT_ANSWER_ID = 1L;
    private static final Long UPDATED_ANSWER_ID = 2L;

    private static final String ENTITY_API_URL = "/api/evaluated-answers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvaluatedAnswerRepository evaluatedAnswerRepository;

    @Autowired
    private EvaluatedAnswerMapper evaluatedAnswerMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEvaluatedAnswerMockMvc;

    private EvaluatedAnswer evaluatedAnswer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluatedAnswer createEntity(EntityManager em) {
        EvaluatedAnswer evaluatedAnswer = new EvaluatedAnswer().answerId(DEFAULT_ANSWER_ID);
        return evaluatedAnswer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluatedAnswer createUpdatedEntity(EntityManager em) {
        EvaluatedAnswer evaluatedAnswer = new EvaluatedAnswer().answerId(UPDATED_ANSWER_ID);
        return evaluatedAnswer;
    }

    @BeforeEach
    public void initTest() {
        evaluatedAnswer = createEntity(em);
    }

    @Test
    @Transactional
    void getAllEvaluatedAnswers() throws Exception {
        // Initialize the database
        evaluatedAnswerRepository.saveAndFlush(evaluatedAnswer);

        // Get all the evaluatedAnswerList
        restEvaluatedAnswerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evaluatedAnswer.getId().intValue())))
            .andExpect(jsonPath("$.[*].answerId").value(hasItem(DEFAULT_ANSWER_ID.intValue())));
    }

    @Test
    @Transactional
    void getEvaluatedAnswer() throws Exception {
        // Initialize the database
        evaluatedAnswerRepository.saveAndFlush(evaluatedAnswer);

        // Get the evaluatedAnswer
        restEvaluatedAnswerMockMvc
            .perform(get(ENTITY_API_URL_ID, evaluatedAnswer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(evaluatedAnswer.getId().intValue()))
            .andExpect(jsonPath("$.answerId").value(DEFAULT_ANSWER_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEvaluatedAnswer() throws Exception {
        // Get the evaluatedAnswer
        restEvaluatedAnswerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
