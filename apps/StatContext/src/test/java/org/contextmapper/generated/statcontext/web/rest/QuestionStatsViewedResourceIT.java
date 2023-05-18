package org.contextmapper.generated.statcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.statcontext.IntegrationTest;
import org.contextmapper.generated.statcontext.domain.QuestionStatsViewed;
import org.contextmapper.generated.statcontext.repository.QuestionStatsViewedRepository;
import org.contextmapper.generated.statcontext.service.dto.QuestionStatsViewedDTO;
import org.contextmapper.generated.statcontext.service.mapper.QuestionStatsViewedMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionStatsViewedResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionStatsViewedResourceIT {

    private static final String ENTITY_API_URL = "/api/question-stats-vieweds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionStatsViewedRepository questionStatsViewedRepository;

    @Autowired
    private QuestionStatsViewedMapper questionStatsViewedMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionStatsViewedMockMvc;

    private QuestionStatsViewed questionStatsViewed;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionStatsViewed createEntity(EntityManager em) {
        QuestionStatsViewed questionStatsViewed = new QuestionStatsViewed();
        return questionStatsViewed;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionStatsViewed createUpdatedEntity(EntityManager em) {
        QuestionStatsViewed questionStatsViewed = new QuestionStatsViewed();
        return questionStatsViewed;
    }

    @BeforeEach
    public void initTest() {
        questionStatsViewed = createEntity(em);
    }

    @Test
    @Transactional
    void createQuestionStatsViewed() throws Exception {
        int databaseSizeBeforeCreate = questionStatsViewedRepository.findAll().size();
        // Create the QuestionStatsViewed
        QuestionStatsViewedDTO questionStatsViewedDTO = questionStatsViewedMapper.toDto(questionStatsViewed);
        restQuestionStatsViewedMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionStatsViewedDTO))
            )
            .andExpect(status().isCreated());

        // Validate the QuestionStatsViewed in the database
        List<QuestionStatsViewed> questionStatsViewedList = questionStatsViewedRepository.findAll();
        assertThat(questionStatsViewedList).hasSize(databaseSizeBeforeCreate + 1);
        QuestionStatsViewed testQuestionStatsViewed = questionStatsViewedList.get(questionStatsViewedList.size() - 1);
    }

    @Test
    @Transactional
    void createQuestionStatsViewedWithExistingId() throws Exception {
        // Create the QuestionStatsViewed with an existing ID
        questionStatsViewed.setId(1L);
        QuestionStatsViewedDTO questionStatsViewedDTO = questionStatsViewedMapper.toDto(questionStatsViewed);

        int databaseSizeBeforeCreate = questionStatsViewedRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionStatsViewedMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionStatsViewed in the database
        List<QuestionStatsViewed> questionStatsViewedList = questionStatsViewedRepository.findAll();
        assertThat(questionStatsViewedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQuestionStatsVieweds() throws Exception {
        // Initialize the database
        questionStatsViewedRepository.saveAndFlush(questionStatsViewed);

        // Get all the questionStatsViewedList
        restQuestionStatsViewedMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionStatsViewed.getId().intValue())));
    }

    @Test
    @Transactional
    void getQuestionStatsViewed() throws Exception {
        // Initialize the database
        questionStatsViewedRepository.saveAndFlush(questionStatsViewed);

        // Get the questionStatsViewed
        restQuestionStatsViewedMockMvc
            .perform(get(ENTITY_API_URL_ID, questionStatsViewed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionStatsViewed.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingQuestionStatsViewed() throws Exception {
        // Get the questionStatsViewed
        restQuestionStatsViewedMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQuestionStatsViewed() throws Exception {
        // Initialize the database
        questionStatsViewedRepository.saveAndFlush(questionStatsViewed);

        int databaseSizeBeforeUpdate = questionStatsViewedRepository.findAll().size();

        // Update the questionStatsViewed
        QuestionStatsViewed updatedQuestionStatsViewed = questionStatsViewedRepository.findById(questionStatsViewed.getId()).get();
        // Disconnect from session so that the updates on updatedQuestionStatsViewed are not directly saved in db
        em.detach(updatedQuestionStatsViewed);
        QuestionStatsViewedDTO questionStatsViewedDTO = questionStatsViewedMapper.toDto(updatedQuestionStatsViewed);

        restQuestionStatsViewedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionStatsViewedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionStatsViewedDTO))
            )
            .andExpect(status().isOk());

        // Validate the QuestionStatsViewed in the database
        List<QuestionStatsViewed> questionStatsViewedList = questionStatsViewedRepository.findAll();
        assertThat(questionStatsViewedList).hasSize(databaseSizeBeforeUpdate);
        QuestionStatsViewed testQuestionStatsViewed = questionStatsViewedList.get(questionStatsViewedList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingQuestionStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = questionStatsViewedRepository.findAll().size();
        questionStatsViewed.setId(count.incrementAndGet());

        // Create the QuestionStatsViewed
        QuestionStatsViewedDTO questionStatsViewedDTO = questionStatsViewedMapper.toDto(questionStatsViewed);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionStatsViewedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionStatsViewedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionStatsViewed in the database
        List<QuestionStatsViewed> questionStatsViewedList = questionStatsViewedRepository.findAll();
        assertThat(questionStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQuestionStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = questionStatsViewedRepository.findAll().size();
        questionStatsViewed.setId(count.incrementAndGet());

        // Create the QuestionStatsViewed
        QuestionStatsViewedDTO questionStatsViewedDTO = questionStatsViewedMapper.toDto(questionStatsViewed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionStatsViewedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionStatsViewed in the database
        List<QuestionStatsViewed> questionStatsViewedList = questionStatsViewedRepository.findAll();
        assertThat(questionStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQuestionStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = questionStatsViewedRepository.findAll().size();
        questionStatsViewed.setId(count.incrementAndGet());

        // Create the QuestionStatsViewed
        QuestionStatsViewedDTO questionStatsViewedDTO = questionStatsViewedMapper.toDto(questionStatsViewed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionStatsViewedMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionStatsViewedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuestionStatsViewed in the database
        List<QuestionStatsViewed> questionStatsViewedList = questionStatsViewedRepository.findAll();
        assertThat(questionStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQuestionStatsViewedWithPatch() throws Exception {
        // Initialize the database
        questionStatsViewedRepository.saveAndFlush(questionStatsViewed);

        int databaseSizeBeforeUpdate = questionStatsViewedRepository.findAll().size();

        // Update the questionStatsViewed using partial update
        QuestionStatsViewed partialUpdatedQuestionStatsViewed = new QuestionStatsViewed();
        partialUpdatedQuestionStatsViewed.setId(questionStatsViewed.getId());

        restQuestionStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestionStatsViewed.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestionStatsViewed))
            )
            .andExpect(status().isOk());

        // Validate the QuestionStatsViewed in the database
        List<QuestionStatsViewed> questionStatsViewedList = questionStatsViewedRepository.findAll();
        assertThat(questionStatsViewedList).hasSize(databaseSizeBeforeUpdate);
        QuestionStatsViewed testQuestionStatsViewed = questionStatsViewedList.get(questionStatsViewedList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateQuestionStatsViewedWithPatch() throws Exception {
        // Initialize the database
        questionStatsViewedRepository.saveAndFlush(questionStatsViewed);

        int databaseSizeBeforeUpdate = questionStatsViewedRepository.findAll().size();

        // Update the questionStatsViewed using partial update
        QuestionStatsViewed partialUpdatedQuestionStatsViewed = new QuestionStatsViewed();
        partialUpdatedQuestionStatsViewed.setId(questionStatsViewed.getId());

        restQuestionStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestionStatsViewed.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestionStatsViewed))
            )
            .andExpect(status().isOk());

        // Validate the QuestionStatsViewed in the database
        List<QuestionStatsViewed> questionStatsViewedList = questionStatsViewedRepository.findAll();
        assertThat(questionStatsViewedList).hasSize(databaseSizeBeforeUpdate);
        QuestionStatsViewed testQuestionStatsViewed = questionStatsViewedList.get(questionStatsViewedList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingQuestionStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = questionStatsViewedRepository.findAll().size();
        questionStatsViewed.setId(count.incrementAndGet());

        // Create the QuestionStatsViewed
        QuestionStatsViewedDTO questionStatsViewedDTO = questionStatsViewedMapper.toDto(questionStatsViewed);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, questionStatsViewedDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionStatsViewed in the database
        List<QuestionStatsViewed> questionStatsViewedList = questionStatsViewedRepository.findAll();
        assertThat(questionStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQuestionStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = questionStatsViewedRepository.findAll().size();
        questionStatsViewed.setId(count.incrementAndGet());

        // Create the QuestionStatsViewed
        QuestionStatsViewedDTO questionStatsViewedDTO = questionStatsViewedMapper.toDto(questionStatsViewed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionStatsViewed in the database
        List<QuestionStatsViewed> questionStatsViewedList = questionStatsViewedRepository.findAll();
        assertThat(questionStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQuestionStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = questionStatsViewedRepository.findAll().size();
        questionStatsViewed.setId(count.incrementAndGet());

        // Create the QuestionStatsViewed
        QuestionStatsViewedDTO questionStatsViewedDTO = questionStatsViewedMapper.toDto(questionStatsViewed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionStatsViewedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuestionStatsViewed in the database
        List<QuestionStatsViewed> questionStatsViewedList = questionStatsViewedRepository.findAll();
        assertThat(questionStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQuestionStatsViewed() throws Exception {
        // Initialize the database
        questionStatsViewedRepository.saveAndFlush(questionStatsViewed);

        int databaseSizeBeforeDelete = questionStatsViewedRepository.findAll().size();

        // Delete the questionStatsViewed
        restQuestionStatsViewedMockMvc
            .perform(delete(ENTITY_API_URL_ID, questionStatsViewed.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QuestionStatsViewed> questionStatsViewedList = questionStatsViewedRepository.findAll();
        assertThat(questionStatsViewedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
