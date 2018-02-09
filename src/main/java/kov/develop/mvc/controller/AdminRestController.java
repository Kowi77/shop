package kov.develop.mvc.controller;

import kov.develop.mvc.model.Good;
import kov.develop.mvc.model.Purchasing;
import kov.develop.mvc.service.GoodService;
import kov.develop.mvc.service.PurchasingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static kov.develop.mvc.ControllerUtils.getErrors;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {

    private GoodService goodService;
    private PurchasingService purchasingService;

    @Autowired
    public AdminRestController(GoodService goodService, PurchasingService purchasingService) {
        this.goodService = goodService;
        this.purchasingService = purchasingService;
    }

    @GetMapping("goods")
    public List<Good> findAllGoods() {
        return goodService.findAll();
    }

    //Data from request recieved via Spring binding and checeked by validators
    // Return response with error's definition, if it's need
    @PostMapping("good/save")
    public ResponseEntity<String> createOrUpdateGood(@Valid Good good, BindingResult result) {
        ResponseEntity<String> response;
        if (result.hasErrors()){
            response = getErrors(result);
            return getErrors(result);}
        goodService.save(good);
        return new ResponseEntity<>(HttpStatus.OK);
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
}
