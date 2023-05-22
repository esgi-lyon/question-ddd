package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.CreatedQuestionEvent;
import org.contextmapper.generated.gateway.repository.CreatedQuestionEventRepository;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.service.dto.CreatedQuestionEventDTO;
import org.contextmapper.generated.gateway.service.mapper.CreatedQuestionEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link CreatedQuestionEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CreatedQuestionEventResourceIT {

    private static final String ENTITY_API_URL = "/api/created-question-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreatedQuestionEventRepository createdQuestionEventRepository;

    @Autowired
    private CreatedQuestionEventMapper createdQuestionEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CreatedQuestionEvent createdQuestionEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreatedQuestionEvent createEntity(EntityManager em) {
        CreatedQuestionEvent createdQuestionEvent = new CreatedQuestionEvent();
        return createdQuestionEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreatedQuestionEvent createUpdatedEntity(EntityManager em) {
        CreatedQuestionEvent createdQuestionEvent = new CreatedQuestionEvent();
        return createdQuestionEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CreatedQuestionEvent.class).block();
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
        createdQuestionEvent = createEntity(em);
    }

    @Test
    void getAllCreatedQuestionEventsAsStream() {
        // Initialize the database
        createdQuestionEventRepository.save(createdQuestionEvent).block();

        List<CreatedQuestionEvent> createdQuestionEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(CreatedQuestionEventDTO.class)
            .getResponseBody()
            .map(createdQuestionEventMapper::toEntity)
            .filter(createdQuestionEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(createdQuestionEventList).isNotNull();
        assertThat(createdQuestionEventList).hasSize(1);
        CreatedQuestionEvent testCreatedQuestionEvent = createdQuestionEventList.get(0);
    }

    @Test
    void getAllCreatedQuestionEvents() {
        // Initialize the database
        createdQuestionEventRepository.save(createdQuestionEvent).block();

        // Get all the createdQuestionEventList
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
            .value(hasItem(createdQuestionEvent.getId().intValue()));
    }

    @Test
    void getCreatedQuestionEvent() {
        // Initialize the database
        createdQuestionEventRepository.save(createdQuestionEvent).block();

        // Get the createdQuestionEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, createdQuestionEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(createdQuestionEvent.getId().intValue()));
    }

    @Test
    void getNonExistingCreatedQuestionEvent() {
        // Get the createdQuestionEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
