package com.example.cardvalidator.cardUtil;

import com.example.cardvalidator.cardUtil.cards.AmericanExpressCardValidator;
import com.example.cardvalidator.cardUtil.cards.DiscoverCardValidator;
import com.example.cardvalidator.cardUtil.cards.MasterCardValidator;
import com.example.cardvalidator.cardUtil.cards.RupayCardValidator;
import com.example.cardvalidator.cardUtil.cards.VisaCardValidator;
import com.example.cardvalidator.model.CardDetail;

import java.util.ArrayList;

public class CardValidatorHelper {

    public static final int INVALID_DATE = 1;
    public static final int INVALID_CARD_NUMBER = 2;
    public static final int INVALID_FIRST_NAME = 4;
    public static final int INVALID_LAST_NAME = 5;
    public static final int INVALID_CVV = 7;

    private final OnCardValidationListener listener;
    private CardDetail cardDetail;
    private final ArrayList<CardValidator> cardValidators;

    public CardValidatorHelper(OnCardValidationListener listener) {
        this.listener = listener;
        cardValidators = new ArrayList<>();

        cardValidators.add(new MasterCardValidator());
        cardValidators.add(new VisaCardValidator());
        cardValidators.add(new AmericanExpressCardValidator());
        cardValidators.add(new DiscoverCardValidator());
        cardValidators.add(new RupayCardValidator());
    }

    public void checkCardDetail(CardDetail cardDetail) {
        this.cardDetail = cardDetail;
        boolean valid = true;

        CardValidator cardValidator = getIssuerCardValidator();

        if (cardValidator != null) {
            //set card detail
            cardValidator.setCardDetail(cardDetail);

            if (!cardValidator.isValidExpDate()) {
                listener.onInvalidCard(INVALID_DATE, "Invalid expire date.");
                valid = false;
            } else if (cardValidator.isExpired()) {
                listener.onInvalidCard(INVALID_DATE, "Card expired.");
                valid = false;
            }

            if (!cardValidator.isValidFirstName()) {
                listener.onInvalidCard(INVALID_FIRST_NAME, "could contain only alphabetical and spaces");
                valid = false;
            }

            if (!cardValidator.isValidLastName()) {
                listener.onInvalidCard(INVALID_LAST_NAME, "could contain only alphabetical and spaces");
                valid = false;
            }
            if (!cardValidator.isValidCVVNumber()) {
                listener.onInvalidCard(INVALID_CVV, "invalid CVV code");
                valid = false;
            }

            if (!cardValidator.isValidCard() || !cardValidator.isValidCreditCardNumber()) {
                listener.onInvalidCard(INVALID_CARD_NUMBER, "invalid card");
                valid = false;
            }

            //notify that card detail is valid
            if (valid) listener.onCardValidated();

        } else {
            listener.onInvalidCard(-1, "Card not supported");
        }

    }

    public CardValidator getIssuerCardValidator() {
        for (CardValidator cardValidator : cardValidators) {
            if (cardValidator.belongsToThisIssuer(cardDetail.getCardNumber())) {
                return cardValidator;
            }
        }
        return null;
    }

    public void checkCardIssuer(String number) {
        for (CardValidator cardValidator : cardValidators) {
            if (cardValidator.belongsToThisIssuer(number)) {
                listener.onCardIssuerListener(cardValidator.getName());
                return;
            }
        }
        //can be valid Rupay or other card
        listener.onCardIssuerListener(null);

    }

    public interface OnCardValidationListener {
        void onCardValidated();

        void onInvalidCard(int code, String msg);

        void onCardIssuerListener(String issuer);
    }
}
