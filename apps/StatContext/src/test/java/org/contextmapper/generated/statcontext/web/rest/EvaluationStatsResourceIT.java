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
import org.contextmapper.generated.statcontext.domain.EvaluationStats;
import org.contextmapper.generated.statcontext.repository.EvaluationStatsRepository;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatsDTO;
import org.contextmapper.generated.statcontext.service.mapper.EvaluationStatsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EvaluationStatsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EvaluationStatsResourceIT {

    private static final Integer DEFAULT_TOTAL = 1;
    private static final Integer UPDATED_TOTAL = 2;

    private static final String ENTITY_API_URL = "/api/evaluation-stats";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvaluationStatsRepository evaluationStatsRepository;

    @Autowired
    private EvaluationStatsMapper evaluationStatsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEvaluationStatsMockMvc;

    private EvaluationStats evaluationStats;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationStats createEntity(EntityManager em) {
        EvaluationStats evaluationStats = new EvaluationStats().total(DEFAULT_TOTAL);
        return evaluationStats;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationStats createUpdatedEntity(EntityManager em) {
        EvaluationStats evaluationStats = new EvaluationStats().total(UPDATED_TOTAL);
        return evaluationStats;
    }

    @BeforeEach
    public void initTest() {
        evaluationStats = createEntity(em);
    }

    @Test
    @Transactional
    void getAllEvaluationStats() throws Exception {
        // Initialize the database
        evaluationStatsRepository.saveAndFlush(evaluationStats);

        // Get all the evaluationStatsList
        restEvaluationStatsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evaluationStats.getId().intValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)));
    }

    @Test
    @Transactional
    void getEvaluationStats() throws Exception {
        // Initialize the database
        evaluationStatsRepository.saveAndFlush(evaluationStats);

        // Get the evaluationStats
        restEvaluationStatsMockMvc
            .perform(get(ENTITY_API_URL_ID, evaluationStats.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(evaluationStats.getId().intValue()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL));
    }

    @Test
    @Transactional
    void getNonExistingEvaluationStats() throws Exception {
        // Get the evaluationStats
        restEvaluationStatsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
