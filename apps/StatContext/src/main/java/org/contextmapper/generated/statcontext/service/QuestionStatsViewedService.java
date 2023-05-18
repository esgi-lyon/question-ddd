package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.QuestionStatsViewed;
import org.contextmapper.generated.statcontext.repository.QuestionStatsViewedRepository;
import org.contextmapper.generated.statcontext.service.dto.QuestionStatsViewedDTO;
import org.contextmapper.generated.statcontext.service.mapper.QuestionStatsViewedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QuestionStatsViewed}.
 */
@Service
@Transactional
public class QuestionStatsViewedService {

    private final Logger log = LoggerFactory.getLogger(QuestionStatsViewedService.class);

    private final QuestionStatsViewedRepository questionStatsViewedRepository;

    private final QuestionStatsViewedMapper questionStatsViewedMapper;

    public QuestionStatsViewedService(
        QuestionStatsViewedRepository questionStatsViewedRepository,
        QuestionStatsViewedMapper questionStatsViewedMapper
    ) {
        this.questionStatsViewedRepository = questionStatsViewedRepository;
        this.questionStatsViewedMapper = questionStatsViewedMapper;
    }

    /**
     * Save a questionStatsViewed.
     *
     * @param questionStatsViewedDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionStatsViewedDTO save(QuestionStatsViewedDTO questionStatsViewedDTO) {
        log.debug("Request to save QuestionStatsViewed : {}", questionStatsViewedDTO);
        QuestionStatsViewed questionStatsViewed = questionStatsViewedMapper.toEntity(questionStatsViewedDTO);
        questionStatsViewed = questionStatsViewedRepository.save(questionStatsViewed);
        return questionStatsViewedMapper.toDto(questionStatsViewed);
    }

    /**
     * Update a questionStatsViewed.
     *
     * @param questionStatsViewedDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionStatsViewedDTO update(QuestionStatsViewedDTO questionStatsViewedDTO) {
        log.debug("Request to update QuestionStatsViewed : {}", questionStatsViewedDTO);
        QuestionStatsViewed questionStatsViewed = questionStatsViewedMapper.toEntity(questionStatsViewedDTO);
        questionStatsViewed = questionStatsViewedRepository.save(questionStatsViewed);
        return questionStatsViewedMapper.toDto(questionStatsViewed);
    }

    /**
     * Partially update a questionStatsViewed.
     *
     * @param questionStatsViewedDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuestionStatsViewedDTO> partialUpdate(QuestionStatsViewedDTO questionStatsViewedDTO) {
        log.debug("Request to partially update QuestionStatsViewed : {}", questionStatsViewedDTO);

        return questionStatsViewedRepository
            .findById(questionStatsViewedDTO.getId())
            .map(existingQuestionStatsViewed -> {
                questionStatsViewedMapper.partialUpdate(existingQuestionStatsViewed, questionStatsViewedDTO);

                return existingQuestionStatsViewed;
            })
            .map(questionStatsViewedRepository::save)
            .map(questionStatsViewedMapper::toDto);
    }

    /**
     * Get all the questionStatsVieweds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuestionStatsViewedDTO> findAll() {
        log.debug("Request to get all QuestionStatsVieweds");
        return questionStatsViewedRepository
            .findAll()
            .stream()
            .map(questionStatsViewedMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one questionStatsViewed by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuestionStatsViewedDTO> findOne(Long id) {
        log.debug("Request to get QuestionStatsViewed : {}", id);
        return questionStatsViewedRepository.findById(id).map(questionStatsViewedMapper::toDto);
    }

    /**
     * Delete the questionStatsViewed by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QuestionStatsViewed : {}", id);
        questionStatsViewedRepository.deleteById(id);
    }
}
