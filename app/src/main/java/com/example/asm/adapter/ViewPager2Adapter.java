package com.example.asm.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.asm.fragment.khoanFragment;
import com.example.asm.fragment.loaiFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    private int trangThai;

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity, int trangThai) {
        super(fragmentActivity);
        this.trangThai= trangThai;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle=  new Bundle();
        bundle.putInt("trangThai",trangThai);
        switch (position){
            case 0 :
                loaiFragment loaiFragment = new loaiFragment();
                loaiFragment.setArguments(bundle);
                return loaiFragment;
            case 1:
                khoanFragment khoanFragment = new khoanFragment();
                khoanFragment.setArguments(bundle);
                return khoanFragment;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
