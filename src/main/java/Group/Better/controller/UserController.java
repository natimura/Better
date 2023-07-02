package Group.Better.controller;

import Group.Better.form.UserForm;
import Group.Better.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signUp")
    public String signUpCreation(@ModelAttribute("userForm") UserForm Form){
        return "/signup";
    }

    @PostMapping("/signUp")
    public String signUp(@Validated UserForm form, BindingResult result){
        if (result.hasErrors()){
            return signUpCreation(form);
        }
        userService.signUp(form.getUsername(), form.getPassword());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }
}


