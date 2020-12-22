package com.example.cardvalidator;

import com.example.cardvalidator.cardUtil.CardValidator;
import com.example.cardvalidator.cardUtil.cards.MasterCardValidator;
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
public class MasterCardUnitTest {

    CardDetail cardDetail = new CardDetail();
    CardValidator cardValidator = new MasterCardValidator();

    @Before
    public void init() {
        cardValidator.setCardDetail(cardDetail);
    }

    @Test
    public void checkMasterCardNumberTest1() {
        cardDetail.setCardNumber("5179631532130040");
        assertTrue(cardValidator.belongsToThisIssuer(cardDetail.getCardNumber())
                && cardValidator.isValidCreditCardNumber());
    }

    @Test
    public void checkMasterCardNumberTest2() {
        cardDetail.setCardNumber("6011398621692609");
        assertFalse(cardValidator.belongsToThisIssuer(cardDetail.getCardNumber()) &&
                cardValidator.isValidCreditCardNumber());
    }

    @Test
    public void checkMasterCardNumberTest3() {
        cardDetail.setCardNumber("5336860130417943");
        assertTrue(cardValidator.belongsToThisIssuer(cardDetail.getCardNumber())
                && cardValidator.isValidCreditCardNumber());
    }

    @Test
    public void checkMasterCardNumberTest4() {
        cardDetail.setCardNumber("5227935428210119");
        assertTrue(cardValidator.belongsToThisIssuer(cardDetail.getCardNumber())
                && cardValidator.isValidCreditCardNumber());
    }


}