package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.ResourceWaitingForAssociation;
import org.contextmapper.generated.questioncontext.repository.ResourceWaitingForAssociationRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceWaitingForAssociationDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceWaitingForAssociationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ResourceWaitingForAssociation}.
 */
@Service
@Transactional
public class ResourceWaitingForAssociationService {

    private final Logger log = LoggerFactory.getLogger(ResourceWaitingForAssociationService.class);

    private final ResourceWaitingForAssociationRepository resourceWaitingForAssociationRepository;

    private final ResourceWaitingForAssociationMapper resourceWaitingForAssociationMapper;

    public ResourceWaitingForAssociationService(
        ResourceWaitingForAssociationRepository resourceWaitingForAssociationRepository,
        ResourceWaitingForAssociationMapper resourceWaitingForAssociationMapper
    ) {
        this.resourceWaitingForAssociationRepository = resourceWaitingForAssociationRepository;
        this.resourceWaitingForAssociationMapper = resourceWaitingForAssociationMapper;
    }

    /**
     * Save a resourceWaitingForAssociation.
     *
     * @param resourceWaitingForAssociationDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceWaitingForAssociationDTO save(ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO) {
        log.debug("Request to save ResourceWaitingForAssociation : {}", resourceWaitingForAssociationDTO);
        ResourceWaitingForAssociation resourceWaitingForAssociation = resourceWaitingForAssociationMapper.toEntity(
            resourceWaitingForAssociationDTO
        );
        resourceWaitingForAssociation = resourceWaitingForAssociationRepository.save(resourceWaitingForAssociation);
        return resourceWaitingForAssociationMapper.toDto(resourceWaitingForAssociation);
    }

    /**
     * Update a resourceWaitingForAssociation.
     *
     * @param resourceWaitingForAssociationDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceWaitingForAssociationDTO update(ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO) {
        log.debug("Request to update ResourceWaitingForAssociation : {}", resourceWaitingForAssociationDTO);
        ResourceWaitingForAssociation resourceWaitingForAssociation = resourceWaitingForAssociationMapper.toEntity(
            resourceWaitingForAssociationDTO
        );
        resourceWaitingForAssociation = resourceWaitingForAssociationRepository.save(resourceWaitingForAssociation);
        return resourceWaitingForAssociationMapper.toDto(resourceWaitingForAssociation);
    }

    /**
     * Partially update a resourceWaitingForAssociation.
     *
     * @param resourceWaitingForAssociationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ResourceWaitingForAssociationDTO> partialUpdate(ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO) {
        log.debug("Request to partially update ResourceWaitingForAssociation : {}", resourceWaitingForAssociationDTO);

        return resourceWaitingForAssociationRepository
            .findById(resourceWaitingForAssociationDTO.getId())
            .map(existingResourceWaitingForAssociation -> {
                resourceWaitingForAssociationMapper.partialUpdate(existingResourceWaitingForAssociation, resourceWaitingForAssociationDTO);

                return existingResourceWaitingForAssociation;
            })
            .map(resourceWaitingForAssociationRepository::save)
            .map(resourceWaitingForAssociationMapper::toDto);
    }

    /**
     * Get all the resourceWaitingForAssociations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ResourceWaitingForAssociationDTO> findAll() {
        log.debug("Request to get all ResourceWaitingForAssociations");
        return resourceWaitingForAssociationRepository
            .findAll()
            .stream()
            .map(resourceWaitingForAssociationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one resourceWaitingForAssociation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ResourceWaitingForAssociationDTO> findOne(Long id) {
        log.debug("Request to get ResourceWaitingForAssociation : {}", id);
        return resourceWaitingForAssociationRepository.findById(id).map(resourceWaitingForAssociationMapper::toDto);
    }

    /**
     * Delete the resourceWaitingForAssociation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceWaitingForAssociation : {}", id);
        resourceWaitingForAssociationRepository.deleteById(id);
    }
}
