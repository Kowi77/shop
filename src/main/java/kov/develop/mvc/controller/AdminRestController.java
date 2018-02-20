package kov.develop.mvc.controller;

import kov.develop.mvc.model.Good;
import kov.develop.mvc.model.Purchasing;
import kov.develop.mvc.model.PurchasingDto;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static kov.develop.mvc.utils.ControllerUtils.getErrors;

/**
 * Admin RESTful service for goods and purchasing
 */

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AdminRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminRestController.class);

    private GoodService goodService;
    private PurchasingService purchasingService;
    private UserService userService;

    @Autowired
    public AdminRestController(GoodService goodService, PurchasingService purchasingService, UserService userService) {
        this.goodService = goodService;
        this.purchasingService = purchasingService;
        this.userService = userService;
    }

    @Secured(value={"ADMIN"})
    @GetMapping("goods")
    public List<Good> findAllGoods() {
        return goodService.findAll();
    }

    @Secured(value={"ADMIN"})
    @PostMapping("good/save")
    public ResponseEntity<String> createOrUpdateGood(@Valid Good good, BindingResult result) {
        if (result.hasErrors()) {
            LOGGER.error("Binding or validation error for good saving!");
            return getErrors(result);
        }
        Good good1 = goodService.save(good);
        if (good1 != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            LOGGER.error("{} saving was failed!", good);
            return new ResponseEntity<String>("Good's saving was failed!", HttpStatus.CONFLICT);
        }

    }

    @Secured(value={"ADMIN", "USER"})
    @GetMapping("good/{id}")
    public Good getOneGood(@PathVariable("id") int id) {
        return goodService.get(id);
    }

    @Secured(value={"ADMIN"})
    @DeleteMapping("good/{id}")
    public void deleteGood(@PathVariable("id") int id){ goodService.delete(id); }

    @Secured(value={"ADMIN"})
    @GetMapping("admin/purchasings")
    public List<PurchasingDto> findAllPurchasings() {
        return purchasingService.findAllDto();
    }
}
