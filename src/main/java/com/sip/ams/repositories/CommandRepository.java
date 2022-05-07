package com.sip.ams.repositories;

import com.sip.ams.entities.Command;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends CrudRepository <Command, Long>{
}
