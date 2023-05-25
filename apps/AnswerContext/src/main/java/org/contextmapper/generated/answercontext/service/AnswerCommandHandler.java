package org.contextmapper.generated.answercontext.service;

import org.contextmapper.generated.answercontext.domain.TagChoicesListCommand;
import org.contextmapper.generated.answercontext.repository.TagChoicesListCommandRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListedEventDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

@Primary
@Service
@Transactional
public class AnswerCommandHandler extends TagChoicesListCommandService {
    private final Logger log = LoggerFactory.getLogger(AnswerCommandHandler.class);

    private final AnswerService answerService;

    private final TagChoicesListedEventService tagChoicesListedEventService;

    private final AnswerMapper answerMapper;

    public AnswerCommandHandler(
        TagChoicesListCommandRepository tagChoicesListCommandRepository,
        AnswerService answerService,
        TagChoicesListedEventService tagChoicesListedEventService,
        AnswerMapper answerMapper
    ) {
        super(tagChoicesListCommandRepository);
        this.answerService = answerService;
        this.tagChoicesListedEventService = tagChoicesListedEventService;
        this.answerMapper = answerMapper;
    }

    public TagChoicesListCommand handleTagChoicesListCommand(AnswerDTO answerDTO) {
        log.info("Handle command to list tag choices");
        TagChoicesListCommand tagChoicesListCommand = new TagChoicesListCommand();

        final var saved = answerService.save(answerDTO);

        final var tagChoicesListedEventDTO = new TagChoicesListedEventDTO();
        answerDTO.setId(saved.getId());
        tagChoicesListedEventService.save(tagChoicesListedEventDTO);

        return save(tagChoicesListCommand);
    }
}
