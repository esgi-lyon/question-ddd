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
import org.contextmapper.generated.sendquestioncontext.domain.ResourceId;
import org.contextmapper.generated.sendquestioncontext.repository.ResourceIdRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.ResourceIdDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.ResourceIdMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ResourceIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResourceIdResourceIT {

    private static final String ENTITY_API_URL = "/api/resource-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResourceIdRepository resourceIdRepository;

    @Autowired
    private ResourceIdMapper resourceIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResourceIdMockMvc;

    private ResourceId resourceId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceId createEntity(EntityManager em) {
        ResourceId resourceId = new ResourceId();
        return resourceId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceId createUpdatedEntity(EntityManager em) {
        ResourceId resourceId = new ResourceId();
        return resourceId;
    }

    @BeforeEach
    public void initTest() {
        resourceId = createEntity(em);
    }

    @Test
    @Transactional
    void getAllResourceIds() throws Exception {
        // Initialize the database
        resourceIdRepository.saveAndFlush(resourceId);

        // Get all the resourceIdList
        restResourceIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourceId.getId().intValue())));
    }

    @Test
    @Transactional
    void getResourceId() throws Exception {
        // Initialize the database
        resourceIdRepository.saveAndFlush(resourceId);

        // Get the resourceId
        restResourceIdMockMvc
            .perform(get(ENTITY_API_URL_ID, resourceId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resourceId.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingResourceId() throws Exception {
        // Get the resourceId
        restResourceIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
