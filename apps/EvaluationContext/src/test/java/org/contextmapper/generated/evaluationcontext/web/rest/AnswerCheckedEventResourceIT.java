package org.contextmapper.generated.evaluationcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.evaluationcontext.IntegrationTest;
import org.contextmapper.generated.evaluationcontext.domain.AnswerCheckedEvent;
import org.contextmapper.generated.evaluationcontext.repository.AnswerCheckedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AnswerCheckedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AnswerCheckedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AnswerCheckedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnswerCheckedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/answer-checked-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnswerCheckedEventRepository answerCheckedEventRepository;

    @Autowired
    private AnswerCheckedEventMapper answerCheckedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnswerCheckedEventMockMvc;

    private AnswerCheckedEvent answerCheckedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerCheckedEvent createEntity(EntityManager em) {
        AnswerCheckedEvent answerCheckedEvent = new AnswerCheckedEvent();
        return answerCheckedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerCheckedEvent createUpdatedEntity(EntityManager em) {
        AnswerCheckedEvent answerCheckedEvent = new AnswerCheckedEvent();
        return answerCheckedEvent;
    }

    @BeforeEach
    public void initTest() {
        answerCheckedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllAnswerCheckedEvents() throws Exception {
        // Initialize the database
        answerCheckedEventRepository.saveAndFlush(answerCheckedEvent);

        // Get all the answerCheckedEventList
        restAnswerCheckedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answerCheckedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getAnswerCheckedEvent() throws Exception {
        // Initialize the database
        answerCheckedEventRepository.saveAndFlush(answerCheckedEvent);

        // Get the answerCheckedEvent
        restAnswerCheckedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, answerCheckedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(answerCheckedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAnswerCheckedEvent() throws Exception {
        // Get the answerCheckedEvent
        restAnswerCheckedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
