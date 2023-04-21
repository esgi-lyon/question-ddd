package org.contextmapper.generated.skillcontext.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.TagId;
import org.contextmapper.generated.skillcontext.repository.TagIdRepository;
import org.contextmapper.generated.skillcontext.service.TagIdService;
import org.contextmapper.generated.skillcontext.service.dto.TagIdDTO;
import org.contextmapper.generated.skillcontext.service.mapper.TagIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TagId}.
 */
@Service
@Transactional
public class TagIdServiceImpl implements TagIdService {

    private final Logger log = LoggerFactory.getLogger(TagIdServiceImpl.class);

    private final TagIdRepository tagIdRepository;

    private final TagIdMapper tagIdMapper;

    public TagIdServiceImpl(TagIdRepository tagIdRepository, TagIdMapper tagIdMapper) {
        this.tagIdRepository = tagIdRepository;
        this.tagIdMapper = tagIdMapper;
    }

    @Override
    public TagIdDTO save(TagIdDTO tagIdDTO) {
        log.debug("Request to save TagId : {}", tagIdDTO);
        TagId tagId = tagIdMapper.toEntity(tagIdDTO);
        tagId = tagIdRepository.save(tagId);
        return tagIdMapper.toDto(tagId);
    }

    @Override
    public TagIdDTO update(TagIdDTO tagIdDTO) {
        log.debug("Request to update TagId : {}", tagIdDTO);
        TagId tagId = tagIdMapper.toEntity(tagIdDTO);
        tagId = tagIdRepository.save(tagId);
        return tagIdMapper.toDto(tagId);
    }

    @Override
    public Optional<TagIdDTO> partialUpdate(TagIdDTO tagIdDTO) {
        log.debug("Request to partially update TagId : {}", tagIdDTO);

        return tagIdRepository
            .findById(tagIdDTO.getId())
            .map(existingTagId -> {
                tagIdMapper.partialUpdate(existingTagId, tagIdDTO);

                return existingTagId;
            })
            .map(tagIdRepository::save)
            .map(tagIdMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagIdDTO> findAll() {
        log.debug("Request to get all TagIds");
        return tagIdRepository.findAll().stream().map(tagIdMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TagIdDTO> findOne(Long id) {
        log.debug("Request to get TagId : {}", id);
        return tagIdRepository.findById(id).map(tagIdMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TagId : {}", id);
        tagIdRepository.deleteById(id);
    }
}
