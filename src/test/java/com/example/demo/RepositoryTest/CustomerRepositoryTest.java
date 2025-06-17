package com.example.demo.RepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Test 
	public void saveAndTestbyID() {
		Customer newcustomer = new Customer(null, "balaji", "@123", "abc123", null);
		customerRepository.save(newcustomer);
		
		Optional<Customer>foundcustomer = customerRepository.findById(newcustomer.getCustomerID());
		assertThat(foundcustomer.get().getCustomerID()).isEqualByComparingTo(newcustomer.getCustomerID());
		
	}
	@Test
	public void saveAndTestbyname() {
		Customer newcustomer = new Customer(null, "balaji", "@123", "abc123", null);
		customerRepository.save(newcustomer);
		
		Optional<Customer>foundcustomer = customerRepository.findByName(newcustomer.getName());
		assertThat(foundcustomer.get().getName()).isEqualTo(newcustomer.getName());
		
		
	}
	@Test
	public void saveAndListALL() {
		Customer newcustomer = new Customer(null, "balaji", "@123", "abc123", null);
		customerRepository.save(newcustomer);
		Customer newcustomer1 = new Customer(null, "manij", "@123", "abc123", null);
		customerRepository.save(newcustomer1);
		Customer newcustomer2 = new Customer(null, "ksdi", "@123", "abc123", null);
		customerRepository.save(newcustomer2);
		
		List<Customer>customers = customerRepository.findAll();
		int count = (int) customerRepository.count();
		assertThat(customers.size()).isEqualTo(count);
		
	}

	@Test
	public void saveAndDelete() {
		Customer newcustomer = new Customer(null, "balaji", "@123", "abc123", null);
		customerRepository.save(newcustomer);
		int count = (int) customerRepository.count();
		customerRepository.delete(newcustomer);
		assertThat(customerRepository.count()).isEqualTo(0);
		
		
	}
}
