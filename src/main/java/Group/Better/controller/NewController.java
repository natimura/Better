package Group.Better.controller;

import Group.Better.entity.Post;
import Group.Better.entity.User;
import Group.Better.repository.UserRepository;
import Group.Better.service.PostService;
import Group.Better.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@AllArgsConstructor
//@RequestMapping("/account")
public class NewController {

    private final PostService postService;
    private final UserRepository userRepository;

    @Autowired
    private StorageService storageService;

    @GetMapping("/new")
    public String newPost(@ModelAttribute("post") Post post){
        return "/new";
    }

    @PostMapping("/posts")
    public String postCreate(@Validated  Post post, BindingResult result,
        @RequestParam("image") MultipartFile file, Model model, HttpServletRequest httpServletRequest) throws IOException{

        if (result.hasErrors()) {
            model.addAttribute("post", post);
            return "new";
        }

        String username = httpServletRequest.getRemoteUser();
        User user = userRepository.findByUsername(username);
        post.setUser(user);
        postService.save(post);
        storageService.uploadImage(file);

        return "redirect:/";
    }
}
