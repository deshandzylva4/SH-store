package com.example.newstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Join_now extends AppCompatActivity {
    Button createAccount;
    EditText user_name,register_phone_number,register_password;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_now);
        createAccount = (Button) findViewById(R.id.create_account);
        user_name = (EditText) findViewById(R.id.register_name);
        register_password = (EditText) findViewById(R.id.register_password);
        register_phone_number = (EditText) findViewById(R.id.register_phone);
        loadingbar = new ProgressDialog(this);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser(user_name,register_password,register_phone_number);
            }
        });
    }

    private void registeruser(EditText user_name, EditText register_password, EditText register_phone_number) {
        String user = user_name.getText().toString();
        String password = register_password.getText().toString();
        String phone = register_phone_number.getText().toString();
        if (TextUtils.isEmpty(user))
        {
            Toast.makeText(this, "Please Enter the user name...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please Enter the Password...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please Enter the phone number", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingbar.setTitle("Create Account");
            loadingbar.setMessage("Please wait, while we are checking the credentials");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();
            validateUser(user,password,phone);
        }
    }

    private void validateUser(String user, String password, String phone)
    {
        DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(phone).exists()))
                {
                    Map<String,Object> userdataMap = new HashMap<>();
                    userdataMap.put("Phone",phone);
                    userdataMap.put("Password",password);
                    userdataMap.put("Name",user);
                    rootref.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(Join_now.this, "Your account is created successfully", Toast.LENGTH_SHORT).show();
                                        loadingbar.dismiss();
                                        Intent main_intet = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(main_intet);

                                    }
                                    else
                                    {
                                        loadingbar.dismiss();
                                        Toast.makeText(Join_now.this, "Network Error: Please try again...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(Join_now.this, "This Phone number already exists!", Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                    Toast.makeText(Join_now.this, "Please try again using another number!", Toast.LENGTH_LONG).show();
                    Intent main_intet = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main_intet);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}