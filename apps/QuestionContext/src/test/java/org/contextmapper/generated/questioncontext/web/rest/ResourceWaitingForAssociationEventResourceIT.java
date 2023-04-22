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
import org.contextmapper.generated.questioncontext.domain.ResourceWaitingForAssociationEvent;
import org.contextmapper.generated.questioncontext.repository.ResourceWaitingForAssociationEventRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceWaitingForAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceWaitingForAssociationEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ResourceWaitingForAssociationEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResourceWaitingForAssociationEventResourceIT {

    private static final String ENTITY_API_URL = "/api/resource-waiting-for-association-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResourceWaitingForAssociationEventRepository resourceWaitingForAssociationEventRepository;

    @Autowired
    private ResourceWaitingForAssociationEventMapper resourceWaitingForAssociationEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResourceWaitingForAssociationEventMockMvc;

    private ResourceWaitingForAssociationEvent resourceWaitingForAssociationEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceWaitingForAssociationEvent createEntity(EntityManager em) {
        ResourceWaitingForAssociationEvent resourceWaitingForAssociationEvent = new ResourceWaitingForAssociationEvent();
        return resourceWaitingForAssociationEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceWaitingForAssociationEvent createUpdatedEntity(EntityManager em) {
        ResourceWaitingForAssociationEvent resourceWaitingForAssociationEvent = new ResourceWaitingForAssociationEvent();
        return resourceWaitingForAssociationEvent;
    }

    @BeforeEach
    public void initTest() {
        resourceWaitingForAssociationEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllResourceWaitingForAssociationEvents() throws Exception {
        // Initialize the database
        resourceWaitingForAssociationEventRepository.saveAndFlush(resourceWaitingForAssociationEvent);

        // Get all the resourceWaitingForAssociationEventList
        restResourceWaitingForAssociationEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourceWaitingForAssociationEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getResourceWaitingForAssociationEvent() throws Exception {
        // Initialize the database
        resourceWaitingForAssociationEventRepository.saveAndFlush(resourceWaitingForAssociationEvent);

        // Get the resourceWaitingForAssociationEvent
        restResourceWaitingForAssociationEventMockMvc
            .perform(get(ENTITY_API_URL_ID, resourceWaitingForAssociationEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resourceWaitingForAssociationEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingResourceWaitingForAssociationEvent() throws Exception {
        // Get the resourceWaitingForAssociationEvent
        restResourceWaitingForAssociationEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
