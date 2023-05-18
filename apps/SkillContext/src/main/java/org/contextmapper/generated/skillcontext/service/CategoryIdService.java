package org.contextmapper.generated.skillcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.CategoryId;
import org.contextmapper.generated.skillcontext.repository.CategoryIdRepository;
import org.contextmapper.generated.skillcontext.service.dto.CategoryIdDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CategoryIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public CategoryIdDTO save(CategoryIdDTO categoryIdDTO) {
        log.debug("Request to save CategoryId : {}", categoryIdDTO);
        CategoryId categoryId = categoryIdMapper.toEntity(categoryIdDTO);
        categoryId = categoryIdRepository.save(categoryId);
        return categoryIdMapper.toDto(categoryId);
    }

    /**
     * Update a categoryId.
     *
     * @param categoryIdDTO the entity to save.
     * @return the persisted entity.
     */
    public CategoryIdDTO update(CategoryIdDTO categoryIdDTO) {
        log.debug("Request to update CategoryId : {}", categoryIdDTO);
        CategoryId categoryId = categoryIdMapper.toEntity(categoryIdDTO);
        categoryId = categoryIdRepository.save(categoryId);
        return categoryIdMapper.toDto(categoryId);
    }

    /**
     * Partially update a categoryId.
     *
     * @param categoryIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CategoryIdDTO> partialUpdate(CategoryIdDTO categoryIdDTO) {
        log.debug("Request to partially update CategoryId : {}", categoryIdDTO);

        return categoryIdRepository
            .findById(categoryIdDTO.getId())
            .map(existingCategoryId -> {
                categoryIdMapper.partialUpdate(existingCategoryId, categoryIdDTO);

                return existingCategoryId;
            })
            .map(categoryIdRepository::save)
            .map(categoryIdMapper::toDto);
    }

    /**
     * Get all the categoryIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CategoryIdDTO> findAll() {
        log.debug("Request to get all CategoryIds");
        return categoryIdRepository.findAll().stream().map(categoryIdMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one categoryId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CategoryIdDTO> findOne(Long id) {
        log.debug("Request to get CategoryId : {}", id);
        return categoryIdRepository.findById(id).map(categoryIdMapper::toDto);
    }

    /**
     * Delete the categoryId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CategoryId : {}", id);
        categoryIdRepository.deleteById(id);
    }
}
