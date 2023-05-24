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
import org.contextmapper.generated.evaluationcontext.domain.NewAnswerNotifiedEvent;
import org.contextmapper.generated.evaluationcontext.repository.NewAnswerNotifiedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.NewAnswerNotifiedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.NewAnswerNotifiedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NewAnswerNotifiedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NewAnswerNotifiedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/new-answer-notified-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NewAnswerNotifiedEventRepository newAnswerNotifiedEventRepository;

    @Autowired
    private NewAnswerNotifiedEventMapper newAnswerNotifiedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNewAnswerNotifiedEventMockMvc;

    private NewAnswerNotifiedEvent newAnswerNotifiedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NewAnswerNotifiedEvent createEntity(EntityManager em) {
        NewAnswerNotifiedEvent newAnswerNotifiedEvent = new NewAnswerNotifiedEvent();
        return newAnswerNotifiedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NewAnswerNotifiedEvent createUpdatedEntity(EntityManager em) {
        NewAnswerNotifiedEvent newAnswerNotifiedEvent = new NewAnswerNotifiedEvent();
        return newAnswerNotifiedEvent;
    }

    @BeforeEach
    public void initTest() {
        newAnswerNotifiedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllNewAnswerNotifiedEvents() throws Exception {
        // Initialize the database
        newAnswerNotifiedEventRepository.saveAndFlush(newAnswerNotifiedEvent);

        // Get all the newAnswerNotifiedEventList
        restNewAnswerNotifiedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(newAnswerNotifiedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getNewAnswerNotifiedEvent() throws Exception {
        // Initialize the database
        newAnswerNotifiedEventRepository.saveAndFlush(newAnswerNotifiedEvent);

        // Get the newAnswerNotifiedEvent
        restNewAnswerNotifiedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, newAnswerNotifiedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(newAnswerNotifiedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingNewAnswerNotifiedEvent() throws Exception {
        // Get the newAnswerNotifiedEvent
        restNewAnswerNotifiedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
