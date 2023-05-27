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
import org.contextmapper.generated.evaluationcontext.domain.CreateEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.DifficultyLevel;
import org.contextmapper.generated.evaluationcontext.repository.CreateEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.CreateEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.CreateEvaluationCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreateEvaluationCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreateEvaluationCommandResourceIT {

    private static final DifficultyLevel DEFAULT_DIFFICULTY_LEVEL = DifficultyLevel.EASY;
    private static final DifficultyLevel UPDATED_DIFFICULTY_LEVEL = DifficultyLevel.MEDIUM;

    private static final String ENTITY_API_URL = "/api/create-evaluation-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateEvaluationCommandRepository createEvaluationCommandRepository;

    @Autowired
    private CreateEvaluationCommandMapper createEvaluationCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreateEvaluationCommandMockMvc;

    private CreateEvaluationCommand createEvaluationCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateEvaluationCommand createEntity(EntityManager em) {
        CreateEvaluationCommand createEvaluationCommand = new CreateEvaluationCommand().difficultyLevel(DEFAULT_DIFFICULTY_LEVEL);
        return createEvaluationCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateEvaluationCommand createUpdatedEntity(EntityManager em) {
        CreateEvaluationCommand createEvaluationCommand = new CreateEvaluationCommand().difficultyLevel(UPDATED_DIFFICULTY_LEVEL);
        return createEvaluationCommand;
    }

    @BeforeEach
    public void initTest() {
        createEvaluationCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeCreate = createEvaluationCommandRepository.findAll().size();
        // Create the CreateEvaluationCommand
        CreateEvaluationCommandDTO createEvaluationCommandDTO = createEvaluationCommandMapper.toDto(createEvaluationCommand);
        restCreateEvaluationCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeCreate + 1);
        CreateEvaluationCommand testCreateEvaluationCommand = createEvaluationCommandList.get(createEvaluationCommandList.size() - 1);
        assertThat(testCreateEvaluationCommand.getDifficultyLevel()).isEqualTo(DEFAULT_DIFFICULTY_LEVEL);
    }

    @Test
    @Transactional
    void createCreateEvaluationCommandWithExistingId() throws Exception {
        // Create the CreateEvaluationCommand with an existing ID
        createEvaluationCommand.setId(1L);
        CreateEvaluationCommandDTO createEvaluationCommandDTO = createEvaluationCommandMapper.toDto(createEvaluationCommand);

        int databaseSizeBeforeCreate = createEvaluationCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreateEvaluationCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCreateEvaluationCommands() throws Exception {
        // Initialize the database
        createEvaluationCommandRepository.saveAndFlush(createEvaluationCommand);

        // Get all the createEvaluationCommandList
        restCreateEvaluationCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(createEvaluationCommand.getId().intValue())))
            .andExpect(jsonPath("$.[*].difficultyLevel").value(hasItem(DEFAULT_DIFFICULTY_LEVEL.toString())));
    }

    @Test
    @Transactional
    void getCreateEvaluationCommand() throws Exception {
        // Initialize the database
        createEvaluationCommandRepository.saveAndFlush(createEvaluationCommand);

        // Get the createEvaluationCommand
        restCreateEvaluationCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, createEvaluationCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(createEvaluationCommand.getId().intValue()))
            .andExpect(jsonPath("$.difficultyLevel").value(DEFAULT_DIFFICULTY_LEVEL.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCreateEvaluationCommand() throws Exception {
        // Get the createEvaluationCommand
        restCreateEvaluationCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCreateEvaluationCommand() throws Exception {
        // Initialize the database
        createEvaluationCommandRepository.saveAndFlush(createEvaluationCommand);

        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().size();

        // Update the createEvaluationCommand
        CreateEvaluationCommand updatedCreateEvaluationCommand = createEvaluationCommandRepository
            .findById(createEvaluationCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedCreateEvaluationCommand are not directly saved in db
        em.detach(updatedCreateEvaluationCommand);
        updatedCreateEvaluationCommand.difficultyLevel(UPDATED_DIFFICULTY_LEVEL);
        CreateEvaluationCommandDTO createEvaluationCommandDTO = createEvaluationCommandMapper.toDto(updatedCreateEvaluationCommand);

        restCreateEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createEvaluationCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateEvaluationCommand testCreateEvaluationCommand = createEvaluationCommandList.get(createEvaluationCommandList.size() - 1);
        assertThat(testCreateEvaluationCommand.getDifficultyLevel()).isEqualTo(UPDATED_DIFFICULTY_LEVEL);
    }

    @Test
    @Transactional
    void putNonExistingCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().size();
        createEvaluationCommand.setId(count.incrementAndGet());

        // Create the CreateEvaluationCommand
        CreateEvaluationCommandDTO createEvaluationCommandDTO = createEvaluationCommandMapper.toDto(createEvaluationCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createEvaluationCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().size();
        createEvaluationCommand.setId(count.incrementAndGet());

        // Create the CreateEvaluationCommand
        CreateEvaluationCommandDTO createEvaluationCommandDTO = createEvaluationCommandMapper.toDto(createEvaluationCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().size();
        createEvaluationCommand.setId(count.incrementAndGet());

        // Create the CreateEvaluationCommand
        CreateEvaluationCommandDTO createEvaluationCommandDTO = createEvaluationCommandMapper.toDto(createEvaluationCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCreateEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        createEvaluationCommandRepository.saveAndFlush(createEvaluationCommand);

        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().size();

        // Update the createEvaluationCommand using partial update
        CreateEvaluationCommand partialUpdatedCreateEvaluationCommand = new CreateEvaluationCommand();
        partialUpdatedCreateEvaluationCommand.setId(createEvaluationCommand.getId());

        restCreateEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateEvaluationCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateEvaluationCommand))
            )
            .andExpect(status().isOk());

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateEvaluationCommand testCreateEvaluationCommand = createEvaluationCommandList.get(createEvaluationCommandList.size() - 1);
        assertThat(testCreateEvaluationCommand.getDifficultyLevel()).isEqualTo(DEFAULT_DIFFICULTY_LEVEL);
    }

    @Test
    @Transactional
    void fullUpdateCreateEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        createEvaluationCommandRepository.saveAndFlush(createEvaluationCommand);

        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().size();

        // Update the createEvaluationCommand using partial update
        CreateEvaluationCommand partialUpdatedCreateEvaluationCommand = new CreateEvaluationCommand();
        partialUpdatedCreateEvaluationCommand.setId(createEvaluationCommand.getId());

        partialUpdatedCreateEvaluationCommand.difficultyLevel(UPDATED_DIFFICULTY_LEVEL);

        restCreateEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateEvaluationCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateEvaluationCommand))
            )
            .andExpect(status().isOk());

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateEvaluationCommand testCreateEvaluationCommand = createEvaluationCommandList.get(createEvaluationCommandList.size() - 1);
        assertThat(testCreateEvaluationCommand.getDifficultyLevel()).isEqualTo(UPDATED_DIFFICULTY_LEVEL);
    }

    @Test
    @Transactional
    void patchNonExistingCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().size();
        createEvaluationCommand.setId(count.incrementAndGet());

        // Create the CreateEvaluationCommand
        CreateEvaluationCommandDTO createEvaluationCommandDTO = createEvaluationCommandMapper.toDto(createEvaluationCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, createEvaluationCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().size();
        createEvaluationCommand.setId(count.incrementAndGet());

        // Create the CreateEvaluationCommand
        CreateEvaluationCommandDTO createEvaluationCommandDTO = createEvaluationCommandMapper.toDto(createEvaluationCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCreateEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = createEvaluationCommandRepository.findAll().size();
        createEvaluationCommand.setId(count.incrementAndGet());

        // Create the CreateEvaluationCommand
        CreateEvaluationCommandDTO createEvaluationCommandDTO = createEvaluationCommandMapper.toDto(createEvaluationCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createEvaluationCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateEvaluationCommand in the database
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCreateEvaluationCommand() throws Exception {
        // Initialize the database
        createEvaluationCommandRepository.saveAndFlush(createEvaluationCommand);

        int databaseSizeBeforeDelete = createEvaluationCommandRepository.findAll().size();

        // Delete the createEvaluationCommand
        restCreateEvaluationCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, createEvaluationCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CreateEvaluationCommand> createEvaluationCommandList = createEvaluationCommandRepository.findAll();
        assertThat(createEvaluationCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
