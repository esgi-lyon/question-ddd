package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.StatisticSubjectUser;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.StatisticSubjectUserRepository;
import org.contextmapper.generated.gateway.service.dto.StatisticSubjectUserDTO;
import org.contextmapper.generated.gateway.service.mapper.StatisticSubjectUserMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link StatisticSubjectUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class StatisticSubjectUserResourceIT {

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String ENTITY_API_URL = "/api/statistic-subject-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StatisticSubjectUserRepository statisticSubjectUserRepository;

    @Autowired
    private StatisticSubjectUserMapper statisticSubjectUserMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private StatisticSubjectUser statisticSubjectUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticSubjectUser createEntity(EntityManager em) {
        StatisticSubjectUser statisticSubjectUser = new StatisticSubjectUser().userId(DEFAULT_USER_ID);
        return statisticSubjectUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticSubjectUser createUpdatedEntity(EntityManager em) {
        StatisticSubjectUser statisticSubjectUser = new StatisticSubjectUser().userId(UPDATED_USER_ID);
        return statisticSubjectUser;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(StatisticSubjectUser.class).block();
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
        statisticSubjectUser = createEntity(em);
    }

    @Test
    void getAllStatisticSubjectUsersAsStream() {
        // Initialize the database
        statisticSubjectUserRepository.save(statisticSubjectUser).block();

        List<StatisticSubjectUser> statisticSubjectUserList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(StatisticSubjectUserDTO.class)
            .getResponseBody()
            .map(statisticSubjectUserMapper::toEntity)
            .filter(statisticSubjectUser::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(statisticSubjectUserList).isNotNull();
        assertThat(statisticSubjectUserList).hasSize(1);
        StatisticSubjectUser testStatisticSubjectUser = statisticSubjectUserList.get(0);
        assertThat(testStatisticSubjectUser.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    void getAllStatisticSubjectUsers() {
        // Initialize the database
        statisticSubjectUserRepository.save(statisticSubjectUser).block();

        // Get all the statisticSubjectUserList
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
            .value(hasItem(statisticSubjectUser.getId().intValue()))
            .jsonPath("$.[*].userId")
            .value(hasItem(DEFAULT_USER_ID));
    }

    @Test
    void getStatisticSubjectUser() {
        // Initialize the database
        statisticSubjectUserRepository.save(statisticSubjectUser).block();

        // Get the statisticSubjectUser
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, statisticSubjectUser.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(statisticSubjectUser.getId().intValue()))
            .jsonPath("$.userId")
            .value(is(DEFAULT_USER_ID));
    }

    @Test
    void getNonExistingStatisticSubjectUser() {
        // Get the statisticSubjectUser
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
