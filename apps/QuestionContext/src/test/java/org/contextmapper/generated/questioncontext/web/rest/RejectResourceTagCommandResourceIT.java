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
import org.contextmapper.generated.questioncontext.domain.RejectResourceTagCommand;
import org.contextmapper.generated.questioncontext.repository.RejectResourceTagCommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RejectResourceTagCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RejectResourceTagCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/reject-resource-tag-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RejectResourceTagCommandRepository rejectResourceTagCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRejectResourceTagCommandMockMvc;

    private RejectResourceTagCommand rejectResourceTagCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RejectResourceTagCommand createEntity(EntityManager em) {
        RejectResourceTagCommand rejectResourceTagCommand = new RejectResourceTagCommand();
        return rejectResourceTagCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RejectResourceTagCommand createUpdatedEntity(EntityManager em) {
        RejectResourceTagCommand rejectResourceTagCommand = new RejectResourceTagCommand();
        return rejectResourceTagCommand;
    }

    @BeforeEach
    public void initTest() {
        rejectResourceTagCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeCreate = rejectResourceTagCommandRepository.findAll().size();
        // Create the RejectResourceTagCommand
        restRejectResourceTagCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            )
            .andExpect(status().isCreated());

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeCreate + 1);
        RejectResourceTagCommand testRejectResourceTagCommand = rejectResourceTagCommandList.get(rejectResourceTagCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createRejectResourceTagCommandWithExistingId() throws Exception {
        // Create the RejectResourceTagCommand with an existing ID
        rejectResourceTagCommand.setId(1L);

        int databaseSizeBeforeCreate = rejectResourceTagCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRejectResourceTagCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRejectResourceTagCommands() throws Exception {
        // Initialize the database
        rejectResourceTagCommandRepository.saveAndFlush(rejectResourceTagCommand);

        // Get all the rejectResourceTagCommandList
        restRejectResourceTagCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rejectResourceTagCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getRejectResourceTagCommand() throws Exception {
        // Initialize the database
        rejectResourceTagCommandRepository.saveAndFlush(rejectResourceTagCommand);

        // Get the rejectResourceTagCommand
        restRejectResourceTagCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, rejectResourceTagCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rejectResourceTagCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingRejectResourceTagCommand() throws Exception {
        // Get the rejectResourceTagCommand
        restRejectResourceTagCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRejectResourceTagCommand() throws Exception {
        // Initialize the database
        rejectResourceTagCommandRepository.saveAndFlush(rejectResourceTagCommand);

        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().size();

        // Update the rejectResourceTagCommand
        RejectResourceTagCommand updatedRejectResourceTagCommand = rejectResourceTagCommandRepository
            .findById(rejectResourceTagCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedRejectResourceTagCommand are not directly saved in db
        em.detach(updatedRejectResourceTagCommand);

        restRejectResourceTagCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRejectResourceTagCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRejectResourceTagCommand))
            )
            .andExpect(status().isOk());

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
        RejectResourceTagCommand testRejectResourceTagCommand = rejectResourceTagCommandList.get(rejectResourceTagCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().size();
        rejectResourceTagCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRejectResourceTagCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rejectResourceTagCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().size();
        rejectResourceTagCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRejectResourceTagCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().size();
        rejectResourceTagCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRejectResourceTagCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRejectResourceTagCommandWithPatch() throws Exception {
        // Initialize the database
        rejectResourceTagCommandRepository.saveAndFlush(rejectResourceTagCommand);

        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().size();

        // Update the rejectResourceTagCommand using partial update
        RejectResourceTagCommand partialUpdatedRejectResourceTagCommand = new RejectResourceTagCommand();
        partialUpdatedRejectResourceTagCommand.setId(rejectResourceTagCommand.getId());

        restRejectResourceTagCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRejectResourceTagCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRejectResourceTagCommand))
            )
            .andExpect(status().isOk());

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
        RejectResourceTagCommand testRejectResourceTagCommand = rejectResourceTagCommandList.get(rejectResourceTagCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateRejectResourceTagCommandWithPatch() throws Exception {
        // Initialize the database
        rejectResourceTagCommandRepository.saveAndFlush(rejectResourceTagCommand);

        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().size();

        // Update the rejectResourceTagCommand using partial update
        RejectResourceTagCommand partialUpdatedRejectResourceTagCommand = new RejectResourceTagCommand();
        partialUpdatedRejectResourceTagCommand.setId(rejectResourceTagCommand.getId());

        restRejectResourceTagCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRejectResourceTagCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRejectResourceTagCommand))
            )
            .andExpect(status().isOk());

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
        RejectResourceTagCommand testRejectResourceTagCommand = rejectResourceTagCommandList.get(rejectResourceTagCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().size();
        rejectResourceTagCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRejectResourceTagCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rejectResourceTagCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().size();
        rejectResourceTagCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRejectResourceTagCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRejectResourceTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectResourceTagCommandRepository.findAll().size();
        rejectResourceTagCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRejectResourceTagCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rejectResourceTagCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RejectResourceTagCommand in the database
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRejectResourceTagCommand() throws Exception {
        // Initialize the database
        rejectResourceTagCommandRepository.saveAndFlush(rejectResourceTagCommand);

        int databaseSizeBeforeDelete = rejectResourceTagCommandRepository.findAll().size();

        // Delete the rejectResourceTagCommand
        restRejectResourceTagCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, rejectResourceTagCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RejectResourceTagCommand> rejectResourceTagCommandList = rejectResourceTagCommandRepository.findAll();
        assertThat(rejectResourceTagCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
