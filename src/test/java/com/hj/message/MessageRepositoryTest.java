package com.hj.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MessageRepositoryTest {
    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void saveMessageTest(){
        //given

        Message message = new Message();
        message.setMessage("테스트용 입니다!");

        //when

        Message savedMessage = messageRepository.save(message);

        //then

        assertNotNull(savedMessage);
        assertTrue(message.getMessage().equals(savedMessage.getMessage()));
    }

}