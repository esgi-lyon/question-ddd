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
import org.contextmapper.generated.statcontext.domain.LoggedUserStatsViewedEvent;
import org.contextmapper.generated.statcontext.repository.LoggedUserStatsViewedEventRepository;
import org.contextmapper.generated.statcontext.service.dto.LoggedUserStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.mapper.LoggedUserStatsViewedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link LoggedUserStatsViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LoggedUserStatsViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/logged-user-stats-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LoggedUserStatsViewedEventRepository loggedUserStatsViewedEventRepository;

    @Autowired
    private LoggedUserStatsViewedEventMapper loggedUserStatsViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLoggedUserStatsViewedEventMockMvc;

    private LoggedUserStatsViewedEvent loggedUserStatsViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoggedUserStatsViewedEvent createEntity(EntityManager em) {
        LoggedUserStatsViewedEvent loggedUserStatsViewedEvent = new LoggedUserStatsViewedEvent();
        return loggedUserStatsViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoggedUserStatsViewedEvent createUpdatedEntity(EntityManager em) {
        LoggedUserStatsViewedEvent loggedUserStatsViewedEvent = new LoggedUserStatsViewedEvent();
        return loggedUserStatsViewedEvent;
    }

    @BeforeEach
    public void initTest() {
        loggedUserStatsViewedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllLoggedUserStatsViewedEvents() throws Exception {
        // Initialize the database
        loggedUserStatsViewedEventRepository.saveAndFlush(loggedUserStatsViewedEvent);

        // Get all the loggedUserStatsViewedEventList
        restLoggedUserStatsViewedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loggedUserStatsViewedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getLoggedUserStatsViewedEvent() throws Exception {
        // Initialize the database
        loggedUserStatsViewedEventRepository.saveAndFlush(loggedUserStatsViewedEvent);

        // Get the loggedUserStatsViewedEvent
        restLoggedUserStatsViewedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, loggedUserStatsViewedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(loggedUserStatsViewedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingLoggedUserStatsViewedEvent() throws Exception {
        // Get the loggedUserStatsViewedEvent
        restLoggedUserStatsViewedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
