package com.kkokate.shoppingapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.kkokate.shoppingapp.R;
import com.kkokate.shoppingapp.model.Products;

import java.io.File;
import java.util.List;

public class ProductsListLayoutAdapter extends ArrayAdapter<Products> {
    private Context context;

    public ProductsListLayoutAdapter(@NonNull Context context, int resource, @NonNull List<Products> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=null;
        try{
            Products products = getItem(position);
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = mInflater.inflate(R.layout.list_products,parent,false);

            TextView priceText = view.findViewById(R.id.priceText);
            priceText.setText("$ "+products.getPrice().toString());

            TextView titleText = view.findViewById(R.id.titleText);
            titleText.setText(products.getTitle());

            TextView descText = view.findViewById(R.id.descriptionText);
            descText.setText(products.getCategory());

            ImageView image = view.findViewById(R.id.productsImage);
            Glide.with(context)
                    .load(products.getImage())
                    .into(image);
        }catch(Exception e){
            e.printStackTrace();
        }
        return view;
    }
}
