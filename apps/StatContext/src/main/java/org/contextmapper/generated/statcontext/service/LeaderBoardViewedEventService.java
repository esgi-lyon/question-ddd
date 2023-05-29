package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.LeaderBoardViewedEvent;
import org.contextmapper.generated.statcontext.repository.LeaderBoardViewedEventRepository;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardViewedEventDTO;
import org.contextmapper.generated.statcontext.service.mapper.LeaderBoardViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link LeaderBoardViewedEvent}.
 */
@Service
@Transactional
public class LeaderBoardViewedEventService {

    private final Logger log = LoggerFactory.getLogger(LeaderBoardViewedEventService.class);

    private final LeaderBoardViewedEventRepository leaderBoardViewedEventRepository;

    private final LeaderBoardViewedEventMapper leaderBoardViewedEventMapper;

    public LeaderBoardViewedEventService(
        LeaderBoardViewedEventRepository leaderBoardViewedEventRepository,
        LeaderBoardViewedEventMapper leaderBoardViewedEventMapper
    ) {
        this.leaderBoardViewedEventRepository = leaderBoardViewedEventRepository;
        this.leaderBoardViewedEventMapper = leaderBoardViewedEventMapper;
    }

    /**
     * Save a leaderBoardViewedEvent.
     *
     * @param leaderBoardViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public LeaderBoardViewedEventDTO save(LeaderBoardViewedEventDTO leaderBoardViewedEventDTO) {
        log.debug("Request to save LeaderBoardViewedEvent : {}", leaderBoardViewedEventDTO);
        LeaderBoardViewedEvent leaderBoardViewedEvent = leaderBoardViewedEventMapper.toEntity(leaderBoardViewedEventDTO);
        leaderBoardViewedEvent = leaderBoardViewedEventRepository.save(leaderBoardViewedEvent);
        return leaderBoardViewedEventMapper.toDto(leaderBoardViewedEvent);
    }

    /**
     * Update a leaderBoardViewedEvent.
     *
     * @param leaderBoardViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public LeaderBoardViewedEventDTO update(LeaderBoardViewedEventDTO leaderBoardViewedEventDTO) {
        log.debug("Request to update LeaderBoardViewedEvent : {}", leaderBoardViewedEventDTO);
        LeaderBoardViewedEvent leaderBoardViewedEvent = leaderBoardViewedEventMapper.toEntity(leaderBoardViewedEventDTO);
        leaderBoardViewedEvent = leaderBoardViewedEventRepository.save(leaderBoardViewedEvent);
        return leaderBoardViewedEventMapper.toDto(leaderBoardViewedEvent);
    }

    /**
     * Partially update a leaderBoardViewedEvent.
     *
     * @param leaderBoardViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LeaderBoardViewedEventDTO> partialUpdate(LeaderBoardViewedEventDTO leaderBoardViewedEventDTO) {
        log.debug("Request to partially update LeaderBoardViewedEvent : {}", leaderBoardViewedEventDTO);

        return leaderBoardViewedEventRepository
            .findById(leaderBoardViewedEventDTO.getId())
            .map(existingLeaderBoardViewedEvent -> {
                leaderBoardViewedEventMapper.partialUpdate(existingLeaderBoardViewedEvent, leaderBoardViewedEventDTO);

                return existingLeaderBoardViewedEvent;
            })
            .map(leaderBoardViewedEventRepository::save)
            .map(leaderBoardViewedEventMapper::toDto);
    }

    /**
     * Get all the leaderBoardViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LeaderBoardViewedEventDTO> findAll() {
        log.debug("Request to get all LeaderBoardViewedEvents");
        return leaderBoardViewedEventRepository
            .findAll()
            .stream()
            .map(leaderBoardViewedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one leaderBoardViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LeaderBoardViewedEventDTO> findOne(Long id) {
        log.debug("Request to get LeaderBoardViewedEvent : {}", id);
        return leaderBoardViewedEventRepository.findById(id).map(leaderBoardViewedEventMapper::toDto);
    }

    /**
     * Delete the leaderBoardViewedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LeaderBoardViewedEvent : {}", id);
        leaderBoardViewedEventRepository.deleteById(id);
    }
}
