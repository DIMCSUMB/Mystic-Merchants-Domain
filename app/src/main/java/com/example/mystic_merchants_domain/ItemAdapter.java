package com.example.mystic_merchants_domain;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Assisted by Android Development Website. Was confused how to use LayoutInflater
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listviewitems, parent, false);
        }

        //Retrieve item
        Item item = items.get(position);

        //Find the views in listviewitems.xml
        ImageView imageView = convertView.findViewById(R.id.item_ImageView);
        TextView textView = convertView.findViewById(R.id.item_TextView);

        String imageName = item.getImage();
        if (imageName.endsWith(".png")) {
            imageName = imageName.substring(0, imageName.length() - 4);
        }
        //Set item image
        int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        Log.d("ItemAdapter", "Resource ID for image: " + imageResId);
        imageView.setImageResource(imageResId);

        //Asked ChatGPT how I could bold only the name of the items and keep everything else the same
        SpannableString itemNameSpannable = new SpannableString(item.getName());
        itemNameSpannable.setSpan(new StyleSpan(Typeface.BOLD), 0, itemNameSpannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //Set item details
        String itemDetails =
                  item.getDescription() + "\n"
                + item.getAttributes() + "\n"
                + "Quantity: " + item.getQuantity() + "\n"
                + "Price: " + item.getPrice();
        textView.setText(itemNameSpannable);
        textView.append("\n" + itemDetails);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AddToPouchActivity with the selected item's details
                Intent intent = new Intent(context, AddToPouchActivity.class);
                intent.putExtra("SELECTED_ITEM_ID", item.getId());
                // Add other item details as needed
                context.startActivity(intent);
            }
        });

        //Return the completed view
        return convertView;
    }
}
