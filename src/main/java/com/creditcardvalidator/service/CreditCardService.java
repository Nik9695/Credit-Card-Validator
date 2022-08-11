package com.creditcardvalidator.service;


import com.creditcardvalidator.model.CreditCardModel;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreditCardService {

    private boolean creditCardNumberIsValid;
    private boolean cvvCodeIsValid;
    private boolean expiryDateIsValid;
    private boolean validAccordingLunhAlgorithm;


    private CreditCardModel creditCardModel;
    public CreditCardService(CreditCardModel creditCardModel) {
        this.creditCardModel = creditCardModel;
    }
    public String validateCardNumber() {

        String cardNumber = creditCardModel.getCardNumber();

        String pattern_16_digitsCardNumber = "^\\d{16}$";
        String pattern_19_digitsCardNumber = "^\\d{19}$";

        if (cardNumber.matches(pattern_16_digitsCardNumber) || cardNumber.matches(pattern_19_digitsCardNumber)){
            creditCardNumberIsValid = true;
            return cardNumber;
        } else {
            return "Card number is invalid";
        }
    }

    public String validateCvvCode() {

        String cvvCode = creditCardModel.getCvvCode();
        String cardNumber = creditCardModel.getCardNumber();


        String patternAmericanExpressCard = "^34|^37";
        String pattern_3_digitsCvvCode = "^\\d{3}$";
        String pattern_4_digitsCvvCode = "^\\d{4}$";

        Matcher matcher = Pattern.compile(patternAmericanExpressCard).matcher("");
        matcher.reset(cardNumber);

        if (matcher.find()){
            if (cvvCode.matches(pattern_4_digitsCvvCode)){
                cvvCodeIsValid = true;
                return cvvCode;
            }else {
                return "CVV code is invalid";
            }
        } else if(cvvCode.matches(pattern_3_digitsCvvCode)) {
            cvvCodeIsValid = true;
            return cvvCode;
        }else {
            return "CVV code is invalid";
        }
    }
    public String validateWithLunhAlgorithm() {
        String cardNumber = creditCardModel.getCardNumber();
        int[] ints = new int[cardNumber.length()];
        for (int i = 0; i < cardNumber.length(); i++) {
            ints[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }
        if (sum % 10 == 0) {
            validAccordingLunhAlgorithm = true;
            return cardNumber;
        } else {
            validAccordingLunhAlgorithm = false;
            return "Validation according Lunh algorithm is failed.";
        }
    }
    public String validateExpiryDate() {
        String cardExpiryDate = creditCardModel.getExpiryDate();

        DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("MM/yyyy");

        YearMonth lastValidMonth = YearMonth.parse(cardExpiryDate, yearMonthFormatter);

        if(YearMonth.now(ZoneId.systemDefault()).isAfter(lastValidMonth)) {
            return "The date must be after present time!";
        }
        expiryDateIsValid = true;
        return cardExpiryDate;
    }

    public boolean validateCard(CreditCardModel creditCard) {
        validateWithLunhAlgorithm();
        validateCardNumber();
        validateExpiryDate();
        validateCvvCode();
        if(creditCardNumberIsValid == true && cvvCodeIsValid == true &&
                expiryDateIsValid == true && validAccordingLunhAlgorithm == true){
            return true;
        }
        return false;
    }

}
