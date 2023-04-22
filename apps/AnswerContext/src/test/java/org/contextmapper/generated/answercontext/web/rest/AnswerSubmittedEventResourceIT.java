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
import org.contextmapper.generated.answercontext.domain.AnswerSubmittedEvent;
import org.contextmapper.generated.answercontext.repository.AnswerSubmittedEventRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmittedEventDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerSubmittedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AnswerSubmittedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnswerSubmittedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/answer-submitted-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnswerSubmittedEventRepository answerSubmittedEventRepository;

    @Autowired
    private AnswerSubmittedEventMapper answerSubmittedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnswerSubmittedEventMockMvc;

    private AnswerSubmittedEvent answerSubmittedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSubmittedEvent createEntity(EntityManager em) {
        AnswerSubmittedEvent answerSubmittedEvent = new AnswerSubmittedEvent();
        return answerSubmittedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSubmittedEvent createUpdatedEntity(EntityManager em) {
        AnswerSubmittedEvent answerSubmittedEvent = new AnswerSubmittedEvent();
        return answerSubmittedEvent;
    }

    @BeforeEach
    public void initTest() {
        answerSubmittedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllAnswerSubmittedEvents() throws Exception {
        // Initialize the database
        answerSubmittedEventRepository.saveAndFlush(answerSubmittedEvent);

        // Get all the answerSubmittedEventList
        restAnswerSubmittedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answerSubmittedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getAnswerSubmittedEvent() throws Exception {
        // Initialize the database
        answerSubmittedEventRepository.saveAndFlush(answerSubmittedEvent);

        // Get the answerSubmittedEvent
        restAnswerSubmittedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, answerSubmittedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(answerSubmittedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAnswerSubmittedEvent() throws Exception {
        // Get the answerSubmittedEvent
        restAnswerSubmittedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
