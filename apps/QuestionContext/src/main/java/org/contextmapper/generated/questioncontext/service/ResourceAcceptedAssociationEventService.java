package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.ResourceAcceptedAssociationEvent;
import org.contextmapper.generated.questioncontext.repository.ResourceAcceptedAssociationEventRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceAcceptedAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceAcceptedAssociationEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ResourceAcceptedAssociationEvent}.
 */
@Service
@Transactional
public class ResourceAcceptedAssociationEventService {

    private final Logger log = LoggerFactory.getLogger(ResourceAcceptedAssociationEventService.class);

    private final ResourceAcceptedAssociationEventRepository resourceAcceptedAssociationEventRepository;

    private final ResourceAcceptedAssociationEventMapper resourceAcceptedAssociationEventMapper;

    public ResourceAcceptedAssociationEventService(
        ResourceAcceptedAssociationEventRepository resourceAcceptedAssociationEventRepository,
        ResourceAcceptedAssociationEventMapper resourceAcceptedAssociationEventMapper
    ) {
        this.resourceAcceptedAssociationEventRepository = resourceAcceptedAssociationEventRepository;
        this.resourceAcceptedAssociationEventMapper = resourceAcceptedAssociationEventMapper;
    }

    /**
     * Save a resourceAcceptedAssociationEvent.
     *
     * @param resourceAcceptedAssociationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceAcceptedAssociationEventDTO save(ResourceAcceptedAssociationEventDTO resourceAcceptedAssociationEventDTO) {
        log.debug("Request to save ResourceAcceptedAssociationEvent : {}", resourceAcceptedAssociationEventDTO);
        ResourceAcceptedAssociationEvent resourceAcceptedAssociationEvent = resourceAcceptedAssociationEventMapper.toEntity(
            resourceAcceptedAssociationEventDTO
        );
        resourceAcceptedAssociationEvent = resourceAcceptedAssociationEventRepository.save(resourceAcceptedAssociationEvent);
        return resourceAcceptedAssociationEventMapper.toDto(resourceAcceptedAssociationEvent);
    }

    /**
     * Update a resourceAcceptedAssociationEvent.
     *
     * @param resourceAcceptedAssociationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceAcceptedAssociationEventDTO update(ResourceAcceptedAssociationEventDTO resourceAcceptedAssociationEventDTO) {
        log.debug("Request to update ResourceAcceptedAssociationEvent : {}", resourceAcceptedAssociationEventDTO);
        ResourceAcceptedAssociationEvent resourceAcceptedAssociationEvent = resourceAcceptedAssociationEventMapper.toEntity(
            resourceAcceptedAssociationEventDTO
        );
        resourceAcceptedAssociationEvent = resourceAcceptedAssociationEventRepository.save(resourceAcceptedAssociationEvent);
        return resourceAcceptedAssociationEventMapper.toDto(resourceAcceptedAssociationEvent);
    }

    /**
     * Partially update a resourceAcceptedAssociationEvent.
     *
     * @param resourceAcceptedAssociationEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ResourceAcceptedAssociationEventDTO> partialUpdate(
        ResourceAcceptedAssociationEventDTO resourceAcceptedAssociationEventDTO
    ) {
        log.debug("Request to partially update ResourceAcceptedAssociationEvent : {}", resourceAcceptedAssociationEventDTO);

        return resourceAcceptedAssociationEventRepository
            .findById(resourceAcceptedAssociationEventDTO.getId())
            .map(existingResourceAcceptedAssociationEvent -> {
                resourceAcceptedAssociationEventMapper.partialUpdate(
                    existingResourceAcceptedAssociationEvent,
                    resourceAcceptedAssociationEventDTO
                );

                return existingResourceAcceptedAssociationEvent;
            })
            .map(resourceAcceptedAssociationEventRepository::save)
            .map(resourceAcceptedAssociationEventMapper::toDto);
    }

    /**
     * Get all the resourceAcceptedAssociationEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ResourceAcceptedAssociationEventDTO> findAll() {
        log.debug("Request to get all ResourceAcceptedAssociationEvents");
        return resourceAcceptedAssociationEventRepository
            .findAll()
            .stream()
            .map(resourceAcceptedAssociationEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one resourceAcceptedAssociationEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ResourceAcceptedAssociationEventDTO> findOne(Long id) {
        log.debug("Request to get ResourceAcceptedAssociationEvent : {}", id);
        return resourceAcceptedAssociationEventRepository.findById(id).map(resourceAcceptedAssociationEventMapper::toDto);
    }

    /**
     * Delete the resourceAcceptedAssociationEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceAcceptedAssociationEvent : {}", id);
        resourceAcceptedAssociationEventRepository.deleteById(id);
    }
}
