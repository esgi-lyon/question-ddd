package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.QuestionSentTagInfos;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.QuestionSentTagInfosRepository;
import org.contextmapper.generated.gateway.service.dto.QuestionSentTagInfosDTO;
import org.contextmapper.generated.gateway.service.mapper.QuestionSentTagInfosMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link QuestionSentTagInfosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class QuestionSentTagInfosResourceIT {

    private static final Integer DEFAULT_TAG_ID = 1;
    private static final Integer UPDATED_TAG_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/question-sent-tag-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionSentTagInfosRepository questionSentTagInfosRepository;

    @Autowired
    private QuestionSentTagInfosMapper questionSentTagInfosMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private QuestionSentTagInfos questionSentTagInfos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentTagInfos createEntity(EntityManager em) {
        QuestionSentTagInfos questionSentTagInfos = new QuestionSentTagInfos().tagId(DEFAULT_TAG_ID).name(DEFAULT_NAME);
        return questionSentTagInfos;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentTagInfos createUpdatedEntity(EntityManager em) {
        QuestionSentTagInfos questionSentTagInfos = new QuestionSentTagInfos().tagId(UPDATED_TAG_ID).name(UPDATED_NAME);
        return questionSentTagInfos;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(QuestionSentTagInfos.class).block();
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
        questionSentTagInfos = createEntity(em);
    }

    @Test
    void getAllQuestionSentTagInfosAsStream() {
        // Initialize the database
        questionSentTagInfosRepository.save(questionSentTagInfos).block();

        List<QuestionSentTagInfos> questionSentTagInfosList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(QuestionSentTagInfosDTO.class)
            .getResponseBody()
            .map(questionSentTagInfosMapper::toEntity)
            .filter(questionSentTagInfos::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(questionSentTagInfosList).isNotNull();
        assertThat(questionSentTagInfosList).hasSize(1);
        QuestionSentTagInfos testQuestionSentTagInfos = questionSentTagInfosList.get(0);
        assertThat(testQuestionSentTagInfos.getTagId()).isEqualTo(DEFAULT_TAG_ID);
        assertThat(testQuestionSentTagInfos.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    void getAllQuestionSentTagInfos() {
        // Initialize the database
        questionSentTagInfosRepository.save(questionSentTagInfos).block();

        // Get all the questionSentTagInfosList
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
            .value(hasItem(questionSentTagInfos.getId().intValue()))
            .jsonPath("$.[*].tagId")
            .value(hasItem(DEFAULT_TAG_ID))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME));
    }

    @Test
    void getQuestionSentTagInfos() {
        // Initialize the database
        questionSentTagInfosRepository.save(questionSentTagInfos).block();

        // Get the questionSentTagInfos
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, questionSentTagInfos.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(questionSentTagInfos.getId().intValue()))
            .jsonPath("$.tagId")
            .value(is(DEFAULT_TAG_ID))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME));
    }

    @Test
    void getNonExistingQuestionSentTagInfos() {
        // Get the questionSentTagInfos
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
