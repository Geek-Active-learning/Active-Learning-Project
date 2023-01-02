package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/** Represents a single stream */

@Entity(name = "courses_table")
@Table(appliesTo = "courses_table")
@Builder
@Getter
@Setter
@JsonIgnoreProperties(value = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uidgenerator")
    @SequenceGenerator(name = "uidgenerator", sequenceName = "uid_sequence")
    @Getter
    @Setter(AccessLevel.NONE)
    private Long id;
    private String  name;
    public Course(@NonNull String course) {
        this.name = course;
    }
}
