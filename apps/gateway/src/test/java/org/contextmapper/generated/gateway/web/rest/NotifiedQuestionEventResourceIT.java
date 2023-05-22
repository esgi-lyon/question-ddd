package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.NotifiedQuestionEvent;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.NotifiedQuestionEventRepository;
import org.contextmapper.generated.gateway.service.dto.NotifiedQuestionEventDTO;
import org.contextmapper.generated.gateway.service.mapper.NotifiedQuestionEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link NotifiedQuestionEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class NotifiedQuestionEventResourceIT {

    private static final String ENTITY_API_URL = "/api/notified-question-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NotifiedQuestionEventRepository notifiedQuestionEventRepository;

    @Autowired
    private NotifiedQuestionEventMapper notifiedQuestionEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private NotifiedQuestionEvent notifiedQuestionEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifiedQuestionEvent createEntity(EntityManager em) {
        NotifiedQuestionEvent notifiedQuestionEvent = new NotifiedQuestionEvent();
        return notifiedQuestionEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifiedQuestionEvent createUpdatedEntity(EntityManager em) {
        NotifiedQuestionEvent notifiedQuestionEvent = new NotifiedQuestionEvent();
        return notifiedQuestionEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(NotifiedQuestionEvent.class).block();
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
        notifiedQuestionEvent = createEntity(em);
    }

    @Test
    void getAllNotifiedQuestionEventsAsStream() {
        // Initialize the database
        notifiedQuestionEventRepository.save(notifiedQuestionEvent).block();

        List<NotifiedQuestionEvent> notifiedQuestionEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(NotifiedQuestionEventDTO.class)
            .getResponseBody()
            .map(notifiedQuestionEventMapper::toEntity)
            .filter(notifiedQuestionEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(notifiedQuestionEventList).isNotNull();
        assertThat(notifiedQuestionEventList).hasSize(1);
        NotifiedQuestionEvent testNotifiedQuestionEvent = notifiedQuestionEventList.get(0);
    }

    @Test
    void getAllNotifiedQuestionEvents() {
        // Initialize the database
        notifiedQuestionEventRepository.save(notifiedQuestionEvent).block();

        // Get all the notifiedQuestionEventList
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
            .value(hasItem(notifiedQuestionEvent.getId().intValue()));
    }

    @Test
    void getNotifiedQuestionEvent() {
        // Initialize the database
        notifiedQuestionEventRepository.save(notifiedQuestionEvent).block();

        // Get the notifiedQuestionEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, notifiedQuestionEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(notifiedQuestionEvent.getId().intValue()));
    }

    @Test
    void getNonExistingNotifiedQuestionEvent() {
        // Get the notifiedQuestionEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
