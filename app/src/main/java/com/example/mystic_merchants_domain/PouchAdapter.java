package com.example.mystic_merchants_domain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import java.util.List;

//Assisted by AI help to implement a RecyclerView instead of a ListView like I was doing beforehand.
//Helped in solving some issues I was facing and helped me resolve some of the syntax that I was incorrect about
public class PouchAdapter extends RecyclerView.Adapter<PouchAdapter.ViewHolder> {
    private List<Pouch> pouchItems;

    public PouchAdapter(List<Pouch> pouchItems) {
        this.pouchItems = pouchItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pouch_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pouch item = pouchItems.get(position);
        holder.textViewItemName.setText(String.valueOf(item.getItemId()));
        holder.textViewItemQuantity.setText("Quantity: " + item.getQuantity());
    }

    @Override
    public int getItemCount() {
        return pouchItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItemName;
        TextView textViewItemQuantity;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewItemName = itemView.findViewById(R.id.textView_ItemName);
            textViewItemQuantity = itemView.findViewById(R.id.textView_ItemQuantity);
        }
    }
}
