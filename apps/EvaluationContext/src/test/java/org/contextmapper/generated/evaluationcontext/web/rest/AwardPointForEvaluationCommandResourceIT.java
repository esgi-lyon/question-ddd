package org.contextmapper.generated.evaluationcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.evaluationcontext.IntegrationTest;
import org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.AwardPointForEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardPointForEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AwardPointForEvaluationCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AwardPointForEvaluationCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AwardPointForEvaluationCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/award-point-for-evaluation-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AwardPointForEvaluationCommandRepository awardPointForEvaluationCommandRepository;

    @Autowired
    private AwardPointForEvaluationCommandMapper awardPointForEvaluationCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAwardPointForEvaluationCommandMockMvc;

    private AwardPointForEvaluationCommand awardPointForEvaluationCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AwardPointForEvaluationCommand createEntity(EntityManager em) {
        AwardPointForEvaluationCommand awardPointForEvaluationCommand = new AwardPointForEvaluationCommand();
        return awardPointForEvaluationCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AwardPointForEvaluationCommand createUpdatedEntity(EntityManager em) {
        AwardPointForEvaluationCommand awardPointForEvaluationCommand = new AwardPointForEvaluationCommand();
        return awardPointForEvaluationCommand;
    }

    @BeforeEach
    public void initTest() {
        awardPointForEvaluationCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeCreate = awardPointForEvaluationCommandRepository.findAll().size();
        // Create the AwardPointForEvaluationCommand
        AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO = awardPointForEvaluationCommandMapper.toDto(
            awardPointForEvaluationCommand
        );
        restAwardPointForEvaluationCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository.findAll();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeCreate + 1);
        AwardPointForEvaluationCommand testAwardPointForEvaluationCommand = awardPointForEvaluationCommandList.get(
            awardPointForEvaluationCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void createAwardPointForEvaluationCommandWithExistingId() throws Exception {
        // Create the AwardPointForEvaluationCommand with an existing ID
        awardPointForEvaluationCommand.setId(1L);
        AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO = awardPointForEvaluationCommandMapper.toDto(
            awardPointForEvaluationCommand
        );

        int databaseSizeBeforeCreate = awardPointForEvaluationCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAwardPointForEvaluationCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository.findAll();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAwardPointForEvaluationCommands() throws Exception {
        // Initialize the database
        awardPointForEvaluationCommandRepository.saveAndFlush(awardPointForEvaluationCommand);

        // Get all the awardPointForEvaluationCommandList
        restAwardPointForEvaluationCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(awardPointForEvaluationCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getAwardPointForEvaluationCommand() throws Exception {
        // Initialize the database
        awardPointForEvaluationCommandRepository.saveAndFlush(awardPointForEvaluationCommand);

        // Get the awardPointForEvaluationCommand
        restAwardPointForEvaluationCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, awardPointForEvaluationCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(awardPointForEvaluationCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAwardPointForEvaluationCommand() throws Exception {
        // Get the awardPointForEvaluationCommand
        restAwardPointForEvaluationCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAwardPointForEvaluationCommand() throws Exception {
        // Initialize the database
        awardPointForEvaluationCommandRepository.saveAndFlush(awardPointForEvaluationCommand);

        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().size();

        // Update the awardPointForEvaluationCommand
        AwardPointForEvaluationCommand updatedAwardPointForEvaluationCommand = awardPointForEvaluationCommandRepository
            .findById(awardPointForEvaluationCommand.getId())
            .get();
        // Disconnect from session so that the updates on updatedAwardPointForEvaluationCommand are not directly saved in db
        em.detach(updatedAwardPointForEvaluationCommand);
        AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO = awardPointForEvaluationCommandMapper.toDto(
            updatedAwardPointForEvaluationCommand
        );

        restAwardPointForEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, awardPointForEvaluationCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository.findAll();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        AwardPointForEvaluationCommand testAwardPointForEvaluationCommand = awardPointForEvaluationCommandList.get(
            awardPointForEvaluationCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void putNonExistingAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().size();
        awardPointForEvaluationCommand.setId(count.incrementAndGet());

        // Create the AwardPointForEvaluationCommand
        AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO = awardPointForEvaluationCommandMapper.toDto(
            awardPointForEvaluationCommand
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAwardPointForEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, awardPointForEvaluationCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository.findAll();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().size();
        awardPointForEvaluationCommand.setId(count.incrementAndGet());

        // Create the AwardPointForEvaluationCommand
        AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO = awardPointForEvaluationCommandMapper.toDto(
            awardPointForEvaluationCommand
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAwardPointForEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository.findAll();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().size();
        awardPointForEvaluationCommand.setId(count.incrementAndGet());

        // Create the AwardPointForEvaluationCommand
        AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO = awardPointForEvaluationCommandMapper.toDto(
            awardPointForEvaluationCommand
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAwardPointForEvaluationCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository.findAll();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAwardPointForEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        awardPointForEvaluationCommandRepository.saveAndFlush(awardPointForEvaluationCommand);

        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().size();

        // Update the awardPointForEvaluationCommand using partial update
        AwardPointForEvaluationCommand partialUpdatedAwardPointForEvaluationCommand = new AwardPointForEvaluationCommand();
        partialUpdatedAwardPointForEvaluationCommand.setId(awardPointForEvaluationCommand.getId());

        restAwardPointForEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAwardPointForEvaluationCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAwardPointForEvaluationCommand))
            )
            .andExpect(status().isOk());

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository.findAll();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        AwardPointForEvaluationCommand testAwardPointForEvaluationCommand = awardPointForEvaluationCommandList.get(
            awardPointForEvaluationCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void fullUpdateAwardPointForEvaluationCommandWithPatch() throws Exception {
        // Initialize the database
        awardPointForEvaluationCommandRepository.saveAndFlush(awardPointForEvaluationCommand);

        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().size();

        // Update the awardPointForEvaluationCommand using partial update
        AwardPointForEvaluationCommand partialUpdatedAwardPointForEvaluationCommand = new AwardPointForEvaluationCommand();
        partialUpdatedAwardPointForEvaluationCommand.setId(awardPointForEvaluationCommand.getId());

        restAwardPointForEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAwardPointForEvaluationCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAwardPointForEvaluationCommand))
            )
            .andExpect(status().isOk());

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository.findAll();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
        AwardPointForEvaluationCommand testAwardPointForEvaluationCommand = awardPointForEvaluationCommandList.get(
            awardPointForEvaluationCommandList.size() - 1
        );
    }

    @Test
    @Transactional
    void patchNonExistingAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().size();
        awardPointForEvaluationCommand.setId(count.incrementAndGet());

        // Create the AwardPointForEvaluationCommand
        AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO = awardPointForEvaluationCommandMapper.toDto(
            awardPointForEvaluationCommand
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAwardPointForEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, awardPointForEvaluationCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository.findAll();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().size();
        awardPointForEvaluationCommand.setId(count.incrementAndGet());

        // Create the AwardPointForEvaluationCommand
        AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO = awardPointForEvaluationCommandMapper.toDto(
            awardPointForEvaluationCommand
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAwardPointForEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository.findAll();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAwardPointForEvaluationCommand() throws Exception {
        int databaseSizeBeforeUpdate = awardPointForEvaluationCommandRepository.findAll().size();
        awardPointForEvaluationCommand.setId(count.incrementAndGet());

        // Create the AwardPointForEvaluationCommand
        AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO = awardPointForEvaluationCommandMapper.toDto(
            awardPointForEvaluationCommand
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAwardPointForEvaluationCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(awardPointForEvaluationCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AwardPointForEvaluationCommand in the database
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository.findAll();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAwardPointForEvaluationCommand() throws Exception {
        // Initialize the database
        awardPointForEvaluationCommandRepository.saveAndFlush(awardPointForEvaluationCommand);

        int databaseSizeBeforeDelete = awardPointForEvaluationCommandRepository.findAll().size();

        // Delete the awardPointForEvaluationCommand
        restAwardPointForEvaluationCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, awardPointForEvaluationCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AwardPointForEvaluationCommand> awardPointForEvaluationCommandList = awardPointForEvaluationCommandRepository.findAll();
        assertThat(awardPointForEvaluationCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
