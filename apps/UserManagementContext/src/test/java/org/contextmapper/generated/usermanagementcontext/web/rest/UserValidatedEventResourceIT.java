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
import org.contextmapper.generated.usermanagementcontext.domain.UserValidatedEvent;
import org.contextmapper.generated.usermanagementcontext.repository.UserValidatedEventRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserValidatedEventDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserValidatedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserValidatedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserValidatedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/user-validated-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserValidatedEventRepository userValidatedEventRepository;

    @Autowired
    private UserValidatedEventMapper userValidatedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserValidatedEventMockMvc;

    private UserValidatedEvent userValidatedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserValidatedEvent createEntity(EntityManager em) {
        UserValidatedEvent userValidatedEvent = new UserValidatedEvent();
        return userValidatedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserValidatedEvent createUpdatedEntity(EntityManager em) {
        UserValidatedEvent userValidatedEvent = new UserValidatedEvent();
        return userValidatedEvent;
    }

    @BeforeEach
    public void initTest() {
        userValidatedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllUserValidatedEvents() throws Exception {
        // Initialize the database
        userValidatedEventRepository.saveAndFlush(userValidatedEvent);

        // Get all the userValidatedEventList
        restUserValidatedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userValidatedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getUserValidatedEvent() throws Exception {
        // Initialize the database
        userValidatedEventRepository.saveAndFlush(userValidatedEvent);

        // Get the userValidatedEvent
        restUserValidatedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, userValidatedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userValidatedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingUserValidatedEvent() throws Exception {
        // Get the userValidatedEvent
        restUserValidatedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
