package org.contextmapper.generated.answercontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.answercontext.IntegrationTest;
import org.contextmapper.generated.answercontext.domain.AnswerSubmitCommand;
import org.contextmapper.generated.answercontext.repository.AnswerSubmitCommandRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmitCommandDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerSubmitCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AnswerSubmitCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnswerSubmitCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/answer-submit-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnswerSubmitCommandRepository answerSubmitCommandRepository;

    @Autowired
    private AnswerSubmitCommandMapper answerSubmitCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnswerSubmitCommandMockMvc;

    private AnswerSubmitCommand answerSubmitCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSubmitCommand createEntity(EntityManager em) {
        AnswerSubmitCommand answerSubmitCommand = new AnswerSubmitCommand();
        return answerSubmitCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSubmitCommand createUpdatedEntity(EntityManager em) {
        AnswerSubmitCommand answerSubmitCommand = new AnswerSubmitCommand();
        return answerSubmitCommand;
    }

    @BeforeEach
    public void initTest() {
        answerSubmitCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeCreate = answerSubmitCommandRepository.findAll().size();
        // Create the AnswerSubmitCommand
        AnswerSubmitCommandDTO answerSubmitCommandDTO = answerSubmitCommandMapper.toDto(answerSubmitCommand);
        restAnswerSubmitCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeCreate + 1);
        AnswerSubmitCommand testAnswerSubmitCommand = answerSubmitCommandList.get(answerSubmitCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createAnswerSubmitCommandWithExistingId() throws Exception {
        // Create the AnswerSubmitCommand with an existing ID
        answerSubmitCommand.setId(1L);
        AnswerSubmitCommandDTO answerSubmitCommandDTO = answerSubmitCommandMapper.toDto(answerSubmitCommand);

        int databaseSizeBeforeCreate = answerSubmitCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnswerSubmitCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAnswerSubmitCommands() throws Exception {
        // Initialize the database
        answerSubmitCommandRepository.saveAndFlush(answerSubmitCommand);

        // Get all the answerSubmitCommandList
        restAnswerSubmitCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answerSubmitCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getAnswerSubmitCommand() throws Exception {
        // Initialize the database
        answerSubmitCommandRepository.saveAndFlush(answerSubmitCommand);

        // Get the answerSubmitCommand
        restAnswerSubmitCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, answerSubmitCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(answerSubmitCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAnswerSubmitCommand() throws Exception {
        // Get the answerSubmitCommand
        restAnswerSubmitCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAnswerSubmitCommand() throws Exception {
        // Initialize the database
        answerSubmitCommandRepository.saveAndFlush(answerSubmitCommand);

        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().size();

        // Update the answerSubmitCommand
        AnswerSubmitCommand updatedAnswerSubmitCommand = answerSubmitCommandRepository.findById(answerSubmitCommand.getId()).get();
        // Disconnect from session so that the updates on updatedAnswerSubmitCommand are not directly saved in db
        em.detach(updatedAnswerSubmitCommand);
        AnswerSubmitCommandDTO answerSubmitCommandDTO = answerSubmitCommandMapper.toDto(updatedAnswerSubmitCommand);

        restAnswerSubmitCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, answerSubmitCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
        AnswerSubmitCommand testAnswerSubmitCommand = answerSubmitCommandList.get(answerSubmitCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().size();
        answerSubmitCommand.setId(count.incrementAndGet());

        // Create the AnswerSubmitCommand
        AnswerSubmitCommandDTO answerSubmitCommandDTO = answerSubmitCommandMapper.toDto(answerSubmitCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnswerSubmitCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, answerSubmitCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().size();
        answerSubmitCommand.setId(count.incrementAndGet());

        // Create the AnswerSubmitCommand
        AnswerSubmitCommandDTO answerSubmitCommandDTO = answerSubmitCommandMapper.toDto(answerSubmitCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerSubmitCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().size();
        answerSubmitCommand.setId(count.incrementAndGet());

        // Create the AnswerSubmitCommand
        AnswerSubmitCommandDTO answerSubmitCommandDTO = answerSubmitCommandMapper.toDto(answerSubmitCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerSubmitCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAnswerSubmitCommandWithPatch() throws Exception {
        // Initialize the database
        answerSubmitCommandRepository.saveAndFlush(answerSubmitCommand);

        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().size();

        // Update the answerSubmitCommand using partial update
        AnswerSubmitCommand partialUpdatedAnswerSubmitCommand = new AnswerSubmitCommand();
        partialUpdatedAnswerSubmitCommand.setId(answerSubmitCommand.getId());

        restAnswerSubmitCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnswerSubmitCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnswerSubmitCommand))
            )
            .andExpect(status().isOk());

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
        AnswerSubmitCommand testAnswerSubmitCommand = answerSubmitCommandList.get(answerSubmitCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateAnswerSubmitCommandWithPatch() throws Exception {
        // Initialize the database
        answerSubmitCommandRepository.saveAndFlush(answerSubmitCommand);

        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().size();

        // Update the answerSubmitCommand using partial update
        AnswerSubmitCommand partialUpdatedAnswerSubmitCommand = new AnswerSubmitCommand();
        partialUpdatedAnswerSubmitCommand.setId(answerSubmitCommand.getId());

        restAnswerSubmitCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnswerSubmitCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnswerSubmitCommand))
            )
            .andExpect(status().isOk());

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
        AnswerSubmitCommand testAnswerSubmitCommand = answerSubmitCommandList.get(answerSubmitCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().size();
        answerSubmitCommand.setId(count.incrementAndGet());

        // Create the AnswerSubmitCommand
        AnswerSubmitCommandDTO answerSubmitCommandDTO = answerSubmitCommandMapper.toDto(answerSubmitCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnswerSubmitCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, answerSubmitCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().size();
        answerSubmitCommand.setId(count.incrementAndGet());

        // Create the AnswerSubmitCommand
        AnswerSubmitCommandDTO answerSubmitCommandDTO = answerSubmitCommandMapper.toDto(answerSubmitCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerSubmitCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAnswerSubmitCommand() throws Exception {
        int databaseSizeBeforeUpdate = answerSubmitCommandRepository.findAll().size();
        answerSubmitCommand.setId(count.incrementAndGet());

        // Create the AnswerSubmitCommand
        AnswerSubmitCommandDTO answerSubmitCommandDTO = answerSubmitCommandMapper.toDto(answerSubmitCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerSubmitCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerSubmitCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnswerSubmitCommand in the database
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAnswerSubmitCommand() throws Exception {
        // Initialize the database
        answerSubmitCommandRepository.saveAndFlush(answerSubmitCommand);

        int databaseSizeBeforeDelete = answerSubmitCommandRepository.findAll().size();

        // Delete the answerSubmitCommand
        restAnswerSubmitCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, answerSubmitCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AnswerSubmitCommand> answerSubmitCommandList = answerSubmitCommandRepository.findAll();
        assertThat(answerSubmitCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
