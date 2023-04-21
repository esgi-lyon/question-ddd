package org.contextmapper.generated.questioncontext.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.QuestionResourceTag;
import org.contextmapper.generated.questioncontext.repository.QuestionResourceTagRepository;
import org.contextmapper.generated.questioncontext.service.QuestionResourceTagService;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagDTO;
import org.contextmapper.generated.questioncontext.service.mapper.QuestionResourceTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QuestionResourceTag}.
 */
@Service
@Transactional
public class QuestionResourceTagServiceImpl implements QuestionResourceTagService {

    private final Logger log = LoggerFactory.getLogger(QuestionResourceTagServiceImpl.class);

    private final QuestionResourceTagRepository questionResourceTagRepository;

    private final QuestionResourceTagMapper questionResourceTagMapper;

    public QuestionResourceTagServiceImpl(
        QuestionResourceTagRepository questionResourceTagRepository,
        QuestionResourceTagMapper questionResourceTagMapper
    ) {
        this.questionResourceTagRepository = questionResourceTagRepository;
        this.questionResourceTagMapper = questionResourceTagMapper;
    }

    @Override
    public QuestionResourceTagDTO save(QuestionResourceTagDTO questionResourceTagDTO) {
        log.debug("Request to save QuestionResourceTag : {}", questionResourceTagDTO);
        QuestionResourceTag questionResourceTag = questionResourceTagMapper.toEntity(questionResourceTagDTO);
        questionResourceTag = questionResourceTagRepository.save(questionResourceTag);
        return questionResourceTagMapper.toDto(questionResourceTag);
    }

    @Override
    public QuestionResourceTagDTO update(QuestionResourceTagDTO questionResourceTagDTO) {
        log.debug("Request to update QuestionResourceTag : {}", questionResourceTagDTO);
        QuestionResourceTag questionResourceTag = questionResourceTagMapper.toEntity(questionResourceTagDTO);
        questionResourceTag = questionResourceTagRepository.save(questionResourceTag);
        return questionResourceTagMapper.toDto(questionResourceTag);
    }

    @Override
    public Optional<QuestionResourceTagDTO> partialUpdate(QuestionResourceTagDTO questionResourceTagDTO) {
        log.debug("Request to partially update QuestionResourceTag : {}", questionResourceTagDTO);

        return questionResourceTagRepository
            .findById(questionResourceTagDTO.getId())
            .map(existingQuestionResourceTag -> {
                questionResourceTagMapper.partialUpdate(existingQuestionResourceTag, questionResourceTagDTO);

                return existingQuestionResourceTag;
            })
            .map(questionResourceTagRepository::save)
            .map(questionResourceTagMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResourceTagDTO> findAll() {
        log.debug("Request to get all QuestionResourceTags");
        return questionResourceTagRepository
            .findAll()
            .stream()
            .map(questionResourceTagMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionResourceTagDTO> findOne(Long id) {
        log.debug("Request to get QuestionResourceTag : {}", id);
        return questionResourceTagRepository.findById(id).map(questionResourceTagMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuestionResourceTag : {}", id);
        questionResourceTagRepository.deleteById(id);
    }
}
