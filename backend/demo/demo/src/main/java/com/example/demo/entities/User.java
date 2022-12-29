package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Table;


import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/** Represents a single User */
@Entity(name = "users_table")
@Table(appliesTo = "users_table")
@Builder
@Getter
@Setter
@JsonIgnoreProperties(value = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uidgenerator")
    @SequenceGenerator(name = "uidgenerator", sequenceName = "uid_sequence")
    @Getter
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    private String surname;

    private Date dob;

    private  String githubUsername;

    private String email;

    private String phoneNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_streams_table",
            joinColumns = @JoinColumn(
                    name = "users-table", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "streams_table", referencedColumnName = "id"))
    private Stream stream;

    private Date startDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles_table",
            joinColumns = @JoinColumn(
                    name = "users-table", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "roles_table", referencedColumnName = "id"))
    private Roles roles;

}
