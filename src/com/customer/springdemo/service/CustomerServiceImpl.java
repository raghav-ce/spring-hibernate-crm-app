package com.customer.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.springdemo.dao.CustomerDao;
import com.customer.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	@Transactional
	public List<Customer> getCustomerList() {
		return customerDao.getCustomerList();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDao.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int theCustomerId) {
		return customerDao.getCustomer(theCustomerId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theCustomerId) {
		customerDao.deleteCustomer(theCustomerId);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomer(String theSearchName) {
		return customerDao.searchCustomer(theSearchName);
	}

}
