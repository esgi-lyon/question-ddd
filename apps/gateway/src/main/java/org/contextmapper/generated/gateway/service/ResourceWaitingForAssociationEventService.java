package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.ResourceWaitingForAssociationEvent;
import org.contextmapper.generated.gateway.repository.ResourceWaitingForAssociationEventRepository;
import org.contextmapper.generated.gateway.service.dto.ResourceWaitingForAssociationEventDTO;
import org.contextmapper.generated.gateway.service.mapper.ResourceWaitingForAssociationEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<ResourceWaitingForAssociationEventDTO> save(ResourceWaitingForAssociationEventDTO resourceWaitingForAssociationEventDTO) {
        log.debug("Request to save ResourceWaitingForAssociationEvent : {}", resourceWaitingForAssociationEventDTO);
        return resourceWaitingForAssociationEventRepository
            .save(resourceWaitingForAssociationEventMapper.toEntity(resourceWaitingForAssociationEventDTO))
            .map(resourceWaitingForAssociationEventMapper::toDto);
    }

    /**
     * Update a resourceWaitingForAssociationEvent.
     *
     * @param resourceWaitingForAssociationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ResourceWaitingForAssociationEventDTO> update(ResourceWaitingForAssociationEventDTO resourceWaitingForAssociationEventDTO) {
        log.debug("Request to update ResourceWaitingForAssociationEvent : {}", resourceWaitingForAssociationEventDTO);
        return resourceWaitingForAssociationEventRepository
            .save(resourceWaitingForAssociationEventMapper.toEntity(resourceWaitingForAssociationEventDTO))
            .map(resourceWaitingForAssociationEventMapper::toDto);
    }

    /**
     * Partially update a resourceWaitingForAssociationEvent.
     *
     * @param resourceWaitingForAssociationEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<ResourceWaitingForAssociationEventDTO> partialUpdate(
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
            .flatMap(resourceWaitingForAssociationEventRepository::save)
            .map(resourceWaitingForAssociationEventMapper::toDto);
    }

    /**
     * Get all the resourceWaitingForAssociationEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ResourceWaitingForAssociationEventDTO> findAll() {
        log.debug("Request to get all ResourceWaitingForAssociationEvents");
        return resourceWaitingForAssociationEventRepository.findAll().map(resourceWaitingForAssociationEventMapper::toDto);
    }

    /**
     * Returns the number of resourceWaitingForAssociationEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return resourceWaitingForAssociationEventRepository.count();
    }

    /**
     * Get one resourceWaitingForAssociationEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<ResourceWaitingForAssociationEventDTO> findOne(Long id) {
        log.debug("Request to get ResourceWaitingForAssociationEvent : {}", id);
        return resourceWaitingForAssociationEventRepository.findById(id).map(resourceWaitingForAssociationEventMapper::toDto);
    }

    /**
     * Delete the resourceWaitingForAssociationEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ResourceWaitingForAssociationEvent : {}", id);
        return resourceWaitingForAssociationEventRepository.deleteById(id);
    }
}
