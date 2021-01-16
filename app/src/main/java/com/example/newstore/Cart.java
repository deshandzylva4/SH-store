package com.example.newstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newstore.ViewHolder.CartViewHolder;
import com.example.newstore.modleclasses.ModleCart;
import com.example.newstore.prevalent.Prevalent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class Cart extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button payNow;
    private TextView totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        payNow = (Button) findViewById(R.id.payNowbtn);
        totalAmount = (TextView) findViewById(R.id.totalprice);
        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this,PayActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        final DatabaseReference cart_lsi_ref = FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<ModleCart> options =
                new FirebaseRecyclerOptions.Builder<ModleCart>()
                .setQuery(cart_lsi_ref.child("User View").child(Prevalent.onlineUser.getPhone()).child("Products"),
                        ModleCart.class).build();

        FirebaseRecyclerAdapter<ModleCart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<ModleCart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull CartViewHolder cartViewHolder, int i, @NonNull @NotNull ModleCart modleCart) {
               cartViewHolder.productquantity.setText("Quantity: "+modleCart.getQuantity());
               cartViewHolder.productPrice.setText("Total Price: "+modleCart.getPrice());
               cartViewHolder.productName.setText("Name: "+modleCart.getPname());
               cartViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       CharSequence options[] = new CharSequence[]
                               {
                                    "Edit",
                                    "Delete"
                               };
                       AlertDialog.Builder builder = new AlertDialog.Builder(Cart.this);
                       builder.setTitle("Cart Options: ");
                       builder.setItems(options, new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which)
                           {
                                if(which == 0)
                                {
                                    Intent intent = new Intent(Cart.this,ItemDetails.class);
                                    intent.putExtra("productidenty",modleCart.getPid());
                                    startActivity(intent);
                                }
                                if(which == 1)
                                {
                                    cart_lsi_ref.child("User View").child(Prevalent.onlineUser.getPhone())
                                            .child("Products").child(modleCart.getPid()).removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull @NotNull Task<Void> task)
                                                {
                                                    if(task.isSuccessful())
                                                    {
                                                        Toast.makeText(Cart.this, "Product deleted successfully", Toast.LENGTH_SHORT).show();

                                                    }
                                                }
                                            });
                                }
                           }
                       });
                       builder.show();
                   }
               });

            }

            @NonNull
            @NotNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_single_item_layout,parent,false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}