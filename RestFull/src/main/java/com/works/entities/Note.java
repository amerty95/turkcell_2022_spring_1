package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nid;

    @Column(unique = true, length = 100)
    private String title;


    @Column(length = 500)
    private String detail;

}
