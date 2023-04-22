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
import org.contextmapper.generated.skillcontext.domain.CategoryCreatedEvent;
import org.contextmapper.generated.skillcontext.repository.CategoryCreatedEventRepository;
import org.contextmapper.generated.skillcontext.service.dto.CategoryCreatedEventDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CategoryCreatedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CategoryCreatedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CategoryCreatedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/category-created-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CategoryCreatedEventRepository categoryCreatedEventRepository;

    @Autowired
    private CategoryCreatedEventMapper categoryCreatedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategoryCreatedEventMockMvc;

    private CategoryCreatedEvent categoryCreatedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryCreatedEvent createEntity(EntityManager em) {
        CategoryCreatedEvent categoryCreatedEvent = new CategoryCreatedEvent();
        return categoryCreatedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryCreatedEvent createUpdatedEntity(EntityManager em) {
        CategoryCreatedEvent categoryCreatedEvent = new CategoryCreatedEvent();
        return categoryCreatedEvent;
    }

    @BeforeEach
    public void initTest() {
        categoryCreatedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllCategoryCreatedEvents() throws Exception {
        // Initialize the database
        categoryCreatedEventRepository.saveAndFlush(categoryCreatedEvent);

        // Get all the categoryCreatedEventList
        restCategoryCreatedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoryCreatedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getCategoryCreatedEvent() throws Exception {
        // Initialize the database
        categoryCreatedEventRepository.saveAndFlush(categoryCreatedEvent);

        // Get the categoryCreatedEvent
        restCategoryCreatedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, categoryCreatedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categoryCreatedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCategoryCreatedEvent() throws Exception {
        // Get the categoryCreatedEvent
        restCategoryCreatedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
