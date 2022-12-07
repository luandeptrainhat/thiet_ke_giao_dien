package com.example.asm.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.asm.MainActivity;
import com.example.asm.R;
import com.example.asm.fragment.ThuChiFragment;
import com.example.asm.fragment.gioi_thieu_fragment;
import com.example.asm.fragment.thongke_fragment;
import com.google.android.material.navigation.NavigationView;

public class gioi_thieu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);
        Button btnVao = findViewById(R.id.btnVao);
        btnVao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gioi_thieu.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}