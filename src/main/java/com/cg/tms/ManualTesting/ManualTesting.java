package com.cg.tms.ManualTesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.tms.entities.Customer;
import com.cg.tms.service.ICustomerService;

public class ManualTesting {
	@Component
	public class RouteUI {

		@Autowired
		private ICustomerService service;

		public void start() {
			Customer customer1 = new Customer();
			Customer customer2 = new Customer();
			customer1.setCustomerId(1);
			customer1.setCustomerName("Mohan");
			customer1.setAddress("kancheepuram");
			customer2.setCustomerId(2);
			customer2.setCustomerName("Navneeth");
			customer2.setAddress("chennai");
			customer1 = service.addCustomer(customer1);
			customer2 = service.addCustomer(customer2);
			display(customer1);
			display(customer2);
			customer1.setCustomerId(1);
			customer1.setCustomerName("MSP");
			customer1.setAddress("Chennai");
			customer1 = service.updateCustomer(customer1);
			display(customer1);
			List<Customer> list = new ArrayList<>();
			list.add(customer1);
			list.add(customer2);
			list = service.viewAll();
			System.out.println(Arrays.toString(list.toArray()));
		}

		public void display(Customer customer) {
			System.out.println("Customer_Id = " + customer.getCustomerId());
			System.out.println("Customer_Name = " + customer.getCustomerName());
			System.out.println("Customer_Address= " + customer.getAddress());

		}

	}

}
