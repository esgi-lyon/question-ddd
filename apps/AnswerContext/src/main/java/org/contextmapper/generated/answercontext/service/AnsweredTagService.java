package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.AnsweredTag;
import org.contextmapper.generated.answercontext.repository.AnsweredTagRepository;
import org.contextmapper.generated.answercontext.service.dto.AnsweredTagDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnsweredTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnsweredTag}.
 */
@Service
@Transactional
public class AnsweredTagService {

    private final Logger log = LoggerFactory.getLogger(AnsweredTagService.class);

    private final AnsweredTagRepository answeredTagRepository;

    private final AnsweredTagMapper answeredTagMapper;

    public AnsweredTagService(AnsweredTagRepository answeredTagRepository, AnsweredTagMapper answeredTagMapper) {
        this.answeredTagRepository = answeredTagRepository;
        this.answeredTagMapper = answeredTagMapper;
    }

    /**
     * Save a answeredTag.
     *
     * @param answeredTagDTO the entity to save.
     * @return the persisted entity.
     */
    public AnsweredTagDTO save(AnsweredTagDTO answeredTagDTO) {
        log.debug("Request to save AnsweredTag : {}", answeredTagDTO);
        AnsweredTag answeredTag = answeredTagMapper.toEntity(answeredTagDTO);
        answeredTag = answeredTagRepository.save(answeredTag);
        return answeredTagMapper.toDto(answeredTag);
    }

    /**
     * Update a answeredTag.
     *
     * @param answeredTagDTO the entity to save.
     * @return the persisted entity.
     */
    public AnsweredTagDTO update(AnsweredTagDTO answeredTagDTO) {
        log.debug("Request to update AnsweredTag : {}", answeredTagDTO);
        AnsweredTag answeredTag = answeredTagMapper.toEntity(answeredTagDTO);
        answeredTag = answeredTagRepository.save(answeredTag);
        return answeredTagMapper.toDto(answeredTag);
    }

    /**
     * Partially update a answeredTag.
     *
     * @param answeredTagDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AnsweredTagDTO> partialUpdate(AnsweredTagDTO answeredTagDTO) {
        log.debug("Request to partially update AnsweredTag : {}", answeredTagDTO);

        return answeredTagRepository
            .findById(answeredTagDTO.getId())
            .map(existingAnsweredTag -> {
                answeredTagMapper.partialUpdate(existingAnsweredTag, answeredTagDTO);

                return existingAnsweredTag;
            })
            .map(answeredTagRepository::save)
            .map(answeredTagMapper::toDto);
    }

    /**
     * Get all the answeredTags.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AnsweredTagDTO> findAll() {
        log.debug("Request to get all AnsweredTags");
        return answeredTagRepository.findAll().stream().map(answeredTagMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one answeredTag by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnsweredTagDTO> findOne(Long id) {
        log.debug("Request to get AnsweredTag : {}", id);
        return answeredTagRepository.findById(id).map(answeredTagMapper::toDto);
    }

    /**
     * Delete the answeredTag by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnsweredTag : {}", id);
        answeredTagRepository.deleteById(id);
    }
}
