package kov.develop.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Redirect for another view
 */

@Controller
public class RootController {

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() { return "user"; }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/purchasings")
    public String purchasings() {
        return "purchasings";
    }

    @GetMapping("/exception")
    public String exception() {
        return "exception";
    }

}
