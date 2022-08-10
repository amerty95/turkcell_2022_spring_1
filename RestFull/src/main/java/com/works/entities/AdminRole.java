package com.works.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class AdminRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    private String role;

    @ManyToMany(mappedBy = "adminRoles", fetch = FetchType.LAZY)
    List<Admin> admins;

}
