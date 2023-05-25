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
import org.contextmapper.generated.sendquestioncontext.domain.SendQuestionByTagsPreferencesCommand;
import org.contextmapper.generated.sendquestioncontext.repository.SendQuestionByTagsPreferencesCommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SendQuestionByTagsPreferencesCommandHandlerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SendQuestionByTagsPreferencesCommandHandlerResourceIT {

    private static final String ENTITY_API_URL = "/api/send-question-by-tags-preferences-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SendQuestionByTagsPreferencesCommandRepository sendQuestionByTagsPreferencesCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSendQuestionByTagsPreferencesCommandMockMvc;

    private SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SendQuestionByTagsPreferencesCommand createEntity(EntityManager em) {
        SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand = new SendQuestionByTagsPreferencesCommand();
        return sendQuestionByTagsPreferencesCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SendQuestionByTagsPreferencesCommand createUpdatedEntity(EntityManager em) {
        SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand = new SendQuestionByTagsPreferencesCommand();
        return sendQuestionByTagsPreferencesCommand;
    }

    @BeforeEach
    public void initTest() {
        sendQuestionByTagsPreferencesCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeCreate = sendQuestionByTagsPreferencesCommandRepository.findAll().size();
        // Create the SendQuestionByTagsPreferencesCommand
        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            )
            .andExpect(status().isCreated());

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeCreate + 1);
        SendQuestionByTagsPreferencesCommand testSendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommandList.get(
            sendQuestionByTagsPreferencesCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void createSendQuestionByTagsPreferencesCommandWithExistingId() throws Exception {
        // Create the SendQuestionByTagsPreferencesCommand with an existing ID
        sendQuestionByTagsPreferencesCommand.setId(1L);

        int databaseSizeBeforeCreate = sendQuestionByTagsPreferencesCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSendQuestionByTagsPreferencesCommands() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesCommandRepository.saveAndFlush(sendQuestionByTagsPreferencesCommand);

        // Get all the sendQuestionByTagsPreferencesCommandList
        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sendQuestionByTagsPreferencesCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getSendQuestionByTagsPreferencesCommand() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesCommandRepository.saveAndFlush(sendQuestionByTagsPreferencesCommand);

        // Get the sendQuestionByTagsPreferencesCommand
        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, sendQuestionByTagsPreferencesCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sendQuestionByTagsPreferencesCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingSendQuestionByTagsPreferencesCommand() throws Exception {
        // Get the sendQuestionByTagsPreferencesCommand
        restSendQuestionByTagsPreferencesCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSendQuestionByTagsPreferencesCommand() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesCommandRepository.saveAndFlush(sendQuestionByTagsPreferencesCommand);

        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().size();

        // Update the sendQuestionByTagsPreferencesCommand
        SendQuestionByTagsPreferencesCommand updatedSendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommandRepository
            .findById(sendQuestionByTagsPreferencesCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedSendQuestionByTagsPreferencesCommand are not directly saved in db
        em.detach(updatedSendQuestionByTagsPreferencesCommand);

        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSendQuestionByTagsPreferencesCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSendQuestionByTagsPreferencesCommand))
            )
            .andExpect(status().isOk());

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
        SendQuestionByTagsPreferencesCommand testSendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommandList.get(
            sendQuestionByTagsPreferencesCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void putNonExistingSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().size();
        sendQuestionByTagsPreferencesCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sendQuestionByTagsPreferencesCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().size();
        sendQuestionByTagsPreferencesCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().size();
        sendQuestionByTagsPreferencesCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSendQuestionByTagsPreferencesCommandWithPatch() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesCommandRepository.saveAndFlush(sendQuestionByTagsPreferencesCommand);

        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().size();

        // Update the sendQuestionByTagsPreferencesCommand using partial update
        SendQuestionByTagsPreferencesCommand partialUpdatedSendQuestionByTagsPreferencesCommand = new SendQuestionByTagsPreferencesCommand();
        partialUpdatedSendQuestionByTagsPreferencesCommand.setId(sendQuestionByTagsPreferencesCommand.getId());

        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSendQuestionByTagsPreferencesCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSendQuestionByTagsPreferencesCommand))
            )
            .andExpect(status().isOk());

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
        SendQuestionByTagsPreferencesCommand testSendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommandList.get(
            sendQuestionByTagsPreferencesCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void fullUpdateSendQuestionByTagsPreferencesCommandWithPatch() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesCommandRepository.saveAndFlush(sendQuestionByTagsPreferencesCommand);

        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().size();

        // Update the sendQuestionByTagsPreferencesCommand using partial update
        SendQuestionByTagsPreferencesCommand partialUpdatedSendQuestionByTagsPreferencesCommand = new SendQuestionByTagsPreferencesCommand();
        partialUpdatedSendQuestionByTagsPreferencesCommand.setId(sendQuestionByTagsPreferencesCommand.getId());

        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSendQuestionByTagsPreferencesCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSendQuestionByTagsPreferencesCommand))
            )
            .andExpect(status().isOk());

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
        SendQuestionByTagsPreferencesCommand testSendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommandList.get(
            sendQuestionByTagsPreferencesCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void patchNonExistingSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().size();
        sendQuestionByTagsPreferencesCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sendQuestionByTagsPreferencesCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().size();
        sendQuestionByTagsPreferencesCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSendQuestionByTagsPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendQuestionByTagsPreferencesCommandRepository.findAll().size();
        sendQuestionByTagsPreferencesCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sendQuestionByTagsPreferencesCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SendQuestionByTagsPreferencesCommand in the database
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSendQuestionByTagsPreferencesCommand() throws Exception {
        // Initialize the database
        sendQuestionByTagsPreferencesCommandRepository.saveAndFlush(sendQuestionByTagsPreferencesCommand);

        int databaseSizeBeforeDelete = sendQuestionByTagsPreferencesCommandRepository.findAll().size();

        // Delete the sendQuestionByTagsPreferencesCommand
        restSendQuestionByTagsPreferencesCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, sendQuestionByTagsPreferencesCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommandList = sendQuestionByTagsPreferencesCommandRepository.findAll();
        assertThat(sendQuestionByTagsPreferencesCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
