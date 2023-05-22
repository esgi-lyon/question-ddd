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
import org.contextmapper.generated.skillcontext.domain.TagWithCategory;
import org.contextmapper.generated.skillcontext.repository.TagWithCategoryRepository;
import org.contextmapper.generated.skillcontext.service.dto.TagWithCategoryDTO;
import org.contextmapper.generated.skillcontext.service.mapper.TagWithCategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TagWithCategoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TagWithCategoryResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_CATEGORY = 1L;
    private static final Long UPDATED_CATEGORY = 2L;

    private static final String ENTITY_API_URL = "/api/tag-with-categories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagWithCategoryRepository tagWithCategoryRepository;

    @Autowired
    private TagWithCategoryMapper tagWithCategoryMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagWithCategoryMockMvc;

    private TagWithCategory tagWithCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagWithCategory createEntity(EntityManager em) {
        TagWithCategory tagWithCategory = new TagWithCategory().name(DEFAULT_NAME).category(DEFAULT_CATEGORY);
        return tagWithCategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagWithCategory createUpdatedEntity(EntityManager em) {
        TagWithCategory tagWithCategory = new TagWithCategory().name(UPDATED_NAME).category(UPDATED_CATEGORY);
        return tagWithCategory;
    }

    @BeforeEach
    public void initTest() {
        tagWithCategory = createEntity(em);
    }

    @Test
    @Transactional
    void getAllTagWithCategories() throws Exception {
        // Initialize the database
        tagWithCategoryRepository.saveAndFlush(tagWithCategory);

        // Get all the tagWithCategoryList
        restTagWithCategoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagWithCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.intValue())));
    }

    @Test
    @Transactional
    void getTagWithCategory() throws Exception {
        // Initialize the database
        tagWithCategoryRepository.saveAndFlush(tagWithCategory);

        // Get the tagWithCategory
        restTagWithCategoryMockMvc
            .perform(get(ENTITY_API_URL_ID, tagWithCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tagWithCategory.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTagWithCategory() throws Exception {
        // Get the tagWithCategory
        restTagWithCategoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
