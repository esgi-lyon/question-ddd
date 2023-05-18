package org.contextmapper.generated.skillcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.CategoryCreated;
import org.contextmapper.generated.skillcontext.repository.CategoryCreatedRepository;
import org.contextmapper.generated.skillcontext.service.dto.CategoryCreatedDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CategoryCreatedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CategoryCreated}.
 */
@Service
@Transactional
public class CategoryCreatedService {

    private final Logger log = LoggerFactory.getLogger(CategoryCreatedService.class);

    private final CategoryCreatedRepository categoryCreatedRepository;

    private final CategoryCreatedMapper categoryCreatedMapper;

    public CategoryCreatedService(CategoryCreatedRepository categoryCreatedRepository, CategoryCreatedMapper categoryCreatedMapper) {
        this.categoryCreatedRepository = categoryCreatedRepository;
        this.categoryCreatedMapper = categoryCreatedMapper;
    }

    /**
     * Save a categoryCreated.
     *
     * @param categoryCreatedDTO the entity to save.
     * @return the persisted entity.
     */
    public CategoryCreatedDTO save(CategoryCreatedDTO categoryCreatedDTO) {
        log.debug("Request to save CategoryCreated : {}", categoryCreatedDTO);
        CategoryCreated categoryCreated = categoryCreatedMapper.toEntity(categoryCreatedDTO);
        categoryCreated = categoryCreatedRepository.save(categoryCreated);
        return categoryCreatedMapper.toDto(categoryCreated);
    }

    /**
     * Update a categoryCreated.
     *
     * @param categoryCreatedDTO the entity to save.
     * @return the persisted entity.
     */
    public CategoryCreatedDTO update(CategoryCreatedDTO categoryCreatedDTO) {
        log.debug("Request to update CategoryCreated : {}", categoryCreatedDTO);
        CategoryCreated categoryCreated = categoryCreatedMapper.toEntity(categoryCreatedDTO);
        categoryCreated = categoryCreatedRepository.save(categoryCreated);
        return categoryCreatedMapper.toDto(categoryCreated);
    }

    /**
     * Partially update a categoryCreated.
     *
     * @param categoryCreatedDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CategoryCreatedDTO> partialUpdate(CategoryCreatedDTO categoryCreatedDTO) {
        log.debug("Request to partially update CategoryCreated : {}", categoryCreatedDTO);

        return categoryCreatedRepository
            .findById(categoryCreatedDTO.getId())
            .map(existingCategoryCreated -> {
                categoryCreatedMapper.partialUpdate(existingCategoryCreated, categoryCreatedDTO);

                return existingCategoryCreated;
            })
            .map(categoryCreatedRepository::save)
            .map(categoryCreatedMapper::toDto);
    }

    /**
     * Get all the categoryCreateds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CategoryCreatedDTO> findAll() {
        log.debug("Request to get all CategoryCreateds");
        return categoryCreatedRepository
            .findAll()
            .stream()
            .map(categoryCreatedMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one categoryCreated by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CategoryCreatedDTO> findOne(Long id) {
        log.debug("Request to get CategoryCreated : {}", id);
        return categoryCreatedRepository.findById(id).map(categoryCreatedMapper::toDto);
    }

    /**
     * Delete the categoryCreated by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CategoryCreated : {}", id);
        categoryCreatedRepository.deleteById(id);
    }
}
