package com.example.lvaservice.Repositories;

import com.example.lvaservice.Entities.LVA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LVARepository extends CrudRepository<LVA, Long> {
}
