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
import org.contextmapper.generated.skillcontext.domain.CreatedById;
import org.contextmapper.generated.skillcontext.repository.CreatedByIdRepository;
import org.contextmapper.generated.skillcontext.service.dto.CreatedByIdDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CreatedByIdMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreatedByIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreatedByIdResourceIT {

    private static final String ENTITY_API_URL = "/api/created-by-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreatedByIdRepository createdByIdRepository;

    @Autowired
    private CreatedByIdMapper createdByIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreatedByIdMockMvc;

    private CreatedById createdById;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreatedById createEntity(EntityManager em) {
        CreatedById createdById = new CreatedById();
        return createdById;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreatedById createUpdatedEntity(EntityManager em) {
        CreatedById createdById = new CreatedById();
        return createdById;
    }

    @BeforeEach
    public void initTest() {
        createdById = createEntity(em);
    }

    @Test
    @Transactional
    void getAllCreatedByIds() throws Exception {
        // Initialize the database
        createdByIdRepository.saveAndFlush(createdById);

        // Get all the createdByIdList
        restCreatedByIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(createdById.getId().intValue())));
    }

    @Test
    @Transactional
    void getCreatedById() throws Exception {
        // Initialize the database
        createdByIdRepository.saveAndFlush(createdById);

        // Get the createdById
        restCreatedByIdMockMvc
            .perform(get(ENTITY_API_URL_ID, createdById.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(createdById.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCreatedById() throws Exception {
        // Get the createdById
        restCreatedByIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
