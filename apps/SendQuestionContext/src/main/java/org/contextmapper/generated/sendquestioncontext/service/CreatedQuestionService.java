package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.CreatedQuestion;
import org.contextmapper.generated.sendquestioncontext.repository.CreatedQuestionRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreatedQuestionDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.CreatedQuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreatedQuestion}.
 */
@Service
@Transactional
public class CreatedQuestionService {

    private final Logger log = LoggerFactory.getLogger(CreatedQuestionService.class);

    private final CreatedQuestionRepository createdQuestionRepository;

    private final CreatedQuestionMapper createdQuestionMapper;

    public CreatedQuestionService(CreatedQuestionRepository createdQuestionRepository, CreatedQuestionMapper createdQuestionMapper) {
        this.createdQuestionRepository = createdQuestionRepository;
        this.createdQuestionMapper = createdQuestionMapper;
    }

    /**
     * Save a createdQuestion.
     *
     * @param createdQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public CreatedQuestionDTO save(CreatedQuestionDTO createdQuestionDTO) {
        log.debug("Request to save CreatedQuestion : {}", createdQuestionDTO);
        CreatedQuestion createdQuestion = createdQuestionMapper.toEntity(createdQuestionDTO);
        createdQuestion = createdQuestionRepository.save(createdQuestion);
        return createdQuestionMapper.toDto(createdQuestion);
    }

    /**
     * Update a createdQuestion.
     *
     * @param createdQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public CreatedQuestionDTO update(CreatedQuestionDTO createdQuestionDTO) {
        log.debug("Request to update CreatedQuestion : {}", createdQuestionDTO);
        CreatedQuestion createdQuestion = createdQuestionMapper.toEntity(createdQuestionDTO);
        createdQuestion = createdQuestionRepository.save(createdQuestion);
        return createdQuestionMapper.toDto(createdQuestion);
    }

    /**
     * Partially update a createdQuestion.
     *
     * @param createdQuestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreatedQuestionDTO> partialUpdate(CreatedQuestionDTO createdQuestionDTO) {
        log.debug("Request to partially update CreatedQuestion : {}", createdQuestionDTO);

        return createdQuestionRepository
            .findById(createdQuestionDTO.getId())
            .map(existingCreatedQuestion -> {
                createdQuestionMapper.partialUpdate(existingCreatedQuestion, createdQuestionDTO);

                return existingCreatedQuestion;
            })
            .map(createdQuestionRepository::save)
            .map(createdQuestionMapper::toDto);
    }

    /**
     * Get all the createdQuestions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreatedQuestionDTO> findAll() {
        log.debug("Request to get all CreatedQuestions");
        return createdQuestionRepository
            .findAll()
            .stream()
            .map(createdQuestionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one createdQuestion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreatedQuestionDTO> findOne(Long id) {
        log.debug("Request to get CreatedQuestion : {}", id);
        return createdQuestionRepository.findById(id).map(createdQuestionMapper::toDto);
    }

    /**
     * Delete the createdQuestion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreatedQuestion : {}", id);
        createdQuestionRepository.deleteById(id);
    }
}
