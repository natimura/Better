package Group.Better.controller;

import Group.Better.service.PostService;
import Group.Better.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

import java.util.Base64;

@Controller
@AllArgsConstructor
public class DetailController {

    private final PostService postService;
    private final StorageService storageService;

    @GetMapping("/detail/{id}")
    public String detailPost(@PathVariable String id, Model model) {
        var post = postService.getById(id);

        //byte[] imageData = storageService.downloadImage(2);
        //String base64ImageData = Base64.getEncoder().encodeToString(imageData);
        //model.addAttribute("base64ImageData", base64ImageData);

        model.addAttribute("post", post);
        return "detail";
    }

}
