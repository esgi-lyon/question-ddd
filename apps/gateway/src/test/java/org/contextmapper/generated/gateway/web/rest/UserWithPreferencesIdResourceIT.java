package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.UserWithPreferencesId;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.UserWithPreferencesIdRepository;
import org.contextmapper.generated.gateway.service.dto.UserWithPreferencesIdDTO;
import org.contextmapper.generated.gateway.service.mapper.UserWithPreferencesIdMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link UserWithPreferencesIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class UserWithPreferencesIdResourceIT {

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String ENTITY_API_URL = "/api/user-with-preferences-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserWithPreferencesIdRepository userWithPreferencesIdRepository;

    @Autowired
    private UserWithPreferencesIdMapper userWithPreferencesIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private UserWithPreferencesId userWithPreferencesId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserWithPreferencesId createEntity(EntityManager em) {
        UserWithPreferencesId userWithPreferencesId = new UserWithPreferencesId().userId(DEFAULT_USER_ID);
        return userWithPreferencesId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserWithPreferencesId createUpdatedEntity(EntityManager em) {
        UserWithPreferencesId userWithPreferencesId = new UserWithPreferencesId().userId(UPDATED_USER_ID);
        return userWithPreferencesId;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(UserWithPreferencesId.class).block();
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
        userWithPreferencesId = createEntity(em);
    }

    @Test
    void getAllUserWithPreferencesIdsAsStream() {
        // Initialize the database
        userWithPreferencesIdRepository.save(userWithPreferencesId).block();

        List<UserWithPreferencesId> userWithPreferencesIdList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(UserWithPreferencesIdDTO.class)
            .getResponseBody()
            .map(userWithPreferencesIdMapper::toEntity)
            .filter(userWithPreferencesId::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(userWithPreferencesIdList).isNotNull();
        assertThat(userWithPreferencesIdList).hasSize(1);
        UserWithPreferencesId testUserWithPreferencesId = userWithPreferencesIdList.get(0);
        assertThat(testUserWithPreferencesId.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    void getAllUserWithPreferencesIds() {
        // Initialize the database
        userWithPreferencesIdRepository.save(userWithPreferencesId).block();

        // Get all the userWithPreferencesIdList
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
            .value(hasItem(userWithPreferencesId.getId().intValue()))
            .jsonPath("$.[*].userId")
            .value(hasItem(DEFAULT_USER_ID));
    }

    @Test
    void getUserWithPreferencesId() {
        // Initialize the database
        userWithPreferencesIdRepository.save(userWithPreferencesId).block();

        // Get the userWithPreferencesId
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, userWithPreferencesId.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(userWithPreferencesId.getId().intValue()))
            .jsonPath("$.userId")
            .value(is(DEFAULT_USER_ID));
    }

    @Test
    void getNonExistingUserWithPreferencesId() {
        // Get the userWithPreferencesId
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
