package org.contextmapper.generated.sendquestioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.sendquestioncontext.IntegrationTest;
import org.contextmapper.generated.sendquestioncontext.domain.CreateQuestion;
import org.contextmapper.generated.sendquestioncontext.repository.CreateQuestionRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreateQuestionDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.CreateQuestionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreateQuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreateQuestionResourceIT {

    private static final String ENTITY_API_URL = "/api/create-questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateQuestionRepository createQuestionRepository;

    @Autowired
    private CreateQuestionMapper createQuestionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreateQuestionMockMvc;

    private CreateQuestion createQuestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateQuestion createEntity(EntityManager em) {
        CreateQuestion createQuestion = new CreateQuestion();
        return createQuestion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateQuestion createUpdatedEntity(EntityManager em) {
        CreateQuestion createQuestion = new CreateQuestion();
        return createQuestion;
    }

    @BeforeEach
    public void initTest() {
        createQuestion = createEntity(em);
    }

    @Test
    @Transactional
    void createCreateQuestion() throws Exception {
        int databaseSizeBeforeCreate = createQuestionRepository.findAll().size();
        // Create the CreateQuestion
        CreateQuestionDTO createQuestionDTO = createQuestionMapper.toDto(createQuestion);
        restCreateQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createQuestionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CreateQuestion in the database
        List<CreateQuestion> createQuestionList = createQuestionRepository.findAll();
        assertThat(createQuestionList).hasSize(databaseSizeBeforeCreate + 1);
        CreateQuestion testCreateQuestion = createQuestionList.get(createQuestionList.size() - 1);
    }

    @Test
    @Transactional
    void createCreateQuestionWithExistingId() throws Exception {
        // Create the CreateQuestion with an existing ID
        createQuestion.setId(1L);
        CreateQuestionDTO createQuestionDTO = createQuestionMapper.toDto(createQuestion);

        int databaseSizeBeforeCreate = createQuestionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreateQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateQuestion in the database
        List<CreateQuestion> createQuestionList = createQuestionRepository.findAll();
        assertThat(createQuestionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCreateQuestions() throws Exception {
        // Initialize the database
        createQuestionRepository.saveAndFlush(createQuestion);

        // Get all the createQuestionList
        restCreateQuestionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(createQuestion.getId().intValue())));
    }

    @Test
    @Transactional
    void getCreateQuestion() throws Exception {
        // Initialize the database
        createQuestionRepository.saveAndFlush(createQuestion);

        // Get the createQuestion
        restCreateQuestionMockMvc
            .perform(get(ENTITY_API_URL_ID, createQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(createQuestion.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCreateQuestion() throws Exception {
        // Get the createQuestion
        restCreateQuestionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCreateQuestion() throws Exception {
        // Initialize the database
        createQuestionRepository.saveAndFlush(createQuestion);

        int databaseSizeBeforeUpdate = createQuestionRepository.findAll().size();

        // Update the createQuestion
        CreateQuestion updatedCreateQuestion = createQuestionRepository.findById(createQuestion.getId()).get();
        // Disconnect from session so that the updates on updatedCreateQuestion are not directly saved in db
        em.detach(updatedCreateQuestion);
        CreateQuestionDTO createQuestionDTO = createQuestionMapper.toDto(updatedCreateQuestion);

        restCreateQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionDTO))
            )
            .andExpect(status().isOk());

        // Validate the CreateQuestion in the database
        List<CreateQuestion> createQuestionList = createQuestionRepository.findAll();
        assertThat(createQuestionList).hasSize(databaseSizeBeforeUpdate);
        CreateQuestion testCreateQuestion = createQuestionList.get(createQuestionList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCreateQuestion() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionRepository.findAll().size();
        createQuestion.setId(count.incrementAndGet());

        // Create the CreateQuestion
        CreateQuestionDTO createQuestionDTO = createQuestionMapper.toDto(createQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateQuestion in the database
        List<CreateQuestion> createQuestionList = createQuestionRepository.findAll();
        assertThat(createQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCreateQuestion() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionRepository.findAll().size();
        createQuestion.setId(count.incrementAndGet());

        // Create the CreateQuestion
        CreateQuestionDTO createQuestionDTO = createQuestionMapper.toDto(createQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateQuestion in the database
        List<CreateQuestion> createQuestionList = createQuestionRepository.findAll();
        assertThat(createQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCreateQuestion() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionRepository.findAll().size();
        createQuestion.setId(count.incrementAndGet());

        // Create the CreateQuestion
        CreateQuestionDTO createQuestionDTO = createQuestionMapper.toDto(createQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateQuestionMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateQuestion in the database
        List<CreateQuestion> createQuestionList = createQuestionRepository.findAll();
        assertThat(createQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCreateQuestionWithPatch() throws Exception {
        // Initialize the database
        createQuestionRepository.saveAndFlush(createQuestion);

        int databaseSizeBeforeUpdate = createQuestionRepository.findAll().size();

        // Update the createQuestion using partial update
        CreateQuestion partialUpdatedCreateQuestion = new CreateQuestion();
        partialUpdatedCreateQuestion.setId(createQuestion.getId());

        restCreateQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateQuestion))
            )
            .andExpect(status().isOk());

        // Validate the CreateQuestion in the database
        List<CreateQuestion> createQuestionList = createQuestionRepository.findAll();
        assertThat(createQuestionList).hasSize(databaseSizeBeforeUpdate);
        CreateQuestion testCreateQuestion = createQuestionList.get(createQuestionList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCreateQuestionWithPatch() throws Exception {
        // Initialize the database
        createQuestionRepository.saveAndFlush(createQuestion);

        int databaseSizeBeforeUpdate = createQuestionRepository.findAll().size();

        // Update the createQuestion using partial update
        CreateQuestion partialUpdatedCreateQuestion = new CreateQuestion();
        partialUpdatedCreateQuestion.setId(createQuestion.getId());

        restCreateQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateQuestion))
            )
            .andExpect(status().isOk());

        // Validate the CreateQuestion in the database
        List<CreateQuestion> createQuestionList = createQuestionRepository.findAll();
        assertThat(createQuestionList).hasSize(databaseSizeBeforeUpdate);
        CreateQuestion testCreateQuestion = createQuestionList.get(createQuestionList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCreateQuestion() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionRepository.findAll().size();
        createQuestion.setId(count.incrementAndGet());

        // Create the CreateQuestion
        CreateQuestionDTO createQuestionDTO = createQuestionMapper.toDto(createQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, createQuestionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateQuestion in the database
        List<CreateQuestion> createQuestionList = createQuestionRepository.findAll();
        assertThat(createQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCreateQuestion() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionRepository.findAll().size();
        createQuestion.setId(count.incrementAndGet());

        // Create the CreateQuestion
        CreateQuestionDTO createQuestionDTO = createQuestionMapper.toDto(createQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateQuestion in the database
        List<CreateQuestion> createQuestionList = createQuestionRepository.findAll();
        assertThat(createQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCreateQuestion() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionRepository.findAll().size();
        createQuestion.setId(count.incrementAndGet());

        // Create the CreateQuestion
        CreateQuestionDTO createQuestionDTO = createQuestionMapper.toDto(createQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateQuestion in the database
        List<CreateQuestion> createQuestionList = createQuestionRepository.findAll();
        assertThat(createQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCreateQuestion() throws Exception {
        // Initialize the database
        createQuestionRepository.saveAndFlush(createQuestion);

        int databaseSizeBeforeDelete = createQuestionRepository.findAll().size();

        // Delete the createQuestion
        restCreateQuestionMockMvc
            .perform(delete(ENTITY_API_URL_ID, createQuestion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CreateQuestion> createQuestionList = createQuestionRepository.findAll();
        assertThat(createQuestionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
