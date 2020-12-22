package com.example.cardvalidator.cardUtil.cards;

import com.example.cardvalidator.cardUtil.CardValidator;

public class RupayCardValidator extends CardValidator {

    @Override
    public boolean isValidCard() {
        return belongsToThisIssuer(cardDetail.getCardNumber());
    }

    @Override
    public String getName() {
        return "Rupay Card";
    }

    @Override
    public boolean belongsToThisIssuer(String number) {
        //IIN range = 60, 6521, 6522
        return number.startsWith("60") || number.startsWith("6521") || number.startsWith("6522");
    }
}
