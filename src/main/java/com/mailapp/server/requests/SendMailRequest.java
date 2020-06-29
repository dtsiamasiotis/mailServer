package com.mailapp.server.requests;

import com.mailapp.server.entities.OutgoingMail;
import com.mailapp.server.entities.User;
import lombok.Data;

import javax.persistence.Column;

@Data
public class SendMailRequest {
    private String username;
    private String password;
    private String recipient;
    private String message;
    private String title;

    public OutgoingMail toOutgoingMail(User sender)
    {
        OutgoingMail outgoingMail = new OutgoingMail();
        outgoingMail.setMessage(this.message);
        outgoingMail.setRecipient(this.recipient);
        outgoingMail.setTitle(this.title);
        outgoingMail.setSender(sender);
        return outgoingMail;
    }
}


