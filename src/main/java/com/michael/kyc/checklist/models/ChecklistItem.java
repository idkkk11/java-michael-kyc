package com.michael.kyc.checklist.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ChecklistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private String title;
    private String description;
    private String status;
    private LocalDate completionDate;
    private String completedBy;

    public ChecklistItem() {
        this.status = "in-progress";
    }

    public ChecklistItem(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = "in-progress";
    }

    public void completeItem(String completedBy) {
        this.status = "completed";
        this.completionDate = LocalDate.now();
        this.completedBy = completedBy;
    }

    public void markIncomplete() {
        this.status = "in-progress";
        this.completionDate = null;
        this.completedBy = null;
    }

    // Getter Methods
    public Long getId() {
        return id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public  String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getStatus() {
        return status;
    }
    public LocalDate getCompletionDate() {
        return completionDate;
    }
    public String getCompletedBy() {
        return completedBy;
    }

    // SetterMethods
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    // In case want to force mark as complete
    public void setCompleted(String completedBy) {
        this.status = "completed-forced";
        this.completedBy = completedBy;
        this.completionDate = LocalDate.now();
    }

}
