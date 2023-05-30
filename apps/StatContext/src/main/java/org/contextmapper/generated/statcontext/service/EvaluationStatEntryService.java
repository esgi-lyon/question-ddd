package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.EvaluationStatEntry;
import org.contextmapper.generated.statcontext.repository.EvaluationStatEntryRepository;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatEntryDTO;
import org.contextmapper.generated.statcontext.service.mapper.EvaluationStatEntryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EvaluationStatEntry}.
 */
@Service
@Transactional
public class EvaluationStatEntryService {

    private final Logger log = LoggerFactory.getLogger(EvaluationStatEntryService.class);

    private final EvaluationStatEntryRepository evaluationStatEntryRepository;

    private final EvaluationStatEntryMapper evaluationStatEntryMapper;

    public EvaluationStatEntryService(
        EvaluationStatEntryRepository evaluationStatEntryRepository,
        EvaluationStatEntryMapper evaluationStatEntryMapper
    ) {
        this.evaluationStatEntryRepository = evaluationStatEntryRepository;
        this.evaluationStatEntryMapper = evaluationStatEntryMapper;
    }

    /**
     * Save a evaluationStatEntry.
     *
     * @param evaluationStatEntryDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluationStatEntryDTO save(EvaluationStatEntryDTO evaluationStatEntryDTO) {
        log.debug("Request to save EvaluationStatEntry : {}", evaluationStatEntryDTO);
        EvaluationStatEntry evaluationStatEntry = evaluationStatEntryMapper.toEntity(evaluationStatEntryDTO);
        evaluationStatEntry = evaluationStatEntryRepository.save(evaluationStatEntry);
        return evaluationStatEntryMapper.toDto(evaluationStatEntry);
    }

    /**
     * Update a evaluationStatEntry.
     *
     * @param evaluationStatEntryDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluationStatEntryDTO update(EvaluationStatEntryDTO evaluationStatEntryDTO) {
        log.debug("Request to update EvaluationStatEntry : {}", evaluationStatEntryDTO);
        EvaluationStatEntry evaluationStatEntry = evaluationStatEntryMapper.toEntity(evaluationStatEntryDTO);
        evaluationStatEntry = evaluationStatEntryRepository.save(evaluationStatEntry);
        return evaluationStatEntryMapper.toDto(evaluationStatEntry);
    }

    /**
     * Partially update a evaluationStatEntry.
     *
     * @param evaluationStatEntryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EvaluationStatEntryDTO> partialUpdate(EvaluationStatEntryDTO evaluationStatEntryDTO) {
        log.debug("Request to partially update EvaluationStatEntry : {}", evaluationStatEntryDTO);

        return evaluationStatEntryRepository
            .findById(evaluationStatEntryDTO.getId())
            .map(existingEvaluationStatEntry -> {
                evaluationStatEntryMapper.partialUpdate(existingEvaluationStatEntry, evaluationStatEntryDTO);

                return existingEvaluationStatEntry;
            })
            .map(evaluationStatEntryRepository::save)
            .map(evaluationStatEntryMapper::toDto);
    }

    /**
     * Get all the evaluationStatEntries.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EvaluationStatEntryDTO> findAll() {
        log.debug("Request to get all EvaluationStatEntries");
        return evaluationStatEntryRepository
            .findAll()
            .stream()
            .map(evaluationStatEntryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one evaluationStatEntry by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EvaluationStatEntryDTO> findOne(Long id) {
        log.debug("Request to get EvaluationStatEntry : {}", id);
        return evaluationStatEntryRepository.findById(id).map(evaluationStatEntryMapper::toDto);
    }

    /**
     * Delete the evaluationStatEntry by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EvaluationStatEntry : {}", id);
        evaluationStatEntryRepository.deleteById(id);
    }
}
