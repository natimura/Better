package Group.Better.controller;

import Group.Better.entity.Post;
import Group.Better.service.PostService;
import Group.Better.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Base64;
import java.util.List;

@Controller
@AllArgsConstructor
public class IndexController {

    private final PostService postService;
    private final StorageService storageService;

    @GetMapping("/")
    public String index(Model model) {
        List<Post> postList = postService.getAll();
        byte[] imageData = storageService.downloadImage(2);
        String base64ImageData = Base64.getEncoder().encodeToString(imageData);
        model.addAttribute("postList", postList);
        model.addAttribute("base64ImageData", base64ImageData);
        return "index";
    }
}



