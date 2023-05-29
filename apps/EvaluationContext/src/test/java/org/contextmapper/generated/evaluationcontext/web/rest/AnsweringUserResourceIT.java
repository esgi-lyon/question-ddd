package org.contextmapper.generated.evaluationcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.evaluationcontext.IntegrationTest;
import org.contextmapper.generated.evaluationcontext.domain.AnsweringUser;
import org.contextmapper.generated.evaluationcontext.repository.AnsweringUserRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AnsweringUserDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AnsweringUserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AnsweringUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnsweringUserResourceIT {

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/answering-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnsweringUserRepository answeringUserRepository;

    @Autowired
    private AnsweringUserMapper answeringUserMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnsweringUserMockMvc;

    private AnsweringUser answeringUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnsweringUser createEntity(EntityManager em) {
        AnsweringUser answeringUser = new AnsweringUser().mail(DEFAULT_MAIL);
        return answeringUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnsweringUser createUpdatedEntity(EntityManager em) {
        AnsweringUser answeringUser = new AnsweringUser().mail(UPDATED_MAIL);
        return answeringUser;
    }

    @BeforeEach
    public void initTest() {
        answeringUser = createEntity(em);
    }

    @Test
    @Transactional
    void getAllAnsweringUsers() throws Exception {
        // Initialize the database
        answeringUserRepository.saveAndFlush(answeringUser);

        // Get all the answeringUserList
        restAnsweringUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answeringUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)));
    }

    @Test
    @Transactional
    void getAnsweringUser() throws Exception {
        // Initialize the database
        answeringUserRepository.saveAndFlush(answeringUser);

        // Get the answeringUser
        restAnsweringUserMockMvc
            .perform(get(ENTITY_API_URL_ID, answeringUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(answeringUser.getId().intValue()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL));
    }

    @Test
    @Transactional
    void getNonExistingAnsweringUser() throws Exception {
        // Get the answeringUser
        restAnsweringUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
