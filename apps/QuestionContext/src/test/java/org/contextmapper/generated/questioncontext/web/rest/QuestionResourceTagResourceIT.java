package org.contextmapper.generated.questioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.questioncontext.IntegrationTest;
import org.contextmapper.generated.questioncontext.domain.QuestionResourceTag;
import org.contextmapper.generated.questioncontext.repository.QuestionResourceTagRepository;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagDTO;
import org.contextmapper.generated.questioncontext.service.mapper.QuestionResourceTagMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionResourceTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionResourceTagResourceIT {

    private static final String ENTITY_API_URL = "/api/question-resource-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionResourceTagRepository questionResourceTagRepository;

    @Autowired
    private QuestionResourceTagMapper questionResourceTagMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionResourceTagMockMvc;

    private QuestionResourceTag questionResourceTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionResourceTag createEntity(EntityManager em) {
        QuestionResourceTag questionResourceTag = new QuestionResourceTag();
        return questionResourceTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionResourceTag createUpdatedEntity(EntityManager em) {
        QuestionResourceTag questionResourceTag = new QuestionResourceTag();
        return questionResourceTag;
    }

    @BeforeEach
    public void initTest() {
        questionResourceTag = createEntity(em);
    }

    @Test
    @Transactional
    void createQuestionResourceTag() throws Exception {
        int databaseSizeBeforeCreate = questionResourceTagRepository.findAll().size();
        // Create the QuestionResourceTag
        QuestionResourceTagDTO questionResourceTagDTO = questionResourceTagMapper.toDto(questionResourceTag);
        restQuestionResourceTagMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceTagDTO))
            )
            .andExpect(status().isCreated());

        // Validate the QuestionResourceTag in the database
        List<QuestionResourceTag> questionResourceTagList = questionResourceTagRepository.findAll();
        assertThat(questionResourceTagList).hasSize(databaseSizeBeforeCreate + 1);
        QuestionResourceTag testQuestionResourceTag = questionResourceTagList.get(questionResourceTagList.size() - 1);
    }

    @Test
    @Transactional
    void createQuestionResourceTagWithExistingId() throws Exception {
        // Create the QuestionResourceTag with an existing ID
        questionResourceTag.setId(1L);
        QuestionResourceTagDTO questionResourceTagDTO = questionResourceTagMapper.toDto(questionResourceTag);

        int databaseSizeBeforeCreate = questionResourceTagRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionResourceTagMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionResourceTag in the database
        List<QuestionResourceTag> questionResourceTagList = questionResourceTagRepository.findAll();
        assertThat(questionResourceTagList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQuestionResourceTags() throws Exception {
        // Initialize the database
        questionResourceTagRepository.saveAndFlush(questionResourceTag);

        // Get all the questionResourceTagList
        restQuestionResourceTagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionResourceTag.getId().intValue())));
    }

    @Test
    @Transactional
    void getQuestionResourceTag() throws Exception {
        // Initialize the database
        questionResourceTagRepository.saveAndFlush(questionResourceTag);

        // Get the questionResourceTag
        restQuestionResourceTagMockMvc
            .perform(get(ENTITY_API_URL_ID, questionResourceTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionResourceTag.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingQuestionResourceTag() throws Exception {
        // Get the questionResourceTag
        restQuestionResourceTagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQuestionResourceTag() throws Exception {
        // Initialize the database
        questionResourceTagRepository.saveAndFlush(questionResourceTag);

        int databaseSizeBeforeUpdate = questionResourceTagRepository.findAll().size();

        // Update the questionResourceTag
        QuestionResourceTag updatedQuestionResourceTag = questionResourceTagRepository.findById(questionResourceTag.getId()).get();
        // Disconnect from session so that the updates on updatedQuestionResourceTag are not directly saved in db
        em.detach(updatedQuestionResourceTag);
        QuestionResourceTagDTO questionResourceTagDTO = questionResourceTagMapper.toDto(updatedQuestionResourceTag);

        restQuestionResourceTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionResourceTagDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceTagDTO))
            )
            .andExpect(status().isOk());

        // Validate the QuestionResourceTag in the database
        List<QuestionResourceTag> questionResourceTagList = questionResourceTagRepository.findAll();
        assertThat(questionResourceTagList).hasSize(databaseSizeBeforeUpdate);
        QuestionResourceTag testQuestionResourceTag = questionResourceTagList.get(questionResourceTagList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingQuestionResourceTag() throws Exception {
        int databaseSizeBeforeUpdate = questionResourceTagRepository.findAll().size();
        questionResourceTag.setId(count.incrementAndGet());

        // Create the QuestionResourceTag
        QuestionResourceTagDTO questionResourceTagDTO = questionResourceTagMapper.toDto(questionResourceTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionResourceTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionResourceTagDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionResourceTag in the database
        List<QuestionResourceTag> questionResourceTagList = questionResourceTagRepository.findAll();
        assertThat(questionResourceTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQuestionResourceTag() throws Exception {
        int databaseSizeBeforeUpdate = questionResourceTagRepository.findAll().size();
        questionResourceTag.setId(count.incrementAndGet());

        // Create the QuestionResourceTag
        QuestionResourceTagDTO questionResourceTagDTO = questionResourceTagMapper.toDto(questionResourceTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionResourceTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionResourceTag in the database
        List<QuestionResourceTag> questionResourceTagList = questionResourceTagRepository.findAll();
        assertThat(questionResourceTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQuestionResourceTag() throws Exception {
        int databaseSizeBeforeUpdate = questionResourceTagRepository.findAll().size();
        questionResourceTag.setId(count.incrementAndGet());

        // Create the QuestionResourceTag
        QuestionResourceTagDTO questionResourceTagDTO = questionResourceTagMapper.toDto(questionResourceTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionResourceTagMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceTagDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuestionResourceTag in the database
        List<QuestionResourceTag> questionResourceTagList = questionResourceTagRepository.findAll();
        assertThat(questionResourceTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQuestionResourceTagWithPatch() throws Exception {
        // Initialize the database
        questionResourceTagRepository.saveAndFlush(questionResourceTag);

        int databaseSizeBeforeUpdate = questionResourceTagRepository.findAll().size();

        // Update the questionResourceTag using partial update
        QuestionResourceTag partialUpdatedQuestionResourceTag = new QuestionResourceTag();
        partialUpdatedQuestionResourceTag.setId(questionResourceTag.getId());

        restQuestionResourceTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestionResourceTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestionResourceTag))
            )
            .andExpect(status().isOk());

        // Validate the QuestionResourceTag in the database
        List<QuestionResourceTag> questionResourceTagList = questionResourceTagRepository.findAll();
        assertThat(questionResourceTagList).hasSize(databaseSizeBeforeUpdate);
        QuestionResourceTag testQuestionResourceTag = questionResourceTagList.get(questionResourceTagList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateQuestionResourceTagWithPatch() throws Exception {
        // Initialize the database
        questionResourceTagRepository.saveAndFlush(questionResourceTag);

        int databaseSizeBeforeUpdate = questionResourceTagRepository.findAll().size();

        // Update the questionResourceTag using partial update
        QuestionResourceTag partialUpdatedQuestionResourceTag = new QuestionResourceTag();
        partialUpdatedQuestionResourceTag.setId(questionResourceTag.getId());

        restQuestionResourceTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestionResourceTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestionResourceTag))
            )
            .andExpect(status().isOk());

        // Validate the QuestionResourceTag in the database
        List<QuestionResourceTag> questionResourceTagList = questionResourceTagRepository.findAll();
        assertThat(questionResourceTagList).hasSize(databaseSizeBeforeUpdate);
        QuestionResourceTag testQuestionResourceTag = questionResourceTagList.get(questionResourceTagList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingQuestionResourceTag() throws Exception {
        int databaseSizeBeforeUpdate = questionResourceTagRepository.findAll().size();
        questionResourceTag.setId(count.incrementAndGet());

        // Create the QuestionResourceTag
        QuestionResourceTagDTO questionResourceTagDTO = questionResourceTagMapper.toDto(questionResourceTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionResourceTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, questionResourceTagDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionResourceTag in the database
        List<QuestionResourceTag> questionResourceTagList = questionResourceTagRepository.findAll();
        assertThat(questionResourceTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQuestionResourceTag() throws Exception {
        int databaseSizeBeforeUpdate = questionResourceTagRepository.findAll().size();
        questionResourceTag.setId(count.incrementAndGet());

        // Create the QuestionResourceTag
        QuestionResourceTagDTO questionResourceTagDTO = questionResourceTagMapper.toDto(questionResourceTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionResourceTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionResourceTag in the database
        List<QuestionResourceTag> questionResourceTagList = questionResourceTagRepository.findAll();
        assertThat(questionResourceTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQuestionResourceTag() throws Exception {
        int databaseSizeBeforeUpdate = questionResourceTagRepository.findAll().size();
        questionResourceTag.setId(count.incrementAndGet());

        // Create the QuestionResourceTag
        QuestionResourceTagDTO questionResourceTagDTO = questionResourceTagMapper.toDto(questionResourceTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionResourceTagMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceTagDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuestionResourceTag in the database
        List<QuestionResourceTag> questionResourceTagList = questionResourceTagRepository.findAll();
        assertThat(questionResourceTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQuestionResourceTag() throws Exception {
        // Initialize the database
        questionResourceTagRepository.saveAndFlush(questionResourceTag);

        int databaseSizeBeforeDelete = questionResourceTagRepository.findAll().size();

        // Delete the questionResourceTag
        restQuestionResourceTagMockMvc
            .perform(delete(ENTITY_API_URL_ID, questionResourceTag.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QuestionResourceTag> questionResourceTagList = questionResourceTagRepository.findAll();
        assertThat(questionResourceTagList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
