package com.example.asm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm.database.Dbhelper;
import com.example.asm.model.Loai;

import java.util.ArrayList;

public class LoaiDAO {
    private Dbhelper dbhelper;
    public LoaiDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    //lâys danh sách loại theo trạng thái
    //0:thu- 1:chi
    public ArrayList<Loai> layDSLoai (int trangThai){
        ArrayList<Loai> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor  cursor= sqLiteDatabase.rawQuery("SELECT * FROM LOAI WHERE trangThai =?",new String[]{String.valueOf(trangThai)});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                list.add(new Loai(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }while (cursor.moveToNext());
        }

        return list;
    }
    //true : theem thanh cong, false : them that bai
    public boolean themLoai(Loai loai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai",loai.getTenLoai());
        contentValues.put("trangThai",loai.getTrangThai());
        long check = sqLiteDatabase.insert("LOAI",null,contentValues);
        if (check==-1){
            return false;
        }
        return true;
    }


    // true cap nhat thanh cong
    //false cap nhat that bai
    public boolean capNhat(Loai loai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai",loai.getTenLoai());
        contentValues.put("trangThai",loai.getTrangThai());
        long check = sqLiteDatabase.update("LOAI",contentValues,"idLoai=?",new String[]{String.valueOf(loai.getIdLoai())});
        if (check==-1){
            return false;
        }return true;
    }

    //
    public  boolean xoaLoai(int idLoai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        long check= sqLiteDatabase.delete("LOAI","idLoai = ?",new String[]{String.valueOf(idLoai)});
        if (check==-1){
            return false;
        }return true;
    }
}
