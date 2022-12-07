package com.example.asm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm.R;

public class Login extends AppCompatActivity {
    String user = "user";
    String pass="admin";
    EditText edtUser, edtPass;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtPass = findViewById(R.id.password);
        edtUser = findViewById(R.id.username);
        btnLogin= findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUser.getText().toString().equals(user)&& edtPass.getText().toString().equals(pass)) {
                    Intent intent = new Intent(Login.this, gioi_thieu.class);
                    startActivity(intent);
                } else if (edtUser.equals("")) {
                    Toast.makeText(Login.this, "Không được để trống tài khoản", Toast.LENGTH_SHORT).show();
                } else if (edtPass.equals("")) {
                    Toast.makeText(Login.this, "Không được để trống mật khẩu", Toast.LENGTH_SHORT).show();
                }else  {
                    Toast.makeText(Login.this, "Sai tài khoản hoặc mật khẩu "+edtPass.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}