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
import org.contextmapper.generated.evaluationcontext.domain.UserEvaluationViewedEvent;
import org.contextmapper.generated.evaluationcontext.repository.UserEvaluationViewedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.UserEvaluationViewedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.UserEvaluationViewedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserEvaluationViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserEvaluationViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/user-evaluation-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserEvaluationViewedEventRepository userEvaluationViewedEventRepository;

    @Autowired
    private UserEvaluationViewedEventMapper userEvaluationViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserEvaluationViewedEventMockMvc;

    private UserEvaluationViewedEvent userEvaluationViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserEvaluationViewedEvent createEntity(EntityManager em) {
        UserEvaluationViewedEvent userEvaluationViewedEvent = new UserEvaluationViewedEvent();
        return userEvaluationViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserEvaluationViewedEvent createUpdatedEntity(EntityManager em) {
        UserEvaluationViewedEvent userEvaluationViewedEvent = new UserEvaluationViewedEvent();
        return userEvaluationViewedEvent;
    }

    @BeforeEach
    public void initTest() {
        userEvaluationViewedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllUserEvaluationViewedEvents() throws Exception {
        // Initialize the database
        userEvaluationViewedEventRepository.saveAndFlush(userEvaluationViewedEvent);

        // Get all the userEvaluationViewedEventList
        restUserEvaluationViewedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userEvaluationViewedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getUserEvaluationViewedEvent() throws Exception {
        // Initialize the database
        userEvaluationViewedEventRepository.saveAndFlush(userEvaluationViewedEvent);

        // Get the userEvaluationViewedEvent
        restUserEvaluationViewedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, userEvaluationViewedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userEvaluationViewedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingUserEvaluationViewedEvent() throws Exception {
        // Get the userEvaluationViewedEvent
        restUserEvaluationViewedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
