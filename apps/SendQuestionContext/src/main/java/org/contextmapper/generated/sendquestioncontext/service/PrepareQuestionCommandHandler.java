package org.contextmapper.generated.sendquestioncontext.service;

import org.contextmapper.generated.sendquestioncontext.client.skillcontext.api.TagResourceApi;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.model.TagDTO;
import org.contextmapper.generated.sendquestioncontext.domain.enumeration.QuestionNotificationStatus;
import org.contextmapper.generated.sendquestioncontext.repository.CreatedQuestionEventRepository;
import org.contextmapper.generated.sendquestioncontext.repository.PrepareQuestionCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.*;
import org.contextmapper.generated.sendquestioncontext.service.mapper.CreatedQuestionEventMapper;
import org.contextmapper.generated.sendquestioncontext.service.mapper.PrepareQuestionCommandMapper;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Primary
@Service
@Transactional
public class PrepareQuestionCommandHandler extends CreatedQuestionEventService {
    private final Logger log = LoggerFactory.getLogger(PrepareQuestionCommandHandler.class);

    private final QuestionSentService questionSentService;

    private final TagResourceApi tagResourceApi;  // Assuming an external API to fetch questions based on tag

    private final QuestionSentTagInfosService questionSentTagIdService;
    private final ResourceIdService resourceIdService;

    public PrepareQuestionCommandHandler(
            QuestionSentService questionSentService,
            CreatedQuestionEventRepository eventRepository,
            TagResourceApi tagResourceApi,
            QuestionSentTagInfosService questionSentTagIdService,
            ResourceIdService resourceIdService,
            CreatedQuestionEventMapper createdQuestionEventMapper
    ) {
        super(eventRepository, createdQuestionEventMapper);
        this.questionSentService = questionSentService;
        this.tagResourceApi = tagResourceApi;
        this.questionSentTagIdService = questionSentTagIdService;
        this.resourceIdService = resourceIdService;
    }

    public CreatedQuestionEventDTO handlePrepareQuestionsCommand(PrepareQuestionCommandDTO prepareQuestionsCommand) {
        log.info("Handle command to prepare questions for tag: {}", prepareQuestionsCommand);

        QuestionSentDTO questionSentDTO = new QuestionSentDTO();
        final var resourceIdDTO = new ResourceIdDTO();
        resourceIdDTO.setId(prepareQuestionsCommand.getResourceId());
        final var savedResourceId = resourceIdService.save(resourceIdDTO);
        questionSentDTO.setResourceId(savedResourceId);
        questionSentDTO.setStatus(QuestionNotificationStatus.PREPARING);

        QuestionSentDTO saved = questionSentService.save(questionSentDTO);

        Set<TagDTO> randomTags = Objects.requireNonNull(tagResourceApi.getAllTags().getBody())
            .stream()
            .limit(3)
            .collect(Collectors.toUnmodifiableSet());

        randomTags.forEach(tag -> {
            final var questionSentTagId = new QuestionSentTagInfosDTO();
            questionSentTagId.setTagId(tag.getId());
            questionSentTagId.setTagName(tag.getName());
            questionSentTagId.setQuestionSent(saved);
            questionSentTagIdService.save(questionSentTagId);
        });

        CreatedQuestionEventDTO eventDTO = new CreatedQuestionEventDTO();
        eventDTO.setQuestionAndTag(saved);

        return save(eventDTO);
    }
}
