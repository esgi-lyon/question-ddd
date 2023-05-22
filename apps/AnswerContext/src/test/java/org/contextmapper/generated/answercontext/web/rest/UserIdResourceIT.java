package org.contextmapper.generated.answercontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.answercontext.IntegrationTest;
import org.contextmapper.generated.answercontext.domain.UserId;
import org.contextmapper.generated.answercontext.repository.UserIdRepository;
import org.contextmapper.generated.answercontext.service.dto.UserIdDTO;
import org.contextmapper.generated.answercontext.service.mapper.UserIdMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserIdResourceIT {

    private static final String ENTITY_API_URL = "/api/user-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserIdRepository userIdRepository;

    @Autowired
    private UserIdMapper userIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserIdMockMvc;

    private UserId userId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserId createEntity(EntityManager em) {
        UserId userId = new UserId();
        return userId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserId createUpdatedEntity(EntityManager em) {
        UserId userId = new UserId();
        return userId;
    }

    @BeforeEach
    public void initTest() {
        userId = createEntity(em);
    }

    @Test
    @Transactional
    void getAllUserIds() throws Exception {
        // Initialize the database
        userIdRepository.saveAndFlush(userId);

        // Get all the userIdList
        restUserIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userId.getId().intValue())));
    }

    @Test
    @Transactional
    void getUserId() throws Exception {
        // Initialize the database
        userIdRepository.saveAndFlush(userId);

        // Get the userId
        restUserIdMockMvc
            .perform(get(ENTITY_API_URL_ID, userId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userId.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingUserId() throws Exception {
        // Get the userId
        restUserIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
