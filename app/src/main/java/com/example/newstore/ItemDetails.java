package com.example.newstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.newstore.modleclasses.Products;
import com.example.newstore.prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ItemDetails extends AppCompatActivity {
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productprice,productDescription, productName;
    private String productID;
    private Button addToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        productID = getIntent().getStringExtra("productidenty");

//        addtocartbtn = (FloatingActionButton) findViewById(R.id.product_details_cart_button);
        addToCart = (Button) findViewById(R.id.addtocartbtn);
        numberButton = (ElegantNumberButton) findViewById(R.id.numberbtn);
        productImage = (ImageView) findViewById(R.id.imageViewitem);
        productprice = (TextView) findViewById(R.id.productpriceitem);
        productDescription = (TextView) findViewById(R.id.productdescriptionitem);
        productName = (TextView) findViewById(R.id.productnameitem);
        getProductDetails(productID);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingTocartList();
            }
        });
    }

    private void addingTocartList() {
        String saveCurrentTime,savecurrentDate;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("MMM dd, yyyy");
        savecurrentDate = currentdate.format(calendar.getTime());

        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentdate.format(calendar.getTime());

        DatabaseReference cartlistRef = FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String,Object> cartMap  =new HashMap<>();
        cartMap.put("pid",productID);
        cartMap.put("pname",productName.getText().toString());
        cartMap.put("date",savecurrentDate);
        cartMap.put("time",saveCurrentTime);
        cartMap.put("price",productprice.getText().toString());
        cartMap.put("quantity",numberButton.getNumber());
        cartlistRef.child("User View").child(Prevalent.onlineUser.getPhone()).child("Products")
                .child(productID).updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ItemDetails.this, "Added to cart successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ItemDetails.this,HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    private void getProductDetails(String productID)
    {
        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("products");
        productRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    Products products = dataSnapshot.getValue(Products.class);
                    productName.setText(products.getPname());
                    productprice.setText("Rs. "+ products.getPrice());
                    productDescription.setText(products.getDescription());
                    Picasso.get().load(products.getImage()).into(productImage);
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError)
            {

            }
        });
    }
}