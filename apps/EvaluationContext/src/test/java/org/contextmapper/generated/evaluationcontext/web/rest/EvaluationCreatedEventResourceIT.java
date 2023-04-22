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
import org.contextmapper.generated.evaluationcontext.domain.EvaluationCreatedEvent;
import org.contextmapper.generated.evaluationcontext.repository.EvaluationCreatedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationCreatedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationCreatedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EvaluationCreatedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EvaluationCreatedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/evaluation-created-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvaluationCreatedEventRepository evaluationCreatedEventRepository;

    @Autowired
    private EvaluationCreatedEventMapper evaluationCreatedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEvaluationCreatedEventMockMvc;

    private EvaluationCreatedEvent evaluationCreatedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationCreatedEvent createEntity(EntityManager em) {
        EvaluationCreatedEvent evaluationCreatedEvent = new EvaluationCreatedEvent();
        return evaluationCreatedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationCreatedEvent createUpdatedEntity(EntityManager em) {
        EvaluationCreatedEvent evaluationCreatedEvent = new EvaluationCreatedEvent();
        return evaluationCreatedEvent;
    }

    @BeforeEach
    public void initTest() {
        evaluationCreatedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllEvaluationCreatedEvents() throws Exception {
        // Initialize the database
        evaluationCreatedEventRepository.saveAndFlush(evaluationCreatedEvent);

        // Get all the evaluationCreatedEventList
        restEvaluationCreatedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evaluationCreatedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getEvaluationCreatedEvent() throws Exception {
        // Initialize the database
        evaluationCreatedEventRepository.saveAndFlush(evaluationCreatedEvent);

        // Get the evaluationCreatedEvent
        restEvaluationCreatedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, evaluationCreatedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(evaluationCreatedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEvaluationCreatedEvent() throws Exception {
        // Get the evaluationCreatedEvent
        restEvaluationCreatedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
