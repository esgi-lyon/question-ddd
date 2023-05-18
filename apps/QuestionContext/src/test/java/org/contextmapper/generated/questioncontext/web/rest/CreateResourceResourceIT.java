package org.contextmapper.generated.questioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.questioncontext.IntegrationTest;
import org.contextmapper.generated.questioncontext.domain.CreateResource;
import org.contextmapper.generated.questioncontext.repository.CreateResourceRepository;
import org.contextmapper.generated.questioncontext.service.dto.CreateResourceDTO;
import org.contextmapper.generated.questioncontext.service.mapper.CreateResourceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreateResourceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreateResourceResourceIT {

    private static final String ENTITY_API_URL = "/api/create-resources";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreateResourceRepository createResourceRepository;

    @Autowired
    private CreateResourceMapper createResourceMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreateResourceMockMvc;

    private CreateResource createResource;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateResource createEntity(EntityManager em) {
        CreateResource createResource = new CreateResource();
        return createResource;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreateResource createUpdatedEntity(EntityManager em) {
        CreateResource createResource = new CreateResource();
        return createResource;
    }

    @BeforeEach
    public void initTest() {
        createResource = createEntity(em);
    }

    @Test
    @Transactional
    void createCreateResource() throws Exception {
        int databaseSizeBeforeCreate = createResourceRepository.findAll().size();
        // Create the CreateResource
        CreateResourceDTO createResourceDTO = createResourceMapper.toDto(createResource);
        restCreateResourceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createResourceDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CreateResource in the database
        List<CreateResource> createResourceList = createResourceRepository.findAll();
        assertThat(createResourceList).hasSize(databaseSizeBeforeCreate + 1);
        CreateResource testCreateResource = createResourceList.get(createResourceList.size() - 1);
    }

    @Test
    @Transactional
    void createCreateResourceWithExistingId() throws Exception {
        // Create the CreateResource with an existing ID
        createResource.setId(1L);
        CreateResourceDTO createResourceDTO = createResourceMapper.toDto(createResource);

        int databaseSizeBeforeCreate = createResourceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreateResourceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createResourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateResource in the database
        List<CreateResource> createResourceList = createResourceRepository.findAll();
        assertThat(createResourceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCreateResources() throws Exception {
        // Initialize the database
        createResourceRepository.saveAndFlush(createResource);

        // Get all the createResourceList
        restCreateResourceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(createResource.getId().intValue())));
    }

    @Test
    @Transactional
    void getCreateResource() throws Exception {
        // Initialize the database
        createResourceRepository.saveAndFlush(createResource);

        // Get the createResource
        restCreateResourceMockMvc
            .perform(get(ENTITY_API_URL_ID, createResource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(createResource.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCreateResource() throws Exception {
        // Get the createResource
        restCreateResourceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCreateResource() throws Exception {
        // Initialize the database
        createResourceRepository.saveAndFlush(createResource);

        int databaseSizeBeforeUpdate = createResourceRepository.findAll().size();

        // Update the createResource
        CreateResource updatedCreateResource = createResourceRepository.findById(createResource.getId()).get();
        // Disconnect from session so that the updates on updatedCreateResource are not directly saved in db
        em.detach(updatedCreateResource);
        CreateResourceDTO createResourceDTO = createResourceMapper.toDto(updatedCreateResource);

        restCreateResourceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createResourceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createResourceDTO))
            )
            .andExpect(status().isOk());

        // Validate the CreateResource in the database
        List<CreateResource> createResourceList = createResourceRepository.findAll();
        assertThat(createResourceList).hasSize(databaseSizeBeforeUpdate);
        CreateResource testCreateResource = createResourceList.get(createResourceList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCreateResource() throws Exception {
        int databaseSizeBeforeUpdate = createResourceRepository.findAll().size();
        createResource.setId(count.incrementAndGet());

        // Create the CreateResource
        CreateResourceDTO createResourceDTO = createResourceMapper.toDto(createResource);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateResourceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, createResourceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createResourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateResource in the database
        List<CreateResource> createResourceList = createResourceRepository.findAll();
        assertThat(createResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCreateResource() throws Exception {
        int databaseSizeBeforeUpdate = createResourceRepository.findAll().size();
        createResource.setId(count.incrementAndGet());

        // Create the CreateResource
        CreateResourceDTO createResourceDTO = createResourceMapper.toDto(createResource);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateResourceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(createResourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateResource in the database
        List<CreateResource> createResourceList = createResourceRepository.findAll();
        assertThat(createResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCreateResource() throws Exception {
        int databaseSizeBeforeUpdate = createResourceRepository.findAll().size();
        createResource.setId(count.incrementAndGet());

        // Create the CreateResource
        CreateResourceDTO createResourceDTO = createResourceMapper.toDto(createResource);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateResourceMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createResourceDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateResource in the database
        List<CreateResource> createResourceList = createResourceRepository.findAll();
        assertThat(createResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCreateResourceWithPatch() throws Exception {
        // Initialize the database
        createResourceRepository.saveAndFlush(createResource);

        int databaseSizeBeforeUpdate = createResourceRepository.findAll().size();

        // Update the createResource using partial update
        CreateResource partialUpdatedCreateResource = new CreateResource();
        partialUpdatedCreateResource.setId(createResource.getId());

        restCreateResourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateResource.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateResource))
            )
            .andExpect(status().isOk());

        // Validate the CreateResource in the database
        List<CreateResource> createResourceList = createResourceRepository.findAll();
        assertThat(createResourceList).hasSize(databaseSizeBeforeUpdate);
        CreateResource testCreateResource = createResourceList.get(createResourceList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCreateResourceWithPatch() throws Exception {
        // Initialize the database
        createResourceRepository.saveAndFlush(createResource);

        int databaseSizeBeforeUpdate = createResourceRepository.findAll().size();

        // Update the createResource using partial update
        CreateResource partialUpdatedCreateResource = new CreateResource();
        partialUpdatedCreateResource.setId(createResource.getId());

        restCreateResourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreateResource.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCreateResource))
            )
            .andExpect(status().isOk());

        // Validate the CreateResource in the database
        List<CreateResource> createResourceList = createResourceRepository.findAll();
        assertThat(createResourceList).hasSize(databaseSizeBeforeUpdate);
        CreateResource testCreateResource = createResourceList.get(createResourceList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCreateResource() throws Exception {
        int databaseSizeBeforeUpdate = createResourceRepository.findAll().size();
        createResource.setId(count.incrementAndGet());

        // Create the CreateResource
        CreateResourceDTO createResourceDTO = createResourceMapper.toDto(createResource);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreateResourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, createResourceDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createResourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateResource in the database
        List<CreateResource> createResourceList = createResourceRepository.findAll();
        assertThat(createResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCreateResource() throws Exception {
        int databaseSizeBeforeUpdate = createResourceRepository.findAll().size();
        createResource.setId(count.incrementAndGet());

        // Create the CreateResource
        CreateResourceDTO createResourceDTO = createResourceMapper.toDto(createResource);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateResourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createResourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreateResource in the database
        List<CreateResource> createResourceList = createResourceRepository.findAll();
        assertThat(createResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCreateResource() throws Exception {
        int databaseSizeBeforeUpdate = createResourceRepository.findAll().size();
        createResource.setId(count.incrementAndGet());

        // Create the CreateResource
        CreateResourceDTO createResourceDTO = createResourceMapper.toDto(createResource);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreateResourceMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(createResourceDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreateResource in the database
        List<CreateResource> createResourceList = createResourceRepository.findAll();
        assertThat(createResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCreateResource() throws Exception {
        // Initialize the database
        createResourceRepository.saveAndFlush(createResource);

        int databaseSizeBeforeDelete = createResourceRepository.findAll().size();

        // Delete the createResource
        restCreateResourceMockMvc
            .perform(delete(ENTITY_API_URL_ID, createResource.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CreateResource> createResourceList = createResourceRepository.findAll();
        assertThat(createResourceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
