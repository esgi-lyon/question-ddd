package org.contextmapper.generated.sendquestioncontext.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.Question;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionRepository;
import org.contextmapper.generated.sendquestioncontext.service.QuestionService;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Question}.
 */
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    public QuestionDTO save(QuestionDTO questionDTO) {
        log.debug("Request to save Question : {}", questionDTO);
        Question question = questionMapper.toEntity(questionDTO);
        question = questionRepository.save(question);
        return questionMapper.toDto(question);
    }

    @Override
    public QuestionDTO update(QuestionDTO questionDTO) {
        log.debug("Request to update Question : {}", questionDTO);
        Question question = questionMapper.toEntity(questionDTO);
        // no save call needed as we have no fields that can be updated
        return questionMapper.toDto(question);
    }

    @Override
    public Optional<QuestionDTO> partialUpdate(QuestionDTO questionDTO) {
        log.debug("Request to partially update Question : {}", questionDTO);

        return questionRepository
            .findById(questionDTO.getId())
            .map(existingQuestion -> {
                questionMapper.partialUpdate(existingQuestion, questionDTO);

                return existingQuestion;
            })
            // .map(questionRepository::save)
            .map(questionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTO> findAll() {
        log.debug("Request to get all Questions");
        return questionRepository.findAll().stream().map(questionMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionDTO> findOne(Long id) {
        log.debug("Request to get Question : {}", id);
        return questionRepository.findById(id).map(questionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Question : {}", id);
        questionRepository.deleteById(id);
    }
}
