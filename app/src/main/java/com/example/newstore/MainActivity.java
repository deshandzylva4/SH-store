package com.example.newstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newstore.modleclasses.Users;
import com.example.newstore.prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;


public class MainActivity extends AppCompatActivity {
    private String maindbuser = "Users";
    Button login_button,join_now_button;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_button = (Button) findViewById(R.id.main_login);
        join_now_button = (Button) findViewById(R.id.main_join_now);
        Paper.init(this);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_intet = new Intent(getApplicationContext(), loginActivity.class);
                startActivity(login_intet);
            }
        });
        join_now_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent join_now_intent = new Intent(getApplicationContext(),Join_now.class);
                startActivity(join_now_intent);
            }
        });
        //using paper library to read the values that user enterd.
        String UserPhone = Paper.book().read(Prevalent.userPhoneKey);
        String password = Paper.book().read(Prevalent.userPasswordKey);
        if (UserPhone != "" && password != "")
        {
            //double checking parameters
            if (!TextUtils.isEmpty(UserPhone) && !TextUtils.isEmpty(password))
            {
                AllowAccess(UserPhone,password);
                loadingbar.setTitle("Please wait...");
                loadingbar.setMessage("Already logged in using credentials you entered before");
                loadingbar.show();
            }
        }
    }

    private void AllowAccess(String phone, String password)
    {

        loadingbar = new ProgressDialog(this);
        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(maindbuser).child(phone).exists())
                {
                    Users userdata = snapshot.child(maindbuser).child(phone).getValue(Users.class);
                    if(userdata.getPhone().equals(phone))
                    {
                        if(userdata.getPassword().equals(password))
                        {
                            loadingbar.dismiss();
                            Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            Intent login_intent = new Intent(getApplicationContext(),HomeActivity.class);
                            Prevalent.onlineUser = userdata;
                            startActivity(login_intent);

                        }
                        else
                        {
                            loadingbar.dismiss();
                            Toast.makeText(MainActivity.this, "Name or password incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Account not found", Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                    Toast.makeText(MainActivity.this, "Please try again using another number", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}