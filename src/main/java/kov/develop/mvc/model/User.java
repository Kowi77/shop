package kov.develop.mvc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@ToString(exclude = "id")
@EqualsAndHashCode(exclude = "id")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty()
    private Integer id;

    @Column(name = "username")
    @NotBlank(message = "Username must be not empty!")
    @Size(max = 60, message = "Username must be 60 charts max!")
    @JsonProperty("username")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Password must have min 5 and 12 max lenght!")
    @Size(min = 5, max = 12, message = "Password must have min 5 and 12 max lenght!")
    @JsonProperty("password")
    private String password;

    @Column(name = "role")
    @NotBlank(message = "Role must be not empty!")
    @JsonProperty("role")
    private String role;

}
