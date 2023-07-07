package Group.Better.controller;

import Group.Better.details.CustomUserDetails;
import Group.Better.entity.User;
import Group.Better.form.UserForm;
import Group.Better.service.CustomUserDetailsService;
import Group.Better.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

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
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(form.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }
}


