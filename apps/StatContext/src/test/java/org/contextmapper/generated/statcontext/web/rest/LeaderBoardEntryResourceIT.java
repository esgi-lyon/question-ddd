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
import org.contextmapper.generated.statcontext.domain.LeaderBoardEntry;
import org.contextmapper.generated.statcontext.repository.LeaderBoardEntryRepository;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardEntryDTO;
import org.contextmapper.generated.statcontext.service.mapper.LeaderBoardEntryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link LeaderBoardEntryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LeaderBoardEntryResourceIT {

    private static final String DEFAULT_USER_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_USER_LEVEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    private static final String ENTITY_API_URL = "/api/leader-board-entries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LeaderBoardEntryRepository leaderBoardEntryRepository;

    @Autowired
    private LeaderBoardEntryMapper leaderBoardEntryMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLeaderBoardEntryMockMvc;

    private LeaderBoardEntry leaderBoardEntry;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeaderBoardEntry createEntity(EntityManager em) {
        LeaderBoardEntry leaderBoardEntry = new LeaderBoardEntry().userLevel(DEFAULT_USER_LEVEL).score(DEFAULT_SCORE);
        return leaderBoardEntry;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeaderBoardEntry createUpdatedEntity(EntityManager em) {
        LeaderBoardEntry leaderBoardEntry = new LeaderBoardEntry().userLevel(UPDATED_USER_LEVEL).score(UPDATED_SCORE);
        return leaderBoardEntry;
    }

    @BeforeEach
    public void initTest() {
        leaderBoardEntry = createEntity(em);
    }

    @Test
    @Transactional
    void createLeaderBoardEntry() throws Exception {
        int databaseSizeBeforeCreate = leaderBoardEntryRepository.findAll().size();
        // Create the LeaderBoardEntry
        LeaderBoardEntryDTO leaderBoardEntryDTO = leaderBoardEntryMapper.toDto(leaderBoardEntry);
        restLeaderBoardEntryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(leaderBoardEntryDTO))
            )
            .andExpect(status().isCreated());

        // Validate the LeaderBoardEntry in the database
        List<LeaderBoardEntry> leaderBoardEntryList = leaderBoardEntryRepository.findAll();
        assertThat(leaderBoardEntryList).hasSize(databaseSizeBeforeCreate + 1);
        LeaderBoardEntry testLeaderBoardEntry = leaderBoardEntryList.get(leaderBoardEntryList.size() - 1);
        assertThat(testLeaderBoardEntry.getUserLevel()).isEqualTo(DEFAULT_USER_LEVEL);
        assertThat(testLeaderBoardEntry.getScore()).isEqualTo(DEFAULT_SCORE);
    }

    @Test
    @Transactional
    void createLeaderBoardEntryWithExistingId() throws Exception {
        // Create the LeaderBoardEntry with an existing ID
        leaderBoardEntry.setId(1L);
        LeaderBoardEntryDTO leaderBoardEntryDTO = leaderBoardEntryMapper.toDto(leaderBoardEntry);

        int databaseSizeBeforeCreate = leaderBoardEntryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLeaderBoardEntryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(leaderBoardEntryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaderBoardEntry in the database
        List<LeaderBoardEntry> leaderBoardEntryList = leaderBoardEntryRepository.findAll();
        assertThat(leaderBoardEntryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLeaderBoardEntries() throws Exception {
        // Initialize the database
        leaderBoardEntryRepository.saveAndFlush(leaderBoardEntry);

        // Get all the leaderBoardEntryList
        restLeaderBoardEntryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leaderBoardEntry.getId().intValue())))
            .andExpect(jsonPath("$.[*].userLevel").value(hasItem(DEFAULT_USER_LEVEL)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)));
    }

    @Test
    @Transactional
    void getLeaderBoardEntry() throws Exception {
        // Initialize the database
        leaderBoardEntryRepository.saveAndFlush(leaderBoardEntry);

        // Get the leaderBoardEntry
        restLeaderBoardEntryMockMvc
            .perform(get(ENTITY_API_URL_ID, leaderBoardEntry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(leaderBoardEntry.getId().intValue()))
            .andExpect(jsonPath("$.userLevel").value(DEFAULT_USER_LEVEL))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE));
    }

    @Test
    @Transactional
    void getNonExistingLeaderBoardEntry() throws Exception {
        // Get the leaderBoardEntry
        restLeaderBoardEntryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLeaderBoardEntry() throws Exception {
        // Initialize the database
        leaderBoardEntryRepository.saveAndFlush(leaderBoardEntry);

        int databaseSizeBeforeUpdate = leaderBoardEntryRepository.findAll().size();

        // Update the leaderBoardEntry
        LeaderBoardEntry updatedLeaderBoardEntry = leaderBoardEntryRepository.findById(leaderBoardEntry.getId()).get();
        // Disconnect from session so that the updates on updatedLeaderBoardEntry are not directly saved in db
        em.detach(updatedLeaderBoardEntry);
        updatedLeaderBoardEntry.userLevel(UPDATED_USER_LEVEL).score(UPDATED_SCORE);
        LeaderBoardEntryDTO leaderBoardEntryDTO = leaderBoardEntryMapper.toDto(updatedLeaderBoardEntry);

        restLeaderBoardEntryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, leaderBoardEntryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(leaderBoardEntryDTO))
            )
            .andExpect(status().isOk());

        // Validate the LeaderBoardEntry in the database
        List<LeaderBoardEntry> leaderBoardEntryList = leaderBoardEntryRepository.findAll();
        assertThat(leaderBoardEntryList).hasSize(databaseSizeBeforeUpdate);
        LeaderBoardEntry testLeaderBoardEntry = leaderBoardEntryList.get(leaderBoardEntryList.size() - 1);
        assertThat(testLeaderBoardEntry.getUserLevel()).isEqualTo(UPDATED_USER_LEVEL);
        assertThat(testLeaderBoardEntry.getScore()).isEqualTo(UPDATED_SCORE);
    }

    @Test
    @Transactional
    void putNonExistingLeaderBoardEntry() throws Exception {
        int databaseSizeBeforeUpdate = leaderBoardEntryRepository.findAll().size();
        leaderBoardEntry.setId(count.incrementAndGet());

        // Create the LeaderBoardEntry
        LeaderBoardEntryDTO leaderBoardEntryDTO = leaderBoardEntryMapper.toDto(leaderBoardEntry);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeaderBoardEntryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, leaderBoardEntryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(leaderBoardEntryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaderBoardEntry in the database
        List<LeaderBoardEntry> leaderBoardEntryList = leaderBoardEntryRepository.findAll();
        assertThat(leaderBoardEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLeaderBoardEntry() throws Exception {
        int databaseSizeBeforeUpdate = leaderBoardEntryRepository.findAll().size();
        leaderBoardEntry.setId(count.incrementAndGet());

        // Create the LeaderBoardEntry
        LeaderBoardEntryDTO leaderBoardEntryDTO = leaderBoardEntryMapper.toDto(leaderBoardEntry);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaderBoardEntryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(leaderBoardEntryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaderBoardEntry in the database
        List<LeaderBoardEntry> leaderBoardEntryList = leaderBoardEntryRepository.findAll();
        assertThat(leaderBoardEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLeaderBoardEntry() throws Exception {
        int databaseSizeBeforeUpdate = leaderBoardEntryRepository.findAll().size();
        leaderBoardEntry.setId(count.incrementAndGet());

        // Create the LeaderBoardEntry
        LeaderBoardEntryDTO leaderBoardEntryDTO = leaderBoardEntryMapper.toDto(leaderBoardEntry);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaderBoardEntryMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(leaderBoardEntryDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LeaderBoardEntry in the database
        List<LeaderBoardEntry> leaderBoardEntryList = leaderBoardEntryRepository.findAll();
        assertThat(leaderBoardEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLeaderBoardEntryWithPatch() throws Exception {
        // Initialize the database
        leaderBoardEntryRepository.saveAndFlush(leaderBoardEntry);

        int databaseSizeBeforeUpdate = leaderBoardEntryRepository.findAll().size();

        // Update the leaderBoardEntry using partial update
        LeaderBoardEntry partialUpdatedLeaderBoardEntry = new LeaderBoardEntry();
        partialUpdatedLeaderBoardEntry.setId(leaderBoardEntry.getId());

        partialUpdatedLeaderBoardEntry.userLevel(UPDATED_USER_LEVEL).score(UPDATED_SCORE);

        restLeaderBoardEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLeaderBoardEntry.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLeaderBoardEntry))
            )
            .andExpect(status().isOk());

        // Validate the LeaderBoardEntry in the database
        List<LeaderBoardEntry> leaderBoardEntryList = leaderBoardEntryRepository.findAll();
        assertThat(leaderBoardEntryList).hasSize(databaseSizeBeforeUpdate);
        LeaderBoardEntry testLeaderBoardEntry = leaderBoardEntryList.get(leaderBoardEntryList.size() - 1);
        assertThat(testLeaderBoardEntry.getUserLevel()).isEqualTo(UPDATED_USER_LEVEL);
        assertThat(testLeaderBoardEntry.getScore()).isEqualTo(UPDATED_SCORE);
    }

    @Test
    @Transactional
    void fullUpdateLeaderBoardEntryWithPatch() throws Exception {
        // Initialize the database
        leaderBoardEntryRepository.saveAndFlush(leaderBoardEntry);

        int databaseSizeBeforeUpdate = leaderBoardEntryRepository.findAll().size();

        // Update the leaderBoardEntry using partial update
        LeaderBoardEntry partialUpdatedLeaderBoardEntry = new LeaderBoardEntry();
        partialUpdatedLeaderBoardEntry.setId(leaderBoardEntry.getId());

        partialUpdatedLeaderBoardEntry.userLevel(UPDATED_USER_LEVEL).score(UPDATED_SCORE);

        restLeaderBoardEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLeaderBoardEntry.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLeaderBoardEntry))
            )
            .andExpect(status().isOk());

        // Validate the LeaderBoardEntry in the database
        List<LeaderBoardEntry> leaderBoardEntryList = leaderBoardEntryRepository.findAll();
        assertThat(leaderBoardEntryList).hasSize(databaseSizeBeforeUpdate);
        LeaderBoardEntry testLeaderBoardEntry = leaderBoardEntryList.get(leaderBoardEntryList.size() - 1);
        assertThat(testLeaderBoardEntry.getUserLevel()).isEqualTo(UPDATED_USER_LEVEL);
        assertThat(testLeaderBoardEntry.getScore()).isEqualTo(UPDATED_SCORE);
    }

    @Test
    @Transactional
    void patchNonExistingLeaderBoardEntry() throws Exception {
        int databaseSizeBeforeUpdate = leaderBoardEntryRepository.findAll().size();
        leaderBoardEntry.setId(count.incrementAndGet());

        // Create the LeaderBoardEntry
        LeaderBoardEntryDTO leaderBoardEntryDTO = leaderBoardEntryMapper.toDto(leaderBoardEntry);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeaderBoardEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, leaderBoardEntryDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(leaderBoardEntryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaderBoardEntry in the database
        List<LeaderBoardEntry> leaderBoardEntryList = leaderBoardEntryRepository.findAll();
        assertThat(leaderBoardEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLeaderBoardEntry() throws Exception {
        int databaseSizeBeforeUpdate = leaderBoardEntryRepository.findAll().size();
        leaderBoardEntry.setId(count.incrementAndGet());

        // Create the LeaderBoardEntry
        LeaderBoardEntryDTO leaderBoardEntryDTO = leaderBoardEntryMapper.toDto(leaderBoardEntry);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaderBoardEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(leaderBoardEntryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaderBoardEntry in the database
        List<LeaderBoardEntry> leaderBoardEntryList = leaderBoardEntryRepository.findAll();
        assertThat(leaderBoardEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLeaderBoardEntry() throws Exception {
        int databaseSizeBeforeUpdate = leaderBoardEntryRepository.findAll().size();
        leaderBoardEntry.setId(count.incrementAndGet());

        // Create the LeaderBoardEntry
        LeaderBoardEntryDTO leaderBoardEntryDTO = leaderBoardEntryMapper.toDto(leaderBoardEntry);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaderBoardEntryMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(leaderBoardEntryDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LeaderBoardEntry in the database
        List<LeaderBoardEntry> leaderBoardEntryList = leaderBoardEntryRepository.findAll();
        assertThat(leaderBoardEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLeaderBoardEntry() throws Exception {
        // Initialize the database
        leaderBoardEntryRepository.saveAndFlush(leaderBoardEntry);

        int databaseSizeBeforeDelete = leaderBoardEntryRepository.findAll().size();

        // Delete the leaderBoardEntry
        restLeaderBoardEntryMockMvc
            .perform(delete(ENTITY_API_URL_ID, leaderBoardEntry.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LeaderBoardEntry> leaderBoardEntryList = leaderBoardEntryRepository.findAll();
        assertThat(leaderBoardEntryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
