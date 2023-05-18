package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectQuestion;
import org.contextmapper.generated.statcontext.repository.StatisticSubjectQuestionRepository;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectQuestionDTO;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectQuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public StatisticSubjectQuestionDTO save(StatisticSubjectQuestionDTO statisticSubjectQuestionDTO) {
        log.debug("Request to save StatisticSubjectQuestion : {}", statisticSubjectQuestionDTO);
        StatisticSubjectQuestion statisticSubjectQuestion = statisticSubjectQuestionMapper.toEntity(statisticSubjectQuestionDTO);
        statisticSubjectQuestion = statisticSubjectQuestionRepository.save(statisticSubjectQuestion);
        return statisticSubjectQuestionMapper.toDto(statisticSubjectQuestion);
    }

    /**
     * Update a statisticSubjectQuestion.
     *
     * @param statisticSubjectQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public StatisticSubjectQuestionDTO update(StatisticSubjectQuestionDTO statisticSubjectQuestionDTO) {
        log.debug("Request to update StatisticSubjectQuestion : {}", statisticSubjectQuestionDTO);
        StatisticSubjectQuestion statisticSubjectQuestion = statisticSubjectQuestionMapper.toEntity(statisticSubjectQuestionDTO);
        statisticSubjectQuestion = statisticSubjectQuestionRepository.save(statisticSubjectQuestion);
        return statisticSubjectQuestionMapper.toDto(statisticSubjectQuestion);
    }

    /**
     * Partially update a statisticSubjectQuestion.
     *
     * @param statisticSubjectQuestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<StatisticSubjectQuestionDTO> partialUpdate(StatisticSubjectQuestionDTO statisticSubjectQuestionDTO) {
        log.debug("Request to partially update StatisticSubjectQuestion : {}", statisticSubjectQuestionDTO);

        return statisticSubjectQuestionRepository
            .findById(statisticSubjectQuestionDTO.getId())
            .map(existingStatisticSubjectQuestion -> {
                statisticSubjectQuestionMapper.partialUpdate(existingStatisticSubjectQuestion, statisticSubjectQuestionDTO);

                return existingStatisticSubjectQuestion;
            })
            .map(statisticSubjectQuestionRepository::save)
            .map(statisticSubjectQuestionMapper::toDto);
    }

    /**
     * Get all the statisticSubjectQuestions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<StatisticSubjectQuestionDTO> findAll() {
        log.debug("Request to get all StatisticSubjectQuestions");
        return statisticSubjectQuestionRepository
            .findAll()
            .stream()
            .map(statisticSubjectQuestionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one statisticSubjectQuestion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StatisticSubjectQuestionDTO> findOne(Long id) {
        log.debug("Request to get StatisticSubjectQuestion : {}", id);
        return statisticSubjectQuestionRepository.findById(id).map(statisticSubjectQuestionMapper::toDto);
    }

    /**
     * Delete the statisticSubjectQuestion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete StatisticSubjectQuestion : {}", id);
        statisticSubjectQuestionRepository.deleteById(id);
    }
}
