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
import org.contextmapper.generated.questioncontext.domain.ResourceRefusedAssociationEvent;
import org.contextmapper.generated.questioncontext.repository.ResourceRefusedAssociationEventRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceRefusedAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceRefusedAssociationEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ResourceRefusedAssociationEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResourceRefusedAssociationEventResourceIT {

    private static final String ENTITY_API_URL = "/api/resource-refused-association-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResourceRefusedAssociationEventRepository resourceRefusedAssociationEventRepository;

    @Autowired
    private ResourceRefusedAssociationEventMapper resourceRefusedAssociationEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResourceRefusedAssociationEventMockMvc;

    private ResourceRefusedAssociationEvent resourceRefusedAssociationEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceRefusedAssociationEvent createEntity(EntityManager em) {
        ResourceRefusedAssociationEvent resourceRefusedAssociationEvent = new ResourceRefusedAssociationEvent();
        return resourceRefusedAssociationEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceRefusedAssociationEvent createUpdatedEntity(EntityManager em) {
        ResourceRefusedAssociationEvent resourceRefusedAssociationEvent = new ResourceRefusedAssociationEvent();
        return resourceRefusedAssociationEvent;
    }

    @BeforeEach
    public void initTest() {
        resourceRefusedAssociationEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllResourceRefusedAssociationEvents() throws Exception {
        // Initialize the database
        resourceRefusedAssociationEventRepository.saveAndFlush(resourceRefusedAssociationEvent);

        // Get all the resourceRefusedAssociationEventList
        restResourceRefusedAssociationEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourceRefusedAssociationEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getResourceRefusedAssociationEvent() throws Exception {
        // Initialize the database
        resourceRefusedAssociationEventRepository.saveAndFlush(resourceRefusedAssociationEvent);

        // Get the resourceRefusedAssociationEvent
        restResourceRefusedAssociationEventMockMvc
            .perform(get(ENTITY_API_URL_ID, resourceRefusedAssociationEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resourceRefusedAssociationEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingResourceRefusedAssociationEvent() throws Exception {
        // Get the resourceRefusedAssociationEvent
        restResourceRefusedAssociationEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
