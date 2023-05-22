package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.EvaluationQuestion;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.EvaluationQuestionRepository;
import org.contextmapper.generated.gateway.service.dto.EvaluationQuestionDTO;
import org.contextmapper.generated.gateway.service.mapper.EvaluationQuestionMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link EvaluationQuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class EvaluationQuestionResourceIT {

    private static final Integer DEFAULT_QUESTION_ID = 1;
    private static final Integer UPDATED_QUESTION_ID = 2;

    private static final String ENTITY_API_URL = "/api/evaluation-questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvaluationQuestionRepository evaluationQuestionRepository;

    @Autowired
    private EvaluationQuestionMapper evaluationQuestionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private EvaluationQuestion evaluationQuestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationQuestion createEntity(EntityManager em) {
        EvaluationQuestion evaluationQuestion = new EvaluationQuestion().questionId(DEFAULT_QUESTION_ID);
        return evaluationQuestion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationQuestion createUpdatedEntity(EntityManager em) {
        EvaluationQuestion evaluationQuestion = new EvaluationQuestion().questionId(UPDATED_QUESTION_ID);
        return evaluationQuestion;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(EvaluationQuestion.class).block();
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
        evaluationQuestion = createEntity(em);
    }

    @Test
    void getAllEvaluationQuestionsAsStream() {
        // Initialize the database
        evaluationQuestionRepository.save(evaluationQuestion).block();

        List<EvaluationQuestion> evaluationQuestionList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(EvaluationQuestionDTO.class)
            .getResponseBody()
            .map(evaluationQuestionMapper::toEntity)
            .filter(evaluationQuestion::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(evaluationQuestionList).isNotNull();
        assertThat(evaluationQuestionList).hasSize(1);
        EvaluationQuestion testEvaluationQuestion = evaluationQuestionList.get(0);
        assertThat(testEvaluationQuestion.getQuestionId()).isEqualTo(DEFAULT_QUESTION_ID);
    }

    @Test
    void getAllEvaluationQuestions() {
        // Initialize the database
        evaluationQuestionRepository.save(evaluationQuestion).block();

        // Get all the evaluationQuestionList
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
            .value(hasItem(evaluationQuestion.getId().intValue()))
            .jsonPath("$.[*].questionId")
            .value(hasItem(DEFAULT_QUESTION_ID));
    }

    @Test
    void getEvaluationQuestion() {
        // Initialize the database
        evaluationQuestionRepository.save(evaluationQuestion).block();

        // Get the evaluationQuestion
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, evaluationQuestion.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(evaluationQuestion.getId().intValue()))
            .jsonPath("$.questionId")
            .value(is(DEFAULT_QUESTION_ID));
    }

    @Test
    void getNonExistingEvaluationQuestion() {
        // Get the evaluationQuestion
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
