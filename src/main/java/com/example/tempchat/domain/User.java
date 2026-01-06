package com.example.tempchat.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="passwd")
    private String password;

    @Column(name="fName")
    private String firstName;

    @Column(name="lName")
    private String lastName;

    public void jpt(){
        System.out.println(getId());
    }
}
