package org.contextmapper.generated.sendquestioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.sendquestioncontext.IntegrationTest;
import org.contextmapper.generated.sendquestioncontext.domain.CreatedQuestionEvent;
import org.contextmapper.generated.sendquestioncontext.repository.CreatedQuestionEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreatedQuestionEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.CreatedQuestionEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreatedQuestionEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreatedQuestionEventResourceIT {

    private static final String ENTITY_API_URL = "/api/created-question-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreatedQuestionEventRepository createdQuestionEventRepository;

    @Autowired
    private CreatedQuestionEventMapper createdQuestionEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreatedQuestionEventMockMvc;

    private CreatedQuestionEvent createdQuestionEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreatedQuestionEvent createEntity(EntityManager em) {
        CreatedQuestionEvent createdQuestionEvent = new CreatedQuestionEvent();
        return createdQuestionEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreatedQuestionEvent createUpdatedEntity(EntityManager em) {
        CreatedQuestionEvent createdQuestionEvent = new CreatedQuestionEvent();
        return createdQuestionEvent;
    }

    @BeforeEach
    public void initTest() {
        createdQuestionEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllCreatedQuestionEvents() throws Exception {
        // Initialize the database
        createdQuestionEventRepository.saveAndFlush(createdQuestionEvent);

        // Get all the createdQuestionEventList
        restCreatedQuestionEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(createdQuestionEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getCreatedQuestionEvent() throws Exception {
        // Initialize the database
        createdQuestionEventRepository.saveAndFlush(createdQuestionEvent);

        // Get the createdQuestionEvent
        restCreatedQuestionEventMockMvc
            .perform(get(ENTITY_API_URL_ID, createdQuestionEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(createdQuestionEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCreatedQuestionEvent() throws Exception {
        // Get the createdQuestionEvent
        restCreatedQuestionEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
