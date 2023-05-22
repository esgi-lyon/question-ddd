package org.contextmapper.generated.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.contextmapper.generated.gateway.IntegrationTest;
import org.contextmapper.generated.gateway.domain.QuestionSentQuestionResourceTagId;
import org.contextmapper.generated.gateway.repository.EntityManager;
import org.contextmapper.generated.gateway.repository.QuestionSentQuestionResourceTagIdRepository;
import org.contextmapper.generated.gateway.service.dto.QuestionSentQuestionResourceTagIdDTO;
import org.contextmapper.generated.gateway.service.mapper.QuestionSentQuestionResourceTagIdMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link QuestionSentQuestionResourceTagIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class QuestionSentQuestionResourceTagIdResourceIT {

    private static final Integer DEFAULT_TAG_ID = 1;
    private static final Integer UPDATED_TAG_ID = 2;

    private static final String ENTITY_API_URL = "/api/question-sent-question-resource-tag-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionSentQuestionResourceTagIdRepository questionSentQuestionResourceTagIdRepository;

    @Autowired
    private QuestionSentQuestionResourceTagIdMapper questionSentQuestionResourceTagIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentQuestionResourceTagId createEntity(EntityManager em) {
        QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId = new QuestionSentQuestionResourceTagId().tagId(DEFAULT_TAG_ID);
        return questionSentQuestionResourceTagId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionSentQuestionResourceTagId createUpdatedEntity(EntityManager em) {
        QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId = new QuestionSentQuestionResourceTagId().tagId(UPDATED_TAG_ID);
        return questionSentQuestionResourceTagId;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(QuestionSentQuestionResourceTagId.class).block();
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
        questionSentQuestionResourceTagId = createEntity(em);
    }

    @Test
    void getAllQuestionSentQuestionResourceTagIdsAsStream() {
        // Initialize the database
        questionSentQuestionResourceTagIdRepository.save(questionSentQuestionResourceTagId).block();

        List<QuestionSentQuestionResourceTagId> questionSentQuestionResourceTagIdList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(QuestionSentQuestionResourceTagIdDTO.class)
            .getResponseBody()
            .map(questionSentQuestionResourceTagIdMapper::toEntity)
            .filter(questionSentQuestionResourceTagId::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(questionSentQuestionResourceTagIdList).isNotNull();
        assertThat(questionSentQuestionResourceTagIdList).hasSize(1);
        QuestionSentQuestionResourceTagId testQuestionSentQuestionResourceTagId = questionSentQuestionResourceTagIdList.get(0);
        assertThat(testQuestionSentQuestionResourceTagId.getTagId()).isEqualTo(DEFAULT_TAG_ID);
    }

    @Test
    void getAllQuestionSentQuestionResourceTagIds() {
        // Initialize the database
        questionSentQuestionResourceTagIdRepository.save(questionSentQuestionResourceTagId).block();

        // Get all the questionSentQuestionResourceTagIdList
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
            .value(hasItem(questionSentQuestionResourceTagId.getId().intValue()))
            .jsonPath("$.[*].tagId")
            .value(hasItem(DEFAULT_TAG_ID));
    }

    @Test
    void getQuestionSentQuestionResourceTagId() {
        // Initialize the database
        questionSentQuestionResourceTagIdRepository.save(questionSentQuestionResourceTagId).block();

        // Get the questionSentQuestionResourceTagId
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, questionSentQuestionResourceTagId.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(questionSentQuestionResourceTagId.getId().intValue()))
            .jsonPath("$.tagId")
            .value(is(DEFAULT_TAG_ID));
    }

    @Test
    void getNonExistingQuestionSentQuestionResourceTagId() {
        // Get the questionSentQuestionResourceTagId
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
