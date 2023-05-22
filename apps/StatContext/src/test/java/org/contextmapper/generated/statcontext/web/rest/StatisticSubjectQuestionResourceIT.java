package org.contextmapper.generated.statcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.statcontext.IntegrationTest;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectQuestion;
import org.contextmapper.generated.statcontext.repository.StatisticSubjectQuestionRepository;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectQuestionDTO;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectQuestionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link StatisticSubjectQuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StatisticSubjectQuestionResourceIT {

    private static final Long DEFAULT_QUESTION_ID = 1L;
    private static final Long UPDATED_QUESTION_ID = 2L;

    private static final String ENTITY_API_URL = "/api/statistic-subject-questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StatisticSubjectQuestionRepository statisticSubjectQuestionRepository;

    @Autowired
    private StatisticSubjectQuestionMapper statisticSubjectQuestionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStatisticSubjectQuestionMockMvc;

    private StatisticSubjectQuestion statisticSubjectQuestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticSubjectQuestion createEntity(EntityManager em) {
        StatisticSubjectQuestion statisticSubjectQuestion = new StatisticSubjectQuestion().questionId(DEFAULT_QUESTION_ID);
        return statisticSubjectQuestion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticSubjectQuestion createUpdatedEntity(EntityManager em) {
        StatisticSubjectQuestion statisticSubjectQuestion = new StatisticSubjectQuestion().questionId(UPDATED_QUESTION_ID);
        return statisticSubjectQuestion;
    }

    @BeforeEach
    public void initTest() {
        statisticSubjectQuestion = createEntity(em);
    }

    @Test
    @Transactional
    void getAllStatisticSubjectQuestions() throws Exception {
        // Initialize the database
        statisticSubjectQuestionRepository.saveAndFlush(statisticSubjectQuestion);

        // Get all the statisticSubjectQuestionList
        restStatisticSubjectQuestionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statisticSubjectQuestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].questionId").value(hasItem(DEFAULT_QUESTION_ID.intValue())));
    }

    @Test
    @Transactional
    void getStatisticSubjectQuestion() throws Exception {
        // Initialize the database
        statisticSubjectQuestionRepository.saveAndFlush(statisticSubjectQuestion);

        // Get the statisticSubjectQuestion
        restStatisticSubjectQuestionMockMvc
            .perform(get(ENTITY_API_URL_ID, statisticSubjectQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(statisticSubjectQuestion.getId().intValue()))
            .andExpect(jsonPath("$.questionId").value(DEFAULT_QUESTION_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingStatisticSubjectQuestion() throws Exception {
        // Get the statisticSubjectQuestion
        restStatisticSubjectQuestionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
