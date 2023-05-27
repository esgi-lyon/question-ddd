package org.contextmapper.generated.usermanagementcontext.service;

import org.contextmapper.generated.usermanagementcontext.domain.UserViewedEvent;
import org.contextmapper.generated.usermanagementcontext.repository.CustomUserInfosRepository;
import org.contextmapper.generated.usermanagementcontext.repository.UserViewedEventRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserViewedEventDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.ViewUserByEmailCommandDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserInfosMapper;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserViewedEvent}.
 */
@Service
@Transactional
public class ViewUserByEmailQueryHandler extends UserViewedEventService {

    private final Logger log = LoggerFactory.getLogger(ViewUserByEmailQueryHandler.class);

    private final CustomUserInfosRepository userInfosRepository;

    private final UserInfosMapper userInfosMapper;

    public ViewUserByEmailQueryHandler(
        UserViewedEventRepository userViewedEventRepository,
        UserViewedEventMapper userViewedEventMapper,
        CustomUserInfosRepository userInfosRepository,
        UserInfosMapper userInfosMapper
    ) {
        super(userViewedEventRepository, userViewedEventMapper);
        this.userInfosRepository = userInfosRepository;
        this.userInfosMapper = userInfosMapper;
    }

    /**
     * Save a userViewedEvent.
     *
     * @param viewUserByEmailCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public UserInfosDTO handle(ViewUserByEmailCommandDTO viewUserByEmailCommandDTO) {
        log.debug("Request to view user by email : {}", viewUserByEmailCommandDTO);

        return userInfosMapper.toDto(
            userInfosRepository.findByMail(viewUserByEmailCommandDTO.getMail()).orElseThrow()
        );
    }
}
