package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.AnsweredTag;
import org.contextmapper.generated.gateway.repository.AnsweredTagRepository;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.service.dto.AnsweredTagDTO;
import org.contextmapper.generated.gateway.service.mapper.AnsweredTagMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link AnsweredTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AnsweredTagResourceIT {

    private static final Integer DEFAULT_TAG_ID = 1;
    private static final Integer UPDATED_TAG_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/answered-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnsweredTagRepository answeredTagRepository;

    @Autowired
    private AnsweredTagMapper answeredTagMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private AnsweredTag answeredTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnsweredTag createEntity(EntityManager em) {
        AnsweredTag answeredTag = new AnsweredTag().tagId(DEFAULT_TAG_ID).name(DEFAULT_NAME);
        return answeredTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnsweredTag createUpdatedEntity(EntityManager em) {
        AnsweredTag answeredTag = new AnsweredTag().tagId(UPDATED_TAG_ID).name(UPDATED_NAME);
        return answeredTag;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(AnsweredTag.class).block();
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
        answeredTag = createEntity(em);
    }

    @Test
    void getAllAnsweredTagsAsStream() {
        // Initialize the database
        answeredTagRepository.save(answeredTag).block();

        List<AnsweredTag> answeredTagList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(AnsweredTagDTO.class)
            .getResponseBody()
            .map(answeredTagMapper::toEntity)
            .filter(answeredTag::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(answeredTagList).isNotNull();
        assertThat(answeredTagList).hasSize(1);
        AnsweredTag testAnsweredTag = answeredTagList.get(0);
        assertThat(testAnsweredTag.getTagId()).isEqualTo(DEFAULT_TAG_ID);
        assertThat(testAnsweredTag.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    void getAllAnsweredTags() {
        // Initialize the database
        answeredTagRepository.save(answeredTag).block();

        // Get all the answeredTagList
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
            .value(hasItem(answeredTag.getId().intValue()))
            .jsonPath("$.[*].tagId")
            .value(hasItem(DEFAULT_TAG_ID))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME));
    }

    @Test
    void getAnsweredTag() {
        // Initialize the database
        answeredTagRepository.save(answeredTag).block();

        // Get the answeredTag
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, answeredTag.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(answeredTag.getId().intValue()))
            .jsonPath("$.tagId")
            .value(is(DEFAULT_TAG_ID))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME));
    }

    @Test
    void getNonExistingAnsweredTag() {
        // Get the answeredTag
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
