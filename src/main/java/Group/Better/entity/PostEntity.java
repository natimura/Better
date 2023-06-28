package Group.Better.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PostEntity {
    private long id;
    private String title;
    private  String content;
}
