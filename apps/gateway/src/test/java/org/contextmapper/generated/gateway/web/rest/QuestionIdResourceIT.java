package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.QuestionId;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.QuestionIdRepository;
import org.contextmapper.generated.gateway.service.dto.QuestionIdDTO;
import org.contextmapper.generated.gateway.service.mapper.QuestionIdMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link QuestionIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class QuestionIdResourceIT {

    private static final Integer DEFAULT_QUESTION_ID = 1;
    private static final Integer UPDATED_QUESTION_ID = 2;

    private static final String ENTITY_API_URL = "/api/question-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionIdRepository questionIdRepository;

    @Autowired
    private QuestionIdMapper questionIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private QuestionId questionId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionId createEntity(EntityManager em) {
        QuestionId questionId = new QuestionId().questionId(DEFAULT_QUESTION_ID);
        return questionId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionId createUpdatedEntity(EntityManager em) {
        QuestionId questionId = new QuestionId().questionId(UPDATED_QUESTION_ID);
        return questionId;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(QuestionId.class).block();
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
        questionId = createEntity(em);
    }

    @Test
    void getAllQuestionIdsAsStream() {
        // Initialize the database
        questionIdRepository.save(questionId).block();

        List<QuestionId> questionIdList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(QuestionIdDTO.class)
            .getResponseBody()
            .map(questionIdMapper::toEntity)
            .filter(questionId::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(questionIdList).isNotNull();
        assertThat(questionIdList).hasSize(1);
        QuestionId testQuestionId = questionIdList.get(0);
        assertThat(testQuestionId.getQuestionId()).isEqualTo(DEFAULT_QUESTION_ID);
    }

    @Test
    void getAllQuestionIds() {
        // Initialize the database
        questionIdRepository.save(questionId).block();

        // Get all the questionIdList
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
            .value(hasItem(questionId.getId().intValue()))
            .jsonPath("$.[*].questionId")
            .value(hasItem(DEFAULT_QUESTION_ID));
    }

    @Test
    void getQuestionId() {
        // Initialize the database
        questionIdRepository.save(questionId).block();

        // Get the questionId
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, questionId.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(questionId.getId().intValue()))
            .jsonPath("$.questionId")
            .value(is(DEFAULT_QUESTION_ID));
    }

    @Test
    void getNonExistingQuestionId() {
        // Get the questionId
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
