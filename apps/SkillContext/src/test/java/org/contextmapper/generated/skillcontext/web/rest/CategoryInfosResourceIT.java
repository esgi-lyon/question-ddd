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
import org.contextmapper.generated.skillcontext.domain.CategoryInfos;
import org.contextmapper.generated.skillcontext.repository.CategoryInfosRepository;
import org.contextmapper.generated.skillcontext.service.dto.CategoryInfosDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CategoryInfosMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CategoryInfosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CategoryInfosResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/category-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CategoryInfosRepository categoryInfosRepository;

    @Autowired
    private CategoryInfosMapper categoryInfosMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategoryInfosMockMvc;

    private CategoryInfos categoryInfos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryInfos createEntity(EntityManager em) {
        CategoryInfos categoryInfos = new CategoryInfos().name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION);
        return categoryInfos;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryInfos createUpdatedEntity(EntityManager em) {
        CategoryInfos categoryInfos = new CategoryInfos().name(UPDATED_NAME).description(UPDATED_DESCRIPTION);
        return categoryInfos;
    }

    @BeforeEach
    public void initTest() {
        categoryInfos = createEntity(em);
    }

    @Test
    @Transactional
    void getAllCategoryInfos() throws Exception {
        // Initialize the database
        categoryInfosRepository.saveAndFlush(categoryInfos);

        // Get all the categoryInfosList
        restCategoryInfosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoryInfos.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getCategoryInfos() throws Exception {
        // Initialize the database
        categoryInfosRepository.saveAndFlush(categoryInfos);

        // Get the categoryInfos
        restCategoryInfosMockMvc
            .perform(get(ENTITY_API_URL_ID, categoryInfos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categoryInfos.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingCategoryInfos() throws Exception {
        // Get the categoryInfos
        restCategoryInfosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
