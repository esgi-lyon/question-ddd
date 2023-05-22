package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.ResourceAcceptedAssociationEvent;
import org.contextmapper.generated.gateway.repository.ResourceAcceptedAssociationEventRepository;
import org.contextmapper.generated.gateway.service.dto.ResourceAcceptedAssociationEventDTO;
import org.contextmapper.generated.gateway.service.mapper.ResourceAcceptedAssociationEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<ResourceAcceptedAssociationEventDTO> save(ResourceAcceptedAssociationEventDTO resourceAcceptedAssociationEventDTO) {
        log.debug("Request to save ResourceAcceptedAssociationEvent : {}", resourceAcceptedAssociationEventDTO);
        return resourceAcceptedAssociationEventRepository
            .save(resourceAcceptedAssociationEventMapper.toEntity(resourceAcceptedAssociationEventDTO))
            .map(resourceAcceptedAssociationEventMapper::toDto);
    }

    /**
     * Update a resourceAcceptedAssociationEvent.
     *
     * @param resourceAcceptedAssociationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ResourceAcceptedAssociationEventDTO> update(ResourceAcceptedAssociationEventDTO resourceAcceptedAssociationEventDTO) {
        log.debug("Request to update ResourceAcceptedAssociationEvent : {}", resourceAcceptedAssociationEventDTO);
        return resourceAcceptedAssociationEventRepository
            .save(resourceAcceptedAssociationEventMapper.toEntity(resourceAcceptedAssociationEventDTO))
            .map(resourceAcceptedAssociationEventMapper::toDto);
    }

    /**
     * Partially update a resourceAcceptedAssociationEvent.
     *
     * @param resourceAcceptedAssociationEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<ResourceAcceptedAssociationEventDTO> partialUpdate(
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
            .flatMap(resourceAcceptedAssociationEventRepository::save)
            .map(resourceAcceptedAssociationEventMapper::toDto);
    }

    /**
     * Get all the resourceAcceptedAssociationEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ResourceAcceptedAssociationEventDTO> findAll() {
        log.debug("Request to get all ResourceAcceptedAssociationEvents");
        return resourceAcceptedAssociationEventRepository.findAll().map(resourceAcceptedAssociationEventMapper::toDto);
    }

    /**
     * Returns the number of resourceAcceptedAssociationEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return resourceAcceptedAssociationEventRepository.count();
    }

    /**
     * Get one resourceAcceptedAssociationEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<ResourceAcceptedAssociationEventDTO> findOne(Long id) {
        log.debug("Request to get ResourceAcceptedAssociationEvent : {}", id);
        return resourceAcceptedAssociationEventRepository.findById(id).map(resourceAcceptedAssociationEventMapper::toDto);
    }

    /**
     * Delete the resourceAcceptedAssociationEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ResourceAcceptedAssociationEvent : {}", id);
        return resourceAcceptedAssociationEventRepository.deleteById(id);
    }
}
