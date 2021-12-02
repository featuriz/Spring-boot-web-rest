/**
 * 
 */
package com.featuriz.sbm.exception;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 01-Dec-2021 12:09:10 pm
 */
public class OrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(Long id) {
		super("Order " + id + " not found!");
	}
}
