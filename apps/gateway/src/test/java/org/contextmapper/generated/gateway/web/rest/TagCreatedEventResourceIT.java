package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.TagCreatedEvent;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.TagCreatedEventRepository;
import org.contextmapper.generated.gateway.service.dto.TagCreatedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.TagCreatedEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link TagCreatedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class TagCreatedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/tag-created-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagCreatedEventRepository tagCreatedEventRepository;

    @Autowired
    private TagCreatedEventMapper tagCreatedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private TagCreatedEvent tagCreatedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagCreatedEvent createEntity(EntityManager em) {
        TagCreatedEvent tagCreatedEvent = new TagCreatedEvent();
        return tagCreatedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagCreatedEvent createUpdatedEntity(EntityManager em) {
        TagCreatedEvent tagCreatedEvent = new TagCreatedEvent();
        return tagCreatedEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(TagCreatedEvent.class).block();
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
        tagCreatedEvent = createEntity(em);
    }

    @Test
    void getAllTagCreatedEventsAsStream() {
        // Initialize the database
        tagCreatedEventRepository.save(tagCreatedEvent).block();

        List<TagCreatedEvent> tagCreatedEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(TagCreatedEventDTO.class)
            .getResponseBody()
            .map(tagCreatedEventMapper::toEntity)
            .filter(tagCreatedEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(tagCreatedEventList).isNotNull();
        assertThat(tagCreatedEventList).hasSize(1);
        TagCreatedEvent testTagCreatedEvent = tagCreatedEventList.get(0);
    }

    @Test
    void getAllTagCreatedEvents() {
        // Initialize the database
        tagCreatedEventRepository.save(tagCreatedEvent).block();

        // Get all the tagCreatedEventList
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
            .value(hasItem(tagCreatedEvent.getId().intValue()));
    }

    @Test
    void getTagCreatedEvent() {
        // Initialize the database
        tagCreatedEventRepository.save(tagCreatedEvent).block();

        // Get the tagCreatedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, tagCreatedEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(tagCreatedEvent.getId().intValue()));
    }

    @Test
    void getNonExistingTagCreatedEvent() {
        // Get the tagCreatedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
