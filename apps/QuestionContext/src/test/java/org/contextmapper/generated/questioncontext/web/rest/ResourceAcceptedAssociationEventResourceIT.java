package org.contextmapper.generated.questioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.questioncontext.IntegrationTest;
import org.contextmapper.generated.questioncontext.domain.ResourceAcceptedAssociationEvent;
import org.contextmapper.generated.questioncontext.repository.ResourceAcceptedAssociationEventRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceAcceptedAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceAcceptedAssociationEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ResourceAcceptedAssociationEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResourceAcceptedAssociationEventResourceIT {

    private static final String ENTITY_API_URL = "/api/resource-accepted-association-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResourceAcceptedAssociationEventRepository resourceAcceptedAssociationEventRepository;

    @Autowired
    private ResourceAcceptedAssociationEventMapper resourceAcceptedAssociationEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResourceAcceptedAssociationEventMockMvc;

    private ResourceAcceptedAssociationEvent resourceAcceptedAssociationEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceAcceptedAssociationEvent createEntity(EntityManager em) {
        ResourceAcceptedAssociationEvent resourceAcceptedAssociationEvent = new ResourceAcceptedAssociationEvent();
        return resourceAcceptedAssociationEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceAcceptedAssociationEvent createUpdatedEntity(EntityManager em) {
        ResourceAcceptedAssociationEvent resourceAcceptedAssociationEvent = new ResourceAcceptedAssociationEvent();
        return resourceAcceptedAssociationEvent;
    }

    @BeforeEach
    public void initTest() {
        resourceAcceptedAssociationEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllResourceAcceptedAssociationEvents() throws Exception {
        // Initialize the database
        resourceAcceptedAssociationEventRepository.saveAndFlush(resourceAcceptedAssociationEvent);

        // Get all the resourceAcceptedAssociationEventList
        restResourceAcceptedAssociationEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourceAcceptedAssociationEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getResourceAcceptedAssociationEvent() throws Exception {
        // Initialize the database
        resourceAcceptedAssociationEventRepository.saveAndFlush(resourceAcceptedAssociationEvent);

        // Get the resourceAcceptedAssociationEvent
        restResourceAcceptedAssociationEventMockMvc
            .perform(get(ENTITY_API_URL_ID, resourceAcceptedAssociationEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resourceAcceptedAssociationEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingResourceAcceptedAssociationEvent() throws Exception {
        // Get the resourceAcceptedAssociationEvent
        restResourceAcceptedAssociationEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
