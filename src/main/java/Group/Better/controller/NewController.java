package Group.Better.controller;

import Group.Better.entity.Choice;
import Group.Better.entity.ImageData;
import Group.Better.entity.Post;
import Group.Better.entity.User;
import Group.Better.repository.StorageRepository;
import Group.Better.repository.UserRepository;
import Group.Better.service.ChoiceService;
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
@RequestMapping("/account")
public class NewController {

    private final PostService postService;
    private final UserRepository userRepository;
    private final StorageRepository storageRepository;
    private final ChoiceService choiceService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/new")
    public String newPost(@ModelAttribute("post") Post post){
        return "/new";
    }

    @PostMapping("/posts")
    public String postCreate(@Validated  Post post, BindingResult result,
        @RequestParam("image") MultipartFile file,
                             @RequestParam("choiceContent") String[] choiceContents,
        Model model, HttpServletRequest httpServletRequest) throws IOException{

        if (result.hasErrors()) {
            model.addAttribute("post", post);
            return "new";
        }

        String username = httpServletRequest.getRemoteUser();
        User user = userRepository.findByUsername(username);
        post.setUser(user);

        Long imagedata = storageService.uploadImage(file);
        if (imagedata != null) {
            ImageData imageData = storageRepository.findById(imagedata).orElse(null);
            post.setImageData(imageData);
        }

        postService.save(post);

        for (String choiceContent : choiceContents) {
            Choice choice = new Choice();
            choice.setChoiceContent(choiceContent);
            choice.setPost(post);
            choiceService.save(choice);
        }



        return "redirect:/";
    }
}
