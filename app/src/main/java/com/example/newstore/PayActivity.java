package com.example.newstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PayActivity extends AppCompatActivity {
    private Button ProceedToPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ProceedToPay = (Button) findViewById(R.id.buttonpay);
        ProceedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayActivity.this,CategoryRedirect.class);
                intent.putExtra("category","Beta version : Payment Gateway");
                startActivity(intent);
            }
        });
    }
}