package com.example.asm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;


import com.example.asm.fragment.ThuChiFragment;
import com.example.asm.fragment.thongke_fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private DrawerLayout drawerLayout;

    private LinearLayout linearLayout;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        linearLayout = findViewById(R.id.linearLayout);
        navigationView = findViewById(R.id.navigation_view);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                Bundle bundle = new Bundle();
                switch (item.getItemId()) {
                    case R.id.menuthu:
                        fragment = new ThuChiFragment();
                        bundle.putInt("trangThai", 0);
                        fragment.setArguments(bundle);
                        break;
                    case R.id.menuchi:
                        fragment = new ThuChiFragment();
                        bundle.putInt("trangThai", 1);
                        fragment.setArguments(bundle);
                        break;
                    case R.id.menuthongKe:
                        fragment = new thongke_fragment();
//                        Toast.makeText(MainActivity.this, "có", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menugioiThieu:
                        break;
                    case R.id.menuthoat:
                        break;
                }

               FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}