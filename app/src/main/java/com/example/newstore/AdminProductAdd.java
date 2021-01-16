package com.example.newstore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class AdminProductAdd extends AppCompatActivity {
    private String catogory_name,pDescription,pPrice,pName, savedate,savetime;
    private Button AddNewProduct;
    private ImageView addNewProduct;
    private EditText itemName ,itemPrice ,itemDescription;
    private static final int GalleryPick = 1;
    private Uri imageUri;
    private String productRandomKey,downloadImageUrl;
    private StorageReference storeimgref;
    private DatabaseReference productreference;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_add);
        loadingbar = new ProgressDialog(this);
        productreference = FirebaseDatabase.getInstance().getReference().child("products");
        storeimgref = FirebaseStorage.getInstance().getReference().child("Item Images");
        catogory_name = getIntent().getExtras().get("category").toString();
        AddNewProduct = (Button) findViewById(R.id.submitFeedback);
        AddNewProduct.setText("Add a "+catogory_name);
        addNewProduct = (ImageView) findViewById(R.id.addImage);
        itemName = (EditText) findViewById(R.id.itemName);
        itemPrice = (EditText) findViewById(R.id.itemPrice);
        itemDescription = (EditText) findViewById(R.id.feedbackmessage);
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        AddNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateItemdata();
            }
        });

    }

    private void validateItemdata()
    {
        pDescription = itemDescription.getText().toString();
        pPrice = itemPrice.getText().toString();
        pName = itemName.getText().toString();
        if(imageUri == null)
        {
            Toast.makeText(this, "Product image is required!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pDescription))
        {
            Toast.makeText(this, "Product Description is reqired!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pPrice))
        {
            Toast.makeText(this, "Product Price is reqired!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pName))
        {
            Toast.makeText(this, "Product Name is reqired!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            productInformation();
        }

    }

    private void productInformation()
    {
        loadingbar.setTitle("Add New Item");
        loadingbar.setMessage("Please wait, while we are adding new item...");
        loadingbar.show();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentdate  =new SimpleDateFormat("MMM dd, yyyy");
        savedate = currentdate.format(calendar.getTime());
        SimpleDateFormat currentTime  =new SimpleDateFormat("HH:mm:ss a");
        savetime = currentTime.format(calendar.getTime());
        productRandomKey = savedate+savetime;
        StorageReference filepath = storeimgref.child(imageUri.getLastPathSegment() + productRandomKey);
        final UploadTask uploadTask = filepath.putFile(imageUri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loadingbar.dismiss();
               String message = e.toString();
                Toast.makeText(AdminProductAdd.this, "Error:"+message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AdminProductAdd.this, "Item image Uploaded successfully", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();

                        }
                        downloadImageUrl = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();
                            Toast.makeText(AdminProductAdd.this, "Getting product image Url is successful", Toast.LENGTH_SHORT).show();
                            saveProductInfoTodataBase();
                        }
                    }
                });
            }
        });
    }

    private void saveProductInfoTodataBase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("pid",productRandomKey);
        productMap.put("date",savedate);
        productMap.put("time",savetime);
        productMap.put("description",pDescription);
        productMap.put("image",downloadImageUrl);
        productMap.put("category",catogory_name);
        productMap.put("price",pPrice);
        productMap.put("pname",pName);
        productreference.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Intent login_intent = new Intent(getApplicationContext(), AdminCatogories.class);
                            startActivity(login_intent);
                            loadingbar.dismiss();
                            Toast.makeText(AdminProductAdd.this, "Item is added successfully...", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingbar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AdminProductAdd.this, "Error: "+message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void openGallery()
    {
        Intent opengallery = new Intent();
        opengallery.setAction(Intent.ACTION_GET_CONTENT);
        opengallery.setType("image/*");
        startActivityForResult(opengallery,GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GalleryPick && resultCode == RESULT_OK && data!=null)
        {
            imageUri = data.getData();
            addNewProduct.setImageURI(imageUri);

        }
    }
}