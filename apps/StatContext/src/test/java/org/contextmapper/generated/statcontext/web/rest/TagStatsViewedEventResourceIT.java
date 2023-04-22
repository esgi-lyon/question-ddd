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
import org.contextmapper.generated.statcontext.domain.TagStatsViewedEvent;
import org.contextmapper.generated.statcontext.repository.TagStatsViewedEventRepository;
import org.contextmapper.generated.statcontext.service.dto.TagStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.mapper.TagStatsViewedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TagStatsViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TagStatsViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/tag-stats-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagStatsViewedEventRepository tagStatsViewedEventRepository;

    @Autowired
    private TagStatsViewedEventMapper tagStatsViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagStatsViewedEventMockMvc;

    private TagStatsViewedEvent tagStatsViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagStatsViewedEvent createEntity(EntityManager em) {
        TagStatsViewedEvent tagStatsViewedEvent = new TagStatsViewedEvent();
        return tagStatsViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagStatsViewedEvent createUpdatedEntity(EntityManager em) {
        TagStatsViewedEvent tagStatsViewedEvent = new TagStatsViewedEvent();
        return tagStatsViewedEvent;
    }

    @BeforeEach
    public void initTest() {
        tagStatsViewedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllTagStatsViewedEvents() throws Exception {
        // Initialize the database
        tagStatsViewedEventRepository.saveAndFlush(tagStatsViewedEvent);

        // Get all the tagStatsViewedEventList
        restTagStatsViewedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagStatsViewedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getTagStatsViewedEvent() throws Exception {
        // Initialize the database
        tagStatsViewedEventRepository.saveAndFlush(tagStatsViewedEvent);

        // Get the tagStatsViewedEvent
        restTagStatsViewedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, tagStatsViewedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tagStatsViewedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTagStatsViewedEvent() throws Exception {
        // Get the tagStatsViewedEvent
        restTagStatsViewedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
