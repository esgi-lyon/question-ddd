package org.contextmapper.generated.usermanagementcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.usermanagementcontext.IntegrationTest;
import org.contextmapper.generated.usermanagementcontext.domain.UserViewedEvent;
import org.contextmapper.generated.usermanagementcontext.repository.UserViewedEventRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserViewedEventDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserViewedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/user-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserViewedEventRepository userViewedEventRepository;

    @Autowired
    private UserViewedEventMapper userViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserViewedEventMockMvc;

    private UserViewedEvent userViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserViewedEvent createEntity(EntityManager em) {
        UserViewedEvent userViewedEvent = new UserViewedEvent();
        return userViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserViewedEvent createUpdatedEntity(EntityManager em) {
        UserViewedEvent userViewedEvent = new UserViewedEvent();
        return userViewedEvent;
    }

    @BeforeEach
    public void initTest() {
        userViewedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllUserViewedEvents() throws Exception {
        // Initialize the database
        userViewedEventRepository.saveAndFlush(userViewedEvent);

        // Get all the userViewedEventList
        restUserViewedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userViewedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getUserViewedEvent() throws Exception {
        // Initialize the database
        userViewedEventRepository.saveAndFlush(userViewedEvent);

        // Get the userViewedEvent
        restUserViewedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, userViewedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userViewedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingUserViewedEvent() throws Exception {
        // Get the userViewedEvent
        restUserViewedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
