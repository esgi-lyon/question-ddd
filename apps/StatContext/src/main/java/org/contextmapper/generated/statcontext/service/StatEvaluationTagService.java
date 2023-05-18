package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.StatEvaluationTag;
import org.contextmapper.generated.statcontext.repository.StatEvaluationTagRepository;
import org.contextmapper.generated.statcontext.service.dto.StatEvaluationTagDTO;
import org.contextmapper.generated.statcontext.service.mapper.StatEvaluationTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link StatEvaluationTag}.
 */
@Service
@Transactional
public class StatEvaluationTagService {

    private final Logger log = LoggerFactory.getLogger(StatEvaluationTagService.class);

    private final StatEvaluationTagRepository statEvaluationTagRepository;

    private final StatEvaluationTagMapper statEvaluationTagMapper;

    public StatEvaluationTagService(
        StatEvaluationTagRepository statEvaluationTagRepository,
        StatEvaluationTagMapper statEvaluationTagMapper
    ) {
        this.statEvaluationTagRepository = statEvaluationTagRepository;
        this.statEvaluationTagMapper = statEvaluationTagMapper;
    }

    /**
     * Save a statEvaluationTag.
     *
     * @param statEvaluationTagDTO the entity to save.
     * @return the persisted entity.
     */
    public StatEvaluationTagDTO save(StatEvaluationTagDTO statEvaluationTagDTO) {
        log.debug("Request to save StatEvaluationTag : {}", statEvaluationTagDTO);
        StatEvaluationTag statEvaluationTag = statEvaluationTagMapper.toEntity(statEvaluationTagDTO);
        statEvaluationTag = statEvaluationTagRepository.save(statEvaluationTag);
        return statEvaluationTagMapper.toDto(statEvaluationTag);
    }

    /**
     * Update a statEvaluationTag.
     *
     * @param statEvaluationTagDTO the entity to save.
     * @return the persisted entity.
     */
    public StatEvaluationTagDTO update(StatEvaluationTagDTO statEvaluationTagDTO) {
        log.debug("Request to update StatEvaluationTag : {}", statEvaluationTagDTO);
        StatEvaluationTag statEvaluationTag = statEvaluationTagMapper.toEntity(statEvaluationTagDTO);
        statEvaluationTag = statEvaluationTagRepository.save(statEvaluationTag);
        return statEvaluationTagMapper.toDto(statEvaluationTag);
    }

    /**
     * Partially update a statEvaluationTag.
     *
     * @param statEvaluationTagDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<StatEvaluationTagDTO> partialUpdate(StatEvaluationTagDTO statEvaluationTagDTO) {
        log.debug("Request to partially update StatEvaluationTag : {}", statEvaluationTagDTO);

        return statEvaluationTagRepository
            .findById(statEvaluationTagDTO.getId())
            .map(existingStatEvaluationTag -> {
                statEvaluationTagMapper.partialUpdate(existingStatEvaluationTag, statEvaluationTagDTO);

                return existingStatEvaluationTag;
            })
            .map(statEvaluationTagRepository::save)
            .map(statEvaluationTagMapper::toDto);
    }

    /**
     * Get all the statEvaluationTags.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<StatEvaluationTagDTO> findAll() {
        log.debug("Request to get all StatEvaluationTags");
        return statEvaluationTagRepository
            .findAll()
            .stream()
            .map(statEvaluationTagMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one statEvaluationTag by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StatEvaluationTagDTO> findOne(Long id) {
        log.debug("Request to get StatEvaluationTag : {}", id);
        return statEvaluationTagRepository.findById(id).map(statEvaluationTagMapper::toDto);
    }

    /**
     * Delete the statEvaluationTag by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete StatEvaluationTag : {}", id);
        statEvaluationTagRepository.deleteById(id);
    }
}
