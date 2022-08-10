package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @Column(length = 100)
    @NotNull(message = "name NotNull")
    @NotEmpty(message = "name NotEmpty")
    @Length(min = 3, max = 30, message = "name min = 3, max = 30")
    private String name;

    @Email(message = "Email format Fail")
    @NotEmpty(message = "Email NotEmpty")
    @NotNull(message = "Email NotNull")
    @Column(unique = true, length = 200)
    private String email;

    @Length(message = "Password min 3 max 32", min = 3, max = 32)
    @NotEmpty(message = "Password NotEmpty")
    @NotNull(message = "Password NotNull")
    @Column(length = 32)
    private String password;

}
