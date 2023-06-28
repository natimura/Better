package Group.Better.controller;

import Group.Better.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
@AllArgsConstructor
public class DetailController {

    private final PostRepository postRepository;

    @GetMapping("/detail/{id}")
        public String postDetail(@PathVariable long id, Model model) {
        var post = postRepository.findById(id);
        model.addAttribute("post", post);
        return "detail";
    }

}
