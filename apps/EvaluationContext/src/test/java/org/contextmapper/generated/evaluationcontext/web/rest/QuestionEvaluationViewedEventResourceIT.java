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
import org.contextmapper.generated.evaluationcontext.domain.QuestionEvaluationViewedEvent;
import org.contextmapper.generated.evaluationcontext.repository.QuestionEvaluationViewedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.QuestionEvaluationViewedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.QuestionEvaluationViewedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionEvaluationViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionEvaluationViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/question-evaluation-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionEvaluationViewedEventRepository questionEvaluationViewedEventRepository;

    @Autowired
    private QuestionEvaluationViewedEventMapper questionEvaluationViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionEvaluationViewedEventMockMvc;

    private QuestionEvaluationViewedEvent questionEvaluationViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionEvaluationViewedEvent createEntity(EntityManager em) {
        QuestionEvaluationViewedEvent questionEvaluationViewedEvent = new QuestionEvaluationViewedEvent();
        return questionEvaluationViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionEvaluationViewedEvent createUpdatedEntity(EntityManager em) {
        QuestionEvaluationViewedEvent questionEvaluationViewedEvent = new QuestionEvaluationViewedEvent();
        return questionEvaluationViewedEvent;
    }

    @BeforeEach
    public void initTest() {
        questionEvaluationViewedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllQuestionEvaluationViewedEvents() throws Exception {
        // Initialize the database
        questionEvaluationViewedEventRepository.saveAndFlush(questionEvaluationViewedEvent);

        // Get all the questionEvaluationViewedEventList
        restQuestionEvaluationViewedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionEvaluationViewedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getQuestionEvaluationViewedEvent() throws Exception {
        // Initialize the database
        questionEvaluationViewedEventRepository.saveAndFlush(questionEvaluationViewedEvent);

        // Get the questionEvaluationViewedEvent
        restQuestionEvaluationViewedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, questionEvaluationViewedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionEvaluationViewedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingQuestionEvaluationViewedEvent() throws Exception {
        // Get the questionEvaluationViewedEvent
        restQuestionEvaluationViewedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
