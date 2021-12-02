/**
 * 
 */
package com.featuriz.sbm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 30-Nov-2021 12:07:11 pm
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "firstname")
	@Length(min = 5, message = "*Your Firstname must have at least 5 characters")
	@NotEmpty(message = "*Please provide a Firstname")
	private String firstName;
	@Column(name = "lastname")
	@Length(min = 5, message = "*Your Lastname must have at least 5 characters")
	@NotEmpty(message = "*Please provide a Lastname")
	private String lastName;
	@Column(name = "role")
	@Length(min = 3, message = "*Your role must have at least 3 characters")
	@NotEmpty(message = "*Please provide a role")
	private String role;

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public void setName(String name) {
		String[] parts = name.split(" ");
		this.firstName = parts[0];
		this.lastName = parts[1];
	}

}
