package Group.Better.controller;

import Group.Better.entity.Post;
import Group.Better.service.ChoiceService;
import Group.Better.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class VoteController {

    private final ChoiceService choiceService;
    private final PostService postService;

    @PostMapping("/detail/{choiceId}/vote")
    public String vote(@PathVariable Long choiceId) {
        Long postId = choiceService.vote(choiceId);
        return "redirect:/detail/" + postId;
    }

    @PostMapping("/detail/{id}/closedVote")
    public String closedVote(@PathVariable Long id) {
        Post post = postService.getById(id);
        post.setClosedVote(true);
        postService.save(post);
        return "redirect:/detail/" + id;
    }
}

