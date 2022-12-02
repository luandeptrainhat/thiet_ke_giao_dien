package com.example.asm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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


        return view;
    }


    private void getDS(){
        ArrayList<Loai> list = loaiDAO.layDSLoai(trangThai);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        loaiAdapter adapter = new loaiAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
    }
}
