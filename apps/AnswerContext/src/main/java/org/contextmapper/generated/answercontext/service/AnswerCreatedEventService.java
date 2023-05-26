package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.AnswerCreatedEvent;
import org.contextmapper.generated.answercontext.repository.AnswerCreatedEventRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerCreatedEventDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerCreatedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnswerCreatedEvent}.
 */
@Service
@Transactional
public class AnswerCreatedEventService {

    private final Logger log = LoggerFactory.getLogger(AnswerCreatedEventService.class);

    private final AnswerCreatedEventRepository answerCreatedEventRepository;

    private final AnswerCreatedEventMapper answerCreatedEventMapper;

    public AnswerCreatedEventService(
        AnswerCreatedEventRepository answerCreatedEventRepository,
        AnswerCreatedEventMapper answerCreatedEventMapper
    ) {
        this.answerCreatedEventRepository = answerCreatedEventRepository;
        this.answerCreatedEventMapper = answerCreatedEventMapper;
    }

    /**
     * Save a answerCreatedEvent.
     *
     * @param answerCreatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerCreatedEventDTO save(AnswerCreatedEventDTO answerCreatedEventDTO) {
        log.debug("Request to save AnswerCreatedEvent : {}", answerCreatedEventDTO);
        AnswerCreatedEvent answerCreatedEvent = answerCreatedEventMapper.toEntity(answerCreatedEventDTO);
        answerCreatedEvent = answerCreatedEventRepository.save(answerCreatedEvent);
        return answerCreatedEventMapper.toDto(answerCreatedEvent);
    }

    /**
     * Update a answerCreatedEvent.
     *
     * @param answerCreatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerCreatedEventDTO update(AnswerCreatedEventDTO answerCreatedEventDTO) {
        log.debug("Request to update AnswerCreatedEvent : {}", answerCreatedEventDTO);
        AnswerCreatedEvent answerCreatedEvent = answerCreatedEventMapper.toEntity(answerCreatedEventDTO);
        answerCreatedEvent = answerCreatedEventRepository.save(answerCreatedEvent);
        return answerCreatedEventMapper.toDto(answerCreatedEvent);
    }

    /**
     * Partially update a answerCreatedEvent.
     *
     * @param answerCreatedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AnswerCreatedEventDTO> partialUpdate(AnswerCreatedEventDTO answerCreatedEventDTO) {
        log.debug("Request to partially update AnswerCreatedEvent : {}", answerCreatedEventDTO);

        return answerCreatedEventRepository
            .findById(answerCreatedEventDTO.getId())
            .map(existingAnswerCreatedEvent -> {
                answerCreatedEventMapper.partialUpdate(existingAnswerCreatedEvent, answerCreatedEventDTO);

                return existingAnswerCreatedEvent;
            })
            .map(answerCreatedEventRepository::save)
            .map(answerCreatedEventMapper::toDto);
    }

    /**
     * Get all the answerCreatedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AnswerCreatedEventDTO> findAll() {
        log.debug("Request to get all AnswerCreatedEvents");
        return answerCreatedEventRepository
            .findAll()
            .stream()
            .map(answerCreatedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one answerCreatedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnswerCreatedEventDTO> findOne(Long id) {
        log.debug("Request to get AnswerCreatedEvent : {}", id);
        return answerCreatedEventRepository.findById(id).map(answerCreatedEventMapper::toDto);
    }

    /**
     * Delete the answerCreatedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnswerCreatedEvent : {}", id);
        answerCreatedEventRepository.deleteById(id);
    }
}
