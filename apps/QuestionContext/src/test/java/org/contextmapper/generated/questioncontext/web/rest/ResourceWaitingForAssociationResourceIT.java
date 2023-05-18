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
import org.contextmapper.generated.questioncontext.domain.ResourceWaitingForAssociation;
import org.contextmapper.generated.questioncontext.repository.ResourceWaitingForAssociationRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceWaitingForAssociationDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceWaitingForAssociationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ResourceWaitingForAssociationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResourceWaitingForAssociationResourceIT {

    private static final String ENTITY_API_URL = "/api/resource-waiting-for-associations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResourceWaitingForAssociationRepository resourceWaitingForAssociationRepository;

    @Autowired
    private ResourceWaitingForAssociationMapper resourceWaitingForAssociationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResourceWaitingForAssociationMockMvc;

    private ResourceWaitingForAssociation resourceWaitingForAssociation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceWaitingForAssociation createEntity(EntityManager em) {
        ResourceWaitingForAssociation resourceWaitingForAssociation = new ResourceWaitingForAssociation();
        return resourceWaitingForAssociation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceWaitingForAssociation createUpdatedEntity(EntityManager em) {
        ResourceWaitingForAssociation resourceWaitingForAssociation = new ResourceWaitingForAssociation();
        return resourceWaitingForAssociation;
    }

    @BeforeEach
    public void initTest() {
        resourceWaitingForAssociation = createEntity(em);
    }

    @Test
    @Transactional
    void createResourceWaitingForAssociation() throws Exception {
        int databaseSizeBeforeCreate = resourceWaitingForAssociationRepository.findAll().size();
        // Create the ResourceWaitingForAssociation
        ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO = resourceWaitingForAssociationMapper.toDto(
            resourceWaitingForAssociation
        );
        restResourceWaitingForAssociationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceWaitingForAssociationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ResourceWaitingForAssociation in the database
        List<ResourceWaitingForAssociation> resourceWaitingForAssociationList = resourceWaitingForAssociationRepository.findAll();
        assertThat(resourceWaitingForAssociationList).hasSize(databaseSizeBeforeCreate + 1);
        ResourceWaitingForAssociation testResourceWaitingForAssociation = resourceWaitingForAssociationList.get(
            resourceWaitingForAssociationList.size() - 1
        );
    }

    @Test
    @Transactional
    void createResourceWaitingForAssociationWithExistingId() throws Exception {
        // Create the ResourceWaitingForAssociation with an existing ID
        resourceWaitingForAssociation.setId(1L);
        ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO = resourceWaitingForAssociationMapper.toDto(
            resourceWaitingForAssociation
        );

        int databaseSizeBeforeCreate = resourceWaitingForAssociationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResourceWaitingForAssociationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceWaitingForAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceWaitingForAssociation in the database
        List<ResourceWaitingForAssociation> resourceWaitingForAssociationList = resourceWaitingForAssociationRepository.findAll();
        assertThat(resourceWaitingForAssociationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResourceWaitingForAssociations() throws Exception {
        // Initialize the database
        resourceWaitingForAssociationRepository.saveAndFlush(resourceWaitingForAssociation);

        // Get all the resourceWaitingForAssociationList
        restResourceWaitingForAssociationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourceWaitingForAssociation.getId().intValue())));
    }

    @Test
    @Transactional
    void getResourceWaitingForAssociation() throws Exception {
        // Initialize the database
        resourceWaitingForAssociationRepository.saveAndFlush(resourceWaitingForAssociation);

        // Get the resourceWaitingForAssociation
        restResourceWaitingForAssociationMockMvc
            .perform(get(ENTITY_API_URL_ID, resourceWaitingForAssociation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resourceWaitingForAssociation.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingResourceWaitingForAssociation() throws Exception {
        // Get the resourceWaitingForAssociation
        restResourceWaitingForAssociationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResourceWaitingForAssociation() throws Exception {
        // Initialize the database
        resourceWaitingForAssociationRepository.saveAndFlush(resourceWaitingForAssociation);

        int databaseSizeBeforeUpdate = resourceWaitingForAssociationRepository.findAll().size();

        // Update the resourceWaitingForAssociation
        ResourceWaitingForAssociation updatedResourceWaitingForAssociation = resourceWaitingForAssociationRepository
            .findById(resourceWaitingForAssociation.getId())
            .get();
        // Disconnect from session so that the updates on updatedResourceWaitingForAssociation are not directly saved in db
        em.detach(updatedResourceWaitingForAssociation);
        ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO = resourceWaitingForAssociationMapper.toDto(
            updatedResourceWaitingForAssociation
        );

        restResourceWaitingForAssociationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resourceWaitingForAssociationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceWaitingForAssociationDTO))
            )
            .andExpect(status().isOk());

        // Validate the ResourceWaitingForAssociation in the database
        List<ResourceWaitingForAssociation> resourceWaitingForAssociationList = resourceWaitingForAssociationRepository.findAll();
        assertThat(resourceWaitingForAssociationList).hasSize(databaseSizeBeforeUpdate);
        ResourceWaitingForAssociation testResourceWaitingForAssociation = resourceWaitingForAssociationList.get(
            resourceWaitingForAssociationList.size() - 1
        );
    }

    @Test
    @Transactional
    void putNonExistingResourceWaitingForAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceWaitingForAssociationRepository.findAll().size();
        resourceWaitingForAssociation.setId(count.incrementAndGet());

        // Create the ResourceWaitingForAssociation
        ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO = resourceWaitingForAssociationMapper.toDto(
            resourceWaitingForAssociation
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourceWaitingForAssociationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resourceWaitingForAssociationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceWaitingForAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceWaitingForAssociation in the database
        List<ResourceWaitingForAssociation> resourceWaitingForAssociationList = resourceWaitingForAssociationRepository.findAll();
        assertThat(resourceWaitingForAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResourceWaitingForAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceWaitingForAssociationRepository.findAll().size();
        resourceWaitingForAssociation.setId(count.incrementAndGet());

        // Create the ResourceWaitingForAssociation
        ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO = resourceWaitingForAssociationMapper.toDto(
            resourceWaitingForAssociation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceWaitingForAssociationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceWaitingForAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceWaitingForAssociation in the database
        List<ResourceWaitingForAssociation> resourceWaitingForAssociationList = resourceWaitingForAssociationRepository.findAll();
        assertThat(resourceWaitingForAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResourceWaitingForAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceWaitingForAssociationRepository.findAll().size();
        resourceWaitingForAssociation.setId(count.incrementAndGet());

        // Create the ResourceWaitingForAssociation
        ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO = resourceWaitingForAssociationMapper.toDto(
            resourceWaitingForAssociation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceWaitingForAssociationMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceWaitingForAssociationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResourceWaitingForAssociation in the database
        List<ResourceWaitingForAssociation> resourceWaitingForAssociationList = resourceWaitingForAssociationRepository.findAll();
        assertThat(resourceWaitingForAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResourceWaitingForAssociationWithPatch() throws Exception {
        // Initialize the database
        resourceWaitingForAssociationRepository.saveAndFlush(resourceWaitingForAssociation);

        int databaseSizeBeforeUpdate = resourceWaitingForAssociationRepository.findAll().size();

        // Update the resourceWaitingForAssociation using partial update
        ResourceWaitingForAssociation partialUpdatedResourceWaitingForAssociation = new ResourceWaitingForAssociation();
        partialUpdatedResourceWaitingForAssociation.setId(resourceWaitingForAssociation.getId());

        restResourceWaitingForAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourceWaitingForAssociation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResourceWaitingForAssociation))
            )
            .andExpect(status().isOk());

        // Validate the ResourceWaitingForAssociation in the database
        List<ResourceWaitingForAssociation> resourceWaitingForAssociationList = resourceWaitingForAssociationRepository.findAll();
        assertThat(resourceWaitingForAssociationList).hasSize(databaseSizeBeforeUpdate);
        ResourceWaitingForAssociation testResourceWaitingForAssociation = resourceWaitingForAssociationList.get(
            resourceWaitingForAssociationList.size() - 1
        );
    }

    @Test
    @Transactional
    void fullUpdateResourceWaitingForAssociationWithPatch() throws Exception {
        // Initialize the database
        resourceWaitingForAssociationRepository.saveAndFlush(resourceWaitingForAssociation);

        int databaseSizeBeforeUpdate = resourceWaitingForAssociationRepository.findAll().size();

        // Update the resourceWaitingForAssociation using partial update
        ResourceWaitingForAssociation partialUpdatedResourceWaitingForAssociation = new ResourceWaitingForAssociation();
        partialUpdatedResourceWaitingForAssociation.setId(resourceWaitingForAssociation.getId());

        restResourceWaitingForAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourceWaitingForAssociation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResourceWaitingForAssociation))
            )
            .andExpect(status().isOk());

        // Validate the ResourceWaitingForAssociation in the database
        List<ResourceWaitingForAssociation> resourceWaitingForAssociationList = resourceWaitingForAssociationRepository.findAll();
        assertThat(resourceWaitingForAssociationList).hasSize(databaseSizeBeforeUpdate);
        ResourceWaitingForAssociation testResourceWaitingForAssociation = resourceWaitingForAssociationList.get(
            resourceWaitingForAssociationList.size() - 1
        );
    }

    @Test
    @Transactional
    void patchNonExistingResourceWaitingForAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceWaitingForAssociationRepository.findAll().size();
        resourceWaitingForAssociation.setId(count.incrementAndGet());

        // Create the ResourceWaitingForAssociation
        ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO = resourceWaitingForAssociationMapper.toDto(
            resourceWaitingForAssociation
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourceWaitingForAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resourceWaitingForAssociationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resourceWaitingForAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceWaitingForAssociation in the database
        List<ResourceWaitingForAssociation> resourceWaitingForAssociationList = resourceWaitingForAssociationRepository.findAll();
        assertThat(resourceWaitingForAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResourceWaitingForAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceWaitingForAssociationRepository.findAll().size();
        resourceWaitingForAssociation.setId(count.incrementAndGet());

        // Create the ResourceWaitingForAssociation
        ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO = resourceWaitingForAssociationMapper.toDto(
            resourceWaitingForAssociation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceWaitingForAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resourceWaitingForAssociationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceWaitingForAssociation in the database
        List<ResourceWaitingForAssociation> resourceWaitingForAssociationList = resourceWaitingForAssociationRepository.findAll();
        assertThat(resourceWaitingForAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResourceWaitingForAssociation() throws Exception {
        int databaseSizeBeforeUpdate = resourceWaitingForAssociationRepository.findAll().size();
        resourceWaitingForAssociation.setId(count.incrementAndGet());

        // Create the ResourceWaitingForAssociation
        ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO = resourceWaitingForAssociationMapper.toDto(
            resourceWaitingForAssociation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceWaitingForAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resourceWaitingForAssociationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResourceWaitingForAssociation in the database
        List<ResourceWaitingForAssociation> resourceWaitingForAssociationList = resourceWaitingForAssociationRepository.findAll();
        assertThat(resourceWaitingForAssociationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResourceWaitingForAssociation() throws Exception {
        // Initialize the database
        resourceWaitingForAssociationRepository.saveAndFlush(resourceWaitingForAssociation);

        int databaseSizeBeforeDelete = resourceWaitingForAssociationRepository.findAll().size();

        // Delete the resourceWaitingForAssociation
        restResourceWaitingForAssociationMockMvc
            .perform(delete(ENTITY_API_URL_ID, resourceWaitingForAssociation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResourceWaitingForAssociation> resourceWaitingForAssociationList = resourceWaitingForAssociationRepository.findAll();
        assertThat(resourceWaitingForAssociationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
