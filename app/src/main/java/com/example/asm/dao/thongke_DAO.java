package com.example.asm.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm.database.Dbhelper;

public class thongke_DAO {
    private Dbhelper dbhelper;
    public thongke_DAO(Context context){
        dbhelper = new Dbhelper(context);
    }
    public float[] getThongTinThuChi() {
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        int thu = 0, chi = 0;

        //select sum(tien)
        //from khoan
        //where idLoai in (select idLoai from Loai where trangthai = 0)
        Cursor cursorThu = sqLiteDatabase.rawQuery("select sum(tien) from KHOAN where idLoai in (select idLoai from LOAI where trangthai = 0) ", null);

        if (cursorThu.getCount() != 0) {
            cursorThu.moveToFirst();
            thu = cursorThu.getInt(0);
        }

        //select sum(tien)
        //from khoan
        //where idLoai in (select idLoai from Loai where trangthai = 1)
        Cursor cursorChi = sqLiteDatabase.rawQuery("select sum(tien) from KHOAN where idLoai in (select idLoai from LOAI where trangthai = 1)", null);

        if (cursorChi.getCount() != 0) {
            cursorChi.moveToFirst();
            chi = cursorChi.getInt(0);
        }

        float[] ketQua = new float[]{thu, chi};
        return ketQua;
    }
}
