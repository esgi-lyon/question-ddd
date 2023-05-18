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
import org.contextmapper.generated.statcontext.domain.StatEvaluationTag;
import org.contextmapper.generated.statcontext.repository.StatEvaluationTagRepository;
import org.contextmapper.generated.statcontext.service.dto.StatEvaluationTagDTO;
import org.contextmapper.generated.statcontext.service.mapper.StatEvaluationTagMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link StatEvaluationTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StatEvaluationTagResourceIT {

    private static final Integer DEFAULT_TAG_ID = 1;
    private static final Integer UPDATED_TAG_ID = 2;

    private static final String ENTITY_API_URL = "/api/stat-evaluation-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StatEvaluationTagRepository statEvaluationTagRepository;

    @Autowired
    private StatEvaluationTagMapper statEvaluationTagMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStatEvaluationTagMockMvc;

    private StatEvaluationTag statEvaluationTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatEvaluationTag createEntity(EntityManager em) {
        StatEvaluationTag statEvaluationTag = new StatEvaluationTag().tagId(DEFAULT_TAG_ID);
        return statEvaluationTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatEvaluationTag createUpdatedEntity(EntityManager em) {
        StatEvaluationTag statEvaluationTag = new StatEvaluationTag().tagId(UPDATED_TAG_ID);
        return statEvaluationTag;
    }

    @BeforeEach
    public void initTest() {
        statEvaluationTag = createEntity(em);
    }

    @Test
    @Transactional
    void getAllStatEvaluationTags() throws Exception {
        // Initialize the database
        statEvaluationTagRepository.saveAndFlush(statEvaluationTag);

        // Get all the statEvaluationTagList
        restStatEvaluationTagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statEvaluationTag.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID)));
    }

    @Test
    @Transactional
    void getStatEvaluationTag() throws Exception {
        // Initialize the database
        statEvaluationTagRepository.saveAndFlush(statEvaluationTag);

        // Get the statEvaluationTag
        restStatEvaluationTagMockMvc
            .perform(get(ENTITY_API_URL_ID, statEvaluationTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(statEvaluationTag.getId().intValue()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID));
    }

    @Test
    @Transactional
    void getNonExistingStatEvaluationTag() throws Exception {
        // Get the statEvaluationTag
        restStatEvaluationTagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
