package com.bi.cardioexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class MainActivity extends AppCompatActivity {

    private int MY_SCAN_REQUEST_CODE = 666;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
    }


    public void onScanPress(View v) {
        Intent scanIntent = new Intent(this, CardIOActivity.class);
        scanIntent.putExtra(CardIOActivity.EXTRA_USE_PAYPAL_ACTIONBAR_ICON, false); // default: false
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_SCAN_REQUEST_CODE) {
            String resultDisplayStr;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
                resultDisplayStr = "Card Number: " + scanResult.getFormattedCardNumber() + "\n";
            }
            else {
                resultDisplayStr = "Scan was canceled.";
            }
            textView.setText(resultDisplayStr);
        }
    }

}