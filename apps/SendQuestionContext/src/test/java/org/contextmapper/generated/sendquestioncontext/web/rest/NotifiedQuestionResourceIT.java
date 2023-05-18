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
import org.contextmapper.generated.sendquestioncontext.domain.NotifiedQuestion;
import org.contextmapper.generated.sendquestioncontext.repository.NotifiedQuestionRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedQuestionDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.NotifiedQuestionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NotifiedQuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NotifiedQuestionResourceIT {

    private static final String ENTITY_API_URL = "/api/notified-questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NotifiedQuestionRepository notifiedQuestionRepository;

    @Autowired
    private NotifiedQuestionMapper notifiedQuestionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNotifiedQuestionMockMvc;

    private NotifiedQuestion notifiedQuestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifiedQuestion createEntity(EntityManager em) {
        NotifiedQuestion notifiedQuestion = new NotifiedQuestion();
        return notifiedQuestion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifiedQuestion createUpdatedEntity(EntityManager em) {
        NotifiedQuestion notifiedQuestion = new NotifiedQuestion();
        return notifiedQuestion;
    }

    @BeforeEach
    public void initTest() {
        notifiedQuestion = createEntity(em);
    }

    @Test
    @Transactional
    void createNotifiedQuestion() throws Exception {
        int databaseSizeBeforeCreate = notifiedQuestionRepository.findAll().size();
        // Create the NotifiedQuestion
        NotifiedQuestionDTO notifiedQuestionDTO = notifiedQuestionMapper.toDto(notifiedQuestion);
        restNotifiedQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(notifiedQuestionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NotifiedQuestion in the database
        List<NotifiedQuestion> notifiedQuestionList = notifiedQuestionRepository.findAll();
        assertThat(notifiedQuestionList).hasSize(databaseSizeBeforeCreate + 1);
        NotifiedQuestion testNotifiedQuestion = notifiedQuestionList.get(notifiedQuestionList.size() - 1);
    }

    @Test
    @Transactional
    void createNotifiedQuestionWithExistingId() throws Exception {
        // Create the NotifiedQuestion with an existing ID
        notifiedQuestion.setId(1L);
        NotifiedQuestionDTO notifiedQuestionDTO = notifiedQuestionMapper.toDto(notifiedQuestion);

        int databaseSizeBeforeCreate = notifiedQuestionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotifiedQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(notifiedQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifiedQuestion in the database
        List<NotifiedQuestion> notifiedQuestionList = notifiedQuestionRepository.findAll();
        assertThat(notifiedQuestionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNotifiedQuestions() throws Exception {
        // Initialize the database
        notifiedQuestionRepository.saveAndFlush(notifiedQuestion);

        // Get all the notifiedQuestionList
        restNotifiedQuestionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notifiedQuestion.getId().intValue())));
    }

    @Test
    @Transactional
    void getNotifiedQuestion() throws Exception {
        // Initialize the database
        notifiedQuestionRepository.saveAndFlush(notifiedQuestion);

        // Get the notifiedQuestion
        restNotifiedQuestionMockMvc
            .perform(get(ENTITY_API_URL_ID, notifiedQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notifiedQuestion.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingNotifiedQuestion() throws Exception {
        // Get the notifiedQuestion
        restNotifiedQuestionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNotifiedQuestion() throws Exception {
        // Initialize the database
        notifiedQuestionRepository.saveAndFlush(notifiedQuestion);

        int databaseSizeBeforeUpdate = notifiedQuestionRepository.findAll().size();

        // Update the notifiedQuestion
        NotifiedQuestion updatedNotifiedQuestion = notifiedQuestionRepository.findById(notifiedQuestion.getId()).get();
        // Disconnect from session so that the updates on updatedNotifiedQuestion are not directly saved in db
        em.detach(updatedNotifiedQuestion);
        NotifiedQuestionDTO notifiedQuestionDTO = notifiedQuestionMapper.toDto(updatedNotifiedQuestion);

        restNotifiedQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, notifiedQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifiedQuestionDTO))
            )
            .andExpect(status().isOk());

        // Validate the NotifiedQuestion in the database
        List<NotifiedQuestion> notifiedQuestionList = notifiedQuestionRepository.findAll();
        assertThat(notifiedQuestionList).hasSize(databaseSizeBeforeUpdate);
        NotifiedQuestion testNotifiedQuestion = notifiedQuestionList.get(notifiedQuestionList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingNotifiedQuestion() throws Exception {
        int databaseSizeBeforeUpdate = notifiedQuestionRepository.findAll().size();
        notifiedQuestion.setId(count.incrementAndGet());

        // Create the NotifiedQuestion
        NotifiedQuestionDTO notifiedQuestionDTO = notifiedQuestionMapper.toDto(notifiedQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotifiedQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, notifiedQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifiedQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifiedQuestion in the database
        List<NotifiedQuestion> notifiedQuestionList = notifiedQuestionRepository.findAll();
        assertThat(notifiedQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNotifiedQuestion() throws Exception {
        int databaseSizeBeforeUpdate = notifiedQuestionRepository.findAll().size();
        notifiedQuestion.setId(count.incrementAndGet());

        // Create the NotifiedQuestion
        NotifiedQuestionDTO notifiedQuestionDTO = notifiedQuestionMapper.toDto(notifiedQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifiedQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifiedQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifiedQuestion in the database
        List<NotifiedQuestion> notifiedQuestionList = notifiedQuestionRepository.findAll();
        assertThat(notifiedQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNotifiedQuestion() throws Exception {
        int databaseSizeBeforeUpdate = notifiedQuestionRepository.findAll().size();
        notifiedQuestion.setId(count.incrementAndGet());

        // Create the NotifiedQuestion
        NotifiedQuestionDTO notifiedQuestionDTO = notifiedQuestionMapper.toDto(notifiedQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifiedQuestionMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(notifiedQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NotifiedQuestion in the database
        List<NotifiedQuestion> notifiedQuestionList = notifiedQuestionRepository.findAll();
        assertThat(notifiedQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNotifiedQuestionWithPatch() throws Exception {
        // Initialize the database
        notifiedQuestionRepository.saveAndFlush(notifiedQuestion);

        int databaseSizeBeforeUpdate = notifiedQuestionRepository.findAll().size();

        // Update the notifiedQuestion using partial update
        NotifiedQuestion partialUpdatedNotifiedQuestion = new NotifiedQuestion();
        partialUpdatedNotifiedQuestion.setId(notifiedQuestion.getId());

        restNotifiedQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNotifiedQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNotifiedQuestion))
            )
            .andExpect(status().isOk());

        // Validate the NotifiedQuestion in the database
        List<NotifiedQuestion> notifiedQuestionList = notifiedQuestionRepository.findAll();
        assertThat(notifiedQuestionList).hasSize(databaseSizeBeforeUpdate);
        NotifiedQuestion testNotifiedQuestion = notifiedQuestionList.get(notifiedQuestionList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateNotifiedQuestionWithPatch() throws Exception {
        // Initialize the database
        notifiedQuestionRepository.saveAndFlush(notifiedQuestion);

        int databaseSizeBeforeUpdate = notifiedQuestionRepository.findAll().size();

        // Update the notifiedQuestion using partial update
        NotifiedQuestion partialUpdatedNotifiedQuestion = new NotifiedQuestion();
        partialUpdatedNotifiedQuestion.setId(notifiedQuestion.getId());

        restNotifiedQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNotifiedQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNotifiedQuestion))
            )
            .andExpect(status().isOk());

        // Validate the NotifiedQuestion in the database
        List<NotifiedQuestion> notifiedQuestionList = notifiedQuestionRepository.findAll();
        assertThat(notifiedQuestionList).hasSize(databaseSizeBeforeUpdate);
        NotifiedQuestion testNotifiedQuestion = notifiedQuestionList.get(notifiedQuestionList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingNotifiedQuestion() throws Exception {
        int databaseSizeBeforeUpdate = notifiedQuestionRepository.findAll().size();
        notifiedQuestion.setId(count.incrementAndGet());

        // Create the NotifiedQuestion
        NotifiedQuestionDTO notifiedQuestionDTO = notifiedQuestionMapper.toDto(notifiedQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotifiedQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, notifiedQuestionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notifiedQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifiedQuestion in the database
        List<NotifiedQuestion> notifiedQuestionList = notifiedQuestionRepository.findAll();
        assertThat(notifiedQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNotifiedQuestion() throws Exception {
        int databaseSizeBeforeUpdate = notifiedQuestionRepository.findAll().size();
        notifiedQuestion.setId(count.incrementAndGet());

        // Create the NotifiedQuestion
        NotifiedQuestionDTO notifiedQuestionDTO = notifiedQuestionMapper.toDto(notifiedQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifiedQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notifiedQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifiedQuestion in the database
        List<NotifiedQuestion> notifiedQuestionList = notifiedQuestionRepository.findAll();
        assertThat(notifiedQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNotifiedQuestion() throws Exception {
        int databaseSizeBeforeUpdate = notifiedQuestionRepository.findAll().size();
        notifiedQuestion.setId(count.incrementAndGet());

        // Create the NotifiedQuestion
        NotifiedQuestionDTO notifiedQuestionDTO = notifiedQuestionMapper.toDto(notifiedQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifiedQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notifiedQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NotifiedQuestion in the database
        List<NotifiedQuestion> notifiedQuestionList = notifiedQuestionRepository.findAll();
        assertThat(notifiedQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNotifiedQuestion() throws Exception {
        // Initialize the database
        notifiedQuestionRepository.saveAndFlush(notifiedQuestion);

        int databaseSizeBeforeDelete = notifiedQuestionRepository.findAll().size();

        // Delete the notifiedQuestion
        restNotifiedQuestionMockMvc
            .perform(delete(ENTITY_API_URL_ID, notifiedQuestion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotifiedQuestion> notifiedQuestionList = notifiedQuestionRepository.findAll();
        assertThat(notifiedQuestionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
