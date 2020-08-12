package com.customer.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.springdemo.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomerList() {
		//Create a hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//Create a Query
		Query<Customer> customerQuery = session.createQuery("from Customer order by lastName", Customer.class);
		
		//get the results from query
		List<Customer> customerList = customerQuery.getResultList();
		
		//return the results
		return customerList;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		//Create a hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//save or update the customer
		session.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theCustomerId) {
		//Create a hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// get the customer by customerID
		return session.get(Customer.class, theCustomerId);
	}

	@Override
	public void deleteCustomer(int theCustomerId) {
		//Create a hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// Delete the customer by customerID
		Query query = session.createQuery("delete from Customer where id=:customerId");
		
		query.setParameter("customerId", theCustomerId);
		
		query.executeUpdate();
		
	}

	@Override
	public List<Customer> searchCustomer(String theSearchName) {
		// get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
	}

}
