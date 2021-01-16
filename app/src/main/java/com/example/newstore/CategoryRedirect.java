package com.example.newstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryRedirect extends AppCompatActivity {
    private ImageView underconbtn;
    private String catogoryname = "";
    private TextView betatxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_redirect);
        Toast.makeText(this, "This App is the beta version of SH Store!", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "You can  get full experience in our final release!", Toast.LENGTH_LONG).show();
        underconbtn = (ImageView) findViewById(R.id.underconback);
        catogoryname = getIntent().getStringExtra("category");
        betatxt = (TextView) findViewById(R.id.underconbetatxt);
        betatxt.setText(catogoryname);
        underconbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryRedirect.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}