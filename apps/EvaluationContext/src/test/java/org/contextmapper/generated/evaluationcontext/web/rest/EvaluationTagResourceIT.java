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
import org.contextmapper.generated.evaluationcontext.domain.EvaluationTag;
import org.contextmapper.generated.evaluationcontext.repository.EvaluationTagRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationTagDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationTagMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EvaluationTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EvaluationTagResourceIT {

    private static final Long DEFAULT_TAG_ID = 1L;
    private static final Long UPDATED_TAG_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/evaluation-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvaluationTagRepository evaluationTagRepository;

    @Autowired
    private EvaluationTagMapper evaluationTagMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEvaluationTagMockMvc;

    private EvaluationTag evaluationTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationTag createEntity(EntityManager em) {
        EvaluationTag evaluationTag = new EvaluationTag().tagId(DEFAULT_TAG_ID).name(DEFAULT_NAME);
        return evaluationTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationTag createUpdatedEntity(EntityManager em) {
        EvaluationTag evaluationTag = new EvaluationTag().tagId(UPDATED_TAG_ID).name(UPDATED_NAME);
        return evaluationTag;
    }

    @BeforeEach
    public void initTest() {
        evaluationTag = createEntity(em);
    }

    @Test
    @Transactional
    void getAllEvaluationTags() throws Exception {
        // Initialize the database
        evaluationTagRepository.saveAndFlush(evaluationTag);

        // Get all the evaluationTagList
        restEvaluationTagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evaluationTag.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getEvaluationTag() throws Exception {
        // Initialize the database
        evaluationTagRepository.saveAndFlush(evaluationTag);

        // Get the evaluationTag
        restEvaluationTagMockMvc
            .perform(get(ENTITY_API_URL_ID, evaluationTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(evaluationTag.getId().intValue()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingEvaluationTag() throws Exception {
        // Get the evaluationTag
        restEvaluationTagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
