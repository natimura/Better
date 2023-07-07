package Group.Better.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank(message = "入力してください")
    private String title;

    @NotBlank(message = "入力してください")
    private  String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public Post(){
    }
}
