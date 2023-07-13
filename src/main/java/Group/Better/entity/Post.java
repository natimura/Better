package Group.Better.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank(message = "入力してください")
    private String title;

    @NotBlank(message = "入力してください")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_data_id")
    private ImageData imageData;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Choice> choices;
}
