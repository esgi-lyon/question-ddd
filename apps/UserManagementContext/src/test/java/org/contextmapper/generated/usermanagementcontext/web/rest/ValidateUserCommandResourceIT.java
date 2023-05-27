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
import org.contextmapper.generated.usermanagementcontext.domain.ValidateUserCommand;
import org.contextmapper.generated.usermanagementcontext.repository.ValidateUserCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.ValidateUserCommandDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.ValidateUserCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ValidateUserCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ValidateUserCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/validate-user-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ValidateUserCommandRepository validateUserCommandRepository;

    @Autowired
    private ValidateUserCommandMapper validateUserCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restValidateUserCommandMockMvc;

    private ValidateUserCommand validateUserCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ValidateUserCommand createEntity(EntityManager em) {
        ValidateUserCommand validateUserCommand = new ValidateUserCommand();
        return validateUserCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ValidateUserCommand createUpdatedEntity(EntityManager em) {
        ValidateUserCommand validateUserCommand = new ValidateUserCommand();
        return validateUserCommand;
    }

    @BeforeEach
    public void initTest() {
        validateUserCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createValidateUserCommand() throws Exception {
        int databaseSizeBeforeCreate = validateUserCommandRepository.findAll().size();
        // Create the ValidateUserCommand
        ValidateUserCommandDTO validateUserCommandDTO = validateUserCommandMapper.toDto(validateUserCommand);
        restValidateUserCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateUserCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ValidateUserCommand in the database
        List<ValidateUserCommand> validateUserCommandList = validateUserCommandRepository.findAll();
        assertThat(validateUserCommandList).hasSize(databaseSizeBeforeCreate + 1);
        ValidateUserCommand testValidateUserCommand = validateUserCommandList.get(validateUserCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createValidateUserCommandWithExistingId() throws Exception {
        // Create the ValidateUserCommand with an existing ID
        validateUserCommand.setId(1L);
        ValidateUserCommandDTO validateUserCommandDTO = validateUserCommandMapper.toDto(validateUserCommand);

        int databaseSizeBeforeCreate = validateUserCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restValidateUserCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateUserCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateUserCommand in the database
        List<ValidateUserCommand> validateUserCommandList = validateUserCommandRepository.findAll();
        assertThat(validateUserCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllValidateUserCommands() throws Exception {
        // Initialize the database
        validateUserCommandRepository.saveAndFlush(validateUserCommand);

        // Get all the validateUserCommandList
        restValidateUserCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(validateUserCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getValidateUserCommand() throws Exception {
        // Initialize the database
        validateUserCommandRepository.saveAndFlush(validateUserCommand);

        // Get the validateUserCommand
        restValidateUserCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, validateUserCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(validateUserCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingValidateUserCommand() throws Exception {
        // Get the validateUserCommand
        restValidateUserCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingValidateUserCommand() throws Exception {
        // Initialize the database
        validateUserCommandRepository.saveAndFlush(validateUserCommand);

        int databaseSizeBeforeUpdate = validateUserCommandRepository.findAll().size();

        // Update the validateUserCommand
        ValidateUserCommand updatedValidateUserCommand = validateUserCommandRepository.findById(validateUserCommand.getId()).get();
        // Disconnect from session so that the updates on updatedValidateUserCommand are not directly saved in db
        em.detach(updatedValidateUserCommand);
        ValidateUserCommandDTO validateUserCommandDTO = validateUserCommandMapper.toDto(updatedValidateUserCommand);

        restValidateUserCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, validateUserCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateUserCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the ValidateUserCommand in the database
        List<ValidateUserCommand> validateUserCommandList = validateUserCommandRepository.findAll();
        assertThat(validateUserCommandList).hasSize(databaseSizeBeforeUpdate);
        ValidateUserCommand testValidateUserCommand = validateUserCommandList.get(validateUserCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingValidateUserCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateUserCommandRepository.findAll().size();
        validateUserCommand.setId(count.incrementAndGet());

        // Create the ValidateUserCommand
        ValidateUserCommandDTO validateUserCommandDTO = validateUserCommandMapper.toDto(validateUserCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restValidateUserCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, validateUserCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateUserCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateUserCommand in the database
        List<ValidateUserCommand> validateUserCommandList = validateUserCommandRepository.findAll();
        assertThat(validateUserCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchValidateUserCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateUserCommandRepository.findAll().size();
        validateUserCommand.setId(count.incrementAndGet());

        // Create the ValidateUserCommand
        ValidateUserCommandDTO validateUserCommandDTO = validateUserCommandMapper.toDto(validateUserCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restValidateUserCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateUserCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateUserCommand in the database
        List<ValidateUserCommand> validateUserCommandList = validateUserCommandRepository.findAll();
        assertThat(validateUserCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamValidateUserCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateUserCommandRepository.findAll().size();
        validateUserCommand.setId(count.incrementAndGet());

        // Create the ValidateUserCommand
        ValidateUserCommandDTO validateUserCommandDTO = validateUserCommandMapper.toDto(validateUserCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restValidateUserCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateUserCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ValidateUserCommand in the database
        List<ValidateUserCommand> validateUserCommandList = validateUserCommandRepository.findAll();
        assertThat(validateUserCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateValidateUserCommandWithPatch() throws Exception {
        // Initialize the database
        validateUserCommandRepository.saveAndFlush(validateUserCommand);

        int databaseSizeBeforeUpdate = validateUserCommandRepository.findAll().size();

        // Update the validateUserCommand using partial update
        ValidateUserCommand partialUpdatedValidateUserCommand = new ValidateUserCommand();
        partialUpdatedValidateUserCommand.setId(validateUserCommand.getId());

        restValidateUserCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedValidateUserCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedValidateUserCommand))
            )
            .andExpect(status().isOk());

        // Validate the ValidateUserCommand in the database
        List<ValidateUserCommand> validateUserCommandList = validateUserCommandRepository.findAll();
        assertThat(validateUserCommandList).hasSize(databaseSizeBeforeUpdate);
        ValidateUserCommand testValidateUserCommand = validateUserCommandList.get(validateUserCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateValidateUserCommandWithPatch() throws Exception {
        // Initialize the database
        validateUserCommandRepository.saveAndFlush(validateUserCommand);

        int databaseSizeBeforeUpdate = validateUserCommandRepository.findAll().size();

        // Update the validateUserCommand using partial update
        ValidateUserCommand partialUpdatedValidateUserCommand = new ValidateUserCommand();
        partialUpdatedValidateUserCommand.setId(validateUserCommand.getId());

        restValidateUserCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedValidateUserCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedValidateUserCommand))
            )
            .andExpect(status().isOk());

        // Validate the ValidateUserCommand in the database
        List<ValidateUserCommand> validateUserCommandList = validateUserCommandRepository.findAll();
        assertThat(validateUserCommandList).hasSize(databaseSizeBeforeUpdate);
        ValidateUserCommand testValidateUserCommand = validateUserCommandList.get(validateUserCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingValidateUserCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateUserCommandRepository.findAll().size();
        validateUserCommand.setId(count.incrementAndGet());

        // Create the ValidateUserCommand
        ValidateUserCommandDTO validateUserCommandDTO = validateUserCommandMapper.toDto(validateUserCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restValidateUserCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, validateUserCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(validateUserCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateUserCommand in the database
        List<ValidateUserCommand> validateUserCommandList = validateUserCommandRepository.findAll();
        assertThat(validateUserCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchValidateUserCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateUserCommandRepository.findAll().size();
        validateUserCommand.setId(count.incrementAndGet());

        // Create the ValidateUserCommand
        ValidateUserCommandDTO validateUserCommandDTO = validateUserCommandMapper.toDto(validateUserCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restValidateUserCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(validateUserCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateUserCommand in the database
        List<ValidateUserCommand> validateUserCommandList = validateUserCommandRepository.findAll();
        assertThat(validateUserCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamValidateUserCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateUserCommandRepository.findAll().size();
        validateUserCommand.setId(count.incrementAndGet());

        // Create the ValidateUserCommand
        ValidateUserCommandDTO validateUserCommandDTO = validateUserCommandMapper.toDto(validateUserCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restValidateUserCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(validateUserCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ValidateUserCommand in the database
        List<ValidateUserCommand> validateUserCommandList = validateUserCommandRepository.findAll();
        assertThat(validateUserCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteValidateUserCommand() throws Exception {
        // Initialize the database
        validateUserCommandRepository.saveAndFlush(validateUserCommand);

        int databaseSizeBeforeDelete = validateUserCommandRepository.findAll().size();

        // Delete the validateUserCommand
        restValidateUserCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, validateUserCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ValidateUserCommand> validateUserCommandList = validateUserCommandRepository.findAll();
        assertThat(validateUserCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
