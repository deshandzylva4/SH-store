package com.example.newstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminApppageUnderConstructon extends AppCompatActivity {
    private ImageView backimageadmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_apppage_under_constructon);
        Toast.makeText(this, "This App is the beta version of SH Store!", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "You can  get full experience in our final release!", Toast.LENGTH_LONG).show();
        backimageadmin = (ImageView) findViewById(R.id.underconbackadmin);
        backimageadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AdminCatogories.class);
                startActivity(intent);
            }
        });
    }
}