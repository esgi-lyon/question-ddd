package org.contextmapper.generated.sendquestioncontext.service;

import org.contextmapper.generated.sendquestioncontext.client.skillcontext.api.TagResourceApi;
import org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.api.QueryHandlerResourceApi;
import org.contextmapper.generated.sendquestioncontext.domain.PreferencesAddedEvent;
import org.contextmapper.generated.sendquestioncontext.domain.UserPreferences;
import org.contextmapper.generated.sendquestioncontext.domain.UserPreferencesTagInfos;
import org.contextmapper.generated.sendquestioncontext.domain.UserWithPreferencesId;
import org.contextmapper.generated.sendquestioncontext.repository.CustomUserPreferencesRepository;
import org.contextmapper.generated.sendquestioncontext.repository.PreferencesAddedEventRepository;
import org.contextmapper.generated.sendquestioncontext.repository.UserPreferencesTagInfosRepository;
import org.contextmapper.generated.sendquestioncontext.repository.UserWithPreferencesIdRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.*;
import org.contextmapper.generated.sendquestioncontext.service.mapper.PreferencesAddedEventMapper;
import org.contextmapper.generated.sendquestioncontext.service.mapper.UserPreferencesMapper;
import org.contextmapper.generated.sendquestioncontext.service.mapper.UserPreferencesMapperImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

/**
 * Service Implementation for managing {@link PreferencesAddedEvent}.
 */
@Service
@Transactional
public class AddPreferenceCommandHandler extends PreferencesAddedEventService {

    private final Logger log = LoggerFactory.getLogger(AddPreferenceCommandHandler.class);

    private final TagResourceApi tagResourceApi;

    private final QueryHandlerResourceApi queryHandlerResourceApi;
    private final UserPreferencesTagInfosRepository userPreferencesTagInfosRepository;
    private final UserWithPreferencesIdRepository userWithPreferencesIdRepository;
    private final CustomUserPreferencesRepository customUserPreferencesRepository;
    private final UserPreferencesMapper userPreferencesMapper;

    public AddPreferenceCommandHandler(
        PreferencesAddedEventRepository preferencesAddedEventRepository,
        PreferencesAddedEventMapper preferencesAddedEventMapper,
        TagResourceApi tagResourceApi,
        QueryHandlerResourceApi queryHandlerResourceApi,
        UserPreferencesTagInfosRepository userPreferencesTagInfosRepository,
        UserWithPreferencesIdRepository userWithPreferencesIdRepository,
        CustomUserPreferencesRepository customUserPreferencesRepository,
        UserPreferencesMapper userPreferencesMapper
    ) {
        super(preferencesAddedEventRepository, preferencesAddedEventMapper);
        this.tagResourceApi = tagResourceApi;
        this.queryHandlerResourceApi = queryHandlerResourceApi;
        this.userPreferencesTagInfosRepository = userPreferencesTagInfosRepository;
        this.userWithPreferencesIdRepository = userWithPreferencesIdRepository;
        this.customUserPreferencesRepository = customUserPreferencesRepository;
        this.userPreferencesMapper = userPreferencesMapper;
    }

    public PreferencesAddedEventDTO handle(AddPreferencesCommandDTO addPreferencesCommandDTO) {
        log.debug("Request to save PreferencesAddedEvent : {}", addPreferencesCommandDTO);
        log.info("Handle command to add preferences");

        final var tagFromApi = Optional.ofNullable(
            tagResourceApi.getTag(addPreferencesCommandDTO.getPreferences().getTagId()).getBody()
        ).orElseThrow();

        final var userFromApi = Optional.ofNullable(queryHandlerResourceApi.handleViewUserByMail(
            SecurityContextHolder.getContext().getAuthentication().getName()
        ).getBody()).orElseThrow();

        final var userPreferencesDTO = new UserPreferences();
        final var userIdDTO = new UserWithPreferencesId();
        userIdDTO.mail(userFromApi.getMail());

        final var savedUser = userWithPreferencesIdRepository.save(userIdDTO);

        final var tagInfosDto = new UserPreferencesTagInfos();
        tagInfosDto.tagId(tagFromApi.getId());

        final var savedTagInfos = userPreferencesTagInfosRepository.save(tagInfosDto);
        userPreferencesDTO.preferences(Set.of(savedTagInfos)).user(savedUser);

        final var saved = customUserPreferencesRepository.save(userPreferencesDTO);

        final var preferencesAddedEventDTO = new PreferencesAddedEventDTO();
        preferencesAddedEventDTO.setPreferences(userPreferencesMapper.toDto(saved));

        return save(preferencesAddedEventDTO);
    }

}
