package com.example.arapp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arapp3.Activity.TestActivity;
import com.example.arapp3.Fragment.AppDetailsFragment;
import com.example.arapp3.Fragment.ArViewFragment;
import com.example.arapp3.Fragment.CartFragment;
import com.example.arapp3.Fragment.HomeFragment;
import com.example.arapp3.Fragment.LoginFragment;
import com.example.arapp3.Fragment.OrderDetailsFragment;
import com.example.arapp3.Fragment.OrdersFragment;
import com.example.arapp3.Fragment.ProfileFragment;
import com.example.arapp3.Fragment.RegisterFragment;
import com.example.arapp3.Util.Session;
import com.example.arapp3.Util.SqlLiteClass;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    static Toolbar toolbar;

    Fragment fragment;

    Session session;

    TextView cartCount;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Log.d("TAG","MainActivity Started");



        session = new Session(this);



        navigationView = findViewById(R.id.navMenu);
        toolbar = findViewById(R.id.toolbar_id);
        drawerLayout = findViewById(R.id.drawer);







        if (!session.getLoggedIn()) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.iconmenu);
        } else {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.loginmenu);
        }

        setSupportActionBar(toolbar);


        toolbar.inflateMenu(R.menu.cartmenu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Log.d("TAG","Menu Clicked");
                return false;
            }
        });

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SqlLiteClass sqlLiteClass = new SqlLiteClass(MainActivity.this, Constant.CART_DB_NAME);
                int count = sqlLiteClass.numberOfRows(Constant.CART_TABLE_NAME);
                if (count==0){
//                    Toast.makeText(MainActivity.this, "Cart is Empty", Toast.LENGTH_SHORT).show();
                }
                
                else{
                    try{
                        Log.d("TAG","Toolbar Clicked");
                        Toast.makeText(MainActivity.this, "Cart Panel", Toast.LENGTH_SHORT).show();
                        fragment = new CartFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }



            }
        });



        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();




        fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.menu_home || item.getItemId()==R.id.nav_home || item.getItemId()==R.id.nav_products){
                    Toast.makeText(MainActivity.this, "Home Panel", Toast.LENGTH_SHORT).show();
                    fragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                if (item.getItemId()==R.id.login){
                    Toast.makeText(MainActivity.this, "Login Panel", Toast.LENGTH_SHORT).show();
                    fragment = new LoginFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if (item.getItemId()==R.id.register){
                    Toast.makeText(MainActivity.this, "Register Panel", Toast.LENGTH_SHORT).show();
                    fragment = new RegisterFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if (item.getItemId()==R.id.app_details){
                    Toast.makeText(MainActivity.this, "App Details", Toast.LENGTH_SHORT).show();
                    fragment = new AppDetailsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if (item.getItemId()==R.id.nav_logout){
                    Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                    session.setLoggedIn(false);
                    session.setUserId("");
                    navigationView.getMenu().clear();
                    navigationView.inflateMenu(R.menu.iconmenu);
                    fragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                if (item.getItemId()==R.id.nav_cart){

                    SqlLiteClass sqlLiteClass = new SqlLiteClass(MainActivity.this, Constant.CART_DB_NAME);
                    int count = sqlLiteClass.numberOfRows(Constant.CART_TABLE_NAME);
                    if (count==0){
                        Toast.makeText(MainActivity.this, "Cart is Empty", Toast.LENGTH_SHORT).show();
                        return false;
                    }


                    Toast.makeText(MainActivity.this, "Cart Panel", Toast.LENGTH_SHORT).show();
                    fragment = new CartFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                if (item.getItemId()==R.id.nav_orders){
                    Toast.makeText(MainActivity.this, "Orders Panel", Toast.LENGTH_SHORT).show();
                    fragment = new OrdersFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                if (item.getItemId()==R.id.nav_profile){
                    Toast.makeText(MainActivity.this, "Profile Panel", Toast.LENGTH_SHORT).show();
                    fragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }


                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cartmenu, menu);

        MenuItem menuItem = menu.findItem(R.id.cartMenuId);
        View actionView = menuItem.getActionView();

        TextView cartCount = actionView.findViewById(R.id.cart_badge);

        SqlUpdateCartCount(this);



        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG","Cart Clicked");
                Toast.makeText(MainActivity.this, "Cart Panel", Toast.LENGTH_SHORT).show();

                MenuItem menuItem = toolbar.getMenu().findItem(R.id.cartMenuId);
                View actionView = menuItem.getActionView();
                TextView cartCount = actionView.findViewById(R.id.cart_badge);
                if (cartCount.getText().toString().equals("0")){
                    Toast.makeText(MainActivity.this, "Cart is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                fragment = new CartFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
            }
        });


        return true;
    }

    public static void updateCartCount(int count) {
        MenuItem menuItem = toolbar.getMenu().findItem(R.id.cartMenuId);
        View actionView = menuItem.getActionView();
        TextView cartCount = actionView.findViewById(R.id.cart_badge);
        cartCount.setText(String.valueOf(count));
    }

    public  static void SqlUpdateCartCount(Context context) {
        SqlLiteClass sqlLiteClass = new SqlLiteClass(context, Constant.CART_DB_NAME);
        int count = sqlLiteClass.numberOfRows(Constant.CART_TABLE_NAME);
        updateCartCount(count);
    }

    @Override
    public  void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (currentFragment instanceof HomeFragment){
            super.onBackPressed();
        }else if (currentFragment instanceof ArViewFragment){
            fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
        }
        else if (currentFragment instanceof CartFragment){
            fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
        }
        else if (currentFragment instanceof OrdersFragment){
            fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
        }
        else if (currentFragment instanceof OrderDetailsFragment){
            fragment = new OrdersFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
        }
        else if (currentFragment instanceof ProfileFragment){
            fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
        }
        else if (currentFragment instanceof AppDetailsFragment){
            fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
        }
        else if (currentFragment instanceof LoginFragment){
            fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
        }
        else if (currentFragment instanceof RegisterFragment){
            fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
        }
    }











}