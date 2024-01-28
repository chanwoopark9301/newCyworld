package com.hj.message;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/v1/messages")
@RestController
public class MessageController {
    private final MessageService messageService;
    private final MessageMapper mapper;

    public MessageController(MessageService messageService,
                             MessageMapper mapper) {
        this.messageService = messageService;
        this.mapper = mapper;
    }

    @PostMapping("/post")
    public ResponseEntity postMessage(@Valid @RequestBody MessagePostDto messagePostDto) {
        Message message = messageService.createMessage(mapper.messageDtoToMessage(messagePostDto));
        URI newResourceLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{messageId}")
                .buildAndExpand(message.getMessageId())
                .toUri();
        return ResponseEntity.created(newResourceLocation).build();
    }
}
