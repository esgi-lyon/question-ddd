package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.ResourceWaitingForAssociationEvent;
import org.contextmapper.generated.questioncontext.repository.ResourceWaitingForAssociationEventRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceWaitingForAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceWaitingForAssociationEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ResourceWaitingForAssociationEvent}.
 */
@Service
@Transactional
public class ResourceWaitingForAssociationEventService {

    private final Logger log = LoggerFactory.getLogger(ResourceWaitingForAssociationEventService.class);

    private final ResourceWaitingForAssociationEventRepository resourceWaitingForAssociationEventRepository;

    private final ResourceWaitingForAssociationEventMapper resourceWaitingForAssociationEventMapper;

    public ResourceWaitingForAssociationEventService(
        ResourceWaitingForAssociationEventRepository resourceWaitingForAssociationEventRepository,
        ResourceWaitingForAssociationEventMapper resourceWaitingForAssociationEventMapper
    ) {
        this.resourceWaitingForAssociationEventRepository = resourceWaitingForAssociationEventRepository;
        this.resourceWaitingForAssociationEventMapper = resourceWaitingForAssociationEventMapper;
    }

    /**
     * Save a resourceWaitingForAssociationEvent.
     *
     * @param resourceWaitingForAssociationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceWaitingForAssociationEventDTO save(ResourceWaitingForAssociationEventDTO resourceWaitingForAssociationEventDTO) {
        log.debug("Request to save ResourceWaitingForAssociationEvent : {}", resourceWaitingForAssociationEventDTO);
        ResourceWaitingForAssociationEvent resourceWaitingForAssociationEvent = resourceWaitingForAssociationEventMapper.toEntity(
            resourceWaitingForAssociationEventDTO
        );
        resourceWaitingForAssociationEvent = resourceWaitingForAssociationEventRepository.save(resourceWaitingForAssociationEvent);
        return resourceWaitingForAssociationEventMapper.toDto(resourceWaitingForAssociationEvent);
    }

    /**
     * Update a resourceWaitingForAssociationEvent.
     *
     * @param resourceWaitingForAssociationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceWaitingForAssociationEventDTO update(ResourceWaitingForAssociationEventDTO resourceWaitingForAssociationEventDTO) {
        log.debug("Request to update ResourceWaitingForAssociationEvent : {}", resourceWaitingForAssociationEventDTO);
        ResourceWaitingForAssociationEvent resourceWaitingForAssociationEvent = resourceWaitingForAssociationEventMapper.toEntity(
            resourceWaitingForAssociationEventDTO
        );
        resourceWaitingForAssociationEvent = resourceWaitingForAssociationEventRepository.save(resourceWaitingForAssociationEvent);
        return resourceWaitingForAssociationEventMapper.toDto(resourceWaitingForAssociationEvent);
    }

    /**
     * Partially update a resourceWaitingForAssociationEvent.
     *
     * @param resourceWaitingForAssociationEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ResourceWaitingForAssociationEventDTO> partialUpdate(
        ResourceWaitingForAssociationEventDTO resourceWaitingForAssociationEventDTO
    ) {
        log.debug("Request to partially update ResourceWaitingForAssociationEvent : {}", resourceWaitingForAssociationEventDTO);

        return resourceWaitingForAssociationEventRepository
            .findById(resourceWaitingForAssociationEventDTO.getId())
            .map(existingResourceWaitingForAssociationEvent -> {
                resourceWaitingForAssociationEventMapper.partialUpdate(
                    existingResourceWaitingForAssociationEvent,
                    resourceWaitingForAssociationEventDTO
                );

                return existingResourceWaitingForAssociationEvent;
            })
            .map(resourceWaitingForAssociationEventRepository::save)
            .map(resourceWaitingForAssociationEventMapper::toDto);
    }

    /**
     * Get all the resourceWaitingForAssociationEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ResourceWaitingForAssociationEventDTO> findAll() {
        log.debug("Request to get all ResourceWaitingForAssociationEvents");
        return resourceWaitingForAssociationEventRepository
            .findAll()
            .stream()
            .map(resourceWaitingForAssociationEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one resourceWaitingForAssociationEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ResourceWaitingForAssociationEventDTO> findOne(Long id) {
        log.debug("Request to get ResourceWaitingForAssociationEvent : {}", id);
        return resourceWaitingForAssociationEventRepository.findById(id).map(resourceWaitingForAssociationEventMapper::toDto);
    }

    /**
     * Delete the resourceWaitingForAssociationEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceWaitingForAssociationEvent : {}", id);
        resourceWaitingForAssociationEventRepository.deleteById(id);
    }
}
