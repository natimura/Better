package Group.Better.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@Data

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private  String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(){
    }
}
