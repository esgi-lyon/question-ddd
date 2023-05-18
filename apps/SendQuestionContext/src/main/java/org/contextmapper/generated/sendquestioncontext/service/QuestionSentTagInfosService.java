package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfos;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentTagInfosRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagInfosDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentTagInfosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QuestionSentTagInfos}.
 */
@Service
@Transactional
public class QuestionSentTagInfosService {

    private final Logger log = LoggerFactory.getLogger(QuestionSentTagInfosService.class);

    private final QuestionSentTagInfosRepository questionSentTagInfosRepository;

    private final QuestionSentTagInfosMapper questionSentTagInfosMapper;

    public QuestionSentTagInfosService(
        QuestionSentTagInfosRepository questionSentTagInfosRepository,
        QuestionSentTagInfosMapper questionSentTagInfosMapper
    ) {
        this.questionSentTagInfosRepository = questionSentTagInfosRepository;
        this.questionSentTagInfosMapper = questionSentTagInfosMapper;
    }

    /**
     * Save a questionSentTagInfos.
     *
     * @param questionSentTagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionSentTagInfosDTO save(QuestionSentTagInfosDTO questionSentTagInfosDTO) {
        log.debug("Request to save QuestionSentTagInfos : {}", questionSentTagInfosDTO);
        QuestionSentTagInfos questionSentTagInfos = questionSentTagInfosMapper.toEntity(questionSentTagInfosDTO);
        questionSentTagInfos = questionSentTagInfosRepository.save(questionSentTagInfos);
        return questionSentTagInfosMapper.toDto(questionSentTagInfos);
    }

    /**
     * Update a questionSentTagInfos.
     *
     * @param questionSentTagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionSentTagInfosDTO update(QuestionSentTagInfosDTO questionSentTagInfosDTO) {
        log.debug("Request to update QuestionSentTagInfos : {}", questionSentTagInfosDTO);
        QuestionSentTagInfos questionSentTagInfos = questionSentTagInfosMapper.toEntity(questionSentTagInfosDTO);
        questionSentTagInfos = questionSentTagInfosRepository.save(questionSentTagInfos);
        return questionSentTagInfosMapper.toDto(questionSentTagInfos);
    }

    /**
     * Partially update a questionSentTagInfos.
     *
     * @param questionSentTagInfosDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuestionSentTagInfosDTO> partialUpdate(QuestionSentTagInfosDTO questionSentTagInfosDTO) {
        log.debug("Request to partially update QuestionSentTagInfos : {}", questionSentTagInfosDTO);

        return questionSentTagInfosRepository
            .findById(questionSentTagInfosDTO.getId())
            .map(existingQuestionSentTagInfos -> {
                questionSentTagInfosMapper.partialUpdate(existingQuestionSentTagInfos, questionSentTagInfosDTO);

                return existingQuestionSentTagInfos;
            })
            .map(questionSentTagInfosRepository::save)
            .map(questionSentTagInfosMapper::toDto);
    }

    /**
     * Get all the questionSentTagInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuestionSentTagInfosDTO> findAll() {
        log.debug("Request to get all QuestionSentTagInfos");
        return questionSentTagInfosRepository
            .findAll()
            .stream()
            .map(questionSentTagInfosMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one questionSentTagInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuestionSentTagInfosDTO> findOne(Long id) {
        log.debug("Request to get QuestionSentTagInfos : {}", id);
        return questionSentTagInfosRepository.findById(id).map(questionSentTagInfosMapper::toDto);
    }

    /**
     * Delete the questionSentTagInfos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QuestionSentTagInfos : {}", id);
        questionSentTagInfosRepository.deleteById(id);
    }
}
