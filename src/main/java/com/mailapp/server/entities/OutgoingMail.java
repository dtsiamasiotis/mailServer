package com.mailapp.server.entities;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "outgoingMails")
public class OutgoingMail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String recipient;

    @Column
    private String message;

    @Column
    private String title;

    @OneToOne
    @JoinColumn(name = "sender_id")
    private User sender;

}
