package com.example.asm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm.R;
import com.example.asm.model.Loai;

import java.util.ArrayList;

public class loaiAdapter extends RecyclerView.Adapter<loaiAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Loai> list;

    public loaiAdapter(Context context, ArrayList<Loai> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loai, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtID.setText(String.valueOf(list.get(position).getIdLoai()));
        holder.txtTenLoai.setText(list.get(position).getTenLoai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtID, txtTenLoai ;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtID= itemView.findViewById(R.id.txtID);
            txtTenLoai= itemView.findViewById(R.id.txtTenLoai);
        }
    }
}
