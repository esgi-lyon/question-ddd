package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.repository.QuestionResourceRepository;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.mapper.QuestionResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QuestionResource}.
 */
@Service
@Transactional
public class QuestionResourceService {

    private final Logger log = LoggerFactory.getLogger(QuestionResourceService.class);

    private final QuestionResourceRepository questionResourceRepository;

    private final QuestionResourceMapper questionResourceMapper;

    public QuestionResourceService(QuestionResourceRepository questionResourceRepository, QuestionResourceMapper questionResourceMapper) {
        this.questionResourceRepository = questionResourceRepository;
        this.questionResourceMapper = questionResourceMapper;
    }

    /**
     * Save a questionResource.
     *
     * @param questionResourceDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionResourceDTO save(QuestionResourceDTO questionResourceDTO) {
        log.debug("Request to save QuestionResource : {}", questionResourceDTO);
        QuestionResource questionResource = questionResourceMapper.toEntity(questionResourceDTO);
        questionResource = questionResourceRepository.save(questionResource);
        return questionResourceMapper.toDto(questionResource);
    }

    /**
     * Update a questionResource.
     *
     * @param questionResourceDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionResourceDTO update(QuestionResourceDTO questionResourceDTO) {
        log.debug("Request to update QuestionResource : {}", questionResourceDTO);
        QuestionResource questionResource = questionResourceMapper.toEntity(questionResourceDTO);
        questionResource = questionResourceRepository.save(questionResource);
        return questionResourceMapper.toDto(questionResource);
    }

    /**
     * Partially update a questionResource.
     *
     * @param questionResourceDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuestionResourceDTO> partialUpdate(QuestionResourceDTO questionResourceDTO) {
        log.debug("Request to partially update QuestionResource : {}", questionResourceDTO);

        return questionResourceRepository
            .findById(questionResourceDTO.getId())
            .map(existingQuestionResource -> {
                questionResourceMapper.partialUpdate(existingQuestionResource, questionResourceDTO);

                return existingQuestionResource;
            })
            .map(questionResourceRepository::save)
            .map(questionResourceMapper::toDto);
    }

    /**
     * Get all the questionResources.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuestionResourceDTO> findAll() {
        log.debug("Request to get all QuestionResources");
        return questionResourceRepository
            .findAll()
            .stream()
            .map(questionResourceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one questionResource by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuestionResourceDTO> findOne(Long id) {
        log.debug("Request to get QuestionResource : {}", id);
        return questionResourceRepository.findById(id).map(questionResourceMapper::toDto);
    }

    /**
     * Delete the questionResource by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QuestionResource : {}", id);
        questionResourceRepository.deleteById(id);
    }
}
