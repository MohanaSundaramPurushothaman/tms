package com.cg.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.tms.entities.Customer;
import com.cg.tms.entities.Route;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
	List<Customer>findbyrouteId(Route route);
	List<Customer>findbypack(Package pack);
}
