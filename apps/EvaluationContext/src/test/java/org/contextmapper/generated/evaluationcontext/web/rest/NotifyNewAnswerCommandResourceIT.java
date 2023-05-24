package org.contextmapper.generated.evaluationcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.evaluationcontext.IntegrationTest;
import org.contextmapper.generated.evaluationcontext.domain.NotifyNewAnswerCommand;
import org.contextmapper.generated.evaluationcontext.repository.NotifyNewAnswerCommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NotifyNewAnswerCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NotifyNewAnswerCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/notify-new-answer-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NotifyNewAnswerCommandRepository notifyNewAnswerCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNotifyNewAnswerCommandMockMvc;

    private NotifyNewAnswerCommand notifyNewAnswerCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifyNewAnswerCommand createEntity(EntityManager em) {
        NotifyNewAnswerCommand notifyNewAnswerCommand = new NotifyNewAnswerCommand();
        return notifyNewAnswerCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifyNewAnswerCommand createUpdatedEntity(EntityManager em) {
        NotifyNewAnswerCommand notifyNewAnswerCommand = new NotifyNewAnswerCommand();
        return notifyNewAnswerCommand;
    }

    @BeforeEach
    public void initTest() {
        notifyNewAnswerCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createNotifyNewAnswerCommand() throws Exception {
        int databaseSizeBeforeCreate = notifyNewAnswerCommandRepository.findAll().size();
        // Create the NotifyNewAnswerCommand
        restNotifyNewAnswerCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyNewAnswerCommand))
            )
            .andExpect(status().isCreated());

        // Validate the NotifyNewAnswerCommand in the database
        List<NotifyNewAnswerCommand> notifyNewAnswerCommandList = notifyNewAnswerCommandRepository.findAll();
        assertThat(notifyNewAnswerCommandList).hasSize(databaseSizeBeforeCreate + 1);
        NotifyNewAnswerCommand testNotifyNewAnswerCommand = notifyNewAnswerCommandList.get(notifyNewAnswerCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createNotifyNewAnswerCommandWithExistingId() throws Exception {
        // Create the NotifyNewAnswerCommand with an existing ID
        notifyNewAnswerCommand.setId(1L);

        int databaseSizeBeforeCreate = notifyNewAnswerCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotifyNewAnswerCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyNewAnswerCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifyNewAnswerCommand in the database
        List<NotifyNewAnswerCommand> notifyNewAnswerCommandList = notifyNewAnswerCommandRepository.findAll();
        assertThat(notifyNewAnswerCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNotifyNewAnswerCommands() throws Exception {
        // Initialize the database
        notifyNewAnswerCommandRepository.saveAndFlush(notifyNewAnswerCommand);

        // Get all the notifyNewAnswerCommandList
        restNotifyNewAnswerCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notifyNewAnswerCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getNotifyNewAnswerCommand() throws Exception {
        // Initialize the database
        notifyNewAnswerCommandRepository.saveAndFlush(notifyNewAnswerCommand);

        // Get the notifyNewAnswerCommand
        restNotifyNewAnswerCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, notifyNewAnswerCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notifyNewAnswerCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingNotifyNewAnswerCommand() throws Exception {
        // Get the notifyNewAnswerCommand
        restNotifyNewAnswerCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNotifyNewAnswerCommand() throws Exception {
        // Initialize the database
        notifyNewAnswerCommandRepository.saveAndFlush(notifyNewAnswerCommand);

        int databaseSizeBeforeUpdate = notifyNewAnswerCommandRepository.findAll().size();

        // Update the notifyNewAnswerCommand
        NotifyNewAnswerCommand updatedNotifyNewAnswerCommand = notifyNewAnswerCommandRepository
            .findById(notifyNewAnswerCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedNotifyNewAnswerCommand are not directly saved in db
        em.detach(updatedNotifyNewAnswerCommand);

        restNotifyNewAnswerCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedNotifyNewAnswerCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedNotifyNewAnswerCommand))
            )
            .andExpect(status().isOk());

        // Validate the NotifyNewAnswerCommand in the database
        List<NotifyNewAnswerCommand> notifyNewAnswerCommandList = notifyNewAnswerCommandRepository.findAll();
        assertThat(notifyNewAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
        NotifyNewAnswerCommand testNotifyNewAnswerCommand = notifyNewAnswerCommandList.get(notifyNewAnswerCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingNotifyNewAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = notifyNewAnswerCommandRepository.findAll().size();
        notifyNewAnswerCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotifyNewAnswerCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, notifyNewAnswerCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyNewAnswerCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifyNewAnswerCommand in the database
        List<NotifyNewAnswerCommand> notifyNewAnswerCommandList = notifyNewAnswerCommandRepository.findAll();
        assertThat(notifyNewAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNotifyNewAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = notifyNewAnswerCommandRepository.findAll().size();
        notifyNewAnswerCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifyNewAnswerCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyNewAnswerCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifyNewAnswerCommand in the database
        List<NotifyNewAnswerCommand> notifyNewAnswerCommandList = notifyNewAnswerCommandRepository.findAll();
        assertThat(notifyNewAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNotifyNewAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = notifyNewAnswerCommandRepository.findAll().size();
        notifyNewAnswerCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifyNewAnswerCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifyNewAnswerCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NotifyNewAnswerCommand in the database
        List<NotifyNewAnswerCommand> notifyNewAnswerCommandList = notifyNewAnswerCommandRepository.findAll();
        assertThat(notifyNewAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNotifyNewAnswerCommandWithPatch() throws Exception {
        // Initialize the database
        notifyNewAnswerCommandRepository.saveAndFlush(notifyNewAnswerCommand);

        int databaseSizeBeforeUpdate = notifyNewAnswerCommandRepository.findAll().size();

        // Update the notifyNewAnswerCommand using partial update
        NotifyNewAnswerCommand partialUpdatedNotifyNewAnswerCommand = new NotifyNewAnswerCommand();
        partialUpdatedNotifyNewAnswerCommand.setId(notifyNewAnswerCommand.getId());

        restNotifyNewAnswerCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNotifyNewAnswerCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNotifyNewAnswerCommand))
            )
            .andExpect(status().isOk());

        // Validate the NotifyNewAnswerCommand in the database
        List<NotifyNewAnswerCommand> notifyNewAnswerCommandList = notifyNewAnswerCommandRepository.findAll();
        assertThat(notifyNewAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
        NotifyNewAnswerCommand testNotifyNewAnswerCommand = notifyNewAnswerCommandList.get(notifyNewAnswerCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateNotifyNewAnswerCommandWithPatch() throws Exception {
        // Initialize the database
        notifyNewAnswerCommandRepository.saveAndFlush(notifyNewAnswerCommand);

        int databaseSizeBeforeUpdate = notifyNewAnswerCommandRepository.findAll().size();

        // Update the notifyNewAnswerCommand using partial update
        NotifyNewAnswerCommand partialUpdatedNotifyNewAnswerCommand = new NotifyNewAnswerCommand();
        partialUpdatedNotifyNewAnswerCommand.setId(notifyNewAnswerCommand.getId());

        restNotifyNewAnswerCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNotifyNewAnswerCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNotifyNewAnswerCommand))
            )
            .andExpect(status().isOk());

        // Validate the NotifyNewAnswerCommand in the database
        List<NotifyNewAnswerCommand> notifyNewAnswerCommandList = notifyNewAnswerCommandRepository.findAll();
        assertThat(notifyNewAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
        NotifyNewAnswerCommand testNotifyNewAnswerCommand = notifyNewAnswerCommandList.get(notifyNewAnswerCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingNotifyNewAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = notifyNewAnswerCommandRepository.findAll().size();
        notifyNewAnswerCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotifyNewAnswerCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, notifyNewAnswerCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notifyNewAnswerCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifyNewAnswerCommand in the database
        List<NotifyNewAnswerCommand> notifyNewAnswerCommandList = notifyNewAnswerCommandRepository.findAll();
        assertThat(notifyNewAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNotifyNewAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = notifyNewAnswerCommandRepository.findAll().size();
        notifyNewAnswerCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifyNewAnswerCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notifyNewAnswerCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifyNewAnswerCommand in the database
        List<NotifyNewAnswerCommand> notifyNewAnswerCommandList = notifyNewAnswerCommandRepository.findAll();
        assertThat(notifyNewAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNotifyNewAnswerCommand() throws Exception {
        int databaseSizeBeforeUpdate = notifyNewAnswerCommandRepository.findAll().size();
        notifyNewAnswerCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifyNewAnswerCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notifyNewAnswerCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NotifyNewAnswerCommand in the database
        List<NotifyNewAnswerCommand> notifyNewAnswerCommandList = notifyNewAnswerCommandRepository.findAll();
        assertThat(notifyNewAnswerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNotifyNewAnswerCommand() throws Exception {
        // Initialize the database
        notifyNewAnswerCommandRepository.saveAndFlush(notifyNewAnswerCommand);

        int databaseSizeBeforeDelete = notifyNewAnswerCommandRepository.findAll().size();

        // Delete the notifyNewAnswerCommand
        restNotifyNewAnswerCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, notifyNewAnswerCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotifyNewAnswerCommand> notifyNewAnswerCommandList = notifyNewAnswerCommandRepository.findAll();
        assertThat(notifyNewAnswerCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
