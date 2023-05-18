package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.NotifiedQuestion;
import org.contextmapper.generated.sendquestioncontext.repository.NotifiedQuestionRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedQuestionDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.NotifiedQuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NotifiedQuestion}.
 */
@Service
@Transactional
public class NotifiedQuestionService {

    private final Logger log = LoggerFactory.getLogger(NotifiedQuestionService.class);

    private final NotifiedQuestionRepository notifiedQuestionRepository;

    private final NotifiedQuestionMapper notifiedQuestionMapper;

    public NotifiedQuestionService(NotifiedQuestionRepository notifiedQuestionRepository, NotifiedQuestionMapper notifiedQuestionMapper) {
        this.notifiedQuestionRepository = notifiedQuestionRepository;
        this.notifiedQuestionMapper = notifiedQuestionMapper;
    }

    /**
     * Save a notifiedQuestion.
     *
     * @param notifiedQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public NotifiedQuestionDTO save(NotifiedQuestionDTO notifiedQuestionDTO) {
        log.debug("Request to save NotifiedQuestion : {}", notifiedQuestionDTO);
        NotifiedQuestion notifiedQuestion = notifiedQuestionMapper.toEntity(notifiedQuestionDTO);
        notifiedQuestion = notifiedQuestionRepository.save(notifiedQuestion);
        return notifiedQuestionMapper.toDto(notifiedQuestion);
    }

    /**
     * Update a notifiedQuestion.
     *
     * @param notifiedQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public NotifiedQuestionDTO update(NotifiedQuestionDTO notifiedQuestionDTO) {
        log.debug("Request to update NotifiedQuestion : {}", notifiedQuestionDTO);
        NotifiedQuestion notifiedQuestion = notifiedQuestionMapper.toEntity(notifiedQuestionDTO);
        notifiedQuestion = notifiedQuestionRepository.save(notifiedQuestion);
        return notifiedQuestionMapper.toDto(notifiedQuestion);
    }

    /**
     * Partially update a notifiedQuestion.
     *
     * @param notifiedQuestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NotifiedQuestionDTO> partialUpdate(NotifiedQuestionDTO notifiedQuestionDTO) {
        log.debug("Request to partially update NotifiedQuestion : {}", notifiedQuestionDTO);

        return notifiedQuestionRepository
            .findById(notifiedQuestionDTO.getId())
            .map(existingNotifiedQuestion -> {
                notifiedQuestionMapper.partialUpdate(existingNotifiedQuestion, notifiedQuestionDTO);

                return existingNotifiedQuestion;
            })
            .map(notifiedQuestionRepository::save)
            .map(notifiedQuestionMapper::toDto);
    }

    /**
     * Get all the notifiedQuestions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NotifiedQuestionDTO> findAll() {
        log.debug("Request to get all NotifiedQuestions");
        return notifiedQuestionRepository
            .findAll()
            .stream()
            .map(notifiedQuestionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one notifiedQuestion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NotifiedQuestionDTO> findOne(Long id) {
        log.debug("Request to get NotifiedQuestion : {}", id);
        return notifiedQuestionRepository.findById(id).map(notifiedQuestionMapper::toDto);
    }

    /**
     * Delete the notifiedQuestion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NotifiedQuestion : {}", id);
        notifiedQuestionRepository.deleteById(id);
    }
}
