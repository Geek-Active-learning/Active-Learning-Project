package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/** Represents a single stream */

@Entity(name = "roles_table")
@Table(appliesTo = "roles_table")
@Builder
@Getter
@Setter
@JsonIgnoreProperties(value = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Data
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uidgenerator")
    @SequenceGenerator(name = "uidgenerator", sequenceName = "uid_sequence")
    @Getter
    @Setter(AccessLevel.NONE)
    private Long id;
    private String  name;
}
