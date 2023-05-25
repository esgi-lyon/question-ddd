package org.contextmapper.generated.statcontext.web.rest;

import org.contextmapper.generated.statcontext.domain.ViewStatsCommand;
import org.contextmapper.generated.statcontext.service.ViewStatsCommandHandler;
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

    private final ViewStatsCommandHandler viewStatsCommandHandler;

    public ViewStatsQueryHandlerResource(ViewStatsCommandHandler viewStatsCommandHandler) {
        this.viewStatsCommandHandler = viewStatsCommandHandler;
    }

    @PostMapping("/view-stats-query")
    public ResponseEntity<ViewStatsCommand> handleViewStatsCommand(@RequestBody ViewStatsCommand viewStatsCommand)
        throws URISyntaxException {
        ViewStatsCommand result = viewStatsCommandHandler.handleViewStatsQuery(viewStatsCommand);
        return ResponseEntity
            .created(new URI("/api/handlers/view-stats-query/" + result.getId()))
            .body(result);
    }
}
