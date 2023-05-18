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
import org.contextmapper.generated.statcontext.domain.EvaluationId;
import org.contextmapper.generated.statcontext.repository.EvaluationIdRepository;
import org.contextmapper.generated.statcontext.service.dto.EvaluationIdDTO;
import org.contextmapper.generated.statcontext.service.mapper.EvaluationIdMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EvaluationIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EvaluationIdResourceIT {

    private static final Integer DEFAULT_EVALUATION_ID = 1;
    private static final Integer UPDATED_EVALUATION_ID = 2;

    private static final String ENTITY_API_URL = "/api/evaluation-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvaluationIdRepository evaluationIdRepository;

    @Autowired
    private EvaluationIdMapper evaluationIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEvaluationIdMockMvc;

    private EvaluationId evaluationId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationId createEntity(EntityManager em) {
        EvaluationId evaluationId = new EvaluationId().evaluationId(DEFAULT_EVALUATION_ID);
        return evaluationId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationId createUpdatedEntity(EntityManager em) {
        EvaluationId evaluationId = new EvaluationId().evaluationId(UPDATED_EVALUATION_ID);
        return evaluationId;
    }

    @BeforeEach
    public void initTest() {
        evaluationId = createEntity(em);
    }

    @Test
    @Transactional
    void getAllEvaluationIds() throws Exception {
        // Initialize the database
        evaluationIdRepository.saveAndFlush(evaluationId);

        // Get all the evaluationIdList
        restEvaluationIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evaluationId.getId().intValue())))
            .andExpect(jsonPath("$.[*].evaluationId").value(hasItem(DEFAULT_EVALUATION_ID)));
    }

    @Test
    @Transactional
    void getEvaluationId() throws Exception {
        // Initialize the database
        evaluationIdRepository.saveAndFlush(evaluationId);

        // Get the evaluationId
        restEvaluationIdMockMvc
            .perform(get(ENTITY_API_URL_ID, evaluationId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(evaluationId.getId().intValue()))
            .andExpect(jsonPath("$.evaluationId").value(DEFAULT_EVALUATION_ID));
    }

    @Test
    @Transactional
    void getNonExistingEvaluationId() throws Exception {
        // Get the evaluationId
        restEvaluationIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
