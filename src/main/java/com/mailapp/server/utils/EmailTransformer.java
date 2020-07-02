package com.mailapp.server.utils;

import com.mailapp.server.entities.IncomingMail;
import com.sun.mail.imap.IMAPMessage;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;

@Component
public class EmailTransformer extends AbstractMailMessageTransformer<IncomingMail> {
    @Override
    protected AbstractIntegrationMessageBuilder doTransform(Message message) throws Exception {
        String bodyMessage = "";

        MimeMultipart multipart = (MimeMultipart)message.getContent();

        if(multipart.getBodyPart(0).getContentType().equals("text/plain; charset=\"UTF-8\""))
           bodyMessage = multipart.getBodyPart(0).getContent().toString();

        if(multipart.getBodyPart(0).getContentType().equals("text/html; charset=UTF-8"))
           bodyMessage = org.jsoup.Jsoup.parse(multipart.getBodyPart(0).getContent().toString()).text();

        IncomingMail incomingMail = new IncomingMail();
        incomingMail.setMessage(bodyMessage);
        incomingMail.setSender(message.getFrom()[0].toString());
        incomingMail.setSubject(message.getSubject());
        incomingMail.setDateReceived(message.getReceivedDate());
        return MessageBuilder.withPayload(incomingMail);
    }
}
