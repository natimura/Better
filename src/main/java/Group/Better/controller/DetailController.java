package Group.Better.controller;

import Group.Better.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
@AllArgsConstructor
public class DetailController {

    private final PostService postService;

    @GetMapping("/detail/{id}")
    public String detailPost(@PathVariable String id, Model model) {
        var post = postService.getById(id);
        model.addAttribute("post", post);
        return "detail";
    }

}
