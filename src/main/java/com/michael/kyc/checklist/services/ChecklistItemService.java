package com.michael.kyc.checklist.services;

import com.michael.kyc.checklist.dto.ChecklistItemUpdateRequest;
import com.michael.kyc.checklist.models.ChecklistItem;
import com.michael.kyc.checklist.models.Customer;
import com.michael.kyc.checklist.repositories.ChecklistItemRepository;
import com.michael.kyc.checklist.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// The @Service thing tells SpringBoot this is a service class. Automatically registers this class as a bean.
@Service
public class ChecklistItemService {
    @Autowired
    private ChecklistItemRepository checklistItemRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public List<ChecklistItem> getAllChecklistItems() {
        return checklistItemRepository.findAll();
    }

    public ChecklistItem getChecklistItem(Long id) {
        return checklistItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Checklist item not found."));
    }

    public List<ChecklistItem> getChecklistItemsByCustomerId(Long customerId) {
        return checklistItemRepository.findByCustomerId(customerId);
    }

    public ChecklistItem addChecklistItem(Long customerId, ChecklistItem checklistItem) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found."));
        checklistItem.setCustomer(customer);
        return checklistItemRepository.save(checklistItem);
    }

    public ChecklistItem completeChecklistItem(Long checklistItemId, String completedBy) {
        ChecklistItem checklistItem = checklistItemRepository.findById(checklistItemId).orElseThrow(() -> new RuntimeException("Checklist Item not found."));
        checklistItem.completeItem(completedBy);
        return checklistItemRepository.save(checklistItem);
    }

    public ChecklistItem markIncompleteChecklistItem(Long checklistItemId) {
        ChecklistItem checklistItem = checklistItemRepository.findById(checklistItemId).orElseThrow(() -> new RuntimeException("Checklist Item not found."));
        checklistItem.markIncomplete();
        return checklistItemRepository.save(checklistItem);
    }

    public ChecklistItem updateChecklistItem(Long checklistItemId, ChecklistItemUpdateRequest updateBody) {
        ChecklistItem checklistItem = checklistItemRepository.findById(checklistItemId).orElseThrow(() -> new RuntimeException("Checklist Item not found."));

        if (updateBody.getTitle() != null) {
            checklistItem.setTitle(updateBody.getTitle());
        }
        if (updateBody.getDescription() != null) {
            checklistItem.setDescription(updateBody.getDescription());
        }

        return checklistItemRepository.save(checklistItem);
    }

    public void deleteChecklistItem(Long checklistItemId) {
        ChecklistItem checklistItem = checklistItemRepository.findById(checklistItemId).orElseThrow(() -> new RuntimeException("Checklist Item not found."));
        checklistItemRepository.delete(checklistItem);
    }
}
