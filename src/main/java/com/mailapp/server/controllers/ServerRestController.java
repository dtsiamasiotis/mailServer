package com.mailapp.server.controllers;

import com.mailapp.server.entities.OutgoingMail;
import com.mailapp.server.entities.User;
import com.mailapp.server.repositories.OutgoingMailRepository;
import com.mailapp.server.repositories.UserRepository;
import com.mailapp.server.requests.SendMailRequest;
import com.mailapp.server.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/actions",produces = "application/json")
public class ServerRestController {

    private UserRepository userRepository;
    private OutgoingMailRepository outgoingMailRepository;
    @Autowired private PasswordEncoder encoder;
    @Autowired private EmailService emailService;

    public ServerRestController(UserRepository userRepository, OutgoingMailRepository outgoingMailRepository)
    {
        this.userRepository = userRepository;
        this.outgoingMailRepository = outgoingMailRepository;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder();
    }

    @PostMapping(value = "/registrateUser",consumes = "application/json")
    public String registrateUser(@RequestBody User user)
    {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if(userFromDB != null)
            return "User exists";

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "OK";
    }

    @PostMapping(value = "/validateLogin",consumes = "application/json")
    public String validateLogin(@RequestBody User user)
    {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if(userFromDB == null)
            return "user doesn't exist";

        if(encoder.matches(user.getPassword(),userFromDB.getPassword()))
            return "OK";

        return "Wrong password";
    }

    @PostMapping(value = "/sendMail", consumes = "application/json")
    public String sendMail(@RequestBody SendMailRequest sendMailRequest)
    {
        //TODO VALIDATE USER

        User userFromDB = userRepository.findByUsername(sendMailRequest.getUsername());
        OutgoingMail outgoingMail = sendMailRequest.toOutgoingMail(userFromDB);
        emailService.sendSimpleMessage(outgoingMail.getRecipient(),outgoingMail.getTitle(),outgoingMail.getMessage());
        outgoingMailRepository.save(outgoingMail);
        return "OK";
    }

}
