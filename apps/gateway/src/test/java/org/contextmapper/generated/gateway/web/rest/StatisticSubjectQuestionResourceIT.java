package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.StatisticSubjectQuestion;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.StatisticSubjectQuestionRepository;
import org.contextmapper.generated.gateway.service.dto.StatisticSubjectQuestionDTO;
import org.contextmapper.generated.gateway.service.mapper.StatisticSubjectQuestionMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link StatisticSubjectQuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class StatisticSubjectQuestionResourceIT {

    private static final Integer DEFAULT_QUESTION_ID = 1;
    private static final Integer UPDATED_QUESTION_ID = 2;

    private static final String ENTITY_API_URL = "/api/statistic-subject-questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StatisticSubjectQuestionRepository statisticSubjectQuestionRepository;

    @Autowired
    private StatisticSubjectQuestionMapper statisticSubjectQuestionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private StatisticSubjectQuestion statisticSubjectQuestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticSubjectQuestion createEntity(EntityManager em) {
        StatisticSubjectQuestion statisticSubjectQuestion = new StatisticSubjectQuestion().questionId(DEFAULT_QUESTION_ID);
        return statisticSubjectQuestion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticSubjectQuestion createUpdatedEntity(EntityManager em) {
        StatisticSubjectQuestion statisticSubjectQuestion = new StatisticSubjectQuestion().questionId(UPDATED_QUESTION_ID);
        return statisticSubjectQuestion;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(StatisticSubjectQuestion.class).block();
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
        statisticSubjectQuestion = createEntity(em);
    }

    @Test
    void getAllStatisticSubjectQuestionsAsStream() {
        // Initialize the database
        statisticSubjectQuestionRepository.save(statisticSubjectQuestion).block();

        List<StatisticSubjectQuestion> statisticSubjectQuestionList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(StatisticSubjectQuestionDTO.class)
            .getResponseBody()
            .map(statisticSubjectQuestionMapper::toEntity)
            .filter(statisticSubjectQuestion::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(statisticSubjectQuestionList).isNotNull();
        assertThat(statisticSubjectQuestionList).hasSize(1);
        StatisticSubjectQuestion testStatisticSubjectQuestion = statisticSubjectQuestionList.get(0);
        assertThat(testStatisticSubjectQuestion.getQuestionId()).isEqualTo(DEFAULT_QUESTION_ID);
    }

    @Test
    void getAllStatisticSubjectQuestions() {
        // Initialize the database
        statisticSubjectQuestionRepository.save(statisticSubjectQuestion).block();

        // Get all the statisticSubjectQuestionList
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
            .value(hasItem(statisticSubjectQuestion.getId().intValue()))
            .jsonPath("$.[*].questionId")
            .value(hasItem(DEFAULT_QUESTION_ID));
    }

    @Test
    void getStatisticSubjectQuestion() {
        // Initialize the database
        statisticSubjectQuestionRepository.save(statisticSubjectQuestion).block();

        // Get the statisticSubjectQuestion
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, statisticSubjectQuestion.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(statisticSubjectQuestion.getId().intValue()))
            .jsonPath("$.questionId")
            .value(is(DEFAULT_QUESTION_ID));
    }

    @Test
    void getNonExistingStatisticSubjectQuestion() {
        // Get the statisticSubjectQuestion
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
