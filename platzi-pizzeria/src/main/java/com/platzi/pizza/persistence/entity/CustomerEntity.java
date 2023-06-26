package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @Column(name = "id_customer", nullable = false, length = 15)
    private String idCustomer;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(length = 100)
    private String address;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
}
