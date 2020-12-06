package com.example.insights;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>{

    ImageView currency;
    private List<Item> items;
    private ItemsListener itemsListener;

    public ItemsAdapter(List<Item> items, ItemsListener itemsListener) {
        this.items = items;
        this.itemsListener = itemsListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_items,
                        parent,
                        false

                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bindItem(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<Item> getSelectedItems(){
        List<Item> selectedItems = new ArrayList<>();
        for (Item item :items){
            if (item.isSelected){
                selectedItems.add(item);
            }
        }
        return selectedItems;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layoutItem;
        View viewBackground;
        RoundedImageView imageItem;
        TextView textName, textAboutItem;


        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layoutItems);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imageItem = itemView.findViewById(R.id.imageItems);
            textName = itemView.findViewById(R.id.textName);
            textAboutItem = itemView.findViewById(R.id.textAboutItem);
            //currency = itemView.findViewById(R.id.imageViewprice);

        }

        void bindItem (final Item item){
            imageItem.setImageResource(item.image);
            textName.setText(item.name);
            textAboutItem.setText(item.aboutItem);
            if (item.isSelected){
                viewBackground.setBackgroundResource(R.drawable.items_selected_background);

            }else {
                viewBackground.setBackgroundResource(R.drawable.items_background);

            }
            layoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.isSelected){
                        viewBackground.setBackgroundResource(R.drawable.items_background);
                        item.isSelected = false;
                        if (getSelectedItems().size()==0){
                            itemsListener.onItemAction(false);
                        }
                    }else {

                        viewBackground.setBackgroundResource(R.drawable.items_selected_background);
                        item.isSelected = true;
                        itemsListener.onItemAction(true);
                    }
                }
            });
        }
    }
}
