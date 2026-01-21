package com.law.caseflow.domain.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<CaseFile> cases;

    @OneToOne(cascade = CascadeType.ALL) // Client silinirse User da silinsin (opsiyonel)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // GETTERS
    public UUID getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public List<CaseFile> getCases() { return cases; }
    public User getUser() { return user; }

    // SETTERS
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setCases(List<CaseFile> cases) { this.cases = cases; }
    public void setUser(User user) { this.user = user; }
}
