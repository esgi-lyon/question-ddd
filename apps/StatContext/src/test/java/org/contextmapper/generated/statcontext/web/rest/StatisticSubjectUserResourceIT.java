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
import org.contextmapper.generated.statcontext.domain.StatisticSubjectUser;
import org.contextmapper.generated.statcontext.repository.StatisticSubjectUserRepository;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectUserDTO;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectUserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link StatisticSubjectUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StatisticSubjectUserResourceIT {

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/statistic-subject-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StatisticSubjectUserRepository statisticSubjectUserRepository;

    @Autowired
    private StatisticSubjectUserMapper statisticSubjectUserMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStatisticSubjectUserMockMvc;

    private StatisticSubjectUser statisticSubjectUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticSubjectUser createEntity(EntityManager em) {
        StatisticSubjectUser statisticSubjectUser = new StatisticSubjectUser().mail(DEFAULT_MAIL);
        return statisticSubjectUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticSubjectUser createUpdatedEntity(EntityManager em) {
        StatisticSubjectUser statisticSubjectUser = new StatisticSubjectUser().mail(UPDATED_MAIL);
        return statisticSubjectUser;
    }

    @BeforeEach
    public void initTest() {
        statisticSubjectUser = createEntity(em);
    }

    @Test
    @Transactional
    void getAllStatisticSubjectUsers() throws Exception {
        // Initialize the database
        statisticSubjectUserRepository.saveAndFlush(statisticSubjectUser);

        // Get all the statisticSubjectUserList
        restStatisticSubjectUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statisticSubjectUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)));
    }

    @Test
    @Transactional
    void getStatisticSubjectUser() throws Exception {
        // Initialize the database
        statisticSubjectUserRepository.saveAndFlush(statisticSubjectUser);

        // Get the statisticSubjectUser
        restStatisticSubjectUserMockMvc
            .perform(get(ENTITY_API_URL_ID, statisticSubjectUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(statisticSubjectUser.getId().intValue()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL));
    }

    @Test
    @Transactional
    void getNonExistingStatisticSubjectUser() throws Exception {
        // Get the statisticSubjectUser
        restStatisticSubjectUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
