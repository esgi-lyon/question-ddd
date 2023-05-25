package org.contextmapper.generated.answercontext.web.rest;

import org.contextmapper.generated.answercontext.domain.TagChoicesListCommand;
import org.contextmapper.generated.answercontext.service.TagChoicesListQueryHandler;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/handlers")
public class TagChoicesListQueryResource {

    private final Logger log = LoggerFactory.getLogger(TagChoicesListQueryResource.class);

    private static final String ENTITY_NAME = "answerContextTagChoicesListCommand";

    private final TagChoicesListQueryHandler tagChoicesListQueryHandler;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public TagChoicesListQueryResource(TagChoicesListQueryHandler tagChoicesListQueryHandler) {
        this.tagChoicesListQueryHandler = tagChoicesListQueryHandler;
    }

    @GetMapping("/tags-choices-list-query")
    public ResponseEntity<TagChoicesListCommand> handleTagChoicesListCommand() throws URISyntaxException {
        log.debug("REST request to handle TagChoicesListCommand");
        final var result = tagChoicesListQueryHandler.handleTagChoicesListCommand();
        return ResponseEntity
            .created(new URI("/api/handlers/tags-choices-list-query/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
