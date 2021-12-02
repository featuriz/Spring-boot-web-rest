/**
 * 
 */
package com.featuriz.sbm.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.featuriz.sbm.entity.Employee;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 30-Nov-2021 8:43:46 pm
 */
@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

	@Override
	public EntityModel<Employee> toModel(Employee employee) {
		return EntityModel.of(employee, //
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).one(employee.getId()))
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).all())
						.withRel("employees"));
	}

}
