package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.TagInfos;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.TagInfosRepository;
import org.contextmapper.generated.gateway.service.dto.TagInfosDTO;
import org.contextmapper.generated.gateway.service.mapper.TagInfosMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link TagInfosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class TagInfosResourceIT {

    private static final Integer DEFAULT_TAG_ID = 1;
    private static final Integer UPDATED_TAG_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tag-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagInfosRepository tagInfosRepository;

    @Autowired
    private TagInfosMapper tagInfosMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private TagInfos tagInfos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagInfos createEntity(EntityManager em) {
        TagInfos tagInfos = new TagInfos().tagId(DEFAULT_TAG_ID).name(DEFAULT_NAME);
        return tagInfos;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagInfos createUpdatedEntity(EntityManager em) {
        TagInfos tagInfos = new TagInfos().tagId(UPDATED_TAG_ID).name(UPDATED_NAME);
        return tagInfos;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(TagInfos.class).block();
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
        tagInfos = createEntity(em);
    }

    @Test
    void getAllTagInfosAsStream() {
        // Initialize the database
        tagInfosRepository.save(tagInfos).block();

        List<TagInfos> tagInfosList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(TagInfosDTO.class)
            .getResponseBody()
            .map(tagInfosMapper::toEntity)
            .filter(tagInfos::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(tagInfosList).isNotNull();
        assertThat(tagInfosList).hasSize(1);
        TagInfos testTagInfos = tagInfosList.get(0);
        assertThat(testTagInfos.getTagId()).isEqualTo(DEFAULT_TAG_ID);
        assertThat(testTagInfos.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    void getAllTagInfos() {
        // Initialize the database
        tagInfosRepository.save(tagInfos).block();

        // Get all the tagInfosList
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
            .value(hasItem(tagInfos.getId().intValue()))
            .jsonPath("$.[*].tagId")
            .value(hasItem(DEFAULT_TAG_ID))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME));
    }

    @Test
    void getTagInfos() {
        // Initialize the database
        tagInfosRepository.save(tagInfos).block();

        // Get the tagInfos
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, tagInfos.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(tagInfos.getId().intValue()))
            .jsonPath("$.tagId")
            .value(is(DEFAULT_TAG_ID))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME));
    }

    @Test
    void getNonExistingTagInfos() {
        // Get the tagInfos
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
