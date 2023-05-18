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
import org.contextmapper.generated.sendquestioncontext.domain.CreatedQuestion;
import org.contextmapper.generated.sendquestioncontext.repository.CreatedQuestionRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreatedQuestionDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.CreatedQuestionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreatedQuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreatedQuestionResourceIT {

    private static final String ENTITY_API_URL = "/api/created-questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreatedQuestionRepository createdQuestionRepository;

    @Autowired
    private CreatedQuestionMapper createdQuestionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreatedQuestionMockMvc;

    private CreatedQuestion createdQuestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreatedQuestion createEntity(EntityManager em) {
        CreatedQuestion createdQuestion = new CreatedQuestion();
        return createdQuestion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreatedQuestion createUpdatedEntity(EntityManager em) {
        CreatedQuestion createdQuestion = new CreatedQuestion();
        return createdQuestion;
    }

    @BeforeEach
    public void initTest() {
        createdQuestion = createEntity(em);
    }

    @Test
    @Transactional
    void createCreatedQuestion() throws Exception {
        int databaseSizeBeforeCreate = createdQuestionRepository.findAll().size();
        // Create the CreatedQuestion
        CreatedQuestionDTO createdQuestionDTO = createdQuestionMapper.toDto(createdQuestion);
        restCreatedQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createdQuestionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CreatedQuestion in the database
        List<CreatedQuestion> createdQuestionList = createdQuestionRepository.findAll();
        assertThat(createdQuestionList).hasSize(databaseSizeBeforeCreate + 1);
        CreatedQuestion testCreatedQuestion = createdQuestionList.get(createdQuestionList.size() - 1);
    }

    @Test
    @Transactional
    void createCreatedQuestionWithExistingId() throws Exception {
        // Create the CreatedQuestion with an existing ID
        createdQuestion.setId(1L);
        CreatedQuestionDTO createdQuestionDTO = createdQuestionMapper.toDto(createdQuestion);

        int databaseSizeBeforeCreate = createdQuestionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreatedQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createdQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreatedQuestion in the database
        List<CreatedQuestion> createdQuestionList = createdQuestionRepository.findAll();
        assertThat(createdQuestionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCreatedQuestions() throws Exception {
        // Initialize the database
        createdQuestionRepository.saveAndFlush(createdQuestion);

        // Get all the createdQuestionList
        restCreatedQuestionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(createdQuestion.getId().intValue())));
    }

    @Test
    @Transactional
    void getCreatedQuestion() throws Exception {
        // Initialize the database
        createdQuestionRepository.saveAndFlush(createdQuestion);

        // Get the createdQuestion
        restCreatedQuestionMockMvc
            .perform(get(ENTITY_API_URL_ID, createdQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(createdQuestion.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCreatedQuestion() throws Exception {
        // Get the createdQuestion
        restCreatedQuestionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCreatedQuestion() throws Exception {
        // Initialize the database
        createdQuestionRepository.saveAndFlush(createdQuestion);

        int databaseSizeBeforeUpdate = createdQuestionRepository.findAll().size();

        // Update the createdQuestion
        CreatedQuestion updatedCreatedQuestion = createdQuestionRepository.findById(createdQuestion.getId()).get();
        // Disconnect from session so that the updates on updatedCreatedQuestion are not directly saved in db
        em.detach(updatedCreatedQuestion);
        CreatedQuestionDTO createdQuestionDTO = createdQuestionMapper.toDto(updatedCreatedQuestion);

        restCreatedQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createdQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createdQuestionDTO))
            )
            .andExpect(status().isOk());

        // Validate the CreatedQuestion in the database
        List<CreatedQuestion> createdQuestionList = createdQuestionRepository.findAll();
        assertThat(createdQuestionList).hasSize(databaseSizeBeforeUpdate);
        CreatedQuestion testCreatedQuestion = createdQuestionList.get(createdQuestionList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCreatedQuestion() throws Exception {
        int databaseSizeBeforeUpdate = createdQuestionRepository.findAll().size();
        createdQuestion.setId(count.incrementAndGet());

        // Create the CreatedQuestion
        CreatedQuestionDTO createdQuestionDTO = createdQuestionMapper.toDto(createdQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreatedQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createdQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createdQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreatedQuestion in the database
        List<CreatedQuestion> createdQuestionList = createdQuestionRepository.findAll();
        assertThat(createdQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCreatedQuestion() throws Exception {
        int databaseSizeBeforeUpdate = createdQuestionRepository.findAll().size();
        createdQuestion.setId(count.incrementAndGet());

        // Create the CreatedQuestion
        CreatedQuestionDTO createdQuestionDTO = createdQuestionMapper.toDto(createdQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreatedQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createdQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreatedQuestion in the database
        List<CreatedQuestion> createdQuestionList = createdQuestionRepository.findAll();
        assertThat(createdQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCreatedQuestion() throws Exception {
        int databaseSizeBeforeUpdate = createdQuestionRepository.findAll().size();
        createdQuestion.setId(count.incrementAndGet());

        // Create the CreatedQuestion
        CreatedQuestionDTO createdQuestionDTO = createdQuestionMapper.toDto(createdQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreatedQuestionMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createdQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreatedQuestion in the database
        List<CreatedQuestion> createdQuestionList = createdQuestionRepository.findAll();
        assertThat(createdQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCreatedQuestionWithPatch() throws Exception {
        // Initialize the database
        createdQuestionRepository.saveAndFlush(createdQuestion);

        int databaseSizeBeforeUpdate = createdQuestionRepository.findAll().size();

        // Update the createdQuestion using partial update
        CreatedQuestion partialUpdatedCreatedQuestion = new CreatedQuestion();
        partialUpdatedCreatedQuestion.setId(createdQuestion.getId());

        restCreatedQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreatedQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreatedQuestion))
            )
            .andExpect(status().isOk());

        // Validate the CreatedQuestion in the database
        List<CreatedQuestion> createdQuestionList = createdQuestionRepository.findAll();
        assertThat(createdQuestionList).hasSize(databaseSizeBeforeUpdate);
        CreatedQuestion testCreatedQuestion = createdQuestionList.get(createdQuestionList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCreatedQuestionWithPatch() throws Exception {
        // Initialize the database
        createdQuestionRepository.saveAndFlush(createdQuestion);

        int databaseSizeBeforeUpdate = createdQuestionRepository.findAll().size();

        // Update the createdQuestion using partial update
        CreatedQuestion partialUpdatedCreatedQuestion = new CreatedQuestion();
        partialUpdatedCreatedQuestion.setId(createdQuestion.getId());

        restCreatedQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreatedQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreatedQuestion))
            )
            .andExpect(status().isOk());

        // Validate the CreatedQuestion in the database
        List<CreatedQuestion> createdQuestionList = createdQuestionRepository.findAll();
        assertThat(createdQuestionList).hasSize(databaseSizeBeforeUpdate);
        CreatedQuestion testCreatedQuestion = createdQuestionList.get(createdQuestionList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCreatedQuestion() throws Exception {
        int databaseSizeBeforeUpdate = createdQuestionRepository.findAll().size();
        createdQuestion.setId(count.incrementAndGet());

        // Create the CreatedQuestion
        CreatedQuestionDTO createdQuestionDTO = createdQuestionMapper.toDto(createdQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreatedQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, createdQuestionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createdQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreatedQuestion in the database
        List<CreatedQuestion> createdQuestionList = createdQuestionRepository.findAll();
        assertThat(createdQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCreatedQuestion() throws Exception {
        int databaseSizeBeforeUpdate = createdQuestionRepository.findAll().size();
        createdQuestion.setId(count.incrementAndGet());

        // Create the CreatedQuestion
        CreatedQuestionDTO createdQuestionDTO = createdQuestionMapper.toDto(createdQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreatedQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createdQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreatedQuestion in the database
        List<CreatedQuestion> createdQuestionList = createdQuestionRepository.findAll();
        assertThat(createdQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCreatedQuestion() throws Exception {
        int databaseSizeBeforeUpdate = createdQuestionRepository.findAll().size();
        createdQuestion.setId(count.incrementAndGet());

        // Create the CreatedQuestion
        CreatedQuestionDTO createdQuestionDTO = createdQuestionMapper.toDto(createdQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreatedQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createdQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreatedQuestion in the database
        List<CreatedQuestion> createdQuestionList = createdQuestionRepository.findAll();
        assertThat(createdQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCreatedQuestion() throws Exception {
        // Initialize the database
        createdQuestionRepository.saveAndFlush(createdQuestion);

        int databaseSizeBeforeDelete = createdQuestionRepository.findAll().size();

        // Delete the createdQuestion
        restCreatedQuestionMockMvc
            .perform(delete(ENTITY_API_URL_ID, createdQuestion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CreatedQuestion> createdQuestionList = createdQuestionRepository.findAll();
        assertThat(createdQuestionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
