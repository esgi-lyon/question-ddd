package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.ValidateResourceTagLinkage;
import org.contextmapper.generated.questioncontext.repository.ValidateResourceTagLinkageRepository;
import org.contextmapper.generated.questioncontext.service.dto.ValidateResourceTagLinkageDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ValidateResourceTagLinkageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ValidateResourceTagLinkage}.
 */
@Service
@Transactional
public class ValidateResourceTagLinkageService {

    private final Logger log = LoggerFactory.getLogger(ValidateResourceTagLinkageService.class);

    private final ValidateResourceTagLinkageRepository validateResourceTagLinkageRepository;

    private final ValidateResourceTagLinkageMapper validateResourceTagLinkageMapper;

    public ValidateResourceTagLinkageService(
        ValidateResourceTagLinkageRepository validateResourceTagLinkageRepository,
        ValidateResourceTagLinkageMapper validateResourceTagLinkageMapper
    ) {
        this.validateResourceTagLinkageRepository = validateResourceTagLinkageRepository;
        this.validateResourceTagLinkageMapper = validateResourceTagLinkageMapper;
    }

    /**
     * Save a validateResourceTagLinkage.
     *
     * @param validateResourceTagLinkageDTO the entity to save.
     * @return the persisted entity.
     */
    public ValidateResourceTagLinkageDTO save(ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO) {
        log.debug("Request to save ValidateResourceTagLinkage : {}", validateResourceTagLinkageDTO);
        ValidateResourceTagLinkage validateResourceTagLinkage = validateResourceTagLinkageMapper.toEntity(validateResourceTagLinkageDTO);
        validateResourceTagLinkage = validateResourceTagLinkageRepository.save(validateResourceTagLinkage);
        return validateResourceTagLinkageMapper.toDto(validateResourceTagLinkage);
    }

    /**
     * Update a validateResourceTagLinkage.
     *
     * @param validateResourceTagLinkageDTO the entity to save.
     * @return the persisted entity.
     */
    public ValidateResourceTagLinkageDTO update(ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO) {
        log.debug("Request to update ValidateResourceTagLinkage : {}", validateResourceTagLinkageDTO);
        ValidateResourceTagLinkage validateResourceTagLinkage = validateResourceTagLinkageMapper.toEntity(validateResourceTagLinkageDTO);
        validateResourceTagLinkage = validateResourceTagLinkageRepository.save(validateResourceTagLinkage);
        return validateResourceTagLinkageMapper.toDto(validateResourceTagLinkage);
    }

    /**
     * Partially update a validateResourceTagLinkage.
     *
     * @param validateResourceTagLinkageDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ValidateResourceTagLinkageDTO> partialUpdate(ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO) {
        log.debug("Request to partially update ValidateResourceTagLinkage : {}", validateResourceTagLinkageDTO);

        return validateResourceTagLinkageRepository
            .findById(validateResourceTagLinkageDTO.getId())
            .map(existingValidateResourceTagLinkage -> {
                validateResourceTagLinkageMapper.partialUpdate(existingValidateResourceTagLinkage, validateResourceTagLinkageDTO);

                return existingValidateResourceTagLinkage;
            })
            .map(validateResourceTagLinkageRepository::save)
            .map(validateResourceTagLinkageMapper::toDto);
    }

    /**
     * Get all the validateResourceTagLinkages.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ValidateResourceTagLinkageDTO> findAll() {
        log.debug("Request to get all ValidateResourceTagLinkages");
        return validateResourceTagLinkageRepository
            .findAll()
            .stream()
            .map(validateResourceTagLinkageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one validateResourceTagLinkage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ValidateResourceTagLinkageDTO> findOne(Long id) {
        log.debug("Request to get ValidateResourceTagLinkage : {}", id);
        return validateResourceTagLinkageRepository.findById(id).map(validateResourceTagLinkageMapper::toDto);
    }

    /**
     * Delete the validateResourceTagLinkage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ValidateResourceTagLinkage : {}", id);
        validateResourceTagLinkageRepository.deleteById(id);
    }
}
