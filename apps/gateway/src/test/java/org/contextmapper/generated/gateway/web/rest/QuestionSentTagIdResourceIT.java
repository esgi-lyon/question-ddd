package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.QuestionSentTagId;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.QuestionSentTagIdRepository;
import org.contextmapper.generated.gateway.service.dto.QuestionSentTagIdDTO;
import org.contextmapper.generated.gateway.service.mapper.QuestionSentTagIdMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link QuestionSentTagIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class QuestionSentTagIdResourceIT {

    private static final Integer DEFAULT_TAG_ID = 1;
    private static final Integer UPDATED_TAG_ID = 2;

    private static final String ENTITY_API_URL = "/api/question-sent-tag-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionSentTagIdRepository questionSentTagIdRepository;

    @Autowired
    private QuestionSentTagIdMapper questionSentTagIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private QuestionSentTagId questionSentTagId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentTagId createEntity(EntityManager em) {
        QuestionSentTagId questionSentTagId = new QuestionSentTagId().tagId(DEFAULT_TAG_ID);
        return questionSentTagId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentTagId createUpdatedEntity(EntityManager em) {
        QuestionSentTagId questionSentTagId = new QuestionSentTagId().tagId(UPDATED_TAG_ID);
        return questionSentTagId;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(QuestionSentTagId.class).block();
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
        questionSentTagId = createEntity(em);
    }

    @Test
    void getAllQuestionSentTagIdsAsStream() {
        // Initialize the database
        questionSentTagIdRepository.save(questionSentTagId).block();

        List<QuestionSentTagId> questionSentTagIdList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(QuestionSentTagIdDTO.class)
            .getResponseBody()
            .map(questionSentTagIdMapper::toEntity)
            .filter(questionSentTagId::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(questionSentTagIdList).isNotNull();
        assertThat(questionSentTagIdList).hasSize(1);
        QuestionSentTagId testQuestionSentTagId = questionSentTagIdList.get(0);
        assertThat(testQuestionSentTagId.getTagId()).isEqualTo(DEFAULT_TAG_ID);
    }

    @Test
    void getAllQuestionSentTagIds() {
        // Initialize the database
        questionSentTagIdRepository.save(questionSentTagId).block();

        // Get all the questionSentTagIdList
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
            .value(hasItem(questionSentTagId.getId().intValue()))
            .jsonPath("$.[*].tagId")
            .value(hasItem(DEFAULT_TAG_ID));
    }

    @Test
    void getQuestionSentTagId() {
        // Initialize the database
        questionSentTagIdRepository.save(questionSentTagId).block();

        // Get the questionSentTagId
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, questionSentTagId.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(questionSentTagId.getId().intValue()))
            .jsonPath("$.tagId")
            .value(is(DEFAULT_TAG_ID));
    }

    @Test
    void getNonExistingQuestionSentTagId() {
        // Get the questionSentTagId
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
