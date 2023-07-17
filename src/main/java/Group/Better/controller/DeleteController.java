package Group.Better.controller;

import Group.Better.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class DeleteController {

    private final PostService postService;

    @PostMapping("detail/{id}/delete")
    public String postDelete(@PathVariable Long id){
        postService.delete(id);
        return "redirect:/";
    }
}
