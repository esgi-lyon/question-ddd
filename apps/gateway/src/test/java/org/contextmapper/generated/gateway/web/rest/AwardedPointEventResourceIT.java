package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.AwardedPointEvent;
import org.contextmapper.generated.gateway.repository.AwardedPointEventRepository;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.service.dto.AwardedPointEventDTO;
import org.contextmapper.generated.gateway.service.mapper.AwardedPointEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link AwardedPointEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AwardedPointEventResourceIT {

    private static final String ENTITY_API_URL = "/api/awarded-point-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AwardedPointEventRepository awardedPointEventRepository;

    @Autowired
    private AwardedPointEventMapper awardedPointEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private AwardedPointEvent awardedPointEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AwardedPointEvent createEntity(EntityManager em) {
        AwardedPointEvent awardedPointEvent = new AwardedPointEvent();
        return awardedPointEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AwardedPointEvent createUpdatedEntity(EntityManager em) {
        AwardedPointEvent awardedPointEvent = new AwardedPointEvent();
        return awardedPointEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(AwardedPointEvent.class).block();
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
        awardedPointEvent = createEntity(em);
    }

    @Test
    void getAllAwardedPointEventsAsStream() {
        // Initialize the database
        awardedPointEventRepository.save(awardedPointEvent).block();

        List<AwardedPointEvent> awardedPointEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(AwardedPointEventDTO.class)
            .getResponseBody()
            .map(awardedPointEventMapper::toEntity)
            .filter(awardedPointEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(awardedPointEventList).isNotNull();
        assertThat(awardedPointEventList).hasSize(1);
        AwardedPointEvent testAwardedPointEvent = awardedPointEventList.get(0);
    }

    @Test
    void getAllAwardedPointEvents() {
        // Initialize the database
        awardedPointEventRepository.save(awardedPointEvent).block();

        // Get all the awardedPointEventList
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
            .value(hasItem(awardedPointEvent.getId().intValue()));
    }

    @Test
    void getAwardedPointEvent() {
        // Initialize the database
        awardedPointEventRepository.save(awardedPointEvent).block();

        // Get the awardedPointEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, awardedPointEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(awardedPointEvent.getId().intValue()));
    }

    @Test
    void getNonExistingAwardedPointEvent() {
        // Get the awardedPointEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
