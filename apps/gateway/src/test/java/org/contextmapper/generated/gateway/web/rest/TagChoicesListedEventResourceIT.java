package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.TagChoicesListedEvent;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.TagChoicesListedEventRepository;
import org.contextmapper.generated.gateway.service.dto.TagChoicesListedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.TagChoicesListedEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link TagChoicesListedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class TagChoicesListedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/tag-choices-listed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagChoicesListedEventRepository tagChoicesListedEventRepository;

    @Autowired
    private TagChoicesListedEventMapper tagChoicesListedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private TagChoicesListedEvent tagChoicesListedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagChoicesListedEvent createEntity(EntityManager em) {
        TagChoicesListedEvent tagChoicesListedEvent = new TagChoicesListedEvent();
        return tagChoicesListedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagChoicesListedEvent createUpdatedEntity(EntityManager em) {
        TagChoicesListedEvent tagChoicesListedEvent = new TagChoicesListedEvent();
        return tagChoicesListedEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(TagChoicesListedEvent.class).block();
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
        tagChoicesListedEvent = createEntity(em);
    }

    @Test
    void getAllTagChoicesListedEventsAsStream() {
        // Initialize the database
        tagChoicesListedEventRepository.save(tagChoicesListedEvent).block();

        List<TagChoicesListedEvent> tagChoicesListedEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(TagChoicesListedEventDTO.class)
            .getResponseBody()
            .map(tagChoicesListedEventMapper::toEntity)
            .filter(tagChoicesListedEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(tagChoicesListedEventList).isNotNull();
        assertThat(tagChoicesListedEventList).hasSize(1);
        TagChoicesListedEvent testTagChoicesListedEvent = tagChoicesListedEventList.get(0);
    }

    @Test
    void getAllTagChoicesListedEvents() {
        // Initialize the database
        tagChoicesListedEventRepository.save(tagChoicesListedEvent).block();

        // Get all the tagChoicesListedEventList
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
            .value(hasItem(tagChoicesListedEvent.getId().intValue()));
    }

    @Test
    void getTagChoicesListedEvent() {
        // Initialize the database
        tagChoicesListedEventRepository.save(tagChoicesListedEvent).block();

        // Get the tagChoicesListedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, tagChoicesListedEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(tagChoicesListedEvent.getId().intValue()));
    }

    @Test
    void getNonExistingTagChoicesListedEvent() {
        // Get the tagChoicesListedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
