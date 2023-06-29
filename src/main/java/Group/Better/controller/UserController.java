package Group.Better.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {



    @GetMapping("/signUp")
    public String sigUp(){
        return "/signup";
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }


}
