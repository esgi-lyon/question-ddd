package org.contextmapper.generated.skillcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.skillcontext.IntegrationTest;
import org.contextmapper.generated.skillcontext.domain.TagCreatedEvent;
import org.contextmapper.generated.skillcontext.repository.TagCreatedEventRepository;
import org.contextmapper.generated.skillcontext.service.dto.TagCreatedEventDTO;
import org.contextmapper.generated.skillcontext.service.mapper.TagCreatedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TagCreatedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TagCreatedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/tag-created-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagCreatedEventRepository tagCreatedEventRepository;

    @Autowired
    private TagCreatedEventMapper tagCreatedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagCreatedEventMockMvc;

    private TagCreatedEvent tagCreatedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagCreatedEvent createEntity(EntityManager em) {
        TagCreatedEvent tagCreatedEvent = new TagCreatedEvent();
        return tagCreatedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagCreatedEvent createUpdatedEntity(EntityManager em) {
        TagCreatedEvent tagCreatedEvent = new TagCreatedEvent();
        return tagCreatedEvent;
    }

    @BeforeEach
    public void initTest() {
        tagCreatedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllTagCreatedEvents() throws Exception {
        // Initialize the database
        tagCreatedEventRepository.saveAndFlush(tagCreatedEvent);

        // Get all the tagCreatedEventList
        restTagCreatedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagCreatedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getTagCreatedEvent() throws Exception {
        // Initialize the database
        tagCreatedEventRepository.saveAndFlush(tagCreatedEvent);

        // Get the tagCreatedEvent
        restTagCreatedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, tagCreatedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tagCreatedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTagCreatedEvent() throws Exception {
        // Get the tagCreatedEvent
        restTagCreatedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
