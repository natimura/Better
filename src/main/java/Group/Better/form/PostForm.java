package Group.Better.form;

import Group.Better.entity.User;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Data
public class PostForm {

    @NotBlank(message = "入力してください")
    private String title;

    @NotBlank(message = "入力してください")
    private String content;

    private UserId users.id;

}
