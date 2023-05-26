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
import org.contextmapper.generated.sendquestioncontext.domain.SendByPreferencesCommand;
import org.contextmapper.generated.sendquestioncontext.repository.SendByPreferencesCommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SendByPreferencesCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SendByPreferencesCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/send-by-preferences-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SendByPreferencesCommandRepository sendByPreferencesCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSendByPreferencesCommandMockMvc;

    private SendByPreferencesCommand sendByPreferencesCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SendByPreferencesCommand createEntity(EntityManager em) {
        SendByPreferencesCommand sendByPreferencesCommand = new SendByPreferencesCommand();
        return sendByPreferencesCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SendByPreferencesCommand createUpdatedEntity(EntityManager em) {
        SendByPreferencesCommand sendByPreferencesCommand = new SendByPreferencesCommand();
        return sendByPreferencesCommand;
    }

    @BeforeEach
    public void initTest() {
        sendByPreferencesCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createSendByPreferencesCommand() throws Exception {
        int databaseSizeBeforeCreate = sendByPreferencesCommandRepository.findAll().size();
        // Create the SendByPreferencesCommand
        restSendByPreferencesCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendByPreferencesCommand))
            )
            .andExpect(status().isCreated());

        // Validate the SendByPreferencesCommand in the database
        List<SendByPreferencesCommand> sendByPreferencesCommandList = sendByPreferencesCommandRepository.findAll();
        assertThat(sendByPreferencesCommandList).hasSize(databaseSizeBeforeCreate + 1);
        SendByPreferencesCommand testSendByPreferencesCommand = sendByPreferencesCommandList.get(sendByPreferencesCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createSendByPreferencesCommandWithExistingId() throws Exception {
        // Create the SendByPreferencesCommand with an existing ID
        sendByPreferencesCommand.setId(1L);

        int databaseSizeBeforeCreate = sendByPreferencesCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSendByPreferencesCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendByPreferencesCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendByPreferencesCommand in the database
        List<SendByPreferencesCommand> sendByPreferencesCommandList = sendByPreferencesCommandRepository.findAll();
        assertThat(sendByPreferencesCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSendByPreferencesCommands() throws Exception {
        // Initialize the database
        sendByPreferencesCommandRepository.saveAndFlush(sendByPreferencesCommand);

        // Get all the sendByPreferencesCommandList
        restSendByPreferencesCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sendByPreferencesCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getSendByPreferencesCommand() throws Exception {
        // Initialize the database
        sendByPreferencesCommandRepository.saveAndFlush(sendByPreferencesCommand);

        // Get the sendByPreferencesCommand
        restSendByPreferencesCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, sendByPreferencesCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sendByPreferencesCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingSendByPreferencesCommand() throws Exception {
        // Get the sendByPreferencesCommand
        restSendByPreferencesCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSendByPreferencesCommand() throws Exception {
        // Initialize the database
        sendByPreferencesCommandRepository.saveAndFlush(sendByPreferencesCommand);

        int databaseSizeBeforeUpdate = sendByPreferencesCommandRepository.findAll().size();

        // Update the sendByPreferencesCommand
        SendByPreferencesCommand updatedSendByPreferencesCommand = sendByPreferencesCommandRepository
            .findById(sendByPreferencesCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedSendByPreferencesCommand are not directly saved in db
        em.detach(updatedSendByPreferencesCommand);

        restSendByPreferencesCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSendByPreferencesCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSendByPreferencesCommand))
            )
            .andExpect(status().isOk());

        // Validate the SendByPreferencesCommand in the database
        List<SendByPreferencesCommand> sendByPreferencesCommandList = sendByPreferencesCommandRepository.findAll();
        assertThat(sendByPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
        SendByPreferencesCommand testSendByPreferencesCommand = sendByPreferencesCommandList.get(sendByPreferencesCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingSendByPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendByPreferencesCommandRepository.findAll().size();
        sendByPreferencesCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSendByPreferencesCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sendByPreferencesCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendByPreferencesCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendByPreferencesCommand in the database
        List<SendByPreferencesCommand> sendByPreferencesCommandList = sendByPreferencesCommandRepository.findAll();
        assertThat(sendByPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSendByPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendByPreferencesCommandRepository.findAll().size();
        sendByPreferencesCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSendByPreferencesCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendByPreferencesCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendByPreferencesCommand in the database
        List<SendByPreferencesCommand> sendByPreferencesCommandList = sendByPreferencesCommandRepository.findAll();
        assertThat(sendByPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSendByPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendByPreferencesCommandRepository.findAll().size();
        sendByPreferencesCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSendByPreferencesCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sendByPreferencesCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SendByPreferencesCommand in the database
        List<SendByPreferencesCommand> sendByPreferencesCommandList = sendByPreferencesCommandRepository.findAll();
        assertThat(sendByPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSendByPreferencesCommandWithPatch() throws Exception {
        // Initialize the database
        sendByPreferencesCommandRepository.saveAndFlush(sendByPreferencesCommand);

        int databaseSizeBeforeUpdate = sendByPreferencesCommandRepository.findAll().size();

        // Update the sendByPreferencesCommand using partial update
        SendByPreferencesCommand partialUpdatedSendByPreferencesCommand = new SendByPreferencesCommand();
        partialUpdatedSendByPreferencesCommand.setId(sendByPreferencesCommand.getId());

        restSendByPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSendByPreferencesCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSendByPreferencesCommand))
            )
            .andExpect(status().isOk());

        // Validate the SendByPreferencesCommand in the database
        List<SendByPreferencesCommand> sendByPreferencesCommandList = sendByPreferencesCommandRepository.findAll();
        assertThat(sendByPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
        SendByPreferencesCommand testSendByPreferencesCommand = sendByPreferencesCommandList.get(sendByPreferencesCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateSendByPreferencesCommandWithPatch() throws Exception {
        // Initialize the database
        sendByPreferencesCommandRepository.saveAndFlush(sendByPreferencesCommand);

        int databaseSizeBeforeUpdate = sendByPreferencesCommandRepository.findAll().size();

        // Update the sendByPreferencesCommand using partial update
        SendByPreferencesCommand partialUpdatedSendByPreferencesCommand = new SendByPreferencesCommand();
        partialUpdatedSendByPreferencesCommand.setId(sendByPreferencesCommand.getId());

        restSendByPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSendByPreferencesCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSendByPreferencesCommand))
            )
            .andExpect(status().isOk());

        // Validate the SendByPreferencesCommand in the database
        List<SendByPreferencesCommand> sendByPreferencesCommandList = sendByPreferencesCommandRepository.findAll();
        assertThat(sendByPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
        SendByPreferencesCommand testSendByPreferencesCommand = sendByPreferencesCommandList.get(sendByPreferencesCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingSendByPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendByPreferencesCommandRepository.findAll().size();
        sendByPreferencesCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSendByPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sendByPreferencesCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sendByPreferencesCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendByPreferencesCommand in the database
        List<SendByPreferencesCommand> sendByPreferencesCommandList = sendByPreferencesCommandRepository.findAll();
        assertThat(sendByPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSendByPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendByPreferencesCommandRepository.findAll().size();
        sendByPreferencesCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSendByPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sendByPreferencesCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the SendByPreferencesCommand in the database
        List<SendByPreferencesCommand> sendByPreferencesCommandList = sendByPreferencesCommandRepository.findAll();
        assertThat(sendByPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSendByPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = sendByPreferencesCommandRepository.findAll().size();
        sendByPreferencesCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSendByPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sendByPreferencesCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SendByPreferencesCommand in the database
        List<SendByPreferencesCommand> sendByPreferencesCommandList = sendByPreferencesCommandRepository.findAll();
        assertThat(sendByPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSendByPreferencesCommand() throws Exception {
        // Initialize the database
        sendByPreferencesCommandRepository.saveAndFlush(sendByPreferencesCommand);

        int databaseSizeBeforeDelete = sendByPreferencesCommandRepository.findAll().size();

        // Delete the sendByPreferencesCommand
        restSendByPreferencesCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, sendByPreferencesCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SendByPreferencesCommand> sendByPreferencesCommandList = sendByPreferencesCommandRepository.findAll();
        assertThat(sendByPreferencesCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
