package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.QuestionSentTagInfos;
import org.contextmapper.generated.gateway.repository.QuestionSentTagInfosRepository;
import org.contextmapper.generated.gateway.service.dto.QuestionSentTagInfosDTO;
import org.contextmapper.generated.gateway.service.mapper.QuestionSentTagInfosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<QuestionSentTagInfosDTO> save(QuestionSentTagInfosDTO questionSentTagInfosDTO) {
        log.debug("Request to save QuestionSentTagInfos : {}", questionSentTagInfosDTO);
        return questionSentTagInfosRepository
            .save(questionSentTagInfosMapper.toEntity(questionSentTagInfosDTO))
            .map(questionSentTagInfosMapper::toDto);
    }

    /**
     * Update a questionSentTagInfos.
     *
     * @param questionSentTagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<QuestionSentTagInfosDTO> update(QuestionSentTagInfosDTO questionSentTagInfosDTO) {
        log.debug("Request to update QuestionSentTagInfos : {}", questionSentTagInfosDTO);
        return questionSentTagInfosRepository
            .save(questionSentTagInfosMapper.toEntity(questionSentTagInfosDTO))
            .map(questionSentTagInfosMapper::toDto);
    }

    /**
     * Partially update a questionSentTagInfos.
     *
     * @param questionSentTagInfosDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<QuestionSentTagInfosDTO> partialUpdate(QuestionSentTagInfosDTO questionSentTagInfosDTO) {
        log.debug("Request to partially update QuestionSentTagInfos : {}", questionSentTagInfosDTO);

        return questionSentTagInfosRepository
            .findById(questionSentTagInfosDTO.getId())
            .map(existingQuestionSentTagInfos -> {
                questionSentTagInfosMapper.partialUpdate(existingQuestionSentTagInfos, questionSentTagInfosDTO);

                return existingQuestionSentTagInfos;
            })
            .flatMap(questionSentTagInfosRepository::save)
            .map(questionSentTagInfosMapper::toDto);
    }

    /**
     * Get all the questionSentTagInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<QuestionSentTagInfosDTO> findAll() {
        log.debug("Request to get all QuestionSentTagInfos");
        return questionSentTagInfosRepository.findAll().map(questionSentTagInfosMapper::toDto);
    }

    /**
     * Returns the number of questionSentTagInfos available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return questionSentTagInfosRepository.count();
    }

    /**
     * Get one questionSentTagInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<QuestionSentTagInfosDTO> findOne(Long id) {
        log.debug("Request to get QuestionSentTagInfos : {}", id);
        return questionSentTagInfosRepository.findById(id).map(questionSentTagInfosMapper::toDto);
    }

    /**
     * Delete the questionSentTagInfos by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete QuestionSentTagInfos : {}", id);
        return questionSentTagInfosRepository.deleteById(id);
    }
}
