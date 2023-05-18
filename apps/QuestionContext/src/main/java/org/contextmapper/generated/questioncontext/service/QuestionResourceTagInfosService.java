package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.QuestionResourceTagInfos;
import org.contextmapper.generated.questioncontext.repository.QuestionResourceTagInfosRepository;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.questioncontext.service.mapper.QuestionResourceTagInfosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public QuestionResourceTagInfosDTO save(QuestionResourceTagInfosDTO questionResourceTagInfosDTO) {
        log.debug("Request to save QuestionResourceTagInfos : {}", questionResourceTagInfosDTO);
        QuestionResourceTagInfos questionResourceTagInfos = questionResourceTagInfosMapper.toEntity(questionResourceTagInfosDTO);
        questionResourceTagInfos = questionResourceTagInfosRepository.save(questionResourceTagInfos);
        return questionResourceTagInfosMapper.toDto(questionResourceTagInfos);
    }

    /**
     * Update a questionResourceTagInfos.
     *
     * @param questionResourceTagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionResourceTagInfosDTO update(QuestionResourceTagInfosDTO questionResourceTagInfosDTO) {
        log.debug("Request to update QuestionResourceTagInfos : {}", questionResourceTagInfosDTO);
        QuestionResourceTagInfos questionResourceTagInfos = questionResourceTagInfosMapper.toEntity(questionResourceTagInfosDTO);
        questionResourceTagInfos = questionResourceTagInfosRepository.save(questionResourceTagInfos);
        return questionResourceTagInfosMapper.toDto(questionResourceTagInfos);
    }

    /**
     * Partially update a questionResourceTagInfos.
     *
     * @param questionResourceTagInfosDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuestionResourceTagInfosDTO> partialUpdate(QuestionResourceTagInfosDTO questionResourceTagInfosDTO) {
        log.debug("Request to partially update QuestionResourceTagInfos : {}", questionResourceTagInfosDTO);

        return questionResourceTagInfosRepository
            .findById(questionResourceTagInfosDTO.getId())
            .map(existingQuestionResourceTagInfos -> {
                questionResourceTagInfosMapper.partialUpdate(existingQuestionResourceTagInfos, questionResourceTagInfosDTO);

                return existingQuestionResourceTagInfos;
            })
            .map(questionResourceTagInfosRepository::save)
            .map(questionResourceTagInfosMapper::toDto);
    }

    /**
     * Get all the questionResourceTagInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuestionResourceTagInfosDTO> findAll() {
        log.debug("Request to get all QuestionResourceTagInfos");
        return questionResourceTagInfosRepository
            .findAll()
            .stream()
            .map(questionResourceTagInfosMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one questionResourceTagInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuestionResourceTagInfosDTO> findOne(Long id) {
        log.debug("Request to get QuestionResourceTagInfos : {}", id);
        return questionResourceTagInfosRepository.findById(id).map(questionResourceTagInfosMapper::toDto);
    }

    /**
     * Delete the questionResourceTagInfos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QuestionResourceTagInfos : {}", id);
        questionResourceTagInfosRepository.deleteById(id);
    }
}
