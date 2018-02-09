package kov.develop.mvc.controller;

import kov.develop.mvc.service.GoodService;
import kov.develop.mvc.service.PurchasingService;
import kov.develop.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RootController {

    private UserService userService;
    private PurchasingService purchasingService;
    private GoodService goodService;

    public RootController(UserService userService, PurchasingService purchasingService, GoodService goodService) {
        this.userService = userService;
        this.purchasingService = purchasingService;
        this.goodService = goodService;
    }

    //Базовая страница приложения
    @GetMapping("/admin")
    public String root(Model model) {
        model.addAttribute("goods", goodService.findAll());
        model.addAttribute("purchasings", purchasingService.findAll());
        return "admin";
    }

}
