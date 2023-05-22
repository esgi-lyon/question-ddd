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
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagId;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentTagIdRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagIdDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentTagIdMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionSentTagIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionSentTagIdResourceIT {

    private static final Long DEFAULT_TAG_ID = 1L;
    private static final Long UPDATED_TAG_ID = 2L;

    private static final String ENTITY_API_URL = "/api/question-sent-tag-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionSentTagIdRepository questionSentTagIdRepository;

    @Autowired
    private QuestionSentTagIdMapper questionSentTagIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionSentTagIdMockMvc;

    private QuestionSentTagId questionSentTagId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentTagId createEntity(EntityManager em) {
        QuestionSentTagId questionSentTagId = new QuestionSentTagId().tagId(DEFAULT_TAG_ID);
        return questionSentTagId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentTagId createUpdatedEntity(EntityManager em) {
        QuestionSentTagId questionSentTagId = new QuestionSentTagId().tagId(UPDATED_TAG_ID);
        return questionSentTagId;
    }

    @BeforeEach
    public void initTest() {
        questionSentTagId = createEntity(em);
    }

    @Test
    @Transactional
    void getAllQuestionSentTagIds() throws Exception {
        // Initialize the database
        questionSentTagIdRepository.saveAndFlush(questionSentTagId);

        // Get all the questionSentTagIdList
        restQuestionSentTagIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionSentTagId.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID.intValue())));
    }

    @Test
    @Transactional
    void getQuestionSentTagId() throws Exception {
        // Initialize the database
        questionSentTagIdRepository.saveAndFlush(questionSentTagId);

        // Get the questionSentTagId
        restQuestionSentTagIdMockMvc
            .perform(get(ENTITY_API_URL_ID, questionSentTagId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionSentTagId.getId().intValue()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingQuestionSentTagId() throws Exception {
        // Get the questionSentTagId
        restQuestionSentTagIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
