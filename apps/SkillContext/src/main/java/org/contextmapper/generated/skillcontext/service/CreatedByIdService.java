package org.contextmapper.generated.skillcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.CreatedById;
import org.contextmapper.generated.skillcontext.repository.CreatedByIdRepository;
import org.contextmapper.generated.skillcontext.service.dto.CreatedByIdDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CreatedByIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreatedById}.
 */
@Service
@Transactional
public class CreatedByIdService {

    private final Logger log = LoggerFactory.getLogger(CreatedByIdService.class);

    private final CreatedByIdRepository createdByIdRepository;

    private final CreatedByIdMapper createdByIdMapper;

    public CreatedByIdService(CreatedByIdRepository createdByIdRepository, CreatedByIdMapper createdByIdMapper) {
        this.createdByIdRepository = createdByIdRepository;
        this.createdByIdMapper = createdByIdMapper;
    }

    /**
     * Save a createdById.
     *
     * @param createdByIdDTO the entity to save.
     * @return the persisted entity.
     */
    public CreatedByIdDTO save(CreatedByIdDTO createdByIdDTO) {
        log.debug("Request to save CreatedById : {}", createdByIdDTO);
        CreatedById createdById = createdByIdMapper.toEntity(createdByIdDTO);
        createdById = createdByIdRepository.save(createdById);
        return createdByIdMapper.toDto(createdById);
    }

    /**
     * Update a createdById.
     *
     * @param createdByIdDTO the entity to save.
     * @return the persisted entity.
     */
    public CreatedByIdDTO update(CreatedByIdDTO createdByIdDTO) {
        log.debug("Request to update CreatedById : {}", createdByIdDTO);
        CreatedById createdById = createdByIdMapper.toEntity(createdByIdDTO);
        // no save call needed as we have no fields that can be updated
        return createdByIdMapper.toDto(createdById);
    }

    /**
     * Partially update a createdById.
     *
     * @param createdByIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreatedByIdDTO> partialUpdate(CreatedByIdDTO createdByIdDTO) {
        log.debug("Request to partially update CreatedById : {}", createdByIdDTO);

        return createdByIdRepository
            .findById(createdByIdDTO.getId())
            .map(existingCreatedById -> {
                createdByIdMapper.partialUpdate(existingCreatedById, createdByIdDTO);

                return existingCreatedById;
            })
            // .map(createdByIdRepository::save)
            .map(createdByIdMapper::toDto);
    }

    /**
     * Get all the createdByIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreatedByIdDTO> findAll() {
        log.debug("Request to get all CreatedByIds");
        return createdByIdRepository.findAll().stream().map(createdByIdMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one createdById by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreatedByIdDTO> findOne(Long id) {
        log.debug("Request to get CreatedById : {}", id);
        return createdByIdRepository.findById(id).map(createdByIdMapper::toDto);
    }

    /**
     * Delete the createdById by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreatedById : {}", id);
        createdByIdRepository.deleteById(id);
    }
}
