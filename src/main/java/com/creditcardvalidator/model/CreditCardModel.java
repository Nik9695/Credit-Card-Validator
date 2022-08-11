package com.creditcardvalidator.model;

/**
 * Class represents model of the credit card which need to be validated.
 */
public class CreditCardModel {

    private String expiryDate;
    private String cvvCode;
    private String cardNumber;

    /**
     * Default constructor, required only for proper work of annotation {@link org.springframework.web.bind.annotation.ModelAttribute}
     */

    public CreditCardModel() {}

    /**
     * Class constructor, used by {@link com.creditcardvalidator.controllers.CreditCardController}, taking all parameters from input on web page.
     * @param cardNumber PAN of the credit card
     * @param expiryDate expiry date of the credit card
     * @param cvvCode CVV code of the credit card
     */

    public CreditCardModel(String cardNumber, String expiryDate, String cvvCode) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvvCode = cvvCode;
    }

    /**
     *
     * @return expiry date as {@link String}
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Setting expiry date of the credit card
     * @param expiryDate expiry date
     */

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     *
     * @return CVV code as {@link String}
     */
    public String getCvvCode() {
        return cvvCode;
    }

    /**
     * Setting CVV code of the credit card
     * @param cvvCode CVV code of the card
     */

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    /**
     *
     * @return PAN of the card as {@link String}
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Setting PAN of the credit card
     * @param cardNumber PAN of the credit card
     */

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
