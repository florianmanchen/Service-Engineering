package com.example.lvaservice.Repositories;

import com.example.lvaservice.Entities.LVALeader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LVALeaderRepository extends CrudRepository<LVALeader, Long> {
}
