package org.contextmapper.generated.skillcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.CategoryCreatedEvent;
import org.contextmapper.generated.skillcontext.repository.CategoryCreatedEventRepository;
import org.contextmapper.generated.skillcontext.service.dto.CategoryCreatedEventDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CategoryCreatedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public CategoryCreatedEventDTO save(CategoryCreatedEventDTO categoryCreatedEventDTO) {
        log.debug("Request to save CategoryCreatedEvent : {}", categoryCreatedEventDTO);
        CategoryCreatedEvent categoryCreatedEvent = categoryCreatedEventMapper.toEntity(categoryCreatedEventDTO);
        categoryCreatedEvent = categoryCreatedEventRepository.save(categoryCreatedEvent);
        return categoryCreatedEventMapper.toDto(categoryCreatedEvent);
    }

    /**
     * Update a categoryCreatedEvent.
     *
     * @param categoryCreatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public CategoryCreatedEventDTO update(CategoryCreatedEventDTO categoryCreatedEventDTO) {
        log.debug("Request to update CategoryCreatedEvent : {}", categoryCreatedEventDTO);
        CategoryCreatedEvent categoryCreatedEvent = categoryCreatedEventMapper.toEntity(categoryCreatedEventDTO);
        categoryCreatedEvent = categoryCreatedEventRepository.save(categoryCreatedEvent);
        return categoryCreatedEventMapper.toDto(categoryCreatedEvent);
    }

    /**
     * Partially update a categoryCreatedEvent.
     *
     * @param categoryCreatedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CategoryCreatedEventDTO> partialUpdate(CategoryCreatedEventDTO categoryCreatedEventDTO) {
        log.debug("Request to partially update CategoryCreatedEvent : {}", categoryCreatedEventDTO);

        return categoryCreatedEventRepository
            .findById(categoryCreatedEventDTO.getId())
            .map(existingCategoryCreatedEvent -> {
                categoryCreatedEventMapper.partialUpdate(existingCategoryCreatedEvent, categoryCreatedEventDTO);

                return existingCategoryCreatedEvent;
            })
            .map(categoryCreatedEventRepository::save)
            .map(categoryCreatedEventMapper::toDto);
    }

    /**
     * Get all the categoryCreatedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CategoryCreatedEventDTO> findAll() {
        log.debug("Request to get all CategoryCreatedEvents");
        return categoryCreatedEventRepository
            .findAll()
            .stream()
            .map(categoryCreatedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one categoryCreatedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CategoryCreatedEventDTO> findOne(Long id) {
        log.debug("Request to get CategoryCreatedEvent : {}", id);
        return categoryCreatedEventRepository.findById(id).map(categoryCreatedEventMapper::toDto);
    }

    /**
     * Delete the categoryCreatedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CategoryCreatedEvent : {}", id);
        categoryCreatedEventRepository.deleteById(id);
    }
}
