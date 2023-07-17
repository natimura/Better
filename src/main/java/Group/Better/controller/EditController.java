package Group.Better.controller;

import Group.Better.entity.Choice;
import Group.Better.entity.ImageData;
import Group.Better.entity.Post;
import Group.Better.entity.User;
import Group.Better.form.PostForm;
import Group.Better.repository.StorageRepository;
import Group.Better.service.ChoiceService;
import Group.Better.service.PostService;
import Group.Better.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
public class EditController {

    private final PostService postService;
    private final StorageService storageService;
    private final StorageRepository storageRepository;
    private final ChoiceService choiceService;

    @GetMapping("/detail/{id}/edit")
    public String editPost(@PathVariable Long id, Model model) {

        var post = postService.getById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        if (!post.getUser().getUsername().equals(currentUserName)) {
            return "redirect:/";
        }

        PostForm postForm = new PostForm();
        postForm.setPost(post);
        postForm.setChoices(post.getChoices());

        model.addAttribute("postForm", postForm);
        return "edit";
    }

    @PostMapping("/detail/{id}/update")
    public String postUpdate(@PathVariable Long id, @Validated PostForm postForm, BindingResult result, Model model,
        @RequestParam("image") MultipartFile file) throws IOException {

        if (result.hasErrors()) {
            model.addAttribute("postForm", postForm);
            return "edit";
        }

        Post post = postService.getById(id);
        post.setTitle(postForm.getPost().getTitle());
        post.setContent(postForm.getPost().getContent());

        List<Choice> updatedChoices = postForm.getChoices();
        List<Choice> existingChoices = post.getChoices();

        int minSize = Math.min(existingChoices.size(), updatedChoices.size());
        for (int i = 0; i < minSize; i++) {
            existingChoices.get(i).setChoiceContent(updatedChoices.get(i).getChoiceContent());
        }

        for (int i = minSize; i < updatedChoices.size(); i++) {
            Choice newChoice = updatedChoices.get(i);
            newChoice.setPost(post);
            choiceService.save(newChoice);
        }

        User user = post.getUser();
        post.setUser(user);

        if (file != null && !file.isEmpty()) {
            Long imagedata = storageService.uploadImage(file);
            if (imagedata != null) {
                ImageData imageData = storageRepository.findById(imagedata).orElse(null);
                post.setImageData(imageData);
            }
        }
        else {
            post.setImageData((post.getImageData()));
        }

        postService.save(post);
        return "redirect:/detail/{id}";
    }

}
