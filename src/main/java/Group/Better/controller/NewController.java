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

    @PostMapping("/create")
    public String postCreate(@ModelAttribute("postForm")
                             @Validated PostForm postForm, BindingResult result,
                             @RequestParam("image") MultipartFile file,
                             @RequestParam("choiceImage") MultipartFile[] choiceImages,
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

        List<Choice> choices = postForm.getChoices();

        for (int i = 0; i < choices.size(); i++) {
            Choice choice = choices.get(i);
            if (choice.getChoiceContent() != null && !choice.getChoiceContent().isEmpty()) {
                choice.setPost(post);
                if (i < choiceImages.length && !choiceImages[i].isEmpty()) {
                    Long choiceImageDataId = storageService.uploadImage(choiceImages[i]);
                    if (choiceImageDataId != null) {
                        ImageData choiceImageData = storageRepository.findById(choiceImageDataId).orElse(null);
                        choice.setImageData(choiceImageData);
                    }
                }
                choiceService.save(choice);
            }
        }
        return "redirect:/";
    }
}
