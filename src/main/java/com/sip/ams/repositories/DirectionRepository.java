package com.sip.ams.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sip.ams.entities.Direction;
import com.sip.ams.entities.Directiong;

@Repository
public interface DirectionRepository extends CrudRepository<Direction , Long> {

	List<Direction> findByDirectiong(Directiong directiong);

}
