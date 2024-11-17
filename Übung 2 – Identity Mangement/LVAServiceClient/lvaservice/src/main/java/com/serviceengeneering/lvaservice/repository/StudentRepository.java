package com.serviceengeneering.lvaservice.repository;

import com.serviceengeneering.lvaservice.Model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
