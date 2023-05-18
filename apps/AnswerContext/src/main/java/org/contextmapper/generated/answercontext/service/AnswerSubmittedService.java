package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.AnswerSubmitted;
import org.contextmapper.generated.answercontext.repository.AnswerSubmittedRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmittedDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerSubmittedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnswerSubmitted}.
 */
@Service
@Transactional
public class AnswerSubmittedService {

    private final Logger log = LoggerFactory.getLogger(AnswerSubmittedService.class);

    private final AnswerSubmittedRepository answerSubmittedRepository;

    private final AnswerSubmittedMapper answerSubmittedMapper;

    public AnswerSubmittedService(AnswerSubmittedRepository answerSubmittedRepository, AnswerSubmittedMapper answerSubmittedMapper) {
        this.answerSubmittedRepository = answerSubmittedRepository;
        this.answerSubmittedMapper = answerSubmittedMapper;
    }

    /**
     * Save a answerSubmitted.
     *
     * @param answerSubmittedDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerSubmittedDTO save(AnswerSubmittedDTO answerSubmittedDTO) {
        log.debug("Request to save AnswerSubmitted : {}", answerSubmittedDTO);
        AnswerSubmitted answerSubmitted = answerSubmittedMapper.toEntity(answerSubmittedDTO);
        answerSubmitted = answerSubmittedRepository.save(answerSubmitted);
        return answerSubmittedMapper.toDto(answerSubmitted);
    }

    /**
     * Update a answerSubmitted.
     *
     * @param answerSubmittedDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerSubmittedDTO update(AnswerSubmittedDTO answerSubmittedDTO) {
        log.debug("Request to update AnswerSubmitted : {}", answerSubmittedDTO);
        AnswerSubmitted answerSubmitted = answerSubmittedMapper.toEntity(answerSubmittedDTO);
        answerSubmitted = answerSubmittedRepository.save(answerSubmitted);
        return answerSubmittedMapper.toDto(answerSubmitted);
    }

    /**
     * Partially update a answerSubmitted.
     *
     * @param answerSubmittedDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AnswerSubmittedDTO> partialUpdate(AnswerSubmittedDTO answerSubmittedDTO) {
        log.debug("Request to partially update AnswerSubmitted : {}", answerSubmittedDTO);

        return answerSubmittedRepository
            .findById(answerSubmittedDTO.getId())
            .map(existingAnswerSubmitted -> {
                answerSubmittedMapper.partialUpdate(existingAnswerSubmitted, answerSubmittedDTO);

                return existingAnswerSubmitted;
            })
            .map(answerSubmittedRepository::save)
            .map(answerSubmittedMapper::toDto);
    }

    /**
     * Get all the answerSubmitteds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AnswerSubmittedDTO> findAll() {
        log.debug("Request to get all AnswerSubmitteds");
        return answerSubmittedRepository
            .findAll()
            .stream()
            .map(answerSubmittedMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one answerSubmitted by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnswerSubmittedDTO> findOne(Long id) {
        log.debug("Request to get AnswerSubmitted : {}", id);
        return answerSubmittedRepository.findById(id).map(answerSubmittedMapper::toDto);
    }

    /**
     * Delete the answerSubmitted by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnswerSubmitted : {}", id);
        answerSubmittedRepository.deleteById(id);
    }
}
