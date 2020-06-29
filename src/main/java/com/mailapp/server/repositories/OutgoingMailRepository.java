package com.mailapp.server.repositories;

import com.mailapp.server.entities.OutgoingMail;
import org.springframework.data.repository.CrudRepository;

public interface OutgoingMailRepository extends CrudRepository<OutgoingMail,Long> {
}
