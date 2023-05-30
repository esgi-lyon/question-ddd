package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.AnswerChecked;
import org.contextmapper.generated.evaluationcontext.repository.AnswerCheckedRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AnswerCheckedDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AnswerCheckedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnswerChecked}.
 */
@Service
@Transactional
public class AnswerCheckedService {

    private final Logger log = LoggerFactory.getLogger(AnswerCheckedService.class);

    private final AnswerCheckedRepository answerCheckedRepository;

    private final AnswerCheckedMapper answerCheckedMapper;

    public AnswerCheckedService(AnswerCheckedRepository answerCheckedRepository, AnswerCheckedMapper answerCheckedMapper) {
        this.answerCheckedRepository = answerCheckedRepository;
        this.answerCheckedMapper = answerCheckedMapper;
    }

    /**
     * Save a answerChecked.
     *
     * @param answerCheckedDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerCheckedDTO save(AnswerCheckedDTO answerCheckedDTO) {
        log.debug("Request to save AnswerChecked : {}", answerCheckedDTO);
        AnswerChecked answerChecked = answerCheckedMapper.toEntity(answerCheckedDTO);
        answerChecked = answerCheckedRepository.save(answerChecked);
        return answerCheckedMapper.toDto(answerChecked);
    }

    /**
     * Update a answerChecked.
     *
     * @param answerCheckedDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerCheckedDTO update(AnswerCheckedDTO answerCheckedDTO) {
        log.debug("Request to update AnswerChecked : {}", answerCheckedDTO);
        AnswerChecked answerChecked = answerCheckedMapper.toEntity(answerCheckedDTO);
        answerChecked = answerCheckedRepository.save(answerChecked);
        return answerCheckedMapper.toDto(answerChecked);
    }

    /**
     * Partially update a answerChecked.
     *
     * @param answerCheckedDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AnswerCheckedDTO> partialUpdate(AnswerCheckedDTO answerCheckedDTO) {
        log.debug("Request to partially update AnswerChecked : {}", answerCheckedDTO);

        return answerCheckedRepository
            .findById(answerCheckedDTO.getId())
            .map(existingAnswerChecked -> {
                answerCheckedMapper.partialUpdate(existingAnswerChecked, answerCheckedDTO);

                return existingAnswerChecked;
            })
            .map(answerCheckedRepository::save)
            .map(answerCheckedMapper::toDto);
    }

    /**
     * Get all the answerCheckeds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AnswerCheckedDTO> findAll() {
        log.debug("Request to get all AnswerCheckeds");
        return answerCheckedRepository.findAll().stream().map(answerCheckedMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one answerChecked by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnswerCheckedDTO> findOne(Long id) {
        log.debug("Request to get AnswerChecked : {}", id);
        return answerCheckedRepository.findById(id).map(answerCheckedMapper::toDto);
    }

    /**
     * Delete the answerChecked by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnswerChecked : {}", id);
        answerCheckedRepository.deleteById(id);
    }
}
