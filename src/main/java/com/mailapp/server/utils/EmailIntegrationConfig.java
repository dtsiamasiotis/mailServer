package com.mailapp.server.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.mail.SearchTermStrategy;
import org.springframework.integration.mail.dsl.Mail;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;

@Configuration
public class EmailIntegrationConfig {

    @Bean
    public IntegrationFlow emailIntegrationFlow(EmailTransformer emailTransformer, EmailHandler emailHandler){
        String username = "";
        String imap3Url = "imaps://"+username+":@imap.mail.yahoo.com:993/inbox";


        return IntegrationFlows.from(Mail.imapInboundAdapter(imap3Url).simpleContent(true).shouldMarkMessagesAsRead(false).searchTermStrategy((supportedFlags,folder)->{return new FlagTerm(new Flags(Flags.Flag.SEEN),false);}),c -> c.poller(Pollers.fixedDelay(5000)))
                .transform(emailTransformer).handle(emailHandler).get();
    }
}
