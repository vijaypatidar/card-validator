package com.example.cardvalidator.cardUtil.cards;

import com.example.cardvalidator.cardUtil.CardValidator;

public class VisaCardValidator extends CardValidator {
    @Override
    public boolean isValidCard() {
        return belongsToThisIssuer(cardDetail.getCardNumber());
    }

    @Override
    public String getName() {
        return "Visa Card";
    }

    @Override
    public boolean belongsToThisIssuer(String number) {
        //IIN ranges = 4
        return number.startsWith("4");
    }
}
