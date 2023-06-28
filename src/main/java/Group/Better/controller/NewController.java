package Group.Better.controller;

import Group.Better.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import Group.Better.form.PostForm;

@Controller
@AllArgsConstructor
public class NewController {

    private final PostRepository postRepository;

    @GetMapping("/new")
    public String newPost(@ModelAttribute("postForm") PostForm form){
        return "/new";
    }


    @PostMapping("/posts")
    public String createPost(PostForm postForm){
        postRepository.insert(postForm.getTitle(), postForm.getContent());
        return "redirect:/";
    }

}
