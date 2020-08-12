package com.customer.springdemo.service;

import java.util.List;
import com.customer.springdemo.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomerList();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theCustomerId);

	public void deleteCustomer(int theCustomerId);

	public List<Customer> searchCustomer(String theSearchName);

}
