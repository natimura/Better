package Group.Better.controller;

import Group.Better.entity.Post;
import Group.Better.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class IndexController {

    private final PostService postService;

    @GetMapping("/")
    public String index(Model model) {
        List<Post> postList = postService.getAll();
        model.addAttribute("postList", postList);
        return "index";
    }
}



