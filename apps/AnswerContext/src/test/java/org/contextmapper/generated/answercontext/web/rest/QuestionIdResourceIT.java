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
import org.contextmapper.generated.answercontext.domain.QuestionId;
import org.contextmapper.generated.answercontext.repository.QuestionIdRepository;
import org.contextmapper.generated.answercontext.service.dto.QuestionIdDTO;
import org.contextmapper.generated.answercontext.service.mapper.QuestionIdMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionIdResourceIT {

    private static final Long DEFAULT_QUESTION_ID = 1L;
    private static final Long UPDATED_QUESTION_ID = 2L;

    private static final String ENTITY_API_URL = "/api/question-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionIdRepository questionIdRepository;

    @Autowired
    private QuestionIdMapper questionIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionIdMockMvc;

    private QuestionId questionId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionId createEntity(EntityManager em) {
        QuestionId questionId = new QuestionId().questionId(DEFAULT_QUESTION_ID);
        return questionId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionId createUpdatedEntity(EntityManager em) {
        QuestionId questionId = new QuestionId().questionId(UPDATED_QUESTION_ID);
        return questionId;
    }

    @BeforeEach
    public void initTest() {
        questionId = createEntity(em);
    }

    @Test
    @Transactional
    void getAllQuestionIds() throws Exception {
        // Initialize the database
        questionIdRepository.saveAndFlush(questionId);

        // Get all the questionIdList
        restQuestionIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionId.getId().intValue())))
            .andExpect(jsonPath("$.[*].questionId").value(hasItem(DEFAULT_QUESTION_ID.intValue())));
    }

    @Test
    @Transactional
    void getQuestionId() throws Exception {
        // Initialize the database
        questionIdRepository.saveAndFlush(questionId);

        // Get the questionId
        restQuestionIdMockMvc
            .perform(get(ENTITY_API_URL_ID, questionId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionId.getId().intValue()))
            .andExpect(jsonPath("$.questionId").value(DEFAULT_QUESTION_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingQuestionId() throws Exception {
        // Get the questionId
        restQuestionIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
