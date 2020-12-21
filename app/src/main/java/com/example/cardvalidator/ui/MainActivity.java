package com.example.cardvalidator.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import com.example.cardvalidator.CardValidator;
import com.example.cardvalidator.R;
import com.example.cardvalidator.model.CardDetail;

public class MainActivity extends AppCompatActivity implements CardValidator.OnCardValidationListener {

    AppCompatEditText editCardNumber;
    AppCompatEditText editCvvCode;
    AppCompatEditText editFirstName;
    AppCompatEditText editLastName;
    AppCompatEditText editExpDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CardValidator cardValidator = new CardValidator(this);
        editCardNumber = findViewById(R.id.cardNumber);
        editCvvCode = findViewById(R.id.cvvCode);
        editFirstName = findViewById(R.id.firstName);
        editLastName = findViewById(R.id.lastName);
        editExpDate = findViewById(R.id.expDate);

        editExpDate.addTextChangedListener((MyTextWatcher) (s, start, before, count) -> {
            if (before == 0 && s.length() == 2) {
                editExpDate.setText(String.format("%s/", editExpDate.getText()));
                editExpDate.setSelection(3);
            } else if (s.length() == 5) {
                editCvvCode.requestFocus();
            } else if (s.length() > 5) {
                editExpDate.setText(s.subSequence(0, 5));
            }
        });

        editCardNumber.addTextChangedListener((MyTextWatcher) (s, start, before, count) -> {
            if (s.length() == 16) editExpDate.requestFocus();
            else if (s.length() > 16) {
                editCardNumber.setText(s.subSequence(0, 16));
            }
        });

        editCvvCode.addTextChangedListener((MyTextWatcher) (s, start, before, count) -> {
            if (s.length() == 3) editFirstName.requestFocus();
            else if (s.length() > 3) {
                editCvvCode.setText(s.subSequence(0, 3));
            }
        });


        findViewById(R.id.btnProceed).setOnClickListener(view1 -> {
            try {
                CardDetail cardDetail = new CardDetail();
                cardDetail.setCardNumber(editCardNumber.getText().toString());
                cardDetail.setCvv(Integer.parseInt(editCvvCode.getText().toString()));
                cardDetail.setExpDate(editExpDate.getText().toString());
                cardDetail.setFirstName(editFirstName.getText().toString().trim());
                cardDetail.setLastName(editLastName.getText().toString().trim());

                //verify card detail
                cardValidator.checkCardDetail(cardDetail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCardValidated() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Payment was successful.");
        ab.setPositiveButton("OK", null);
        ab.create().show();
    }

    @Override
    public void onInvalidCard(int code, String msg) {
        switch (code) {
            case CardValidator.INVALID_CARD_NUMBER:
                editCardNumber.setError(msg);
                break;
            case CardValidator.INVALID_LAST_NAME:
                editLastName.setError(msg);
                break;
            case CardValidator.INVALID_FIRST_NAME:
                editFirstName.setError(msg);
                break;
            case CardValidator.INVALID_CVV:
                editCvvCode.setError(msg);
                break;
            case CardValidator.INVALID_DATE:
                editExpDate.setError(msg);
                break;
        }
    }

    public interface MyTextWatcher extends TextWatcher {
        default void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        default void afterTextChanged(Editable s) {
        }
    }
}