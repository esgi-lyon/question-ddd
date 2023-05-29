package org.contextmapper.generated.sendquestioncontext.service;

import org.apache.commons.collections4.CollectionUtils;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.api.QuestionResourceResourceApi;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.api.QuestionResourceTagInfosResourceApi;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.api.TagInfosResourceApi;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.api.TagResourceApi;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.model.TagDTO;
import org.contextmapper.generated.sendquestioncontext.domain.enumeration.QuestionNotificationStatus;
import org.contextmapper.generated.sendquestioncontext.repository.CreatedQuestionEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.*;
import org.contextmapper.generated.sendquestioncontext.service.mapper.CreatedQuestionEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Primary
@Service
@Transactional
public class PrepareQuestionCommandHandler extends CreatedQuestionEventService {
    private final Logger log = LoggerFactory.getLogger(PrepareQuestionCommandHandler.class);

    private final QuestionSentService questionSentService;

    private final TagResourceApi tagResourceApi;  // Assuming an external API to fetch questions based on tag

    private final QuestionResourceResourceApi resourceResourceApi;

    private final QuestionSentTagInfosService questionSentTagIdService;
    private final ResourceIdService resourceIdService;

    private final QuestionResourceTagInfosResourceApi tagInfosResourceApi;

    public PrepareQuestionCommandHandler(
        QuestionSentService questionSentService,
        CreatedQuestionEventRepository eventRepository,
        TagResourceApi tagResourceApi,
        QuestionSentTagInfosService questionSentTagIdService,
        ResourceIdService resourceIdService,
        CreatedQuestionEventMapper createdQuestionEventMapper,
        QuestionResourceResourceApi resourceResourceApi,
        QuestionResourceTagInfosResourceApi tagInfosResourceApi
    ) {
        super(eventRepository, createdQuestionEventMapper);
        this.questionSentService = questionSentService;
        this.tagResourceApi = tagResourceApi;
        this.questionSentTagIdService = questionSentTagIdService;
        this.resourceIdService = resourceIdService;
        this.resourceResourceApi = resourceResourceApi;
        this.tagInfosResourceApi = tagInfosResourceApi;
    }

    public CreatedQuestionEventDTO handlePrepareQuestionsCommand(PrepareQuestionCommandDTO prepareQuestionsCommand) {
        log.info("Handle command to prepare questions for tag: {}", prepareQuestionsCommand);

        QuestionSentDTO questionSentDTO = new QuestionSentDTO();
        final var resourceIdDTO = new ResourceIdDTO();
        resourceIdDTO.setId(prepareQuestionsCommand.getResourceId());
        final var savedResourceId = resourceIdService.save(resourceIdDTO);
        questionSentDTO.setResourceId(savedResourceId);
        questionSentDTO.setStatus(QuestionNotificationStatus.PREPARING);

        final var saved = questionSentService.save(questionSentDTO);

        final var tagCorrect = new TagDTO();
        final var questionResource = Optional.ofNullable(
                resourceResourceApi.getQuestionResource(prepareQuestionsCommand.getResourceId())
                    .getBody()
            )
            .orElseThrow();
        final var tagInfos = Optional.ofNullable(tagInfosResourceApi.getQuestionResourceTagInfos(questionResource.getTagInfos().getId()).getBody())
            .orElseThrow();
        tagCorrect.setId(tagInfos.getTagId());
        tagCorrect.setName(tagInfos.getName());

        final var randomTags = CollectionUtils.union(
            Objects.requireNonNull(tagResourceApi.getAllTags().getBody())
                .stream()
                .limit(2).collect(Collectors.toUnmodifiableSet()), Set.of(tagCorrect));

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
