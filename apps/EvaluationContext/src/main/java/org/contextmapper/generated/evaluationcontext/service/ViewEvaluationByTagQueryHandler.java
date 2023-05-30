package org.contextmapper.generated.evaluationcontext.service;

import org.contextmapper.generated.evaluationcontext.domain.Evaluation;
import org.contextmapper.generated.evaluationcontext.repository.CustomEvaluationRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Service Implementation for managing {@link Evaluation}.
 */
@Service
@Transactional
public class ViewEvaluationByTagQueryHandler {

    private final Logger log = LoggerFactory.getLogger(ViewEvaluationByTagQueryHandler.class);

    private final CustomEvaluationRepository evaluationRepository;

    private final EvaluationMapper evaluationMapper;

    public ViewEvaluationByTagQueryHandler(CustomEvaluationRepository evaluationRepository, EvaluationMapper evaluationMapper) {
        this.evaluationRepository = evaluationRepository;
        this.evaluationMapper = evaluationMapper;
    }

    public List<EvaluationDTO> handle(@Nullable Long tagId) {

        log.info("Get evaluation for tag :{}", tagId);

        return evaluationMapper.toDto(evaluationRepository.findAllByTagTagId(tagId));
    }
}
