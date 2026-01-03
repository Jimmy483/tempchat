package com.example.tempchat.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name="message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "group_list", referencedColumnName = "id")
    private GroupList groupList;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;
}
