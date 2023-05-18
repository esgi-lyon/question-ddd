package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.ResourceRefusedAssociation;
import org.contextmapper.generated.questioncontext.repository.ResourceRefusedAssociationRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceRefusedAssociationDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceRefusedAssociationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ResourceRefusedAssociation}.
 */
@Service
@Transactional
public class ResourceRefusedAssociationService {

    private final Logger log = LoggerFactory.getLogger(ResourceRefusedAssociationService.class);

    private final ResourceRefusedAssociationRepository resourceRefusedAssociationRepository;

    private final ResourceRefusedAssociationMapper resourceRefusedAssociationMapper;

    public ResourceRefusedAssociationService(
        ResourceRefusedAssociationRepository resourceRefusedAssociationRepository,
        ResourceRefusedAssociationMapper resourceRefusedAssociationMapper
    ) {
        this.resourceRefusedAssociationRepository = resourceRefusedAssociationRepository;
        this.resourceRefusedAssociationMapper = resourceRefusedAssociationMapper;
    }

    /**
     * Save a resourceRefusedAssociation.
     *
     * @param resourceRefusedAssociationDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceRefusedAssociationDTO save(ResourceRefusedAssociationDTO resourceRefusedAssociationDTO) {
        log.debug("Request to save ResourceRefusedAssociation : {}", resourceRefusedAssociationDTO);
        ResourceRefusedAssociation resourceRefusedAssociation = resourceRefusedAssociationMapper.toEntity(resourceRefusedAssociationDTO);
        resourceRefusedAssociation = resourceRefusedAssociationRepository.save(resourceRefusedAssociation);
        return resourceRefusedAssociationMapper.toDto(resourceRefusedAssociation);
    }

    /**
     * Update a resourceRefusedAssociation.
     *
     * @param resourceRefusedAssociationDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceRefusedAssociationDTO update(ResourceRefusedAssociationDTO resourceRefusedAssociationDTO) {
        log.debug("Request to update ResourceRefusedAssociation : {}", resourceRefusedAssociationDTO);
        ResourceRefusedAssociation resourceRefusedAssociation = resourceRefusedAssociationMapper.toEntity(resourceRefusedAssociationDTO);
        resourceRefusedAssociation = resourceRefusedAssociationRepository.save(resourceRefusedAssociation);
        return resourceRefusedAssociationMapper.toDto(resourceRefusedAssociation);
    }

    /**
     * Partially update a resourceRefusedAssociation.
     *
     * @param resourceRefusedAssociationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ResourceRefusedAssociationDTO> partialUpdate(ResourceRefusedAssociationDTO resourceRefusedAssociationDTO) {
        log.debug("Request to partially update ResourceRefusedAssociation : {}", resourceRefusedAssociationDTO);

        return resourceRefusedAssociationRepository
            .findById(resourceRefusedAssociationDTO.getId())
            .map(existingResourceRefusedAssociation -> {
                resourceRefusedAssociationMapper.partialUpdate(existingResourceRefusedAssociation, resourceRefusedAssociationDTO);

                return existingResourceRefusedAssociation;
            })
            .map(resourceRefusedAssociationRepository::save)
            .map(resourceRefusedAssociationMapper::toDto);
    }

    /**
     * Get all the resourceRefusedAssociations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ResourceRefusedAssociationDTO> findAll() {
        log.debug("Request to get all ResourceRefusedAssociations");
        return resourceRefusedAssociationRepository
            .findAll()
            .stream()
            .map(resourceRefusedAssociationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one resourceRefusedAssociation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ResourceRefusedAssociationDTO> findOne(Long id) {
        log.debug("Request to get ResourceRefusedAssociation : {}", id);
        return resourceRefusedAssociationRepository.findById(id).map(resourceRefusedAssociationMapper::toDto);
    }

    /**
     * Delete the resourceRefusedAssociation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceRefusedAssociation : {}", id);
        resourceRefusedAssociationRepository.deleteById(id);
    }
}
