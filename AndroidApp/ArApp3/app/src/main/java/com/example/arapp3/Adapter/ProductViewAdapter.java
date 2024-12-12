package com.example.arapp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arapp3.Activity.ArViewActivity;
import com.example.arapp3.Activity.TestActivity;
import com.example.arapp3.Constant;
import com.example.arapp3.Fragment.HomeFragment;
import com.example.arapp3.MainActivity;
import com.example.arapp3.Model.ProductList;
import com.example.arapp3.R;
import com.example.arapp3.Util.SqlLiteClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ViewHolder> {

    List<ProductList> productList;
    Context context;

    SqlLiteClass sqlLiteClass;

    String TABLE_NAME = "Cart";

    public ProductViewAdapter(List<ProductList> productList, Context context) {
        this.productList = productList;
        this.context = context;
        this.sqlLiteClass = new SqlLiteClass(context, "CartDB");
        sqlLiteClass.createTable(TABLE_NAME, "id INTEGER PRIMARY KEY AUTOINCREMENT, product_id INTEGER UNIQUE, name VARCHAR, price VARCHAR, description VARCHAR, image VARCHAR, quantity INTEGER");
    }

    @NonNull
    @Override
    public ProductViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewAdapter.ViewHolder holder, int position) {
        ProductList product = productList.get(position);
        holder.name.setText(product.getName());
        holder.description.setText(product.getDescription());
        holder.price.setText(product.getPrice());

        String imageUrl = Constant.IMAGE_URL  + product.getFolderName() + "/" + product.getImage();
        Log.d("TAG", "Image URL: " + imageUrl);
        Picasso.with( context )
                .load( imageUrl )
                .placeholder( R.drawable.ic_launcher_background )
                .fit()
                .centerInside()
                .into( holder.image );


        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
          ;

                String newColumns = "product_id, name, price, description, image, folderName, quantity";
                List<String> data = new ArrayList<>();
                data.add(String.valueOf(product.getId()));
                data.add(product.getName());
                data.add(product.getPrice());
                data.add(product.getDescription());
                data.add(product.getImage());
                data.add(product.getFolderName());
                data.add("1");

                sqlLiteClass.insertData(TABLE_NAME, newColumns, data);

                updateCartIconText(context);
            }
        });

        holder.btnViewToAr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                try {
                    Intent intent = new Intent(context, ArViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("folderName", product.getFolderName());
                    bundle.putString("model", product.getModel());

                    intent.putExtras(bundle);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } catch (Exception e) {
                    Log.d("Error", e.toString());
                }















            }
        });



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name, description, model, price;
        public Button btnAddToCart, btnViewToAr;
        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_name);
            description = itemView.findViewById(R.id.product_description);
            price = itemView.findViewById(R.id.product_price);
            btnAddToCart = itemView.findViewById(R.id.btn_add_to_cart);
            btnViewToAr = itemView.findViewById(R.id.btn_view_to_ar);



        }
    }


    public void updateCartIconText(Context context){
        MainActivity.SqlUpdateCartCount(context);
    }
}
