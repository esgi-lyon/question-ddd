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
import org.contextmapper.generated.sendquestioncontext.domain.AddPreferencesCommand;
import org.contextmapper.generated.sendquestioncontext.repository.AddPreferencesCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.AddPreferencesCommandDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.AddPreferencesCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AddPreferencesCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AddPreferencesCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/add-preferences-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AddPreferencesCommandRepository addPreferencesCommandRepository;

    @Autowired
    private AddPreferencesCommandMapper addPreferencesCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAddPreferencesCommandMockMvc;

    private AddPreferencesCommand addPreferencesCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddPreferencesCommand createEntity(EntityManager em) {
        AddPreferencesCommand addPreferencesCommand = new AddPreferencesCommand();
        return addPreferencesCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddPreferencesCommand createUpdatedEntity(EntityManager em) {
        AddPreferencesCommand addPreferencesCommand = new AddPreferencesCommand();
        return addPreferencesCommand;
    }

    @BeforeEach
    public void initTest() {
        addPreferencesCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createAddPreferencesCommand() throws Exception {
        int databaseSizeBeforeCreate = addPreferencesCommandRepository.findAll().size();
        // Create the AddPreferencesCommand
        AddPreferencesCommandDTO addPreferencesCommandDTO = addPreferencesCommandMapper.toDto(addPreferencesCommand);
        restAddPreferencesCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addPreferencesCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AddPreferencesCommand in the database
        List<AddPreferencesCommand> addPreferencesCommandList = addPreferencesCommandRepository.findAll();
        assertThat(addPreferencesCommandList).hasSize(databaseSizeBeforeCreate + 1);
        AddPreferencesCommand testAddPreferencesCommand = addPreferencesCommandList.get(addPreferencesCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createAddPreferencesCommandWithExistingId() throws Exception {
        // Create the AddPreferencesCommand with an existing ID
        addPreferencesCommand.setId(1L);
        AddPreferencesCommandDTO addPreferencesCommandDTO = addPreferencesCommandMapper.toDto(addPreferencesCommand);

        int databaseSizeBeforeCreate = addPreferencesCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddPreferencesCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addPreferencesCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddPreferencesCommand in the database
        List<AddPreferencesCommand> addPreferencesCommandList = addPreferencesCommandRepository.findAll();
        assertThat(addPreferencesCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAddPreferencesCommands() throws Exception {
        // Initialize the database
        addPreferencesCommandRepository.saveAndFlush(addPreferencesCommand);

        // Get all the addPreferencesCommandList
        restAddPreferencesCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addPreferencesCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getAddPreferencesCommand() throws Exception {
        // Initialize the database
        addPreferencesCommandRepository.saveAndFlush(addPreferencesCommand);

        // Get the addPreferencesCommand
        restAddPreferencesCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, addPreferencesCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(addPreferencesCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAddPreferencesCommand() throws Exception {
        // Get the addPreferencesCommand
        restAddPreferencesCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAddPreferencesCommand() throws Exception {
        // Initialize the database
        addPreferencesCommandRepository.saveAndFlush(addPreferencesCommand);

        int databaseSizeBeforeUpdate = addPreferencesCommandRepository.findAll().size();

        // Update the addPreferencesCommand
        AddPreferencesCommand updatedAddPreferencesCommand = addPreferencesCommandRepository.findById(addPreferencesCommand.getId()).get();
        // Disconnect from session so that the updates on updatedAddPreferencesCommand are not directly saved in db
        em.detach(updatedAddPreferencesCommand);
        AddPreferencesCommandDTO addPreferencesCommandDTO = addPreferencesCommandMapper.toDto(updatedAddPreferencesCommand);

        restAddPreferencesCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, addPreferencesCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addPreferencesCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the AddPreferencesCommand in the database
        List<AddPreferencesCommand> addPreferencesCommandList = addPreferencesCommandRepository.findAll();
        assertThat(addPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
        AddPreferencesCommand testAddPreferencesCommand = addPreferencesCommandList.get(addPreferencesCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingAddPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = addPreferencesCommandRepository.findAll().size();
        addPreferencesCommand.setId(count.incrementAndGet());

        // Create the AddPreferencesCommand
        AddPreferencesCommandDTO addPreferencesCommandDTO = addPreferencesCommandMapper.toDto(addPreferencesCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddPreferencesCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, addPreferencesCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addPreferencesCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddPreferencesCommand in the database
        List<AddPreferencesCommand> addPreferencesCommandList = addPreferencesCommandRepository.findAll();
        assertThat(addPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAddPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = addPreferencesCommandRepository.findAll().size();
        addPreferencesCommand.setId(count.incrementAndGet());

        // Create the AddPreferencesCommand
        AddPreferencesCommandDTO addPreferencesCommandDTO = addPreferencesCommandMapper.toDto(addPreferencesCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAddPreferencesCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addPreferencesCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddPreferencesCommand in the database
        List<AddPreferencesCommand> addPreferencesCommandList = addPreferencesCommandRepository.findAll();
        assertThat(addPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAddPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = addPreferencesCommandRepository.findAll().size();
        addPreferencesCommand.setId(count.incrementAndGet());

        // Create the AddPreferencesCommand
        AddPreferencesCommandDTO addPreferencesCommandDTO = addPreferencesCommandMapper.toDto(addPreferencesCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAddPreferencesCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addPreferencesCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AddPreferencesCommand in the database
        List<AddPreferencesCommand> addPreferencesCommandList = addPreferencesCommandRepository.findAll();
        assertThat(addPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAddPreferencesCommandWithPatch() throws Exception {
        // Initialize the database
        addPreferencesCommandRepository.saveAndFlush(addPreferencesCommand);

        int databaseSizeBeforeUpdate = addPreferencesCommandRepository.findAll().size();

        // Update the addPreferencesCommand using partial update
        AddPreferencesCommand partialUpdatedAddPreferencesCommand = new AddPreferencesCommand();
        partialUpdatedAddPreferencesCommand.setId(addPreferencesCommand.getId());

        restAddPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAddPreferencesCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAddPreferencesCommand))
            )
            .andExpect(status().isOk());

        // Validate the AddPreferencesCommand in the database
        List<AddPreferencesCommand> addPreferencesCommandList = addPreferencesCommandRepository.findAll();
        assertThat(addPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
        AddPreferencesCommand testAddPreferencesCommand = addPreferencesCommandList.get(addPreferencesCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateAddPreferencesCommandWithPatch() throws Exception {
        // Initialize the database
        addPreferencesCommandRepository.saveAndFlush(addPreferencesCommand);

        int databaseSizeBeforeUpdate = addPreferencesCommandRepository.findAll().size();

        // Update the addPreferencesCommand using partial update
        AddPreferencesCommand partialUpdatedAddPreferencesCommand = new AddPreferencesCommand();
        partialUpdatedAddPreferencesCommand.setId(addPreferencesCommand.getId());

        restAddPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAddPreferencesCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAddPreferencesCommand))
            )
            .andExpect(status().isOk());

        // Validate the AddPreferencesCommand in the database
        List<AddPreferencesCommand> addPreferencesCommandList = addPreferencesCommandRepository.findAll();
        assertThat(addPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
        AddPreferencesCommand testAddPreferencesCommand = addPreferencesCommandList.get(addPreferencesCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingAddPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = addPreferencesCommandRepository.findAll().size();
        addPreferencesCommand.setId(count.incrementAndGet());

        // Create the AddPreferencesCommand
        AddPreferencesCommandDTO addPreferencesCommandDTO = addPreferencesCommandMapper.toDto(addPreferencesCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, addPreferencesCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(addPreferencesCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddPreferencesCommand in the database
        List<AddPreferencesCommand> addPreferencesCommandList = addPreferencesCommandRepository.findAll();
        assertThat(addPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAddPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = addPreferencesCommandRepository.findAll().size();
        addPreferencesCommand.setId(count.incrementAndGet());

        // Create the AddPreferencesCommand
        AddPreferencesCommandDTO addPreferencesCommandDTO = addPreferencesCommandMapper.toDto(addPreferencesCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAddPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(addPreferencesCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddPreferencesCommand in the database
        List<AddPreferencesCommand> addPreferencesCommandList = addPreferencesCommandRepository.findAll();
        assertThat(addPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAddPreferencesCommand() throws Exception {
        int databaseSizeBeforeUpdate = addPreferencesCommandRepository.findAll().size();
        addPreferencesCommand.setId(count.incrementAndGet());

        // Create the AddPreferencesCommand
        AddPreferencesCommandDTO addPreferencesCommandDTO = addPreferencesCommandMapper.toDto(addPreferencesCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAddPreferencesCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(addPreferencesCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AddPreferencesCommand in the database
        List<AddPreferencesCommand> addPreferencesCommandList = addPreferencesCommandRepository.findAll();
        assertThat(addPreferencesCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAddPreferencesCommand() throws Exception {
        // Initialize the database
        addPreferencesCommandRepository.saveAndFlush(addPreferencesCommand);

        int databaseSizeBeforeDelete = addPreferencesCommandRepository.findAll().size();

        // Delete the addPreferencesCommand
        restAddPreferencesCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, addPreferencesCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AddPreferencesCommand> addPreferencesCommandList = addPreferencesCommandRepository.findAll();
        assertThat(addPreferencesCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
