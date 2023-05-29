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
import org.contextmapper.generated.statcontext.domain.LeaderBoardViewedEvent;
import org.contextmapper.generated.statcontext.repository.LeaderBoardViewedEventRepository;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardViewedEventDTO;
import org.contextmapper.generated.statcontext.service.mapper.LeaderBoardViewedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link LeaderBoardViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LeaderBoardViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/leader-board-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LeaderBoardViewedEventRepository leaderBoardViewedEventRepository;

    @Autowired
    private LeaderBoardViewedEventMapper leaderBoardViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLeaderBoardViewedEventMockMvc;

    private LeaderBoardViewedEvent leaderBoardViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeaderBoardViewedEvent createEntity(EntityManager em) {
        LeaderBoardViewedEvent leaderBoardViewedEvent = new LeaderBoardViewedEvent();
        return leaderBoardViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeaderBoardViewedEvent createUpdatedEntity(EntityManager em) {
        LeaderBoardViewedEvent leaderBoardViewedEvent = new LeaderBoardViewedEvent();
        return leaderBoardViewedEvent;
    }

    @BeforeEach
    public void initTest() {
        leaderBoardViewedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllLeaderBoardViewedEvents() throws Exception {
        // Initialize the database
        leaderBoardViewedEventRepository.saveAndFlush(leaderBoardViewedEvent);

        // Get all the leaderBoardViewedEventList
        restLeaderBoardViewedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leaderBoardViewedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getLeaderBoardViewedEvent() throws Exception {
        // Initialize the database
        leaderBoardViewedEventRepository.saveAndFlush(leaderBoardViewedEvent);

        // Get the leaderBoardViewedEvent
        restLeaderBoardViewedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, leaderBoardViewedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(leaderBoardViewedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingLeaderBoardViewedEvent() throws Exception {
        // Get the leaderBoardViewedEvent
        restLeaderBoardViewedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
