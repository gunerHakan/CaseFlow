package com.law.caseflow.domain.entity;

import com.law.caseflow.domain.enums.CaseStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "cases")
public class CaseFile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String caseNumber;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CaseStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // GETTERS
    public UUID getId() { return id; }
    public String getCaseNumber() { return caseNumber; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public CaseStatus getStatus() { return status; }
    public Client getClient() { return client; }

    // SETTERS
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(CaseStatus status) { this.status = status; }
    public void setClient(Client client) { this.client = client; }
}
