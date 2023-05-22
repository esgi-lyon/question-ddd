package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.ResourceAcceptedAssociationEvent;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.ResourceAcceptedAssociationEventRepository;
import org.contextmapper.generated.gateway.service.dto.ResourceAcceptedAssociationEventDTO;
import org.contextmapper.generated.gateway.service.mapper.ResourceAcceptedAssociationEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link ResourceAcceptedAssociationEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ResourceAcceptedAssociationEventResourceIT {

    private static final String ENTITY_API_URL = "/api/resource-accepted-association-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResourceAcceptedAssociationEventRepository resourceAcceptedAssociationEventRepository;

    @Autowired
    private ResourceAcceptedAssociationEventMapper resourceAcceptedAssociationEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ResourceAcceptedAssociationEvent resourceAcceptedAssociationEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceAcceptedAssociationEvent createEntity(EntityManager em) {
        ResourceAcceptedAssociationEvent resourceAcceptedAssociationEvent = new ResourceAcceptedAssociationEvent();
        return resourceAcceptedAssociationEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceAcceptedAssociationEvent createUpdatedEntity(EntityManager em) {
        ResourceAcceptedAssociationEvent resourceAcceptedAssociationEvent = new ResourceAcceptedAssociationEvent();
        return resourceAcceptedAssociationEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ResourceAcceptedAssociationEvent.class).block();
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
        resourceAcceptedAssociationEvent = createEntity(em);
    }

    @Test
    void getAllResourceAcceptedAssociationEventsAsStream() {
        // Initialize the database
        resourceAcceptedAssociationEventRepository.save(resourceAcceptedAssociationEvent).block();

        List<ResourceAcceptedAssociationEvent> resourceAcceptedAssociationEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(ResourceAcceptedAssociationEventDTO.class)
            .getResponseBody()
            .map(resourceAcceptedAssociationEventMapper::toEntity)
            .filter(resourceAcceptedAssociationEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(resourceAcceptedAssociationEventList).isNotNull();
        assertThat(resourceAcceptedAssociationEventList).hasSize(1);
        ResourceAcceptedAssociationEvent testResourceAcceptedAssociationEvent = resourceAcceptedAssociationEventList.get(0);
    }

    @Test
    void getAllResourceAcceptedAssociationEvents() {
        // Initialize the database
        resourceAcceptedAssociationEventRepository.save(resourceAcceptedAssociationEvent).block();

        // Get all the resourceAcceptedAssociationEventList
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
            .value(hasItem(resourceAcceptedAssociationEvent.getId().intValue()));
    }

    @Test
    void getResourceAcceptedAssociationEvent() {
        // Initialize the database
        resourceAcceptedAssociationEventRepository.save(resourceAcceptedAssociationEvent).block();

        // Get the resourceAcceptedAssociationEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, resourceAcceptedAssociationEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(resourceAcceptedAssociationEvent.getId().intValue()));
    }

    @Test
    void getNonExistingResourceAcceptedAssociationEvent() {
        // Get the resourceAcceptedAssociationEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
