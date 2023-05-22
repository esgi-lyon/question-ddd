package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.ResourceRefusedAssociationEvent;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.ResourceRefusedAssociationEventRepository;
import org.contextmapper.generated.gateway.service.dto.ResourceRefusedAssociationEventDTO;
import org.contextmapper.generated.gateway.service.mapper.ResourceRefusedAssociationEventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link ResourceRefusedAssociationEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ResourceRefusedAssociationEventResourceIT {

    private static final String ENTITY_API_URL = "/api/resource-refused-association-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResourceRefusedAssociationEventRepository resourceRefusedAssociationEventRepository;

    @Autowired
    private ResourceRefusedAssociationEventMapper resourceRefusedAssociationEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ResourceRefusedAssociationEvent resourceRefusedAssociationEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceRefusedAssociationEvent createEntity(EntityManager em) {
        ResourceRefusedAssociationEvent resourceRefusedAssociationEvent = new ResourceRefusedAssociationEvent();
        return resourceRefusedAssociationEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceRefusedAssociationEvent createUpdatedEntity(EntityManager em) {
        ResourceRefusedAssociationEvent resourceRefusedAssociationEvent = new ResourceRefusedAssociationEvent();
        return resourceRefusedAssociationEvent;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ResourceRefusedAssociationEvent.class).block();
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
        resourceRefusedAssociationEvent = createEntity(em);
    }

    @Test
    void getAllResourceRefusedAssociationEventsAsStream() {
        // Initialize the database
        resourceRefusedAssociationEventRepository.save(resourceRefusedAssociationEvent).block();

        List<ResourceRefusedAssociationEvent> resourceRefusedAssociationEventList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(ResourceRefusedAssociationEventDTO.class)
            .getResponseBody()
            .map(resourceRefusedAssociationEventMapper::toEntity)
            .filter(resourceRefusedAssociationEvent::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(resourceRefusedAssociationEventList).isNotNull();
        assertThat(resourceRefusedAssociationEventList).hasSize(1);
        ResourceRefusedAssociationEvent testResourceRefusedAssociationEvent = resourceRefusedAssociationEventList.get(0);
    }

    @Test
    void getAllResourceRefusedAssociationEvents() {
        // Initialize the database
        resourceRefusedAssociationEventRepository.save(resourceRefusedAssociationEvent).block();

        // Get all the resourceRefusedAssociationEventList
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
            .value(hasItem(resourceRefusedAssociationEvent.getId().intValue()));
    }

    @Test
    void getResourceRefusedAssociationEvent() {
        // Initialize the database
        resourceRefusedAssociationEventRepository.save(resourceRefusedAssociationEvent).block();

        // Get the resourceRefusedAssociationEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, resourceRefusedAssociationEvent.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(resourceRefusedAssociationEvent.getId().intValue()));
    }

    @Test
    void getNonExistingResourceRefusedAssociationEvent() {
        // Get the resourceRefusedAssociationEvent
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
