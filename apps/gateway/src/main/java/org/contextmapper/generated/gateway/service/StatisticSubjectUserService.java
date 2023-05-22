package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.StatisticSubjectUser;
import org.contextmapper.generated.gateway.repository.StatisticSubjectUserRepository;
import org.contextmapper.generated.gateway.service.dto.StatisticSubjectUserDTO;
import org.contextmapper.generated.gateway.service.mapper.StatisticSubjectUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link StatisticSubjectUser}.
 */
@Service
@Transactional
public class StatisticSubjectUserService {

    private final Logger log = LoggerFactory.getLogger(StatisticSubjectUserService.class);

    private final StatisticSubjectUserRepository statisticSubjectUserRepository;

    private final StatisticSubjectUserMapper statisticSubjectUserMapper;

    public StatisticSubjectUserService(
        StatisticSubjectUserRepository statisticSubjectUserRepository,
        StatisticSubjectUserMapper statisticSubjectUserMapper
    ) {
        this.statisticSubjectUserRepository = statisticSubjectUserRepository;
        this.statisticSubjectUserMapper = statisticSubjectUserMapper;
    }

    /**
     * Save a statisticSubjectUser.
     *
     * @param statisticSubjectUserDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<StatisticSubjectUserDTO> save(StatisticSubjectUserDTO statisticSubjectUserDTO) {
        log.debug("Request to save StatisticSubjectUser : {}", statisticSubjectUserDTO);
        return statisticSubjectUserRepository
            .save(statisticSubjectUserMapper.toEntity(statisticSubjectUserDTO))
            .map(statisticSubjectUserMapper::toDto);
    }

    /**
     * Update a statisticSubjectUser.
     *
     * @param statisticSubjectUserDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<StatisticSubjectUserDTO> update(StatisticSubjectUserDTO statisticSubjectUserDTO) {
        log.debug("Request to update StatisticSubjectUser : {}", statisticSubjectUserDTO);
        return statisticSubjectUserRepository
            .save(statisticSubjectUserMapper.toEntity(statisticSubjectUserDTO))
            .map(statisticSubjectUserMapper::toDto);
    }

    /**
     * Partially update a statisticSubjectUser.
     *
     * @param statisticSubjectUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<StatisticSubjectUserDTO> partialUpdate(StatisticSubjectUserDTO statisticSubjectUserDTO) {
        log.debug("Request to partially update StatisticSubjectUser : {}", statisticSubjectUserDTO);

        return statisticSubjectUserRepository
            .findById(statisticSubjectUserDTO.getId())
            .map(existingStatisticSubjectUser -> {
                statisticSubjectUserMapper.partialUpdate(existingStatisticSubjectUser, statisticSubjectUserDTO);

                return existingStatisticSubjectUser;
            })
            .flatMap(statisticSubjectUserRepository::save)
            .map(statisticSubjectUserMapper::toDto);
    }

    /**
     * Get all the statisticSubjectUsers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<StatisticSubjectUserDTO> findAll() {
        log.debug("Request to get all StatisticSubjectUsers");
        return statisticSubjectUserRepository.findAll().map(statisticSubjectUserMapper::toDto);
    }

    /**
     * Returns the number of statisticSubjectUsers available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return statisticSubjectUserRepository.count();
    }

    /**
     * Get one statisticSubjectUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<StatisticSubjectUserDTO> findOne(Long id) {
        log.debug("Request to get StatisticSubjectUser : {}", id);
        return statisticSubjectUserRepository.findById(id).map(statisticSubjectUserMapper::toDto);
    }

    /**
     * Delete the statisticSubjectUser by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete StatisticSubjectUser : {}", id);
        return statisticSubjectUserRepository.deleteById(id);
    }
}
