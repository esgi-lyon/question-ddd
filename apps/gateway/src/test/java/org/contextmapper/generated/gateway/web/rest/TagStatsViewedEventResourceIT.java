package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.TagStatsViewedEvent;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.TagStatsViewedEventRepository;
import org.contextmapper.generated.gateway.service.dto.TagStatsViewedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.TagStatsViewedEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link TagStatsViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class TagStatsViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/tag-stats-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagStatsViewedEventRepository tagStatsViewedEventRepository;

    @Autowired
    private TagStatsViewedEventMapper tagStatsViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private TagStatsViewedEvent tagStatsViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagStatsViewedEvent createEntity(EntityManager em) {
        TagStatsViewedEvent tagStatsViewedEvent = new TagStatsViewedEvent();
        return tagStatsViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagStatsViewedEvent createUpdatedEntity(EntityManager em) {
        TagStatsViewedEvent tagStatsViewedEvent = new TagStatsViewedEvent();
        return tagStatsViewedEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(TagStatsViewedEvent.class).block();
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
        tagStatsViewedEvent = createEntity(em);
    }

    @Test
    void getAllTagStatsViewedEventsAsStream() {
        // Initialize the database
        tagStatsViewedEventRepository.save(tagStatsViewedEvent).block();

        List<TagStatsViewedEvent> tagStatsViewedEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(TagStatsViewedEventDTO.class)
            .getResponseBody()
            .map(tagStatsViewedEventMapper::toEntity)
            .filter(tagStatsViewedEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(tagStatsViewedEventList).isNotNull();
        assertThat(tagStatsViewedEventList).hasSize(1);
        TagStatsViewedEvent testTagStatsViewedEvent = tagStatsViewedEventList.get(0);
    }

    @Test
    void getAllTagStatsViewedEvents() {
        // Initialize the database
        tagStatsViewedEventRepository.save(tagStatsViewedEvent).block();

        // Get all the tagStatsViewedEventList
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
            .value(hasItem(tagStatsViewedEvent.getId().intValue()));
    }

    @Test
    void getTagStatsViewedEvent() {
        // Initialize the database
        tagStatsViewedEventRepository.save(tagStatsViewedEvent).block();

        // Get the tagStatsViewedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, tagStatsViewedEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(tagStatsViewedEvent.getId().intValue()));
    }

    @Test
    void getNonExistingTagStatsViewedEvent() {
        // Get the tagStatsViewedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
