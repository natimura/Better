package Group.Better.controller;

import Group.Better.entity.Choice;
import Group.Better.service.PostService;
import Group.Better.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Base64;
import java.util.List;

@Controller
@AllArgsConstructor
public class DetailController {

    private final PostService postService;
    private final StorageService storageService;

    @GetMapping("/detail/{id}")
    public String detailPost(@PathVariable String id, Model model) {
        var post = postService.getById(id);

        if (post.getImageData() != null) {
            byte[] imageData = storageService.downloadImage(post.getImageData().getId());
            String base64ImageData = Base64.getEncoder().encodeToString(imageData);
            model.addAttribute("base64ImageData", base64ImageData);
        }

        List<Choice> choices = post.getChoices();
        model.addAttribute("choices", choices);

        model.addAttribute("post", post);


        return "detail";
    }
}
