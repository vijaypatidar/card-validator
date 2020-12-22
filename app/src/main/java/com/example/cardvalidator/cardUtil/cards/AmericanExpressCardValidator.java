package com.example.cardvalidator.cardUtil.cards;

import com.example.cardvalidator.cardUtil.CardValidator;

public class AmericanExpressCardValidator extends CardValidator {
    @Override
    public boolean isValidCard() {
        return belongsToThisIssuer(cardDetail.getCardNumber());
    }

    @Override
    public String getName() {
        return "American Express";
    }

    @Override
    public boolean belongsToThisIssuer(String number) {
        //IIN Ranges 34, 37
        return number.startsWith("34") || number.startsWith("37");
    }
}
