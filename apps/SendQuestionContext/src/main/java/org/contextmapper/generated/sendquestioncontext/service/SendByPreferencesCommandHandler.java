package org.contextmapper.generated.sendquestioncontext.service;

import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfos;
import org.contextmapper.generated.sendquestioncontext.domain.enumeration.QuestionNotificationStatus;
import org.contextmapper.generated.sendquestioncontext.repository.*;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedQuestionEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.SendByPreferencesCommandDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentMapper;
import org.contextmapper.generated.sendquestioncontext.service.mapper.SendByPreferencesCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Primary
@Service
@Transactional
public class SendByPreferencesCommandHandler extends SendByPreferencesCommandService {
    private final Logger log = LoggerFactory.getLogger(SendByPreferencesCommandHandler.class);

    private final QuestionSentService questionSentService;

    private final CustomQuestionSentRepository customQuestionSentRepository;

    private final CustomUserPreferencesRepository customUserPreferencesRepository;

    private final UserPreferencesTagInfosRepository customUserPreferencesTagInfosRepository;

    private final NotifiedQuestionEventService notifiedQuestionEventService;

    private final QuestionSentMapper questionSentMapper;

    private final UserPreferencesService userPreferencesService;  // Assuming a service to fetch user preferences

    private final

    public SendByPreferencesCommandHandler(
        SendByPreferencesCommandRepository sendQuestionByTagsPreferencesCommandRepository,
        QuestionSentService questionSentService,
        NotifiedQuestionEventService notifiedQuestionEventService,
        QuestionSentMapper questionSentMapper,
        UserPreferencesService userPreferencesService,
        SendByPreferencesCommandMapper sendByPreferencesCommandMapper,
        CustomQuestionSentRepository customQuestionSentRepository,
        CustomUserPreferencesRepository customUserPreferencesRepository,
        UserPreferencesTagInfosRepository customUserPreferencesTagInfosRepository
    ) {
        super(sendQuestionByTagsPreferencesCommandRepository, sendByPreferencesCommandMapper);
        this.questionSentService = questionSentService;
        this.notifiedQuestionEventService = notifiedQuestionEventService;
        this.userPreferencesService = userPreferencesService;
        this.customQuestionSentRepository = customQuestionSentRepository;
        this.questionSentMapper = questionSentMapper;
        this.customUserPreferencesRepository = customUserPreferencesRepository;
        this.customUserPreferencesTagInfosRepository = customUserPreferencesTagInfosRepository;
    }

    public SendByPreferencesCommandDTO handleSendQuestionByTagsPreferencesCommand(SendByPreferencesCommandDTO questionSentDTO) {
        log.info("Handle command to send question by tags preferences");

        if (!questionSentDTO.getQuestionToSend().getStatus().equals(QuestionNotificationStatus.PREPARING)) {
            throw new RuntimeException("Question is not ready or already sent");
        }

        customQuestionSentRepository.findById(questionSentDTO.getId())
            .ifPresent(questionSent -> {
                final var interestedUsers = customUserPreferencesRepository.findAllByPreferencesContaining(
                    customUserPreferencesTagInfosRepository.findAllById(
                        questionSent.getTags().stream().map(QuestionSentTagInfos::getTagId).collect(Collectors.toUnmodifiableSet())
                    ).stream().collect(Collectors.toUnmodifiableSet())
                );
                final var notified = new NotifiedQuestionEventDTO();
                notified.setQuestionResource(questionSentMapper.toDto(questionSent));
                
                notifiedQuestionEventService.save(notified);
            });

        return save(new SendByPreferencesCommandDTO());
    }
}
