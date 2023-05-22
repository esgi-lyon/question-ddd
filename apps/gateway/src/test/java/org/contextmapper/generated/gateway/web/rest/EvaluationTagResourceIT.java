package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.EvaluationTag;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.EvaluationTagRepository;
import org.contextmapper.generated.gateway.service.dto.EvaluationTagDTO;
import org.contextmapper.generated.gateway.service.mapper.EvaluationTagMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link EvaluationTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class EvaluationTagResourceIT {

    private static final Integer DEFAULT_TAG_ID = 1;
    private static final Integer UPDATED_TAG_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/evaluation-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvaluationTagRepository evaluationTagRepository;

    @Autowired
    private EvaluationTagMapper evaluationTagMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private EvaluationTag evaluationTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationTag createEntity(EntityManager em) {
        EvaluationTag evaluationTag = new EvaluationTag().tagId(DEFAULT_TAG_ID).name(DEFAULT_NAME);
        return evaluationTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationTag createUpdatedEntity(EntityManager em) {
        EvaluationTag evaluationTag = new EvaluationTag().tagId(UPDATED_TAG_ID).name(UPDATED_NAME);
        return evaluationTag;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(EvaluationTag.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntities(em);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        evaluationTag = createEntity(em);
    }

    @Test
    void getAllEvaluationTagsAsStream() {
        // Initialize the database
        evaluationTagRepository.save(evaluationTag).block();

        List<EvaluationTag> evaluationTagList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(EvaluationTagDTO.class)
            .getResponseBody()
            .map(evaluationTagMapper::toEntity)
            .filter(evaluationTag::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(evaluationTagList).isNotNull();
        assertThat(evaluationTagList).hasSize(1);
        EvaluationTag testEvaluationTag = evaluationTagList.get(0);
        assertThat(testEvaluationTag.getTagId()).isEqualTo(DEFAULT_TAG_ID);
        assertThat(testEvaluationTag.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    void getAllEvaluationTags() {
        // Initialize the database
        evaluationTagRepository.save(evaluationTag).block();

        // Get all the evaluationTagList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(evaluationTag.getId().intValue()))
            .jsonPath("$.[*].tagId")
            .value(hasItem(DEFAULT_TAG_ID))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME));
    }

    @Test
    void getEvaluationTag() {
        // Initialize the database
        evaluationTagRepository.save(evaluationTag).block();

        // Get the evaluationTag
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, evaluationTag.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(evaluationTag.getId().intValue()))
            .jsonPath("$.tagId")
            .value(is(DEFAULT_TAG_ID))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME));
    }

    @Test
    void getNonExistingEvaluationTag() {
        // Get the evaluationTag
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
