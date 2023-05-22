package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.NotifiedQuestionEvent;
import org.contextmapper.generated.gateway.repository.NotifiedQuestionEventRepository;
import org.contextmapper.generated.gateway.service.dto.NotifiedQuestionEventDTO;
import org.contextmapper.generated.gateway.service.mapper.NotifiedQuestionEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<NotifiedQuestionEventDTO> save(NotifiedQuestionEventDTO notifiedQuestionEventDTO) {
        log.debug("Request to save NotifiedQuestionEvent : {}", notifiedQuestionEventDTO);
        return notifiedQuestionEventRepository
            .save(notifiedQuestionEventMapper.toEntity(notifiedQuestionEventDTO))
            .map(notifiedQuestionEventMapper::toDto);
    }

    /**
     * Update a notifiedQuestionEvent.
     *
     * @param notifiedQuestionEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<NotifiedQuestionEventDTO> update(NotifiedQuestionEventDTO notifiedQuestionEventDTO) {
        log.debug("Request to update NotifiedQuestionEvent : {}", notifiedQuestionEventDTO);
        return notifiedQuestionEventRepository
            .save(notifiedQuestionEventMapper.toEntity(notifiedQuestionEventDTO))
            .map(notifiedQuestionEventMapper::toDto);
    }

    /**
     * Partially update a notifiedQuestionEvent.
     *
     * @param notifiedQuestionEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<NotifiedQuestionEventDTO> partialUpdate(NotifiedQuestionEventDTO notifiedQuestionEventDTO) {
        log.debug("Request to partially update NotifiedQuestionEvent : {}", notifiedQuestionEventDTO);

        return notifiedQuestionEventRepository
            .findById(notifiedQuestionEventDTO.getId())
            .map(existingNotifiedQuestionEvent -> {
                notifiedQuestionEventMapper.partialUpdate(existingNotifiedQuestionEvent, notifiedQuestionEventDTO);

                return existingNotifiedQuestionEvent;
            })
            .flatMap(notifiedQuestionEventRepository::save)
            .map(notifiedQuestionEventMapper::toDto);
    }

    /**
     * Get all the notifiedQuestionEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<NotifiedQuestionEventDTO> findAll() {
        log.debug("Request to get all NotifiedQuestionEvents");
        return notifiedQuestionEventRepository.findAll().map(notifiedQuestionEventMapper::toDto);
    }

    /**
     * Returns the number of notifiedQuestionEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return notifiedQuestionEventRepository.count();
    }

    /**
     * Get one notifiedQuestionEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<NotifiedQuestionEventDTO> findOne(Long id) {
        log.debug("Request to get NotifiedQuestionEvent : {}", id);
        return notifiedQuestionEventRepository.findById(id).map(notifiedQuestionEventMapper::toDto);
    }

    /**
     * Delete the notifiedQuestionEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete NotifiedQuestionEvent : {}", id);
        return notifiedQuestionEventRepository.deleteById(id);
    }
}
