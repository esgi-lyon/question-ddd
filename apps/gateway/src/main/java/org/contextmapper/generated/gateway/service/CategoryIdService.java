package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.CategoryId;
import org.contextmapper.generated.gateway.repository.CategoryIdRepository;
import org.contextmapper.generated.gateway.service.dto.CategoryIdDTO;
import org.contextmapper.generated.gateway.service.mapper.CategoryIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link CategoryId}.
 */
@Service
@Transactional
public class CategoryIdService {

    private final Logger log = LoggerFactory.getLogger(CategoryIdService.class);

    private final CategoryIdRepository categoryIdRepository;

    private final CategoryIdMapper categoryIdMapper;

    public CategoryIdService(CategoryIdRepository categoryIdRepository, CategoryIdMapper categoryIdMapper) {
        this.categoryIdRepository = categoryIdRepository;
        this.categoryIdMapper = categoryIdMapper;
    }

    /**
     * Save a categoryId.
     *
     * @param categoryIdDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CategoryIdDTO> save(CategoryIdDTO categoryIdDTO) {
        log.debug("Request to save CategoryId : {}", categoryIdDTO);
        return categoryIdRepository.save(categoryIdMapper.toEntity(categoryIdDTO)).map(categoryIdMapper::toDto);
    }

    /**
     * Update a categoryId.
     *
     * @param categoryIdDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CategoryIdDTO> update(CategoryIdDTO categoryIdDTO) {
        log.debug("Request to update CategoryId : {}", categoryIdDTO);
        return categoryIdRepository.save(categoryIdMapper.toEntity(categoryIdDTO)).map(categoryIdMapper::toDto);
    }

    /**
     * Partially update a categoryId.
     *
     * @param categoryIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CategoryIdDTO> partialUpdate(CategoryIdDTO categoryIdDTO) {
        log.debug("Request to partially update CategoryId : {}", categoryIdDTO);

        return categoryIdRepository
            .findById(categoryIdDTO.getId())
            .map(existingCategoryId -> {
                categoryIdMapper.partialUpdate(existingCategoryId, categoryIdDTO);

                return existingCategoryId;
            })
            .flatMap(categoryIdRepository::save)
            .map(categoryIdMapper::toDto);
    }

    /**
     * Get all the categoryIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CategoryIdDTO> findAll() {
        log.debug("Request to get all CategoryIds");
        return categoryIdRepository.findAll().map(categoryIdMapper::toDto);
    }

    /**
     * Returns the number of categoryIds available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return categoryIdRepository.count();
    }

    /**
     * Get one categoryId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CategoryIdDTO> findOne(Long id) {
        log.debug("Request to get CategoryId : {}", id);
        return categoryIdRepository.findById(id).map(categoryIdMapper::toDto);
    }

    /**
     * Delete the categoryId by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CategoryId : {}", id);
        return categoryIdRepository.deleteById(id);
    }
}
