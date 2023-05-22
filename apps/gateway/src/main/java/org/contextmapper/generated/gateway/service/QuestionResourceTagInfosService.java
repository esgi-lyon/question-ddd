package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.QuestionResourceTagInfos;
import org.contextmapper.generated.gateway.repository.QuestionResourceTagInfosRepository;
import org.contextmapper.generated.gateway.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.gateway.service.mapper.QuestionResourceTagInfosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link QuestionResourceTagInfos}.
 */
@Service
@Transactional
public class QuestionResourceTagInfosService {

    private final Logger log = LoggerFactory.getLogger(QuestionResourceTagInfosService.class);

    private final QuestionResourceTagInfosRepository questionResourceTagInfosRepository;

    private final QuestionResourceTagInfosMapper questionResourceTagInfosMapper;

    public QuestionResourceTagInfosService(
        QuestionResourceTagInfosRepository questionResourceTagInfosRepository,
        QuestionResourceTagInfosMapper questionResourceTagInfosMapper
    ) {
        this.questionResourceTagInfosRepository = questionResourceTagInfosRepository;
        this.questionResourceTagInfosMapper = questionResourceTagInfosMapper;
    }

    /**
     * Save a questionResourceTagInfos.
     *
     * @param questionResourceTagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<QuestionResourceTagInfosDTO> save(QuestionResourceTagInfosDTO questionResourceTagInfosDTO) {
        log.debug("Request to save QuestionResourceTagInfos : {}", questionResourceTagInfosDTO);
        return questionResourceTagInfosRepository
            .save(questionResourceTagInfosMapper.toEntity(questionResourceTagInfosDTO))
            .map(questionResourceTagInfosMapper::toDto);
    }

    /**
     * Update a questionResourceTagInfos.
     *
     * @param questionResourceTagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<QuestionResourceTagInfosDTO> update(QuestionResourceTagInfosDTO questionResourceTagInfosDTO) {
        log.debug("Request to update QuestionResourceTagInfos : {}", questionResourceTagInfosDTO);
        return questionResourceTagInfosRepository
            .save(questionResourceTagInfosMapper.toEntity(questionResourceTagInfosDTO))
            .map(questionResourceTagInfosMapper::toDto);
    }

    /**
     * Partially update a questionResourceTagInfos.
     *
     * @param questionResourceTagInfosDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<QuestionResourceTagInfosDTO> partialUpdate(QuestionResourceTagInfosDTO questionResourceTagInfosDTO) {
        log.debug("Request to partially update QuestionResourceTagInfos : {}", questionResourceTagInfosDTO);

        return questionResourceTagInfosRepository
            .findById(questionResourceTagInfosDTO.getId())
            .map(existingQuestionResourceTagInfos -> {
                questionResourceTagInfosMapper.partialUpdate(existingQuestionResourceTagInfos, questionResourceTagInfosDTO);

                return existingQuestionResourceTagInfos;
            })
            .flatMap(questionResourceTagInfosRepository::save)
            .map(questionResourceTagInfosMapper::toDto);
    }

    /**
     * Get all the questionResourceTagInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<QuestionResourceTagInfosDTO> findAll() {
        log.debug("Request to get all QuestionResourceTagInfos");
        return questionResourceTagInfosRepository.findAll().map(questionResourceTagInfosMapper::toDto);
    }

    /**
     * Returns the number of questionResourceTagInfos available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return questionResourceTagInfosRepository.count();
    }

    /**
     * Get one questionResourceTagInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<QuestionResourceTagInfosDTO> findOne(Long id) {
        log.debug("Request to get QuestionResourceTagInfos : {}", id);
        return questionResourceTagInfosRepository.findById(id).map(questionResourceTagInfosMapper::toDto);
    }

    /**
     * Delete the questionResourceTagInfos by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete QuestionResourceTagInfos : {}", id);
        return questionResourceTagInfosRepository.deleteById(id);
    }
}
