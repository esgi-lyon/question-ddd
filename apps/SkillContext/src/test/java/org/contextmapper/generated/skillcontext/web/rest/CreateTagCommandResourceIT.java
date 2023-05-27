package org.contextmapper.generated.skillcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.skillcontext.IntegrationTest;
import org.contextmapper.generated.skillcontext.domain.CreateTagCommand;
import org.contextmapper.generated.skillcontext.repository.CreateTagCommandRepository;
import org.contextmapper.generated.skillcontext.service.dto.CreateTagCommandDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CreateTagCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreateTagCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreateTagCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/create-tag-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateTagCommandRepository createTagCommandRepository;

    @Autowired
    private CreateTagCommandMapper createTagCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreateTagCommandMockMvc;

    private CreateTagCommand createTagCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateTagCommand createEntity(EntityManager em) {
        CreateTagCommand createTagCommand = new CreateTagCommand();
        return createTagCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateTagCommand createUpdatedEntity(EntityManager em) {
        CreateTagCommand createTagCommand = new CreateTagCommand();
        return createTagCommand;
    }

    @BeforeEach
    public void initTest() {
        createTagCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createCreateTagCommand() throws Exception {
        int databaseSizeBeforeCreate = createTagCommandRepository.findAll().size();
        // Create the CreateTagCommand
        CreateTagCommandDTO createTagCommandDTO = createTagCommandMapper.toDto(createTagCommand);
        restCreateTagCommandMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createTagCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeCreate + 1);
        CreateTagCommand testCreateTagCommand = createTagCommandList.get(createTagCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createCreateTagCommandWithExistingId() throws Exception {
        // Create the CreateTagCommand with an existing ID
        createTagCommand.setId(1L);
        CreateTagCommandDTO createTagCommandDTO = createTagCommandMapper.toDto(createTagCommand);

        int databaseSizeBeforeCreate = createTagCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreateTagCommandMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createTagCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCreateTagCommands() throws Exception {
        // Initialize the database
        createTagCommandRepository.saveAndFlush(createTagCommand);

        // Get all the createTagCommandList
        restCreateTagCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(createTagCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getCreateTagCommand() throws Exception {
        // Initialize the database
        createTagCommandRepository.saveAndFlush(createTagCommand);

        // Get the createTagCommand
        restCreateTagCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, createTagCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(createTagCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCreateTagCommand() throws Exception {
        // Get the createTagCommand
        restCreateTagCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCreateTagCommand() throws Exception {
        // Initialize the database
        createTagCommandRepository.saveAndFlush(createTagCommand);

        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().size();

        // Update the createTagCommand
        CreateTagCommand updatedCreateTagCommand = createTagCommandRepository.findById(createTagCommand.getId()).get();
        // Disconnect from session so that the updates on updatedCreateTagCommand are not directly saved in db
        em.detach(updatedCreateTagCommand);
        CreateTagCommandDTO createTagCommandDTO = createTagCommandMapper.toDto(updatedCreateTagCommand);

        restCreateTagCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createTagCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createTagCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateTagCommand testCreateTagCommand = createTagCommandList.get(createTagCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCreateTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().size();
        createTagCommand.setId(count.incrementAndGet());

        // Create the CreateTagCommand
        CreateTagCommandDTO createTagCommandDTO = createTagCommandMapper.toDto(createTagCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateTagCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createTagCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createTagCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCreateTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().size();
        createTagCommand.setId(count.incrementAndGet());

        // Create the CreateTagCommand
        CreateTagCommandDTO createTagCommandDTO = createTagCommandMapper.toDto(createTagCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateTagCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createTagCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCreateTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().size();
        createTagCommand.setId(count.incrementAndGet());

        // Create the CreateTagCommand
        CreateTagCommandDTO createTagCommandDTO = createTagCommandMapper.toDto(createTagCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateTagCommandMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createTagCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCreateTagCommandWithPatch() throws Exception {
        // Initialize the database
        createTagCommandRepository.saveAndFlush(createTagCommand);

        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().size();

        // Update the createTagCommand using partial update
        CreateTagCommand partialUpdatedCreateTagCommand = new CreateTagCommand();
        partialUpdatedCreateTagCommand.setId(createTagCommand.getId());

        restCreateTagCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateTagCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateTagCommand))
            )
            .andExpect(status().isOk());

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateTagCommand testCreateTagCommand = createTagCommandList.get(createTagCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCreateTagCommandWithPatch() throws Exception {
        // Initialize the database
        createTagCommandRepository.saveAndFlush(createTagCommand);

        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().size();

        // Update the createTagCommand using partial update
        CreateTagCommand partialUpdatedCreateTagCommand = new CreateTagCommand();
        partialUpdatedCreateTagCommand.setId(createTagCommand.getId());

        restCreateTagCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateTagCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateTagCommand))
            )
            .andExpect(status().isOk());

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateTagCommand testCreateTagCommand = createTagCommandList.get(createTagCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCreateTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().size();
        createTagCommand.setId(count.incrementAndGet());

        // Create the CreateTagCommand
        CreateTagCommandDTO createTagCommandDTO = createTagCommandMapper.toDto(createTagCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateTagCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, createTagCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createTagCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCreateTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().size();
        createTagCommand.setId(count.incrementAndGet());

        // Create the CreateTagCommand
        CreateTagCommandDTO createTagCommandDTO = createTagCommandMapper.toDto(createTagCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateTagCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createTagCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCreateTagCommand() throws Exception {
        int databaseSizeBeforeUpdate = createTagCommandRepository.findAll().size();
        createTagCommand.setId(count.incrementAndGet());

        // Create the CreateTagCommand
        CreateTagCommandDTO createTagCommandDTO = createTagCommandMapper.toDto(createTagCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateTagCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createTagCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateTagCommand in the database
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCreateTagCommand() throws Exception {
        // Initialize the database
        createTagCommandRepository.saveAndFlush(createTagCommand);

        int databaseSizeBeforeDelete = createTagCommandRepository.findAll().size();

        // Delete the createTagCommand
        restCreateTagCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, createTagCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CreateTagCommand> createTagCommandList = createTagCommandRepository.findAll();
        assertThat(createTagCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
