package com.creditcardvalidator.service;


import com.creditcardvalidator.model.CreditCardModel;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class responds for credit card validation process.
 */
public class CreditCardService {


    private boolean creditCardNumberIsValid;
    private boolean cvvCodeIsValid;
    private boolean expiryDateIsValid;
    private boolean validAccordingLunhAlgorithm;


    private CreditCardModel creditCardModel;

    /**
     * Class constructor
     * @param creditCardModel instance of {@link CreditCardModel}
     */
    public CreditCardService(CreditCardModel creditCardModel) {
        this.creditCardModel = creditCardModel;
    }

    /**
     * Method used for checking validity of credit card number (16 or 19 digits). If credit card number is valid,
     * method returns card number as {@link String} and sets {@link CreditCardService#creditCardNumberIsValid} to 'true',
     * otherwise it returns message about failure and sets {@link CreditCardService#creditCardNumberIsValid} to 'false'.
     * @return {@link String} card number or failure message
     */
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

    /**
     * Method used for checking validity of CVV code (3 or 4 digits). If CVV code is valid,
     * method returns CVV code as {@link String} and sets {@link CreditCardService#cvvCodeIsValid} to 'true',
     * otherwise it returns message about failure and sets {@link CreditCardService#cvvCodeIsValid} to 'false'.
     * @return {@link String} CVV code or failure message
     */
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

    /**
     * Method used for checking validity of credit card number according ot Lunh Algorithm. If card number is valid,
     * method returns card number as {@link String} and sets {@link CreditCardService#validAccordingLunhAlgorithm} to 'true',
     * otherwise returns failure message and sets {@link CreditCardService#validAccordingLunhAlgorithm} to 'false'.
     * @return {@link String} card number or failure message
     */
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

    /**
     * Method used for checking validity of credit card's expiry date (3 or 4 digits). If expiry date is valid,
     * method returns expiry date as {@link String} and sets {@link CreditCardService#expiryDateIsValid} to 'true',
     * otherwise it returns message about failure and sets {@link CreditCardService#expiryDateIsValid} to 'false'.
     * @return {@link String} expiry date or failure message
     */
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

    /**
     * Method used to encapsulate all validation methods
     * @param creditCard instance of {@link CreditCardModel}
     * @return 'true' if all methods passed successfully, if at least one failed returns 'false'.
     */
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
