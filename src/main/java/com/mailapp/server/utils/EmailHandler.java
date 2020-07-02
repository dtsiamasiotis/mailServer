package com.mailapp.server.utils;

import com.mailapp.server.entities.IncomingMail;
import com.mailapp.server.repositories.IncomingMailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class EmailHandler implements GenericHandler<IncomingMail> {

    @Autowired
    private IncomingMailRepository incomingMailRepository;
    @Override
    public Object handle(IncomingMail incomingMail, MessageHeaders messageHeaders) {
        incomingMailRepository.save(incomingMail);
        return null;

    }

}
