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
import org.contextmapper.generated.questioncontext.domain.ResourceRefusedAssociation;
import org.contextmapper.generated.questioncontext.repository.ResourceRefusedAssociationRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceRefusedAssociationDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceRefusedAssociationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ResourceRefusedAssociationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResourceRefusedAssociationResourceIT {

    private static final String ENTITY_API_URL = "/api/resource-refused-associations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResourceRefusedAssociationRepository resourceRefusedAssociationRepository;

    @Autowired
    private ResourceRefusedAssociationMapper resourceRefusedAssociationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResourceRefusedAssociationMockMvc;

    private ResourceRefusedAssociation resourceRefusedAssociation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceRefusedAssociation createEntity(EntityManager em) {
        ResourceRefusedAssociation resourceRefusedAssociation = new ResourceRefusedAssociation();
        return resourceRefusedAssociation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceRefusedAssociation createUpdatedEntity(EntityManager em) {
        ResourceRefusedAssociation resourceRefusedAssociation = new ResourceRefusedAssociation();
        return resourceRefusedAssociation;
    }

    @BeforeEach
    public void initTest() {
        resourceRefusedAssociation = createEntity(em);
    }

    @Test
    @Transactional
    void createResourceRefusedAssociation() throws Exception {
        int databaseSizeBeforeCreate = resourceRefusedAssociationRepository.findAll().size();
        // Create the ResourceRefusedAssociation
        ResourceRefusedAssociationDTO resourceRefusedAssociationDTO = resourceRefusedAssociationMapper.toDto(resourceRefusedAssociation);
        restResourceRefusedAssociationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceRefusedAssociationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ResourceRefusedAssociation in the database
        List<ResourceRefusedAssociation> resourceRefusedAssociationList = resourceRefusedAssociationRepository.findAll();
        assertThat(resourceRefusedAssociationList).hasSize(databaseSizeBeforeCreate + 1);
        ResourceRefusedAssociation testResourceRefusedAssociation = resourceRefusedAssociationList.get(
            resourceRefusedAssociationList.size() - 1
        );
    }

    @Test
    @Transactional
    void createResourceRefusedAssociationWithExistingId() throws Exception {
        // Create the ResourceRefusedAssociation with an existing ID
        resourceRefusedAssociation.setId(1L);
        ResourceRefusedAssociationDTO resourceRefusedAssociationDTO = resourceRefusedAssociationMapper.toDto(resourceRefusedAssociation);

        int databaseSizeBeforeCreate = resourceRefusedAssociationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResourceRefusedAssociationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceRefusedAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceRefusedAssociation in the database
        List<ResourceRefusedAssociation> resourceRefusedAssociationList = resourceRefusedAssociationRepository.findAll();
        assertThat(resourceRefusedAssociationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResourceRefusedAssociations() throws Exception {
        // Initialize the database
        resourceRefusedAssociationRepository.saveAndFlush(resourceRefusedAssociation);

        // Get all the resourceRefusedAssociationList
        restResourceRefusedAssociationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourceRefusedAssociation.getId().intValue())));
    }

    @Test
    @Transactional
    void getResourceRefusedAssociation() throws Exception {
        // Initialize the database
        resourceRefusedAssociationRepository.saveAndFlush(resourceRefusedAssociation);

        // Get the resourceRefusedAssociation
        restResourceRefusedAssociationMockMvc
            .perform(get(ENTITY_API_URL_ID, resourceRefusedAssociation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resourceRefusedAssociation.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingResourceRefusedAssociation() throws Exception {
        // Get the resourceRefusedAssociation
        restResourceRefusedAssociationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResourceRefusedAssociation() throws Exception {
        // Initialize the database
        resourceRefusedAssociationRepository.saveAndFlush(resourceRefusedAssociation);

        int databaseSizeBeforeUpdate = resourceRefusedAssociationRepository.findAll().size();

        // Update the resourceRefusedAssociation
        ResourceRefusedAssociation updatedResourceRefusedAssociation = resourceRefusedAssociationRepository
            .findById(resourceRefusedAssociation.getId())
            .get();
        // Disconnect from session so that the updates on updatedResourceRefusedAssociation are not directly saved in db
        em.detach(updatedResourceRefusedAssociation);
        ResourceRefusedAssociationDTO resourceRefusedAssociationDTO = resourceRefusedAssociationMapper.toDto(
            updatedResourceRefusedAssociation
        );

        restResourceRefusedAssociationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resourceRefusedAssociationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceRefusedAssociationDTO))
            )
            .andExpect(status().isOk());

        // Validate the ResourceRefusedAssociation in the database
        List<ResourceRefusedAssociation> resourceRefusedAssociationList = resourceRefusedAssociationRepository.findAll();
        assertThat(resourceRefusedAssociationList).hasSize(databaseSizeBeforeUpdate);
        ResourceRefusedAssociation testResourceRefusedAssociation = resourceRefusedAssociationList.get(
            resourceRefusedAssociationList.size() - 1
        );
    }

    @Test
    @Transactional
    void putNonExistingResourceRefusedAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceRefusedAssociationRepository.findAll().size();
        resourceRefusedAssociation.setId(count.incrementAndGet());

        // Create the ResourceRefusedAssociation
        ResourceRefusedAssociationDTO resourceRefusedAssociationDTO = resourceRefusedAssociationMapper.toDto(resourceRefusedAssociation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourceRefusedAssociationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resourceRefusedAssociationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceRefusedAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceRefusedAssociation in the database
        List<ResourceRefusedAssociation> resourceRefusedAssociationList = resourceRefusedAssociationRepository.findAll();
        assertThat(resourceRefusedAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResourceRefusedAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceRefusedAssociationRepository.findAll().size();
        resourceRefusedAssociation.setId(count.incrementAndGet());

        // Create the ResourceRefusedAssociation
        ResourceRefusedAssociationDTO resourceRefusedAssociationDTO = resourceRefusedAssociationMapper.toDto(resourceRefusedAssociation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceRefusedAssociationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceRefusedAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceRefusedAssociation in the database
        List<ResourceRefusedAssociation> resourceRefusedAssociationList = resourceRefusedAssociationRepository.findAll();
        assertThat(resourceRefusedAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResourceRefusedAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceRefusedAssociationRepository.findAll().size();
        resourceRefusedAssociation.setId(count.incrementAndGet());

        // Create the ResourceRefusedAssociation
        ResourceRefusedAssociationDTO resourceRefusedAssociationDTO = resourceRefusedAssociationMapper.toDto(resourceRefusedAssociation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceRefusedAssociationMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceRefusedAssociationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResourceRefusedAssociation in the database
        List<ResourceRefusedAssociation> resourceRefusedAssociationList = resourceRefusedAssociationRepository.findAll();
        assertThat(resourceRefusedAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResourceRefusedAssociationWithPatch() throws Exception {
        // Initialize the database
        resourceRefusedAssociationRepository.saveAndFlush(resourceRefusedAssociation);

        int databaseSizeBeforeUpdate = resourceRefusedAssociationRepository.findAll().size();

        // Update the resourceRefusedAssociation using partial update
        ResourceRefusedAssociation partialUpdatedResourceRefusedAssociation = new ResourceRefusedAssociation();
        partialUpdatedResourceRefusedAssociation.setId(resourceRefusedAssociation.getId());

        restResourceRefusedAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourceRefusedAssociation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResourceRefusedAssociation))
            )
            .andExpect(status().isOk());

        // Validate the ResourceRefusedAssociation in the database
        List<ResourceRefusedAssociation> resourceRefusedAssociationList = resourceRefusedAssociationRepository.findAll();
        assertThat(resourceRefusedAssociationList).hasSize(databaseSizeBeforeUpdate);
        ResourceRefusedAssociation testResourceRefusedAssociation = resourceRefusedAssociationList.get(
            resourceRefusedAssociationList.size() - 1
        );
    }

    @Test
    @Transactional
    void fullUpdateResourceRefusedAssociationWithPatch() throws Exception {
        // Initialize the database
        resourceRefusedAssociationRepository.saveAndFlush(resourceRefusedAssociation);

        int databaseSizeBeforeUpdate = resourceRefusedAssociationRepository.findAll().size();

        // Update the resourceRefusedAssociation using partial update
        ResourceRefusedAssociation partialUpdatedResourceRefusedAssociation = new ResourceRefusedAssociation();
        partialUpdatedResourceRefusedAssociation.setId(resourceRefusedAssociation.getId());

        restResourceRefusedAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourceRefusedAssociation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResourceRefusedAssociation))
            )
            .andExpect(status().isOk());

        // Validate the ResourceRefusedAssociation in the database
        List<ResourceRefusedAssociation> resourceRefusedAssociationList = resourceRefusedAssociationRepository.findAll();
        assertThat(resourceRefusedAssociationList).hasSize(databaseSizeBeforeUpdate);
        ResourceRefusedAssociation testResourceRefusedAssociation = resourceRefusedAssociationList.get(
            resourceRefusedAssociationList.size() - 1
        );
    }

    @Test
    @Transactional
    void patchNonExistingResourceRefusedAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceRefusedAssociationRepository.findAll().size();
        resourceRefusedAssociation.setId(count.incrementAndGet());

        // Create the ResourceRefusedAssociation
        ResourceRefusedAssociationDTO resourceRefusedAssociationDTO = resourceRefusedAssociationMapper.toDto(resourceRefusedAssociation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourceRefusedAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resourceRefusedAssociationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resourceRefusedAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceRefusedAssociation in the database
        List<ResourceRefusedAssociation> resourceRefusedAssociationList = resourceRefusedAssociationRepository.findAll();
        assertThat(resourceRefusedAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResourceRefusedAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceRefusedAssociationRepository.findAll().size();
        resourceRefusedAssociation.setId(count.incrementAndGet());

        // Create the ResourceRefusedAssociation
        ResourceRefusedAssociationDTO resourceRefusedAssociationDTO = resourceRefusedAssociationMapper.toDto(resourceRefusedAssociation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceRefusedAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resourceRefusedAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceRefusedAssociation in the database
        List<ResourceRefusedAssociation> resourceRefusedAssociationList = resourceRefusedAssociationRepository.findAll();
        assertThat(resourceRefusedAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResourceRefusedAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceRefusedAssociationRepository.findAll().size();
        resourceRefusedAssociation.setId(count.incrementAndGet());

        // Create the ResourceRefusedAssociation
        ResourceRefusedAssociationDTO resourceRefusedAssociationDTO = resourceRefusedAssociationMapper.toDto(resourceRefusedAssociation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceRefusedAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resourceRefusedAssociationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResourceRefusedAssociation in the database
        List<ResourceRefusedAssociation> resourceRefusedAssociationList = resourceRefusedAssociationRepository.findAll();
        assertThat(resourceRefusedAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResourceRefusedAssociation() throws Exception {
        // Initialize the database
        resourceRefusedAssociationRepository.saveAndFlush(resourceRefusedAssociation);

        int databaseSizeBeforeDelete = resourceRefusedAssociationRepository.findAll().size();

        // Delete the resourceRefusedAssociation
        restResourceRefusedAssociationMockMvc
            .perform(delete(ENTITY_API_URL_ID, resourceRefusedAssociation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResourceRefusedAssociation> resourceRefusedAssociationList = resourceRefusedAssociationRepository.findAll();
        assertThat(resourceRefusedAssociationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
