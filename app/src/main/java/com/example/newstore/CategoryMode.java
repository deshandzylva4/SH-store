package com.example.newstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CategoryMode extends AppCompatActivity {
    private ImageView tshirts, shirts, jeans, shorts;
    private ImageView blouse,boots,belt,handbag;
    private ImageView jacket,cap,femaleshoes,skirt;
    private ImageView tie, watch, socks, frock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_mode);
        tshirts = (ImageView) findViewById(R.id.tshirts_user);
        shirts = (ImageView) findViewById(R.id.shirts_user);
        jeans = (ImageView) findViewById(R.id.jeans_user);
        shorts = (ImageView) findViewById(R.id.shorts_user);
        blouse = (ImageView) findViewById(R.id.blouse_user);
        boots = (ImageView) findViewById(R.id.boots_user);
        belt =(ImageView) findViewById(R.id.belt_user);
        handbag =(ImageView) findViewById(R.id.handbag_user);
        jacket = (ImageView) findViewById(R.id.jacket_user);
        cap = (ImageView) findViewById(R.id.cap_user);
        femaleshoes = (ImageView) findViewById(R.id.femaleshoes_user);
        skirt = (ImageView) findViewById(R.id.skirt_user);
        tie = (ImageView) findViewById(R.id.tie_user);
        watch = (ImageView) findViewById(R.id.watch_user);
        socks = (ImageView) findViewById(R.id.socks_user);
        frock = (ImageView) findViewById(R.id.frock_user);

        tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","T shirt");
                startActivity(intent);
            }
        });
        shirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Shirt");
                startActivity(intent);
            }
        });
        jeans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Jean");
                startActivity(intent);
            }
        });
        shorts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Short");
                startActivity(intent);
            }
        });
        blouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Blouse");
                startActivity(intent);
            }
        });
        boots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Male shoe");
                startActivity(intent);
            }
        });
        belt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Belt");
                startActivity(intent);
            }
        });
        handbag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Handbag");
                startActivity(intent);
            }
        });
        jacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Jacket");
                startActivity(intent);
            }
        });
        cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Cap");
                startActivity(intent);
            }
        });
        femaleshoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Female shoe");
                startActivity(intent);
            }
        });
        skirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Skirt");
                startActivity(intent);
            }
        });
        tie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Tie");
                startActivity(intent);
            }
        });
        skirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Skirt");
                startActivity(intent);
            }
        });
        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Watch");
                startActivity(intent);
            }
        });
        socks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Pair of Socks");
                startActivity(intent);
            }
        });
        frock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMode.this,CategoryRedirect.class);
                intent.putExtra("category","Frock");
                startActivity(intent);
            }
        });
    }
}