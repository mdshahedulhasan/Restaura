package com.restaura.customerservice.service;

import com.restaura.customerservice.entities.Customer;
import com.restaura.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findById(email);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> updateCustomer(String email, Customer customerDetails) {
        return customerRepository.findById(email).map(customer -> {
            customer.setName(customerDetails.getName());
            customer.setAddresses(customerDetails.getAddresses());
            customer.setAge(customerDetails.getAge());
            customer.setOccupation(customerDetails.getOccupation());
            customer.setSex(customerDetails.getSex());
            customer.setContactNo(customerDetails.getContactNo());
            return customerRepository.save(customer);
        });
    }

    public boolean deleteCustomer(String email) {
        return customerRepository.findById(email).map(customer -> {
            customerRepository.delete(customer);
            return true;
        }).orElse(false);
    }
}

