package com.michael.kyc.checklist.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String kycStatus;
    private LocalDate kycDateInitiated;
    private LocalDate kycDateCompleted;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.kycStatus = "in-progress";
        this.kycDateInitiated = LocalDate.now();
    }

    public void completeKyc() {
        this.kycStatus = "verified";
        this.kycDateCompleted = LocalDate.now();
    }

    // Getter Methods
    public Long getId() {
        return this.id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPhone() {
        return this.phone;
    }
    public String getAddress() {
        return this.address;
    }
    public String getKycStatus() {
        return this.kycStatus;
    }
    public LocalDate getKycDateInitiated() {
        return this.kycDateInitiated;
    }
    public LocalDate getKycDateCompleted() {
        return this.kycDateCompleted;
    }

    // Setter Methods
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }
    public void setKycDateInitiated(LocalDate kycDateInitiated) {
        this.kycDateInitiated = kycDateInitiated;
    }

}
