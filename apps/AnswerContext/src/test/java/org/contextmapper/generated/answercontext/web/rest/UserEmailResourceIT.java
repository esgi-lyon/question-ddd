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
import org.contextmapper.generated.answercontext.domain.UserEmail;
import org.contextmapper.generated.answercontext.repository.UserEmailRepository;
import org.contextmapper.generated.answercontext.service.dto.UserEmailDTO;
import org.contextmapper.generated.answercontext.service.mapper.UserEmailMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserEmailResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserEmailResourceIT {

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/user-emails";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserEmailRepository userEmailRepository;

    @Autowired
    private UserEmailMapper userEmailMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserEmailMockMvc;

    private UserEmail userEmail;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserEmail createEntity(EntityManager em) {
        UserEmail userEmail = new UserEmail().mail(DEFAULT_MAIL);
        return userEmail;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserEmail createUpdatedEntity(EntityManager em) {
        UserEmail userEmail = new UserEmail().mail(UPDATED_MAIL);
        return userEmail;
    }

    @BeforeEach
    public void initTest() {
        userEmail = createEntity(em);
    }

    @Test
    @Transactional
    void getAllUserEmails() throws Exception {
        // Initialize the database
        userEmailRepository.saveAndFlush(userEmail);

        // Get all the userEmailList
        restUserEmailMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userEmail.getId().intValue())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)));
    }

    @Test
    @Transactional
    void getUserEmail() throws Exception {
        // Initialize the database
        userEmailRepository.saveAndFlush(userEmail);

        // Get the userEmail
        restUserEmailMockMvc
            .perform(get(ENTITY_API_URL_ID, userEmail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userEmail.getId().intValue()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL));
    }

    @Test
    @Transactional
    void getNonExistingUserEmail() throws Exception {
        // Get the userEmail
        restUserEmailMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
