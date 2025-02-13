package com.example.newstore.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newstore.Interface.ItemClickListner;
import com.example.newstore.R;

import org.jetbrains.annotations.NotNull;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtproduct_name, txtProductDescription, txtProductPrice;
    public ImageView imageView;
    public ItemClickListner listner;

    public ProductViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.product_image);
        txtproduct_name = (TextView) itemView.findViewById(R.id.product_name);
        txtProductDescription = (TextView) itemView.findViewById(R.id.product_description);
        txtProductPrice = (TextView) itemView.findViewById(R.id.product_Price);
    }
    public void setItemClickListener(ItemClickListner listener)
    {
        this.listner = listener;
    }

    @Override
    public void onClick(View v)
    {
        listner.onClick(v,getAdapterPosition(),false);
    }
}
