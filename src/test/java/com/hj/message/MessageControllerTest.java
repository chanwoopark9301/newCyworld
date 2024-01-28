package com.hj.message;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {
    @MockBean
    private MessageService messageService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @Autowired
    private MessageMapper mapper;


    @Test
    void postMessageTest() throws Exception {
        //given

        MessagePostDto messagePostDto = new MessagePostDto("테스트 중입니다!");
        Message message = mapper.messageDtoToMessage(messagePostDto);
        message.setMessageId(1L);

        given(messageService.createMessage(Mockito.any(Message.class)))
                .willReturn(message);
        String content = gson.toJson(messagePostDto);


        //when

        ResultActions actions = mockMvc.perform(post("/v1/messages/post")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));

        // then

            actions.andExpect(status().isCreated())
                    .andExpect(header().string("Location",equalTo("http://localhost/v1/messages/post/1")));
    }
}