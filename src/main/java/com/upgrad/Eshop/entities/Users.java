package com.upgrad.Eshop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Data
@Entity
@Table(name = "eshop_user")
public class Users {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "HIBERNATE_SEQUENCE "),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private int id;
    @Column( nullable = false)
    private Date created;
    @Column( nullable = false, unique = true)
    private String email;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column( nullable = false)
    private String password;
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
    //@JoinColumn(name = "role", nullable = false)
    /*@ManyToOne
    @JsonBackReference
    Role role;*/
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;
    @Column( nullable = false)
    private Date updated;

    public Users() {
    }
}
