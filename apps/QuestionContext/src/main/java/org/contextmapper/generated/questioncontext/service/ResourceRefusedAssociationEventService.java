package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.ResourceRefusedAssociationEvent;
import org.contextmapper.generated.questioncontext.repository.ResourceRefusedAssociationEventRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceRefusedAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceRefusedAssociationEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ResourceRefusedAssociationEvent}.
 */
@Service
@Transactional
public class ResourceRefusedAssociationEventService {

    private final Logger log = LoggerFactory.getLogger(ResourceRefusedAssociationEventService.class);

    private final ResourceRefusedAssociationEventRepository resourceRefusedAssociationEventRepository;

    private final ResourceRefusedAssociationEventMapper resourceRefusedAssociationEventMapper;

    public ResourceRefusedAssociationEventService(
        ResourceRefusedAssociationEventRepository resourceRefusedAssociationEventRepository,
        ResourceRefusedAssociationEventMapper resourceRefusedAssociationEventMapper
    ) {
        this.resourceRefusedAssociationEventRepository = resourceRefusedAssociationEventRepository;
        this.resourceRefusedAssociationEventMapper = resourceRefusedAssociationEventMapper;
    }

    /**
     * Save a resourceRefusedAssociationEvent.
     *
     * @param resourceRefusedAssociationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceRefusedAssociationEventDTO save(ResourceRefusedAssociationEventDTO resourceRefusedAssociationEventDTO) {
        log.debug("Request to save ResourceRefusedAssociationEvent : {}", resourceRefusedAssociationEventDTO);
        ResourceRefusedAssociationEvent resourceRefusedAssociationEvent = resourceRefusedAssociationEventMapper.toEntity(
            resourceRefusedAssociationEventDTO
        );
        resourceRefusedAssociationEvent = resourceRefusedAssociationEventRepository.save(resourceRefusedAssociationEvent);
        return resourceRefusedAssociationEventMapper.toDto(resourceRefusedAssociationEvent);
    }

    /**
     * Update a resourceRefusedAssociationEvent.
     *
     * @param resourceRefusedAssociationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceRefusedAssociationEventDTO update(ResourceRefusedAssociationEventDTO resourceRefusedAssociationEventDTO) {
        log.debug("Request to update ResourceRefusedAssociationEvent : {}", resourceRefusedAssociationEventDTO);
        ResourceRefusedAssociationEvent resourceRefusedAssociationEvent = resourceRefusedAssociationEventMapper.toEntity(
            resourceRefusedAssociationEventDTO
        );
        resourceRefusedAssociationEvent = resourceRefusedAssociationEventRepository.save(resourceRefusedAssociationEvent);
        return resourceRefusedAssociationEventMapper.toDto(resourceRefusedAssociationEvent);
    }

    /**
     * Partially update a resourceRefusedAssociationEvent.
     *
     * @param resourceRefusedAssociationEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ResourceRefusedAssociationEventDTO> partialUpdate(
        ResourceRefusedAssociationEventDTO resourceRefusedAssociationEventDTO
    ) {
        log.debug("Request to partially update ResourceRefusedAssociationEvent : {}", resourceRefusedAssociationEventDTO);

        return resourceRefusedAssociationEventRepository
            .findById(resourceRefusedAssociationEventDTO.getId())
            .map(existingResourceRefusedAssociationEvent -> {
                resourceRefusedAssociationEventMapper.partialUpdate(
                    existingResourceRefusedAssociationEvent,
                    resourceRefusedAssociationEventDTO
                );

                return existingResourceRefusedAssociationEvent;
            })
            .map(resourceRefusedAssociationEventRepository::save)
            .map(resourceRefusedAssociationEventMapper::toDto);
    }

    /**
     * Get all the resourceRefusedAssociationEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ResourceRefusedAssociationEventDTO> findAll() {
        log.debug("Request to get all ResourceRefusedAssociationEvents");
        return resourceRefusedAssociationEventRepository
            .findAll()
            .stream()
            .map(resourceRefusedAssociationEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one resourceRefusedAssociationEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ResourceRefusedAssociationEventDTO> findOne(Long id) {
        log.debug("Request to get ResourceRefusedAssociationEvent : {}", id);
        return resourceRefusedAssociationEventRepository.findById(id).map(resourceRefusedAssociationEventMapper::toDto);
    }

    /**
     * Delete the resourceRefusedAssociationEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceRefusedAssociationEvent : {}", id);
        resourceRefusedAssociationEventRepository.deleteById(id);
    }
}
