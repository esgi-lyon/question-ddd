package org.contextmapper.generated.sendquestioncontext.service;

import org.contextmapper.generated.sendquestioncontext.client.skillcontext.api.TagResourceApi;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.model.TagDTO;
import org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionCommand;
import org.contextmapper.generated.sendquestioncontext.domain.enumeration.QuestionNotificationStatus;
import org.contextmapper.generated.sendquestioncontext.repository.PrepareQuestionCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreatedQuestionEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagInfosDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Primary
@Service
@Transactional
public class PrepareQuestionCommandHandler extends PrepareQuestionCommandService {
    private final Logger log = LoggerFactory.getLogger(PrepareQuestionCommandHandler.class);

    private final QuestionSentService questionSentService;

    private final CreatedQuestionEventService createdQuestionEventService;

    private final QuestionSentMapper questionSentMapper;

    private final TagResourceApi tagResourceApi;  // Assuming an external API to fetch questions based on tag

    private final QuestionSentTagInfosService questionSentTagIdService;

    public PrepareQuestionCommandHandler(
            PrepareQuestionCommandRepository prepareQuestionsCommandRepository,
            QuestionSentService questionSentService,
            CreatedQuestionEventService createdQuestionEventService,
            QuestionSentMapper questionSentMapper,
            TagResourceApi tagResourceApi,
            QuestionSentTagInfosService questionSentTagIdService
    ) {
        super(prepareQuestionsCommandRepository);
        this.questionSentService = questionSentService;
        this.createdQuestionEventService = createdQuestionEventService;
        this.questionSentMapper = questionSentMapper;
        this.tagResourceApi = tagResourceApi;
        this.questionSentTagIdService = questionSentTagIdService;
    }

    public PrepareQuestionCommand handlePrepareQuestionsCommand(PrepareQuestionCommand prepareQuestionsCommand) {
        log.info("Handle command to prepare questions for tag: {}", prepareQuestionsCommand);
        Random rand = new Random();
        Set<TagDTO> randomTags = rand.longs(3L, 0L, 100L)
                .boxed()
                .map(tagResourceApi::getTag)
                .map(HttpEntity::getBody)
                .collect(Collectors.toUnmodifiableSet());

        final var questionSendTags = randomTags.stream().map(tag -> {
            final var questionSentTagId = new QuestionSentTagInfosDTO();
            questionSentTagId.setId(tag.getId());
            return questionSentTagIdService.save(questionSentTagId);
        }).collect(Collectors.toList());

        QuestionSentDTO questionSentDTO = new QuestionSentDTO();

        questionSentDTO.setStatus(QuestionNotificationStatus.SENT);
        questionSentDTO.setSentDate(LocalDate.now());
        final var questionSentTagId = new QuestionSentTagInfosDTO();
        // questionSentTagId.setId(question.getTag());
        questionSentTagIdService.save(questionSentTagId);
// Assuming single tag for simplicity
        //questionSentDTO.setResourceId(new ResourceIdDTO(question.getId()));  // Assuming question ID is the resource ID

        QuestionSentDTO saved = questionSentService.save(questionSentDTO);

        CreatedQuestionEventDTO eventDTO = new CreatedQuestionEventDTO();
        eventDTO.setQuestionAndTag(saved);
        createdQuestionEventService.save(eventDTO);

        return save(new PrepareQuestionCommand());
    }
}
