package com.michael.kyc.checklist.repositories;

import com.michael.kyc.checklist.models.ChecklistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This part tells SpringBoot to create a repo that can perform CRUD operations on Customer Entities. No need to put anything else inside.
public interface ChecklistItemRepository extends JpaRepository<ChecklistItem, Long> {
    List<ChecklistItem> findByCustomerId(Long customerId);
}