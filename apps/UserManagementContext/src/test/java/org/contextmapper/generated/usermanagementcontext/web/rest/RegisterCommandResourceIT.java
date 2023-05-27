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
import org.contextmapper.generated.usermanagementcontext.domain.RegisterCommand;
import org.contextmapper.generated.usermanagementcontext.domain.enumeration.Roles;
import org.contextmapper.generated.usermanagementcontext.repository.RegisterCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.RegisterCommandDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.RegisterCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RegisterCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RegisterCommandResourceIT {

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final Roles DEFAULT_ROLE = Roles.EVALUATOR;
    private static final Roles UPDATED_ROLE = Roles.STUDENT;

    private static final String ENTITY_API_URL = "/api/register-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RegisterCommandRepository registerCommandRepository;

    @Autowired
    private RegisterCommandMapper registerCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRegisterCommandMockMvc;

    private RegisterCommand registerCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegisterCommand createEntity(EntityManager em) {
        RegisterCommand registerCommand = new RegisterCommand()
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .mail(DEFAULT_MAIL)
            .password(DEFAULT_PASSWORD)
            .role(DEFAULT_ROLE);
        return registerCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegisterCommand createUpdatedEntity(EntityManager em) {
        RegisterCommand registerCommand = new RegisterCommand()
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .mail(UPDATED_MAIL)
            .password(UPDATED_PASSWORD)
            .role(UPDATED_ROLE);
        return registerCommand;
    }

    @BeforeEach
    public void initTest() {
        registerCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createRegisterCommand() throws Exception {
        int databaseSizeBeforeCreate = registerCommandRepository.findAll().size();
        // Create the RegisterCommand
        RegisterCommandDTO registerCommandDTO = registerCommandMapper.toDto(registerCommand);
        restRegisterCommandMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(registerCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RegisterCommand in the database
        List<RegisterCommand> registerCommandList = registerCommandRepository.findAll();
        assertThat(registerCommandList).hasSize(databaseSizeBeforeCreate + 1);
        RegisterCommand testRegisterCommand = registerCommandList.get(registerCommandList.size() - 1);
        assertThat(testRegisterCommand.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testRegisterCommand.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testRegisterCommand.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testRegisterCommand.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testRegisterCommand.getRole()).isEqualTo(DEFAULT_ROLE);
    }

    @Test
    @Transactional
    void createRegisterCommandWithExistingId() throws Exception {
        // Create the RegisterCommand with an existing ID
        registerCommand.setId(1L);
        RegisterCommandDTO registerCommandDTO = registerCommandMapper.toDto(registerCommand);

        int databaseSizeBeforeCreate = registerCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegisterCommandMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(registerCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegisterCommand in the database
        List<RegisterCommand> registerCommandList = registerCommandRepository.findAll();
        assertThat(registerCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRegisterCommands() throws Exception {
        // Initialize the database
        registerCommandRepository.saveAndFlush(registerCommand);

        // Get all the registerCommandList
        restRegisterCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(registerCommand.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())));
    }

    @Test
    @Transactional
    void getRegisterCommand() throws Exception {
        // Initialize the database
        registerCommandRepository.saveAndFlush(registerCommand);

        // Get the registerCommand
        restRegisterCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, registerCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(registerCommand.getId().intValue()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingRegisterCommand() throws Exception {
        // Get the registerCommand
        restRegisterCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRegisterCommand() throws Exception {
        // Initialize the database
        registerCommandRepository.saveAndFlush(registerCommand);

        int databaseSizeBeforeUpdate = registerCommandRepository.findAll().size();

        // Update the registerCommand
        RegisterCommand updatedRegisterCommand = registerCommandRepository.findById(registerCommand.getId()).get();
        // Disconnect from session so that the updates on updatedRegisterCommand are not directly saved in db
        em.detach(updatedRegisterCommand);
        updatedRegisterCommand
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .mail(UPDATED_MAIL)
            .password(UPDATED_PASSWORD)
            .role(UPDATED_ROLE);
        RegisterCommandDTO registerCommandDTO = registerCommandMapper.toDto(updatedRegisterCommand);

        restRegisterCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, registerCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(registerCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the RegisterCommand in the database
        List<RegisterCommand> registerCommandList = registerCommandRepository.findAll();
        assertThat(registerCommandList).hasSize(databaseSizeBeforeUpdate);
        RegisterCommand testRegisterCommand = registerCommandList.get(registerCommandList.size() - 1);
        assertThat(testRegisterCommand.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testRegisterCommand.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testRegisterCommand.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testRegisterCommand.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testRegisterCommand.getRole()).isEqualTo(UPDATED_ROLE);
    }

    @Test
    @Transactional
    void putNonExistingRegisterCommand() throws Exception {
        int databaseSizeBeforeUpdate = registerCommandRepository.findAll().size();
        registerCommand.setId(count.incrementAndGet());

        // Create the RegisterCommand
        RegisterCommandDTO registerCommandDTO = registerCommandMapper.toDto(registerCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegisterCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, registerCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(registerCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegisterCommand in the database
        List<RegisterCommand> registerCommandList = registerCommandRepository.findAll();
        assertThat(registerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRegisterCommand() throws Exception {
        int databaseSizeBeforeUpdate = registerCommandRepository.findAll().size();
        registerCommand.setId(count.incrementAndGet());

        // Create the RegisterCommand
        RegisterCommandDTO registerCommandDTO = registerCommandMapper.toDto(registerCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegisterCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(registerCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegisterCommand in the database
        List<RegisterCommand> registerCommandList = registerCommandRepository.findAll();
        assertThat(registerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRegisterCommand() throws Exception {
        int databaseSizeBeforeUpdate = registerCommandRepository.findAll().size();
        registerCommand.setId(count.incrementAndGet());

        // Create the RegisterCommand
        RegisterCommandDTO registerCommandDTO = registerCommandMapper.toDto(registerCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegisterCommandMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(registerCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RegisterCommand in the database
        List<RegisterCommand> registerCommandList = registerCommandRepository.findAll();
        assertThat(registerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRegisterCommandWithPatch() throws Exception {
        // Initialize the database
        registerCommandRepository.saveAndFlush(registerCommand);

        int databaseSizeBeforeUpdate = registerCommandRepository.findAll().size();

        // Update the registerCommand using partial update
        RegisterCommand partialUpdatedRegisterCommand = new RegisterCommand();
        partialUpdatedRegisterCommand.setId(registerCommand.getId());

        partialUpdatedRegisterCommand.mail(UPDATED_MAIL).role(UPDATED_ROLE);

        restRegisterCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRegisterCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRegisterCommand))
            )
            .andExpect(status().isOk());

        // Validate the RegisterCommand in the database
        List<RegisterCommand> registerCommandList = registerCommandRepository.findAll();
        assertThat(registerCommandList).hasSize(databaseSizeBeforeUpdate);
        RegisterCommand testRegisterCommand = registerCommandList.get(registerCommandList.size() - 1);
        assertThat(testRegisterCommand.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testRegisterCommand.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testRegisterCommand.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testRegisterCommand.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testRegisterCommand.getRole()).isEqualTo(UPDATED_ROLE);
    }

    @Test
    @Transactional
    void fullUpdateRegisterCommandWithPatch() throws Exception {
        // Initialize the database
        registerCommandRepository.saveAndFlush(registerCommand);

        int databaseSizeBeforeUpdate = registerCommandRepository.findAll().size();

        // Update the registerCommand using partial update
        RegisterCommand partialUpdatedRegisterCommand = new RegisterCommand();
        partialUpdatedRegisterCommand.setId(registerCommand.getId());

        partialUpdatedRegisterCommand
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .mail(UPDATED_MAIL)
            .password(UPDATED_PASSWORD)
            .role(UPDATED_ROLE);

        restRegisterCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRegisterCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRegisterCommand))
            )
            .andExpect(status().isOk());

        // Validate the RegisterCommand in the database
        List<RegisterCommand> registerCommandList = registerCommandRepository.findAll();
        assertThat(registerCommandList).hasSize(databaseSizeBeforeUpdate);
        RegisterCommand testRegisterCommand = registerCommandList.get(registerCommandList.size() - 1);
        assertThat(testRegisterCommand.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testRegisterCommand.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testRegisterCommand.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testRegisterCommand.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testRegisterCommand.getRole()).isEqualTo(UPDATED_ROLE);
    }

    @Test
    @Transactional
    void patchNonExistingRegisterCommand() throws Exception {
        int databaseSizeBeforeUpdate = registerCommandRepository.findAll().size();
        registerCommand.setId(count.incrementAndGet());

        // Create the RegisterCommand
        RegisterCommandDTO registerCommandDTO = registerCommandMapper.toDto(registerCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegisterCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, registerCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(registerCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegisterCommand in the database
        List<RegisterCommand> registerCommandList = registerCommandRepository.findAll();
        assertThat(registerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRegisterCommand() throws Exception {
        int databaseSizeBeforeUpdate = registerCommandRepository.findAll().size();
        registerCommand.setId(count.incrementAndGet());

        // Create the RegisterCommand
        RegisterCommandDTO registerCommandDTO = registerCommandMapper.toDto(registerCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegisterCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(registerCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegisterCommand in the database
        List<RegisterCommand> registerCommandList = registerCommandRepository.findAll();
        assertThat(registerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRegisterCommand() throws Exception {
        int databaseSizeBeforeUpdate = registerCommandRepository.findAll().size();
        registerCommand.setId(count.incrementAndGet());

        // Create the RegisterCommand
        RegisterCommandDTO registerCommandDTO = registerCommandMapper.toDto(registerCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegisterCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(registerCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RegisterCommand in the database
        List<RegisterCommand> registerCommandList = registerCommandRepository.findAll();
        assertThat(registerCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRegisterCommand() throws Exception {
        // Initialize the database
        registerCommandRepository.saveAndFlush(registerCommand);

        int databaseSizeBeforeDelete = registerCommandRepository.findAll().size();

        // Delete the registerCommand
        restRegisterCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, registerCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RegisterCommand> registerCommandList = registerCommandRepository.findAll();
        assertThat(registerCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
