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
import org.contextmapper.generated.usermanagementcontext.domain.UserRejectedEvent;
import org.contextmapper.generated.usermanagementcontext.repository.UserRejectedEventRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserRejectedEventDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserRejectedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserRejectedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserRejectedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/user-rejected-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserRejectedEventRepository userRejectedEventRepository;

    @Autowired
    private UserRejectedEventMapper userRejectedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserRejectedEventMockMvc;

    private UserRejectedEvent userRejectedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserRejectedEvent createEntity(EntityManager em) {
        UserRejectedEvent userRejectedEvent = new UserRejectedEvent();
        return userRejectedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserRejectedEvent createUpdatedEntity(EntityManager em) {
        UserRejectedEvent userRejectedEvent = new UserRejectedEvent();
        return userRejectedEvent;
    }

    @BeforeEach
    public void initTest() {
        userRejectedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllUserRejectedEvents() throws Exception {
        // Initialize the database
        userRejectedEventRepository.saveAndFlush(userRejectedEvent);

        // Get all the userRejectedEventList
        restUserRejectedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userRejectedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getUserRejectedEvent() throws Exception {
        // Initialize the database
        userRejectedEventRepository.saveAndFlush(userRejectedEvent);

        // Get the userRejectedEvent
        restUserRejectedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, userRejectedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userRejectedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingUserRejectedEvent() throws Exception {
        // Get the userRejectedEvent
        restUserRejectedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
