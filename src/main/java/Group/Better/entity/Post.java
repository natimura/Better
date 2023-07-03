package Group.Better.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@Data
public class Post {
    private long id;
    private String title;
    private  String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
