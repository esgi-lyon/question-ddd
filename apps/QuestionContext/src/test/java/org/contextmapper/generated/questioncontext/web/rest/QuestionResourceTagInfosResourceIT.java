package org.contextmapper.generated.questioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.questioncontext.IntegrationTest;
import org.contextmapper.generated.questioncontext.domain.QuestionResourceTagInfos;
import org.contextmapper.generated.questioncontext.repository.QuestionResourceTagInfosRepository;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.questioncontext.service.mapper.QuestionResourceTagInfosMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionResourceTagInfosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionResourceTagInfosResourceIT {

    private static final Long DEFAULT_TAG_ID = 1L;
    private static final Long UPDATED_TAG_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/question-resource-tag-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionResourceTagInfosRepository questionResourceTagInfosRepository;

    @Autowired
    private QuestionResourceTagInfosMapper questionResourceTagInfosMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionResourceTagInfosMockMvc;

    private QuestionResourceTagInfos questionResourceTagInfos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionResourceTagInfos createEntity(EntityManager em) {
        QuestionResourceTagInfos questionResourceTagInfos = new QuestionResourceTagInfos().tagId(DEFAULT_TAG_ID).name(DEFAULT_NAME);
        return questionResourceTagInfos;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionResourceTagInfos createUpdatedEntity(EntityManager em) {
        QuestionResourceTagInfos questionResourceTagInfos = new QuestionResourceTagInfos().tagId(UPDATED_TAG_ID).name(UPDATED_NAME);
        return questionResourceTagInfos;
    }

    @BeforeEach
    public void initTest() {
        questionResourceTagInfos = createEntity(em);
    }

    @Test
    @Transactional
    void getAllQuestionResourceTagInfos() throws Exception {
        // Initialize the database
        questionResourceTagInfosRepository.saveAndFlush(questionResourceTagInfos);

        // Get all the questionResourceTagInfosList
        restQuestionResourceTagInfosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionResourceTagInfos.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getQuestionResourceTagInfos() throws Exception {
        // Initialize the database
        questionResourceTagInfosRepository.saveAndFlush(questionResourceTagInfos);

        // Get the questionResourceTagInfos
        restQuestionResourceTagInfosMockMvc
            .perform(get(ENTITY_API_URL_ID, questionResourceTagInfos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionResourceTagInfos.getId().intValue()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingQuestionResourceTagInfos() throws Exception {
        // Get the questionResourceTagInfos
        restQuestionResourceTagInfosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
