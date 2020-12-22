package com.example.cardvalidator;

import com.example.cardvalidator.cardUtil.CardValidator;
import com.example.cardvalidator.cardUtil.cards.VisaCardValidator;
import com.example.cardvalidator.model.CardDetail;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class VisaCardUnitTest {
    CardValidator cardValidator = new VisaCardValidator();
    CardDetail cardDetail = new CardDetail();

    @Before
    public void init() {
        cardValidator.setCardDetail(cardDetail);
    }

    @Test
    public void checkVisaCardNumberTest1() {
        cardDetail.setCardNumber("4461827385702139");
        assertTrue(cardValidator.belongsToThisIssuer(cardDetail.getCardNumber())
                && cardValidator.isValidCreditCardNumber());
    }

    @Test
    public void checkVisaCardNumberTest2() {
        cardDetail.setCardNumber("5227935428210119");
        assertFalse(cardValidator.belongsToThisIssuer(cardDetail.getCardNumber())
                && cardValidator.isValidCreditCardNumber());
    }

    @Test
    public void checkVisaCardNumberTest3() {
        cardDetail.setCardNumber("4024007131850397");
        assertTrue(cardValidator.belongsToThisIssuer(cardDetail.getCardNumber())
                && cardValidator.isValidCreditCardNumber());
    }

    @Test
    public void checkVisaCardNumberTest4() {
        cardDetail.setCardNumber("4929223753476777");
        assertTrue(cardValidator.belongsToThisIssuer(cardDetail.getCardNumber())
                && cardValidator.isValidCreditCardNumber());
    }
}