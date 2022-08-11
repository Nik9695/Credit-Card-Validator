package com.creditcardvalidator.controllers;

import com.creditcardvalidator.model.CreditCardModel;
import com.creditcardvalidator.service.CreditCardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreditCardController {

    private CreditCardService creditCardService;


    @GetMapping("/")
    public String getValidate(){
        return "/validator";
    }

    @GetMapping("/validationPassed")
    public String getValidationPassed(){
        return "/validationPassed";
    }
    @GetMapping("/validationFailed")
    public String getValidationFailed(){
        return "/validationFailed";
    }

    @GetMapping("/style")
    public String getStyle(){
        return "/style";
    }

    @PostMapping("/cards/validator")
    public String validate(@ModelAttribute CreditCardModel creditCard){
        creditCardService = new CreditCardService(creditCard);
        if(creditCardService.validateCard(creditCard)){
            return "redirect:/validationPassed";
        }
        return "redirect:/validationFailed";
    }
}