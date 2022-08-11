package com.creditcardvalidator.controllers;

import com.creditcardvalidator.model.CreditCardModel;
import com.creditcardvalidator.service.CreditCardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Class is used for processing and sending requests
 * @see CreditCardModel
 * @see CreditCardService
 */
@Controller
public class CreditCardController {

    private CreditCardService creditCardService;


    /**
     *
     * @return opens main page of the application
     */
    @GetMapping("/")
    public String getValidate(){
        return "validator";
    }

    /**
     *
     * @return returns a page in case of successful credit card validation
     */
    @GetMapping("/validationPassed")
    public String getValidationPassed(){
        return "/validationPassed";
    }

    /**
     *
     * @return returns a page in case of failed credit card validation
     */
    @GetMapping("/validationFailed")
    public String getValidationFailed(){
        return "/validationFailed";
    }

    /**
     *
     * @return returns resource with style.css file (not available for user)
     */
    @GetMapping("/style")
    public String getStyle(){
        return "/style";
    }

    /**
     * Method is used for obtaining information from web page. As the result instance of {@link CreditCardModel} will be created,
     * validation will be performed, the user will be redirected to another page depends on the validation result.
     * @param creditCard model of the credit card
     * @return web page, depends on validation result.
     */
    @PostMapping("/cards/validator")
    public String validate(@ModelAttribute CreditCardModel creditCard){
        creditCardService = new CreditCardService(creditCard);
        if(creditCardService.validateCard(creditCard)){
            return "redirect:/validationPassed";
        }
        return "redirect:/validationFailed";
    }
}