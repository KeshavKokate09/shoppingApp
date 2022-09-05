package com.kkokate.shoppingapp.adapter;

import android.content.Context;
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

import java.util.List;

public class HomePageProductListAdapter extends ArrayAdapter<Products> {
    protected Context context;
    public HomePageProductListAdapter(@NonNull Context context, int resource, @NonNull List<Products> objects) {
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

            view = mInflater.inflate(R.layout.list_products_horizontal,parent,false);

            TextView priceText = view.findViewById(R.id.priceTag);
            priceText.setText("$ "+products.getPrice().toString());

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
