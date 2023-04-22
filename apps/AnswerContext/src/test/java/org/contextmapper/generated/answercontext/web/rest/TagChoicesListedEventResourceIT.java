package org.contextmapper.generated.answercontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.answercontext.IntegrationTest;
import org.contextmapper.generated.answercontext.domain.TagChoicesListedEvent;
import org.contextmapper.generated.answercontext.repository.TagChoicesListedEventRepository;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListedEventDTO;
import org.contextmapper.generated.answercontext.service.mapper.TagChoicesListedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TagChoicesListedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TagChoicesListedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/tag-choices-listed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagChoicesListedEventRepository tagChoicesListedEventRepository;

    @Autowired
    private TagChoicesListedEventMapper tagChoicesListedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagChoicesListedEventMockMvc;

    private TagChoicesListedEvent tagChoicesListedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagChoicesListedEvent createEntity(EntityManager em) {
        TagChoicesListedEvent tagChoicesListedEvent = new TagChoicesListedEvent();
        return tagChoicesListedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagChoicesListedEvent createUpdatedEntity(EntityManager em) {
        TagChoicesListedEvent tagChoicesListedEvent = new TagChoicesListedEvent();
        return tagChoicesListedEvent;
    }

    @BeforeEach
    public void initTest() {
        tagChoicesListedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllTagChoicesListedEvents() throws Exception {
        // Initialize the database
        tagChoicesListedEventRepository.saveAndFlush(tagChoicesListedEvent);

        // Get all the tagChoicesListedEventList
        restTagChoicesListedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagChoicesListedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getTagChoicesListedEvent() throws Exception {
        // Initialize the database
        tagChoicesListedEventRepository.saveAndFlush(tagChoicesListedEvent);

        // Get the tagChoicesListedEvent
        restTagChoicesListedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, tagChoicesListedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tagChoicesListedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTagChoicesListedEvent() throws Exception {
        // Get the tagChoicesListedEvent
        restTagChoicesListedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
