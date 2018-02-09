package kov.develop.mvc.controller;

import kov.develop.mvc.model.Good;
import kov.develop.mvc.service.GoodService;
import kov.develop.mvc.service.PurchasingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static kov.develop.mvc.ControllerUtils.getErrors;

@RestController
public class AdminRestController {

    private GoodService goodService;
    private PurchasingService purchasingService;

    @Autowired
    public AdminRestController(GoodService goodService, PurchasingService purchasingService) {
        this.goodService = goodService;
        this.purchasingService = purchasingService;
    }

    @GetMapping("goods")
    public List<Good> findAll() {
        return goodService.findAll();
    }

    //Data from request recieved via Spring binding and checeked by validators
    // Return response with error's definition, if it's need
    @PostMapping("good/save")
    public ResponseEntity<String> createOrUpdate(@Valid Good good, BindingResult result) {
        ResponseEntity<String> response;
        if (result.hasErrors()){
            response = getErrors(result);
            return getErrors(result);}
        goodService.save(good);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
