package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.RejectResourceTag;
import org.contextmapper.generated.questioncontext.repository.RejectResourceTagRepository;
import org.contextmapper.generated.questioncontext.service.dto.RejectResourceTagDTO;
import org.contextmapper.generated.questioncontext.service.mapper.RejectResourceTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RejectResourceTag}.
 */
@Service
@Transactional
public class RejectResourceTagService {

    private final Logger log = LoggerFactory.getLogger(RejectResourceTagService.class);

    private final RejectResourceTagRepository rejectResourceTagRepository;

    private final RejectResourceTagMapper rejectResourceTagMapper;

    public RejectResourceTagService(
        RejectResourceTagRepository rejectResourceTagRepository,
        RejectResourceTagMapper rejectResourceTagMapper
    ) {
        this.rejectResourceTagRepository = rejectResourceTagRepository;
        this.rejectResourceTagMapper = rejectResourceTagMapper;
    }

    /**
     * Save a rejectResourceTag.
     *
     * @param rejectResourceTagDTO the entity to save.
     * @return the persisted entity.
     */
    public RejectResourceTagDTO save(RejectResourceTagDTO rejectResourceTagDTO) {
        log.debug("Request to save RejectResourceTag : {}", rejectResourceTagDTO);
        RejectResourceTag rejectResourceTag = rejectResourceTagMapper.toEntity(rejectResourceTagDTO);
        rejectResourceTag = rejectResourceTagRepository.save(rejectResourceTag);
        return rejectResourceTagMapper.toDto(rejectResourceTag);
    }

    /**
     * Update a rejectResourceTag.
     *
     * @param rejectResourceTagDTO the entity to save.
     * @return the persisted entity.
     */
    public RejectResourceTagDTO update(RejectResourceTagDTO rejectResourceTagDTO) {
        log.debug("Request to update RejectResourceTag : {}", rejectResourceTagDTO);
        RejectResourceTag rejectResourceTag = rejectResourceTagMapper.toEntity(rejectResourceTagDTO);
        rejectResourceTag = rejectResourceTagRepository.save(rejectResourceTag);
        return rejectResourceTagMapper.toDto(rejectResourceTag);
    }

    /**
     * Partially update a rejectResourceTag.
     *
     * @param rejectResourceTagDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RejectResourceTagDTO> partialUpdate(RejectResourceTagDTO rejectResourceTagDTO) {
        log.debug("Request to partially update RejectResourceTag : {}", rejectResourceTagDTO);

        return rejectResourceTagRepository
            .findById(rejectResourceTagDTO.getId())
            .map(existingRejectResourceTag -> {
                rejectResourceTagMapper.partialUpdate(existingRejectResourceTag, rejectResourceTagDTO);

                return existingRejectResourceTag;
            })
            .map(rejectResourceTagRepository::save)
            .map(rejectResourceTagMapper::toDto);
    }

    /**
     * Get all the rejectResourceTags.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RejectResourceTagDTO> findAll() {
        log.debug("Request to get all RejectResourceTags");
        return rejectResourceTagRepository
            .findAll()
            .stream()
            .map(rejectResourceTagMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one rejectResourceTag by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RejectResourceTagDTO> findOne(Long id) {
        log.debug("Request to get RejectResourceTag : {}", id);
        return rejectResourceTagRepository.findById(id).map(rejectResourceTagMapper::toDto);
    }

    /**
     * Delete the rejectResourceTag by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RejectResourceTag : {}", id);
        rejectResourceTagRepository.deleteById(id);
    }
}
