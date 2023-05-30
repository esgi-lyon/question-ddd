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
import org.contextmapper.generated.evaluationcontext.domain.TagEvaluationViewedEvent;
import org.contextmapper.generated.evaluationcontext.repository.TagEvaluationViewedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.TagEvaluationViewedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.TagEvaluationViewedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TagEvaluationViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TagEvaluationViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/tag-evaluation-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagEvaluationViewedEventRepository tagEvaluationViewedEventRepository;

    @Autowired
    private TagEvaluationViewedEventMapper tagEvaluationViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagEvaluationViewedEventMockMvc;

    private TagEvaluationViewedEvent tagEvaluationViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagEvaluationViewedEvent createEntity(EntityManager em) {
        TagEvaluationViewedEvent tagEvaluationViewedEvent = new TagEvaluationViewedEvent();
        return tagEvaluationViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagEvaluationViewedEvent createUpdatedEntity(EntityManager em) {
        TagEvaluationViewedEvent tagEvaluationViewedEvent = new TagEvaluationViewedEvent();
        return tagEvaluationViewedEvent;
    }

    @BeforeEach
    public void initTest() {
        tagEvaluationViewedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllTagEvaluationViewedEvents() throws Exception {
        // Initialize the database
        tagEvaluationViewedEventRepository.saveAndFlush(tagEvaluationViewedEvent);

        // Get all the tagEvaluationViewedEventList
        restTagEvaluationViewedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagEvaluationViewedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getTagEvaluationViewedEvent() throws Exception {
        // Initialize the database
        tagEvaluationViewedEventRepository.saveAndFlush(tagEvaluationViewedEvent);

        // Get the tagEvaluationViewedEvent
        restTagEvaluationViewedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, tagEvaluationViewedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tagEvaluationViewedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTagEvaluationViewedEvent() throws Exception {
        // Get the tagEvaluationViewedEvent
        restTagEvaluationViewedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
