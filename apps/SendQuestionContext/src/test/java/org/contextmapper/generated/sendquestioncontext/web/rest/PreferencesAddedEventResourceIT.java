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
import org.contextmapper.generated.sendquestioncontext.domain.PreferencesAddedEvent;
import org.contextmapper.generated.sendquestioncontext.repository.PreferencesAddedEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.PreferencesAddedEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.PreferencesAddedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PreferencesAddedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PreferencesAddedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/preferences-added-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PreferencesAddedEventRepository preferencesAddedEventRepository;

    @Autowired
    private PreferencesAddedEventMapper preferencesAddedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPreferencesAddedEventMockMvc;

    private PreferencesAddedEvent preferencesAddedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PreferencesAddedEvent createEntity(EntityManager em) {
        PreferencesAddedEvent preferencesAddedEvent = new PreferencesAddedEvent();
        return preferencesAddedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PreferencesAddedEvent createUpdatedEntity(EntityManager em) {
        PreferencesAddedEvent preferencesAddedEvent = new PreferencesAddedEvent();
        return preferencesAddedEvent;
    }

    @BeforeEach
    public void initTest() {
        preferencesAddedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllPreferencesAddedEvents() throws Exception {
        // Initialize the database
        preferencesAddedEventRepository.saveAndFlush(preferencesAddedEvent);

        // Get all the preferencesAddedEventList
        restPreferencesAddedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(preferencesAddedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getPreferencesAddedEvent() throws Exception {
        // Initialize the database
        preferencesAddedEventRepository.saveAndFlush(preferencesAddedEvent);

        // Get the preferencesAddedEvent
        restPreferencesAddedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, preferencesAddedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(preferencesAddedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingPreferencesAddedEvent() throws Exception {
        // Get the preferencesAddedEvent
        restPreferencesAddedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
