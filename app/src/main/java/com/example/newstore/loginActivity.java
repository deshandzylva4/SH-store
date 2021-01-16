package com.example.newstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.newstore.modleclasses.Users;
import com.example.newstore.prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class loginActivity extends AppCompatActivity {
    private EditText loginname, loginpassword;
    private Button LoginButton;
    private ProgressDialog loadingbar;
    private String maindbuser = "Users";
    private CheckBox checkBox;
    private TextView adminLink, notAdminLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginname = findViewById(R.id.phonenumber);
        loginpassword = findViewById(R.id.password);
        LoginButton = findViewById(R.id.redirectpage_login);
        loadingbar = new ProgressDialog(this);
        checkBox = findViewById(R.id.remindmebox);
        Paper.init(this);
        adminLink = (TextView) findViewById(R.id.imadmin);
        notAdminLink = (TextView)  findViewById(R.id.imNotAdmin);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        adminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maindbuser = "Admins";
                LoginButton.setText("Login Admin");
                adminLink.setVisibility(View.INVISIBLE);
                notAdminLink.setVisibility(View.VISIBLE);
                //TODO: Admin checkbox is not active in admin section right now


            }
        });
        notAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maindbuser = "Users";
                LoginButton.setText("login");
                adminLink.setVisibility(View.VISIBLE);
                notAdminLink.setVisibility(View.INVISIBLE);

            }
        });
    }

    private void loginUser()
    {
        String phone = loginname.getText().toString();
        String password = loginpassword.getText().toString();
        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please enter the phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter the password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingbar.setTitle("Login Account");
            loadingbar.setMessage("Please wait, while we are checking the credentials");
            loadingbar.show();
            allowAccesstoAccount(phone,password);
        }
    }

    private void allowAccesstoAccount(String phone, String password)
    {
        if(checkBox.isChecked())
        {
            Paper.book().write(Prevalent.userPhoneKey,phone);
            Paper.book().write(Prevalent.userPasswordKey,password);
        }
        DatabaseReference rootref;
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
                            if (maindbuser.equals("Admins"))
                            {
                                loadingbar.dismiss();
                                Intent adimin_login_intent = new Intent(getApplicationContext(), AdminCatogories.class);
                                startActivity(adimin_login_intent);
                                Toast.makeText(loginActivity.this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                            }
                            else if(maindbuser.equals("Users"))
                            {
                                loadingbar.dismiss();
                                Intent login_intent = new Intent(getApplicationContext(), HomeActivity.class);
                                Prevalent.onlineUser = userdata;
                                startActivity(login_intent);
                                Toast.makeText(loginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            loadingbar.dismiss();
                            Toast.makeText(loginActivity.this, "Name or password incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(loginActivity.this, "Account not found", Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                    Toast.makeText(loginActivity.this, "Please try again using another number", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}