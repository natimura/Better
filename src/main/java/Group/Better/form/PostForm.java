package Group.Better.form;

import Group.Better.entity.Choice;
import Group.Better.entity.ImageData;
import Group.Better.entity.Post;
import Group.Better.validation.ValidChoice;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;
@Data
@ValidChoice
public class PostForm {

    @Valid
    private Post post;

    private List<Choice> choices;

    private ImageData imageData;

}
