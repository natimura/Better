package Group.Better.controller;

import Group.Better.form.PostForm;
import Group.Better.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
//@RequestMapping("/account")
public class EditController {

    private final PostRepository postRepository;

    @GetMapping("/detail/{id}/edit")
    public String postEdit(@PathVariable long id, Model model) {
        var post = postRepository.findById(id);
        model.addAttribute("post", post);
        return "edit";
    }

    @PostMapping("/detail/{id}/update")
    public String updatePost(@PathVariable long id, PostForm postForm){
        postRepository.update(id, postForm.getTitle(), postForm.getContent());
        return "redirect:/";
    }
}
