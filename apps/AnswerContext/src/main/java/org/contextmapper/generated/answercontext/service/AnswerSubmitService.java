package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.AnswerSubmit;
import org.contextmapper.generated.answercontext.repository.AnswerSubmitRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmitDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerSubmitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnswerSubmit}.
 */
@Service
@Transactional
public class AnswerSubmitService {

    private final Logger log = LoggerFactory.getLogger(AnswerSubmitService.class);

    private final AnswerSubmitRepository answerSubmitRepository;

    private final AnswerSubmitMapper answerSubmitMapper;

    public AnswerSubmitService(AnswerSubmitRepository answerSubmitRepository, AnswerSubmitMapper answerSubmitMapper) {
        this.answerSubmitRepository = answerSubmitRepository;
        this.answerSubmitMapper = answerSubmitMapper;
    }

    /**
     * Save a answerSubmit.
     *
     * @param answerSubmitDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerSubmitDTO save(AnswerSubmitDTO answerSubmitDTO) {
        log.debug("Request to save AnswerSubmit : {}", answerSubmitDTO);
        AnswerSubmit answerSubmit = answerSubmitMapper.toEntity(answerSubmitDTO);
        answerSubmit = answerSubmitRepository.save(answerSubmit);
        return answerSubmitMapper.toDto(answerSubmit);
    }

    /**
     * Update a answerSubmit.
     *
     * @param answerSubmitDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerSubmitDTO update(AnswerSubmitDTO answerSubmitDTO) {
        log.debug("Request to update AnswerSubmit : {}", answerSubmitDTO);
        AnswerSubmit answerSubmit = answerSubmitMapper.toEntity(answerSubmitDTO);
        answerSubmit = answerSubmitRepository.save(answerSubmit);
        return answerSubmitMapper.toDto(answerSubmit);
    }

    /**
     * Partially update a answerSubmit.
     *
     * @param answerSubmitDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AnswerSubmitDTO> partialUpdate(AnswerSubmitDTO answerSubmitDTO) {
        log.debug("Request to partially update AnswerSubmit : {}", answerSubmitDTO);

        return answerSubmitRepository
            .findById(answerSubmitDTO.getId())
            .map(existingAnswerSubmit -> {
                answerSubmitMapper.partialUpdate(existingAnswerSubmit, answerSubmitDTO);

                return existingAnswerSubmit;
            })
            .map(answerSubmitRepository::save)
            .map(answerSubmitMapper::toDto);
    }

    /**
     * Get all the answerSubmits.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AnswerSubmitDTO> findAll() {
        log.debug("Request to get all AnswerSubmits");
        return answerSubmitRepository.findAll().stream().map(answerSubmitMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one answerSubmit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnswerSubmitDTO> findOne(Long id) {
        log.debug("Request to get AnswerSubmit : {}", id);
        return answerSubmitRepository.findById(id).map(answerSubmitMapper::toDto);
    }

    /**
     * Delete the answerSubmit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnswerSubmit : {}", id);
        answerSubmitRepository.deleteById(id);
    }
}
