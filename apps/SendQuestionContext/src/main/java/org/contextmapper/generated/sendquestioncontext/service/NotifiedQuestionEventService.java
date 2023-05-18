package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.NotifiedQuestionEvent;
import org.contextmapper.generated.sendquestioncontext.repository.NotifiedQuestionEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedQuestionEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.NotifiedQuestionEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NotifiedQuestionEvent}.
 */
@Service
@Transactional
public class NotifiedQuestionEventService {

    private final Logger log = LoggerFactory.getLogger(NotifiedQuestionEventService.class);

    private final NotifiedQuestionEventRepository notifiedQuestionEventRepository;

    private final NotifiedQuestionEventMapper notifiedQuestionEventMapper;

    public NotifiedQuestionEventService(
        NotifiedQuestionEventRepository notifiedQuestionEventRepository,
        NotifiedQuestionEventMapper notifiedQuestionEventMapper
    ) {
        this.notifiedQuestionEventRepository = notifiedQuestionEventRepository;
        this.notifiedQuestionEventMapper = notifiedQuestionEventMapper;
    }

    /**
     * Save a notifiedQuestionEvent.
     *
     * @param notifiedQuestionEventDTO the entity to save.
     * @return the persisted entity.
     */
    public NotifiedQuestionEventDTO save(NotifiedQuestionEventDTO notifiedQuestionEventDTO) {
        log.debug("Request to save NotifiedQuestionEvent : {}", notifiedQuestionEventDTO);
        NotifiedQuestionEvent notifiedQuestionEvent = notifiedQuestionEventMapper.toEntity(notifiedQuestionEventDTO);
        notifiedQuestionEvent = notifiedQuestionEventRepository.save(notifiedQuestionEvent);
        return notifiedQuestionEventMapper.toDto(notifiedQuestionEvent);
    }

    /**
     * Update a notifiedQuestionEvent.
     *
     * @param notifiedQuestionEventDTO the entity to save.
     * @return the persisted entity.
     */
    public NotifiedQuestionEventDTO update(NotifiedQuestionEventDTO notifiedQuestionEventDTO) {
        log.debug("Request to update NotifiedQuestionEvent : {}", notifiedQuestionEventDTO);
        NotifiedQuestionEvent notifiedQuestionEvent = notifiedQuestionEventMapper.toEntity(notifiedQuestionEventDTO);
        notifiedQuestionEvent = notifiedQuestionEventRepository.save(notifiedQuestionEvent);
        return notifiedQuestionEventMapper.toDto(notifiedQuestionEvent);
    }

    /**
     * Partially update a notifiedQuestionEvent.
     *
     * @param notifiedQuestionEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NotifiedQuestionEventDTO> partialUpdate(NotifiedQuestionEventDTO notifiedQuestionEventDTO) {
        log.debug("Request to partially update NotifiedQuestionEvent : {}", notifiedQuestionEventDTO);

        return notifiedQuestionEventRepository
            .findById(notifiedQuestionEventDTO.getId())
            .map(existingNotifiedQuestionEvent -> {
                notifiedQuestionEventMapper.partialUpdate(existingNotifiedQuestionEvent, notifiedQuestionEventDTO);

                return existingNotifiedQuestionEvent;
            })
            .map(notifiedQuestionEventRepository::save)
            .map(notifiedQuestionEventMapper::toDto);
    }

    /**
     * Get all the notifiedQuestionEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NotifiedQuestionEventDTO> findAll() {
        log.debug("Request to get all NotifiedQuestionEvents");
        return notifiedQuestionEventRepository
            .findAll()
            .stream()
            .map(notifiedQuestionEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one notifiedQuestionEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NotifiedQuestionEventDTO> findOne(Long id) {
        log.debug("Request to get NotifiedQuestionEvent : {}", id);
        return notifiedQuestionEventRepository.findById(id).map(notifiedQuestionEventMapper::toDto);
    }

    /**
     * Delete the notifiedQuestionEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NotifiedQuestionEvent : {}", id);
        notifiedQuestionEventRepository.deleteById(id);
    }
}
