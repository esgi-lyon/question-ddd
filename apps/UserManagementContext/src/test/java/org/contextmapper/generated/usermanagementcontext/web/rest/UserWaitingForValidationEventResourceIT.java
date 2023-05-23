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
import org.contextmapper.generated.usermanagementcontext.domain.UserWaitingForValidationEvent;
import org.contextmapper.generated.usermanagementcontext.repository.UserWaitingForValidationEventRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserWaitingForValidationEventDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserWaitingForValidationEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserWaitingForValidationEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserWaitingForValidationEventResourceIT {

    private static final String ENTITY_API_URL = "/api/user-waiting-for-validation-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserWaitingForValidationEventRepository userWaitingForValidationEventRepository;

    @Autowired
    private UserWaitingForValidationEventMapper userWaitingForValidationEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserWaitingForValidationEventMockMvc;

    private UserWaitingForValidationEvent userWaitingForValidationEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserWaitingForValidationEvent createEntity(EntityManager em) {
        UserWaitingForValidationEvent userWaitingForValidationEvent = new UserWaitingForValidationEvent();
        return userWaitingForValidationEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserWaitingForValidationEvent createUpdatedEntity(EntityManager em) {
        UserWaitingForValidationEvent userWaitingForValidationEvent = new UserWaitingForValidationEvent();
        return userWaitingForValidationEvent;
    }

    @BeforeEach
    public void initTest() {
        userWaitingForValidationEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllUserWaitingForValidationEvents() throws Exception {
        // Initialize the database
        userWaitingForValidationEventRepository.saveAndFlush(userWaitingForValidationEvent);

        // Get all the userWaitingForValidationEventList
        restUserWaitingForValidationEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userWaitingForValidationEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getUserWaitingForValidationEvent() throws Exception {
        // Initialize the database
        userWaitingForValidationEventRepository.saveAndFlush(userWaitingForValidationEvent);

        // Get the userWaitingForValidationEvent
        restUserWaitingForValidationEventMockMvc
            .perform(get(ENTITY_API_URL_ID, userWaitingForValidationEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userWaitingForValidationEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingUserWaitingForValidationEvent() throws Exception {
        // Get the userWaitingForValidationEvent
        restUserWaitingForValidationEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
