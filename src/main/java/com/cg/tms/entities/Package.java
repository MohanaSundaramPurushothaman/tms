package com.cg.tms.entities;

import java.util.List;

public class Package {

	private int packageId;
	private String packageName;
	private String packageDescription;
	private String packageType;
	private double packageCost;
	private PaymentDetails payment;
	private TicketDetails ticket;
	private Hotel hotel;
	
	private List<Customer> customerlist;

	public List<Customer> getCustomerlist() {
		return customerlist;
	}

	public void setCustomerlist(List<Customer> customerlist) {
		this.customerlist = customerlist;
	}

}
