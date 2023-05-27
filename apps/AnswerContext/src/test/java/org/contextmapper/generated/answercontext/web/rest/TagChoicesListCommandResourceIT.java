package org.contextmapper.generated.answercontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.answercontext.IntegrationTest;
import org.contextmapper.generated.answercontext.domain.TagChoicesListCommand;
import org.contextmapper.generated.answercontext.repository.TagChoicesListCommandRepository;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListCommandDTO;
import org.contextmapper.generated.answercontext.service.mapper.TagChoicesListCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TagChoicesListCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TagChoicesListCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/tag-choices-list-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TagChoicesListCommandRepository tagChoicesListCommandRepository;

    @Autowired
    private TagChoicesListCommandMapper tagChoicesListCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagChoicesListCommandMockMvc;

    private TagChoicesListCommand tagChoicesListCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagChoicesListCommand createEntity(EntityManager em) {
        TagChoicesListCommand tagChoicesListCommand = new TagChoicesListCommand();
        return tagChoicesListCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagChoicesListCommand createUpdatedEntity(EntityManager em) {
        TagChoicesListCommand tagChoicesListCommand = new TagChoicesListCommand();
        return tagChoicesListCommand;
    }

    @BeforeEach
    public void initTest() {
        tagChoicesListCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeCreate = tagChoicesListCommandRepository.findAll().size();
        // Create the TagChoicesListCommand
        TagChoicesListCommandDTO tagChoicesListCommandDTO = tagChoicesListCommandMapper.toDto(tagChoicesListCommand);
        restTagChoicesListCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeCreate + 1);
        TagChoicesListCommand testTagChoicesListCommand = tagChoicesListCommandList.get(tagChoicesListCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createTagChoicesListCommandWithExistingId() throws Exception {
        // Create the TagChoicesListCommand with an existing ID
        tagChoicesListCommand.setId(1L);
        TagChoicesListCommandDTO tagChoicesListCommandDTO = tagChoicesListCommandMapper.toDto(tagChoicesListCommand);

        int databaseSizeBeforeCreate = tagChoicesListCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTagChoicesListCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTagChoicesListCommands() throws Exception {
        // Initialize the database
        tagChoicesListCommandRepository.saveAndFlush(tagChoicesListCommand);

        // Get all the tagChoicesListCommandList
        restTagChoicesListCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagChoicesListCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getTagChoicesListCommand() throws Exception {
        // Initialize the database
        tagChoicesListCommandRepository.saveAndFlush(tagChoicesListCommand);

        // Get the tagChoicesListCommand
        restTagChoicesListCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, tagChoicesListCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tagChoicesListCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTagChoicesListCommand() throws Exception {
        // Get the tagChoicesListCommand
        restTagChoicesListCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTagChoicesListCommand() throws Exception {
        // Initialize the database
        tagChoicesListCommandRepository.saveAndFlush(tagChoicesListCommand);

        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().size();

        // Update the tagChoicesListCommand
        TagChoicesListCommand updatedTagChoicesListCommand = tagChoicesListCommandRepository.findById(tagChoicesListCommand.getId()).get();
        // Disconnect from session so that the updates on updatedTagChoicesListCommand are not directly saved in db
        em.detach(updatedTagChoicesListCommand);
        TagChoicesListCommandDTO tagChoicesListCommandDTO = tagChoicesListCommandMapper.toDto(updatedTagChoicesListCommand);

        restTagChoicesListCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tagChoicesListCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
        TagChoicesListCommand testTagChoicesListCommand = tagChoicesListCommandList.get(tagChoicesListCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().size();
        tagChoicesListCommand.setId(count.incrementAndGet());

        // Create the TagChoicesListCommand
        TagChoicesListCommandDTO tagChoicesListCommandDTO = tagChoicesListCommandMapper.toDto(tagChoicesListCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagChoicesListCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tagChoicesListCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().size();
        tagChoicesListCommand.setId(count.incrementAndGet());

        // Create the TagChoicesListCommand
        TagChoicesListCommandDTO tagChoicesListCommandDTO = tagChoicesListCommandMapper.toDto(tagChoicesListCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagChoicesListCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().size();
        tagChoicesListCommand.setId(count.incrementAndGet());

        // Create the TagChoicesListCommand
        TagChoicesListCommandDTO tagChoicesListCommandDTO = tagChoicesListCommandMapper.toDto(tagChoicesListCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagChoicesListCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTagChoicesListCommandWithPatch() throws Exception {
        // Initialize the database
        tagChoicesListCommandRepository.saveAndFlush(tagChoicesListCommand);

        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().size();

        // Update the tagChoicesListCommand using partial update
        TagChoicesListCommand partialUpdatedTagChoicesListCommand = new TagChoicesListCommand();
        partialUpdatedTagChoicesListCommand.setId(tagChoicesListCommand.getId());

        restTagChoicesListCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTagChoicesListCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTagChoicesListCommand))
            )
            .andExpect(status().isOk());

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
        TagChoicesListCommand testTagChoicesListCommand = tagChoicesListCommandList.get(tagChoicesListCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateTagChoicesListCommandWithPatch() throws Exception {
        // Initialize the database
        tagChoicesListCommandRepository.saveAndFlush(tagChoicesListCommand);

        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().size();

        // Update the tagChoicesListCommand using partial update
        TagChoicesListCommand partialUpdatedTagChoicesListCommand = new TagChoicesListCommand();
        partialUpdatedTagChoicesListCommand.setId(tagChoicesListCommand.getId());

        restTagChoicesListCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTagChoicesListCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTagChoicesListCommand))
            )
            .andExpect(status().isOk());

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
        TagChoicesListCommand testTagChoicesListCommand = tagChoicesListCommandList.get(tagChoicesListCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().size();
        tagChoicesListCommand.setId(count.incrementAndGet());

        // Create the TagChoicesListCommand
        TagChoicesListCommandDTO tagChoicesListCommandDTO = tagChoicesListCommandMapper.toDto(tagChoicesListCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagChoicesListCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tagChoicesListCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().size();
        tagChoicesListCommand.setId(count.incrementAndGet());

        // Create the TagChoicesListCommand
        TagChoicesListCommandDTO tagChoicesListCommandDTO = tagChoicesListCommandMapper.toDto(tagChoicesListCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagChoicesListCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTagChoicesListCommand() throws Exception {
        int databaseSizeBeforeUpdate = tagChoicesListCommandRepository.findAll().size();
        tagChoicesListCommand.setId(count.incrementAndGet());

        // Create the TagChoicesListCommand
        TagChoicesListCommandDTO tagChoicesListCommandDTO = tagChoicesListCommandMapper.toDto(tagChoicesListCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTagChoicesListCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tagChoicesListCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TagChoicesListCommand in the database
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTagChoicesListCommand() throws Exception {
        // Initialize the database
        tagChoicesListCommandRepository.saveAndFlush(tagChoicesListCommand);

        int databaseSizeBeforeDelete = tagChoicesListCommandRepository.findAll().size();

        // Delete the tagChoicesListCommand
        restTagChoicesListCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, tagChoicesListCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TagChoicesListCommand> tagChoicesListCommandList = tagChoicesListCommandRepository.findAll();
        assertThat(tagChoicesListCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
