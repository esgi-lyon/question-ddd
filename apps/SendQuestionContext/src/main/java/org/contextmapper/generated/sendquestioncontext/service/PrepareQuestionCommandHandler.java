package org.contextmapper.generated.sendquestioncontext.service;

import org.contextmapper.generated.sendquestioncontext.client.skillcontext.api.TagResourceApi;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.model.TagDTO;
import org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionsCommand;
import org.contextmapper.generated.sendquestioncontext.domain.enumeration.QuestionNotificationStatus;
import org.contextmapper.generated.sendquestioncontext.repository.PrepareQuestionsCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreatedQuestionEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagIdDTO;
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
import java.util.stream.Collectors;

@Primary
@Service
@Transactional
public class PrepareQuestionCommandHandler extends PrepareQuestionsCommandService {
    private final Logger log = LoggerFactory.getLogger(PrepareQuestionCommandHandler.class);

    private final QuestionSentService questionSentService;

    private final CreatedQuestionEventService createdQuestionEventService;

    private final QuestionSentMapper questionSentMapper;

    private final TagResourceApi tagResourceApi;  // Assuming an external API to fetch questions based on tag

    private final QuestionSentTagIdService questionSentTagIdService;

    public PrepareQuestionCommandHandler(
        PrepareQuestionsCommandRepository prepareQuestionsCommandRepository,
        QuestionSentService questionSentService,
        CreatedQuestionEventService createdQuestionEventService,
        QuestionSentMapper questionSentMapper,
        TagResourceApi tagResourceApi,
        QuestionSentTagIdService questionSentTagIdService
    ) {
        super(prepareQuestionsCommandRepository);
        this.questionSentService = questionSentService;
        this.createdQuestionEventService = createdQuestionEventService;
        this.questionSentMapper = questionSentMapper;
        this.tagResourceApi = tagResourceApi;
        this.questionSentTagIdService = questionSentTagIdService;
    }

    public PrepareQuestionsCommand handlePrepareQuestionsCommand(PrepareQuestionsCommand prepareQuestionsCommand) {
        log.info("Handle command to prepare questions for tag: {}", prepareQuestionsCommand);
        Random rand = new Random();
        List<TagDTO> randomTags = rand.longs(3L, 0L, 100L)
            .boxed()
            .map(randNb -> tagResourceApi.getTag(randNb))
            .map(HttpEntity::getBody)
            .collect(Collectors.toList());

        final var questionSendTags = randomTags.stream().map(tag -> {
            final var questionSentTagId = new QuestionSentTagIdDTO();
            questionSentTagId.setId(tag.getId());
            return questionSentTagIdService.save(questionSentTagId);
        }).collect(Collectors.toList());

        QuestionSentDTO questionSentDTO = new QuestionSentDTO();

        questionSentDTO.setStatus(QuestionNotificationStatus.SENT);
        questionSentDTO.setSentDate(LocalDate.now());
        final var questionSentTagId = new QuestionSentTagIdDTO();
        // questionSentTagId.setId(question.getTag());
        questionSentTagIdService.save(questionSentTagId);
// Assuming single tag for simplicity
        //questionSentDTO.setResourceId(new ResourceIdDTO(question.getId()));  // Assuming question ID is the resource ID

        QuestionSentDTO saved = questionSentService.save(questionSentDTO);

        CreatedQuestionEventDTO eventDTO = new CreatedQuestionEventDTO();
        eventDTO.setQuestionAndTag(saved);
        createdQuestionEventService.save(eventDTO);

        return save(new PrepareQuestionsCommand());
    }
}
