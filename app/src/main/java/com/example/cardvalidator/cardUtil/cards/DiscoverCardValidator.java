package com.example.cardvalidator.cardUtil.cards;

import com.example.cardvalidator.cardUtil.CardValidator;

public class DiscoverCardValidator extends CardValidator {

    @Override
    public boolean isValidCard() {

        return true;
    }

    @Override
    public String getName() {
        return "Discover Card";
    }

    @Override
    public boolean belongsToThisIssuer(String number) {
        try {
            //IIN ranges 6011, 622126 - 622925, 644, 645, 646, 647, 648, 649, 65
            if (number.startsWith("65") || number.startsWith("6011")) return true;
            int iin = Integer.parseInt(number.substring(0, 3));
            if (iin >= 644 && iin <= 649) return true;
            iin = Integer.parseInt(number.substring(0, 6));
            return iin >= 622126 && iin <= 622925;
        } catch (Exception ignored) {

        }
        return false;
    }
}
