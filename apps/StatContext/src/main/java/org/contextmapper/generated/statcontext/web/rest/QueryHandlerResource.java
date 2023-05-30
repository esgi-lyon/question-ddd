package org.contextmapper.generated.statcontext.web.rest;

import org.contextmapper.generated.statcontext.service.ViewLeaderBoardQueryHandler;
import org.contextmapper.generated.statcontext.service.ViewStatsQueryHandler;
import org.contextmapper.generated.statcontext.service.dto.ViewLeaderBoardCommandDTO;
import org.contextmapper.generated.statcontext.service.dto.ViewStatsCommandDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping("/api/handlers")
public class QueryHandlerResource {

    private final ViewLeaderBoardQueryHandler viewLeaderBoardQueryHandler;

    private final ViewStatsQueryHandler viewStatsQueryHandler;

    public QueryHandlerResource(
        ViewLeaderBoardQueryHandler viewLeaderBoardQueryHandler,
        ViewStatsQueryHandler viewStatsQueryHandler
    ) {
        this.viewLeaderBoardQueryHandler = viewLeaderBoardQueryHandler;
        this.viewStatsQueryHandler = viewStatsQueryHandler;
    }

    @PostMapping("/view-stats-query")
    public ResponseEntity<ViewStatsQueryHandler.AllEventsWrapper> handleViewStatsCommand(@RequestBody ViewStatsCommandDTO viewStatsCommand)
        throws URISyntaxException {
        final var result = viewStatsQueryHandler.handle(viewStatsCommand);
        return ResponseEntity
            .created(new URI("/api/handlers/view-stats-query/" + new UUID(1, 1)))
            .body(result);
    }

    @PostMapping("/view-leaderboard-query")
    public ResponseEntity<ViewLeaderBoardCommandDTO> handleViewLeaderBoardCommand(@RequestBody ViewLeaderBoardCommandDTO viewStatsCommand)
        throws URISyntaxException {
        final var result = viewLeaderBoardQueryHandler.handle(viewStatsCommand);
        return ResponseEntity
            .created(new URI("/api/handlers/view-leaderboard-query/" + result.getId()))
            .body(result);
    }
}
