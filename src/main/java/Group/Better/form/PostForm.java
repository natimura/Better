package Group.Better.form;

import Group.Better.entity.Choice;
import Group.Better.entity.Post;
import lombok.Data;

import java.util.List;
@Data
public class PostForm {

    private Post post;

    private List<Choice> choices;

}
