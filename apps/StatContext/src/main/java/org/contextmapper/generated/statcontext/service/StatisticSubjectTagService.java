package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectTag;
import org.contextmapper.generated.statcontext.repository.StatisticSubjectTagRepository;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectTagDTO;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public StatisticSubjectTagDTO save(StatisticSubjectTagDTO statisticSubjectTagDTO) {
        log.debug("Request to save StatisticSubjectTag : {}", statisticSubjectTagDTO);
        StatisticSubjectTag statisticSubjectTag = statisticSubjectTagMapper.toEntity(statisticSubjectTagDTO);
        statisticSubjectTag = statisticSubjectTagRepository.save(statisticSubjectTag);
        return statisticSubjectTagMapper.toDto(statisticSubjectTag);
    }

    /**
     * Update a statisticSubjectTag.
     *
     * @param statisticSubjectTagDTO the entity to save.
     * @return the persisted entity.
     */
    public StatisticSubjectTagDTO update(StatisticSubjectTagDTO statisticSubjectTagDTO) {
        log.debug("Request to update StatisticSubjectTag : {}", statisticSubjectTagDTO);
        StatisticSubjectTag statisticSubjectTag = statisticSubjectTagMapper.toEntity(statisticSubjectTagDTO);
        statisticSubjectTag = statisticSubjectTagRepository.save(statisticSubjectTag);
        return statisticSubjectTagMapper.toDto(statisticSubjectTag);
    }

    /**
     * Partially update a statisticSubjectTag.
     *
     * @param statisticSubjectTagDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<StatisticSubjectTagDTO> partialUpdate(StatisticSubjectTagDTO statisticSubjectTagDTO) {
        log.debug("Request to partially update StatisticSubjectTag : {}", statisticSubjectTagDTO);

        return statisticSubjectTagRepository
            .findById(statisticSubjectTagDTO.getId())
            .map(existingStatisticSubjectTag -> {
                statisticSubjectTagMapper.partialUpdate(existingStatisticSubjectTag, statisticSubjectTagDTO);

                return existingStatisticSubjectTag;
            })
            .map(statisticSubjectTagRepository::save)
            .map(statisticSubjectTagMapper::toDto);
    }

    /**
     * Get all the statisticSubjectTags.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<StatisticSubjectTagDTO> findAll() {
        log.debug("Request to get all StatisticSubjectTags");
        return statisticSubjectTagRepository
            .findAll()
            .stream()
            .map(statisticSubjectTagMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one statisticSubjectTag by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StatisticSubjectTagDTO> findOne(Long id) {
        log.debug("Request to get StatisticSubjectTag : {}", id);
        return statisticSubjectTagRepository.findById(id).map(statisticSubjectTagMapper::toDto);
    }

    /**
     * Delete the statisticSubjectTag by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete StatisticSubjectTag : {}", id);
        statisticSubjectTagRepository.deleteById(id);
    }
}
