package Group.Better.controller;

import Group.Better.form.PostForm;
import Group.Better.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
//@RequestMapping("/account")
public class EditController {

    private final PostRepository postRepository;

    @GetMapping("/detail/{id}/edit")
    public String postEdit(@PathVariable long id, Model model) {
        var form = postRepository.findById(id);
        model.addAttribute("postForm", form);
        return "edit";
    }

    @PostMapping("/detail/{id}/update")
    public String updatePost(@PathVariable long id, @Validated PostForm form, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "edit";
        }
        postRepository.update(id, form.getTitle(), form.getContent());
        return "redirect:/detail/{id}";
    }
}
