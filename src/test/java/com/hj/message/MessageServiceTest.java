package com.hj.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class MessageServiceTest {
    @Mock
    MessageRepository messageRepository;
    @InjectMocks
    private MessageService messageService;

    @Test
    public void createMessageTest() {
        // Given
        Message message = new Message();
        message.setMessage("Test message");
        // Mock repository behavior
        given(messageRepository.save(any(Message.class))).willReturn(message);

        // When
        Message result = messageService.createMessage(message);

        // Then
        // Verify that the repository's save method was called with the correct argument
        verify(messageRepository, times(1)).save(eq(message));

        // Verify that the returned message is the same as the one returned by the repository
        assertEquals(message, result);
    }
}