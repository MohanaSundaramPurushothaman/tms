package com.cg.tms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tms.entities.Customer;
import com.cg.tms.entities.Route;
import com.cg.tms.exceptions.CustomerNotFoundException;
import com.cg.tms.exceptions.InvalidCustomerAddressException;
import com.cg.tms.exceptions.InvalidCustomerIdException;
import com.cg.tms.exceptions.InvalidCustomerNameException;
import com.cg.tms.exceptions.PackageNotFoundException;
import com.cg.tms.exceptions.RouteNotFoundException;
import com.cg.tms.repository.ICustomerRepository;
import com.cg.tms.repository.IPackageRepository;
import com.cg.tms.repository.IRouteRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;
	
	@Autowired
	private IRouteRepository routeRepository;
	
	@Autowired
	private IPackageRepository packageRepository;

	@Override
	public Customer addCustomer(Customer customer) {
		validateCustomerId(customer.getCustomerId());
		validateCustomerName(customer.getCustomerName());
		validateCustomerAddress(customer.getAddress());
		customerRepository.save(customer);

		return customer;

	}

	private void validateCustomerAddress(String address) {
		if (address == null || address.isEmpty() || address.trim().isEmpty()) {

			throw new InvalidCustomerAddressException("packageName can't be null or empty");
		}

	}

	private void validateCustomerName(String customerName) {
		if (customerName == null || customerName.isEmpty() || customerName.trim().isEmpty()) {

			throw new InvalidCustomerNameException("packageName can't be null or empty");
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		validateCustomerId(customer.getCustomerId());
		Optional<Customer> optional = customerRepository.findById(customer.getCustomerId());
		if (!optional.isPresent()) {
			throw new CustomerNotFoundException("This Customer is not found in Database");
		}
		Customer fetched = optional.get();
		fetched.setCustomerName(customer.getCustomerName());
		fetched.setCustomerPassword(customer.getCustomerPassword());
		fetched.setAddress(customer.getAddress());
		fetched = customerRepository.save(customer);
		return fetched;
	}

	public void validateCustomerId(int customerId) {
		if (customerId < 0) {
			throw new InvalidCustomerIdException("This Id does not Exist");
		}

	}

	@Override
	public Customer deleteCustomer(Customer customer) throws CustomerNotFoundException {
		return null;
	}

	@Override
	public Customer viewCustomer(int custid) throws CustomerNotFoundException {
		Optional<Customer> optional = customerRepository.findById(custid);
		if (!optional.isPresent()) {
			throw new CustomerNotFoundException("Booking not found");
		}
		return optional.get();
	}

	@Override
	public List<Customer> viewAllCustomers(int packageId) throws PackageNotFoundException {
		Optional<Package>optionalpack=packageRepository.findById(packageId);
		if (!optionalpack.isPresent()) {
			throw new PackageNotFoundException("The package does not exist for the mentioned packageId= "+packageId);
		}
		Package pack=optionalpack.get();
		List<Customer>customer=customerRepository.findbypack(pack);
		return customer;
	}

	@Override
	public List<Customer> viewCustomerList(String routeId) throws RouteNotFoundException {
		Optional<Route>optionalroute =routeRepository.findById(routeId);
		if(!optionalroute.isPresent()) {
			throw new RouteNotFoundException("The route does not exist for the mentioned Route Id ="+routeId); 
		}
		Route route=optionalroute.get();
		List<Customer>customer =customerRepository.findbyrouteId(route);
		return customer;
	}

	@Override
	public List<Customer> viewAll() {
		List<Customer> viewAll = customerRepository.findAll();
		return viewAll;
	}

}
