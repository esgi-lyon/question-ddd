package org.contextmapper.generated.skillcontext.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.CategoryId;
import org.contextmapper.generated.skillcontext.repository.CategoryIdRepository;
import org.contextmapper.generated.skillcontext.service.CategoryIdService;
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
public class CategoryIdServiceImpl implements CategoryIdService {

    private final Logger log = LoggerFactory.getLogger(CategoryIdServiceImpl.class);

    private final CategoryIdRepository categoryIdRepository;

    private final CategoryIdMapper categoryIdMapper;

    public CategoryIdServiceImpl(CategoryIdRepository categoryIdRepository, CategoryIdMapper categoryIdMapper) {
        this.categoryIdRepository = categoryIdRepository;
        this.categoryIdMapper = categoryIdMapper;
    }

    @Override
    public CategoryIdDTO save(CategoryIdDTO categoryIdDTO) {
        log.debug("Request to save CategoryId : {}", categoryIdDTO);
        CategoryId categoryId = categoryIdMapper.toEntity(categoryIdDTO);
        categoryId = categoryIdRepository.save(categoryId);
        return categoryIdMapper.toDto(categoryId);
    }

    @Override
    public CategoryIdDTO update(CategoryIdDTO categoryIdDTO) {
        log.debug("Request to update CategoryId : {}", categoryIdDTO);
        CategoryId categoryId = categoryIdMapper.toEntity(categoryIdDTO);
        categoryId = categoryIdRepository.save(categoryId);
        return categoryIdMapper.toDto(categoryId);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<CategoryIdDTO> findAll() {
        log.debug("Request to get all CategoryIds");
        return categoryIdRepository.findAll().stream().map(categoryIdMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryIdDTO> findOne(Long id) {
        log.debug("Request to get CategoryId : {}", id);
        return categoryIdRepository.findById(id).map(categoryIdMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategoryId : {}", id);
        categoryIdRepository.deleteById(id);
    }
}
