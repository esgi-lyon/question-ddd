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
import org.contextmapper.generated.answercontext.domain.AvailableAnswer;
import org.contextmapper.generated.answercontext.repository.AvailableAnswerRepository;
import org.contextmapper.generated.answercontext.service.dto.AvailableAnswerDTO;
import org.contextmapper.generated.answercontext.service.mapper.AvailableAnswerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AvailableAnswerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AvailableAnswerResourceIT {

    private static final Long DEFAULT_TAG_ID = 1L;
    private static final Long UPDATED_TAG_ID = 2L;

    private static final String DEFAULT_TAG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TAG_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/available-answers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AvailableAnswerRepository availableAnswerRepository;

    @Autowired
    private AvailableAnswerMapper availableAnswerMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAvailableAnswerMockMvc;

    private AvailableAnswer availableAnswer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AvailableAnswer createEntity(EntityManager em) {
        AvailableAnswer availableAnswer = new AvailableAnswer().tagId(DEFAULT_TAG_ID).tagName(DEFAULT_TAG_NAME);
        return availableAnswer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AvailableAnswer createUpdatedEntity(EntityManager em) {
        AvailableAnswer availableAnswer = new AvailableAnswer().tagId(UPDATED_TAG_ID).tagName(UPDATED_TAG_NAME);
        return availableAnswer;
    }

    @BeforeEach
    public void initTest() {
        availableAnswer = createEntity(em);
    }

    @Test
    @Transactional
    void getAllAvailableAnswers() throws Exception {
        // Initialize the database
        availableAnswerRepository.saveAndFlush(availableAnswer);

        // Get all the availableAnswerList
        restAvailableAnswerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(availableAnswer.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID.intValue())))
            .andExpect(jsonPath("$.[*].tagName").value(hasItem(DEFAULT_TAG_NAME)));
    }

    @Test
    @Transactional
    void getAvailableAnswer() throws Exception {
        // Initialize the database
        availableAnswerRepository.saveAndFlush(availableAnswer);

        // Get the availableAnswer
        restAvailableAnswerMockMvc
            .perform(get(ENTITY_API_URL_ID, availableAnswer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(availableAnswer.getId().intValue()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID.intValue()))
            .andExpect(jsonPath("$.tagName").value(DEFAULT_TAG_NAME));
    }

    @Test
    @Transactional
    void getNonExistingAvailableAnswer() throws Exception {
        // Get the availableAnswer
        restAvailableAnswerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
