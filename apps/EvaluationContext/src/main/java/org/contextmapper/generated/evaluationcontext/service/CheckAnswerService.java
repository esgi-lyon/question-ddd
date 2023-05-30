package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.CheckAnswer;
import org.contextmapper.generated.evaluationcontext.repository.CheckAnswerRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.CheckAnswerDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.CheckAnswerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CheckAnswer}.
 */
@Service
@Transactional
public class CheckAnswerService {

    private final Logger log = LoggerFactory.getLogger(CheckAnswerService.class);

    private final CheckAnswerRepository checkAnswerRepository;

    private final CheckAnswerMapper checkAnswerMapper;

    public CheckAnswerService(CheckAnswerRepository checkAnswerRepository, CheckAnswerMapper checkAnswerMapper) {
        this.checkAnswerRepository = checkAnswerRepository;
        this.checkAnswerMapper = checkAnswerMapper;
    }

    /**
     * Save a checkAnswer.
     *
     * @param checkAnswerDTO the entity to save.
     * @return the persisted entity.
     */
    public CheckAnswerDTO save(CheckAnswerDTO checkAnswerDTO) {
        log.debug("Request to save CheckAnswer : {}", checkAnswerDTO);
        CheckAnswer checkAnswer = checkAnswerMapper.toEntity(checkAnswerDTO);
        checkAnswer = checkAnswerRepository.save(checkAnswer);
        return checkAnswerMapper.toDto(checkAnswer);
    }

    /**
     * Update a checkAnswer.
     *
     * @param checkAnswerDTO the entity to save.
     * @return the persisted entity.
     */
    public CheckAnswerDTO update(CheckAnswerDTO checkAnswerDTO) {
        log.debug("Request to update CheckAnswer : {}", checkAnswerDTO);
        CheckAnswer checkAnswer = checkAnswerMapper.toEntity(checkAnswerDTO);
        checkAnswer = checkAnswerRepository.save(checkAnswer);
        return checkAnswerMapper.toDto(checkAnswer);
    }

    /**
     * Partially update a checkAnswer.
     *
     * @param checkAnswerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CheckAnswerDTO> partialUpdate(CheckAnswerDTO checkAnswerDTO) {
        log.debug("Request to partially update CheckAnswer : {}", checkAnswerDTO);

        return checkAnswerRepository
            .findById(checkAnswerDTO.getId())
            .map(existingCheckAnswer -> {
                checkAnswerMapper.partialUpdate(existingCheckAnswer, checkAnswerDTO);

                return existingCheckAnswer;
            })
            .map(checkAnswerRepository::save)
            .map(checkAnswerMapper::toDto);
    }

    /**
     * Get all the checkAnswers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CheckAnswerDTO> findAll() {
        log.debug("Request to get all CheckAnswers");
        return checkAnswerRepository.findAll().stream().map(checkAnswerMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one checkAnswer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CheckAnswerDTO> findOne(Long id) {
        log.debug("Request to get CheckAnswer : {}", id);
        return checkAnswerRepository.findById(id).map(checkAnswerMapper::toDto);
    }

    /**
     * Delete the checkAnswer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CheckAnswer : {}", id);
        checkAnswerRepository.deleteById(id);
    }
}
