package org.contextmapper.generated.statcontext.service;

import org.contextmapper.generated.statcontext.repository.ViewLeaderBoardCommandRepository;
import org.contextmapper.generated.statcontext.service.dto.ViewLeaderBoardCommandDTO;
import org.contextmapper.generated.statcontext.service.mapper.*;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

@Primary
@Service
@Transactional
public class ViewLeaderBoardQueryHandler extends ViewLeaderBoardCommandService {
    private final Logger log = LoggerFactory.getLogger(ViewLeaderBoardQueryHandler.class);

    public ViewLeaderBoardQueryHandler(
        ViewLeaderBoardCommandRepository viewLeaderboardCommandRepository,
        ViewLeaderBoardCommandMapper viewLeaderboardCommandMapper
    ) {
        super(viewLeaderboardCommandRepository, viewLeaderboardCommandMapper);
    }

    public ViewLeaderBoardCommandDTO handle(ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO) {
        // final var userDTO = viewLeaderBoardCommandDTO.getUser();
        // final var questionDTO = viewStatsCommand.getQuestion();
        // final var tagDTO = viewStatsCommand.getTag();
        /*
        log.info("Handle command to view leaderboard");

        if (userDTO != null) {
            final var userStatsViewedEventDTO = new UserStatsViewedEventDTO();
            userStatsViewedEventDTO.setUser(userDTO);
            userStatsViewedEventService.save(userStatsViewedEventDTO);
            viewLeaderBoardCommandDTO.setUser(userDTO);
        }

        return save(viewLeaderBoardCommandDTO);*/
        return null;
    }
}
