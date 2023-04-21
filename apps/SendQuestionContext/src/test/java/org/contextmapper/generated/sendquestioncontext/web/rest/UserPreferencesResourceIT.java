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
import org.contextmapper.generated.sendquestioncontext.domain.UserPreferences;
import org.contextmapper.generated.sendquestioncontext.repository.UserPreferencesRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserPreferencesDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.UserPreferencesMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserPreferencesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserPreferencesResourceIT {

    private static final String ENTITY_API_URL = "/api/user-preferences";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    @Autowired
    private UserPreferencesMapper userPreferencesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserPreferencesMockMvc;

    private UserPreferences userPreferences;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPreferences createEntity(EntityManager em) {
        UserPreferences userPreferences = new UserPreferences();
        return userPreferences;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPreferences createUpdatedEntity(EntityManager em) {
        UserPreferences userPreferences = new UserPreferences();
        return userPreferences;
    }

    @BeforeEach
    public void initTest() {
        userPreferences = createEntity(em);
    }

    @Test
    @Transactional
    void createUserPreferences() throws Exception {
        int databaseSizeBeforeCreate = userPreferencesRepository.findAll().size();
        // Create the UserPreferences
        UserPreferencesDTO userPreferencesDTO = userPreferencesMapper.toDto(userPreferences);
        restUserPreferencesMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userPreferencesDTO))
            )
            .andExpect(status().isCreated());

        // Validate the UserPreferences in the database
        List<UserPreferences> userPreferencesList = userPreferencesRepository.findAll();
        assertThat(userPreferencesList).hasSize(databaseSizeBeforeCreate + 1);
        UserPreferences testUserPreferences = userPreferencesList.get(userPreferencesList.size() - 1);
    }

    @Test
    @Transactional
    void createUserPreferencesWithExistingId() throws Exception {
        // Create the UserPreferences with an existing ID
        userPreferences.setId(1L);
        UserPreferencesDTO userPreferencesDTO = userPreferencesMapper.toDto(userPreferences);

        int databaseSizeBeforeCreate = userPreferencesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserPreferencesMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userPreferencesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserPreferences in the database
        List<UserPreferences> userPreferencesList = userPreferencesRepository.findAll();
        assertThat(userPreferencesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUserPreferences() throws Exception {
        // Initialize the database
        userPreferencesRepository.saveAndFlush(userPreferences);

        // Get all the userPreferencesList
        restUserPreferencesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userPreferences.getId().intValue())));
    }

    @Test
    @Transactional
    void getUserPreferences() throws Exception {
        // Initialize the database
        userPreferencesRepository.saveAndFlush(userPreferences);

        // Get the userPreferences
        restUserPreferencesMockMvc
            .perform(get(ENTITY_API_URL_ID, userPreferences.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userPreferences.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingUserPreferences() throws Exception {
        // Get the userPreferences
        restUserPreferencesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUserPreferences() throws Exception {
        // Initialize the database
        userPreferencesRepository.saveAndFlush(userPreferences);

        int databaseSizeBeforeUpdate = userPreferencesRepository.findAll().size();

        // Update the userPreferences
        UserPreferences updatedUserPreferences = userPreferencesRepository.findById(userPreferences.getId()).get();
        // Disconnect from session so that the updates on updatedUserPreferences are not directly saved in db
        em.detach(updatedUserPreferences);
        UserPreferencesDTO userPreferencesDTO = userPreferencesMapper.toDto(updatedUserPreferences);

        restUserPreferencesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userPreferencesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userPreferencesDTO))
            )
            .andExpect(status().isOk());

        // Validate the UserPreferences in the database
        List<UserPreferences> userPreferencesList = userPreferencesRepository.findAll();
        assertThat(userPreferencesList).hasSize(databaseSizeBeforeUpdate);
        UserPreferences testUserPreferences = userPreferencesList.get(userPreferencesList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingUserPreferences() throws Exception {
        int databaseSizeBeforeUpdate = userPreferencesRepository.findAll().size();
        userPreferences.setId(count.incrementAndGet());

        // Create the UserPreferences
        UserPreferencesDTO userPreferencesDTO = userPreferencesMapper.toDto(userPreferences);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserPreferencesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userPreferencesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userPreferencesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserPreferences in the database
        List<UserPreferences> userPreferencesList = userPreferencesRepository.findAll();
        assertThat(userPreferencesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUserPreferences() throws Exception {
        int databaseSizeBeforeUpdate = userPreferencesRepository.findAll().size();
        userPreferences.setId(count.incrementAndGet());

        // Create the UserPreferences
        UserPreferencesDTO userPreferencesDTO = userPreferencesMapper.toDto(userPreferences);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserPreferencesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userPreferencesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserPreferences in the database
        List<UserPreferences> userPreferencesList = userPreferencesRepository.findAll();
        assertThat(userPreferencesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUserPreferences() throws Exception {
        int databaseSizeBeforeUpdate = userPreferencesRepository.findAll().size();
        userPreferences.setId(count.incrementAndGet());

        // Create the UserPreferences
        UserPreferencesDTO userPreferencesDTO = userPreferencesMapper.toDto(userPreferences);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserPreferencesMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userPreferencesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserPreferences in the database
        List<UserPreferences> userPreferencesList = userPreferencesRepository.findAll();
        assertThat(userPreferencesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserPreferencesWithPatch() throws Exception {
        // Initialize the database
        userPreferencesRepository.saveAndFlush(userPreferences);

        int databaseSizeBeforeUpdate = userPreferencesRepository.findAll().size();

        // Update the userPreferences using partial update
        UserPreferences partialUpdatedUserPreferences = new UserPreferences();
        partialUpdatedUserPreferences.setId(userPreferences.getId());

        restUserPreferencesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserPreferences.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserPreferences))
            )
            .andExpect(status().isOk());

        // Validate the UserPreferences in the database
        List<UserPreferences> userPreferencesList = userPreferencesRepository.findAll();
        assertThat(userPreferencesList).hasSize(databaseSizeBeforeUpdate);
        UserPreferences testUserPreferences = userPreferencesList.get(userPreferencesList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateUserPreferencesWithPatch() throws Exception {
        // Initialize the database
        userPreferencesRepository.saveAndFlush(userPreferences);

        int databaseSizeBeforeUpdate = userPreferencesRepository.findAll().size();

        // Update the userPreferences using partial update
        UserPreferences partialUpdatedUserPreferences = new UserPreferences();
        partialUpdatedUserPreferences.setId(userPreferences.getId());

        restUserPreferencesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserPreferences.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserPreferences))
            )
            .andExpect(status().isOk());

        // Validate the UserPreferences in the database
        List<UserPreferences> userPreferencesList = userPreferencesRepository.findAll();
        assertThat(userPreferencesList).hasSize(databaseSizeBeforeUpdate);
        UserPreferences testUserPreferences = userPreferencesList.get(userPreferencesList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingUserPreferences() throws Exception {
        int databaseSizeBeforeUpdate = userPreferencesRepository.findAll().size();
        userPreferences.setId(count.incrementAndGet());

        // Create the UserPreferences
        UserPreferencesDTO userPreferencesDTO = userPreferencesMapper.toDto(userPreferences);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserPreferencesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, userPreferencesDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userPreferencesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserPreferences in the database
        List<UserPreferences> userPreferencesList = userPreferencesRepository.findAll();
        assertThat(userPreferencesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUserPreferences() throws Exception {
        int databaseSizeBeforeUpdate = userPreferencesRepository.findAll().size();
        userPreferences.setId(count.incrementAndGet());

        // Create the UserPreferences
        UserPreferencesDTO userPreferencesDTO = userPreferencesMapper.toDto(userPreferences);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserPreferencesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userPreferencesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserPreferences in the database
        List<UserPreferences> userPreferencesList = userPreferencesRepository.findAll();
        assertThat(userPreferencesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUserPreferences() throws Exception {
        int databaseSizeBeforeUpdate = userPreferencesRepository.findAll().size();
        userPreferences.setId(count.incrementAndGet());

        // Create the UserPreferences
        UserPreferencesDTO userPreferencesDTO = userPreferencesMapper.toDto(userPreferences);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserPreferencesMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userPreferencesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserPreferences in the database
        List<UserPreferences> userPreferencesList = userPreferencesRepository.findAll();
        assertThat(userPreferencesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUserPreferences() throws Exception {
        // Initialize the database
        userPreferencesRepository.saveAndFlush(userPreferences);

        int databaseSizeBeforeDelete = userPreferencesRepository.findAll().size();

        // Delete the userPreferences
        restUserPreferencesMockMvc
            .perform(delete(ENTITY_API_URL_ID, userPreferences.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserPreferences> userPreferencesList = userPreferencesRepository.findAll();
        assertThat(userPreferencesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
