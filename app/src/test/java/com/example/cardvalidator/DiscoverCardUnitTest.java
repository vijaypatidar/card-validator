package com.example.cardvalidator;

import com.example.cardvalidator.cardUtil.CardValidator;
import com.example.cardvalidator.cardUtil.cards.DiscoverCardValidator;
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
public class DiscoverCardUnitTest {

    CardValidator cardValidator = new DiscoverCardValidator();
    CardDetail cardDetail = new CardDetail();

    @Before
    public void init() {
        cardValidator.setCardDetail(cardDetail);
    }

    @Test
    public void checkDiscoverCardNumberTest1() {
        cardDetail.setCardNumber("6011308360242029");
        assertTrue(cardValidator.belongsToThisIssuer(cardDetail.getCardNumber())
                && cardValidator.isValidCreditCardNumber());
    }

    @Test
    public void checkDiscoverCardNumberTest2() {
        cardDetail.setCardNumber("6911017982812503");
        assertFalse(cardValidator.belongsToThisIssuer(cardDetail.getCardNumber())
                && cardValidator.isValidCreditCardNumber());
    }

    @Test
    public void checkDiscoverCardNumberTest3() {
        cardDetail.setCardNumber("6011463140461306");
        assertTrue(cardValidator.belongsToThisIssuer(cardDetail.getCardNumber())
                && cardValidator.isValidCreditCardNumber());
//        assertTrue(cardValidator.belongsToThisIssuer("6011463140461306"));
    }

    @Test
    public void checkDiscoverCardNumberTest4() {
        cardDetail.setCardNumber("6011017682812563");
        assertTrue(cardValidator.belongsToThisIssuer(cardDetail.getCardNumber())
                && cardValidator.isValidCreditCardNumber());
//        assertTrue(cardValidator.belongsToThisIssuer("6011017682812563"));
    }


}