package Group.Better.form;

import Group.Better.entity.Choice;
import Group.Better.entity.ImageData;
import Group.Better.entity.Post;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;
@Data
public class PostForm {

    @Valid
    private Post post;

    @Valid
    private List<Choice> choices;

    private ImageData imageData;

}
