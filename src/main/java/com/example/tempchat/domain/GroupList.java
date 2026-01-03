package com.example.tempchat.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_list")
public class GroupList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name="group_name")
    private String groupName;

    @Column(name="date_created")
    private LocalDate dateCreated;

    @Column(name="groupCode")
    private String groupCode;
}
