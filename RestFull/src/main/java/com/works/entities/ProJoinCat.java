package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ProJoinCat {

    @Id
    private Integer pid;

    private String detail;
    private Integer price;
    private String title;
    private String ctitle;
    private Integer cid;


}
