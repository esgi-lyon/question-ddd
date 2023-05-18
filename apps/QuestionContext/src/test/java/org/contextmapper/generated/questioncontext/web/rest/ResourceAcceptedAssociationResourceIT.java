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
import org.contextmapper.generated.questioncontext.domain.ResourceAcceptedAssociation;
import org.contextmapper.generated.questioncontext.repository.ResourceAcceptedAssociationRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceAcceptedAssociationDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceAcceptedAssociationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ResourceAcceptedAssociationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResourceAcceptedAssociationResourceIT {

    private static final String ENTITY_API_URL = "/api/resource-accepted-associations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResourceAcceptedAssociationRepository resourceAcceptedAssociationRepository;

    @Autowired
    private ResourceAcceptedAssociationMapper resourceAcceptedAssociationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResourceAcceptedAssociationMockMvc;

    private ResourceAcceptedAssociation resourceAcceptedAssociation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceAcceptedAssociation createEntity(EntityManager em) {
        ResourceAcceptedAssociation resourceAcceptedAssociation = new ResourceAcceptedAssociation();
        return resourceAcceptedAssociation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceAcceptedAssociation createUpdatedEntity(EntityManager em) {
        ResourceAcceptedAssociation resourceAcceptedAssociation = new ResourceAcceptedAssociation();
        return resourceAcceptedAssociation;
    }

    @BeforeEach
    public void initTest() {
        resourceAcceptedAssociation = createEntity(em);
    }

    @Test
    @Transactional
    void createResourceAcceptedAssociation() throws Exception {
        int databaseSizeBeforeCreate = resourceAcceptedAssociationRepository.findAll().size();
        // Create the ResourceAcceptedAssociation
        ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO = resourceAcceptedAssociationMapper.toDto(
            resourceAcceptedAssociation
        );
        restResourceAcceptedAssociationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceAcceptedAssociationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ResourceAcceptedAssociation in the database
        List<ResourceAcceptedAssociation> resourceAcceptedAssociationList = resourceAcceptedAssociationRepository.findAll();
        assertThat(resourceAcceptedAssociationList).hasSize(databaseSizeBeforeCreate + 1);
        ResourceAcceptedAssociation testResourceAcceptedAssociation = resourceAcceptedAssociationList.get(
            resourceAcceptedAssociationList.size() - 1
        );
    }

    @Test
    @Transactional
    void createResourceAcceptedAssociationWithExistingId() throws Exception {
        // Create the ResourceAcceptedAssociation with an existing ID
        resourceAcceptedAssociation.setId(1L);
        ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO = resourceAcceptedAssociationMapper.toDto(
            resourceAcceptedAssociation
        );

        int databaseSizeBeforeCreate = resourceAcceptedAssociationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResourceAcceptedAssociationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceAcceptedAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceAcceptedAssociation in the database
        List<ResourceAcceptedAssociation> resourceAcceptedAssociationList = resourceAcceptedAssociationRepository.findAll();
        assertThat(resourceAcceptedAssociationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResourceAcceptedAssociations() throws Exception {
        // Initialize the database
        resourceAcceptedAssociationRepository.saveAndFlush(resourceAcceptedAssociation);

        // Get all the resourceAcceptedAssociationList
        restResourceAcceptedAssociationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourceAcceptedAssociation.getId().intValue())));
    }

    @Test
    @Transactional
    void getResourceAcceptedAssociation() throws Exception {
        // Initialize the database
        resourceAcceptedAssociationRepository.saveAndFlush(resourceAcceptedAssociation);

        // Get the resourceAcceptedAssociation
        restResourceAcceptedAssociationMockMvc
            .perform(get(ENTITY_API_URL_ID, resourceAcceptedAssociation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resourceAcceptedAssociation.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingResourceAcceptedAssociation() throws Exception {
        // Get the resourceAcceptedAssociation
        restResourceAcceptedAssociationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResourceAcceptedAssociation() throws Exception {
        // Initialize the database
        resourceAcceptedAssociationRepository.saveAndFlush(resourceAcceptedAssociation);

        int databaseSizeBeforeUpdate = resourceAcceptedAssociationRepository.findAll().size();

        // Update the resourceAcceptedAssociation
        ResourceAcceptedAssociation updatedResourceAcceptedAssociation = resourceAcceptedAssociationRepository
            .findById(resourceAcceptedAssociation.getId())
            .get();
        // Disconnect from session so that the updates on updatedResourceAcceptedAssociation are not directly saved in db
        em.detach(updatedResourceAcceptedAssociation);
        ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO = resourceAcceptedAssociationMapper.toDto(
            updatedResourceAcceptedAssociation
        );

        restResourceAcceptedAssociationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resourceAcceptedAssociationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceAcceptedAssociationDTO))
            )
            .andExpect(status().isOk());

        // Validate the ResourceAcceptedAssociation in the database
        List<ResourceAcceptedAssociation> resourceAcceptedAssociationList = resourceAcceptedAssociationRepository.findAll();
        assertThat(resourceAcceptedAssociationList).hasSize(databaseSizeBeforeUpdate);
        ResourceAcceptedAssociation testResourceAcceptedAssociation = resourceAcceptedAssociationList.get(
            resourceAcceptedAssociationList.size() - 1
        );
    }

    @Test
    @Transactional
    void putNonExistingResourceAcceptedAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceAcceptedAssociationRepository.findAll().size();
        resourceAcceptedAssociation.setId(count.incrementAndGet());

        // Create the ResourceAcceptedAssociation
        ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO = resourceAcceptedAssociationMapper.toDto(
            resourceAcceptedAssociation
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourceAcceptedAssociationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resourceAcceptedAssociationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceAcceptedAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceAcceptedAssociation in the database
        List<ResourceAcceptedAssociation> resourceAcceptedAssociationList = resourceAcceptedAssociationRepository.findAll();
        assertThat(resourceAcceptedAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResourceAcceptedAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceAcceptedAssociationRepository.findAll().size();
        resourceAcceptedAssociation.setId(count.incrementAndGet());

        // Create the ResourceAcceptedAssociation
        ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO = resourceAcceptedAssociationMapper.toDto(
            resourceAcceptedAssociation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceAcceptedAssociationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceAcceptedAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceAcceptedAssociation in the database
        List<ResourceAcceptedAssociation> resourceAcceptedAssociationList = resourceAcceptedAssociationRepository.findAll();
        assertThat(resourceAcceptedAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResourceAcceptedAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceAcceptedAssociationRepository.findAll().size();
        resourceAcceptedAssociation.setId(count.incrementAndGet());

        // Create the ResourceAcceptedAssociation
        ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO = resourceAcceptedAssociationMapper.toDto(
            resourceAcceptedAssociation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceAcceptedAssociationMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceAcceptedAssociationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResourceAcceptedAssociation in the database
        List<ResourceAcceptedAssociation> resourceAcceptedAssociationList = resourceAcceptedAssociationRepository.findAll();
        assertThat(resourceAcceptedAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResourceAcceptedAssociationWithPatch() throws Exception {
        // Initialize the database
        resourceAcceptedAssociationRepository.saveAndFlush(resourceAcceptedAssociation);

        int databaseSizeBeforeUpdate = resourceAcceptedAssociationRepository.findAll().size();

        // Update the resourceAcceptedAssociation using partial update
        ResourceAcceptedAssociation partialUpdatedResourceAcceptedAssociation = new ResourceAcceptedAssociation();
        partialUpdatedResourceAcceptedAssociation.setId(resourceAcceptedAssociation.getId());

        restResourceAcceptedAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourceAcceptedAssociation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResourceAcceptedAssociation))
            )
            .andExpect(status().isOk());

        // Validate the ResourceAcceptedAssociation in the database
        List<ResourceAcceptedAssociation> resourceAcceptedAssociationList = resourceAcceptedAssociationRepository.findAll();
        assertThat(resourceAcceptedAssociationList).hasSize(databaseSizeBeforeUpdate);
        ResourceAcceptedAssociation testResourceAcceptedAssociation = resourceAcceptedAssociationList.get(
            resourceAcceptedAssociationList.size() - 1
        );
    }

    @Test
    @Transactional
    void fullUpdateResourceAcceptedAssociationWithPatch() throws Exception {
        // Initialize the database
        resourceAcceptedAssociationRepository.saveAndFlush(resourceAcceptedAssociation);

        int databaseSizeBeforeUpdate = resourceAcceptedAssociationRepository.findAll().size();

        // Update the resourceAcceptedAssociation using partial update
        ResourceAcceptedAssociation partialUpdatedResourceAcceptedAssociation = new ResourceAcceptedAssociation();
        partialUpdatedResourceAcceptedAssociation.setId(resourceAcceptedAssociation.getId());

        restResourceAcceptedAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourceAcceptedAssociation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResourceAcceptedAssociation))
            )
            .andExpect(status().isOk());

        // Validate the ResourceAcceptedAssociation in the database
        List<ResourceAcceptedAssociation> resourceAcceptedAssociationList = resourceAcceptedAssociationRepository.findAll();
        assertThat(resourceAcceptedAssociationList).hasSize(databaseSizeBeforeUpdate);
        ResourceAcceptedAssociation testResourceAcceptedAssociation = resourceAcceptedAssociationList.get(
            resourceAcceptedAssociationList.size() - 1
        );
    }

    @Test
    @Transactional
    void patchNonExistingResourceAcceptedAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceAcceptedAssociationRepository.findAll().size();
        resourceAcceptedAssociation.setId(count.incrementAndGet());

        // Create the ResourceAcceptedAssociation
        ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO = resourceAcceptedAssociationMapper.toDto(
            resourceAcceptedAssociation
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourceAcceptedAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resourceAcceptedAssociationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resourceAcceptedAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceAcceptedAssociation in the database
        List<ResourceAcceptedAssociation> resourceAcceptedAssociationList = resourceAcceptedAssociationRepository.findAll();
        assertThat(resourceAcceptedAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResourceAcceptedAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceAcceptedAssociationRepository.findAll().size();
        resourceAcceptedAssociation.setId(count.incrementAndGet());

        // Create the ResourceAcceptedAssociation
        ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO = resourceAcceptedAssociationMapper.toDto(
            resourceAcceptedAssociation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceAcceptedAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resourceAcceptedAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceAcceptedAssociation in the database
        List<ResourceAcceptedAssociation> resourceAcceptedAssociationList = resourceAcceptedAssociationRepository.findAll();
        assertThat(resourceAcceptedAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResourceAcceptedAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceAcceptedAssociationRepository.findAll().size();
        resourceAcceptedAssociation.setId(count.incrementAndGet());

        // Create the ResourceAcceptedAssociation
        ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO = resourceAcceptedAssociationMapper.toDto(
            resourceAcceptedAssociation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceAcceptedAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resourceAcceptedAssociationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResourceAcceptedAssociation in the database
        List<ResourceAcceptedAssociation> resourceAcceptedAssociationList = resourceAcceptedAssociationRepository.findAll();
        assertThat(resourceAcceptedAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResourceAcceptedAssociation() throws Exception {
        // Initialize the database
        resourceAcceptedAssociationRepository.saveAndFlush(resourceAcceptedAssociation);

        int databaseSizeBeforeDelete = resourceAcceptedAssociationRepository.findAll().size();

        // Delete the resourceAcceptedAssociation
        restResourceAcceptedAssociationMockMvc
            .perform(delete(ENTITY_API_URL_ID, resourceAcceptedAssociation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResourceAcceptedAssociation> resourceAcceptedAssociationList = resourceAcceptedAssociationRepository.findAll();
        assertThat(resourceAcceptedAssociationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
