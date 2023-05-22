package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.QuestionResourceTagInfos;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.QuestionResourceTagInfosRepository;
import org.contextmapper.generated.gateway.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.gateway.service.mapper.QuestionResourceTagInfosMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link QuestionResourceTagInfosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class QuestionResourceTagInfosResourceIT {

    private static final Integer DEFAULT_TAG_ID = 1;
    private static final Integer UPDATED_TAG_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/question-resource-tag-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionResourceTagInfosRepository questionResourceTagInfosRepository;

    @Autowired
    private QuestionResourceTagInfosMapper questionResourceTagInfosMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private QuestionResourceTagInfos questionResourceTagInfos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionResourceTagInfos createEntity(EntityManager em) {
        QuestionResourceTagInfos questionResourceTagInfos = new QuestionResourceTagInfos().tagId(DEFAULT_TAG_ID).name(DEFAULT_NAME);
        return questionResourceTagInfos;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionResourceTagInfos createUpdatedEntity(EntityManager em) {
        QuestionResourceTagInfos questionResourceTagInfos = new QuestionResourceTagInfos().tagId(UPDATED_TAG_ID).name(UPDATED_NAME);
        return questionResourceTagInfos;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(QuestionResourceTagInfos.class).block();
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
        questionResourceTagInfos = createEntity(em);
    }

    @Test
    void getAllQuestionResourceTagInfosAsStream() {
        // Initialize the database
        questionResourceTagInfosRepository.save(questionResourceTagInfos).block();

        List<QuestionResourceTagInfos> questionResourceTagInfosList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(QuestionResourceTagInfosDTO.class)
            .getResponseBody()
            .map(questionResourceTagInfosMapper::toEntity)
            .filter(questionResourceTagInfos::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(questionResourceTagInfosList).isNotNull();
        assertThat(questionResourceTagInfosList).hasSize(1);
        QuestionResourceTagInfos testQuestionResourceTagInfos = questionResourceTagInfosList.get(0);
        assertThat(testQuestionResourceTagInfos.getTagId()).isEqualTo(DEFAULT_TAG_ID);
        assertThat(testQuestionResourceTagInfos.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    void getAllQuestionResourceTagInfos() {
        // Initialize the database
        questionResourceTagInfosRepository.save(questionResourceTagInfos).block();

        // Get all the questionResourceTagInfosList
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
            .value(hasItem(questionResourceTagInfos.getId().intValue()))
            .jsonPath("$.[*].tagId")
            .value(hasItem(DEFAULT_TAG_ID))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME));
    }

    @Test
    void getQuestionResourceTagInfos() {
        // Initialize the database
        questionResourceTagInfosRepository.save(questionResourceTagInfos).block();

        // Get the questionResourceTagInfos
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, questionResourceTagInfos.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(questionResourceTagInfos.getId().intValue()))
            .jsonPath("$.tagId")
            .value(is(DEFAULT_TAG_ID))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME));
    }

    @Test
    void getNonExistingQuestionResourceTagInfos() {
        // Get the questionResourceTagInfos
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}