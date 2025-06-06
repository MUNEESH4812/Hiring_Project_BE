package com.example.onboarding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String position;

    @Pattern(
            regexp = "APPLIED|INTERVIEWED|OFFERED|ONBOARDED",
            message = "Status must be one of: APPLIED, INTERVIEWED, OFFERED,ONBOARDED"
        )
    private String status;

    @Temporal(TemporalType.DATE)
    private Date hiredDate;
    
    public Candidate() {
    	
    }
    
    public Candidate(Long id) {
        this.id = id;
    }


    // Other fields removed for brevity

    // Getters and Setters for the basic fields
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getHiredDate() { return hiredDate; }
    public void setHiredDate(Date hiredDate) { this.hiredDate = hiredDate; }
}
