package org.contextmapper.generated.usermanagementcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.usermanagementcontext.IntegrationTest;
import org.contextmapper.generated.usermanagementcontext.domain.RejectUserCommand;
import org.contextmapper.generated.usermanagementcontext.repository.RejectUserCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.RejectUserCommandDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.RejectUserCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RejectUserCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RejectUserCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/reject-user-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RejectUserCommandRepository rejectUserCommandRepository;

    @Autowired
    private RejectUserCommandMapper rejectUserCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRejectUserCommandMockMvc;

    private RejectUserCommand rejectUserCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RejectUserCommand createEntity(EntityManager em) {
        RejectUserCommand rejectUserCommand = new RejectUserCommand();
        return rejectUserCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RejectUserCommand createUpdatedEntity(EntityManager em) {
        RejectUserCommand rejectUserCommand = new RejectUserCommand();
        return rejectUserCommand;
    }

    @BeforeEach
    public void initTest() {
        rejectUserCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createRejectUserCommand() throws Exception {
        int databaseSizeBeforeCreate = rejectUserCommandRepository.findAll().size();
        // Create the RejectUserCommand
        RejectUserCommandDTO rejectUserCommandDTO = rejectUserCommandMapper.toDto(rejectUserCommand);
        restRejectUserCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectUserCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RejectUserCommand in the database
        List<RejectUserCommand> rejectUserCommandList = rejectUserCommandRepository.findAll();
        assertThat(rejectUserCommandList).hasSize(databaseSizeBeforeCreate + 1);
        RejectUserCommand testRejectUserCommand = rejectUserCommandList.get(rejectUserCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createRejectUserCommandWithExistingId() throws Exception {
        // Create the RejectUserCommand with an existing ID
        rejectUserCommand.setId(1L);
        RejectUserCommandDTO rejectUserCommandDTO = rejectUserCommandMapper.toDto(rejectUserCommand);

        int databaseSizeBeforeCreate = rejectUserCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRejectUserCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectUserCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectUserCommand in the database
        List<RejectUserCommand> rejectUserCommandList = rejectUserCommandRepository.findAll();
        assertThat(rejectUserCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRejectUserCommands() throws Exception {
        // Initialize the database
        rejectUserCommandRepository.saveAndFlush(rejectUserCommand);

        // Get all the rejectUserCommandList
        restRejectUserCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rejectUserCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getRejectUserCommand() throws Exception {
        // Initialize the database
        rejectUserCommandRepository.saveAndFlush(rejectUserCommand);

        // Get the rejectUserCommand
        restRejectUserCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, rejectUserCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rejectUserCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingRejectUserCommand() throws Exception {
        // Get the rejectUserCommand
        restRejectUserCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRejectUserCommand() throws Exception {
        // Initialize the database
        rejectUserCommandRepository.saveAndFlush(rejectUserCommand);

        int databaseSizeBeforeUpdate = rejectUserCommandRepository.findAll().size();

        // Update the rejectUserCommand
        RejectUserCommand updatedRejectUserCommand = rejectUserCommandRepository.findById(rejectUserCommand.getId()).get();
        // Disconnect from session so that the updates on updatedRejectUserCommand are not directly saved in db
        em.detach(updatedRejectUserCommand);
        RejectUserCommandDTO rejectUserCommandDTO = rejectUserCommandMapper.toDto(updatedRejectUserCommand);

        restRejectUserCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rejectUserCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectUserCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the RejectUserCommand in the database
        List<RejectUserCommand> rejectUserCommandList = rejectUserCommandRepository.findAll();
        assertThat(rejectUserCommandList).hasSize(databaseSizeBeforeUpdate);
        RejectUserCommand testRejectUserCommand = rejectUserCommandList.get(rejectUserCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingRejectUserCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectUserCommandRepository.findAll().size();
        rejectUserCommand.setId(count.incrementAndGet());

        // Create the RejectUserCommand
        RejectUserCommandDTO rejectUserCommandDTO = rejectUserCommandMapper.toDto(rejectUserCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRejectUserCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rejectUserCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectUserCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectUserCommand in the database
        List<RejectUserCommand> rejectUserCommandList = rejectUserCommandRepository.findAll();
        assertThat(rejectUserCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRejectUserCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectUserCommandRepository.findAll().size();
        rejectUserCommand.setId(count.incrementAndGet());

        // Create the RejectUserCommand
        RejectUserCommandDTO rejectUserCommandDTO = rejectUserCommandMapper.toDto(rejectUserCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRejectUserCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rejectUserCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectUserCommand in the database
        List<RejectUserCommand> rejectUserCommandList = rejectUserCommandRepository.findAll();
        assertThat(rejectUserCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRejectUserCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectUserCommandRepository.findAll().size();
        rejectUserCommand.setId(count.incrementAndGet());

        // Create the RejectUserCommand
        RejectUserCommandDTO rejectUserCommandDTO = rejectUserCommandMapper.toDto(rejectUserCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRejectUserCommandMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rejectUserCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RejectUserCommand in the database
        List<RejectUserCommand> rejectUserCommandList = rejectUserCommandRepository.findAll();
        assertThat(rejectUserCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRejectUserCommandWithPatch() throws Exception {
        // Initialize the database
        rejectUserCommandRepository.saveAndFlush(rejectUserCommand);

        int databaseSizeBeforeUpdate = rejectUserCommandRepository.findAll().size();

        // Update the rejectUserCommand using partial update
        RejectUserCommand partialUpdatedRejectUserCommand = new RejectUserCommand();
        partialUpdatedRejectUserCommand.setId(rejectUserCommand.getId());

        restRejectUserCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRejectUserCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRejectUserCommand))
            )
            .andExpect(status().isOk());

        // Validate the RejectUserCommand in the database
        List<RejectUserCommand> rejectUserCommandList = rejectUserCommandRepository.findAll();
        assertThat(rejectUserCommandList).hasSize(databaseSizeBeforeUpdate);
        RejectUserCommand testRejectUserCommand = rejectUserCommandList.get(rejectUserCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateRejectUserCommandWithPatch() throws Exception {
        // Initialize the database
        rejectUserCommandRepository.saveAndFlush(rejectUserCommand);

        int databaseSizeBeforeUpdate = rejectUserCommandRepository.findAll().size();

        // Update the rejectUserCommand using partial update
        RejectUserCommand partialUpdatedRejectUserCommand = new RejectUserCommand();
        partialUpdatedRejectUserCommand.setId(rejectUserCommand.getId());

        restRejectUserCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRejectUserCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRejectUserCommand))
            )
            .andExpect(status().isOk());

        // Validate the RejectUserCommand in the database
        List<RejectUserCommand> rejectUserCommandList = rejectUserCommandRepository.findAll();
        assertThat(rejectUserCommandList).hasSize(databaseSizeBeforeUpdate);
        RejectUserCommand testRejectUserCommand = rejectUserCommandList.get(rejectUserCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingRejectUserCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectUserCommandRepository.findAll().size();
        rejectUserCommand.setId(count.incrementAndGet());

        // Create the RejectUserCommand
        RejectUserCommandDTO rejectUserCommandDTO = rejectUserCommandMapper.toDto(rejectUserCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRejectUserCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rejectUserCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rejectUserCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectUserCommand in the database
        List<RejectUserCommand> rejectUserCommandList = rejectUserCommandRepository.findAll();
        assertThat(rejectUserCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRejectUserCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectUserCommandRepository.findAll().size();
        rejectUserCommand.setId(count.incrementAndGet());

        // Create the RejectUserCommand
        RejectUserCommandDTO rejectUserCommandDTO = rejectUserCommandMapper.toDto(rejectUserCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRejectUserCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rejectUserCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RejectUserCommand in the database
        List<RejectUserCommand> rejectUserCommandList = rejectUserCommandRepository.findAll();
        assertThat(rejectUserCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRejectUserCommand() throws Exception {
        int databaseSizeBeforeUpdate = rejectUserCommandRepository.findAll().size();
        rejectUserCommand.setId(count.incrementAndGet());

        // Create the RejectUserCommand
        RejectUserCommandDTO rejectUserCommandDTO = rejectUserCommandMapper.toDto(rejectUserCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRejectUserCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rejectUserCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RejectUserCommand in the database
        List<RejectUserCommand> rejectUserCommandList = rejectUserCommandRepository.findAll();
        assertThat(rejectUserCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRejectUserCommand() throws Exception {
        // Initialize the database
        rejectUserCommandRepository.saveAndFlush(rejectUserCommand);

        int databaseSizeBeforeDelete = rejectUserCommandRepository.findAll().size();

        // Delete the rejectUserCommand
        restRejectUserCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, rejectUserCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RejectUserCommand> rejectUserCommandList = rejectUserCommandRepository.findAll();
        assertThat(rejectUserCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
