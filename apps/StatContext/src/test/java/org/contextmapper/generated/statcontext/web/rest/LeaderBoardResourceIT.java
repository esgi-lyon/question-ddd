package org.contextmapper.generated.statcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.statcontext.IntegrationTest;
import org.contextmapper.generated.statcontext.domain.LeaderBoard;
import org.contextmapper.generated.statcontext.repository.LeaderBoardRepository;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardDTO;
import org.contextmapper.generated.statcontext.service.mapper.LeaderBoardMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link LeaderBoardResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LeaderBoardResourceIT {

    private static final String ENTITY_API_URL = "/api/leader-boards";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LeaderBoardRepository leaderBoardRepository;

    @Autowired
    private LeaderBoardMapper leaderBoardMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLeaderBoardMockMvc;

    private LeaderBoard leaderBoard;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeaderBoard createEntity(EntityManager em) {
        LeaderBoard leaderBoard = new LeaderBoard();
        return leaderBoard;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeaderBoard createUpdatedEntity(EntityManager em) {
        LeaderBoard leaderBoard = new LeaderBoard();
        return leaderBoard;
    }

    @BeforeEach
    public void initTest() {
        leaderBoard = createEntity(em);
    }

    @Test
    @Transactional
    void createLeaderBoard() throws Exception {
        int databaseSizeBeforeCreate = leaderBoardRepository.findAll().size();
        // Create the LeaderBoard
        LeaderBoardDTO leaderBoardDTO = leaderBoardMapper.toDto(leaderBoard);
        restLeaderBoardMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(leaderBoardDTO))
            )
            .andExpect(status().isCreated());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeCreate + 1);
        LeaderBoard testLeaderBoard = leaderBoardList.get(leaderBoardList.size() - 1);
    }

    @Test
    @Transactional
    void createLeaderBoardWithExistingId() throws Exception {
        // Create the LeaderBoard with an existing ID
        leaderBoard.setId(1L);
        LeaderBoardDTO leaderBoardDTO = leaderBoardMapper.toDto(leaderBoard);

        int databaseSizeBeforeCreate = leaderBoardRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLeaderBoardMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(leaderBoardDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLeaderBoards() throws Exception {
        // Initialize the database
        leaderBoardRepository.saveAndFlush(leaderBoard);

        // Get all the leaderBoardList
        restLeaderBoardMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leaderBoard.getId().intValue())));
    }

    @Test
    @Transactional
    void getLeaderBoard() throws Exception {
        // Initialize the database
        leaderBoardRepository.saveAndFlush(leaderBoard);

        // Get the leaderBoard
        restLeaderBoardMockMvc
            .perform(get(ENTITY_API_URL_ID, leaderBoard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(leaderBoard.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingLeaderBoard() throws Exception {
        // Get the leaderBoard
        restLeaderBoardMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLeaderBoard() throws Exception {
        // Initialize the database
        leaderBoardRepository.saveAndFlush(leaderBoard);

        int databaseSizeBeforeUpdate = leaderBoardRepository.findAll().size();

        // Update the leaderBoard
        LeaderBoard updatedLeaderBoard = leaderBoardRepository.findById(leaderBoard.getId()).get();
        // Disconnect from session so that the updates on updatedLeaderBoard are not directly saved in db
        em.detach(updatedLeaderBoard);
        LeaderBoardDTO leaderBoardDTO = leaderBoardMapper.toDto(updatedLeaderBoard);

        restLeaderBoardMockMvc
            .perform(
                put(ENTITY_API_URL_ID, leaderBoardDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(leaderBoardDTO))
            )
            .andExpect(status().isOk());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeUpdate);
        LeaderBoard testLeaderBoard = leaderBoardList.get(leaderBoardList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingLeaderBoard() throws Exception {
        int databaseSizeBeforeUpdate = leaderBoardRepository.findAll().size();
        leaderBoard.setId(count.incrementAndGet());

        // Create the LeaderBoard
        LeaderBoardDTO leaderBoardDTO = leaderBoardMapper.toDto(leaderBoard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeaderBoardMockMvc
            .perform(
                put(ENTITY_API_URL_ID, leaderBoardDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(leaderBoardDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLeaderBoard() throws Exception {
        int databaseSizeBeforeUpdate = leaderBoardRepository.findAll().size();
        leaderBoard.setId(count.incrementAndGet());

        // Create the LeaderBoard
        LeaderBoardDTO leaderBoardDTO = leaderBoardMapper.toDto(leaderBoard);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaderBoardMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(leaderBoardDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLeaderBoard() throws Exception {
        int databaseSizeBeforeUpdate = leaderBoardRepository.findAll().size();
        leaderBoard.setId(count.incrementAndGet());

        // Create the LeaderBoard
        LeaderBoardDTO leaderBoardDTO = leaderBoardMapper.toDto(leaderBoard);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaderBoardMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(leaderBoardDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLeaderBoardWithPatch() throws Exception {
        // Initialize the database
        leaderBoardRepository.saveAndFlush(leaderBoard);

        int databaseSizeBeforeUpdate = leaderBoardRepository.findAll().size();

        // Update the leaderBoard using partial update
        LeaderBoard partialUpdatedLeaderBoard = new LeaderBoard();
        partialUpdatedLeaderBoard.setId(leaderBoard.getId());

        restLeaderBoardMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLeaderBoard.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLeaderBoard))
            )
            .andExpect(status().isOk());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeUpdate);
        LeaderBoard testLeaderBoard = leaderBoardList.get(leaderBoardList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateLeaderBoardWithPatch() throws Exception {
        // Initialize the database
        leaderBoardRepository.saveAndFlush(leaderBoard);

        int databaseSizeBeforeUpdate = leaderBoardRepository.findAll().size();

        // Update the leaderBoard using partial update
        LeaderBoard partialUpdatedLeaderBoard = new LeaderBoard();
        partialUpdatedLeaderBoard.setId(leaderBoard.getId());

        restLeaderBoardMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLeaderBoard.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLeaderBoard))
            )
            .andExpect(status().isOk());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeUpdate);
        LeaderBoard testLeaderBoard = leaderBoardList.get(leaderBoardList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingLeaderBoard() throws Exception {
        int databaseSizeBeforeUpdate = leaderBoardRepository.findAll().size();
        leaderBoard.setId(count.incrementAndGet());

        // Create the LeaderBoard
        LeaderBoardDTO leaderBoardDTO = leaderBoardMapper.toDto(leaderBoard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeaderBoardMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, leaderBoardDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(leaderBoardDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLeaderBoard() throws Exception {
        int databaseSizeBeforeUpdate = leaderBoardRepository.findAll().size();
        leaderBoard.setId(count.incrementAndGet());

        // Create the LeaderBoard
        LeaderBoardDTO leaderBoardDTO = leaderBoardMapper.toDto(leaderBoard);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaderBoardMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(leaderBoardDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLeaderBoard() throws Exception {
        int databaseSizeBeforeUpdate = leaderBoardRepository.findAll().size();
        leaderBoard.setId(count.incrementAndGet());

        // Create the LeaderBoard
        LeaderBoardDTO leaderBoardDTO = leaderBoardMapper.toDto(leaderBoard);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaderBoardMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(leaderBoardDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLeaderBoard() throws Exception {
        // Initialize the database
        leaderBoardRepository.saveAndFlush(leaderBoard);

        int databaseSizeBeforeDelete = leaderBoardRepository.findAll().size();

        // Delete the leaderBoard
        restLeaderBoardMockMvc
            .perform(delete(ENTITY_API_URL_ID, leaderBoard.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
