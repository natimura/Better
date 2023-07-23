package Group.Better.controller;

import Group.Better.entity.User;
import Group.Better.exception.UsernameAlreadyExistsException;
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
    public String signUpCreation(@ModelAttribute("user") User user, HttpServletRequest request) {
        if (request.getSession().getAttribute("errorMessage") != null) {
            request.setAttribute("errorMessage", request.getSession().getAttribute("errorMessage"));
            request.getSession().removeAttribute("errorMessage");
        }
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@Validated @ModelAttribute("user") User user, BindingResult result, HttpServletRequest request){
        if (result.hasErrors()){
            return signUpCreation(user, request);
        }
        try {
            userService.signUp(user.getUsername(), user.getPassword());
        } catch (UsernameAlreadyExistsException e) {
            result.rejectValue("username", "error.user", e.getMessage());
            return signUpCreation(user, request);
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }
}


