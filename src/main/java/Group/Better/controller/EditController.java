package Group.Better.controller;

import Group.Better.entity.ImageData;
import Group.Better.entity.Post;
import Group.Better.entity.User;
import Group.Better.repository.StorageRepository;
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

@Controller
@AllArgsConstructor
@RequestMapping("/account")
public class EditController {

    private final PostService postService;
    private final StorageService storageService;
    private final StorageRepository storageRepository;

    @GetMapping("/detail/{id}/edit")
    public String editPost(@PathVariable String id, Model model) {
        var post = postService.getById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();

        if (!post.getUser().getUsername().equals(currentUserName)) {
            return "redirect:/";
        }

        model.addAttribute("post", post);
        return "edit";
    }

    @PostMapping("/detail/{id}/update")
    public String postUpdate(@PathVariable String id, @Validated Post post, BindingResult result, Model model,
        @RequestParam("image") MultipartFile file) throws IOException {

        if (result.hasErrors()) {
            model.addAttribute("post", post);
            return "edit";
        }

        Post exPost = postService.getById(id);
        User user = exPost.getUser();
        post.setUser(user);

        if (file != null && !file.isEmpty()) {
            Long imagedata = storageService.uploadImage(file);
            if (imagedata != null) {
                ImageData imageData = storageRepository.findById(imagedata).orElse(null);
                post.setImageData(imageData);
            }
        }
        else {
            post.setImageData((exPost.getImageData()));
        }

        postService.save(post);
        return "redirect:/detail/{id}";
    }

}
