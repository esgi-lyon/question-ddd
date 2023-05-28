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
import org.contextmapper.generated.sendquestioncontext.domain.NotifiedUsers;
import org.contextmapper.generated.sendquestioncontext.repository.NotifiedUsersRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedUsersDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.NotifiedUsersMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NotifiedUsersResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NotifiedUsersResourceIT {

    private static final String ENTITY_API_URL = "/api/notified-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NotifiedUsersRepository notifiedUsersRepository;

    @Autowired
    private NotifiedUsersMapper notifiedUsersMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNotifiedUsersMockMvc;

    private NotifiedUsers notifiedUsers;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifiedUsers createEntity(EntityManager em) {
        NotifiedUsers notifiedUsers = new NotifiedUsers();
        return notifiedUsers;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifiedUsers createUpdatedEntity(EntityManager em) {
        NotifiedUsers notifiedUsers = new NotifiedUsers();
        return notifiedUsers;
    }

    @BeforeEach
    public void initTest() {
        notifiedUsers = createEntity(em);
    }

    @Test
    @Transactional
    void createNotifiedUsers() throws Exception {
        int databaseSizeBeforeCreate = notifiedUsersRepository.findAll().size();
        // Create the NotifiedUsers
        NotifiedUsersDTO notifiedUsersDTO = notifiedUsersMapper.toDto(notifiedUsers);
        restNotifiedUsersMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(notifiedUsersDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NotifiedUsers in the database
        List<NotifiedUsers> notifiedUsersList = notifiedUsersRepository.findAll();
        assertThat(notifiedUsersList).hasSize(databaseSizeBeforeCreate + 1);
        NotifiedUsers testNotifiedUsers = notifiedUsersList.get(notifiedUsersList.size() - 1);
    }

    @Test
    @Transactional
    void createNotifiedUsersWithExistingId() throws Exception {
        // Create the NotifiedUsers with an existing ID
        notifiedUsers.setId(1L);
        NotifiedUsersDTO notifiedUsersDTO = notifiedUsersMapper.toDto(notifiedUsers);

        int databaseSizeBeforeCreate = notifiedUsersRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotifiedUsersMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(notifiedUsersDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifiedUsers in the database
        List<NotifiedUsers> notifiedUsersList = notifiedUsersRepository.findAll();
        assertThat(notifiedUsersList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNotifiedUsers() throws Exception {
        // Initialize the database
        notifiedUsersRepository.saveAndFlush(notifiedUsers);

        // Get all the notifiedUsersList
        restNotifiedUsersMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notifiedUsers.getId().intValue())));
    }

    @Test
    @Transactional
    void getNotifiedUsers() throws Exception {
        // Initialize the database
        notifiedUsersRepository.saveAndFlush(notifiedUsers);

        // Get the notifiedUsers
        restNotifiedUsersMockMvc
            .perform(get(ENTITY_API_URL_ID, notifiedUsers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notifiedUsers.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingNotifiedUsers() throws Exception {
        // Get the notifiedUsers
        restNotifiedUsersMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNotifiedUsers() throws Exception {
        // Initialize the database
        notifiedUsersRepository.saveAndFlush(notifiedUsers);

        int databaseSizeBeforeUpdate = notifiedUsersRepository.findAll().size();

        // Update the notifiedUsers
        NotifiedUsers updatedNotifiedUsers = notifiedUsersRepository.findById(notifiedUsers.getId()).get();
        // Disconnect from session so that the updates on updatedNotifiedUsers are not directly saved in db
        em.detach(updatedNotifiedUsers);
        NotifiedUsersDTO notifiedUsersDTO = notifiedUsersMapper.toDto(updatedNotifiedUsers);

        restNotifiedUsersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, notifiedUsersDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifiedUsersDTO))
            )
            .andExpect(status().isOk());

        // Validate the NotifiedUsers in the database
        List<NotifiedUsers> notifiedUsersList = notifiedUsersRepository.findAll();
        assertThat(notifiedUsersList).hasSize(databaseSizeBeforeUpdate);
        NotifiedUsers testNotifiedUsers = notifiedUsersList.get(notifiedUsersList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingNotifiedUsers() throws Exception {
        int databaseSizeBeforeUpdate = notifiedUsersRepository.findAll().size();
        notifiedUsers.setId(count.incrementAndGet());

        // Create the NotifiedUsers
        NotifiedUsersDTO notifiedUsersDTO = notifiedUsersMapper.toDto(notifiedUsers);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotifiedUsersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, notifiedUsersDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifiedUsersDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifiedUsers in the database
        List<NotifiedUsers> notifiedUsersList = notifiedUsersRepository.findAll();
        assertThat(notifiedUsersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNotifiedUsers() throws Exception {
        int databaseSizeBeforeUpdate = notifiedUsersRepository.findAll().size();
        notifiedUsers.setId(count.incrementAndGet());

        // Create the NotifiedUsers
        NotifiedUsersDTO notifiedUsersDTO = notifiedUsersMapper.toDto(notifiedUsers);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifiedUsersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notifiedUsersDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifiedUsers in the database
        List<NotifiedUsers> notifiedUsersList = notifiedUsersRepository.findAll();
        assertThat(notifiedUsersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNotifiedUsers() throws Exception {
        int databaseSizeBeforeUpdate = notifiedUsersRepository.findAll().size();
        notifiedUsers.setId(count.incrementAndGet());

        // Create the NotifiedUsers
        NotifiedUsersDTO notifiedUsersDTO = notifiedUsersMapper.toDto(notifiedUsers);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifiedUsersMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(notifiedUsersDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NotifiedUsers in the database
        List<NotifiedUsers> notifiedUsersList = notifiedUsersRepository.findAll();
        assertThat(notifiedUsersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNotifiedUsersWithPatch() throws Exception {
        // Initialize the database
        notifiedUsersRepository.saveAndFlush(notifiedUsers);

        int databaseSizeBeforeUpdate = notifiedUsersRepository.findAll().size();

        // Update the notifiedUsers using partial update
        NotifiedUsers partialUpdatedNotifiedUsers = new NotifiedUsers();
        partialUpdatedNotifiedUsers.setId(notifiedUsers.getId());

        restNotifiedUsersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNotifiedUsers.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNotifiedUsers))
            )
            .andExpect(status().isOk());

        // Validate the NotifiedUsers in the database
        List<NotifiedUsers> notifiedUsersList = notifiedUsersRepository.findAll();
        assertThat(notifiedUsersList).hasSize(databaseSizeBeforeUpdate);
        NotifiedUsers testNotifiedUsers = notifiedUsersList.get(notifiedUsersList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateNotifiedUsersWithPatch() throws Exception {
        // Initialize the database
        notifiedUsersRepository.saveAndFlush(notifiedUsers);

        int databaseSizeBeforeUpdate = notifiedUsersRepository.findAll().size();

        // Update the notifiedUsers using partial update
        NotifiedUsers partialUpdatedNotifiedUsers = new NotifiedUsers();
        partialUpdatedNotifiedUsers.setId(notifiedUsers.getId());

        restNotifiedUsersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNotifiedUsers.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNotifiedUsers))
            )
            .andExpect(status().isOk());

        // Validate the NotifiedUsers in the database
        List<NotifiedUsers> notifiedUsersList = notifiedUsersRepository.findAll();
        assertThat(notifiedUsersList).hasSize(databaseSizeBeforeUpdate);
        NotifiedUsers testNotifiedUsers = notifiedUsersList.get(notifiedUsersList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingNotifiedUsers() throws Exception {
        int databaseSizeBeforeUpdate = notifiedUsersRepository.findAll().size();
        notifiedUsers.setId(count.incrementAndGet());

        // Create the NotifiedUsers
        NotifiedUsersDTO notifiedUsersDTO = notifiedUsersMapper.toDto(notifiedUsers);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotifiedUsersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, notifiedUsersDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notifiedUsersDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifiedUsers in the database
        List<NotifiedUsers> notifiedUsersList = notifiedUsersRepository.findAll();
        assertThat(notifiedUsersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNotifiedUsers() throws Exception {
        int databaseSizeBeforeUpdate = notifiedUsersRepository.findAll().size();
        notifiedUsers.setId(count.incrementAndGet());

        // Create the NotifiedUsers
        NotifiedUsersDTO notifiedUsersDTO = notifiedUsersMapper.toDto(notifiedUsers);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifiedUsersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notifiedUsersDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotifiedUsers in the database
        List<NotifiedUsers> notifiedUsersList = notifiedUsersRepository.findAll();
        assertThat(notifiedUsersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNotifiedUsers() throws Exception {
        int databaseSizeBeforeUpdate = notifiedUsersRepository.findAll().size();
        notifiedUsers.setId(count.incrementAndGet());

        // Create the NotifiedUsers
        NotifiedUsersDTO notifiedUsersDTO = notifiedUsersMapper.toDto(notifiedUsers);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotifiedUsersMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notifiedUsersDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NotifiedUsers in the database
        List<NotifiedUsers> notifiedUsersList = notifiedUsersRepository.findAll();
        assertThat(notifiedUsersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNotifiedUsers() throws Exception {
        // Initialize the database
        notifiedUsersRepository.saveAndFlush(notifiedUsers);

        int databaseSizeBeforeDelete = notifiedUsersRepository.findAll().size();

        // Delete the notifiedUsers
        restNotifiedUsersMockMvc
            .perform(delete(ENTITY_API_URL_ID, notifiedUsers.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotifiedUsers> notifiedUsersList = notifiedUsersRepository.findAll();
        assertThat(notifiedUsersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
