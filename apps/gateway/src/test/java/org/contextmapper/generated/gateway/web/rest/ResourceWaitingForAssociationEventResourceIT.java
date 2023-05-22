package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.ResourceWaitingForAssociationEvent;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.ResourceWaitingForAssociationEventRepository;
import org.contextmapper.generated.gateway.service.dto.ResourceWaitingForAssociationEventDTO;
import org.contextmapper.generated.gateway.service.mapper.ResourceWaitingForAssociationEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link ResourceWaitingForAssociationEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ResourceWaitingForAssociationEventResourceIT {

    private static final String ENTITY_API_URL = "/api/resource-waiting-for-association-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResourceWaitingForAssociationEventRepository resourceWaitingForAssociationEventRepository;

    @Autowired
    private ResourceWaitingForAssociationEventMapper resourceWaitingForAssociationEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ResourceWaitingForAssociationEvent resourceWaitingForAssociationEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceWaitingForAssociationEvent createEntity(EntityManager em) {
        ResourceWaitingForAssociationEvent resourceWaitingForAssociationEvent = new ResourceWaitingForAssociationEvent();
        return resourceWaitingForAssociationEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceWaitingForAssociationEvent createUpdatedEntity(EntityManager em) {
        ResourceWaitingForAssociationEvent resourceWaitingForAssociationEvent = new ResourceWaitingForAssociationEvent();
        return resourceWaitingForAssociationEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ResourceWaitingForAssociationEvent.class).block();
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
        resourceWaitingForAssociationEvent = createEntity(em);
    }

    @Test
    void getAllResourceWaitingForAssociationEventsAsStream() {
        // Initialize the database
        resourceWaitingForAssociationEventRepository.save(resourceWaitingForAssociationEvent).block();

        List<ResourceWaitingForAssociationEvent> resourceWaitingForAssociationEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(ResourceWaitingForAssociationEventDTO.class)
            .getResponseBody()
            .map(resourceWaitingForAssociationEventMapper::toEntity)
            .filter(resourceWaitingForAssociationEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(resourceWaitingForAssociationEventList).isNotNull();
        assertThat(resourceWaitingForAssociationEventList).hasSize(1);
        ResourceWaitingForAssociationEvent testResourceWaitingForAssociationEvent = resourceWaitingForAssociationEventList.get(0);
    }

    @Test
    void getAllResourceWaitingForAssociationEvents() {
        // Initialize the database
        resourceWaitingForAssociationEventRepository.save(resourceWaitingForAssociationEvent).block();

        // Get all the resourceWaitingForAssociationEventList
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
            .value(hasItem(resourceWaitingForAssociationEvent.getId().intValue()));
    }

    @Test
    void getResourceWaitingForAssociationEvent() {
        // Initialize the database
        resourceWaitingForAssociationEventRepository.save(resourceWaitingForAssociationEvent).block();

        // Get the resourceWaitingForAssociationEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, resourceWaitingForAssociationEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(resourceWaitingForAssociationEvent.getId().intValue()));
    }

    @Test
    void getNonExistingResourceWaitingForAssociationEvent() {
        // Get the resourceWaitingForAssociationEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
