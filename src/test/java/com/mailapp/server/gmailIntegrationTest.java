package com.mailapp.server;

import com.mailapp.server.utils.EmailTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.mail.ImapMailReceiver;
import org.springframework.integration.mail.MailReceiver;
import org.springframework.integration.mail.dsl.Mail;

public class gmailIntegrationTest {
    @Mock
    private EmailTransformer emailTransformer;
    private IntegrationFlow emailIntegrationFlow;

    @BeforeEach
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void receiveMail()
    {
        try {
           // emailIntegrationFlow(emailTransformer);
            System.out.println("got mails");
        }catch(Exception e){e.printStackTrace();}

    }
}
