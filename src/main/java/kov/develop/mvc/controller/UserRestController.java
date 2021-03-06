package kov.develop.mvc.controller;

import kov.develop.mvc.model.Good;
import kov.develop.mvc.model.Purchasing;
import kov.develop.mvc.model.User;
import kov.develop.mvc.service.GoodService;
import kov.develop.mvc.service.PurchasingService;
import kov.develop.mvc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static kov.develop.mvc.utils.ControllerUtils.getErrors;

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UserRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    private GoodService goodService;
    private PurchasingService purchasingService;
    private UserService userService;

    @Autowired
    public UserRestController(GoodService goodService, PurchasingService purchasingService, UserService userService) {
        this.goodService = goodService;
        this.purchasingService = purchasingService;
        this.userService = userService;
    }

    @Secured(value={"USER"})
    @GetMapping("user/goods")
    public List<Good> findAllGoodsNotZeroQuantity() {
        return goodService.findAllNotZeroQuantity();
    }

    @Secured(value={"USER"})
    @PostMapping("user/purchase/{username}")
    public ResponseEntity<String> purchasing(@Valid Good good, @PathVariable("username") String username, BindingResult result) {
        org.springframework.security.core.userdetails.User authUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!authUser.getUsername().equals(username)) {
            LOGGER.error("Attemp of unauthority operation!");
            return new ResponseEntity<String>(new String("Purchasing failed, you haven't authority for this!"), HttpStatus.UNAUTHORIZED);
        } else if (result.hasErrors()) {
            LOGGER.error("Binding or validation error for purchasing saving!");
            return getErrors(result);
        }
        Integer quantity = good.getQuantity();
        Good good1 = goodService.purchase(good);
        if (good1 != null) {
            Purchasing purchasing = new Purchasing(null, userService.findByUsername(username).getId(), good.getId(), LocalDate.now(), good.getPrice(), quantity);
            purchasingService.save(purchasing);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            LOGGER.error("Error with accept of purchasing!");
            return new ResponseEntity<String>(new String("Purchasing failed!"), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("saveUser")
    public ResponseEntity<String> saveUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            LOGGER.error("Binding or validation error for user saving!");
            return getErrors(result);
        }
        User user1 = userService.save(user);
        if (user1 != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            LOGGER.error("User with same name already exist or another error of users's saving");
            return new ResponseEntity<String>(new String("User with same name already exist!"), HttpStatus.CONFLICT);
        }
    }
}
