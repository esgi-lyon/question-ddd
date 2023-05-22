package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.StatisticSubjectTag;
import org.contextmapper.generated.gateway.repository.StatisticSubjectTagRepository;
import org.contextmapper.generated.gateway.service.dto.StatisticSubjectTagDTO;
import org.contextmapper.generated.gateway.service.mapper.StatisticSubjectTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link StatisticSubjectTag}.
 */
@Service
@Transactional
public class StatisticSubjectTagService {

    private final Logger log = LoggerFactory.getLogger(StatisticSubjectTagService.class);

    private final StatisticSubjectTagRepository statisticSubjectTagRepository;

    private final StatisticSubjectTagMapper statisticSubjectTagMapper;

    public StatisticSubjectTagService(
        StatisticSubjectTagRepository statisticSubjectTagRepository,
        StatisticSubjectTagMapper statisticSubjectTagMapper
    ) {
        this.statisticSubjectTagRepository = statisticSubjectTagRepository;
        this.statisticSubjectTagMapper = statisticSubjectTagMapper;
    }

    /**
     * Save a statisticSubjectTag.
     *
     * @param statisticSubjectTagDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<StatisticSubjectTagDTO> save(StatisticSubjectTagDTO statisticSubjectTagDTO) {
        log.debug("Request to save StatisticSubjectTag : {}", statisticSubjectTagDTO);
        return statisticSubjectTagRepository
            .save(statisticSubjectTagMapper.toEntity(statisticSubjectTagDTO))
            .map(statisticSubjectTagMapper::toDto);
    }

    /**
     * Update a statisticSubjectTag.
     *
     * @param statisticSubjectTagDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<StatisticSubjectTagDTO> update(StatisticSubjectTagDTO statisticSubjectTagDTO) {
        log.debug("Request to update StatisticSubjectTag : {}", statisticSubjectTagDTO);
        return statisticSubjectTagRepository
            .save(statisticSubjectTagMapper.toEntity(statisticSubjectTagDTO))
            .map(statisticSubjectTagMapper::toDto);
    }

    /**
     * Partially update a statisticSubjectTag.
     *
     * @param statisticSubjectTagDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<StatisticSubjectTagDTO> partialUpdate(StatisticSubjectTagDTO statisticSubjectTagDTO) {
        log.debug("Request to partially update StatisticSubjectTag : {}", statisticSubjectTagDTO);

        return statisticSubjectTagRepository
            .findById(statisticSubjectTagDTO.getId())
            .map(existingStatisticSubjectTag -> {
                statisticSubjectTagMapper.partialUpdate(existingStatisticSubjectTag, statisticSubjectTagDTO);

                return existingStatisticSubjectTag;
            })
            .flatMap(statisticSubjectTagRepository::save)
            .map(statisticSubjectTagMapper::toDto);
    }

    /**
     * Get all the statisticSubjectTags.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<StatisticSubjectTagDTO> findAll() {
        log.debug("Request to get all StatisticSubjectTags");
        return statisticSubjectTagRepository.findAll().map(statisticSubjectTagMapper::toDto);
    }

    /**
     * Returns the number of statisticSubjectTags available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return statisticSubjectTagRepository.count();
    }

    /**
     * Get one statisticSubjectTag by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<StatisticSubjectTagDTO> findOne(Long id) {
        log.debug("Request to get StatisticSubjectTag : {}", id);
        return statisticSubjectTagRepository.findById(id).map(statisticSubjectTagMapper::toDto);
    }

    /**
     * Delete the statisticSubjectTag by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete StatisticSubjectTag : {}", id);
        return statisticSubjectTagRepository.deleteById(id);
    }
}
