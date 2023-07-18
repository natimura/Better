package Group.Better.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "choices")
@NoArgsConstructor
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String choiceContent;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_data_id")
    private ImageData imageData;

    private int voteCount = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    @Transient
    private String base64ChoiceImageData;
}
