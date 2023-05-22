package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.CategoryCreatedEvent;
import org.contextmapper.generated.gateway.repository.CategoryCreatedEventRepository;
import org.contextmapper.generated.gateway.service.dto.CategoryCreatedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.CategoryCreatedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link CategoryCreatedEvent}.
 */
@Service
@Transactional
public class CategoryCreatedEventService {

    private final Logger log = LoggerFactory.getLogger(CategoryCreatedEventService.class);

    private final CategoryCreatedEventRepository categoryCreatedEventRepository;

    private final CategoryCreatedEventMapper categoryCreatedEventMapper;

    public CategoryCreatedEventService(
        CategoryCreatedEventRepository categoryCreatedEventRepository,
        CategoryCreatedEventMapper categoryCreatedEventMapper
    ) {
        this.categoryCreatedEventRepository = categoryCreatedEventRepository;
        this.categoryCreatedEventMapper = categoryCreatedEventMapper;
    }

    /**
     * Save a categoryCreatedEvent.
     *
     * @param categoryCreatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CategoryCreatedEventDTO> save(CategoryCreatedEventDTO categoryCreatedEventDTO) {
        log.debug("Request to save CategoryCreatedEvent : {}", categoryCreatedEventDTO);
        return categoryCreatedEventRepository
            .save(categoryCreatedEventMapper.toEntity(categoryCreatedEventDTO))
            .map(categoryCreatedEventMapper::toDto);
    }

    /**
     * Update a categoryCreatedEvent.
     *
     * @param categoryCreatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CategoryCreatedEventDTO> update(CategoryCreatedEventDTO categoryCreatedEventDTO) {
        log.debug("Request to update CategoryCreatedEvent : {}", categoryCreatedEventDTO);
        return categoryCreatedEventRepository
            .save(categoryCreatedEventMapper.toEntity(categoryCreatedEventDTO))
            .map(categoryCreatedEventMapper::toDto);
    }

    /**
     * Partially update a categoryCreatedEvent.
     *
     * @param categoryCreatedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CategoryCreatedEventDTO> partialUpdate(CategoryCreatedEventDTO categoryCreatedEventDTO) {
        log.debug("Request to partially update CategoryCreatedEvent : {}", categoryCreatedEventDTO);

        return categoryCreatedEventRepository
            .findById(categoryCreatedEventDTO.getId())
            .map(existingCategoryCreatedEvent -> {
                categoryCreatedEventMapper.partialUpdate(existingCategoryCreatedEvent, categoryCreatedEventDTO);

                return existingCategoryCreatedEvent;
            })
            .flatMap(categoryCreatedEventRepository::save)
            .map(categoryCreatedEventMapper::toDto);
    }

    /**
     * Get all the categoryCreatedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CategoryCreatedEventDTO> findAll() {
        log.debug("Request to get all CategoryCreatedEvents");
        return categoryCreatedEventRepository.findAll().map(categoryCreatedEventMapper::toDto);
    }

    /**
     * Returns the number of categoryCreatedEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return categoryCreatedEventRepository.count();
    }

    /**
     * Get one categoryCreatedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CategoryCreatedEventDTO> findOne(Long id) {
        log.debug("Request to get CategoryCreatedEvent : {}", id);
        return categoryCreatedEventRepository.findById(id).map(categoryCreatedEventMapper::toDto);
    }

    /**
     * Delete the categoryCreatedEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CategoryCreatedEvent : {}", id);
        return categoryCreatedEventRepository.deleteById(id);
    }
}
