package com.michael.kyc.checklist.repositories;

import com.michael.kyc.checklist.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

// This part tells SpringBoot to create a repo that can perform CRUD operations on Customer Entities. No need to put anything else inside.
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}