/**
 * 
 */
package com.featuriz.sbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.featuriz.sbm.entity.Order;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 01-Dec-2021 11:49:52 am
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}
