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
import org.contextmapper.generated.statcontext.domain.UserStatsViewedEvent;
import org.contextmapper.generated.statcontext.repository.UserStatsViewedEventRepository;
import org.contextmapper.generated.statcontext.service.dto.UserStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.mapper.UserStatsViewedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserStatsViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserStatsViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/user-stats-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserStatsViewedEventRepository userStatsViewedEventRepository;

    @Autowired
    private UserStatsViewedEventMapper userStatsViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserStatsViewedEventMockMvc;

    private UserStatsViewedEvent userStatsViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserStatsViewedEvent createEntity(EntityManager em) {
        UserStatsViewedEvent userStatsViewedEvent = new UserStatsViewedEvent();
        return userStatsViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserStatsViewedEvent createUpdatedEntity(EntityManager em) {
        UserStatsViewedEvent userStatsViewedEvent = new UserStatsViewedEvent();
        return userStatsViewedEvent;
    }

    @BeforeEach
    public void initTest() {
        userStatsViewedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllUserStatsViewedEvents() throws Exception {
        // Initialize the database
        userStatsViewedEventRepository.saveAndFlush(userStatsViewedEvent);

        // Get all the userStatsViewedEventList
        restUserStatsViewedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userStatsViewedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getUserStatsViewedEvent() throws Exception {
        // Initialize the database
        userStatsViewedEventRepository.saveAndFlush(userStatsViewedEvent);

        // Get the userStatsViewedEvent
        restUserStatsViewedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, userStatsViewedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userStatsViewedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingUserStatsViewedEvent() throws Exception {
        // Get the userStatsViewedEvent
        restUserStatsViewedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
