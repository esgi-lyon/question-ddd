package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.ResourceAcceptedAssociation;
import org.contextmapper.generated.questioncontext.repository.ResourceAcceptedAssociationRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceAcceptedAssociationDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceAcceptedAssociationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ResourceAcceptedAssociation}.
 */
@Service
@Transactional
public class ResourceAcceptedAssociationService {

    private final Logger log = LoggerFactory.getLogger(ResourceAcceptedAssociationService.class);

    private final ResourceAcceptedAssociationRepository resourceAcceptedAssociationRepository;

    private final ResourceAcceptedAssociationMapper resourceAcceptedAssociationMapper;

    public ResourceAcceptedAssociationService(
        ResourceAcceptedAssociationRepository resourceAcceptedAssociationRepository,
        ResourceAcceptedAssociationMapper resourceAcceptedAssociationMapper
    ) {
        this.resourceAcceptedAssociationRepository = resourceAcceptedAssociationRepository;
        this.resourceAcceptedAssociationMapper = resourceAcceptedAssociationMapper;
    }

    /**
     * Save a resourceAcceptedAssociation.
     *
     * @param resourceAcceptedAssociationDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceAcceptedAssociationDTO save(ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO) {
        log.debug("Request to save ResourceAcceptedAssociation : {}", resourceAcceptedAssociationDTO);
        ResourceAcceptedAssociation resourceAcceptedAssociation = resourceAcceptedAssociationMapper.toEntity(
            resourceAcceptedAssociationDTO
        );
        resourceAcceptedAssociation = resourceAcceptedAssociationRepository.save(resourceAcceptedAssociation);
        return resourceAcceptedAssociationMapper.toDto(resourceAcceptedAssociation);
    }

    /**
     * Update a resourceAcceptedAssociation.
     *
     * @param resourceAcceptedAssociationDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceAcceptedAssociationDTO update(ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO) {
        log.debug("Request to update ResourceAcceptedAssociation : {}", resourceAcceptedAssociationDTO);
        ResourceAcceptedAssociation resourceAcceptedAssociation = resourceAcceptedAssociationMapper.toEntity(
            resourceAcceptedAssociationDTO
        );
        resourceAcceptedAssociation = resourceAcceptedAssociationRepository.save(resourceAcceptedAssociation);
        return resourceAcceptedAssociationMapper.toDto(resourceAcceptedAssociation);
    }

    /**
     * Partially update a resourceAcceptedAssociation.
     *
     * @param resourceAcceptedAssociationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ResourceAcceptedAssociationDTO> partialUpdate(ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO) {
        log.debug("Request to partially update ResourceAcceptedAssociation : {}", resourceAcceptedAssociationDTO);

        return resourceAcceptedAssociationRepository
            .findById(resourceAcceptedAssociationDTO.getId())
            .map(existingResourceAcceptedAssociation -> {
                resourceAcceptedAssociationMapper.partialUpdate(existingResourceAcceptedAssociation, resourceAcceptedAssociationDTO);

                return existingResourceAcceptedAssociation;
            })
            .map(resourceAcceptedAssociationRepository::save)
            .map(resourceAcceptedAssociationMapper::toDto);
    }

    /**
     * Get all the resourceAcceptedAssociations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ResourceAcceptedAssociationDTO> findAll() {
        log.debug("Request to get all ResourceAcceptedAssociations");
        return resourceAcceptedAssociationRepository
            .findAll()
            .stream()
            .map(resourceAcceptedAssociationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one resourceAcceptedAssociation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ResourceAcceptedAssociationDTO> findOne(Long id) {
        log.debug("Request to get ResourceAcceptedAssociation : {}", id);
        return resourceAcceptedAssociationRepository.findById(id).map(resourceAcceptedAssociationMapper::toDto);
    }

    /**
     * Delete the resourceAcceptedAssociation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceAcceptedAssociation : {}", id);
        resourceAcceptedAssociationRepository.deleteById(id);
    }
}
