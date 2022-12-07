package com.example.asm.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm.R;
import com.example.asm.dao.KhoanDAO;
import com.example.asm.dao.LoaiDAO;
import com.example.asm.model.Khoan;
import com.example.asm.model.Loai;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class khoanAdapter extends RecyclerView.Adapter<khoanAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Khoan> list;
    private KhoanDAO khoanDAO;
    private String idLoai;
    private int trangThai;

    public khoanAdapter(Context context, ArrayList<Khoan> list, KhoanDAO khoanDAO, int trangThai) {
        this.context = context;
        this.list = list;
        this.khoanDAO = khoanDAO;
        this.trangThai = trangThai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khoan, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtidKhoan.setText(String.valueOf(list.get(position).getIdKhoan()));
        holder.txttenKhoan.setText(list.get(position).getTenKhoan());
        holder.txtNgay.setText(list.get(position).getNgay());
        holder.txtTien.setText(String.valueOf(list.get(position).getTien()));
        holder.txtidLoai.setText(String.valueOf(list.get(position).getIdLoai()));
        holder.txttenLoai.setText(list.get(position).getTenLoai());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(list.get(holder.getAdapterPosition()));

            }
        });
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = khoanDAO.xoaKhoan(list.get(holder.getAdapterPosition()).getIdKhoan());
                if (check){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    getDS();
                }else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtidKhoan, txttenKhoan, txtNgay, txtTien, txtidLoai, txttenLoai;
        ImageView ivEdit, ivDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtidKhoan = itemView.findViewById(R.id.idKhoan);
            txttenKhoan = itemView.findViewById(R.id.tenKhoan);
            txtNgay = itemView.findViewById(R.id.ngay);
            txtTien = itemView.findViewById(R.id.tien);
            txtidLoai = itemView.findViewById(R.id.idLoai);
            txttenLoai = itemView.findViewById(R.id.tenLoai);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDel = itemView.findViewById(R.id.ivDel);


        }
    }

    private void showDialog(Khoan khoan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_plthuchi, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        EditText edtTenKhoan = view.findViewById(R.id.edtTenKhoan);
        EditText edtTien = view.findViewById(R.id.edtTien);
        EditText edtNgay = view.findViewById(R.id.edtNgay);
        Spinner spnLoai = view.findViewById(R.id.spnLoai);
        Button btnThemSua = view.findViewById(R.id.btnThemSua);
        Button btnHuySua = view.findViewById(R.id.btnHuySua);
        TextView txtTieuDe = view.findViewById(R.id.txtTieuDe);

        txtTieuDe.setText("Sửa Khoản");
        btnThemSua.setText("Sửa");

        edtTenKhoan.setText(khoan.getTenKhoan());
        edtTien.setText(String.valueOf( khoan.getTien()));
        edtNgay.setText(khoan.getNgay());

        layDSSpinner(spnLoai,khoan.getIdLoai());
        edtNgay.setFocusable(false);
        edtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(edtNgay);
            }
        });
//        int idLoai = -1;
        spnLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, Object> hashMap = (HashMap<String, Object>) spnLoai.getSelectedItem();
                idLoai = String.valueOf(hashMap.get("idLoai"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnHuySua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        btnThemSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenKhoan = edtTenKhoan.getText().toString();
                int tien = Integer.parseInt(edtTien.getText().toString());
                String ngay = edtNgay.getText().toString();
                int id = Integer.parseInt(idLoai);
                Khoan capnhatkhoan = new Khoan(khoan.getIdKhoan(),tenKhoan, tien, ngay, id);
                boolean check = khoanDAO.capNhatKhoan(capnhatkhoan);
                if (check) {
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    getDS();
                } else {
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();

            }
        });
    }

    private void showDatePickerDialog(EditText edtNgay) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String thang = "";
                        if (i1 < 9) {
                            thang = "0" + (i1 + 1);
                        } else {
                            thang = String.valueOf((i1 + 1));
                        }
                        String ngay = "";
                        if (i2 < 10) {
                            ngay = "0" + i2;
                        } else {
                            ngay = String.valueOf(i2);
                        }
                        edtNgay.setText(ngay + "/" + thang + "/" + i);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void layDSSpinner(Spinner spnLoai, int idLoai) {
        int vitri = -1;
        int postion = 0;
        LoaiDAO loaiDAO = new LoaiDAO(context);
        ArrayList<Loai> list = loaiDAO.layDSLoai(trangThai);
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Loai loai : list) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("idLoai", loai.getIdLoai());
            hashMap.put("tenLoai", loai.getTenLoai());
            listHM.add(hashMap);

            if (loai.getIdLoai() == idLoai) {
                vitri = postion;
            }
            postion++;

        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1}
        );
        spnLoai.setAdapter(simpleAdapter);
        spnLoai.setSelection(vitri);

    }

    private void getDS() {
        list.clear();
        list = khoanDAO.layDSKhoan(trangThai);
        notifyDataSetChanged();
    }
}
