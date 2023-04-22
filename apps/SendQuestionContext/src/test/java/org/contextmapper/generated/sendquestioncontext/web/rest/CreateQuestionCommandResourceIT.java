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
import org.contextmapper.generated.sendquestioncontext.domain.CreateQuestionCommand;
import org.contextmapper.generated.sendquestioncontext.repository.CreateQuestionCommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreateQuestionCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreateQuestionCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/create-question-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateQuestionCommandRepository createQuestionCommandRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreateQuestionCommandMockMvc;

    private CreateQuestionCommand createQuestionCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateQuestionCommand createEntity(EntityManager em) {
        CreateQuestionCommand createQuestionCommand = new CreateQuestionCommand();
        return createQuestionCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateQuestionCommand createUpdatedEntity(EntityManager em) {
        CreateQuestionCommand createQuestionCommand = new CreateQuestionCommand();
        return createQuestionCommand;
    }

    @BeforeEach
    public void initTest() {
        createQuestionCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeCreate = createQuestionCommandRepository.findAll().size();
        // Create the CreateQuestionCommand
        restCreateQuestionCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            )
            .andExpect(status().isCreated());

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeCreate + 1);
        CreateQuestionCommand testCreateQuestionCommand = createQuestionCommandList.get(createQuestionCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createCreateQuestionCommandWithExistingId() throws Exception {
        // Create the CreateQuestionCommand with an existing ID
        createQuestionCommand.setId(1L);

        int databaseSizeBeforeCreate = createQuestionCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreateQuestionCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCreateQuestionCommands() throws Exception {
        // Initialize the database
        createQuestionCommandRepository.saveAndFlush(createQuestionCommand);

        // Get all the createQuestionCommandList
        restCreateQuestionCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(createQuestionCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getCreateQuestionCommand() throws Exception {
        // Initialize the database
        createQuestionCommandRepository.saveAndFlush(createQuestionCommand);

        // Get the createQuestionCommand
        restCreateQuestionCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, createQuestionCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(createQuestionCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCreateQuestionCommand() throws Exception {
        // Get the createQuestionCommand
        restCreateQuestionCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCreateQuestionCommand() throws Exception {
        // Initialize the database
        createQuestionCommandRepository.saveAndFlush(createQuestionCommand);

        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().size();

        // Update the createQuestionCommand
        CreateQuestionCommand updatedCreateQuestionCommand = createQuestionCommandRepository.findById(createQuestionCommand.getId()).get();
        // Disconnect from session so that the updates on updatedCreateQuestionCommand are not directly saved in db
        em.detach(updatedCreateQuestionCommand);

        restCreateQuestionCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCreateQuestionCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCreateQuestionCommand))
            )
            .andExpect(status().isOk());

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateQuestionCommand testCreateQuestionCommand = createQuestionCommandList.get(createQuestionCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().size();
        createQuestionCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateQuestionCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createQuestionCommand.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().size();
        createQuestionCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateQuestionCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().size();
        createQuestionCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateQuestionCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCreateQuestionCommandWithPatch() throws Exception {
        // Initialize the database
        createQuestionCommandRepository.saveAndFlush(createQuestionCommand);

        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().size();

        // Update the createQuestionCommand using partial update
        CreateQuestionCommand partialUpdatedCreateQuestionCommand = new CreateQuestionCommand();
        partialUpdatedCreateQuestionCommand.setId(createQuestionCommand.getId());

        restCreateQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateQuestionCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateQuestionCommand))
            )
            .andExpect(status().isOk());

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateQuestionCommand testCreateQuestionCommand = createQuestionCommandList.get(createQuestionCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCreateQuestionCommandWithPatch() throws Exception {
        // Initialize the database
        createQuestionCommandRepository.saveAndFlush(createQuestionCommand);

        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().size();

        // Update the createQuestionCommand using partial update
        CreateQuestionCommand partialUpdatedCreateQuestionCommand = new CreateQuestionCommand();
        partialUpdatedCreateQuestionCommand.setId(createQuestionCommand.getId());

        restCreateQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateQuestionCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateQuestionCommand))
            )
            .andExpect(status().isOk());

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
        CreateQuestionCommand testCreateQuestionCommand = createQuestionCommandList.get(createQuestionCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().size();
        createQuestionCommand.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, createQuestionCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().size();
        createQuestionCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCreateQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = createQuestionCommandRepository.findAll().size();
        createQuestionCommand.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createQuestionCommand))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateQuestionCommand in the database
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCreateQuestionCommand() throws Exception {
        // Initialize the database
        createQuestionCommandRepository.saveAndFlush(createQuestionCommand);

        int databaseSizeBeforeDelete = createQuestionCommandRepository.findAll().size();

        // Delete the createQuestionCommand
        restCreateQuestionCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, createQuestionCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CreateQuestionCommand> createQuestionCommandList = createQuestionCommandRepository.findAll();
        assertThat(createQuestionCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
