package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.UserStatsViewedEvent;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.UserStatsViewedEventRepository;
import org.contextmapper.generated.gateway.service.dto.UserStatsViewedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.UserStatsViewedEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link UserStatsViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class UserStatsViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/user-stats-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserStatsViewedEventRepository userStatsViewedEventRepository;

    @Autowired
    private UserStatsViewedEventMapper userStatsViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private UserStatsViewedEvent userStatsViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserStatsViewedEvent createEntity(EntityManager em) {
        UserStatsViewedEvent userStatsViewedEvent = new UserStatsViewedEvent();
        return userStatsViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserStatsViewedEvent createUpdatedEntity(EntityManager em) {
        UserStatsViewedEvent userStatsViewedEvent = new UserStatsViewedEvent();
        return userStatsViewedEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(UserStatsViewedEvent.class).block();
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
        userStatsViewedEvent = createEntity(em);
    }

    @Test
    void getAllUserStatsViewedEventsAsStream() {
        // Initialize the database
        userStatsViewedEventRepository.save(userStatsViewedEvent).block();

        List<UserStatsViewedEvent> userStatsViewedEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(UserStatsViewedEventDTO.class)
            .getResponseBody()
            .map(userStatsViewedEventMapper::toEntity)
            .filter(userStatsViewedEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(userStatsViewedEventList).isNotNull();
        assertThat(userStatsViewedEventList).hasSize(1);
        UserStatsViewedEvent testUserStatsViewedEvent = userStatsViewedEventList.get(0);
    }

    @Test
    void getAllUserStatsViewedEvents() {
        // Initialize the database
        userStatsViewedEventRepository.save(userStatsViewedEvent).block();

        // Get all the userStatsViewedEventList
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
            .value(hasItem(userStatsViewedEvent.getId().intValue()));
    }

    @Test
    void getUserStatsViewedEvent() {
        // Initialize the database
        userStatsViewedEventRepository.save(userStatsViewedEvent).block();

        // Get the userStatsViewedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, userStatsViewedEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(userStatsViewedEvent.getId().intValue()));
    }

    @Test
    void getNonExistingUserStatsViewedEvent() {
        // Get the userStatsViewedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
