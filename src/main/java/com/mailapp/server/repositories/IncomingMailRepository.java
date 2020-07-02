package com.mailapp.server.repositories;

import com.mailapp.server.entities.IncomingMail;
import org.springframework.data.repository.CrudRepository;

public interface IncomingMailRepository extends CrudRepository<IncomingMail,Long> {
}
