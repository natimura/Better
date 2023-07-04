package Group.Better.controller;

import Group.Better.details.CustomUserDetails;
import Group.Better.entity.User;
import Group.Better.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import Group.Better.form.PostForm;

import javax.servlet.http.HttpSession;

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
    public String createPost(@Validated  PostForm form, BindingResult result, @AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){
        if (result.hasErrors()) {
            model.addAttribute("postForm", form);
            return "new";
        }
        postRepository.insert(form.getTitle(), form.getContent(), customUserDetails.getId());
        return "redirect:/";
    }

}
