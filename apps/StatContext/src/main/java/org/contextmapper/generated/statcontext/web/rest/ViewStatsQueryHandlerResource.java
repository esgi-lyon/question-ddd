package org.contextmapper.generated.statcontext.web.rest;

import org.contextmapper.generated.statcontext.service.ViewLeaderBoardQueryHandler;
import org.contextmapper.generated.statcontext.service.dto.ViewStatsCommandDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/handlers")
public class ViewStatsQueryHandlerResource {

    private final ViewLeaderBoardQueryHandler viewLeaderBoardQueryHandler;

    public ViewStatsQueryHandlerResource(ViewLeaderBoardQueryHandler viewLeaderBoardQueryHandler) {
        this.viewLeaderBoardQueryHandler = viewLeaderBoardQueryHandler;
    }

    @PostMapping("/view-stats-query")
    public ResponseEntity<ViewStatsCommandDTO> handleViewStatsCommand(@RequestBody ViewStatsCommandDTO viewStatsCommand)
        throws URISyntaxException {
        final var result = viewLeaderBoardQueryHandler.handleViewStatsQuery(viewStatsCommand);
        return ResponseEntity
            .created(new URI("/api/handlers/view-stats-query/" + result.getId()))
            .body(result);
    }
}
