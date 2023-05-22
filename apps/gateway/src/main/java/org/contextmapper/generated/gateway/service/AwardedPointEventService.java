package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.AwardedPointEvent;
import org.contextmapper.generated.gateway.repository.AwardedPointEventRepository;
import org.contextmapper.generated.gateway.service.dto.AwardedPointEventDTO;
import org.contextmapper.generated.gateway.service.mapper.AwardedPointEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<AwardedPointEventDTO> save(AwardedPointEventDTO awardedPointEventDTO) {
        log.debug("Request to save AwardedPointEvent : {}", awardedPointEventDTO);
        return awardedPointEventRepository.save(awardedPointEventMapper.toEntity(awardedPointEventDTO)).map(awardedPointEventMapper::toDto);
    }

    /**
     * Update a awardedPointEvent.
     *
     * @param awardedPointEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AwardedPointEventDTO> update(AwardedPointEventDTO awardedPointEventDTO) {
        log.debug("Request to update AwardedPointEvent : {}", awardedPointEventDTO);
        return awardedPointEventRepository.save(awardedPointEventMapper.toEntity(awardedPointEventDTO)).map(awardedPointEventMapper::toDto);
    }

    /**
     * Partially update a awardedPointEvent.
     *
     * @param awardedPointEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<AwardedPointEventDTO> partialUpdate(AwardedPointEventDTO awardedPointEventDTO) {
        log.debug("Request to partially update AwardedPointEvent : {}", awardedPointEventDTO);

        return awardedPointEventRepository
            .findById(awardedPointEventDTO.getId())
            .map(existingAwardedPointEvent -> {
                awardedPointEventMapper.partialUpdate(existingAwardedPointEvent, awardedPointEventDTO);

                return existingAwardedPointEvent;
            })
            .flatMap(awardedPointEventRepository::save)
            .map(awardedPointEventMapper::toDto);
    }

    /**
     * Get all the awardedPointEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<AwardedPointEventDTO> findAll() {
        log.debug("Request to get all AwardedPointEvents");
        return awardedPointEventRepository.findAll().map(awardedPointEventMapper::toDto);
    }

    /**
     * Returns the number of awardedPointEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return awardedPointEventRepository.count();
    }

    /**
     * Get one awardedPointEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<AwardedPointEventDTO> findOne(Long id) {
        log.debug("Request to get AwardedPointEvent : {}", id);
        return awardedPointEventRepository.findById(id).map(awardedPointEventMapper::toDto);
    }

    /**
     * Delete the awardedPointEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete AwardedPointEvent : {}", id);
        return awardedPointEventRepository.deleteById(id);
    }
}
