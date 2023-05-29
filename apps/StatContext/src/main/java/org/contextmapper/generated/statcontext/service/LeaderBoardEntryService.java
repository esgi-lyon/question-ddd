package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.LeaderBoardEntry;
import org.contextmapper.generated.statcontext.repository.LeaderBoardEntryRepository;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardEntryDTO;
import org.contextmapper.generated.statcontext.service.mapper.LeaderBoardEntryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link LeaderBoardEntry}.
 */
@Service
@Transactional
public class LeaderBoardEntryService {

    private final Logger log = LoggerFactory.getLogger(LeaderBoardEntryService.class);

    private final LeaderBoardEntryRepository leaderBoardEntryRepository;

    private final LeaderBoardEntryMapper leaderBoardEntryMapper;

    public LeaderBoardEntryService(LeaderBoardEntryRepository leaderBoardEntryRepository, LeaderBoardEntryMapper leaderBoardEntryMapper) {
        this.leaderBoardEntryRepository = leaderBoardEntryRepository;
        this.leaderBoardEntryMapper = leaderBoardEntryMapper;
    }

    /**
     * Save a leaderBoardEntry.
     *
     * @param leaderBoardEntryDTO the entity to save.
     * @return the persisted entity.
     */
    public LeaderBoardEntryDTO save(LeaderBoardEntryDTO leaderBoardEntryDTO) {
        log.debug("Request to save LeaderBoardEntry : {}", leaderBoardEntryDTO);
        LeaderBoardEntry leaderBoardEntry = leaderBoardEntryMapper.toEntity(leaderBoardEntryDTO);
        leaderBoardEntry = leaderBoardEntryRepository.save(leaderBoardEntry);
        return leaderBoardEntryMapper.toDto(leaderBoardEntry);
    }

    /**
     * Update a leaderBoardEntry.
     *
     * @param leaderBoardEntryDTO the entity to save.
     * @return the persisted entity.
     */
    public LeaderBoardEntryDTO update(LeaderBoardEntryDTO leaderBoardEntryDTO) {
        log.debug("Request to update LeaderBoardEntry : {}", leaderBoardEntryDTO);
        LeaderBoardEntry leaderBoardEntry = leaderBoardEntryMapper.toEntity(leaderBoardEntryDTO);
        leaderBoardEntry = leaderBoardEntryRepository.save(leaderBoardEntry);
        return leaderBoardEntryMapper.toDto(leaderBoardEntry);
    }

    /**
     * Partially update a leaderBoardEntry.
     *
     * @param leaderBoardEntryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LeaderBoardEntryDTO> partialUpdate(LeaderBoardEntryDTO leaderBoardEntryDTO) {
        log.debug("Request to partially update LeaderBoardEntry : {}", leaderBoardEntryDTO);

        return leaderBoardEntryRepository
            .findById(leaderBoardEntryDTO.getId())
            .map(existingLeaderBoardEntry -> {
                leaderBoardEntryMapper.partialUpdate(existingLeaderBoardEntry, leaderBoardEntryDTO);

                return existingLeaderBoardEntry;
            })
            .map(leaderBoardEntryRepository::save)
            .map(leaderBoardEntryMapper::toDto);
    }

    /**
     * Get all the leaderBoardEntries.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LeaderBoardEntryDTO> findAll() {
        log.debug("Request to get all LeaderBoardEntries");
        return leaderBoardEntryRepository
            .findAll()
            .stream()
            .map(leaderBoardEntryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one leaderBoardEntry by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LeaderBoardEntryDTO> findOne(Long id) {
        log.debug("Request to get LeaderBoardEntry : {}", id);
        return leaderBoardEntryRepository.findById(id).map(leaderBoardEntryMapper::toDto);
    }

    /**
     * Delete the leaderBoardEntry by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LeaderBoardEntry : {}", id);
        leaderBoardEntryRepository.deleteById(id);
    }
}
