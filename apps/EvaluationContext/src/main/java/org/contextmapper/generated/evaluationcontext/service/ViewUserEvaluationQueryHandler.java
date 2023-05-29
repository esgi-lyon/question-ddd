package org.contextmapper.generated.evaluationcontext.service;

import org.contextmapper.generated.evaluationcontext.domain.Evaluation;
import org.contextmapper.generated.evaluationcontext.repository.CustomEvaluationRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

/**
 * Service Implementation for managing {@link Evaluation}.
 */
@Service
@Transactional
public class ViewUserEvaluationQueryHandler {

    private final Logger log = LoggerFactory.getLogger(ViewUserEvaluationQueryHandler.class);

    private final CustomEvaluationRepository evaluationRepository;

    private final EvaluationMapper evaluationMapper;

    public ViewUserEvaluationQueryHandler(CustomEvaluationRepository evaluationRepository, EvaluationMapper evaluationMapper) {
        this.evaluationRepository = evaluationRepository;
        this.evaluationMapper = evaluationMapper;
    }

    public List<EvaluationDTO> handle(@Nullable String userMail) {
        final var userToViewEvals = Objects.isNull(userMail)
            ? SecurityContextHolder.getContext().getAuthentication().getName()
            : userMail;

        log.info("Checking user" + userToViewEvals);

        return evaluationMapper.toDto(evaluationRepository.findAllByUserMail(userToViewEvals));
    }

}
