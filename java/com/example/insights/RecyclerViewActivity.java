package com.example.insights;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements ItemsListener{

    private Button buttonAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle);

        buttonAddToCart=findViewById(R.id.buttonAddToCart);

        RecyclerView itemsRecyclerView = findViewById(R.id.itemsRecyclerView);
        buttonAddToCart = (Button) findViewById(R.id.buttonAddToCart);
        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddToCartActivity();
            }
        });
        TextView currencyShop = findViewById(R.id.shopMoney);
        Intent intent = getIntent();
        String currency = intent.getStringExtra(MeniuActivity.currencyGlobal);
        currencyShop.setText(currency);

        List<Item> items = new ArrayList<>();

        Item bow = new Item();
        bow.image = R.drawable.bowtie;
        bow.name = "Bow Tie";
        bow.aboutItem = "A very elegant item of clothing to make your little pets look fancy.";
        items.add(bow);

        Item ears = new Item();
        ears.image = R.drawable.bunny_ears;
        ears.name = "Bunny Ears";
        ears.aboutItem = "Even though Halloween is over, these bunny ears will make your pet look adorable.";
        items.add(ears);

        Item cap = new Item();
        cap.image = R.drawable.cap;
        cap.name = "Cap";
        cap.aboutItem = "This baseball cap will make your pet look like your stereotypical baseball father.";
        items.add(cap);

        Item collar = new Item();
        collar.image = R.drawable.collar;
        collar.name = "Collar";
        collar.aboutItem = "What better accessory than a cute little collar for your doggy?";
        items.add(collar);

        Item hat = new Item();
        hat.image = R.drawable.cowboy_hat;
        hat.name = "Cowboy Hat";
        hat.aboutItem = "YEE HAW";
        items.add(hat);

        Item crown = new Item();
        crown.image = R.drawable.crown;
        crown.name = "Crown";
        crown.aboutItem = "We can't forget about our little princes and princesses. Here's a crown for you guys.";
        items.add(crown);

        Item mask = new Item();
        mask.image = R.drawable.mask;
        mask.name = "Mask";
        mask.aboutItem = "Going to a masquerade? Don't forget your disguise!";
        items.add(mask);

        Item monocle = new Item();
        monocle.image = R.drawable.monocle;
        monocle.name = "Monocle";
        monocle.aboutItem = "A fancy, boujee, vintage monocle for all our antique lovers.";
        items.add(monocle);

        Item moustache = new Item();
        moustache.image = R.drawable.moustache;
        moustache.name = "Moustache";
        moustache.aboutItem = "A neat moustache to make your pet look misterious and mature.";
        items.add(moustache);

        Item tophat = new Item();
        tophat.image = R.drawable.top_hat;
        tophat.name = "Top Hat";
        tophat.aboutItem = "Preparing for your next magic show? Don't forget your hat!";
        items.add(tophat);

        final ItemsAdapter itemsAdapter = new ItemsAdapter(items, this);
        itemsRecyclerView.setAdapter(itemsAdapter);

        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Item> selectedItems = itemsAdapter.getSelectedItems();

                StringBuilder itemNames = new StringBuilder();
                for (int i=1; i < selectedItems.size();i++){
                    if (i==0){
                        itemNames.append(selectedItems.get(i).name);
                    }else {
                        itemNames.append("\n").append(selectedItems.get(i).name);

                    }
                }
                Toast.makeText(RecyclerViewActivity.this, itemNames.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void openAddToCartActivity(){
        Intent intent = new Intent(this, AddToCartActivity.class);
        startActivity(intent);

    }


    @Override
    public void onItemAction(Boolean isSelected) {

        if (isSelected){
            buttonAddToCart.setVisibility(View.VISIBLE);
        }else {
            buttonAddToCart.setVisibility(View.GONE);
        }

    }
}
