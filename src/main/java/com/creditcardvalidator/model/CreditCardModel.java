package com.creditcardvalidator.model;

public class CreditCardModel {

    private String expiryDate; // must be after present time , format MM/yyyy
    private String cvvCode;
    private String cardNumber;

    public CreditCardModel() {}

    public CreditCardModel(String cardNumber, String expiryDate, String cvvCode) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvvCode = cvvCode;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
