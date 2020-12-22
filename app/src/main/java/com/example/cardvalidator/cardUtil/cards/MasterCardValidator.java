package com.example.cardvalidator.cardUtil.cards;

import com.example.cardvalidator.cardUtil.CardValidator;

public class MasterCardValidator extends CardValidator {

    @Override
    public boolean isValidCard() {
        return belongsToThisIssuer(cardDetail.getCardNumber());
    }

    @Override
    public String getName() {
        return "Master Card";
    }

    @Override
    public boolean belongsToThisIssuer(String number) {
        //IIN ranges = 2221-2720 , 51–55
        try {
            int iin = Integer.parseInt(number.substring(0, 2));
            if (iin >= 51 && iin <= 55) return true;
            iin = Integer.parseInt(number.substring(0, 4));
            return iin >= 2221 && iin <= 2720;
        } catch (Exception ignored) {

        }
        return false;
    }
}
