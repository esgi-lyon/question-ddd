package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.EvaluatedAnswer;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.EvaluatedAnswerRepository;
import org.contextmapper.generated.gateway.service.dto.EvaluatedAnswerDTO;
import org.contextmapper.generated.gateway.service.mapper.EvaluatedAnswerMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link EvaluatedAnswerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class EvaluatedAnswerResourceIT {

    private static final Integer DEFAULT_ANSWER_ID = 1;
    private static final Integer UPDATED_ANSWER_ID = 2;

    private static final String ENTITY_API_URL = "/api/evaluated-answers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvaluatedAnswerRepository evaluatedAnswerRepository;

    @Autowired
    private EvaluatedAnswerMapper evaluatedAnswerMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private EvaluatedAnswer evaluatedAnswer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluatedAnswer createEntity(EntityManager em) {
        EvaluatedAnswer evaluatedAnswer = new EvaluatedAnswer().answerId(DEFAULT_ANSWER_ID);
        return evaluatedAnswer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluatedAnswer createUpdatedEntity(EntityManager em) {
        EvaluatedAnswer evaluatedAnswer = new EvaluatedAnswer().answerId(UPDATED_ANSWER_ID);
        return evaluatedAnswer;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(EvaluatedAnswer.class).block();
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
        evaluatedAnswer = createEntity(em);
    }

    @Test
    void getAllEvaluatedAnswersAsStream() {
        // Initialize the database
        evaluatedAnswerRepository.save(evaluatedAnswer).block();

        List<EvaluatedAnswer> evaluatedAnswerList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(EvaluatedAnswerDTO.class)
            .getResponseBody()
            .map(evaluatedAnswerMapper::toEntity)
            .filter(evaluatedAnswer::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(evaluatedAnswerList).isNotNull();
        assertThat(evaluatedAnswerList).hasSize(1);
        EvaluatedAnswer testEvaluatedAnswer = evaluatedAnswerList.get(0);
        assertThat(testEvaluatedAnswer.getAnswerId()).isEqualTo(DEFAULT_ANSWER_ID);
    }

    @Test
    void getAllEvaluatedAnswers() {
        // Initialize the database
        evaluatedAnswerRepository.save(evaluatedAnswer).block();

        // Get all the evaluatedAnswerList
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
            .value(hasItem(evaluatedAnswer.getId().intValue()))
            .jsonPath("$.[*].answerId")
            .value(hasItem(DEFAULT_ANSWER_ID));
    }

    @Test
    void getEvaluatedAnswer() {
        // Initialize the database
        evaluatedAnswerRepository.save(evaluatedAnswer).block();

        // Get the evaluatedAnswer
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, evaluatedAnswer.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(evaluatedAnswer.getId().intValue()))
            .jsonPath("$.answerId")
            .value(is(DEFAULT_ANSWER_ID));
    }

    @Test
    void getNonExistingEvaluatedAnswer() {
        // Get the evaluatedAnswer
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
