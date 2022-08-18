package com.kkokate.shoppingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kkokate.shoppingapp.R;

import java.util.List;

public class CategoryListLayoutAdapter extends ArrayAdapter<String> {

    private Context context;
    private int resource;
    public CategoryListLayoutAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=null;
        try{
            String str = getItem(position);
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = mInflater.inflate(R.layout.text_for_list_view,parent,false);

            TextView priceText = view.findViewById(R.id.category);
            priceText.setText(str);

        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }
}
