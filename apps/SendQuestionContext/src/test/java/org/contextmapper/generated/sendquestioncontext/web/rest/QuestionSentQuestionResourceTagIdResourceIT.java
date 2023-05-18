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
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentQuestionResourceTagId;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentQuestionResourceTagIdRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentQuestionResourceTagIdDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentQuestionResourceTagIdMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionSentQuestionResourceTagIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionSentQuestionResourceTagIdResourceIT {

    private static final Integer DEFAULT_TAG_ID = 1;
    private static final Integer UPDATED_TAG_ID = 2;

    private static final String ENTITY_API_URL = "/api/question-sent-question-resource-tag-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionSentQuestionResourceTagIdRepository questionSentQuestionResourceTagIdRepository;

    @Autowired
    private QuestionSentQuestionResourceTagIdMapper questionSentQuestionResourceTagIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionSentQuestionResourceTagIdMockMvc;

    private QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentQuestionResourceTagId createEntity(EntityManager em) {
        QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId = new QuestionSentQuestionResourceTagId().tagId(DEFAULT_TAG_ID);
        return questionSentQuestionResourceTagId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentQuestionResourceTagId createUpdatedEntity(EntityManager em) {
        QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId = new QuestionSentQuestionResourceTagId().tagId(UPDATED_TAG_ID);
        return questionSentQuestionResourceTagId;
    }

    @BeforeEach
    public void initTest() {
        questionSentQuestionResourceTagId = createEntity(em);
    }

    @Test
    @Transactional
    void getAllQuestionSentQuestionResourceTagIds() throws Exception {
        // Initialize the database
        questionSentQuestionResourceTagIdRepository.saveAndFlush(questionSentQuestionResourceTagId);

        // Get all the questionSentQuestionResourceTagIdList
        restQuestionSentQuestionResourceTagIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionSentQuestionResourceTagId.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID)));
    }

    @Test
    @Transactional
    void getQuestionSentQuestionResourceTagId() throws Exception {
        // Initialize the database
        questionSentQuestionResourceTagIdRepository.saveAndFlush(questionSentQuestionResourceTagId);

        // Get the questionSentQuestionResourceTagId
        restQuestionSentQuestionResourceTagIdMockMvc
            .perform(get(ENTITY_API_URL_ID, questionSentQuestionResourceTagId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionSentQuestionResourceTagId.getId().intValue()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID));
    }

    @Test
    @Transactional
    void getNonExistingQuestionSentQuestionResourceTagId() throws Exception {
        // Get the questionSentQuestionResourceTagId
        restQuestionSentQuestionResourceTagIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
