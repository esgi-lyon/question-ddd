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
import org.contextmapper.generated.sendquestioncontext.domain.UserPreferencesTagInfos;
import org.contextmapper.generated.sendquestioncontext.repository.UserPreferencesTagInfosRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserPreferencesTagInfosDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.UserPreferencesTagInfosMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserPreferencesTagInfosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserPreferencesTagInfosResourceIT {

    private static final Long DEFAULT_TAG_ID = 1L;
    private static final Long UPDATED_TAG_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/user-preferences-tag-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserPreferencesTagInfosRepository userPreferencesTagInfosRepository;

    @Autowired
    private UserPreferencesTagInfosMapper userPreferencesTagInfosMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserPreferencesTagInfosMockMvc;

    private UserPreferencesTagInfos userPreferencesTagInfos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPreferencesTagInfos createEntity(EntityManager em) {
        UserPreferencesTagInfos userPreferencesTagInfos = new UserPreferencesTagInfos().tagId(DEFAULT_TAG_ID).name(DEFAULT_NAME);
        return userPreferencesTagInfos;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPreferencesTagInfos createUpdatedEntity(EntityManager em) {
        UserPreferencesTagInfos userPreferencesTagInfos = new UserPreferencesTagInfos().tagId(UPDATED_TAG_ID).name(UPDATED_NAME);
        return userPreferencesTagInfos;
    }

    @BeforeEach
    public void initTest() {
        userPreferencesTagInfos = createEntity(em);
    }

    @Test
    @Transactional
    void getAllUserPreferencesTagInfos() throws Exception {
        // Initialize the database
        userPreferencesTagInfosRepository.saveAndFlush(userPreferencesTagInfos);

        // Get all the userPreferencesTagInfosList
        restUserPreferencesTagInfosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userPreferencesTagInfos.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getUserPreferencesTagInfos() throws Exception {
        // Initialize the database
        userPreferencesTagInfosRepository.saveAndFlush(userPreferencesTagInfos);

        // Get the userPreferencesTagInfos
        restUserPreferencesTagInfosMockMvc
            .perform(get(ENTITY_API_URL_ID, userPreferencesTagInfos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userPreferencesTagInfos.getId().intValue()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingUserPreferencesTagInfos() throws Exception {
        // Get the userPreferencesTagInfos
        restUserPreferencesTagInfosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
