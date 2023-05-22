package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.StatisticSubjectQuestion;
import org.contextmapper.generated.gateway.repository.StatisticSubjectQuestionRepository;
import org.contextmapper.generated.gateway.service.dto.StatisticSubjectQuestionDTO;
import org.contextmapper.generated.gateway.service.mapper.StatisticSubjectQuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link StatisticSubjectQuestion}.
 */
@Service
@Transactional
public class StatisticSubjectQuestionService {

    private final Logger log = LoggerFactory.getLogger(StatisticSubjectQuestionService.class);

    private final StatisticSubjectQuestionRepository statisticSubjectQuestionRepository;

    private final StatisticSubjectQuestionMapper statisticSubjectQuestionMapper;

    public StatisticSubjectQuestionService(
        StatisticSubjectQuestionRepository statisticSubjectQuestionRepository,
        StatisticSubjectQuestionMapper statisticSubjectQuestionMapper
    ) {
        this.statisticSubjectQuestionRepository = statisticSubjectQuestionRepository;
        this.statisticSubjectQuestionMapper = statisticSubjectQuestionMapper;
    }

    /**
     * Save a statisticSubjectQuestion.
     *
     * @param statisticSubjectQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<StatisticSubjectQuestionDTO> save(StatisticSubjectQuestionDTO statisticSubjectQuestionDTO) {
        log.debug("Request to save StatisticSubjectQuestion : {}", statisticSubjectQuestionDTO);
        return statisticSubjectQuestionRepository
            .save(statisticSubjectQuestionMapper.toEntity(statisticSubjectQuestionDTO))
            .map(statisticSubjectQuestionMapper::toDto);
    }

    /**
     * Update a statisticSubjectQuestion.
     *
     * @param statisticSubjectQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<StatisticSubjectQuestionDTO> update(StatisticSubjectQuestionDTO statisticSubjectQuestionDTO) {
        log.debug("Request to update StatisticSubjectQuestion : {}", statisticSubjectQuestionDTO);
        return statisticSubjectQuestionRepository
            .save(statisticSubjectQuestionMapper.toEntity(statisticSubjectQuestionDTO))
            .map(statisticSubjectQuestionMapper::toDto);
    }

    /**
     * Partially update a statisticSubjectQuestion.
     *
     * @param statisticSubjectQuestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<StatisticSubjectQuestionDTO> partialUpdate(StatisticSubjectQuestionDTO statisticSubjectQuestionDTO) {
        log.debug("Request to partially update StatisticSubjectQuestion : {}", statisticSubjectQuestionDTO);

        return statisticSubjectQuestionRepository
            .findById(statisticSubjectQuestionDTO.getId())
            .map(existingStatisticSubjectQuestion -> {
                statisticSubjectQuestionMapper.partialUpdate(existingStatisticSubjectQuestion, statisticSubjectQuestionDTO);

                return existingStatisticSubjectQuestion;
            })
            .flatMap(statisticSubjectQuestionRepository::save)
            .map(statisticSubjectQuestionMapper::toDto);
    }

    /**
     * Get all the statisticSubjectQuestions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<StatisticSubjectQuestionDTO> findAll() {
        log.debug("Request to get all StatisticSubjectQuestions");
        return statisticSubjectQuestionRepository.findAll().map(statisticSubjectQuestionMapper::toDto);
    }

    /**
     * Returns the number of statisticSubjectQuestions available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return statisticSubjectQuestionRepository.count();
    }

    /**
     * Get one statisticSubjectQuestion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<StatisticSubjectQuestionDTO> findOne(Long id) {
        log.debug("Request to get StatisticSubjectQuestion : {}", id);
        return statisticSubjectQuestionRepository.findById(id).map(statisticSubjectQuestionMapper::toDto);
    }

    /**
     * Delete the statisticSubjectQuestion by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete StatisticSubjectQuestion : {}", id);
        return statisticSubjectQuestionRepository.deleteById(id);
    }
}
