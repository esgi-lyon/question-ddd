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
import org.contextmapper.generated.questioncontext.domain.ValidateResourceTagLinkageCommand;
import org.contextmapper.generated.questioncontext.repository.ValidateResourceTagLinkageCommandRepository;
import org.contextmapper.generated.questioncontext.service.dto.ValidateResourceTagLinkageCommandDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ValidateResourceTagLinkageCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ValidateResourceTagLinkageCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ValidateResourceTagLinkageCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/validate-resource-tag-linkage-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ValidateResourceTagLinkageCommandRepository validateResourceTagLinkageCommandRepository;

    @Autowired
    private ValidateResourceTagLinkageCommandMapper validateResourceTagLinkageCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restValidateResourceTagLinkageCommandMockMvc;

    private ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ValidateResourceTagLinkageCommand createEntity(EntityManager em) {
        ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand = new ValidateResourceTagLinkageCommand();
        return validateResourceTagLinkageCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ValidateResourceTagLinkageCommand createUpdatedEntity(EntityManager em) {
        ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand = new ValidateResourceTagLinkageCommand();
        return validateResourceTagLinkageCommand;
    }

    @BeforeEach
    public void initTest() {
        validateResourceTagLinkageCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeCreate = validateResourceTagLinkageCommandRepository.findAll().size();
        // Create the ValidateResourceTagLinkageCommand
        ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO = validateResourceTagLinkageCommandMapper.toDto(
            validateResourceTagLinkageCommand
        );
        restValidateResourceTagLinkageCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository.findAll();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeCreate + 1);
        ValidateResourceTagLinkageCommand testValidateResourceTagLinkageCommand = validateResourceTagLinkageCommandList.get(
            validateResourceTagLinkageCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void createValidateResourceTagLinkageCommandWithExistingId() throws Exception {
        // Create the ValidateResourceTagLinkageCommand with an existing ID
        validateResourceTagLinkageCommand.setId(1L);
        ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO = validateResourceTagLinkageCommandMapper.toDto(
            validateResourceTagLinkageCommand
        );

        int databaseSizeBeforeCreate = validateResourceTagLinkageCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restValidateResourceTagLinkageCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository.findAll();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllValidateResourceTagLinkageCommands() throws Exception {
        // Initialize the database
        validateResourceTagLinkageCommandRepository.saveAndFlush(validateResourceTagLinkageCommand);

        // Get all the validateResourceTagLinkageCommandList
        restValidateResourceTagLinkageCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(validateResourceTagLinkageCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getValidateResourceTagLinkageCommand() throws Exception {
        // Initialize the database
        validateResourceTagLinkageCommandRepository.saveAndFlush(validateResourceTagLinkageCommand);

        // Get the validateResourceTagLinkageCommand
        restValidateResourceTagLinkageCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, validateResourceTagLinkageCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(validateResourceTagLinkageCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingValidateResourceTagLinkageCommand() throws Exception {
        // Get the validateResourceTagLinkageCommand
        restValidateResourceTagLinkageCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingValidateResourceTagLinkageCommand() throws Exception {
        // Initialize the database
        validateResourceTagLinkageCommandRepository.saveAndFlush(validateResourceTagLinkageCommand);

        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().size();

        // Update the validateResourceTagLinkageCommand
        ValidateResourceTagLinkageCommand updatedValidateResourceTagLinkageCommand = validateResourceTagLinkageCommandRepository
            .findById(validateResourceTagLinkageCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedValidateResourceTagLinkageCommand are not directly saved in db
        em.detach(updatedValidateResourceTagLinkageCommand);
        ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO = validateResourceTagLinkageCommandMapper.toDto(
            updatedValidateResourceTagLinkageCommand
        );

        restValidateResourceTagLinkageCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, validateResourceTagLinkageCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository.findAll();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
        ValidateResourceTagLinkageCommand testValidateResourceTagLinkageCommand = validateResourceTagLinkageCommandList.get(
            validateResourceTagLinkageCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void putNonExistingValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().size();
        validateResourceTagLinkageCommand.setId(count.incrementAndGet());

        // Create the ValidateResourceTagLinkageCommand
        ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO = validateResourceTagLinkageCommandMapper.toDto(
            validateResourceTagLinkageCommand
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restValidateResourceTagLinkageCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, validateResourceTagLinkageCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository.findAll();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().size();
        validateResourceTagLinkageCommand.setId(count.incrementAndGet());

        // Create the ValidateResourceTagLinkageCommand
        ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO = validateResourceTagLinkageCommandMapper.toDto(
            validateResourceTagLinkageCommand
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restValidateResourceTagLinkageCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository.findAll();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().size();
        validateResourceTagLinkageCommand.setId(count.incrementAndGet());

        // Create the ValidateResourceTagLinkageCommand
        ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO = validateResourceTagLinkageCommandMapper.toDto(
            validateResourceTagLinkageCommand
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restValidateResourceTagLinkageCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository.findAll();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateValidateResourceTagLinkageCommandWithPatch() throws Exception {
        // Initialize the database
        validateResourceTagLinkageCommandRepository.saveAndFlush(validateResourceTagLinkageCommand);

        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().size();

        // Update the validateResourceTagLinkageCommand using partial update
        ValidateResourceTagLinkageCommand partialUpdatedValidateResourceTagLinkageCommand = new ValidateResourceTagLinkageCommand();
        partialUpdatedValidateResourceTagLinkageCommand.setId(validateResourceTagLinkageCommand.getId());

        restValidateResourceTagLinkageCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedValidateResourceTagLinkageCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedValidateResourceTagLinkageCommand))
            )
            .andExpect(status().isOk());

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository.findAll();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
        ValidateResourceTagLinkageCommand testValidateResourceTagLinkageCommand = validateResourceTagLinkageCommandList.get(
            validateResourceTagLinkageCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void fullUpdateValidateResourceTagLinkageCommandWithPatch() throws Exception {
        // Initialize the database
        validateResourceTagLinkageCommandRepository.saveAndFlush(validateResourceTagLinkageCommand);

        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().size();

        // Update the validateResourceTagLinkageCommand using partial update
        ValidateResourceTagLinkageCommand partialUpdatedValidateResourceTagLinkageCommand = new ValidateResourceTagLinkageCommand();
        partialUpdatedValidateResourceTagLinkageCommand.setId(validateResourceTagLinkageCommand.getId());

        restValidateResourceTagLinkageCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedValidateResourceTagLinkageCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedValidateResourceTagLinkageCommand))
            )
            .andExpect(status().isOk());

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository.findAll();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
        ValidateResourceTagLinkageCommand testValidateResourceTagLinkageCommand = validateResourceTagLinkageCommandList.get(
            validateResourceTagLinkageCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void patchNonExistingValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().size();
        validateResourceTagLinkageCommand.setId(count.incrementAndGet());

        // Create the ValidateResourceTagLinkageCommand
        ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO = validateResourceTagLinkageCommandMapper.toDto(
            validateResourceTagLinkageCommand
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restValidateResourceTagLinkageCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, validateResourceTagLinkageCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository.findAll();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().size();
        validateResourceTagLinkageCommand.setId(count.incrementAndGet());

        // Create the ValidateResourceTagLinkageCommand
        ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO = validateResourceTagLinkageCommandMapper.toDto(
            validateResourceTagLinkageCommand
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restValidateResourceTagLinkageCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository.findAll();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamValidateResourceTagLinkageCommand() throws Exception {
        int databaseSizeBeforeUpdate = validateResourceTagLinkageCommandRepository.findAll().size();
        validateResourceTagLinkageCommand.setId(count.incrementAndGet());

        // Create the ValidateResourceTagLinkageCommand
        ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO = validateResourceTagLinkageCommandMapper.toDto(
            validateResourceTagLinkageCommand
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restValidateResourceTagLinkageCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(validateResourceTagLinkageCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ValidateResourceTagLinkageCommand in the database
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository.findAll();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteValidateResourceTagLinkageCommand() throws Exception {
        // Initialize the database
        validateResourceTagLinkageCommandRepository.saveAndFlush(validateResourceTagLinkageCommand);

        int databaseSizeBeforeDelete = validateResourceTagLinkageCommandRepository.findAll().size();

        // Delete the validateResourceTagLinkageCommand
        restValidateResourceTagLinkageCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, validateResourceTagLinkageCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommandList = validateResourceTagLinkageCommandRepository.findAll();
        assertThat(validateResourceTagLinkageCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
