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
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfos;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentTagInfosRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagInfosDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentTagInfosMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionSentTagInfosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionSentTagInfosResourceIT {

    private static final Integer DEFAULT_TAG_ID = 1;
    private static final Integer UPDATED_TAG_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/question-sent-tag-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionSentTagInfosRepository questionSentTagInfosRepository;

    @Autowired
    private QuestionSentTagInfosMapper questionSentTagInfosMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionSentTagInfosMockMvc;

    private QuestionSentTagInfos questionSentTagInfos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentTagInfos createEntity(EntityManager em) {
        QuestionSentTagInfos questionSentTagInfos = new QuestionSentTagInfos().tagId(DEFAULT_TAG_ID).name(DEFAULT_NAME);
        return questionSentTagInfos;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentTagInfos createUpdatedEntity(EntityManager em) {
        QuestionSentTagInfos questionSentTagInfos = new QuestionSentTagInfos().tagId(UPDATED_TAG_ID).name(UPDATED_NAME);
        return questionSentTagInfos;
    }

    @BeforeEach
    public void initTest() {
        questionSentTagInfos = createEntity(em);
    }

    @Test
    @Transactional
    void getAllQuestionSentTagInfos() throws Exception {
        // Initialize the database
        questionSentTagInfosRepository.saveAndFlush(questionSentTagInfos);

        // Get all the questionSentTagInfosList
        restQuestionSentTagInfosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionSentTagInfos.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getQuestionSentTagInfos() throws Exception {
        // Initialize the database
        questionSentTagInfosRepository.saveAndFlush(questionSentTagInfos);

        // Get the questionSentTagInfos
        restQuestionSentTagInfosMockMvc
            .perform(get(ENTITY_API_URL_ID, questionSentTagInfos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionSentTagInfos.getId().intValue()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingQuestionSentTagInfos() throws Exception {
        // Get the questionSentTagInfos
        restQuestionSentTagInfosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
