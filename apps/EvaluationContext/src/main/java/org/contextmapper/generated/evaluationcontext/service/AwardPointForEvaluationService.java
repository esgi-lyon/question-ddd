package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluation;
import org.contextmapper.generated.evaluationcontext.repository.AwardPointForEvaluationRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardPointForEvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AwardPointForEvaluationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AwardPointForEvaluation}.
 */
@Service
@Transactional
public class AwardPointForEvaluationService {

    private final Logger log = LoggerFactory.getLogger(AwardPointForEvaluationService.class);

    private final AwardPointForEvaluationRepository awardPointForEvaluationRepository;

    private final AwardPointForEvaluationMapper awardPointForEvaluationMapper;

    public AwardPointForEvaluationService(
        AwardPointForEvaluationRepository awardPointForEvaluationRepository,
        AwardPointForEvaluationMapper awardPointForEvaluationMapper
    ) {
        this.awardPointForEvaluationRepository = awardPointForEvaluationRepository;
        this.awardPointForEvaluationMapper = awardPointForEvaluationMapper;
    }

    /**
     * Save a awardPointForEvaluation.
     *
     * @param awardPointForEvaluationDTO the entity to save.
     * @return the persisted entity.
     */
    public AwardPointForEvaluationDTO save(AwardPointForEvaluationDTO awardPointForEvaluationDTO) {
        log.debug("Request to save AwardPointForEvaluation : {}", awardPointForEvaluationDTO);
        AwardPointForEvaluation awardPointForEvaluation = awardPointForEvaluationMapper.toEntity(awardPointForEvaluationDTO);
        awardPointForEvaluation = awardPointForEvaluationRepository.save(awardPointForEvaluation);
        return awardPointForEvaluationMapper.toDto(awardPointForEvaluation);
    }

    /**
     * Update a awardPointForEvaluation.
     *
     * @param awardPointForEvaluationDTO the entity to save.
     * @return the persisted entity.
     */
    public AwardPointForEvaluationDTO update(AwardPointForEvaluationDTO awardPointForEvaluationDTO) {
        log.debug("Request to update AwardPointForEvaluation : {}", awardPointForEvaluationDTO);
        AwardPointForEvaluation awardPointForEvaluation = awardPointForEvaluationMapper.toEntity(awardPointForEvaluationDTO);
        awardPointForEvaluation = awardPointForEvaluationRepository.save(awardPointForEvaluation);
        return awardPointForEvaluationMapper.toDto(awardPointForEvaluation);
    }

    /**
     * Partially update a awardPointForEvaluation.
     *
     * @param awardPointForEvaluationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AwardPointForEvaluationDTO> partialUpdate(AwardPointForEvaluationDTO awardPointForEvaluationDTO) {
        log.debug("Request to partially update AwardPointForEvaluation : {}", awardPointForEvaluationDTO);

        return awardPointForEvaluationRepository
            .findById(awardPointForEvaluationDTO.getId())
            .map(existingAwardPointForEvaluation -> {
                awardPointForEvaluationMapper.partialUpdate(existingAwardPointForEvaluation, awardPointForEvaluationDTO);

                return existingAwardPointForEvaluation;
            })
            .map(awardPointForEvaluationRepository::save)
            .map(awardPointForEvaluationMapper::toDto);
    }

    /**
     * Get all the awardPointForEvaluations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AwardPointForEvaluationDTO> findAll() {
        log.debug("Request to get all AwardPointForEvaluations");
        return awardPointForEvaluationRepository
            .findAll()
            .stream()
            .map(awardPointForEvaluationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one awardPointForEvaluation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AwardPointForEvaluationDTO> findOne(Long id) {
        log.debug("Request to get AwardPointForEvaluation : {}", id);
        return awardPointForEvaluationRepository.findById(id).map(awardPointForEvaluationMapper::toDto);
    }

    /**
     * Delete the awardPointForEvaluation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AwardPointForEvaluation : {}", id);
        awardPointForEvaluationRepository.deleteById(id);
    }
}
