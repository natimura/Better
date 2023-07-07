package Group.Better.entity;

import Group.Better.validation.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @UniqueUsername
    private String username;

    @NotBlank
    @Size(min = 12, max = 128)
    private String password;

    public User(){
    }
}

