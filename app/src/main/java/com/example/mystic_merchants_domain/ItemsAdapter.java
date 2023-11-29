package com.example.mystic_merchants_domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//This portion of my code was assisted by the Android Development Website and ChatGPT.
//Was very unsure how to implement the recycler view.
//Some automatic generation and research was able to assist me in implementing this.

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>{
    private Context context;
    private List<Items> itemsList;

    public ItemsAdapter(Context context, List<Items> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public ItemsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Items items = itemsList.get(position);
        holder.textViewName.setText(items.name);
        holder.textViewDescription.setText(items.description);
        holder.textViewAttributes.setText(items.attributes);
        holder.textViewQuantity.setText(items.quantity);
        holder.textViewPrice.setText(items.price);

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewDescription, textViewAttributes,
                textViewQuantity, textViewPrice;
        public ItemViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.item_Name);
            textViewDescription = itemView.findViewById(R.id.item_Description);
            textViewAttributes = itemView.findViewById(R.id.item_Attributes);
            textViewQuantity = itemView.findViewById(R.id.item_Quantity);
            textViewPrice = itemView.findViewById(R.id.item_Price);
        }
    }
}
