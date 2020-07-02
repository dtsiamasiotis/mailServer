package com.mailapp.server.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "incomingMails")
public class IncomingMail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition = "text")
    private String message;

    @Column
    private String sender;

    @Column
    private String subject;

    @Column
    private Date dateReceived;
}
