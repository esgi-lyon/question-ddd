package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.EvaluationTag;
import org.contextmapper.generated.evaluationcontext.repository.EvaluationTagRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationTagDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EvaluationTag}.
 */
@Service
@Transactional
public class EvaluationTagService {

    private final Logger log = LoggerFactory.getLogger(EvaluationTagService.class);

    private final EvaluationTagRepository evaluationTagRepository;

    private final EvaluationTagMapper evaluationTagMapper;

    public EvaluationTagService(EvaluationTagRepository evaluationTagRepository, EvaluationTagMapper evaluationTagMapper) {
        this.evaluationTagRepository = evaluationTagRepository;
        this.evaluationTagMapper = evaluationTagMapper;
    }

    /**
     * Save a evaluationTag.
     *
     * @param evaluationTagDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluationTagDTO save(EvaluationTagDTO evaluationTagDTO) {
        log.debug("Request to save EvaluationTag : {}", evaluationTagDTO);
        EvaluationTag evaluationTag = evaluationTagMapper.toEntity(evaluationTagDTO);
        evaluationTag = evaluationTagRepository.save(evaluationTag);
        return evaluationTagMapper.toDto(evaluationTag);
    }

    /**
     * Update a evaluationTag.
     *
     * @param evaluationTagDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluationTagDTO update(EvaluationTagDTO evaluationTagDTO) {
        log.debug("Request to update EvaluationTag : {}", evaluationTagDTO);
        EvaluationTag evaluationTag = evaluationTagMapper.toEntity(evaluationTagDTO);
        evaluationTag = evaluationTagRepository.save(evaluationTag);
        return evaluationTagMapper.toDto(evaluationTag);
    }

    /**
     * Partially update a evaluationTag.
     *
     * @param evaluationTagDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EvaluationTagDTO> partialUpdate(EvaluationTagDTO evaluationTagDTO) {
        log.debug("Request to partially update EvaluationTag : {}", evaluationTagDTO);

        return evaluationTagRepository
            .findById(evaluationTagDTO.getId())
            .map(existingEvaluationTag -> {
                evaluationTagMapper.partialUpdate(existingEvaluationTag, evaluationTagDTO);

                return existingEvaluationTag;
            })
            .map(evaluationTagRepository::save)
            .map(evaluationTagMapper::toDto);
    }

    /**
     * Get all the evaluationTags.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EvaluationTagDTO> findAll() {
        log.debug("Request to get all EvaluationTags");
        return evaluationTagRepository.findAll().stream().map(evaluationTagMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one evaluationTag by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EvaluationTagDTO> findOne(Long id) {
        log.debug("Request to get EvaluationTag : {}", id);
        return evaluationTagRepository.findById(id).map(evaluationTagMapper::toDto);
    }

    /**
     * Delete the evaluationTag by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EvaluationTag : {}", id);
        evaluationTagRepository.deleteById(id);
    }
}
