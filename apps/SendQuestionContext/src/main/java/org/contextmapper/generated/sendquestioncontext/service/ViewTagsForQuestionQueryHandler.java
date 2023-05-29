package org.contextmapper.generated.sendquestioncontext.service;

import org.contextmapper.generated.sendquestioncontext.client.skillcontext.model.TagInfosDTO;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfosViewedEvent;
import org.contextmapper.generated.sendquestioncontext.repository.CustomQuestionSentTagInfosRepository;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentTagInfosViewedEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagInfosDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagInfosViewedEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.ViewTagsForQuestionCommandDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentTagInfosMapper;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentTagInfosViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing {@link QuestionSentTagInfosViewedEvent}.
 */
@Service
@Transactional
public class ViewTagsForQuestionQueryHandler extends QuestionSentTagInfosViewedEventService {

    private final Logger log = LoggerFactory.getLogger(ViewTagsForQuestionQueryHandler.class);

    private final QuestionSentTagInfosMapper tagInfosMapper;

    private final QuestionSentService questionSentService;

    private final CustomQuestionSentTagInfosRepository questionSentTagInfosRepository;


    public ViewTagsForQuestionQueryHandler(
        QuestionSentTagInfosViewedEventRepository repository,
        QuestionSentTagInfosViewedEventMapper questionSentTagInfosViewedEventMapper,
        CustomQuestionSentTagInfosRepository questionSentTagInfosRepository,
        QuestionSentTagInfosMapper tagInfosMapper,
        QuestionSentService questionSentService
    ) {
        super(repository, questionSentTagInfosViewedEventMapper);
        this.questionSentTagInfosRepository = questionSentTagInfosRepository;
        this.tagInfosMapper = tagInfosMapper;
        this.questionSentService = questionSentService;
    }

    /**
     * Save a questionSentTagInfosViewedEvent.
     *
     * @param questionId the entity to save.
     * @return the persisted entity.
     */
    public List<QuestionSentTagInfosDTO> handle(Long questionId) {
        log.debug("Request to save QuestionSentTagInfosViewedEvent : {}", questionId);

        final var questionSent = questionSentService.findOne(questionId).orElseThrow();
        final var questionSentId = questionSent.getId();

        final var allByQuestionSentId = questionSentTagInfosRepository.findAllByQuestionSentId(questionSentId)
            .stream().map(tagInfosMapper::toDto).toList();

        save(new QuestionSentTagInfosViewedEventDTO());

        return allByQuestionSentId;
    }

}
