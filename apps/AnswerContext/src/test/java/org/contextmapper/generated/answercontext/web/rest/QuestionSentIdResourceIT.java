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
import org.contextmapper.generated.answercontext.domain.QuestionSentId;
import org.contextmapper.generated.answercontext.repository.QuestionSentIdRepository;
import org.contextmapper.generated.answercontext.service.dto.QuestionSentIdDTO;
import org.contextmapper.generated.answercontext.service.mapper.QuestionSentIdMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionSentIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionSentIdResourceIT {

    private static final Long DEFAULT_QUESTION_ID = 1L;
    private static final Long UPDATED_QUESTION_ID = 2L;

    private static final String ENTITY_API_URL = "/api/question-sent-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionSentIdRepository questionSentIdRepository;

    @Autowired
    private QuestionSentIdMapper questionSentIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionSentIdMockMvc;

    private QuestionSentId questionSentId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentId createEntity(EntityManager em) {
        QuestionSentId questionSentId = new QuestionSentId().questionId(DEFAULT_QUESTION_ID);
        return questionSentId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentId createUpdatedEntity(EntityManager em) {
        QuestionSentId questionSentId = new QuestionSentId().questionId(UPDATED_QUESTION_ID);
        return questionSentId;
    }

    @BeforeEach
    public void initTest() {
        questionSentId = createEntity(em);
    }

    @Test
    @Transactional
    void getAllQuestionSentIds() throws Exception {
        // Initialize the database
        questionSentIdRepository.saveAndFlush(questionSentId);

        // Get all the questionSentIdList
        restQuestionSentIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionSentId.getId().intValue())))
            .andExpect(jsonPath("$.[*].questionId").value(hasItem(DEFAULT_QUESTION_ID.intValue())));
    }

    @Test
    @Transactional
    void getQuestionSentId() throws Exception {
        // Initialize the database
        questionSentIdRepository.saveAndFlush(questionSentId);

        // Get the questionSentId
        restQuestionSentIdMockMvc
            .perform(get(ENTITY_API_URL_ID, questionSentId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionSentId.getId().intValue()))
            .andExpect(jsonPath("$.questionId").value(DEFAULT_QUESTION_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingQuestionSentId() throws Exception {
        // Get the questionSentId
        restQuestionSentIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
