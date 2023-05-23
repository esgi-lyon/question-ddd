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
import org.contextmapper.generated.usermanagementcontext.domain.UserInfos;
import org.contextmapper.generated.usermanagementcontext.domain.enumeration.Roles;
import org.contextmapper.generated.usermanagementcontext.domain.enumeration.UserStatus;
import org.contextmapper.generated.usermanagementcontext.repository.UserInfosRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserInfosMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserInfosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserInfosResourceIT {

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final Roles DEFAULT_ROLE = Roles.EVALUATOR;
    private static final Roles UPDATED_ROLE = Roles.STUDENT;

    private static final UserStatus DEFAULT_STATUS = UserStatus.VALIDATED;
    private static final UserStatus UPDATED_STATUS = UserStatus.WAITING_VALIDATION;

    private static final String ENTITY_API_URL = "/api/user-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserInfosRepository userInfosRepository;

    @Autowired
    private UserInfosMapper userInfosMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserInfosMockMvc;

    private UserInfos userInfos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserInfos createEntity(EntityManager em) {
        UserInfos userInfos = new UserInfos()
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .password(DEFAULT_PASSWORD)
            .mail(DEFAULT_MAIL)
            .role(DEFAULT_ROLE)
            .status(DEFAULT_STATUS);
        return userInfos;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserInfos createUpdatedEntity(EntityManager em) {
        UserInfos userInfos = new UserInfos()
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .password(UPDATED_PASSWORD)
            .mail(UPDATED_MAIL)
            .role(UPDATED_ROLE)
            .status(UPDATED_STATUS);
        return userInfos;
    }

    @BeforeEach
    public void initTest() {
        userInfos = createEntity(em);
    }

    @Test
    @Transactional
    void createUserInfos() throws Exception {
        int databaseSizeBeforeCreate = userInfosRepository.findAll().size();
        // Create the UserInfos
        UserInfosDTO userInfosDTO = userInfosMapper.toDto(userInfos);
        restUserInfosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userInfosDTO)))
            .andExpect(status().isCreated());

        // Validate the UserInfos in the database
        List<UserInfos> userInfosList = userInfosRepository.findAll();
        assertThat(userInfosList).hasSize(databaseSizeBeforeCreate + 1);
        UserInfos testUserInfos = userInfosList.get(userInfosList.size() - 1);
        assertThat(testUserInfos.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testUserInfos.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testUserInfos.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testUserInfos.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testUserInfos.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testUserInfos.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void createUserInfosWithExistingId() throws Exception {
        // Create the UserInfos with an existing ID
        userInfos.setId(1L);
        UserInfosDTO userInfosDTO = userInfosMapper.toDto(userInfos);

        int databaseSizeBeforeCreate = userInfosRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserInfosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userInfosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserInfos in the database
        List<UserInfos> userInfosList = userInfosRepository.findAll();
        assertThat(userInfosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUserInfos() throws Exception {
        // Initialize the database
        userInfosRepository.saveAndFlush(userInfos);

        // Get all the userInfosList
        restUserInfosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userInfos.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getUserInfos() throws Exception {
        // Initialize the database
        userInfosRepository.saveAndFlush(userInfos);

        // Get the userInfos
        restUserInfosMockMvc
            .perform(get(ENTITY_API_URL_ID, userInfos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userInfos.getId().intValue()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingUserInfos() throws Exception {
        // Get the userInfos
        restUserInfosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUserInfos() throws Exception {
        // Initialize the database
        userInfosRepository.saveAndFlush(userInfos);

        int databaseSizeBeforeUpdate = userInfosRepository.findAll().size();

        // Update the userInfos
        UserInfos updatedUserInfos = userInfosRepository.findById(userInfos.getId()).get();
        // Disconnect from session so that the updates on updatedUserInfos are not directly saved in db
        em.detach(updatedUserInfos);
        updatedUserInfos
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .password(UPDATED_PASSWORD)
            .mail(UPDATED_MAIL)
            .role(UPDATED_ROLE)
            .status(UPDATED_STATUS);
        UserInfosDTO userInfosDTO = userInfosMapper.toDto(updatedUserInfos);

        restUserInfosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userInfosDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userInfosDTO))
            )
            .andExpect(status().isOk());

        // Validate the UserInfos in the database
        List<UserInfos> userInfosList = userInfosRepository.findAll();
        assertThat(userInfosList).hasSize(databaseSizeBeforeUpdate);
        UserInfos testUserInfos = userInfosList.get(userInfosList.size() - 1);
        assertThat(testUserInfos.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testUserInfos.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testUserInfos.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testUserInfos.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testUserInfos.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testUserInfos.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingUserInfos() throws Exception {
        int databaseSizeBeforeUpdate = userInfosRepository.findAll().size();
        userInfos.setId(count.incrementAndGet());

        // Create the UserInfos
        UserInfosDTO userInfosDTO = userInfosMapper.toDto(userInfos);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserInfosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userInfosDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userInfosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserInfos in the database
        List<UserInfos> userInfosList = userInfosRepository.findAll();
        assertThat(userInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUserInfos() throws Exception {
        int databaseSizeBeforeUpdate = userInfosRepository.findAll().size();
        userInfos.setId(count.incrementAndGet());

        // Create the UserInfos
        UserInfosDTO userInfosDTO = userInfosMapper.toDto(userInfos);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserInfosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userInfosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserInfos in the database
        List<UserInfos> userInfosList = userInfosRepository.findAll();
        assertThat(userInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUserInfos() throws Exception {
        int databaseSizeBeforeUpdate = userInfosRepository.findAll().size();
        userInfos.setId(count.incrementAndGet());

        // Create the UserInfos
        UserInfosDTO userInfosDTO = userInfosMapper.toDto(userInfos);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserInfosMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userInfosDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserInfos in the database
        List<UserInfos> userInfosList = userInfosRepository.findAll();
        assertThat(userInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserInfosWithPatch() throws Exception {
        // Initialize the database
        userInfosRepository.saveAndFlush(userInfos);

        int databaseSizeBeforeUpdate = userInfosRepository.findAll().size();

        // Update the userInfos using partial update
        UserInfos partialUpdatedUserInfos = new UserInfos();
        partialUpdatedUserInfos.setId(userInfos.getId());

        partialUpdatedUserInfos.password(UPDATED_PASSWORD).role(UPDATED_ROLE).status(UPDATED_STATUS);

        restUserInfosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserInfos.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserInfos))
            )
            .andExpect(status().isOk());

        // Validate the UserInfos in the database
        List<UserInfos> userInfosList = userInfosRepository.findAll();
        assertThat(userInfosList).hasSize(databaseSizeBeforeUpdate);
        UserInfos testUserInfos = userInfosList.get(userInfosList.size() - 1);
        assertThat(testUserInfos.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testUserInfos.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testUserInfos.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testUserInfos.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testUserInfos.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testUserInfos.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateUserInfosWithPatch() throws Exception {
        // Initialize the database
        userInfosRepository.saveAndFlush(userInfos);

        int databaseSizeBeforeUpdate = userInfosRepository.findAll().size();

        // Update the userInfos using partial update
        UserInfos partialUpdatedUserInfos = new UserInfos();
        partialUpdatedUserInfos.setId(userInfos.getId());

        partialUpdatedUserInfos
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .password(UPDATED_PASSWORD)
            .mail(UPDATED_MAIL)
            .role(UPDATED_ROLE)
            .status(UPDATED_STATUS);

        restUserInfosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserInfos.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserInfos))
            )
            .andExpect(status().isOk());

        // Validate the UserInfos in the database
        List<UserInfos> userInfosList = userInfosRepository.findAll();
        assertThat(userInfosList).hasSize(databaseSizeBeforeUpdate);
        UserInfos testUserInfos = userInfosList.get(userInfosList.size() - 1);
        assertThat(testUserInfos.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testUserInfos.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testUserInfos.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testUserInfos.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testUserInfos.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testUserInfos.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingUserInfos() throws Exception {
        int databaseSizeBeforeUpdate = userInfosRepository.findAll().size();
        userInfos.setId(count.incrementAndGet());

        // Create the UserInfos
        UserInfosDTO userInfosDTO = userInfosMapper.toDto(userInfos);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserInfosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, userInfosDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userInfosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserInfos in the database
        List<UserInfos> userInfosList = userInfosRepository.findAll();
        assertThat(userInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUserInfos() throws Exception {
        int databaseSizeBeforeUpdate = userInfosRepository.findAll().size();
        userInfos.setId(count.incrementAndGet());

        // Create the UserInfos
        UserInfosDTO userInfosDTO = userInfosMapper.toDto(userInfos);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserInfosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userInfosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserInfos in the database
        List<UserInfos> userInfosList = userInfosRepository.findAll();
        assertThat(userInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUserInfos() throws Exception {
        int databaseSizeBeforeUpdate = userInfosRepository.findAll().size();
        userInfos.setId(count.incrementAndGet());

        // Create the UserInfos
        UserInfosDTO userInfosDTO = userInfosMapper.toDto(userInfos);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserInfosMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(userInfosDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserInfos in the database
        List<UserInfos> userInfosList = userInfosRepository.findAll();
        assertThat(userInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUserInfos() throws Exception {
        // Initialize the database
        userInfosRepository.saveAndFlush(userInfos);

        int databaseSizeBeforeDelete = userInfosRepository.findAll().size();

        // Delete the userInfos
        restUserInfosMockMvc
            .perform(delete(ENTITY_API_URL_ID, userInfos.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserInfos> userInfosList = userInfosRepository.findAll();
        assertThat(userInfosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
