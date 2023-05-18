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
import org.contextmapper.generated.sendquestioncontext.domain.SendQuestionByTagsPreferences;
import org.contextmapper.generated.sendquestioncontext.repository.SendQuestionByTagsPreferencesRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.SendQuestionByTagsPreferencesDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.SendQuestionByTagsPreferencesMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SendQuestionByTagsPreferencesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SendQuestionByTagsPreferencesResourceIT {

    private static final String ENTITY_API_URL = "/api/send-question-by-tags-preferences";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SendQuestionByTagsPreferencesRepository sendQuestionByTagsPreferencesRepository;

    @Autowired
    private SendQuestionByTagsPreferencesMapper sendQuestionByTagsPreferencesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSendQuestionByTagsPreferencesMockMvc;

    private SendQuestionByTagsPreferences sendQuestionByTagsPreferences;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SendQuestionByTagsPreferences createEntity(EntityManager em) {
        SendQuestionByTagsPreferences sendQuestionByTagsPreferences = new SendQuestionByTagsPreferences();
        return sendQuestionByTagsPreferences;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SendQuestionByTagsPreferences createUpdatedEntity(EntityManager em) {
        SendQuestionByTagsPreferences sendQuestionByTagsPreferences = new SendQuestionByTagsPreferences();
        return sendQuestionByTagsPreferences;
    }

    @BeforeEach
    public void initTest() {
        sendQuestionByTagsPreferences = createEntity(em);
    }

    @Test
    @Transactional
    void createSendQuestionByTagsPreferences() throws Exception {
        int databaseSizeBeforeCreate = sendQuestionByTagsPreferencesRepository.findAll().size();
        // Create the SendQuestionByTagsPreferences
        SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO = sendQuestionByTagsPreferencesMapper.toDto(
            sendQuestionByTagsPreferences
        );
        restSendQuestionByTagsPreferencesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SendQuestionByTagsPreferences in the database
        List<SendQuestionByTagsPreferences> sendQuestionByTagsPreferencesList = sendQuestionByTagsPreferencesRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesList).hasSize(databaseSizeBeforeCreate + 1);
        SendQuestionByTagsPreferences testSendQuestionByTagsPreferences = sendQuestionByTagsPreferencesList.get(
            sendQuestionByTagsPreferencesList.size() - 1
        );
    }

    @Test
    @Transactional
    void createSendQuestionByTagsPreferencesWithExistingId() throws Exception {
        // Create the SendQuestionByTagsPreferences with an existing ID
        sendQuestionByTagsPreferences.setId(1L);
        SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO = sendQuestionByTagsPreferencesMapper.toDto(
            sendQuestionByTagsPreferences
        );

        int databaseSizeBeforeCreate = sendQuestionByTagsPreferencesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSendQuestionByTagsPreferencesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendQuestionByTagsPreferences in the database
        List<SendQuestionByTagsPreferences> sendQuestionByTagsPreferencesList = sendQuestionByTagsPreferencesRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSendQuestionByTagsPreferences() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesRepository.saveAndFlush(sendQuestionByTagsPreferences);

        // Get all the sendQuestionByTagsPreferencesList
        restSendQuestionByTagsPreferencesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sendQuestionByTagsPreferences.getId().intValue())));
    }

    @Test
    @Transactional
    void getSendQuestionByTagsPreferences() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesRepository.saveAndFlush(sendQuestionByTagsPreferences);

        // Get the sendQuestionByTagsPreferences
        restSendQuestionByTagsPreferencesMockMvc
            .perform(get(ENTITY_API_URL_ID, sendQuestionByTagsPreferences.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sendQuestionByTagsPreferences.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingSendQuestionByTagsPreferences() throws Exception {
        // Get the sendQuestionByTagsPreferences
        restSendQuestionByTagsPreferencesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSendQuestionByTagsPreferences() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesRepository.saveAndFlush(sendQuestionByTagsPreferences);

        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesRepository.findAll().size();

        // Update the sendQuestionByTagsPreferences
        SendQuestionByTagsPreferences updatedSendQuestionByTagsPreferences = sendQuestionByTagsPreferencesRepository
            .findById(sendQuestionByTagsPreferences.getId())
            .get();
        // Disconnect from session so that the updates on updatedSendQuestionByTagsPreferences are not directly saved in db
        em.detach(updatedSendQuestionByTagsPreferences);
        SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO = sendQuestionByTagsPreferencesMapper.toDto(
            updatedSendQuestionByTagsPreferences
        );

        restSendQuestionByTagsPreferencesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sendQuestionByTagsPreferencesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesDTO))
            )
            .andExpect(status().isOk());

        // Validate the SendQuestionByTagsPreferences in the database
        List<SendQuestionByTagsPreferences> sendQuestionByTagsPreferencesList = sendQuestionByTagsPreferencesRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesList).hasSize(databaseSizeBeforeUpdate);
        SendQuestionByTagsPreferences testSendQuestionByTagsPreferences = sendQuestionByTagsPreferencesList.get(
            sendQuestionByTagsPreferencesList.size() - 1
        );
    }

    @Test
    @Transactional
    void putNonExistingSendQuestionByTagsPreferences() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesRepository.findAll().size();
        sendQuestionByTagsPreferences.setId(count.incrementAndGet());

        // Create the SendQuestionByTagsPreferences
        SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO = sendQuestionByTagsPreferencesMapper.toDto(
            sendQuestionByTagsPreferences
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSendQuestionByTagsPreferencesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sendQuestionByTagsPreferencesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendQuestionByTagsPreferences in the database
        List<SendQuestionByTagsPreferences> sendQuestionByTagsPreferencesList = sendQuestionByTagsPreferencesRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSendQuestionByTagsPreferences() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesRepository.findAll().size();
        sendQuestionByTagsPreferences.setId(count.incrementAndGet());

        // Create the SendQuestionByTagsPreferences
        SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO = sendQuestionByTagsPreferencesMapper.toDto(
            sendQuestionByTagsPreferences
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSendQuestionByTagsPreferencesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendQuestionByTagsPreferences in the database
        List<SendQuestionByTagsPreferences> sendQuestionByTagsPreferencesList = sendQuestionByTagsPreferencesRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSendQuestionByTagsPreferences() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesRepository.findAll().size();
        sendQuestionByTagsPreferences.setId(count.incrementAndGet());

        // Create the SendQuestionByTagsPreferences
        SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO = sendQuestionByTagsPreferencesMapper.toDto(
            sendQuestionByTagsPreferences
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSendQuestionByTagsPreferencesMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SendQuestionByTagsPreferences in the database
        List<SendQuestionByTagsPreferences> sendQuestionByTagsPreferencesList = sendQuestionByTagsPreferencesRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSendQuestionByTagsPreferencesWithPatch() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesRepository.saveAndFlush(sendQuestionByTagsPreferences);

        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesRepository.findAll().size();

        // Update the sendQuestionByTagsPreferences using partial update
        SendQuestionByTagsPreferences partialUpdatedSendQuestionByTagsPreferences = new SendQuestionByTagsPreferences();
        partialUpdatedSendQuestionByTagsPreferences.setId(sendQuestionByTagsPreferences.getId());

        restSendQuestionByTagsPreferencesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSendQuestionByTagsPreferences.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSendQuestionByTagsPreferences))
            )
            .andExpect(status().isOk());

        // Validate the SendQuestionByTagsPreferences in the database
        List<SendQuestionByTagsPreferences> sendQuestionByTagsPreferencesList = sendQuestionByTagsPreferencesRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesList).hasSize(databaseSizeBeforeUpdate);
        SendQuestionByTagsPreferences testSendQuestionByTagsPreferences = sendQuestionByTagsPreferencesList.get(
            sendQuestionByTagsPreferencesList.size() - 1
        );
    }

    @Test
    @Transactional
    void fullUpdateSendQuestionByTagsPreferencesWithPatch() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesRepository.saveAndFlush(sendQuestionByTagsPreferences);

        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesRepository.findAll().size();

        // Update the sendQuestionByTagsPreferences using partial update
        SendQuestionByTagsPreferences partialUpdatedSendQuestionByTagsPreferences = new SendQuestionByTagsPreferences();
        partialUpdatedSendQuestionByTagsPreferences.setId(sendQuestionByTagsPreferences.getId());

        restSendQuestionByTagsPreferencesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSendQuestionByTagsPreferences.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSendQuestionByTagsPreferences))
            )
            .andExpect(status().isOk());

        // Validate the SendQuestionByTagsPreferences in the database
        List<SendQuestionByTagsPreferences> sendQuestionByTagsPreferencesList = sendQuestionByTagsPreferencesRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesList).hasSize(databaseSizeBeforeUpdate);
        SendQuestionByTagsPreferences testSendQuestionByTagsPreferences = sendQuestionByTagsPreferencesList.get(
            sendQuestionByTagsPreferencesList.size() - 1
        );
    }

    @Test
    @Transactional
    void patchNonExistingSendQuestionByTagsPreferences() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesRepository.findAll().size();
        sendQuestionByTagsPreferences.setId(count.incrementAndGet());

        // Create the SendQuestionByTagsPreferences
        SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO = sendQuestionByTagsPreferencesMapper.toDto(
            sendQuestionByTagsPreferences
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSendQuestionByTagsPreferencesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sendQuestionByTagsPreferencesDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendQuestionByTagsPreferences in the database
        List<SendQuestionByTagsPreferences> sendQuestionByTagsPreferencesList = sendQuestionByTagsPreferencesRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSendQuestionByTagsPreferences() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesRepository.findAll().size();
        sendQuestionByTagsPreferences.setId(count.incrementAndGet());

        // Create the SendQuestionByTagsPreferences
        SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO = sendQuestionByTagsPreferencesMapper.toDto(
            sendQuestionByTagsPreferences
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSendQuestionByTagsPreferencesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendQuestionByTagsPreferences in the database
        List<SendQuestionByTagsPreferences> sendQuestionByTagsPreferencesList = sendQuestionByTagsPreferencesRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSendQuestionByTagsPreferences() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesRepository.findAll().size();
        sendQuestionByTagsPreferences.setId(count.incrementAndGet());

        // Create the SendQuestionByTagsPreferences
        SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO = sendQuestionByTagsPreferencesMapper.toDto(
            sendQuestionByTagsPreferences
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSendQuestionByTagsPreferencesMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SendQuestionByTagsPreferences in the database
        List<SendQuestionByTagsPreferences> sendQuestionByTagsPreferencesList = sendQuestionByTagsPreferencesRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSendQuestionByTagsPreferences() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesRepository.saveAndFlush(sendQuestionByTagsPreferences);

        int databaseSizeBeforeDelete = sendQuestionByTagsPreferencesRepository.findAll().size();

        // Delete the sendQuestionByTagsPreferences
        restSendQuestionByTagsPreferencesMockMvc
            .perform(delete(ENTITY_API_URL_ID, sendQuestionByTagsPreferences.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SendQuestionByTagsPreferences> sendQuestionByTagsPreferencesList = sendQuestionByTagsPreferencesRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
