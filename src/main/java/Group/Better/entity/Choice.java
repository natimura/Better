package Group.Better.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Entity
@Table(name = "choices")
@NoArgsConstructor
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "入力してください")
    private String choice;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
