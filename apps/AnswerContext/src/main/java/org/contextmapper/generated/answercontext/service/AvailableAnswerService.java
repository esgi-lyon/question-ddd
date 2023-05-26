package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.AvailableAnswer;
import org.contextmapper.generated.answercontext.repository.AvailableAnswerRepository;
import org.contextmapper.generated.answercontext.service.dto.AvailableAnswerDTO;
import org.contextmapper.generated.answercontext.service.mapper.AvailableAnswerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AvailableAnswer}.
 */
@Service
@Transactional
public class AvailableAnswerService {

    private final Logger log = LoggerFactory.getLogger(AvailableAnswerService.class);

    private final AvailableAnswerRepository availableAnswerRepository;

    private final AvailableAnswerMapper availableAnswerMapper;

    public AvailableAnswerService(AvailableAnswerRepository availableAnswerRepository, AvailableAnswerMapper availableAnswerMapper) {
        this.availableAnswerRepository = availableAnswerRepository;
        this.availableAnswerMapper = availableAnswerMapper;
    }

    /**
     * Save a availableAnswer.
     *
     * @param availableAnswerDTO the entity to save.
     * @return the persisted entity.
     */
    public AvailableAnswerDTO save(AvailableAnswerDTO availableAnswerDTO) {
        log.debug("Request to save AvailableAnswer : {}", availableAnswerDTO);
        AvailableAnswer availableAnswer = availableAnswerMapper.toEntity(availableAnswerDTO);
        availableAnswer = availableAnswerRepository.save(availableAnswer);
        return availableAnswerMapper.toDto(availableAnswer);
    }

    /**
     * Update a availableAnswer.
     *
     * @param availableAnswerDTO the entity to save.
     * @return the persisted entity.
     */
    public AvailableAnswerDTO update(AvailableAnswerDTO availableAnswerDTO) {
        log.debug("Request to update AvailableAnswer : {}", availableAnswerDTO);
        AvailableAnswer availableAnswer = availableAnswerMapper.toEntity(availableAnswerDTO);
        availableAnswer = availableAnswerRepository.save(availableAnswer);
        return availableAnswerMapper.toDto(availableAnswer);
    }

    /**
     * Partially update a availableAnswer.
     *
     * @param availableAnswerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AvailableAnswerDTO> partialUpdate(AvailableAnswerDTO availableAnswerDTO) {
        log.debug("Request to partially update AvailableAnswer : {}", availableAnswerDTO);

        return availableAnswerRepository
            .findById(availableAnswerDTO.getId())
            .map(existingAvailableAnswer -> {
                availableAnswerMapper.partialUpdate(existingAvailableAnswer, availableAnswerDTO);

                return existingAvailableAnswer;
            })
            .map(availableAnswerRepository::save)
            .map(availableAnswerMapper::toDto);
    }

    /**
     * Get all the availableAnswers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AvailableAnswerDTO> findAll() {
        log.debug("Request to get all AvailableAnswers");
        return availableAnswerRepository
            .findAll()
            .stream()
            .map(availableAnswerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one availableAnswer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AvailableAnswerDTO> findOne(Long id) {
        log.debug("Request to get AvailableAnswer : {}", id);
        return availableAnswerRepository.findById(id).map(availableAnswerMapper::toDto);
    }

    /**
     * Delete the availableAnswer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AvailableAnswer : {}", id);
        availableAnswerRepository.deleteById(id);
    }
}
