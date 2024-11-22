package com.michael.kyc.checklist.services;

import com.michael.kyc.checklist.models.ChecklistItem;
import com.michael.kyc.checklist.models.Customer;
import com.michael.kyc.checklist.repositories.ChecklistItemRepository;
import com.michael.kyc.checklist.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

// The @Service thing tells SpringBoot this is a service class. Automatically registers this class as a bean.
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ChecklistItemRepository checklistItemRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        customer.setKycStatus("in-progress");
        customer.setKycDateInitiated(LocalDate.now());
        return customerRepository.save(customer);
    }

    public String completeKyc(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found."));
        List<ChecklistItem> checklistItems = checklistItemRepository.findByCustomerId(customerId);
        boolean allItemsComplete = checklistItems.stream().allMatch(item -> "completed".equals(item.getStatus())|| "completed-forced".equals(item.getStatus()));

        if (!allItemsComplete) {
            return "All Checklist items must be completed";
        }

        customer.completeKyc();
        customerRepository.save(customer);
        return "KYC completed successfully";
    }
}
