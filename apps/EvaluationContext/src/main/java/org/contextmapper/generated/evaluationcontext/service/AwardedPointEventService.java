package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.AwardedPointEvent;
import org.contextmapper.generated.evaluationcontext.repository.AwardedPointEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardedPointEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AwardedPointEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AwardedPointEvent}.
 */
@Service
@Transactional
public class AwardedPointEventService {

    private final Logger log = LoggerFactory.getLogger(AwardedPointEventService.class);

    private final AwardedPointEventRepository awardedPointEventRepository;

    private final AwardedPointEventMapper awardedPointEventMapper;

    public AwardedPointEventService(
        AwardedPointEventRepository awardedPointEventRepository,
        AwardedPointEventMapper awardedPointEventMapper
    ) {
        this.awardedPointEventRepository = awardedPointEventRepository;
        this.awardedPointEventMapper = awardedPointEventMapper;
    }

    /**
     * Save a awardedPointEvent.
     *
     * @param awardedPointEventDTO the entity to save.
     * @return the persisted entity.
     */
    public AwardedPointEventDTO save(AwardedPointEventDTO awardedPointEventDTO) {
        log.debug("Request to save AwardedPointEvent : {}", awardedPointEventDTO);
        AwardedPointEvent awardedPointEvent = awardedPointEventMapper.toEntity(awardedPointEventDTO);
        awardedPointEvent = awardedPointEventRepository.save(awardedPointEvent);
        return awardedPointEventMapper.toDto(awardedPointEvent);
    }

    /**
     * Update a awardedPointEvent.
     *
     * @param awardedPointEventDTO the entity to save.
     * @return the persisted entity.
     */
    public AwardedPointEventDTO update(AwardedPointEventDTO awardedPointEventDTO) {
        log.debug("Request to update AwardedPointEvent : {}", awardedPointEventDTO);
        AwardedPointEvent awardedPointEvent = awardedPointEventMapper.toEntity(awardedPointEventDTO);
        awardedPointEvent = awardedPointEventRepository.save(awardedPointEvent);
        return awardedPointEventMapper.toDto(awardedPointEvent);
    }

    /**
     * Partially update a awardedPointEvent.
     *
     * @param awardedPointEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AwardedPointEventDTO> partialUpdate(AwardedPointEventDTO awardedPointEventDTO) {
        log.debug("Request to partially update AwardedPointEvent : {}", awardedPointEventDTO);

        return awardedPointEventRepository
            .findById(awardedPointEventDTO.getId())
            .map(existingAwardedPointEvent -> {
                awardedPointEventMapper.partialUpdate(existingAwardedPointEvent, awardedPointEventDTO);

                return existingAwardedPointEvent;
            })
            .map(awardedPointEventRepository::save)
            .map(awardedPointEventMapper::toDto);
    }

    /**
     * Get all the awardedPointEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AwardedPointEventDTO> findAll() {
        log.debug("Request to get all AwardedPointEvents");
        return awardedPointEventRepository
            .findAll()
            .stream()
            .map(awardedPointEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one awardedPointEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AwardedPointEventDTO> findOne(Long id) {
        log.debug("Request to get AwardedPointEvent : {}", id);
        return awardedPointEventRepository.findById(id).map(awardedPointEventMapper::toDto);
    }

    /**
     * Delete the awardedPointEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AwardedPointEvent : {}", id);
        awardedPointEventRepository.deleteById(id);
    }
}
