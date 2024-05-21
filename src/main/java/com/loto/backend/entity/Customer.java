package com.loto.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customer", schema = "public")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "userid")
    private int userid;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonManagedReference(value = "reference-user-customer")
    private User user;
}
