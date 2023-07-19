package Group.Better.controller;

import Group.Better.entity.Choice;
import Group.Better.entity.ImageData;
import Group.Better.entity.Post;
import Group.Better.form.PostForm;
import Group.Better.repository.StorageRepository;
import Group.Better.service.ChoiceService;
import Group.Better.service.PostService;
import Group.Better.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
@Transactional
public class EditController {

    @Autowired
    private final PostService postService;

    private final StorageService storageService;
    private final StorageRepository storageRepository;
    private final ChoiceService choiceService;

    @GetMapping("/detail/{id}/edit")
    public String editPost(@PathVariable Long id, Model model) {

        var post = postService.getById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        if (!post.getUser().getUsername().equals(currentUserName)) {
            return "redirect:/";
        }

        PostForm postForm = new PostForm();
        postForm.setPost(post);
        postForm.setChoices(post.getChoices());

        model.addAttribute("postForm", postForm);
        return "edit";
    }

    @PostMapping("/detail/{id}/update")
    public String postUpdate(@PathVariable Long id, @Validated PostForm postForm, BindingResult result, Model model,
                             @RequestParam("image") MultipartFile file,
                             @RequestPart("choiceImage") List<MultipartFile> choiceImages) throws IOException {

        if (result.hasErrors()) {
            model.addAttribute("postForm", postForm);
            return "edit";
        }

        Post post = postService.getById(id);
        post.setTitle(postForm.getPost().getTitle());
        post.setContent(postForm.getPost().getContent());

        List<Choice> updatedChoices = postForm.getChoices();
        List<Choice> existingChoices = new ArrayList<>(post.getChoices());
        List<Choice> choicesToDelete = new ArrayList<>();

        boolean isChoiceEdited = false;
        int minSize = Math.min(existingChoices.size(), updatedChoices.size());

        for (int i = 0; i < minSize; i++) {
            Choice updatedChoice = updatedChoices.get(i);
            Choice existingChoice = existingChoices.get(i);

            if (updatedChoice.getChoiceContent().trim().isEmpty()) {
                choicesToDelete.add(existingChoice);
                existingChoices.remove(i);
                updatedChoices.remove(i);
                i--;
                minSize--;
                continue;
            }

            if (!existingChoice.getChoiceContent().equals(updatedChoice.getChoiceContent())) {
                isChoiceEdited = true;
                existingChoice.setChoiceContent(updatedChoice.getChoiceContent());
            }

            MultipartFile choiceImage = choiceImages.get(i);
            if (choiceImage != null && !choiceImage.isEmpty()) {
                Long imageDataId = storageService.uploadImage(choiceImage);
                if (imageDataId != null) {
                    ImageData imageData = storageRepository.findById(imageDataId).orElse(null);
                    existingChoice.setImageData(imageData);
                }
            }
        }

        if (updatedChoices.size() > existingChoices.size()) {
            for (int i = minSize; i < updatedChoices.size(); i++) {
                Choice newChoice = updatedChoices.get(i);
                if (!newChoice.getChoiceContent().trim().isEmpty()) {
                    newChoice.setPost(post);
                    existingChoices.add(newChoice);
                }
            }
            isChoiceEdited = true;
        }

        if (isChoiceEdited) {
            for (Choice choice : existingChoices) {
                choice.setVoteCount(0);
            }
        }

        if (file != null && !file.isEmpty()) {
            Long imagedata = storageService.uploadImage(file);
            if (imagedata != null) {
                ImageData imageData = storageRepository.findById(imagedata).orElse(null);
                post.setImageData(imageData);
            }
        }

        post.setChoices(existingChoices);
        postService.save(post);

        for (Choice choice : choicesToDelete) {
            choiceService.delete(choice);
        }

        return "redirect:/detail/{id}";
    }
}
