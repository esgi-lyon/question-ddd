package org.contextmapper.generated.sendquestioncontext.service;

import org.contextmapper.generated.sendquestioncontext.domain.SendQuestionByTagsPreferencesCommand;
import org.contextmapper.generated.sendquestioncontext.domain.enumeration.QuestionNotificationStatus;
import org.contextmapper.generated.sendquestioncontext.repository.SendQuestionByTagsPreferencesCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedQuestionEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserPreferencesDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

@Primary
@Service
@Transactional
public class SendQuestionByTagsPreferencesCommandHandler extends SendQuestionByTagsPreferencesCommandService {
    private final Logger log = LoggerFactory.getLogger(SendQuestionByTagsPreferencesCommandHandler.class);

    private final QuestionSentService questionSentService;

    private final NotifiedQuestionEventService notifiedQuestionEventService;

    private final QuestionSentMapper questionSentMapper;

    private final UserPreferencesService userPreferencesService;  // Assuming a service to fetch user preferences

    public SendQuestionByTagsPreferencesCommandHandler(
        SendQuestionByTagsPreferencesCommandRepository sendQuestionByTagsPreferencesCommandRepository,
        QuestionSentService questionSentService,
        NotifiedQuestionEventService notifiedQuestionEventService,
        QuestionSentMapper questionSentMapper,
        UserPreferencesService userPreferencesService
    ) {
        super(sendQuestionByTagsPreferencesCommandRepository);
        this.questionSentService = questionSentService;
        this.notifiedQuestionEventService = notifiedQuestionEventService;
        this.questionSentMapper = questionSentMapper;
        this.userPreferencesService = userPreferencesService;
    }

    public SendQuestionByTagsPreferencesCommand handleSendQuestionByTagsPreferencesCommand(QuestionSentDTO questionSentDTO) {
        log.info("Handle command to send question by tags preferences");

        /*
        UserPreferencesDTO userPreferences = userPreferencesService.getUserPreferences(questionSentDTO.getUser().getUserId());  // Get user preferences

        for (UserPreferencesTagInfos preference : userPreferences.getPreferences()) {  // For each preference
            Question question = questionApi.getQuestionByTag(preference.getTagId());  // Get a question based on the preference

            questionSentDTO.setStatus(QuestionNotificationStatus.SENT);
            questionSentDTO.setSentDate(LocalDate.now());
            questionSentDTO.setTags(List.of(new QuestionSentTagId(preference.getTagId())));  // Assuming single tag for simplicity
            questionSentDTO.setResourceId(new ResourceId(question.getId()));  // Assuming question ID is the resource ID

            QuestionSentDTO saved = questionSentService.save(questionSentDTO);

            NotifiedQuestionEventDTO eventDTO = new NotifiedQuestionEventDTO();
            eventDTO.setQuestionResource(saved);
            notifiedQuestionEventService.save(eventDTO);
        }
        */
         

        return save(new SendQuestionByTagsPreferencesCommand().questionToSend(questionSentMapper.toEntity(questionSentDTO)));
    }
}
