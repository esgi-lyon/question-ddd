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
import org.contextmapper.generated.sendquestioncontext.domain.ViewTagsForQuestionCommand;
import org.contextmapper.generated.sendquestioncontext.repository.ViewTagsForQuestionCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.ViewTagsForQuestionCommandDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.ViewTagsForQuestionCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ViewTagsForQuestionCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ViewTagsForQuestionCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/view-tags-for-question-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ViewTagsForQuestionCommandRepository viewTagsForQuestionCommandRepository;

    @Autowired
    private ViewTagsForQuestionCommandMapper viewTagsForQuestionCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restViewTagsForQuestionCommandMockMvc;

    private ViewTagsForQuestionCommand viewTagsForQuestionCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewTagsForQuestionCommand createEntity(EntityManager em) {
        ViewTagsForQuestionCommand viewTagsForQuestionCommand = new ViewTagsForQuestionCommand();
        return viewTagsForQuestionCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewTagsForQuestionCommand createUpdatedEntity(EntityManager em) {
        ViewTagsForQuestionCommand viewTagsForQuestionCommand = new ViewTagsForQuestionCommand();
        return viewTagsForQuestionCommand;
    }

    @BeforeEach
    public void initTest() {
        viewTagsForQuestionCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createViewTagsForQuestionCommand() throws Exception {
        int databaseSizeBeforeCreate = viewTagsForQuestionCommandRepository.findAll().size();
        // Create the ViewTagsForQuestionCommand
        ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO = viewTagsForQuestionCommandMapper.toDto(viewTagsForQuestionCommand);
        restViewTagsForQuestionCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewTagsForQuestionCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ViewTagsForQuestionCommand in the database
        List<ViewTagsForQuestionCommand> viewTagsForQuestionCommandList = viewTagsForQuestionCommandRepository.findAll();
        assertThat(viewTagsForQuestionCommandList).hasSize(databaseSizeBeforeCreate + 1);
        ViewTagsForQuestionCommand testViewTagsForQuestionCommand = viewTagsForQuestionCommandList.get(
            viewTagsForQuestionCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void createViewTagsForQuestionCommandWithExistingId() throws Exception {
        // Create the ViewTagsForQuestionCommand with an existing ID
        viewTagsForQuestionCommand.setId(1L);
        ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO = viewTagsForQuestionCommandMapper.toDto(viewTagsForQuestionCommand);

        int databaseSizeBeforeCreate = viewTagsForQuestionCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restViewTagsForQuestionCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewTagsForQuestionCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewTagsForQuestionCommand in the database
        List<ViewTagsForQuestionCommand> viewTagsForQuestionCommandList = viewTagsForQuestionCommandRepository.findAll();
        assertThat(viewTagsForQuestionCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllViewTagsForQuestionCommands() throws Exception {
        // Initialize the database
        viewTagsForQuestionCommandRepository.saveAndFlush(viewTagsForQuestionCommand);

        // Get all the viewTagsForQuestionCommandList
        restViewTagsForQuestionCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viewTagsForQuestionCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getViewTagsForQuestionCommand() throws Exception {
        // Initialize the database
        viewTagsForQuestionCommandRepository.saveAndFlush(viewTagsForQuestionCommand);

        // Get the viewTagsForQuestionCommand
        restViewTagsForQuestionCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, viewTagsForQuestionCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(viewTagsForQuestionCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingViewTagsForQuestionCommand() throws Exception {
        // Get the viewTagsForQuestionCommand
        restViewTagsForQuestionCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingViewTagsForQuestionCommand() throws Exception {
        // Initialize the database
        viewTagsForQuestionCommandRepository.saveAndFlush(viewTagsForQuestionCommand);

        int databaseSizeBeforeUpdate = viewTagsForQuestionCommandRepository.findAll().size();

        // Update the viewTagsForQuestionCommand
        ViewTagsForQuestionCommand updatedViewTagsForQuestionCommand = viewTagsForQuestionCommandRepository
            .findById(viewTagsForQuestionCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedViewTagsForQuestionCommand are not directly saved in db
        em.detach(updatedViewTagsForQuestionCommand);
        ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO = viewTagsForQuestionCommandMapper.toDto(
            updatedViewTagsForQuestionCommand
        );

        restViewTagsForQuestionCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewTagsForQuestionCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewTagsForQuestionCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the ViewTagsForQuestionCommand in the database
        List<ViewTagsForQuestionCommand> viewTagsForQuestionCommandList = viewTagsForQuestionCommandRepository.findAll();
        assertThat(viewTagsForQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewTagsForQuestionCommand testViewTagsForQuestionCommand = viewTagsForQuestionCommandList.get(
            viewTagsForQuestionCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void putNonExistingViewTagsForQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewTagsForQuestionCommandRepository.findAll().size();
        viewTagsForQuestionCommand.setId(count.incrementAndGet());

        // Create the ViewTagsForQuestionCommand
        ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO = viewTagsForQuestionCommandMapper.toDto(viewTagsForQuestionCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewTagsForQuestionCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewTagsForQuestionCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewTagsForQuestionCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewTagsForQuestionCommand in the database
        List<ViewTagsForQuestionCommand> viewTagsForQuestionCommandList = viewTagsForQuestionCommandRepository.findAll();
        assertThat(viewTagsForQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchViewTagsForQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewTagsForQuestionCommandRepository.findAll().size();
        viewTagsForQuestionCommand.setId(count.incrementAndGet());

        // Create the ViewTagsForQuestionCommand
        ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO = viewTagsForQuestionCommandMapper.toDto(viewTagsForQuestionCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewTagsForQuestionCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewTagsForQuestionCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewTagsForQuestionCommand in the database
        List<ViewTagsForQuestionCommand> viewTagsForQuestionCommandList = viewTagsForQuestionCommandRepository.findAll();
        assertThat(viewTagsForQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamViewTagsForQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewTagsForQuestionCommandRepository.findAll().size();
        viewTagsForQuestionCommand.setId(count.incrementAndGet());

        // Create the ViewTagsForQuestionCommand
        ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO = viewTagsForQuestionCommandMapper.toDto(viewTagsForQuestionCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewTagsForQuestionCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewTagsForQuestionCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewTagsForQuestionCommand in the database
        List<ViewTagsForQuestionCommand> viewTagsForQuestionCommandList = viewTagsForQuestionCommandRepository.findAll();
        assertThat(viewTagsForQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateViewTagsForQuestionCommandWithPatch() throws Exception {
        // Initialize the database
        viewTagsForQuestionCommandRepository.saveAndFlush(viewTagsForQuestionCommand);

        int databaseSizeBeforeUpdate = viewTagsForQuestionCommandRepository.findAll().size();

        // Update the viewTagsForQuestionCommand using partial update
        ViewTagsForQuestionCommand partialUpdatedViewTagsForQuestionCommand = new ViewTagsForQuestionCommand();
        partialUpdatedViewTagsForQuestionCommand.setId(viewTagsForQuestionCommand.getId());

        restViewTagsForQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewTagsForQuestionCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewTagsForQuestionCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewTagsForQuestionCommand in the database
        List<ViewTagsForQuestionCommand> viewTagsForQuestionCommandList = viewTagsForQuestionCommandRepository.findAll();
        assertThat(viewTagsForQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewTagsForQuestionCommand testViewTagsForQuestionCommand = viewTagsForQuestionCommandList.get(
            viewTagsForQuestionCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void fullUpdateViewTagsForQuestionCommandWithPatch() throws Exception {
        // Initialize the database
        viewTagsForQuestionCommandRepository.saveAndFlush(viewTagsForQuestionCommand);

        int databaseSizeBeforeUpdate = viewTagsForQuestionCommandRepository.findAll().size();

        // Update the viewTagsForQuestionCommand using partial update
        ViewTagsForQuestionCommand partialUpdatedViewTagsForQuestionCommand = new ViewTagsForQuestionCommand();
        partialUpdatedViewTagsForQuestionCommand.setId(viewTagsForQuestionCommand.getId());

        restViewTagsForQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewTagsForQuestionCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewTagsForQuestionCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewTagsForQuestionCommand in the database
        List<ViewTagsForQuestionCommand> viewTagsForQuestionCommandList = viewTagsForQuestionCommandRepository.findAll();
        assertThat(viewTagsForQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewTagsForQuestionCommand testViewTagsForQuestionCommand = viewTagsForQuestionCommandList.get(
            viewTagsForQuestionCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void patchNonExistingViewTagsForQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewTagsForQuestionCommandRepository.findAll().size();
        viewTagsForQuestionCommand.setId(count.incrementAndGet());

        // Create the ViewTagsForQuestionCommand
        ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO = viewTagsForQuestionCommandMapper.toDto(viewTagsForQuestionCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewTagsForQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, viewTagsForQuestionCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewTagsForQuestionCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewTagsForQuestionCommand in the database
        List<ViewTagsForQuestionCommand> viewTagsForQuestionCommandList = viewTagsForQuestionCommandRepository.findAll();
        assertThat(viewTagsForQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchViewTagsForQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewTagsForQuestionCommandRepository.findAll().size();
        viewTagsForQuestionCommand.setId(count.incrementAndGet());

        // Create the ViewTagsForQuestionCommand
        ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO = viewTagsForQuestionCommandMapper.toDto(viewTagsForQuestionCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewTagsForQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewTagsForQuestionCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewTagsForQuestionCommand in the database
        List<ViewTagsForQuestionCommand> viewTagsForQuestionCommandList = viewTagsForQuestionCommandRepository.findAll();
        assertThat(viewTagsForQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamViewTagsForQuestionCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewTagsForQuestionCommandRepository.findAll().size();
        viewTagsForQuestionCommand.setId(count.incrementAndGet());

        // Create the ViewTagsForQuestionCommand
        ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO = viewTagsForQuestionCommandMapper.toDto(viewTagsForQuestionCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewTagsForQuestionCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewTagsForQuestionCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewTagsForQuestionCommand in the database
        List<ViewTagsForQuestionCommand> viewTagsForQuestionCommandList = viewTagsForQuestionCommandRepository.findAll();
        assertThat(viewTagsForQuestionCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteViewTagsForQuestionCommand() throws Exception {
        // Initialize the database
        viewTagsForQuestionCommandRepository.saveAndFlush(viewTagsForQuestionCommand);

        int databaseSizeBeforeDelete = viewTagsForQuestionCommandRepository.findAll().size();

        // Delete the viewTagsForQuestionCommand
        restViewTagsForQuestionCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, viewTagsForQuestionCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ViewTagsForQuestionCommand> viewTagsForQuestionCommandList = viewTagsForQuestionCommandRepository.findAll();
        assertThat(viewTagsForQuestionCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
