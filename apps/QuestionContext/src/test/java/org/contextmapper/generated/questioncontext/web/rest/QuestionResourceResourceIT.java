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
import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.domain.enumeration.States;
import org.contextmapper.generated.questioncontext.domain.enumeration.Types;
import org.contextmapper.generated.questioncontext.repository.QuestionResourceRepository;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.mapper.QuestionResourceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QuestionResourceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionResourceResourceIT {

    private static final String DEFAULT_QUESTION_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_CONTENT = "BBBBBBBBBB";

    private static final States DEFAULT_QUESTION_STATE = States.WAITING;
    private static final States UPDATED_QUESTION_STATE = States.ASSOCIATED;

    private static final Types DEFAULT_RESOURCE_TYPE = Types.IMG_URL;
    private static final Types UPDATED_RESOURCE_TYPE = Types.URL;

    private static final String ENTITY_API_URL = "/api/question-resources";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionResourceRepository questionResourceRepository;

    @Autowired
    private QuestionResourceMapper questionResourceMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionResourceMockMvc;

    private QuestionResource questionResource;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionResource createEntity(EntityManager em) {
        QuestionResource questionResource = new QuestionResource()
            .questionContent(DEFAULT_QUESTION_CONTENT)
            .questionState(DEFAULT_QUESTION_STATE)
            .resourceType(DEFAULT_RESOURCE_TYPE);
        return questionResource;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionResource createUpdatedEntity(EntityManager em) {
        QuestionResource questionResource = new QuestionResource()
            .questionContent(UPDATED_QUESTION_CONTENT)
            .questionState(UPDATED_QUESTION_STATE)
            .resourceType(UPDATED_RESOURCE_TYPE);
        return questionResource;
    }

    @BeforeEach
    public void initTest() {
        questionResource = createEntity(em);
    }

    @Test
    @Transactional
    void createQuestionResource() throws Exception {
        int databaseSizeBeforeCreate = questionResourceRepository.findAll().size();
        // Create the QuestionResource
        QuestionResourceDTO questionResourceDTO = questionResourceMapper.toDto(questionResource);
        restQuestionResourceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionResourceDTO))
            )
            .andExpect(status().isCreated());

        // Validate the QuestionResource in the database
        List<QuestionResource> questionResourceList = questionResourceRepository.findAll();
        assertThat(questionResourceList).hasSize(databaseSizeBeforeCreate + 1);
        QuestionResource testQuestionResource = questionResourceList.get(questionResourceList.size() - 1);
        assertThat(testQuestionResource.getQuestionContent()).isEqualTo(DEFAULT_QUESTION_CONTENT);
        assertThat(testQuestionResource.getQuestionState()).isEqualTo(DEFAULT_QUESTION_STATE);
        assertThat(testQuestionResource.getResourceType()).isEqualTo(DEFAULT_RESOURCE_TYPE);
    }

    @Test
    @Transactional
    void createQuestionResourceWithExistingId() throws Exception {
        // Create the QuestionResource with an existing ID
        questionResource.setId(1L);
        QuestionResourceDTO questionResourceDTO = questionResourceMapper.toDto(questionResource);

        int databaseSizeBeforeCreate = questionResourceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionResourceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionResourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionResource in the database
        List<QuestionResource> questionResourceList = questionResourceRepository.findAll();
        assertThat(questionResourceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQuestionResources() throws Exception {
        // Initialize the database
        questionResourceRepository.saveAndFlush(questionResource);

        // Get all the questionResourceList
        restQuestionResourceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionResource.getId().intValue())))
            .andExpect(jsonPath("$.[*].questionContent").value(hasItem(DEFAULT_QUESTION_CONTENT)))
            .andExpect(jsonPath("$.[*].questionState").value(hasItem(DEFAULT_QUESTION_STATE.toString())))
            .andExpect(jsonPath("$.[*].resourceType").value(hasItem(DEFAULT_RESOURCE_TYPE.toString())));
    }

    @Test
    @Transactional
    void getQuestionResource() throws Exception {
        // Initialize the database
        questionResourceRepository.saveAndFlush(questionResource);

        // Get the questionResource
        restQuestionResourceMockMvc
            .perform(get(ENTITY_API_URL_ID, questionResource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionResource.getId().intValue()))
            .andExpect(jsonPath("$.questionContent").value(DEFAULT_QUESTION_CONTENT))
            .andExpect(jsonPath("$.questionState").value(DEFAULT_QUESTION_STATE.toString()))
            .andExpect(jsonPath("$.resourceType").value(DEFAULT_RESOURCE_TYPE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingQuestionResource() throws Exception {
        // Get the questionResource
        restQuestionResourceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQuestionResource() throws Exception {
        // Initialize the database
        questionResourceRepository.saveAndFlush(questionResource);

        int databaseSizeBeforeUpdate = questionResourceRepository.findAll().size();

        // Update the questionResource
        QuestionResource updatedQuestionResource = questionResourceRepository.findById(questionResource.getId()).get();
        // Disconnect from session so that the updates on updatedQuestionResource are not directly saved in db
        em.detach(updatedQuestionResource);
        updatedQuestionResource
            .questionContent(UPDATED_QUESTION_CONTENT)
            .questionState(UPDATED_QUESTION_STATE)
            .resourceType(UPDATED_RESOURCE_TYPE);
        QuestionResourceDTO questionResourceDTO = questionResourceMapper.toDto(updatedQuestionResource);

        restQuestionResourceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionResourceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceDTO))
            )
            .andExpect(status().isOk());

        // Validate the QuestionResource in the database
        List<QuestionResource> questionResourceList = questionResourceRepository.findAll();
        assertThat(questionResourceList).hasSize(databaseSizeBeforeUpdate);
        QuestionResource testQuestionResource = questionResourceList.get(questionResourceList.size() - 1);
        assertThat(testQuestionResource.getQuestionContent()).isEqualTo(UPDATED_QUESTION_CONTENT);
        assertThat(testQuestionResource.getQuestionState()).isEqualTo(UPDATED_QUESTION_STATE);
        assertThat(testQuestionResource.getResourceType()).isEqualTo(UPDATED_RESOURCE_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingQuestionResource() throws Exception {
        int databaseSizeBeforeUpdate = questionResourceRepository.findAll().size();
        questionResource.setId(count.incrementAndGet());

        // Create the QuestionResource
        QuestionResourceDTO questionResourceDTO = questionResourceMapper.toDto(questionResource);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionResourceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionResourceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionResource in the database
        List<QuestionResource> questionResourceList = questionResourceRepository.findAll();
        assertThat(questionResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQuestionResource() throws Exception {
        int databaseSizeBeforeUpdate = questionResourceRepository.findAll().size();
        questionResource.setId(count.incrementAndGet());

        // Create the QuestionResource
        QuestionResourceDTO questionResourceDTO = questionResourceMapper.toDto(questionResource);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionResourceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionResource in the database
        List<QuestionResource> questionResourceList = questionResourceRepository.findAll();
        assertThat(questionResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQuestionResource() throws Exception {
        int databaseSizeBeforeUpdate = questionResourceRepository.findAll().size();
        questionResource.setId(count.incrementAndGet());

        // Create the QuestionResource
        QuestionResourceDTO questionResourceDTO = questionResourceMapper.toDto(questionResource);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionResourceMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionResourceDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuestionResource in the database
        List<QuestionResource> questionResourceList = questionResourceRepository.findAll();
        assertThat(questionResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQuestionResourceWithPatch() throws Exception {
        // Initialize the database
        questionResourceRepository.saveAndFlush(questionResource);

        int databaseSizeBeforeUpdate = questionResourceRepository.findAll().size();

        // Update the questionResource using partial update
        QuestionResource partialUpdatedQuestionResource = new QuestionResource();
        partialUpdatedQuestionResource.setId(questionResource.getId());

        partialUpdatedQuestionResource.questionState(UPDATED_QUESTION_STATE);

        restQuestionResourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestionResource.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestionResource))
            )
            .andExpect(status().isOk());

        // Validate the QuestionResource in the database
        List<QuestionResource> questionResourceList = questionResourceRepository.findAll();
        assertThat(questionResourceList).hasSize(databaseSizeBeforeUpdate);
        QuestionResource testQuestionResource = questionResourceList.get(questionResourceList.size() - 1);
        assertThat(testQuestionResource.getQuestionContent()).isEqualTo(DEFAULT_QUESTION_CONTENT);
        assertThat(testQuestionResource.getQuestionState()).isEqualTo(UPDATED_QUESTION_STATE);
        assertThat(testQuestionResource.getResourceType()).isEqualTo(DEFAULT_RESOURCE_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateQuestionResourceWithPatch() throws Exception {
        // Initialize the database
        questionResourceRepository.saveAndFlush(questionResource);

        int databaseSizeBeforeUpdate = questionResourceRepository.findAll().size();

        // Update the questionResource using partial update
        QuestionResource partialUpdatedQuestionResource = new QuestionResource();
        partialUpdatedQuestionResource.setId(questionResource.getId());

        partialUpdatedQuestionResource
            .questionContent(UPDATED_QUESTION_CONTENT)
            .questionState(UPDATED_QUESTION_STATE)
            .resourceType(UPDATED_RESOURCE_TYPE);

        restQuestionResourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestionResource.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestionResource))
            )
            .andExpect(status().isOk());

        // Validate the QuestionResource in the database
        List<QuestionResource> questionResourceList = questionResourceRepository.findAll();
        assertThat(questionResourceList).hasSize(databaseSizeBeforeUpdate);
        QuestionResource testQuestionResource = questionResourceList.get(questionResourceList.size() - 1);
        assertThat(testQuestionResource.getQuestionContent()).isEqualTo(UPDATED_QUESTION_CONTENT);
        assertThat(testQuestionResource.getQuestionState()).isEqualTo(UPDATED_QUESTION_STATE);
        assertThat(testQuestionResource.getResourceType()).isEqualTo(UPDATED_RESOURCE_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingQuestionResource() throws Exception {
        int databaseSizeBeforeUpdate = questionResourceRepository.findAll().size();
        questionResource.setId(count.incrementAndGet());

        // Create the QuestionResource
        QuestionResourceDTO questionResourceDTO = questionResourceMapper.toDto(questionResource);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionResourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, questionResourceDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionResource in the database
        List<QuestionResource> questionResourceList = questionResourceRepository.findAll();
        assertThat(questionResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQuestionResource() throws Exception {
        int databaseSizeBeforeUpdate = questionResourceRepository.findAll().size();
        questionResource.setId(count.incrementAndGet());

        // Create the QuestionResource
        QuestionResourceDTO questionResourceDTO = questionResourceMapper.toDto(questionResource);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionResourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionResource in the database
        List<QuestionResource> questionResourceList = questionResourceRepository.findAll();
        assertThat(questionResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQuestionResource() throws Exception {
        int databaseSizeBeforeUpdate = questionResourceRepository.findAll().size();
        questionResource.setId(count.incrementAndGet());

        // Create the QuestionResource
        QuestionResourceDTO questionResourceDTO = questionResourceMapper.toDto(questionResource);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionResourceMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionResourceDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuestionResource in the database
        List<QuestionResource> questionResourceList = questionResourceRepository.findAll();
        assertThat(questionResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQuestionResource() throws Exception {
        // Initialize the database
        questionResourceRepository.saveAndFlush(questionResource);

        int databaseSizeBeforeDelete = questionResourceRepository.findAll().size();

        // Delete the questionResource
        restQuestionResourceMockMvc
            .perform(delete(ENTITY_API_URL_ID, questionResource.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QuestionResource> questionResourceList = questionResourceRepository.findAll();
        assertThat(questionResourceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
