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
import org.contextmapper.generated.skillcontext.domain.TagInfos;
import org.contextmapper.generated.skillcontext.repository.TagInfosRepository;
import org.contextmapper.generated.skillcontext.service.dto.TagInfosDTO;
import org.contextmapper.generated.skillcontext.service.mapper.TagInfosMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TagInfosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TagInfosResourceIT {

    private static final Long DEFAULT_TAG_ID = 1L;
    private static final Long UPDATED_TAG_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tag-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagInfosRepository tagInfosRepository;

    @Autowired
    private TagInfosMapper tagInfosMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagInfosMockMvc;

    private TagInfos tagInfos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagInfos createEntity(EntityManager em) {
        TagInfos tagInfos = new TagInfos().tagId(DEFAULT_TAG_ID).name(DEFAULT_NAME);
        return tagInfos;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagInfos createUpdatedEntity(EntityManager em) {
        TagInfos tagInfos = new TagInfos().tagId(UPDATED_TAG_ID).name(UPDATED_NAME);
        return tagInfos;
    }

    @BeforeEach
    public void initTest() {
        tagInfos = createEntity(em);
    }

    @Test
    @Transactional
    void getAllTagInfos() throws Exception {
        // Initialize the database
        tagInfosRepository.saveAndFlush(tagInfos);

        // Get all the tagInfosList
        restTagInfosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagInfos.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getTagInfos() throws Exception {
        // Initialize the database
        tagInfosRepository.saveAndFlush(tagInfos);

        // Get the tagInfos
        restTagInfosMockMvc
            .perform(get(ENTITY_API_URL_ID, tagInfos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tagInfos.getId().intValue()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingTagInfos() throws Exception {
        // Get the tagInfos
        restTagInfosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
