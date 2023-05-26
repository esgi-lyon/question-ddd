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
import org.contextmapper.generated.answercontext.domain.AnswerCreatedEvent;
import org.contextmapper.generated.answercontext.repository.AnswerCreatedEventRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerCreatedEventDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerCreatedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AnswerCreatedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnswerCreatedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/answer-created-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnswerCreatedEventRepository answerCreatedEventRepository;

    @Autowired
    private AnswerCreatedEventMapper answerCreatedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnswerCreatedEventMockMvc;

    private AnswerCreatedEvent answerCreatedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerCreatedEvent createEntity(EntityManager em) {
        AnswerCreatedEvent answerCreatedEvent = new AnswerCreatedEvent();
        return answerCreatedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerCreatedEvent createUpdatedEntity(EntityManager em) {
        AnswerCreatedEvent answerCreatedEvent = new AnswerCreatedEvent();
        return answerCreatedEvent;
    }

    @BeforeEach
    public void initTest() {
        answerCreatedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllAnswerCreatedEvents() throws Exception {
        // Initialize the database
        answerCreatedEventRepository.saveAndFlush(answerCreatedEvent);

        // Get all the answerCreatedEventList
        restAnswerCreatedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answerCreatedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getAnswerCreatedEvent() throws Exception {
        // Initialize the database
        answerCreatedEventRepository.saveAndFlush(answerCreatedEvent);

        // Get the answerCreatedEvent
        restAnswerCreatedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, answerCreatedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(answerCreatedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAnswerCreatedEvent() throws Exception {
        // Get the answerCreatedEvent
        restAnswerCreatedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
