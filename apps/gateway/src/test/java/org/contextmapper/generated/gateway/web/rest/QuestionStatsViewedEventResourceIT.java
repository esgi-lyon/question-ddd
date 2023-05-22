package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.QuestionStatsViewedEvent;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.QuestionStatsViewedEventRepository;
import org.contextmapper.generated.gateway.service.dto.QuestionStatsViewedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.QuestionStatsViewedEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link QuestionStatsViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class QuestionStatsViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/question-stats-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionStatsViewedEventRepository questionStatsViewedEventRepository;

    @Autowired
    private QuestionStatsViewedEventMapper questionStatsViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private QuestionStatsViewedEvent questionStatsViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionStatsViewedEvent createEntity(EntityManager em) {
        QuestionStatsViewedEvent questionStatsViewedEvent = new QuestionStatsViewedEvent();
        return questionStatsViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionStatsViewedEvent createUpdatedEntity(EntityManager em) {
        QuestionStatsViewedEvent questionStatsViewedEvent = new QuestionStatsViewedEvent();
        return questionStatsViewedEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(QuestionStatsViewedEvent.class).block();
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
        questionStatsViewedEvent = createEntity(em);
    }

    @Test
    void getAllQuestionStatsViewedEventsAsStream() {
        // Initialize the database
        questionStatsViewedEventRepository.save(questionStatsViewedEvent).block();

        List<QuestionStatsViewedEvent> questionStatsViewedEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(QuestionStatsViewedEventDTO.class)
            .getResponseBody()
            .map(questionStatsViewedEventMapper::toEntity)
            .filter(questionStatsViewedEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(questionStatsViewedEventList).isNotNull();
        assertThat(questionStatsViewedEventList).hasSize(1);
        QuestionStatsViewedEvent testQuestionStatsViewedEvent = questionStatsViewedEventList.get(0);
    }

    @Test
    void getAllQuestionStatsViewedEvents() {
        // Initialize the database
        questionStatsViewedEventRepository.save(questionStatsViewedEvent).block();

        // Get all the questionStatsViewedEventList
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
            .value(hasItem(questionStatsViewedEvent.getId().intValue()));
    }

    @Test
    void getQuestionStatsViewedEvent() {
        // Initialize the database
        questionStatsViewedEventRepository.save(questionStatsViewedEvent).block();

        // Get the questionStatsViewedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, questionStatsViewedEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(questionStatsViewedEvent.getId().intValue()));
    }

    @Test
    void getNonExistingQuestionStatsViewedEvent() {
        // Get the questionStatsViewedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
