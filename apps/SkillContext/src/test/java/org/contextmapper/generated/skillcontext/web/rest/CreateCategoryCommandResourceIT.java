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
import org.contextmapper.generated.skillcontext.domain.CreateCategoryCommand;
import org.contextmapper.generated.skillcontext.repository.CreateCategoryCommandRepository;
import org.contextmapper.generated.skillcontext.service.dto.CreateCategoryCommandDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CreateCategoryCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreateCategoryCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreateCategoryCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/create-category-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateCategoryCommandRepository createCategoryCommandRepository;

    @Autowired
    private CreateCategoryCommandMapper createCategoryCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreateCategoryCommandMockMvc;

    private CreateCategoryCommand createCategoryCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateCategoryCommand createEntity(EntityManager em) {
        CreateCategoryCommand createCategoryCommand = new CreateCategoryCommand();
        return createCategoryCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateCategoryCommand createUpdatedEntity(EntityManager em) {
        CreateCategoryCommand createCategoryCommand = new CreateCategoryCommand();
        return createCategoryCommand;
    }

    @BeforeEach
    public void initTest() {
        createCategoryCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeCreate = createCategoryCommandRepository.findAll().size();
        // Create the CreateCategoryCommand
        CreateCategoryCommandDTO createCategoryCommandDTO = createCategoryCommandMapper.toDto(createCategoryCommand);
        restCreateCategoryCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeCreate + 1);
        CreateCategoryCommand testCreateCategoryCommand = createCategoryCommandList.get(createCategoryCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createCreateCategoryCommandWithExistingId() throws Exception {
        // Create the CreateCategoryCommand with an existing ID
        createCategoryCommand.setId(1L);
        CreateCategoryCommandDTO createCategoryCommandDTO = createCategoryCommandMapper.toDto(createCategoryCommand);

        int databaseSizeBeforeCreate = createCategoryCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreateCategoryCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCreateCategoryCommands() throws Exception {
        // Initialize the database
        createCategoryCommandRepository.saveAndFlush(createCategoryCommand);

        // Get all the createCategoryCommandList
        restCreateCategoryCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(createCategoryCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getCreateCategoryCommand() throws Exception {
        // Initialize the database
        createCategoryCommandRepository.saveAndFlush(createCategoryCommand);

        // Get the createCategoryCommand
        restCreateCategoryCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, createCategoryCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(createCategoryCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCreateCategoryCommand() throws Exception {
        // Get the createCategoryCommand
        restCreateCategoryCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCreateCategoryCommand() throws Exception {
        // Initialize the database
        createCategoryCommandRepository.saveAndFlush(createCategoryCommand);

        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().size();

        // Update the createCategoryCommand
        CreateCategoryCommand updatedCreateCategoryCommand = createCategoryCommandRepository.findById(createCategoryCommand.getId()).get();
        // Disconnect from session so that the updates on updatedCreateCategoryCommand are not directly saved in db
        em.detach(updatedCreateCategoryCommand);
        CreateCategoryCommandDTO createCategoryCommandDTO = createCategoryCommandMapper.toDto(updatedCreateCategoryCommand);

        restCreateCategoryCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createCategoryCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateCategoryCommand testCreateCategoryCommand = createCategoryCommandList.get(createCategoryCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().size();
        createCategoryCommand.setId(count.incrementAndGet());

        // Create the CreateCategoryCommand
        CreateCategoryCommandDTO createCategoryCommandDTO = createCategoryCommandMapper.toDto(createCategoryCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateCategoryCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createCategoryCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().size();
        createCategoryCommand.setId(count.incrementAndGet());

        // Create the CreateCategoryCommand
        CreateCategoryCommandDTO createCategoryCommandDTO = createCategoryCommandMapper.toDto(createCategoryCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateCategoryCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().size();
        createCategoryCommand.setId(count.incrementAndGet());

        // Create the CreateCategoryCommand
        CreateCategoryCommandDTO createCategoryCommandDTO = createCategoryCommandMapper.toDto(createCategoryCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateCategoryCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCreateCategoryCommandWithPatch() throws Exception {
        // Initialize the database
        createCategoryCommandRepository.saveAndFlush(createCategoryCommand);

        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().size();

        // Update the createCategoryCommand using partial update
        CreateCategoryCommand partialUpdatedCreateCategoryCommand = new CreateCategoryCommand();
        partialUpdatedCreateCategoryCommand.setId(createCategoryCommand.getId());

        restCreateCategoryCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateCategoryCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateCategoryCommand))
            )
            .andExpect(status().isOk());

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateCategoryCommand testCreateCategoryCommand = createCategoryCommandList.get(createCategoryCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCreateCategoryCommandWithPatch() throws Exception {
        // Initialize the database
        createCategoryCommandRepository.saveAndFlush(createCategoryCommand);

        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().size();

        // Update the createCategoryCommand using partial update
        CreateCategoryCommand partialUpdatedCreateCategoryCommand = new CreateCategoryCommand();
        partialUpdatedCreateCategoryCommand.setId(createCategoryCommand.getId());

        restCreateCategoryCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateCategoryCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateCategoryCommand))
            )
            .andExpect(status().isOk());

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateCategoryCommand testCreateCategoryCommand = createCategoryCommandList.get(createCategoryCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().size();
        createCategoryCommand.setId(count.incrementAndGet());

        // Create the CreateCategoryCommand
        CreateCategoryCommandDTO createCategoryCommandDTO = createCategoryCommandMapper.toDto(createCategoryCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateCategoryCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, createCategoryCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().size();
        createCategoryCommand.setId(count.incrementAndGet());

        // Create the CreateCategoryCommand
        CreateCategoryCommandDTO createCategoryCommandDTO = createCategoryCommandMapper.toDto(createCategoryCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateCategoryCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCreateCategoryCommand() throws Exception {
        int databaseSizeBeforeUpdate = createCategoryCommandRepository.findAll().size();
        createCategoryCommand.setId(count.incrementAndGet());

        // Create the CreateCategoryCommand
        CreateCategoryCommandDTO createCategoryCommandDTO = createCategoryCommandMapper.toDto(createCategoryCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateCategoryCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createCategoryCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateCategoryCommand in the database
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCreateCategoryCommand() throws Exception {
        // Initialize the database
        createCategoryCommandRepository.saveAndFlush(createCategoryCommand);

        int databaseSizeBeforeDelete = createCategoryCommandRepository.findAll().size();

        // Delete the createCategoryCommand
        restCreateCategoryCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, createCategoryCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CreateCategoryCommand> createCategoryCommandList = createCategoryCommandRepository.findAll();
        assertThat(createCategoryCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
