package com.example.tfgfontanet.data.modelo.antiguo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "customers", schema = "example_exam_2eva")
@NamedQueries({ @NamedQuery(name = "HQL_GET_ALL_CUSTOMERS", query = "from CustomersH") })
public class CustomersH {

    @Id
    @Column(name = "id")
    private Integer customersId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "date_of_birth")
    private LocalDate dateBirth;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id", referencedColumnName = "customer_id")
    private CredentialsH credentialsH;
}

