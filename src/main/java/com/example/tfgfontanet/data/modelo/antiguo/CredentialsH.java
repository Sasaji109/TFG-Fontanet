package com.example.tfgfontanet.data.modelo.antiguo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "credentials", schema = "example_exam_2eva")
@NamedQueries({ @NamedQuery(name = "HQL_GET_ALL_CREDENTIALS", query = "from CredentialsH") })
public class CredentialsH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;
}
