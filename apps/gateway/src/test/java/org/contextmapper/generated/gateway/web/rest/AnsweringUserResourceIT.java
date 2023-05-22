package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.AnsweringUser;
import org.contextmapper.generated.gateway.repository.AnsweringUserRepository;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.service.dto.AnsweringUserDTO;
import org.contextmapper.generated.gateway.service.mapper.AnsweringUserMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link AnsweringUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AnsweringUserResourceIT {

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/answering-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnsweringUserRepository answeringUserRepository;

    @Autowired
    private AnsweringUserMapper answeringUserMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private AnsweringUser answeringUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnsweringUser createEntity(EntityManager em) {
        AnsweringUser answeringUser = new AnsweringUser().userId(DEFAULT_USER_ID).name(DEFAULT_NAME);
        return answeringUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnsweringUser createUpdatedEntity(EntityManager em) {
        AnsweringUser answeringUser = new AnsweringUser().userId(UPDATED_USER_ID).name(UPDATED_NAME);
        return answeringUser;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(AnsweringUser.class).block();
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
        answeringUser = createEntity(em);
    }

    @Test
    void getAllAnsweringUsersAsStream() {
        // Initialize the database
        answeringUserRepository.save(answeringUser).block();

        List<AnsweringUser> answeringUserList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(AnsweringUserDTO.class)
            .getResponseBody()
            .map(answeringUserMapper::toEntity)
            .filter(answeringUser::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(answeringUserList).isNotNull();
        assertThat(answeringUserList).hasSize(1);
        AnsweringUser testAnsweringUser = answeringUserList.get(0);
        assertThat(testAnsweringUser.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testAnsweringUser.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    void getAllAnsweringUsers() {
        // Initialize the database
        answeringUserRepository.save(answeringUser).block();

        // Get all the answeringUserList
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
            .value(hasItem(answeringUser.getId().intValue()))
            .jsonPath("$.[*].userId")
            .value(hasItem(DEFAULT_USER_ID))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME));
    }

    @Test
    void getAnsweringUser() {
        // Initialize the database
        answeringUserRepository.save(answeringUser).block();

        // Get the answeringUser
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, answeringUser.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(answeringUser.getId().intValue()))
            .jsonPath("$.userId")
            .value(is(DEFAULT_USER_ID))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME));
    }

    @Test
    void getNonExistingAnsweringUser() {
        // Get the answeringUser
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
