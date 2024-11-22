package com.michael.kyc.checklist.controllers;

import com.michael.kyc.checklist.dto.ChecklistItemCompletedByDTO;
import com.michael.kyc.checklist.dto.ChecklistItemUpdateRequest;
import com.michael.kyc.checklist.models.ChecklistItem;
import com.michael.kyc.checklist.services.ChecklistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checklistItems")
public class ChecklistItemController {
    @Autowired
    private ChecklistItemService checklistItemService;

    @GetMapping
    public List<ChecklistItem> getAllChecklistItems() {
        return checklistItemService.getAllChecklistItems();
    }
    @GetMapping("/customer/{customerId}")
    public List<ChecklistItem> getChecklistItemsByCustomerId(
            @PathVariable Long customerId
    ) {
        return checklistItemService.getChecklistItemsByCustomerId(customerId);
    }
    @PostMapping("/customer/{customerId}")
    public ChecklistItem addChecklistItem(
            @PathVariable Long customerId,
            @RequestBody ChecklistItem checklistItem

    ) {
        return checklistItemService.addChecklistItem(customerId, checklistItem);
    }

    @PutMapping("/complete/{checklistItemId}")
    public ChecklistItem completeChecklistItem(
            @PathVariable Long checklistItemId,
//            @RequestBody String completedBy
//            This makes the completeBy look like "completeBy": "Michael" following the json request
            @RequestBody ChecklistItemCompletedByDTO request
    ) {
        return checklistItemService.completeChecklistItem(checklistItemId, request.getCompletedBy());
    }

    @PutMapping("/incomplete/{checklistItemId}")
    public ChecklistItem markIncompleteChecklistItem(
            @PathVariable Long checklistItemId
    ) {
        return checklistItemService.markIncompleteChecklistItem(checklistItemId);
    }

    @PutMapping("/update/{checklistItemId}")
    public ChecklistItem editChecklistItem(
            @PathVariable Long checklistItemId,
            @RequestBody ChecklistItemUpdateRequest updateBody
    ) {
        return checklistItemService.updateChecklistItem(checklistItemId, updateBody);
    }

    @DeleteMapping("/delete/{checklistItemId}")
    public ResponseEntity<String> deleteChecklistItem(
            @PathVariable Long checklistItemId
    ) {
        checklistItemService.deleteChecklistItem(checklistItemId);
        return ResponseEntity.ok("Checklist item deleted");
    }
}