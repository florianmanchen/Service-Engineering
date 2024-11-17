package com.serviceengeneering.lvaservice.repository;

import com.serviceengeneering.lvaservice.Model.Lva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LvaRepository extends CrudRepository<Lva, Long> {
}
