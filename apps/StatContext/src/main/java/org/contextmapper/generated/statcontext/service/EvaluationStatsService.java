package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.EvaluationStats;
import org.contextmapper.generated.statcontext.repository.EvaluationStatsRepository;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatsDTO;
import org.contextmapper.generated.statcontext.service.mapper.EvaluationStatsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EvaluationStats}.
 */
@Service
@Transactional
public class EvaluationStatsService {

    private final Logger log = LoggerFactory.getLogger(EvaluationStatsService.class);

    private final EvaluationStatsRepository evaluationStatsRepository;

    private final EvaluationStatsMapper evaluationStatsMapper;

    public EvaluationStatsService(EvaluationStatsRepository evaluationStatsRepository, EvaluationStatsMapper evaluationStatsMapper) {
        this.evaluationStatsRepository = evaluationStatsRepository;
        this.evaluationStatsMapper = evaluationStatsMapper;
    }

    /**
     * Save a evaluationStats.
     *
     * @param evaluationStatsDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluationStatsDTO save(EvaluationStatsDTO evaluationStatsDTO) {
        log.debug("Request to save EvaluationStats : {}", evaluationStatsDTO);
        EvaluationStats evaluationStats = evaluationStatsMapper.toEntity(evaluationStatsDTO);
        evaluationStats = evaluationStatsRepository.save(evaluationStats);
        return evaluationStatsMapper.toDto(evaluationStats);
    }

    /**
     * Update a evaluationStats.
     *
     * @param evaluationStatsDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluationStatsDTO update(EvaluationStatsDTO evaluationStatsDTO) {
        log.debug("Request to update EvaluationStats : {}", evaluationStatsDTO);
        EvaluationStats evaluationStats = evaluationStatsMapper.toEntity(evaluationStatsDTO);
        evaluationStats = evaluationStatsRepository.save(evaluationStats);
        return evaluationStatsMapper.toDto(evaluationStats);
    }

    /**
     * Partially update a evaluationStats.
     *
     * @param evaluationStatsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EvaluationStatsDTO> partialUpdate(EvaluationStatsDTO evaluationStatsDTO) {
        log.debug("Request to partially update EvaluationStats : {}", evaluationStatsDTO);

        return evaluationStatsRepository
            .findById(evaluationStatsDTO.getId())
            .map(existingEvaluationStats -> {
                evaluationStatsMapper.partialUpdate(existingEvaluationStats, evaluationStatsDTO);

                return existingEvaluationStats;
            })
            .map(evaluationStatsRepository::save)
            .map(evaluationStatsMapper::toDto);
    }

    /**
     * Get all the evaluationStats.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EvaluationStatsDTO> findAll() {
        log.debug("Request to get all EvaluationStats");
        return evaluationStatsRepository
            .findAll()
            .stream()
            .map(evaluationStatsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one evaluationStats by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EvaluationStatsDTO> findOne(Long id) {
        log.debug("Request to get EvaluationStats : {}", id);
        return evaluationStatsRepository.findById(id).map(evaluationStatsMapper::toDto);
    }

    /**
     * Delete the evaluationStats by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EvaluationStats : {}", id);
        evaluationStatsRepository.deleteById(id);
    }
}
