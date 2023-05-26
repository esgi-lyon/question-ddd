package org.contextmapper.generated.sendquestioncontext.service;

import org.contextmapper.generated.sendquestioncontext.domain.SendByPreferencesCommand;
import org.contextmapper.generated.sendquestioncontext.repository.SendByPreferencesCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@Transactional
public class SendByPreferencesCommandHandler extends SendByPreferencesCommandService {
    private final Logger log = LoggerFactory.getLogger(SendByPreferencesCommandHandler.class);

    private final QuestionSentService questionSentService;

    private final NotifiedQuestionEventService notifiedQuestionEventService;

    private final QuestionSentMapper questionSentMapper;

    private final UserPreferencesService userPreferencesService;  // Assuming a service to fetch user preferences

    public SendByPreferencesCommandHandler(
            SendByPreferencesCommandRepository sendQuestionByTagsPreferencesCommandRepository,
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

    public SendByPreferencesCommand handleSendQuestionByTagsPreferencesCommand(QuestionSentDTO questionSentDTO) {
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


        return save(new SendByPreferencesCommand().questionToSend(questionSentMapper.toEntity(questionSentDTO)));
    }
}
