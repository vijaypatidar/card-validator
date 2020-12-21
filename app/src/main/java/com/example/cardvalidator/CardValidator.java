package com.example.cardvalidator;

import com.example.cardvalidator.model.CardDetail;

import java.util.Arrays;

public class CardValidator {

    public static final int INVALID_DATE = 1;
    public static final int INVALID_CARD_NUMBER = 2;
    public static final int INVALID_FIRST_NAME = 4;
    public static final int INVALID_LAST_NAME = 5;
    public static final int INVALID_CVV = 7;

    private final OnCardValidationListener listener;
    private CardDetail cardDetail;

    public CardValidator(OnCardValidationListener listener) {
        this.listener = listener;
    }

    public void checkCardDetail(CardDetail cardDetail) {
        this.cardDetail = cardDetail;
        boolean valid = true;
        if (cardDetail.getCardNumber().length() == 16) {
            if (!checkLuhn()) {
                listener.onInvalidCard(INVALID_CARD_NUMBER, "invalid card number");
                valid = false;
            }
        } else {
            listener.onInvalidCard(INVALID_CARD_NUMBER, "should contains on 16 digits");
            valid = false;
        }

        if (!isValidExpDate(cardDetail.getExpDate())) {
            listener.onInvalidCard(INVALID_DATE, "invalid expire date.");
            valid = false;
        }

        if (isInValidName(cardDetail.getFirstName())) {
            listener.onInvalidCard(INVALID_FIRST_NAME, "could only contain alphabetical and spaces");
            valid = false;
        }

        if (isInValidName(cardDetail.getLastName())) {
            listener.onInvalidCard(INVALID_LAST_NAME, "could only contain alphabetical and spaces");
            valid = false;
        }


        //notify that card detail is valid
        if (valid) listener.onCardValidated();
    }

    private boolean isValidExpDate(String expDate) {
        try {
            String[] split = expDate.split("/");
            int month = Integer.parseInt(split[0]);
            int year = Integer.parseInt(split[1]);
            if (month > 12) return false;
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    private boolean checkLuhn() {
        char[] num = cardDetail.getCardNumber().toCharArray();
        int[] ar = new int[16];
        for (int i = 0; i < 16; i += 2) {
            ar[i] = num[i] - '0';
        }
        for (int i = 1; i < 16; i += 2) {
            ar[i] = sumDigit((num[i] - '0') * 2);
        }
        int sum = 0;
        for (int a : ar) {
            sum += a;
        }
        System.out.println("================================" + sum + Arrays.toString(ar));
        return sum % 10 == 2;
    }

    private boolean isInValidName(String name) {
        for (char ch : name.toCharArray()) {
            if (ch < 'A' || ch > 'z') {
                return true;
            }
        }
        return name.isEmpty();
    }

    private int sumDigit(int n) {
        int res = 0;
        while (n != 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }


    public interface OnCardValidationListener {
        void onCardValidated();

        void onInvalidCard(int code, String msg);
    }
}
