package com.example.newstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


public class About_Us extends AppCompatActivity {
    ImageView backbutton;
    String names[] = {
            "S.AS.A.Subhasinghe -10026407",
            "M.G.A. Mihiransi - 10026836",
            "K.J.P.S.D.De Silva -10027065",
            "W.A.T.Prabuddhika - 10026556",
            "W.K.F.T.Fernando - 10026175",
            "W.C.D.Weerasekara – 10026929"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        backbutton = (ImageView) findViewById(R.id.feedbackback);
        ListView simplelist = findViewById(R.id.list_view_about);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.aboutusitem,
                R.id.aboutname,
                names
        );
        simplelist.setAdapter(arrayAdapter);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(About_Us.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}