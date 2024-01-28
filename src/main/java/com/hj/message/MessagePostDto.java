package com.hj.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class MessagePostDto {
    private String message;

    public MessagePostDto() {
    }

    public MessagePostDto(String message) {
        this.message = message;
    }
}