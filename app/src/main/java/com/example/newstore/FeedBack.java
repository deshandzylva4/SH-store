package com.example.newstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class FeedBack extends AppCompatActivity {
    private EditText feedbackTitle,feedbackMessage;
    private Button submitfeedBack;
    private ImageView backbutton;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        backbutton = findViewById(R.id.feedbackback);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedBack.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        String to = "studentname@students.nsbm.lk";
        feedbackTitle = (EditText) findViewById(R.id.feedbackTitle);
        feedbackMessage = (EditText) findViewById(R.id.feedbackmessage);
        submitfeedBack = (Button) findViewById(R.id.submitFeedback);
        submitfeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{ to});
                intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback For SH Store App");
                intent.putExtra(Intent.EXTRA_TEXT,"Title: "+feedbackTitle.getText().toString()+"\n\nMessage: "+feedbackMessage.getText().toString());
                try {
                    startActivity(Intent.createChooser(intent,"Please select Email"));
                }
                catch (android.content.ActivityNotFoundException e)
                {
                    Toast.makeText(FeedBack.this, "Email app not found!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}