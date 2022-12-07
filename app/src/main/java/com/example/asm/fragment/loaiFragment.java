package com.example.asm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm.R;
import com.example.asm.adapter.loaiAdapter;
import com.example.asm.dao.LoaiDAO;
import com.example.asm.model.Loai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class loaiFragment extends Fragment {
    private RecyclerView recyclerView ;
    private FloatingActionButton floatAdd;
    private LoaiDAO loaiDAO;
    private int trangThai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai, container, false);

        recyclerView = view.findViewById(R.id.recyclerLoai);
        floatAdd =view.findViewById(R.id.floatAdd);

        Bundle bundle= getArguments();
        trangThai=bundle.getInt("trangThai");

        loaiDAO= new LoaiDAO(getContext());
        getDS();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAdd();
            }
        });

        return view;
    }

    private void showDialogAdd() {
        AlertDialog.Builder builder =  new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view=inflater.inflate(R.layout.dialogadd_loai, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        EditText edtTenLoai= view.findViewById(R.id.edtTenLoai);
        Button btnThem= view.findViewById(R.id.btnThem);
        Button btnHuy = view.findViewById(R.id.btnHuy);
        TextView txtTieuDe = view.findViewById(R.id.txtTieuDe);

        txtTieuDe.setText("Thêm loại");

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data= edtTenLoai.getText().toString();
                Loai loai = new Loai(data,trangThai);
                boolean check = loaiDAO.themLoai(loai);
                if (check==true){
                    Toast.makeText(getContext(), "Thêm thành công ", Toast.LENGTH_SHORT).show();
                    getDS();
                }else {
                    Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }


    private void getDS(){
        ArrayList<Loai> list = loaiDAO.layDSLoai(trangThai);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        loaiAdapter adapter = new loaiAdapter(getContext(),list, loaiDAO, trangThai);
        recyclerView.setAdapter(adapter);
    }
}
