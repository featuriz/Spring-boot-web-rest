/**
 * 
 */
package com.featuriz.sbm.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.featuriz.sbm.entity.Order;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 01-Dec-2021 11:51:58 am
 */
@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

	@Override
	public EntityModel<Order> toModel(Order order) {

		// Unconditional links to single-item resource and aggregate root

		EntityModel<Order> orderModel = EntityModel.of(order,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).one(order.getId()))
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).all()).withRel("orders"));

		// Conditional links based on state of the order

		if (order.getStatus() == com.featuriz.sbm.entity.Status.IN_PROGRESS) {
			orderModel.add(WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
			orderModel.add(
					WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).complete(order.getId()))
							.withRel("complete"));
		}

		return orderModel;
	}
}
