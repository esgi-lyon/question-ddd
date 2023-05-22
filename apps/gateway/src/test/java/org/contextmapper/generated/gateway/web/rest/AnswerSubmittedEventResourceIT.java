package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.AnswerSubmittedEvent;
import org.contextmapper.generated.gateway.repository.AnswerSubmittedEventRepository;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.service.dto.AnswerSubmittedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.AnswerSubmittedEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link AnswerSubmittedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AnswerSubmittedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/answer-submitted-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnswerSubmittedEventRepository answerSubmittedEventRepository;

    @Autowired
    private AnswerSubmittedEventMapper answerSubmittedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private AnswerSubmittedEvent answerSubmittedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSubmittedEvent createEntity(EntityManager em) {
        AnswerSubmittedEvent answerSubmittedEvent = new AnswerSubmittedEvent();
        return answerSubmittedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSubmittedEvent createUpdatedEntity(EntityManager em) {
        AnswerSubmittedEvent answerSubmittedEvent = new AnswerSubmittedEvent();
        return answerSubmittedEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(AnswerSubmittedEvent.class).block();
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
        answerSubmittedEvent = createEntity(em);
    }

    @Test
    void getAllAnswerSubmittedEventsAsStream() {
        // Initialize the database
        answerSubmittedEventRepository.save(answerSubmittedEvent).block();

        List<AnswerSubmittedEvent> answerSubmittedEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(AnswerSubmittedEventDTO.class)
            .getResponseBody()
            .map(answerSubmittedEventMapper::toEntity)
            .filter(answerSubmittedEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(answerSubmittedEventList).isNotNull();
        assertThat(answerSubmittedEventList).hasSize(1);
        AnswerSubmittedEvent testAnswerSubmittedEvent = answerSubmittedEventList.get(0);
    }

    @Test
    void getAllAnswerSubmittedEvents() {
        // Initialize the database
        answerSubmittedEventRepository.save(answerSubmittedEvent).block();

        // Get all the answerSubmittedEventList
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
            .value(hasItem(answerSubmittedEvent.getId().intValue()));
    }

    @Test
    void getAnswerSubmittedEvent() {
        // Initialize the database
        answerSubmittedEventRepository.save(answerSubmittedEvent).block();

        // Get the answerSubmittedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, answerSubmittedEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(answerSubmittedEvent.getId().intValue()));
    }

    @Test
    void getNonExistingAnswerSubmittedEvent() {
        // Get the answerSubmittedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
