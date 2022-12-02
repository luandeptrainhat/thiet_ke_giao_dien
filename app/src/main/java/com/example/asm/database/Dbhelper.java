package com.example.asm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {
    public Dbhelper(Context context){
        super(context,"QUANLITHUCHI",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createLoai="CREATE TABLE LOAI(idLoai integer primary key autoincrement, tenLoai text, trangThai integer)";
        sqLiteDatabase.execSQL(createLoai);
        String createKhoan="CREATE TABLE KHOAN(idKhoan integer primary key autoincrement, tenKhoan text, tien integer, ngay text, idLoai integer references LOAI (idLoai))";
        sqLiteDatabase.execSQL(createKhoan);
        //0: loại thu - 1: loại chi
        String dataLoai = "INSERT INTO LOAI VALUES (1, 'Mua sắm', 1),(2, 'Từ thiện', 1), (3, 'Xăng dầu', 1), (4, 'Lương', 0), (5, 'Thưởng', 0)";
        sqLiteDatabase.execSQL(dataLoai);
        String dataKhoan = "INSERT INTO KHOAN VALUES (1, 'Hỗ trợ miền Trung', 500000, '17/11/2022', 2), (2, 'Xăng xe máy', 90000, '17/11/2022', 3), (3, 'Thưởng Tết', 1500000, '24/11/2022', 5), (4, 'Mua đồ Tết', 790000, '25/11/2022', 1)";
        sqLiteDatabase.execSQL(dataKhoan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    if (i!=i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAI");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS KHOAN");
        onCreate(sqLiteDatabase);
    }
    }
}