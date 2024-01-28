package com.hj.message;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Message {
    @Id
    private long messageId;
    private String message;
}
