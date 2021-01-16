package com.example.newstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminCatogories extends AppCompatActivity {
    private ImageView tshirts, shirts, jeans, shorts;
    private ImageView blouse,boots,belt,handbag;
    private ImageView jacket,cap,femaleshoes,skirt;
    private ImageView tie, watch, socks, frock;
    private Button admineditproduct, adminorders, adminback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_catogories);
        tshirts = (ImageView) findViewById(R.id.tshirts);
        shirts = (ImageView) findViewById(R.id.shirts);
        jeans = (ImageView) findViewById(R.id.jeans);
        shorts = (ImageView) findViewById(R.id.shorts);
        blouse = (ImageView) findViewById(R.id.blouse);
        boots = (ImageView) findViewById(R.id.boots);
        belt =(ImageView) findViewById(R.id.belt);
        handbag =(ImageView) findViewById(R.id.handbag);
        jacket = (ImageView) findViewById(R.id.jacket);
        cap = (ImageView) findViewById(R.id.cap);
        femaleshoes = (ImageView) findViewById(R.id.femaleshoes);
        skirt = (ImageView) findViewById(R.id.skirt);
        tie = (ImageView) findViewById(R.id.tie);
        watch = (ImageView) findViewById(R.id.watch);
        socks = (ImageView) findViewById(R.id.socks);
        frock = (ImageView) findViewById(R.id.frock);
        admineditproduct = (Button) findViewById(R.id.editproductsadmin);
        adminorders = (Button) findViewById(R.id.ordersadmin);
        adminback = (Button) findViewById(R.id.backAdmin);
        adminback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,loginActivity.class);
                startActivity(intent);
            }
        });
        admineditproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminApppageUnderConstructon.class);
                startActivity(intent);
            }
        });
        adminorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminApppageUnderConstructon.class);
                startActivity(intent);
            }
        });

        tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","T shirt");
                startActivity(intent);
            }
        });
        shirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Shirt");
                startActivity(intent);
            }
        });
        jeans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Jean");
                startActivity(intent);
            }
        });
        shorts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Short");
                startActivity(intent);
            }
        });
        blouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Blouse");
                startActivity(intent);
            }
        });
        boots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Male shoe");
                startActivity(intent);
            }
        });
        belt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Belt");
                startActivity(intent);
            }
        });
        handbag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Handbag");
                startActivity(intent);
            }
        });
        jacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Jacket");
                startActivity(intent);
            }
        });
        cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Cap");
                startActivity(intent);
            }
        });
        femaleshoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Female shoe");
                startActivity(intent);
            }
        });
        skirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Skirt");
                startActivity(intent);
            }
        });
        tie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Tie");
                startActivity(intent);
            }
        });
        skirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Skirt");
                startActivity(intent);
            }
        });
        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Watch");
                startActivity(intent);
            }
        });
        socks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Pair of Socks");
                startActivity(intent);
            }
        });
        frock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogories.this,AdminProductAdd.class);
                intent.putExtra("category","Frock");
                startActivity(intent);
            }
        });
    }
}