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
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfosViewedEvent;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentTagInfosViewedEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagInfosViewedEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentTagInfosViewedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionSentTagInfosViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionSentTagInfosViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/question-sent-tag-infos-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionSentTagInfosViewedEventRepository questionSentTagInfosViewedEventRepository;

    @Autowired
    private QuestionSentTagInfosViewedEventMapper questionSentTagInfosViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionSentTagInfosViewedEventMockMvc;

    private QuestionSentTagInfosViewedEvent questionSentTagInfosViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentTagInfosViewedEvent createEntity(EntityManager em) {
        QuestionSentTagInfosViewedEvent questionSentTagInfosViewedEvent = new QuestionSentTagInfosViewedEvent();
        return questionSentTagInfosViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentTagInfosViewedEvent createUpdatedEntity(EntityManager em) {
        QuestionSentTagInfosViewedEvent questionSentTagInfosViewedEvent = new QuestionSentTagInfosViewedEvent();
        return questionSentTagInfosViewedEvent;
    }

    @BeforeEach
    public void initTest() {
        questionSentTagInfosViewedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllQuestionSentTagInfosViewedEvents() throws Exception {
        // Initialize the database
        questionSentTagInfosViewedEventRepository.saveAndFlush(questionSentTagInfosViewedEvent);

        // Get all the questionSentTagInfosViewedEventList
        restQuestionSentTagInfosViewedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionSentTagInfosViewedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getQuestionSentTagInfosViewedEvent() throws Exception {
        // Initialize the database
        questionSentTagInfosViewedEventRepository.saveAndFlush(questionSentTagInfosViewedEvent);

        // Get the questionSentTagInfosViewedEvent
        restQuestionSentTagInfosViewedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, questionSentTagInfosViewedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionSentTagInfosViewedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingQuestionSentTagInfosViewedEvent() throws Exception {
        // Get the questionSentTagInfosViewedEvent
        restQuestionSentTagInfosViewedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
