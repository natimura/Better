package Group.Better.controller;

import Group.Better.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
public class DeleteController {

    private final PostService postService;

    @PostMapping("detail/{id}/delete")
    public String postDelete(@PathVariable String id){
        postService.delete(id);
        return "redirect:/";
    }
}
