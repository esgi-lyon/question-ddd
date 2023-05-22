package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.CategoryCreatedEvent;
import org.contextmapper.generated.gateway.repository.CategoryCreatedEventRepository;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.service.dto.CategoryCreatedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.CategoryCreatedEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link CategoryCreatedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CategoryCreatedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/category-created-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CategoryCreatedEventRepository categoryCreatedEventRepository;

    @Autowired
    private CategoryCreatedEventMapper categoryCreatedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CategoryCreatedEvent categoryCreatedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryCreatedEvent createEntity(EntityManager em) {
        CategoryCreatedEvent categoryCreatedEvent = new CategoryCreatedEvent();
        return categoryCreatedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryCreatedEvent createUpdatedEntity(EntityManager em) {
        CategoryCreatedEvent categoryCreatedEvent = new CategoryCreatedEvent();
        return categoryCreatedEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CategoryCreatedEvent.class).block();
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
        categoryCreatedEvent = createEntity(em);
    }

    @Test
    void getAllCategoryCreatedEventsAsStream() {
        // Initialize the database
        categoryCreatedEventRepository.save(categoryCreatedEvent).block();

        List<CategoryCreatedEvent> categoryCreatedEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(CategoryCreatedEventDTO.class)
            .getResponseBody()
            .map(categoryCreatedEventMapper::toEntity)
            .filter(categoryCreatedEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(categoryCreatedEventList).isNotNull();
        assertThat(categoryCreatedEventList).hasSize(1);
        CategoryCreatedEvent testCategoryCreatedEvent = categoryCreatedEventList.get(0);
    }

    @Test
    void getAllCategoryCreatedEvents() {
        // Initialize the database
        categoryCreatedEventRepository.save(categoryCreatedEvent).block();

        // Get all the categoryCreatedEventList
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
            .value(hasItem(categoryCreatedEvent.getId().intValue()));
    }

    @Test
    void getCategoryCreatedEvent() {
        // Initialize the database
        categoryCreatedEventRepository.save(categoryCreatedEvent).block();

        // Get the categoryCreatedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, categoryCreatedEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(categoryCreatedEvent.getId().intValue()));
    }

    @Test
    void getNonExistingCategoryCreatedEvent() {
        // Get the categoryCreatedEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
