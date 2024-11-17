package com.serviceengeneering.lvaservice.repository;

import com.serviceengeneering.lvaservice.Model.LvaLeader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LvaLeaderRepository extends CrudRepository<LvaLeader, Long> {
}
