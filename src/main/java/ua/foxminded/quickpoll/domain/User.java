package ua.foxminded.quickpoll.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    @NotEmpty
    private String username;

    @Column(name = "password")
    @NotEmpty
    @JsonIgnore
    private String password;

    @Column(name="first_name")
    @NotEmpty
    private String firstName;

    @Column(name="lasn_name")
    @NotEmpty
    private String lastName;

    @Column(name="admin", columnDefinition="char(3)")
    @Type(type="yes_no")
    @NotEmpty
    private boolean admin;
}
