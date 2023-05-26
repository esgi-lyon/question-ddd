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
import org.contextmapper.generated.answercontext.domain.AnsweredTag;
import org.contextmapper.generated.answercontext.repository.AnsweredTagRepository;
import org.contextmapper.generated.answercontext.service.dto.AnsweredTagDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnsweredTagMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AnsweredTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnsweredTagResourceIT {

    private static final Long DEFAULT_TAG_ID = 1L;
    private static final Long UPDATED_TAG_ID = 2L;

    private static final String ENTITY_API_URL = "/api/answered-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnsweredTagRepository answeredTagRepository;

    @Autowired
    private AnsweredTagMapper answeredTagMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnsweredTagMockMvc;

    private AnsweredTag answeredTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnsweredTag createEntity(EntityManager em) {
        AnsweredTag answeredTag = new AnsweredTag().tagId(DEFAULT_TAG_ID);
        return answeredTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnsweredTag createUpdatedEntity(EntityManager em) {
        AnsweredTag answeredTag = new AnsweredTag().tagId(UPDATED_TAG_ID);
        return answeredTag;
    }

    @BeforeEach
    public void initTest() {
        answeredTag = createEntity(em);
    }

    @Test
    @Transactional
    void getAllAnsweredTags() throws Exception {
        // Initialize the database
        answeredTagRepository.saveAndFlush(answeredTag);

        // Get all the answeredTagList
        restAnsweredTagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answeredTag.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID.intValue())));
    }

    @Test
    @Transactional
    void getAnsweredTag() throws Exception {
        // Initialize the database
        answeredTagRepository.saveAndFlush(answeredTag);

        // Get the answeredTag
        restAnsweredTagMockMvc
            .perform(get(ENTITY_API_URL_ID, answeredTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(answeredTag.getId().intValue()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAnsweredTag() throws Exception {
        // Get the answeredTag
        restAnsweredTagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
