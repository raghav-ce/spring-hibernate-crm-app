package com.customer.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer.springdemo.dao.CustomerDao;
import com.customer.springdemo.entity.Customer;
import com.customer.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listCustomers(Model TheModel) {
		
		//get the customer from dao
		List<Customer> customerList = customerService.getCustomerList();
		
		//added to the model
		TheModel.addAttribute("customers", customerList);
		
		//return the view
		return "list-customers";
	}
	
	@RequestMapping("/showFormAdd")
	public String showFormAdd(Model theModel) {
		
		//Model of Customer
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		//return the view
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		//Save the customer
		customerService.saveCustomer(theCustomer);
		
		//return the view
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theCustomerId,
			Model theModel) {
		//get the customer by CustomerId
		Customer theCustomer = customerService.getCustomer(theCustomerId);
		
		//pre-populate the form with data
		theModel.addAttribute("customer", theCustomer);
		
		//return the view
		return "customer-form";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int theCustomerId,
			Model theModel) {
		//delete the customer by CustomerId
		customerService.deleteCustomer(theCustomerId);
		
		
		//return the view
		return "redirect:/customer/list";
	}
	
	@PostMapping("/searchCustomer")
	public String searchCustomer(@RequestParam("theSearchName") String theSearchName,
            Model theModel) {
		//delete the customer by CustomerId
		List<Customer> customerList = customerService.searchCustomer(theSearchName);
		
		//Add filtered details to the model
		theModel.addAttribute("customers", customerList);
		
		//return the view
		return "list-customers";
	}
}
