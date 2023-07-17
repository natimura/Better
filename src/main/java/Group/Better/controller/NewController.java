package Group.Better.controller;

import Group.Better.entity.Choice;
import Group.Better.entity.ImageData;
import Group.Better.entity.Post;
import Group.Better.entity.User;
import Group.Better.form.PostForm;
import Group.Better.repository.StorageRepository;
import Group.Better.repository.UserRepository;
import Group.Better.service.ChoiceService;
import Group.Better.service.PostService;
import Group.Better.service.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
@Slf4j
public class NewController {

    private final PostService postService;
    private final UserRepository userRepository;
    private final StorageRepository storageRepository;
    private final ChoiceService choiceService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/new")
    public String newPost(@ModelAttribute("postForm") PostForm postForm){
        return "new";
    }

    @PostMapping("/posts")
    public String postCreate(@ModelAttribute("postForm")
                             @Validated PostForm postForm, BindingResult result,
                             @RequestParam("image") MultipartFile file,
                             Model model, HttpServletRequest httpServletRequest) throws IOException{

        Post post = postForm.getPost();

        if (result.hasErrors()) {
            model.addAttribute("postForm", postForm);
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
        log.info("postのID : " + post.getId());

        List<Choice> choices = postForm.getChoices();
        log.info("postのID : " + post.getId());

        for (Choice choice : choices) {
            if (choice.getChoiceContent() != null && !choice.getChoiceContent().isEmpty()) {
                log.info("postのID : " + post.getId());
                choice.setPost(post);
                log.info("postのID : " + choice.getPost().getId());
                choiceService.save(choice);
            }
        }

        return "redirect:/";
    }
}
