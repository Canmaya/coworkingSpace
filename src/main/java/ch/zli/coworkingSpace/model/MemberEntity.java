package ch.zli.coworkingSpace.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "MEMBER")
public class MemberEntity implements Serializable {
    public MemberEntity(String firstname, String lastname, String email, String password, String role, String description, String job, String gender) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.description = description;
        this.job = job;
        this.gender = gender;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "gender", nullable = false)
    String gender;

    @Column(name = "job", nullable = false)
    private String job;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "role", nullable = false)
    private String role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;
}
