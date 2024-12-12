package com.example.arapp3.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arapp3.Constant;
import com.example.arapp3.Fragment.CartFragment;
import com.example.arapp3.MainActivity;
import com.example.arapp3.Model.CartModel;
import com.example.arapp3.R;
import com.example.arapp3.Util.SqlLiteClass;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{


    List<CartModel> cartModelList;
    Context context;

    String TAG = "TAG";


    public CartAdapter(List<CartModel> cartModelList, Context context){
        this.cartModelList = cartModelList;
        this.context = context;
    }




    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CartModel cartModel = cartModelList.get(position);
        holder.name.setText(cartModel.getName());
        holder.price.setText(String.valueOf(cartModel.getPrice()));
        holder.quantity.setText(String.valueOf(cartModel.getQuantity()));
        holder.total.setText(String.valueOf(cartModel.getTotal()));

        String imageUrl = Constant.IMAGE_URL + cartModel.getFolderName() + "/" + cartModel.getImage();



        Picasso.with( context )
                .load( imageUrl)
                .placeholder( R.drawable.ic_launcher_background )
                .fit()
                .centerInside()
                .into( holder.image );


        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qnt = cartModel.getQuantity();
                qnt++;
                cartModel.setQuantity(qnt);
                holder.quantity.setText(String.valueOf(qnt));
                double price = cartModel.getPrice();
                double total = price * qnt;
                holder.total.setText(String.valueOf(total));

                boolean isUpdated = updateQuantity(cartModel.getCartID(), qnt, context, Constant.CART_DB_NAME, Constant.CART_TABLE_NAME);

                if (isUpdated){
                    Log.d(TAG, "onClick: " + "Quantity Updated");
                }
                else{
                    Log.d(TAG, "onClick: " + "Quantity Not Updated");
                }
                SetAllTotalPrice();
                updateCartIconText( context);
                notifyDataSetChanged();
            }
        });

        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qnt = cartModel.getQuantity();
                if (qnt > 1){
                    qnt--;
                    cartModel.setQuantity(qnt);
                    holder.quantity.setText(String.valueOf(qnt));
                    double price = cartModel.getPrice();
                    double total = price * qnt;
                    holder.total.setText(String.valueOf(total));

                    boolean isUpdated = updateQuantity(cartModel.getCartID(), qnt, context, Constant.CART_DB_NAME, Constant.CART_TABLE_NAME);

                    if (isUpdated){
                        Log.d(TAG, "onClick: " + "Quantity Updated");
                    }
                    else{
                        Log.d(TAG, "onClick: " + "Quantity Not Updated");
                    }
                    SetAllTotalPrice();
                    updateCartIconText( context);

                    notifyDataSetChanged();
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartModelList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartModelList.size());

                boolean isDeleted = deleteItem(String.valueOf(cartModel.getCartID()), context);
                if (isDeleted){
                    Log.d(TAG, "onClick: " + "Item Deleted");
                }
                else{
                    Log.d(TAG, "onClick: " + "Item Not Deleted");
                }
                SetAllTotalPrice();
                updateCartIconText( context);

                notifyDataSetChanged();

            }
        });



    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, price, quantity, total;
        public ImageButton increase, decrease;
        public Button delete;

        public ImageView image;
        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            name = itemView.findViewById(R.id.recpname);
            price = itemView.findViewById(R.id.recprice);
            quantity = itemView.findViewById(R.id.recqnt);

            increase = itemView.findViewById(R.id.incbtn);
            decrease = itemView.findViewById(R.id.decbtn);

            total = itemView.findViewById(R.id.totaleItemPrice);
            delete = itemView.findViewById(R.id.removeBtn);

            image = itemView.findViewById(R.id.saved_cart_item_image);



        }
    }


//    Sql Lite Quantity Update
    public boolean updateQuantity(int id, int quantity, Context context, String dbNAme, String tableName){
        SqlLiteClass sqlLiteClass = new SqlLiteClass(context, dbNAme);

        String ColumnName = "quantity";
        String ColumnId =  String.valueOf(id);
        String ColumnValue = String.valueOf(quantity);

        if (sqlLiteClass.updateQuantity(tableName, ColumnName, ColumnValue, ColumnId)){
            return true;
        }



        return false;
    }


//    Sql Lite Delete
    public boolean deleteItem(String id, Context context) {

        SqlLiteClass sqlLiteClass = new SqlLiteClass(context, Constant.CART_DB_NAME);
        if (sqlLiteClass.deleteItem(Constant.CART_TABLE_NAME, id)) {
            return true;
        }
        return false;
    }

    // Cart Icon Text Update
    public void updateCartIconText(Context context){
        MainActivity.SqlUpdateCartCount(context);
    }


    public void SetAllTotalPrice(){
        double total = 0;
        for (int i = 0; i < cartModelList.size(); i++){
            total += cartModelList.get(i).getTotal();
        }


        CartFragment.updateTotalAmount(total);
    }


}
