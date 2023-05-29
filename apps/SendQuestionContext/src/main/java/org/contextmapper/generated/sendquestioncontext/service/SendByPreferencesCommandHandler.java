package org.contextmapper.generated.sendquestioncontext.service;

import org.contextmapper.generated.sendquestioncontext.domain.NotifiedUsers;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfos;
import org.contextmapper.generated.sendquestioncontext.domain.UserWithPreferencesId;
import org.contextmapper.generated.sendquestioncontext.domain.enumeration.QuestionNotificationStatus;
import org.contextmapper.generated.sendquestioncontext.repository.*;
import org.contextmapper.generated.sendquestioncontext.service.dto.*;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentMapper;
import org.contextmapper.generated.sendquestioncontext.service.mapper.SendByPreferencesCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    private final NotifiedUsersService notifiedUsersService;

    private final UserWithPreferencesIdService userWithPreferencesIdService;

    public SendByPreferencesCommandHandler(
        SendByPreferencesCommandRepository sendQuestionByTagsPreferencesCommandRepository,
        QuestionSentService questionSentService,
        NotifiedQuestionEventService notifiedQuestionEventService,
        QuestionSentMapper questionSentMapper,
        UserPreferencesService userPreferencesService,
        SendByPreferencesCommandMapper sendByPreferencesCommandMapper,
        CustomQuestionSentRepository customQuestionSentRepository,
        CustomUserPreferencesRepository customUserPreferencesRepository,
        UserPreferencesTagInfosRepository customUserPreferencesTagInfosRepository,
        NotifiedUsersService notifiedUsersService,
        UserWithPreferencesIdService userWithPreferencesIdService
    ) {
        super(sendQuestionByTagsPreferencesCommandRepository, sendByPreferencesCommandMapper);
        this.questionSentService = questionSentService;
        this.notifiedQuestionEventService = notifiedQuestionEventService;
        this.userPreferencesService = userPreferencesService;
        this.customQuestionSentRepository = customQuestionSentRepository;
        this.questionSentMapper = questionSentMapper;
        this.customUserPreferencesRepository = customUserPreferencesRepository;
        this.customUserPreferencesTagInfosRepository = customUserPreferencesTagInfosRepository;
        this.notifiedUsersService = notifiedUsersService;
        this.userWithPreferencesIdService = userWithPreferencesIdService;
    }

    public NotifiedUsersDTO handleSendQuestionByTagsPreferencesCommand(SendByPreferencesCommandDTO questiontoSendDTO) {
        log.info("Handle command to send question by tags preferences");

        final var questionSent = customQuestionSentRepository.findById(questiontoSendDTO.getQuestionToSend().getId()).orElseThrow();

        if (!questionSent.getStatus().equals(QuestionNotificationStatus.PREPARING)) {
            throw new RuntimeException("Question is not ready or already sent");
        }

        questionSent.setStatus(QuestionNotificationStatus.SENT);
        questionSent.setSentDate(LocalDate.now());

        final var questionSentSaved = questionSentService.save(questionSentMapper.toDto(questionSent));

        final var notified = new NotifiedUsersDTO();
        notified.setQuestion(questionSentSaved);


        customUserPreferencesRepository.findAllByPreferencesIn(
            customUserPreferencesTagInfosRepository.findAllById(
                questionSent.getTags().stream().map(QuestionSentTagInfos::getTagId).collect(Collectors.toUnmodifiableSet())
            ).stream().collect(Collectors.toUnmodifiableSet())
        ).forEach(e -> {
            final var u = new UserWithPreferencesIdDTO();
            u.setId(e.getUser().getId());
            u.setMail(e.getUser().getMail());
            u.setNotifiedUsers(notified);
            userWithPreferencesIdService.save(u);
        });

        return notifiedUsersService.save(notified);
    }
}
