package activelearning.com.BL.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Table;


import java.util.Date;
import java.util.Set;

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
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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

    private String githubUsername;

    private String email;

    private String phoneNumber;

    private String password;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_course_table",
            joinColumns = @JoinColumn(
                    name = "users-table", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "courses_table", referencedColumnName = "id"))
    private Set<Course> course;

    private Date startDate;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role_table",
            joinColumns = @JoinColumn(
                    name = "users-table", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "roles_table", referencedColumnName = "id"))
    private Role role;

}
