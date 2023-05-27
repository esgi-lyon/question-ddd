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
import org.contextmapper.generated.usermanagementcontext.domain.ViewUserByEmailCommand;
import org.contextmapper.generated.usermanagementcontext.repository.ViewUserByEmailCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.ViewUserByEmailCommandDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.ViewUserByEmailCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ViewUserByEmailCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ViewUserByEmailCommandResourceIT {

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/view-user-by-email-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ViewUserByEmailCommandRepository viewUserByEmailCommandRepository;

    @Autowired
    private ViewUserByEmailCommandMapper viewUserByEmailCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restViewUserByEmailCommandMockMvc;

    private ViewUserByEmailCommand viewUserByEmailCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewUserByEmailCommand createEntity(EntityManager em) {
        ViewUserByEmailCommand viewUserByEmailCommand = new ViewUserByEmailCommand().mail(DEFAULT_MAIL);
        return viewUserByEmailCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewUserByEmailCommand createUpdatedEntity(EntityManager em) {
        ViewUserByEmailCommand viewUserByEmailCommand = new ViewUserByEmailCommand().mail(UPDATED_MAIL);
        return viewUserByEmailCommand;
    }

    @BeforeEach
    public void initTest() {
        viewUserByEmailCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createViewUserByEmailCommand() throws Exception {
        int databaseSizeBeforeCreate = viewUserByEmailCommandRepository.findAll().size();
        // Create the ViewUserByEmailCommand
        ViewUserByEmailCommandDTO viewUserByEmailCommandDTO = viewUserByEmailCommandMapper.toDto(viewUserByEmailCommand);
        restViewUserByEmailCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewUserByEmailCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ViewUserByEmailCommand in the database
        List<ViewUserByEmailCommand> viewUserByEmailCommandList = viewUserByEmailCommandRepository.findAll();
        assertThat(viewUserByEmailCommandList).hasSize(databaseSizeBeforeCreate + 1);
        ViewUserByEmailCommand testViewUserByEmailCommand = viewUserByEmailCommandList.get(viewUserByEmailCommandList.size() - 1);
        assertThat(testViewUserByEmailCommand.getMail()).isEqualTo(DEFAULT_MAIL);
    }

    @Test
    @Transactional
    void createViewUserByEmailCommandWithExistingId() throws Exception {
        // Create the ViewUserByEmailCommand with an existing ID
        viewUserByEmailCommand.setId(1L);
        ViewUserByEmailCommandDTO viewUserByEmailCommandDTO = viewUserByEmailCommandMapper.toDto(viewUserByEmailCommand);

        int databaseSizeBeforeCreate = viewUserByEmailCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restViewUserByEmailCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewUserByEmailCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewUserByEmailCommand in the database
        List<ViewUserByEmailCommand> viewUserByEmailCommandList = viewUserByEmailCommandRepository.findAll();
        assertThat(viewUserByEmailCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllViewUserByEmailCommands() throws Exception {
        // Initialize the database
        viewUserByEmailCommandRepository.saveAndFlush(viewUserByEmailCommand);

        // Get all the viewUserByEmailCommandList
        restViewUserByEmailCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viewUserByEmailCommand.getId().intValue())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)));
    }

    @Test
    @Transactional
    void getViewUserByEmailCommand() throws Exception {
        // Initialize the database
        viewUserByEmailCommandRepository.saveAndFlush(viewUserByEmailCommand);

        // Get the viewUserByEmailCommand
        restViewUserByEmailCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, viewUserByEmailCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(viewUserByEmailCommand.getId().intValue()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL));
    }

    @Test
    @Transactional
    void getNonExistingViewUserByEmailCommand() throws Exception {
        // Get the viewUserByEmailCommand
        restViewUserByEmailCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingViewUserByEmailCommand() throws Exception {
        // Initialize the database
        viewUserByEmailCommandRepository.saveAndFlush(viewUserByEmailCommand);

        int databaseSizeBeforeUpdate = viewUserByEmailCommandRepository.findAll().size();

        // Update the viewUserByEmailCommand
        ViewUserByEmailCommand updatedViewUserByEmailCommand = viewUserByEmailCommandRepository
            .findById(viewUserByEmailCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedViewUserByEmailCommand are not directly saved in db
        em.detach(updatedViewUserByEmailCommand);
        updatedViewUserByEmailCommand.mail(UPDATED_MAIL);
        ViewUserByEmailCommandDTO viewUserByEmailCommandDTO = viewUserByEmailCommandMapper.toDto(updatedViewUserByEmailCommand);

        restViewUserByEmailCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewUserByEmailCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewUserByEmailCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the ViewUserByEmailCommand in the database
        List<ViewUserByEmailCommand> viewUserByEmailCommandList = viewUserByEmailCommandRepository.findAll();
        assertThat(viewUserByEmailCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewUserByEmailCommand testViewUserByEmailCommand = viewUserByEmailCommandList.get(viewUserByEmailCommandList.size() - 1);
        assertThat(testViewUserByEmailCommand.getMail()).isEqualTo(UPDATED_MAIL);
    }

    @Test
    @Transactional
    void putNonExistingViewUserByEmailCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewUserByEmailCommandRepository.findAll().size();
        viewUserByEmailCommand.setId(count.incrementAndGet());

        // Create the ViewUserByEmailCommand
        ViewUserByEmailCommandDTO viewUserByEmailCommandDTO = viewUserByEmailCommandMapper.toDto(viewUserByEmailCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewUserByEmailCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewUserByEmailCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewUserByEmailCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewUserByEmailCommand in the database
        List<ViewUserByEmailCommand> viewUserByEmailCommandList = viewUserByEmailCommandRepository.findAll();
        assertThat(viewUserByEmailCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchViewUserByEmailCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewUserByEmailCommandRepository.findAll().size();
        viewUserByEmailCommand.setId(count.incrementAndGet());

        // Create the ViewUserByEmailCommand
        ViewUserByEmailCommandDTO viewUserByEmailCommandDTO = viewUserByEmailCommandMapper.toDto(viewUserByEmailCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewUserByEmailCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewUserByEmailCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewUserByEmailCommand in the database
        List<ViewUserByEmailCommand> viewUserByEmailCommandList = viewUserByEmailCommandRepository.findAll();
        assertThat(viewUserByEmailCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamViewUserByEmailCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewUserByEmailCommandRepository.findAll().size();
        viewUserByEmailCommand.setId(count.incrementAndGet());

        // Create the ViewUserByEmailCommand
        ViewUserByEmailCommandDTO viewUserByEmailCommandDTO = viewUserByEmailCommandMapper.toDto(viewUserByEmailCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewUserByEmailCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewUserByEmailCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewUserByEmailCommand in the database
        List<ViewUserByEmailCommand> viewUserByEmailCommandList = viewUserByEmailCommandRepository.findAll();
        assertThat(viewUserByEmailCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateViewUserByEmailCommandWithPatch() throws Exception {
        // Initialize the database
        viewUserByEmailCommandRepository.saveAndFlush(viewUserByEmailCommand);

        int databaseSizeBeforeUpdate = viewUserByEmailCommandRepository.findAll().size();

        // Update the viewUserByEmailCommand using partial update
        ViewUserByEmailCommand partialUpdatedViewUserByEmailCommand = new ViewUserByEmailCommand();
        partialUpdatedViewUserByEmailCommand.setId(viewUserByEmailCommand.getId());

        restViewUserByEmailCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewUserByEmailCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewUserByEmailCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewUserByEmailCommand in the database
        List<ViewUserByEmailCommand> viewUserByEmailCommandList = viewUserByEmailCommandRepository.findAll();
        assertThat(viewUserByEmailCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewUserByEmailCommand testViewUserByEmailCommand = viewUserByEmailCommandList.get(viewUserByEmailCommandList.size() - 1);
        assertThat(testViewUserByEmailCommand.getMail()).isEqualTo(DEFAULT_MAIL);
    }

    @Test
    @Transactional
    void fullUpdateViewUserByEmailCommandWithPatch() throws Exception {
        // Initialize the database
        viewUserByEmailCommandRepository.saveAndFlush(viewUserByEmailCommand);

        int databaseSizeBeforeUpdate = viewUserByEmailCommandRepository.findAll().size();

        // Update the viewUserByEmailCommand using partial update
        ViewUserByEmailCommand partialUpdatedViewUserByEmailCommand = new ViewUserByEmailCommand();
        partialUpdatedViewUserByEmailCommand.setId(viewUserByEmailCommand.getId());

        partialUpdatedViewUserByEmailCommand.mail(UPDATED_MAIL);

        restViewUserByEmailCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewUserByEmailCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewUserByEmailCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewUserByEmailCommand in the database
        List<ViewUserByEmailCommand> viewUserByEmailCommandList = viewUserByEmailCommandRepository.findAll();
        assertThat(viewUserByEmailCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewUserByEmailCommand testViewUserByEmailCommand = viewUserByEmailCommandList.get(viewUserByEmailCommandList.size() - 1);
        assertThat(testViewUserByEmailCommand.getMail()).isEqualTo(UPDATED_MAIL);
    }

    @Test
    @Transactional
    void patchNonExistingViewUserByEmailCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewUserByEmailCommandRepository.findAll().size();
        viewUserByEmailCommand.setId(count.incrementAndGet());

        // Create the ViewUserByEmailCommand
        ViewUserByEmailCommandDTO viewUserByEmailCommandDTO = viewUserByEmailCommandMapper.toDto(viewUserByEmailCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewUserByEmailCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, viewUserByEmailCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewUserByEmailCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewUserByEmailCommand in the database
        List<ViewUserByEmailCommand> viewUserByEmailCommandList = viewUserByEmailCommandRepository.findAll();
        assertThat(viewUserByEmailCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchViewUserByEmailCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewUserByEmailCommandRepository.findAll().size();
        viewUserByEmailCommand.setId(count.incrementAndGet());

        // Create the ViewUserByEmailCommand
        ViewUserByEmailCommandDTO viewUserByEmailCommandDTO = viewUserByEmailCommandMapper.toDto(viewUserByEmailCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewUserByEmailCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewUserByEmailCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewUserByEmailCommand in the database
        List<ViewUserByEmailCommand> viewUserByEmailCommandList = viewUserByEmailCommandRepository.findAll();
        assertThat(viewUserByEmailCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamViewUserByEmailCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewUserByEmailCommandRepository.findAll().size();
        viewUserByEmailCommand.setId(count.incrementAndGet());

        // Create the ViewUserByEmailCommand
        ViewUserByEmailCommandDTO viewUserByEmailCommandDTO = viewUserByEmailCommandMapper.toDto(viewUserByEmailCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewUserByEmailCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewUserByEmailCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewUserByEmailCommand in the database
        List<ViewUserByEmailCommand> viewUserByEmailCommandList = viewUserByEmailCommandRepository.findAll();
        assertThat(viewUserByEmailCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteViewUserByEmailCommand() throws Exception {
        // Initialize the database
        viewUserByEmailCommandRepository.saveAndFlush(viewUserByEmailCommand);

        int databaseSizeBeforeDelete = viewUserByEmailCommandRepository.findAll().size();

        // Delete the viewUserByEmailCommand
        restViewUserByEmailCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, viewUserByEmailCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ViewUserByEmailCommand> viewUserByEmailCommandList = viewUserByEmailCommandRepository.findAll();
        assertThat(viewUserByEmailCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
