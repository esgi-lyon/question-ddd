package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.Question;
import org.contextmapper.generated.gateway.repository.QuestionRepository;
import org.contextmapper.generated.gateway.service.dto.QuestionDTO;
import org.contextmapper.generated.gateway.service.mapper.QuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Question}.
 */
@Service
@Transactional
public class QuestionService {

    private final Logger log = LoggerFactory.getLogger(QuestionService.class);

    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper;

    public QuestionService(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    /**
     * Save a question.
     *
     * @param questionDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<QuestionDTO> save(QuestionDTO questionDTO) {
        log.debug("Request to save Question : {}", questionDTO);
        return questionRepository.save(questionMapper.toEntity(questionDTO)).map(questionMapper::toDto);
    }

    /**
     * Update a question.
     *
     * @param questionDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<QuestionDTO> update(QuestionDTO questionDTO) {
        log.debug("Request to update Question : {}", questionDTO);
        return questionRepository.save(questionMapper.toEntity(questionDTO)).map(questionMapper::toDto);
    }

    /**
     * Partially update a question.
     *
     * @param questionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<QuestionDTO> partialUpdate(QuestionDTO questionDTO) {
        log.debug("Request to partially update Question : {}", questionDTO);

        return questionRepository
            .findById(questionDTO.getId())
            .map(existingQuestion -> {
                questionMapper.partialUpdate(existingQuestion, questionDTO);

                return existingQuestion;
            })
            .flatMap(questionRepository::save)
            .map(questionMapper::toDto);
    }

    /**
     * Get all the questions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<QuestionDTO> findAll() {
        log.debug("Request to get all Questions");
        return questionRepository.findAll().map(questionMapper::toDto);
    }

    /**
     * Returns the number of questions available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return questionRepository.count();
    }

    /**
     * Get one question by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<QuestionDTO> findOne(Long id) {
        log.debug("Request to get Question : {}", id);
        return questionRepository.findById(id).map(questionMapper::toDto);
    }

    /**
     * Delete the question by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Question : {}", id);
        return questionRepository.deleteById(id);
    }
}
