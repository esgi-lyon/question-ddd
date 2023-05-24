package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.NewAnswerNotifiedEvent;
import org.contextmapper.generated.evaluationcontext.repository.NewAnswerNotifiedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.NewAnswerNotifiedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.NewAnswerNotifiedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NewAnswerNotifiedEvent}.
 */
@Service
@Transactional
public class NewAnswerNotifiedEventService {

    private final Logger log = LoggerFactory.getLogger(NewAnswerNotifiedEventService.class);

    private final NewAnswerNotifiedEventRepository newAnswerNotifiedEventRepository;

    private final NewAnswerNotifiedEventMapper newAnswerNotifiedEventMapper;

    public NewAnswerNotifiedEventService(
        NewAnswerNotifiedEventRepository newAnswerNotifiedEventRepository,
        NewAnswerNotifiedEventMapper newAnswerNotifiedEventMapper
    ) {
        this.newAnswerNotifiedEventRepository = newAnswerNotifiedEventRepository;
        this.newAnswerNotifiedEventMapper = newAnswerNotifiedEventMapper;
    }

    /**
     * Save a newAnswerNotifiedEvent.
     *
     * @param newAnswerNotifiedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public NewAnswerNotifiedEventDTO save(NewAnswerNotifiedEventDTO newAnswerNotifiedEventDTO) {
        log.debug("Request to save NewAnswerNotifiedEvent : {}", newAnswerNotifiedEventDTO);
        NewAnswerNotifiedEvent newAnswerNotifiedEvent = newAnswerNotifiedEventMapper.toEntity(newAnswerNotifiedEventDTO);
        newAnswerNotifiedEvent = newAnswerNotifiedEventRepository.save(newAnswerNotifiedEvent);
        return newAnswerNotifiedEventMapper.toDto(newAnswerNotifiedEvent);
    }

    /**
     * Update a newAnswerNotifiedEvent.
     *
     * @param newAnswerNotifiedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public NewAnswerNotifiedEventDTO update(NewAnswerNotifiedEventDTO newAnswerNotifiedEventDTO) {
        log.debug("Request to update NewAnswerNotifiedEvent : {}", newAnswerNotifiedEventDTO);
        NewAnswerNotifiedEvent newAnswerNotifiedEvent = newAnswerNotifiedEventMapper.toEntity(newAnswerNotifiedEventDTO);
        newAnswerNotifiedEvent = newAnswerNotifiedEventRepository.save(newAnswerNotifiedEvent);
        return newAnswerNotifiedEventMapper.toDto(newAnswerNotifiedEvent);
    }

    /**
     * Partially update a newAnswerNotifiedEvent.
     *
     * @param newAnswerNotifiedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NewAnswerNotifiedEventDTO> partialUpdate(NewAnswerNotifiedEventDTO newAnswerNotifiedEventDTO) {
        log.debug("Request to partially update NewAnswerNotifiedEvent : {}", newAnswerNotifiedEventDTO);

        return newAnswerNotifiedEventRepository
            .findById(newAnswerNotifiedEventDTO.getId())
            .map(existingNewAnswerNotifiedEvent -> {
                newAnswerNotifiedEventMapper.partialUpdate(existingNewAnswerNotifiedEvent, newAnswerNotifiedEventDTO);

                return existingNewAnswerNotifiedEvent;
            })
            .map(newAnswerNotifiedEventRepository::save)
            .map(newAnswerNotifiedEventMapper::toDto);
    }

    /**
     * Get all the newAnswerNotifiedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NewAnswerNotifiedEventDTO> findAll() {
        log.debug("Request to get all NewAnswerNotifiedEvents");
        return newAnswerNotifiedEventRepository
            .findAll()
            .stream()
            .map(newAnswerNotifiedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one newAnswerNotifiedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NewAnswerNotifiedEventDTO> findOne(Long id) {
        log.debug("Request to get NewAnswerNotifiedEvent : {}", id);
        return newAnswerNotifiedEventRepository.findById(id).map(newAnswerNotifiedEventMapper::toDto);
    }

    /**
     * Delete the newAnswerNotifiedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NewAnswerNotifiedEvent : {}", id);
        newAnswerNotifiedEventRepository.deleteById(id);
    }
}
