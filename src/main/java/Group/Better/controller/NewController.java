package Group.Better.controller;

import Group.Better.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import Group.Better.form.PostForm;

@Controller
@AllArgsConstructor
//@RequestMapping("/account")
public class NewController {

    private final PostRepository postRepository;

    @GetMapping("/new")
    public String newPost(@ModelAttribute("postForm") PostForm form){
        return "/new";
    }


    @PostMapping("/posts")
    public String createPost(@Validated  PostForm form, BindingResult result
        @PathVariable("userId") Integer userId){
        if (result.hasErrors()) {
            return newPost(form);
        }
        postRepository.insert(form.getTitle(), form.getContent(), form.getUser_id());
        return "redirect:/";
    }

}
