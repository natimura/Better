package Group.Better.controller;

import Group.Better.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class IndexController {

    private final PostRepository postRepository;

    @GetMapping("/")
    public String index(Model model){
        var postList = postRepository.findAll();
        model.addAttribute("postList", postList);
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }


}

