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
import org.contextmapper.generated.statcontext.domain.QuestionStatsViewedEvent;
import org.contextmapper.generated.statcontext.repository.QuestionStatsViewedEventRepository;
import org.contextmapper.generated.statcontext.service.dto.QuestionStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.mapper.QuestionStatsViewedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionStatsViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionStatsViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/question-stats-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionStatsViewedEventRepository questionStatsViewedEventRepository;

    @Autowired
    private QuestionStatsViewedEventMapper questionStatsViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionStatsViewedEventMockMvc;

    private QuestionStatsViewedEvent questionStatsViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionStatsViewedEvent createEntity(EntityManager em) {
        QuestionStatsViewedEvent questionStatsViewedEvent = new QuestionStatsViewedEvent();
        return questionStatsViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionStatsViewedEvent createUpdatedEntity(EntityManager em) {
        QuestionStatsViewedEvent questionStatsViewedEvent = new QuestionStatsViewedEvent();
        return questionStatsViewedEvent;
    }

    @BeforeEach
    public void initTest() {
        questionStatsViewedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllQuestionStatsViewedEvents() throws Exception {
        // Initialize the database
        questionStatsViewedEventRepository.saveAndFlush(questionStatsViewedEvent);

        // Get all the questionStatsViewedEventList
        restQuestionStatsViewedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionStatsViewedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getQuestionStatsViewedEvent() throws Exception {
        // Initialize the database
        questionStatsViewedEventRepository.saveAndFlush(questionStatsViewedEvent);

        // Get the questionStatsViewedEvent
        restQuestionStatsViewedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, questionStatsViewedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionStatsViewedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingQuestionStatsViewedEvent() throws Exception {
        // Get the questionStatsViewedEvent
        restQuestionStatsViewedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
