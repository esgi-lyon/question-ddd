package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.CategoryId;
import org.contextmapper.generated.gateway.repository.CategoryIdRepository;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.service.dto.CategoryIdDTO;
import org.contextmapper.generated.gateway.service.mapper.CategoryIdMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link CategoryIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CategoryIdResourceIT {

    private static final Integer DEFAULT_CATEGORY_ID = 1;
    private static final Integer UPDATED_CATEGORY_ID = 2;

    private static final String ENTITY_API_URL = "/api/category-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CategoryIdRepository categoryIdRepository;

    @Autowired
    private CategoryIdMapper categoryIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CategoryId categoryId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryId createEntity(EntityManager em) {
        CategoryId categoryId = new CategoryId().categoryId(DEFAULT_CATEGORY_ID);
        return categoryId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryId createUpdatedEntity(EntityManager em) {
        CategoryId categoryId = new CategoryId().categoryId(UPDATED_CATEGORY_ID);
        return categoryId;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CategoryId.class).block();
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
        categoryId = createEntity(em);
    }

    @Test
    void getAllCategoryIdsAsStream() {
        // Initialize the database
        categoryIdRepository.save(categoryId).block();

        List<CategoryId> categoryIdList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(CategoryIdDTO.class)
            .getResponseBody()
            .map(categoryIdMapper::toEntity)
            .filter(categoryId::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(categoryIdList).isNotNull();
        assertThat(categoryIdList).hasSize(1);
        CategoryId testCategoryId = categoryIdList.get(0);
        assertThat(testCategoryId.getCategoryId()).isEqualTo(DEFAULT_CATEGORY_ID);
    }

    @Test
    void getAllCategoryIds() {
        // Initialize the database
        categoryIdRepository.save(categoryId).block();

        // Get all the categoryIdList
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
            .value(hasItem(categoryId.getId().intValue()))
            .jsonPath("$.[*].categoryId")
            .value(hasItem(DEFAULT_CATEGORY_ID));
    }

    @Test
    void getCategoryId() {
        // Initialize the database
        categoryIdRepository.save(categoryId).block();

        // Get the categoryId
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, categoryId.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(categoryId.getId().intValue()))
            .jsonPath("$.categoryId")
            .value(is(DEFAULT_CATEGORY_ID));
    }

    @Test
    void getNonExistingCategoryId() {
        // Get the categoryId
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
