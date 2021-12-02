/**
 * 
 */
package com.featuriz.sbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.featuriz.sbm.entity.Employee;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 30-Nov-2021 12:40:02 pm
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
