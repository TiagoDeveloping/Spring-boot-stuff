package com.alien.SessionAuth.entities.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @Column(nullable = false, unique = true, length = 45)
    private String email;
     
    @Column(nullable = false, length = 64)
    private String password;
     
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
     
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
     
    // getters and setters are not shown   
}