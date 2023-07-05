package Group.Better.controller;

import Group.Better.details.CustomUserDetails;
import Group.Better.entity.User;
import Group.Better.repository.PostRepository;
import Group.Better.repository.UserRepository;
import Group.Better.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import Group.Better.form.PostForm;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
@AllArgsConstructor
//@RequestMapping("/account")
public class NewController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    private StorageService storageService;

    @GetMapping("/new")
    public String newPost(@ModelAttribute("postForm") PostForm form){
        return "/new";
    }


    @PostMapping("/posts")
    public String createPost(@Validated  PostForm form, BindingResult result,

        @RequestParam("image") MultipartFile file, Model model, HttpServletRequest httpServletRequest) throws IOException{

        if (result.hasErrors()) {
            model.addAttribute("postForm", form);
            return "new";
        }

        String username = httpServletRequest.getRemoteUser();

        long user_id = userRepository.findIdByUsername(username);

        postRepository.insert(form.getTitle(), form.getContent(), user_id);
        storageService.uploadImage(file);

        return "redirect:/";
    }

}
