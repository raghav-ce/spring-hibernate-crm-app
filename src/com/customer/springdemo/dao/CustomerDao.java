package com.customer.springdemo.dao;

import java.util.List;

import com.customer.springdemo.entity.Customer;

public interface CustomerDao {
	
	public List<Customer> getCustomerList();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theCustomerId);

	public void deleteCustomer(int theCustomerId);

	public List<Customer> searchCustomer(String theSearchName);

}
