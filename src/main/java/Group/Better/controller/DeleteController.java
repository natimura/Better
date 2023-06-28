package Group.Better.controller;

import Group.Better.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class DeleteController {

    private final PostRepository postRepository;

    @PostMapping("detail/{id}/delete")
    public String deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return "redirect:/";
    }
}
