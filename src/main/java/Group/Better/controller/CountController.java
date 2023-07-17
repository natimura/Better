package Group.Better.controller;

import Group.Better.service.ChoiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class CountController {

    private final ChoiceService choiceService;

    @PostMapping("/choice/{choiceId}/vote")
    public String vote(@PathVariable String choiceId) {
        Long postId = choiceService.vote(choiceId);
        return "redirect:/detail/" + postId;
    }
}
