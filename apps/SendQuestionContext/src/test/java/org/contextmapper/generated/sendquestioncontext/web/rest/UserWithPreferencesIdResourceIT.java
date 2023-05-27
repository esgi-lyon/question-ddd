package org.contextmapper.generated.sendquestioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.sendquestioncontext.IntegrationTest;
import org.contextmapper.generated.sendquestioncontext.domain.UserWithPreferencesId;
import org.contextmapper.generated.sendquestioncontext.repository.UserWithPreferencesIdRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserWithPreferencesIdDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.UserWithPreferencesIdMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserWithPreferencesIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserWithPreferencesIdResourceIT {

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/user-with-preferences-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserWithPreferencesIdRepository userWithPreferencesIdRepository;

    @Autowired
    private UserWithPreferencesIdMapper userWithPreferencesIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserWithPreferencesIdMockMvc;

    private UserWithPreferencesId userWithPreferencesId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserWithPreferencesId createEntity(EntityManager em) {
        UserWithPreferencesId userWithPreferencesId = new UserWithPreferencesId().mail(DEFAULT_MAIL);
        return userWithPreferencesId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserWithPreferencesId createUpdatedEntity(EntityManager em) {
        UserWithPreferencesId userWithPreferencesId = new UserWithPreferencesId().mail(UPDATED_MAIL);
        return userWithPreferencesId;
    }

    @BeforeEach
    public void initTest() {
        userWithPreferencesId = createEntity(em);
    }

    @Test
    @Transactional
    void getAllUserWithPreferencesIds() throws Exception {
        // Initialize the database
        userWithPreferencesIdRepository.saveAndFlush(userWithPreferencesId);

        // Get all the userWithPreferencesIdList
        restUserWithPreferencesIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userWithPreferencesId.getId().intValue())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)));
    }

    @Test
    @Transactional
    void getUserWithPreferencesId() throws Exception {
        // Initialize the database
        userWithPreferencesIdRepository.saveAndFlush(userWithPreferencesId);

        // Get the userWithPreferencesId
        restUserWithPreferencesIdMockMvc
            .perform(get(ENTITY_API_URL_ID, userWithPreferencesId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userWithPreferencesId.getId().intValue()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL));
    }

    @Test
    @Transactional
    void getNonExistingUserWithPreferencesId() throws Exception {
        // Get the userWithPreferencesId
        restUserWithPreferencesIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
