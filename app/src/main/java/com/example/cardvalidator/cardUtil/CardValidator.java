package com.example.cardvalidator.cardUtil;

import com.example.cardvalidator.model.CardDetail;

import java.util.Calendar;
import java.util.Date;

public abstract class CardValidator {
    protected CardDetail cardDetail;

    public abstract boolean isValidCard();

    public boolean isValidCreditCardNumber() {
        char[] num = cardDetail.getCardNumber().toCharArray();
        int[] ar = new int[16];
        for (int i = 1; i < 16; i += 2) {
            ar[i] = num[i] - '0';
        }
        for (int i = 0; i < 15; i += 2) {
            ar[i] = sumDigit((num[i] - '0') * 2);
        }
        int sum = -1 * ar[15];
        for (int a : ar) {
            sum += a;
        }
        sum = sum * 9;
        return sum % 10 == ar[15];
    }

    private int sumDigit(int n) {
        int res = 0;
        while (n != 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }

    public boolean isValidFirstName() {
        for (char ch : cardDetail.getFirstName().toCharArray()) {
            if (ch < 'A' || ch > 'z') {
                return false;
            }
        }
        return !cardDetail.getFirstName().isEmpty();
    }

    public boolean isValidLastName() {
        for (char ch : cardDetail.getLastName().toCharArray()) {
            if (ch < 'A' || ch > 'z') {
                return false;
            }
        }
        return !cardDetail.getLastName().isEmpty();
    }

    public boolean isValidExpDate() {
        try {
            String[] split = cardDetail.getExpDate().split("/");
            int month = Integer.parseInt(split[0]);
            int year = Integer.parseInt(split[1]);
            if (month <= 12 && month >= 1) return true;
        } catch (Exception ignored) {

        }
        return false;
    }

    public boolean isExpired() {
        String[] split = cardDetail.getExpDate().split("/");
        int month = Integer.parseInt(split[0]);
        int year = Integer.parseInt(split[1]);
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2000 + year);
        instance.set(Calendar.MONTH, month);

        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        return now.compareTo(instance) > 0;
    }

    public abstract String getName();

    public abstract boolean belongsToThisIssuer(String number);

    public void setCardDetail(CardDetail cardDetail) {
        this.cardDetail = cardDetail;
    }
}
