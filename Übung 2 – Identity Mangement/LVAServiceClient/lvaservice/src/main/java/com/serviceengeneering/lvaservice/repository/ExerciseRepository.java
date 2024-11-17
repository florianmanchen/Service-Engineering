package com.serviceengeneering.lvaservice.repository;

import com.serviceengeneering.lvaservice.Model.Exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
}
