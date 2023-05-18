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
import org.contextmapper.generated.statcontext.domain.UserStatsViewed;
import org.contextmapper.generated.statcontext.repository.UserStatsViewedRepository;
import org.contextmapper.generated.statcontext.service.dto.UserStatsViewedDTO;
import org.contextmapper.generated.statcontext.service.mapper.UserStatsViewedMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserStatsViewedResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserStatsViewedResourceIT {

    private static final String ENTITY_API_URL = "/api/user-stats-vieweds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserStatsViewedRepository userStatsViewedRepository;

    @Autowired
    private UserStatsViewedMapper userStatsViewedMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserStatsViewedMockMvc;

    private UserStatsViewed userStatsViewed;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserStatsViewed createEntity(EntityManager em) {
        UserStatsViewed userStatsViewed = new UserStatsViewed();
        return userStatsViewed;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserStatsViewed createUpdatedEntity(EntityManager em) {
        UserStatsViewed userStatsViewed = new UserStatsViewed();
        return userStatsViewed;
    }

    @BeforeEach
    public void initTest() {
        userStatsViewed = createEntity(em);
    }

    @Test
    @Transactional
    void createUserStatsViewed() throws Exception {
        int databaseSizeBeforeCreate = userStatsViewedRepository.findAll().size();
        // Create the UserStatsViewed
        UserStatsViewedDTO userStatsViewedDTO = userStatsViewedMapper.toDto(userStatsViewed);
        restUserStatsViewedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userStatsViewedDTO))
            )
            .andExpect(status().isCreated());

        // Validate the UserStatsViewed in the database
        List<UserStatsViewed> userStatsViewedList = userStatsViewedRepository.findAll();
        assertThat(userStatsViewedList).hasSize(databaseSizeBeforeCreate + 1);
        UserStatsViewed testUserStatsViewed = userStatsViewedList.get(userStatsViewedList.size() - 1);
    }

    @Test
    @Transactional
    void createUserStatsViewedWithExistingId() throws Exception {
        // Create the UserStatsViewed with an existing ID
        userStatsViewed.setId(1L);
        UserStatsViewedDTO userStatsViewedDTO = userStatsViewedMapper.toDto(userStatsViewed);

        int databaseSizeBeforeCreate = userStatsViewedRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserStatsViewedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserStatsViewed in the database
        List<UserStatsViewed> userStatsViewedList = userStatsViewedRepository.findAll();
        assertThat(userStatsViewedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUserStatsVieweds() throws Exception {
        // Initialize the database
        userStatsViewedRepository.saveAndFlush(userStatsViewed);

        // Get all the userStatsViewedList
        restUserStatsViewedMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userStatsViewed.getId().intValue())));
    }

    @Test
    @Transactional
    void getUserStatsViewed() throws Exception {
        // Initialize the database
        userStatsViewedRepository.saveAndFlush(userStatsViewed);

        // Get the userStatsViewed
        restUserStatsViewedMockMvc
            .perform(get(ENTITY_API_URL_ID, userStatsViewed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userStatsViewed.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingUserStatsViewed() throws Exception {
        // Get the userStatsViewed
        restUserStatsViewedMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUserStatsViewed() throws Exception {
        // Initialize the database
        userStatsViewedRepository.saveAndFlush(userStatsViewed);

        int databaseSizeBeforeUpdate = userStatsViewedRepository.findAll().size();

        // Update the userStatsViewed
        UserStatsViewed updatedUserStatsViewed = userStatsViewedRepository.findById(userStatsViewed.getId()).get();
        // Disconnect from session so that the updates on updatedUserStatsViewed are not directly saved in db
        em.detach(updatedUserStatsViewed);
        UserStatsViewedDTO userStatsViewedDTO = userStatsViewedMapper.toDto(updatedUserStatsViewed);

        restUserStatsViewedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userStatsViewedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userStatsViewedDTO))
            )
            .andExpect(status().isOk());

        // Validate the UserStatsViewed in the database
        List<UserStatsViewed> userStatsViewedList = userStatsViewedRepository.findAll();
        assertThat(userStatsViewedList).hasSize(databaseSizeBeforeUpdate);
        UserStatsViewed testUserStatsViewed = userStatsViewedList.get(userStatsViewedList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingUserStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = userStatsViewedRepository.findAll().size();
        userStatsViewed.setId(count.incrementAndGet());

        // Create the UserStatsViewed
        UserStatsViewedDTO userStatsViewedDTO = userStatsViewedMapper.toDto(userStatsViewed);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserStatsViewedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userStatsViewedDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserStatsViewed in the database
        List<UserStatsViewed> userStatsViewedList = userStatsViewedRepository.findAll();
        assertThat(userStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUserStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = userStatsViewedRepository.findAll().size();
        userStatsViewed.setId(count.incrementAndGet());

        // Create the UserStatsViewed
        UserStatsViewedDTO userStatsViewedDTO = userStatsViewedMapper.toDto(userStatsViewed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserStatsViewedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserStatsViewed in the database
        List<UserStatsViewed> userStatsViewedList = userStatsViewedRepository.findAll();
        assertThat(userStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUserStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = userStatsViewedRepository.findAll().size();
        userStatsViewed.setId(count.incrementAndGet());

        // Create the UserStatsViewed
        UserStatsViewedDTO userStatsViewedDTO = userStatsViewedMapper.toDto(userStatsViewed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserStatsViewedMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userStatsViewedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserStatsViewed in the database
        List<UserStatsViewed> userStatsViewedList = userStatsViewedRepository.findAll();
        assertThat(userStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserStatsViewedWithPatch() throws Exception {
        // Initialize the database
        userStatsViewedRepository.saveAndFlush(userStatsViewed);

        int databaseSizeBeforeUpdate = userStatsViewedRepository.findAll().size();

        // Update the userStatsViewed using partial update
        UserStatsViewed partialUpdatedUserStatsViewed = new UserStatsViewed();
        partialUpdatedUserStatsViewed.setId(userStatsViewed.getId());

        restUserStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserStatsViewed.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserStatsViewed))
            )
            .andExpect(status().isOk());

        // Validate the UserStatsViewed in the database
        List<UserStatsViewed> userStatsViewedList = userStatsViewedRepository.findAll();
        assertThat(userStatsViewedList).hasSize(databaseSizeBeforeUpdate);
        UserStatsViewed testUserStatsViewed = userStatsViewedList.get(userStatsViewedList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateUserStatsViewedWithPatch() throws Exception {
        // Initialize the database
        userStatsViewedRepository.saveAndFlush(userStatsViewed);

        int databaseSizeBeforeUpdate = userStatsViewedRepository.findAll().size();

        // Update the userStatsViewed using partial update
        UserStatsViewed partialUpdatedUserStatsViewed = new UserStatsViewed();
        partialUpdatedUserStatsViewed.setId(userStatsViewed.getId());

        restUserStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserStatsViewed.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserStatsViewed))
            )
            .andExpect(status().isOk());

        // Validate the UserStatsViewed in the database
        List<UserStatsViewed> userStatsViewedList = userStatsViewedRepository.findAll();
        assertThat(userStatsViewedList).hasSize(databaseSizeBeforeUpdate);
        UserStatsViewed testUserStatsViewed = userStatsViewedList.get(userStatsViewedList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingUserStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = userStatsViewedRepository.findAll().size();
        userStatsViewed.setId(count.incrementAndGet());

        // Create the UserStatsViewed
        UserStatsViewedDTO userStatsViewedDTO = userStatsViewedMapper.toDto(userStatsViewed);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, userStatsViewedDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserStatsViewed in the database
        List<UserStatsViewed> userStatsViewedList = userStatsViewedRepository.findAll();
        assertThat(userStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUserStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = userStatsViewedRepository.findAll().size();
        userStatsViewed.setId(count.incrementAndGet());

        // Create the UserStatsViewed
        UserStatsViewedDTO userStatsViewedDTO = userStatsViewedMapper.toDto(userStatsViewed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userStatsViewedDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserStatsViewed in the database
        List<UserStatsViewed> userStatsViewedList = userStatsViewedRepository.findAll();
        assertThat(userStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUserStatsViewed() throws Exception {
        int databaseSizeBeforeUpdate = userStatsViewedRepository.findAll().size();
        userStatsViewed.setId(count.incrementAndGet());

        // Create the UserStatsViewed
        UserStatsViewedDTO userStatsViewedDTO = userStatsViewedMapper.toDto(userStatsViewed);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserStatsViewedMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userStatsViewedDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserStatsViewed in the database
        List<UserStatsViewed> userStatsViewedList = userStatsViewedRepository.findAll();
        assertThat(userStatsViewedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUserStatsViewed() throws Exception {
        // Initialize the database
        userStatsViewedRepository.saveAndFlush(userStatsViewed);

        int databaseSizeBeforeDelete = userStatsViewedRepository.findAll().size();

        // Delete the userStatsViewed
        restUserStatsViewedMockMvc
            .perform(delete(ENTITY_API_URL_ID, userStatsViewed.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserStatsViewed> userStatsViewedList = userStatsViewedRepository.findAll();
        assertThat(userStatsViewedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
