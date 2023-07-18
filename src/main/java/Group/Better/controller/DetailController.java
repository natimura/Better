package Group.Better.controller;

import Group.Better.entity.Choice;
import Group.Better.entity.Post;
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
    public String detailPost(@PathVariable Long id, Model model) {
        Post post = postService.getById(id);

        if (post.getImageData() != null) {
            byte[] imageData = storageService.downloadImage(post.getImageData().getId());
            String base64ImageData = Base64.getEncoder().encodeToString(imageData);
            model.addAttribute("base64ImageData", base64ImageData);
        }

        List<Choice> choices = post.getChoices();

        for (Choice choice : choices) {
            if (choice.getImageData() != null) {
                byte[] choiceImageData = storageService.downloadImage(choice.getImageData().getId());
                String base64ChoiceImageData = Base64.getEncoder().encodeToString(choiceImageData);
                choice.setBase64ChoiceImageData(base64ChoiceImageData);
            }
        }

        model.addAttribute("choices", choices);
        model.addAttribute("post", post);

        return "detail";
    }
}
