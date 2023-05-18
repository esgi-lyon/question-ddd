package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectUser;
import org.contextmapper.generated.statcontext.repository.StatisticSubjectUserRepository;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectUserDTO;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public StatisticSubjectUserDTO save(StatisticSubjectUserDTO statisticSubjectUserDTO) {
        log.debug("Request to save StatisticSubjectUser : {}", statisticSubjectUserDTO);
        StatisticSubjectUser statisticSubjectUser = statisticSubjectUserMapper.toEntity(statisticSubjectUserDTO);
        statisticSubjectUser = statisticSubjectUserRepository.save(statisticSubjectUser);
        return statisticSubjectUserMapper.toDto(statisticSubjectUser);
    }

    /**
     * Update a statisticSubjectUser.
     *
     * @param statisticSubjectUserDTO the entity to save.
     * @return the persisted entity.
     */
    public StatisticSubjectUserDTO update(StatisticSubjectUserDTO statisticSubjectUserDTO) {
        log.debug("Request to update StatisticSubjectUser : {}", statisticSubjectUserDTO);
        StatisticSubjectUser statisticSubjectUser = statisticSubjectUserMapper.toEntity(statisticSubjectUserDTO);
        statisticSubjectUser = statisticSubjectUserRepository.save(statisticSubjectUser);
        return statisticSubjectUserMapper.toDto(statisticSubjectUser);
    }

    /**
     * Partially update a statisticSubjectUser.
     *
     * @param statisticSubjectUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<StatisticSubjectUserDTO> partialUpdate(StatisticSubjectUserDTO statisticSubjectUserDTO) {
        log.debug("Request to partially update StatisticSubjectUser : {}", statisticSubjectUserDTO);

        return statisticSubjectUserRepository
            .findById(statisticSubjectUserDTO.getId())
            .map(existingStatisticSubjectUser -> {
                statisticSubjectUserMapper.partialUpdate(existingStatisticSubjectUser, statisticSubjectUserDTO);

                return existingStatisticSubjectUser;
            })
            .map(statisticSubjectUserRepository::save)
            .map(statisticSubjectUserMapper::toDto);
    }

    /**
     * Get all the statisticSubjectUsers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<StatisticSubjectUserDTO> findAll() {
        log.debug("Request to get all StatisticSubjectUsers");
        return statisticSubjectUserRepository
            .findAll()
            .stream()
            .map(statisticSubjectUserMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one statisticSubjectUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StatisticSubjectUserDTO> findOne(Long id) {
        log.debug("Request to get StatisticSubjectUser : {}", id);
        return statisticSubjectUserRepository.findById(id).map(statisticSubjectUserMapper::toDto);
    }

    /**
     * Delete the statisticSubjectUser by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete StatisticSubjectUser : {}", id);
        statisticSubjectUserRepository.deleteById(id);
    }
}
