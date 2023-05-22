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
import org.contextmapper.generated.statcontext.domain.StatisticSubjectTag;
import org.contextmapper.generated.statcontext.repository.StatisticSubjectTagRepository;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectTagDTO;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectTagMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link StatisticSubjectTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StatisticSubjectTagResourceIT {

    private static final Long DEFAULT_TAG_ID = 1L;
    private static final Long UPDATED_TAG_ID = 2L;

    private static final String ENTITY_API_URL = "/api/statistic-subject-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StatisticSubjectTagRepository statisticSubjectTagRepository;

    @Autowired
    private StatisticSubjectTagMapper statisticSubjectTagMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStatisticSubjectTagMockMvc;

    private StatisticSubjectTag statisticSubjectTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticSubjectTag createEntity(EntityManager em) {
        StatisticSubjectTag statisticSubjectTag = new StatisticSubjectTag().tagId(DEFAULT_TAG_ID);
        return statisticSubjectTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticSubjectTag createUpdatedEntity(EntityManager em) {
        StatisticSubjectTag statisticSubjectTag = new StatisticSubjectTag().tagId(UPDATED_TAG_ID);
        return statisticSubjectTag;
    }

    @BeforeEach
    public void initTest() {
        statisticSubjectTag = createEntity(em);
    }

    @Test
    @Transactional
    void getAllStatisticSubjectTags() throws Exception {
        // Initialize the database
        statisticSubjectTagRepository.saveAndFlush(statisticSubjectTag);

        // Get all the statisticSubjectTagList
        restStatisticSubjectTagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statisticSubjectTag.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID.intValue())));
    }

    @Test
    @Transactional
    void getStatisticSubjectTag() throws Exception {
        // Initialize the database
        statisticSubjectTagRepository.saveAndFlush(statisticSubjectTag);

        // Get the statisticSubjectTag
        restStatisticSubjectTagMockMvc
            .perform(get(ENTITY_API_URL_ID, statisticSubjectTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(statisticSubjectTag.getId().intValue()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingStatisticSubjectTag() throws Exception {
        // Get the statisticSubjectTag
        restStatisticSubjectTagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
