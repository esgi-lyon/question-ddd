package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.AnswerSubmittedEvent;
import org.contextmapper.generated.answercontext.repository.AnswerSubmittedEventRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmittedEventDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerSubmittedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnswerSubmittedEvent}.
 */
@Service
@Transactional
public class AnswerSubmittedEventService {

    private final Logger log = LoggerFactory.getLogger(AnswerSubmittedEventService.class);

    private final AnswerSubmittedEventRepository answerSubmittedEventRepository;

    private final AnswerSubmittedEventMapper answerSubmittedEventMapper;

    public AnswerSubmittedEventService(
        AnswerSubmittedEventRepository answerSubmittedEventRepository,
        AnswerSubmittedEventMapper answerSubmittedEventMapper
    ) {
        this.answerSubmittedEventRepository = answerSubmittedEventRepository;
        this.answerSubmittedEventMapper = answerSubmittedEventMapper;
    }

    /**
     * Save a answerSubmittedEvent.
     *
     * @param answerSubmittedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerSubmittedEventDTO save(AnswerSubmittedEventDTO answerSubmittedEventDTO) {
        log.debug("Request to save AnswerSubmittedEvent : {}", answerSubmittedEventDTO);
        AnswerSubmittedEvent answerSubmittedEvent = answerSubmittedEventMapper.toEntity(answerSubmittedEventDTO);
        answerSubmittedEvent = answerSubmittedEventRepository.save(answerSubmittedEvent);
        return answerSubmittedEventMapper.toDto(answerSubmittedEvent);
    }

    /**
     * Update a answerSubmittedEvent.
     *
     * @param answerSubmittedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerSubmittedEventDTO update(AnswerSubmittedEventDTO answerSubmittedEventDTO) {
        log.debug("Request to update AnswerSubmittedEvent : {}", answerSubmittedEventDTO);
        AnswerSubmittedEvent answerSubmittedEvent = answerSubmittedEventMapper.toEntity(answerSubmittedEventDTO);
        answerSubmittedEvent = answerSubmittedEventRepository.save(answerSubmittedEvent);
        return answerSubmittedEventMapper.toDto(answerSubmittedEvent);
    }

    /**
     * Partially update a answerSubmittedEvent.
     *
     * @param answerSubmittedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AnswerSubmittedEventDTO> partialUpdate(AnswerSubmittedEventDTO answerSubmittedEventDTO) {
        log.debug("Request to partially update AnswerSubmittedEvent : {}", answerSubmittedEventDTO);

        return answerSubmittedEventRepository
            .findById(answerSubmittedEventDTO.getId())
            .map(existingAnswerSubmittedEvent -> {
                answerSubmittedEventMapper.partialUpdate(existingAnswerSubmittedEvent, answerSubmittedEventDTO);

                return existingAnswerSubmittedEvent;
            })
            .map(answerSubmittedEventRepository::save)
            .map(answerSubmittedEventMapper::toDto);
    }

    /**
     * Get all the answerSubmittedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AnswerSubmittedEventDTO> findAll() {
        log.debug("Request to get all AnswerSubmittedEvents");
        return answerSubmittedEventRepository
            .findAll()
            .stream()
            .map(answerSubmittedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one answerSubmittedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnswerSubmittedEventDTO> findOne(Long id) {
        log.debug("Request to get AnswerSubmittedEvent : {}", id);
        return answerSubmittedEventRepository.findById(id).map(answerSubmittedEventMapper::toDto);
    }

    /**
     * Delete the answerSubmittedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnswerSubmittedEvent : {}", id);
        answerSubmittedEventRepository.deleteById(id);
    }
}
