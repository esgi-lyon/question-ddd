package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.CreateQuestion;
import org.contextmapper.generated.sendquestioncontext.repository.CreateQuestionRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreateQuestionDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.CreateQuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateQuestion}.
 */
@Service
@Transactional
public class CreateQuestionService {

    private final Logger log = LoggerFactory.getLogger(CreateQuestionService.class);

    private final CreateQuestionRepository createQuestionRepository;

    private final CreateQuestionMapper createQuestionMapper;

    public CreateQuestionService(CreateQuestionRepository createQuestionRepository, CreateQuestionMapper createQuestionMapper) {
        this.createQuestionRepository = createQuestionRepository;
        this.createQuestionMapper = createQuestionMapper;
    }

    /**
     * Save a createQuestion.
     *
     * @param createQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateQuestionDTO save(CreateQuestionDTO createQuestionDTO) {
        log.debug("Request to save CreateQuestion : {}", createQuestionDTO);
        CreateQuestion createQuestion = createQuestionMapper.toEntity(createQuestionDTO);
        createQuestion = createQuestionRepository.save(createQuestion);
        return createQuestionMapper.toDto(createQuestion);
    }

    /**
     * Update a createQuestion.
     *
     * @param createQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateQuestionDTO update(CreateQuestionDTO createQuestionDTO) {
        log.debug("Request to update CreateQuestion : {}", createQuestionDTO);
        CreateQuestion createQuestion = createQuestionMapper.toEntity(createQuestionDTO);
        createQuestion = createQuestionRepository.save(createQuestion);
        return createQuestionMapper.toDto(createQuestion);
    }

    /**
     * Partially update a createQuestion.
     *
     * @param createQuestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateQuestionDTO> partialUpdate(CreateQuestionDTO createQuestionDTO) {
        log.debug("Request to partially update CreateQuestion : {}", createQuestionDTO);

        return createQuestionRepository
            .findById(createQuestionDTO.getId())
            .map(existingCreateQuestion -> {
                createQuestionMapper.partialUpdate(existingCreateQuestion, createQuestionDTO);

                return existingCreateQuestion;
            })
            .map(createQuestionRepository::save)
            .map(createQuestionMapper::toDto);
    }

    /**
     * Get all the createQuestions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateQuestionDTO> findAll() {
        log.debug("Request to get all CreateQuestions");
        return createQuestionRepository
            .findAll()
            .stream()
            .map(createQuestionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one createQuestion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateQuestionDTO> findOne(Long id) {
        log.debug("Request to get CreateQuestion : {}", id);
        return createQuestionRepository.findById(id).map(createQuestionMapper::toDto);
    }

    /**
     * Delete the createQuestion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreateQuestion : {}", id);
        createQuestionRepository.deleteById(id);
    }
}
