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
import org.contextmapper.generated.evaluationcontext.domain.UserAndLevel;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.UserLevel;
import org.contextmapper.generated.evaluationcontext.repository.UserAndLevelRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.UserAndLevelDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.UserAndLevelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserAndLevelResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserAndLevelResourceIT {

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final UserLevel DEFAULT_USER_LEVEL = UserLevel.NEW;
    private static final UserLevel UPDATED_USER_LEVEL = UserLevel.REGULAR;

    private static final String ENTITY_API_URL = "/api/user-and-levels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserAndLevelRepository userAndLevelRepository;

    @Autowired
    private UserAndLevelMapper userAndLevelMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserAndLevelMockMvc;

    private UserAndLevel userAndLevel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserAndLevel createEntity(EntityManager em) {
        UserAndLevel userAndLevel = new UserAndLevel().mail(DEFAULT_MAIL).userLevel(DEFAULT_USER_LEVEL);
        return userAndLevel;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserAndLevel createUpdatedEntity(EntityManager em) {
        UserAndLevel userAndLevel = new UserAndLevel().mail(UPDATED_MAIL).userLevel(UPDATED_USER_LEVEL);
        return userAndLevel;
    }

    @BeforeEach
    public void initTest() {
        userAndLevel = createEntity(em);
    }

    @Test
    @Transactional
    void createUserAndLevel() throws Exception {
        int databaseSizeBeforeCreate = userAndLevelRepository.findAll().size();
        // Create the UserAndLevel
        UserAndLevelDTO userAndLevelDTO = userAndLevelMapper.toDto(userAndLevel);
        restUserAndLevelMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userAndLevelDTO))
            )
            .andExpect(status().isCreated());

        // Validate the UserAndLevel in the database
        List<UserAndLevel> userAndLevelList = userAndLevelRepository.findAll();
        assertThat(userAndLevelList).hasSize(databaseSizeBeforeCreate + 1);
        UserAndLevel testUserAndLevel = userAndLevelList.get(userAndLevelList.size() - 1);
        assertThat(testUserAndLevel.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testUserAndLevel.getUserLevel()).isEqualTo(DEFAULT_USER_LEVEL);
    }

    @Test
    @Transactional
    void createUserAndLevelWithExistingId() throws Exception {
        // Create the UserAndLevel with an existing ID
        userAndLevel.setId(1L);
        UserAndLevelDTO userAndLevelDTO = userAndLevelMapper.toDto(userAndLevel);

        int databaseSizeBeforeCreate = userAndLevelRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserAndLevelMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userAndLevelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserAndLevel in the database
        List<UserAndLevel> userAndLevelList = userAndLevelRepository.findAll();
        assertThat(userAndLevelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUserAndLevels() throws Exception {
        // Initialize the database
        userAndLevelRepository.saveAndFlush(userAndLevel);

        // Get all the userAndLevelList
        restUserAndLevelMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userAndLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)))
            .andExpect(jsonPath("$.[*].userLevel").value(hasItem(DEFAULT_USER_LEVEL.toString())));
    }

    @Test
    @Transactional
    void getUserAndLevel() throws Exception {
        // Initialize the database
        userAndLevelRepository.saveAndFlush(userAndLevel);

        // Get the userAndLevel
        restUserAndLevelMockMvc
            .perform(get(ENTITY_API_URL_ID, userAndLevel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userAndLevel.getId().intValue()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL))
            .andExpect(jsonPath("$.userLevel").value(DEFAULT_USER_LEVEL.toString()));
    }

    @Test
    @Transactional
    void getNonExistingUserAndLevel() throws Exception {
        // Get the userAndLevel
        restUserAndLevelMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUserAndLevel() throws Exception {
        // Initialize the database
        userAndLevelRepository.saveAndFlush(userAndLevel);

        int databaseSizeBeforeUpdate = userAndLevelRepository.findAll().size();

        // Update the userAndLevel
        UserAndLevel updatedUserAndLevel = userAndLevelRepository.findById(userAndLevel.getId()).get();
        // Disconnect from session so that the updates on updatedUserAndLevel are not directly saved in db
        em.detach(updatedUserAndLevel);
        updatedUserAndLevel.mail(UPDATED_MAIL).userLevel(UPDATED_USER_LEVEL);
        UserAndLevelDTO userAndLevelDTO = userAndLevelMapper.toDto(updatedUserAndLevel);

        restUserAndLevelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userAndLevelDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userAndLevelDTO))
            )
            .andExpect(status().isOk());

        // Validate the UserAndLevel in the database
        List<UserAndLevel> userAndLevelList = userAndLevelRepository.findAll();
        assertThat(userAndLevelList).hasSize(databaseSizeBeforeUpdate);
        UserAndLevel testUserAndLevel = userAndLevelList.get(userAndLevelList.size() - 1);
        assertThat(testUserAndLevel.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testUserAndLevel.getUserLevel()).isEqualTo(UPDATED_USER_LEVEL);
    }

    @Test
    @Transactional
    void putNonExistingUserAndLevel() throws Exception {
        int databaseSizeBeforeUpdate = userAndLevelRepository.findAll().size();
        userAndLevel.setId(count.incrementAndGet());

        // Create the UserAndLevel
        UserAndLevelDTO userAndLevelDTO = userAndLevelMapper.toDto(userAndLevel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserAndLevelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userAndLevelDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userAndLevelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserAndLevel in the database
        List<UserAndLevel> userAndLevelList = userAndLevelRepository.findAll();
        assertThat(userAndLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUserAndLevel() throws Exception {
        int databaseSizeBeforeUpdate = userAndLevelRepository.findAll().size();
        userAndLevel.setId(count.incrementAndGet());

        // Create the UserAndLevel
        UserAndLevelDTO userAndLevelDTO = userAndLevelMapper.toDto(userAndLevel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserAndLevelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userAndLevelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserAndLevel in the database
        List<UserAndLevel> userAndLevelList = userAndLevelRepository.findAll();
        assertThat(userAndLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUserAndLevel() throws Exception {
        int databaseSizeBeforeUpdate = userAndLevelRepository.findAll().size();
        userAndLevel.setId(count.incrementAndGet());

        // Create the UserAndLevel
        UserAndLevelDTO userAndLevelDTO = userAndLevelMapper.toDto(userAndLevel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserAndLevelMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userAndLevelDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserAndLevel in the database
        List<UserAndLevel> userAndLevelList = userAndLevelRepository.findAll();
        assertThat(userAndLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserAndLevelWithPatch() throws Exception {
        // Initialize the database
        userAndLevelRepository.saveAndFlush(userAndLevel);

        int databaseSizeBeforeUpdate = userAndLevelRepository.findAll().size();

        // Update the userAndLevel using partial update
        UserAndLevel partialUpdatedUserAndLevel = new UserAndLevel();
        partialUpdatedUserAndLevel.setId(userAndLevel.getId());

        partialUpdatedUserAndLevel.mail(UPDATED_MAIL);

        restUserAndLevelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserAndLevel.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserAndLevel))
            )
            .andExpect(status().isOk());

        // Validate the UserAndLevel in the database
        List<UserAndLevel> userAndLevelList = userAndLevelRepository.findAll();
        assertThat(userAndLevelList).hasSize(databaseSizeBeforeUpdate);
        UserAndLevel testUserAndLevel = userAndLevelList.get(userAndLevelList.size() - 1);
        assertThat(testUserAndLevel.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testUserAndLevel.getUserLevel()).isEqualTo(DEFAULT_USER_LEVEL);
    }

    @Test
    @Transactional
    void fullUpdateUserAndLevelWithPatch() throws Exception {
        // Initialize the database
        userAndLevelRepository.saveAndFlush(userAndLevel);

        int databaseSizeBeforeUpdate = userAndLevelRepository.findAll().size();

        // Update the userAndLevel using partial update
        UserAndLevel partialUpdatedUserAndLevel = new UserAndLevel();
        partialUpdatedUserAndLevel.setId(userAndLevel.getId());

        partialUpdatedUserAndLevel.mail(UPDATED_MAIL).userLevel(UPDATED_USER_LEVEL);

        restUserAndLevelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserAndLevel.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserAndLevel))
            )
            .andExpect(status().isOk());

        // Validate the UserAndLevel in the database
        List<UserAndLevel> userAndLevelList = userAndLevelRepository.findAll();
        assertThat(userAndLevelList).hasSize(databaseSizeBeforeUpdate);
        UserAndLevel testUserAndLevel = userAndLevelList.get(userAndLevelList.size() - 1);
        assertThat(testUserAndLevel.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testUserAndLevel.getUserLevel()).isEqualTo(UPDATED_USER_LEVEL);
    }

    @Test
    @Transactional
    void patchNonExistingUserAndLevel() throws Exception {
        int databaseSizeBeforeUpdate = userAndLevelRepository.findAll().size();
        userAndLevel.setId(count.incrementAndGet());

        // Create the UserAndLevel
        UserAndLevelDTO userAndLevelDTO = userAndLevelMapper.toDto(userAndLevel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserAndLevelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, userAndLevelDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userAndLevelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserAndLevel in the database
        List<UserAndLevel> userAndLevelList = userAndLevelRepository.findAll();
        assertThat(userAndLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUserAndLevel() throws Exception {
        int databaseSizeBeforeUpdate = userAndLevelRepository.findAll().size();
        userAndLevel.setId(count.incrementAndGet());

        // Create the UserAndLevel
        UserAndLevelDTO userAndLevelDTO = userAndLevelMapper.toDto(userAndLevel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserAndLevelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userAndLevelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserAndLevel in the database
        List<UserAndLevel> userAndLevelList = userAndLevelRepository.findAll();
        assertThat(userAndLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUserAndLevel() throws Exception {
        int databaseSizeBeforeUpdate = userAndLevelRepository.findAll().size();
        userAndLevel.setId(count.incrementAndGet());

        // Create the UserAndLevel
        UserAndLevelDTO userAndLevelDTO = userAndLevelMapper.toDto(userAndLevel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserAndLevelMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userAndLevelDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserAndLevel in the database
        List<UserAndLevel> userAndLevelList = userAndLevelRepository.findAll();
        assertThat(userAndLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUserAndLevel() throws Exception {
        // Initialize the database
        userAndLevelRepository.saveAndFlush(userAndLevel);

        int databaseSizeBeforeDelete = userAndLevelRepository.findAll().size();

        // Delete the userAndLevel
        restUserAndLevelMockMvc
            .perform(delete(ENTITY_API_URL_ID, userAndLevel.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserAndLevel> userAndLevelList = userAndLevelRepository.findAll();
        assertThat(userAndLevelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
