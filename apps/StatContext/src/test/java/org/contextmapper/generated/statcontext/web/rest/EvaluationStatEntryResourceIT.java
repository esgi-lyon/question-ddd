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
import org.contextmapper.generated.statcontext.domain.EvaluationStatEntry;
import org.contextmapper.generated.statcontext.repository.EvaluationStatEntryRepository;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatEntryDTO;
import org.contextmapper.generated.statcontext.service.mapper.EvaluationStatEntryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EvaluationStatEntryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EvaluationStatEntryResourceIT {

    private static final Long DEFAULT_EVALUATION_ID = 1L;
    private static final Long UPDATED_EVALUATION_ID = 2L;

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    private static final String ENTITY_API_URL = "/api/evaluation-stat-entries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvaluationStatEntryRepository evaluationStatEntryRepository;

    @Autowired
    private EvaluationStatEntryMapper evaluationStatEntryMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEvaluationStatEntryMockMvc;

    private EvaluationStatEntry evaluationStatEntry;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationStatEntry createEntity(EntityManager em) {
        EvaluationStatEntry evaluationStatEntry = new EvaluationStatEntry().evaluationId(DEFAULT_EVALUATION_ID).score(DEFAULT_SCORE);
        return evaluationStatEntry;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationStatEntry createUpdatedEntity(EntityManager em) {
        EvaluationStatEntry evaluationStatEntry = new EvaluationStatEntry().evaluationId(UPDATED_EVALUATION_ID).score(UPDATED_SCORE);
        return evaluationStatEntry;
    }

    @BeforeEach
    public void initTest() {
        evaluationStatEntry = createEntity(em);
    }

    @Test
    @Transactional
    void getAllEvaluationStatEntries() throws Exception {
        // Initialize the database
        evaluationStatEntryRepository.saveAndFlush(evaluationStatEntry);

        // Get all the evaluationStatEntryList
        restEvaluationStatEntryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evaluationStatEntry.getId().intValue())))
            .andExpect(jsonPath("$.[*].evaluationId").value(hasItem(DEFAULT_EVALUATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)));
    }

    @Test
    @Transactional
    void getEvaluationStatEntry() throws Exception {
        // Initialize the database
        evaluationStatEntryRepository.saveAndFlush(evaluationStatEntry);

        // Get the evaluationStatEntry
        restEvaluationStatEntryMockMvc
            .perform(get(ENTITY_API_URL_ID, evaluationStatEntry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(evaluationStatEntry.getId().intValue()))
            .andExpect(jsonPath("$.evaluationId").value(DEFAULT_EVALUATION_ID.intValue()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE));
    }

    @Test
    @Transactional
    void getNonExistingEvaluationStatEntry() throws Exception {
        // Get the evaluationStatEntry
        restEvaluationStatEntryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
