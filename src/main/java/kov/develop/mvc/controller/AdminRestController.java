package kov.develop.mvc.controller;

import kov.develop.mvc.model.Good;
import kov.develop.mvc.model.Purchasing;
import kov.develop.mvc.model.User;
import kov.develop.mvc.service.GoodService;
import kov.develop.mvc.service.PurchasingService;
import kov.develop.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static kov.develop.mvc.ControllerUtils.getErrors;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {

    private GoodService goodService;
    private PurchasingService purchasingService;
    private UserService userService;

    @Autowired
    public AdminRestController(GoodService goodService, PurchasingService purchasingService, UserService userService) {
        this.goodService = goodService;
        this.purchasingService = purchasingService;
        this.userService = userService;
    }

    @GetMapping("goods")
    public List<Good> findAllGoods() {
        return goodService.findAll();
    }

    //Data from request recieved via Spring binding and checeked by validators
    // Return response with error's definition, if it's need
    @PostMapping("good/save")
    public ResponseEntity<String> createOrUpdateGood(@Valid Good good, BindingResult result) {
        if (result.hasErrors()) {
            return getErrors(result);
        }
        goodService.save(good);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("user/purchase/{username}")
    public ResponseEntity<String> purchasing(@Valid Good good, @PathVariable("username") String username, BindingResult result) {
        if (result.hasErrors()) {
            return getErrors(result);
        }
        Good good1 = goodService.purchase(good);
        if (good1 != null) {
            purchasingService.save(new Purchasing(null, userService.findByUsername(username).getId(), good.getId(), LocalDate.now(), good.getPrice(), good.getQuantity()));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("good/{id}")
    public Good getOneGood(@PathVariable("id") int id) {
        return goodService.get(id);
    }

    @DeleteMapping("good/{id}")
    public void deleteGood(@PathVariable("id") int id){
        goodService.delete(id);
    }

    @GetMapping("admin/purchasings")
    public List<Purchasing> findAllPurchasings() {
        return purchasingService.findAll();
    }

    @PostMapping("saveUser")
    public ResponseEntity<String> saveUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return getErrors(result);
        }
        User user1 = userService.save(user);
        if (user1 != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.CONFLICT);
        }
    }
}
