package org.contextmapper.generated.usermanagementcontext.web.rest;

import org.contextmapper.generated.usermanagementcontext.service.ViewUserByEmailQueryHandler;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserViewedEventDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.ViewUserByEmailCommandDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * REST controller for managing {@link org.contextmapper.generated.usermanagementcontext.domain.RegisterCommand}.
 */
@RestController
@RequestMapping("/api/handlers")
public class QueryHandlerResource {

    private final Logger log = LoggerFactory.getLogger(QueryHandlerResource.class);

    private static final String USER_ENTITY_NAME = "userManagementContextViewUserByEmailCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViewUserByEmailQueryHandler viewUserByEmailQueryHandler;

    public QueryHandlerResource(
        ViewUserByEmailQueryHandler viewUserByEmailQueryHandler
    ) {
        this.viewUserByEmailQueryHandler = viewUserByEmailQueryHandler;
    }

    @GetMapping("/view-user-query")
    public ResponseEntity<UserInfosDTO> handleViewUserByMail(@RequestParam("mail") String mail) throws URISyntaxException {
        log.debug("REST request to view user : {}", mail);
        final var userCommand = new ViewUserByEmailCommandDTO();
        userCommand.setMail(mail);
        final var result = viewUserByEmailQueryHandler.handle(userCommand);
        return ResponseEntity
            .created(new URI("/api/handlers/view-user-query" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, USER_ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
