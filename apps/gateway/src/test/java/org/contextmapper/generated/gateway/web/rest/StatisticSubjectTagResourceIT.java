package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.StatisticSubjectTag;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.StatisticSubjectTagRepository;
import org.contextmapper.generated.gateway.service.dto.StatisticSubjectTagDTO;
import org.contextmapper.generated.gateway.service.mapper.StatisticSubjectTagMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link StatisticSubjectTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class StatisticSubjectTagResourceIT {

    private static final Integer DEFAULT_TAG_ID = 1;
    private static final Integer UPDATED_TAG_ID = 2;

    private static final String ENTITY_API_URL = "/api/statistic-subject-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StatisticSubjectTagRepository statisticSubjectTagRepository;

    @Autowired
    private StatisticSubjectTagMapper statisticSubjectTagMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private StatisticSubjectTag statisticSubjectTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticSubjectTag createEntity(EntityManager em) {
        StatisticSubjectTag statisticSubjectTag = new StatisticSubjectTag().tagId(DEFAULT_TAG_ID);
        return statisticSubjectTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatisticSubjectTag createUpdatedEntity(EntityManager em) {
        StatisticSubjectTag statisticSubjectTag = new StatisticSubjectTag().tagId(UPDATED_TAG_ID);
        return statisticSubjectTag;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(StatisticSubjectTag.class).block();
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
        statisticSubjectTag = createEntity(em);
    }

    @Test
    void getAllStatisticSubjectTagsAsStream() {
        // Initialize the database
        statisticSubjectTagRepository.save(statisticSubjectTag).block();

        List<StatisticSubjectTag> statisticSubjectTagList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(StatisticSubjectTagDTO.class)
            .getResponseBody()
            .map(statisticSubjectTagMapper::toEntity)
            .filter(statisticSubjectTag::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(statisticSubjectTagList).isNotNull();
        assertThat(statisticSubjectTagList).hasSize(1);
        StatisticSubjectTag testStatisticSubjectTag = statisticSubjectTagList.get(0);
        assertThat(testStatisticSubjectTag.getTagId()).isEqualTo(DEFAULT_TAG_ID);
    }

    @Test
    void getAllStatisticSubjectTags() {
        // Initialize the database
        statisticSubjectTagRepository.save(statisticSubjectTag).block();

        // Get all the statisticSubjectTagList
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
            .value(hasItem(statisticSubjectTag.getId().intValue()))
            .jsonPath("$.[*].tagId")
            .value(hasItem(DEFAULT_TAG_ID));
    }

    @Test
    void getStatisticSubjectTag() {
        // Initialize the database
        statisticSubjectTagRepository.save(statisticSubjectTag).block();

        // Get the statisticSubjectTag
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, statisticSubjectTag.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(statisticSubjectTag.getId().intValue()))
            .jsonPath("$.tagId")
            .value(is(DEFAULT_TAG_ID));
    }

    @Test
    void getNonExistingStatisticSubjectTag() {
        // Get the statisticSubjectTag
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
