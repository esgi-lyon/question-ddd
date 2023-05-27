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
import org.contextmapper.generated.questioncontext.domain.CreateResourceCommand;
import org.contextmapper.generated.questioncontext.repository.CreateResourceCommandRepository;
import org.contextmapper.generated.questioncontext.service.dto.CreateResourceCommandDTO;
import org.contextmapper.generated.questioncontext.service.mapper.CreateResourceCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreateResourceCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreateResourceCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/create-resource-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateResourceCommandRepository createResourceCommandRepository;

    @Autowired
    private CreateResourceCommandMapper createResourceCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreateResourceCommandMockMvc;

    private CreateResourceCommand createResourceCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateResourceCommand createEntity(EntityManager em) {
        CreateResourceCommand createResourceCommand = new CreateResourceCommand();
        return createResourceCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateResourceCommand createUpdatedEntity(EntityManager em) {
        CreateResourceCommand createResourceCommand = new CreateResourceCommand();
        return createResourceCommand;
    }

    @BeforeEach
    public void initTest() {
        createResourceCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createCreateResourceCommand() throws Exception {
        int databaseSizeBeforeCreate = createResourceCommandRepository.findAll().size();
        // Create the CreateResourceCommand
        CreateResourceCommandDTO createResourceCommandDTO = createResourceCommandMapper.toDto(createResourceCommand);
        restCreateResourceCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createResourceCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeCreate + 1);
        CreateResourceCommand testCreateResourceCommand = createResourceCommandList.get(createResourceCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createCreateResourceCommandWithExistingId() throws Exception {
        // Create the CreateResourceCommand with an existing ID
        createResourceCommand.setId(1L);
        CreateResourceCommandDTO createResourceCommandDTO = createResourceCommandMapper.toDto(createResourceCommand);

        int databaseSizeBeforeCreate = createResourceCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreateResourceCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createResourceCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCreateResourceCommands() throws Exception {
        // Initialize the database
        createResourceCommandRepository.saveAndFlush(createResourceCommand);

        // Get all the createResourceCommandList
        restCreateResourceCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(createResourceCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getCreateResourceCommand() throws Exception {
        // Initialize the database
        createResourceCommandRepository.saveAndFlush(createResourceCommand);

        // Get the createResourceCommand
        restCreateResourceCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, createResourceCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(createResourceCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCreateResourceCommand() throws Exception {
        // Get the createResourceCommand
        restCreateResourceCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCreateResourceCommand() throws Exception {
        // Initialize the database
        createResourceCommandRepository.saveAndFlush(createResourceCommand);

        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().size();

        // Update the createResourceCommand
        CreateResourceCommand updatedCreateResourceCommand = createResourceCommandRepository.findById(createResourceCommand.getId()).get();
        // Disconnect from session so that the updates on updatedCreateResourceCommand are not directly saved in db
        em.detach(updatedCreateResourceCommand);
        CreateResourceCommandDTO createResourceCommandDTO = createResourceCommandMapper.toDto(updatedCreateResourceCommand);

        restCreateResourceCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createResourceCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createResourceCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateResourceCommand testCreateResourceCommand = createResourceCommandList.get(createResourceCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCreateResourceCommand() throws Exception {
        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().size();
        createResourceCommand.setId(count.incrementAndGet());

        // Create the CreateResourceCommand
        CreateResourceCommandDTO createResourceCommandDTO = createResourceCommandMapper.toDto(createResourceCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateResourceCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createResourceCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createResourceCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCreateResourceCommand() throws Exception {
        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().size();
        createResourceCommand.setId(count.incrementAndGet());

        // Create the CreateResourceCommand
        CreateResourceCommandDTO createResourceCommandDTO = createResourceCommandMapper.toDto(createResourceCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateResourceCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createResourceCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCreateResourceCommand() throws Exception {
        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().size();
        createResourceCommand.setId(count.incrementAndGet());

        // Create the CreateResourceCommand
        CreateResourceCommandDTO createResourceCommandDTO = createResourceCommandMapper.toDto(createResourceCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateResourceCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createResourceCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCreateResourceCommandWithPatch() throws Exception {
        // Initialize the database
        createResourceCommandRepository.saveAndFlush(createResourceCommand);

        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().size();

        // Update the createResourceCommand using partial update
        CreateResourceCommand partialUpdatedCreateResourceCommand = new CreateResourceCommand();
        partialUpdatedCreateResourceCommand.setId(createResourceCommand.getId());

        restCreateResourceCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateResourceCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateResourceCommand))
            )
            .andExpect(status().isOk());

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateResourceCommand testCreateResourceCommand = createResourceCommandList.get(createResourceCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCreateResourceCommandWithPatch() throws Exception {
        // Initialize the database
        createResourceCommandRepository.saveAndFlush(createResourceCommand);

        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().size();

        // Update the createResourceCommand using partial update
        CreateResourceCommand partialUpdatedCreateResourceCommand = new CreateResourceCommand();
        partialUpdatedCreateResourceCommand.setId(createResourceCommand.getId());

        restCreateResourceCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateResourceCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateResourceCommand))
            )
            .andExpect(status().isOk());

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateResourceCommand testCreateResourceCommand = createResourceCommandList.get(createResourceCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCreateResourceCommand() throws Exception {
        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().size();
        createResourceCommand.setId(count.incrementAndGet());

        // Create the CreateResourceCommand
        CreateResourceCommandDTO createResourceCommandDTO = createResourceCommandMapper.toDto(createResourceCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateResourceCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, createResourceCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createResourceCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCreateResourceCommand() throws Exception {
        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().size();
        createResourceCommand.setId(count.incrementAndGet());

        // Create the CreateResourceCommand
        CreateResourceCommandDTO createResourceCommandDTO = createResourceCommandMapper.toDto(createResourceCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateResourceCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createResourceCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCreateResourceCommand() throws Exception {
        int databaseSizeBeforeUpdate = createResourceCommandRepository.findAll().size();
        createResourceCommand.setId(count.incrementAndGet());

        // Create the CreateResourceCommand
        CreateResourceCommandDTO createResourceCommandDTO = createResourceCommandMapper.toDto(createResourceCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateResourceCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createResourceCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateResourceCommand in the database
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCreateResourceCommand() throws Exception {
        // Initialize the database
        createResourceCommandRepository.saveAndFlush(createResourceCommand);

        int databaseSizeBeforeDelete = createResourceCommandRepository.findAll().size();

        // Delete the createResourceCommand
        restCreateResourceCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, createResourceCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CreateResourceCommand> createResourceCommandList = createResourceCommandRepository.findAll();
        assertThat(createResourceCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
