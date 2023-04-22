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
import org.contextmapper.generated.evaluationcontext.domain.AwardedPointEvent;
import org.contextmapper.generated.evaluationcontext.repository.AwardedPointEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardedPointEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AwardedPointEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AwardedPointEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AwardedPointEventResourceIT {

    private static final String ENTITY_API_URL = "/api/awarded-point-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AwardedPointEventRepository awardedPointEventRepository;

    @Autowired
    private AwardedPointEventMapper awardedPointEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAwardedPointEventMockMvc;

    private AwardedPointEvent awardedPointEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AwardedPointEvent createEntity(EntityManager em) {
        AwardedPointEvent awardedPointEvent = new AwardedPointEvent();
        return awardedPointEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AwardedPointEvent createUpdatedEntity(EntityManager em) {
        AwardedPointEvent awardedPointEvent = new AwardedPointEvent();
        return awardedPointEvent;
    }

    @BeforeEach
    public void initTest() {
        awardedPointEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllAwardedPointEvents() throws Exception {
        // Initialize the database
        awardedPointEventRepository.saveAndFlush(awardedPointEvent);

        // Get all the awardedPointEventList
        restAwardedPointEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(awardedPointEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getAwardedPointEvent() throws Exception {
        // Initialize the database
        awardedPointEventRepository.saveAndFlush(awardedPointEvent);

        // Get the awardedPointEvent
        restAwardedPointEventMockMvc
            .perform(get(ENTITY_API_URL_ID, awardedPointEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(awardedPointEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAwardedPointEvent() throws Exception {
        // Get the awardedPointEvent
        restAwardedPointEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
