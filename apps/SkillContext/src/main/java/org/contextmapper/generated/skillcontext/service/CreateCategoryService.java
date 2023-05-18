package org.contextmapper.generated.skillcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.CreateCategory;
import org.contextmapper.generated.skillcontext.repository.CreateCategoryRepository;
import org.contextmapper.generated.skillcontext.service.dto.CreateCategoryDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CreateCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateCategory}.
 */
@Service
@Transactional
public class CreateCategoryService {

    private final Logger log = LoggerFactory.getLogger(CreateCategoryService.class);

    private final CreateCategoryRepository createCategoryRepository;

    private final CreateCategoryMapper createCategoryMapper;

    public CreateCategoryService(CreateCategoryRepository createCategoryRepository, CreateCategoryMapper createCategoryMapper) {
        this.createCategoryRepository = createCategoryRepository;
        this.createCategoryMapper = createCategoryMapper;
    }

    /**
     * Save a createCategory.
     *
     * @param createCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateCategoryDTO save(CreateCategoryDTO createCategoryDTO) {
        log.debug("Request to save CreateCategory : {}", createCategoryDTO);
        CreateCategory createCategory = createCategoryMapper.toEntity(createCategoryDTO);
        createCategory = createCategoryRepository.save(createCategory);
        return createCategoryMapper.toDto(createCategory);
    }

    /**
     * Update a createCategory.
     *
     * @param createCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateCategoryDTO update(CreateCategoryDTO createCategoryDTO) {
        log.debug("Request to update CreateCategory : {}", createCategoryDTO);
        CreateCategory createCategory = createCategoryMapper.toEntity(createCategoryDTO);
        createCategory = createCategoryRepository.save(createCategory);
        return createCategoryMapper.toDto(createCategory);
    }

    /**
     * Partially update a createCategory.
     *
     * @param createCategoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateCategoryDTO> partialUpdate(CreateCategoryDTO createCategoryDTO) {
        log.debug("Request to partially update CreateCategory : {}", createCategoryDTO);

        return createCategoryRepository
            .findById(createCategoryDTO.getId())
            .map(existingCreateCategory -> {
                createCategoryMapper.partialUpdate(existingCreateCategory, createCategoryDTO);

                return existingCreateCategory;
            })
            .map(createCategoryRepository::save)
            .map(createCategoryMapper::toDto);
    }

    /**
     * Get all the createCategories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateCategoryDTO> findAll() {
        log.debug("Request to get all CreateCategories");
        return createCategoryRepository
            .findAll()
            .stream()
            .map(createCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one createCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateCategoryDTO> findOne(Long id) {
        log.debug("Request to get CreateCategory : {}", id);
        return createCategoryRepository.findById(id).map(createCategoryMapper::toDto);
    }

    /**
     * Delete the createCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreateCategory : {}", id);
        createCategoryRepository.deleteById(id);
    }
}
