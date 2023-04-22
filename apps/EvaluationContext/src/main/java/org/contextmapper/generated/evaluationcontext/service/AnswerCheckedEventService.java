package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.AnswerCheckedEvent;
import org.contextmapper.generated.evaluationcontext.repository.AnswerCheckedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AnswerCheckedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AnswerCheckedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnswerCheckedEvent}.
 */
@Service
@Transactional
public class AnswerCheckedEventService {

    private final Logger log = LoggerFactory.getLogger(AnswerCheckedEventService.class);

    private final AnswerCheckedEventRepository answerCheckedEventRepository;

    private final AnswerCheckedEventMapper answerCheckedEventMapper;

    public AnswerCheckedEventService(
        AnswerCheckedEventRepository answerCheckedEventRepository,
        AnswerCheckedEventMapper answerCheckedEventMapper
    ) {
        this.answerCheckedEventRepository = answerCheckedEventRepository;
        this.answerCheckedEventMapper = answerCheckedEventMapper;
    }

    /**
     * Save a answerCheckedEvent.
     *
     * @param answerCheckedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerCheckedEventDTO save(AnswerCheckedEventDTO answerCheckedEventDTO) {
        log.debug("Request to save AnswerCheckedEvent : {}", answerCheckedEventDTO);
        AnswerCheckedEvent answerCheckedEvent = answerCheckedEventMapper.toEntity(answerCheckedEventDTO);
        answerCheckedEvent = answerCheckedEventRepository.save(answerCheckedEvent);
        return answerCheckedEventMapper.toDto(answerCheckedEvent);
    }

    /**
     * Update a answerCheckedEvent.
     *
     * @param answerCheckedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerCheckedEventDTO update(AnswerCheckedEventDTO answerCheckedEventDTO) {
        log.debug("Request to update AnswerCheckedEvent : {}", answerCheckedEventDTO);
        AnswerCheckedEvent answerCheckedEvent = answerCheckedEventMapper.toEntity(answerCheckedEventDTO);
        // no save call needed as we have no fields that can be updated
        return answerCheckedEventMapper.toDto(answerCheckedEvent);
    }

    /**
     * Partially update a answerCheckedEvent.
     *
     * @param answerCheckedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AnswerCheckedEventDTO> partialUpdate(AnswerCheckedEventDTO answerCheckedEventDTO) {
        log.debug("Request to partially update AnswerCheckedEvent : {}", answerCheckedEventDTO);

        return answerCheckedEventRepository
            .findById(answerCheckedEventDTO.getId())
            .map(existingAnswerCheckedEvent -> {
                answerCheckedEventMapper.partialUpdate(existingAnswerCheckedEvent, answerCheckedEventDTO);

                return existingAnswerCheckedEvent;
            })
            // .map(answerCheckedEventRepository::save)
            .map(answerCheckedEventMapper::toDto);
    }

    /**
     * Get all the answerCheckedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AnswerCheckedEventDTO> findAll() {
        log.debug("Request to get all AnswerCheckedEvents");
        return answerCheckedEventRepository
            .findAll()
            .stream()
            .map(answerCheckedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one answerCheckedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnswerCheckedEventDTO> findOne(Long id) {
        log.debug("Request to get AnswerCheckedEvent : {}", id);
        return answerCheckedEventRepository.findById(id).map(answerCheckedEventMapper::toDto);
    }

    /**
     * Delete the answerCheckedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnswerCheckedEvent : {}", id);
        answerCheckedEventRepository.deleteById(id);
    }
}
