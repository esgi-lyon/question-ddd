package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.EvaluationCreatedEvent;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.EvaluationCreatedEventRepository;
import org.contextmapper.generated.gateway.service.dto.EvaluationCreatedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.EvaluationCreatedEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link EvaluationCreatedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class EvaluationCreatedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/evaluation-created-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EvaluationCreatedEventRepository evaluationCreatedEventRepository;

    @Autowired
    private EvaluationCreatedEventMapper evaluationCreatedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private EvaluationCreatedEvent evaluationCreatedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationCreatedEvent createEntity(EntityManager em) {
        EvaluationCreatedEvent evaluationCreatedEvent = new EvaluationCreatedEvent();
        return evaluationCreatedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationCreatedEvent createUpdatedEntity(EntityManager em) {
        EvaluationCreatedEvent evaluationCreatedEvent = new EvaluationCreatedEvent();
        return evaluationCreatedEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(EvaluationCreatedEvent.class).block();
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
        evaluationCreatedEvent = createEntity(em);
    }

    @Test
    void getAllEvaluationCreatedEventsAsStream() {
        // Initialize the database
        evaluationCreatedEventRepository.save(evaluationCreatedEvent).block();

        List<EvaluationCreatedEvent> evaluationCreatedEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(EvaluationCreatedEventDTO.class)
            .getResponseBody()
            .map(evaluationCreatedEventMapper::toEntity)
            .filter(evaluationCreatedEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(evaluationCreatedEventList).isNotNull();
        assertThat(evaluationCreatedEventList).hasSize(1);
        EvaluationCreatedEvent testEvaluationCreatedEvent = evaluationCreatedEventList.get(0);
    }

    @Test
    void getAllEvaluationCreatedEvents() {
        // Initialize the database
        evaluationCreatedEventRepository.save(evaluationCreatedEvent).block();

        // Get all the evaluationCreatedEventList
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
            .value(hasItem(evaluationCreatedEvent.getId().intValue()));
    }

    @Test
    void getEvaluationCreatedEvent() {
        // Initialize the database
        evaluationCreatedEventRepository.save(evaluationCreatedEvent).block();

        // Get the evaluationCreatedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, evaluationCreatedEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(evaluationCreatedEvent.getId().intValue()));
    }

    @Test
    void getNonExistingEvaluationCreatedEvent() {
        // Get the evaluationCreatedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
