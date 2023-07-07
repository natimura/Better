package Group.Better.controller;

import Group.Better.entity.Post;
import Group.Better.entity.User;
import Group.Better.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
//@RequestMapping("/account")
public class EditController {

    private final PostService postService;

    @GetMapping("/detail/{id}/edit")
    public String editPost(@PathVariable String id, Model model) {
        var post = postService.getById(id);
        model.addAttribute("post", post);
        return "edit";
    }

    @PostMapping("/detail/{id}/update")
    public String postUpdate(@PathVariable String id, @Validated Post post, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("post", post);
            return "edit";
        }

        Post exPost = postService.getById(id);
        User user = exPost.getUser();

        post.setUser(user);
        postService.save(post);
        return "redirect:/detail/{id}";
    }
}
