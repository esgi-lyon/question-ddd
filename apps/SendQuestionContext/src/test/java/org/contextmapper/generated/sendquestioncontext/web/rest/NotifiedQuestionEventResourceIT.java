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
import org.contextmapper.generated.sendquestioncontext.domain.NotifiedQuestionEvent;
import org.contextmapper.generated.sendquestioncontext.repository.NotifiedQuestionEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedQuestionEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.NotifiedQuestionEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NotifiedQuestionEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NotifiedQuestionEventResourceIT {

    private static final String ENTITY_API_URL = "/api/notified-question-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NotifiedQuestionEventRepository notifiedQuestionEventRepository;

    @Autowired
    private NotifiedQuestionEventMapper notifiedQuestionEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNotifiedQuestionEventMockMvc;

    private NotifiedQuestionEvent notifiedQuestionEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifiedQuestionEvent createEntity(EntityManager em) {
        NotifiedQuestionEvent notifiedQuestionEvent = new NotifiedQuestionEvent();
        return notifiedQuestionEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifiedQuestionEvent createUpdatedEntity(EntityManager em) {
        NotifiedQuestionEvent notifiedQuestionEvent = new NotifiedQuestionEvent();
        return notifiedQuestionEvent;
    }

    @BeforeEach
    public void initTest() {
        notifiedQuestionEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllNotifiedQuestionEvents() throws Exception {
        // Initialize the database
        notifiedQuestionEventRepository.saveAndFlush(notifiedQuestionEvent);

        // Get all the notifiedQuestionEventList
        restNotifiedQuestionEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notifiedQuestionEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getNotifiedQuestionEvent() throws Exception {
        // Initialize the database
        notifiedQuestionEventRepository.saveAndFlush(notifiedQuestionEvent);

        // Get the notifiedQuestionEvent
        restNotifiedQuestionEventMockMvc
            .perform(get(ENTITY_API_URL_ID, notifiedQuestionEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notifiedQuestionEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingNotifiedQuestionEvent() throws Exception {
        // Get the notifiedQuestionEvent
        restNotifiedQuestionEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
