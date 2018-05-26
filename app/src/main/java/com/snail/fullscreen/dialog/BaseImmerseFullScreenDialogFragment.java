package com.snail.fullscreen.dialog;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.snail.fullscreen.R;

/**
 * Author: hzlishang
 * Data: 16/9/27 下午10:36
 * Des:纯代码全屏沉浸式DialogFragment
 * version:
 */
public class BaseImmerseFullScreenDialogFragment extends BaseFullScreenDialogFragment {

    @Override
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && getDialog().getWindow() != null) {
            //这句比比添加 ，挺奇怪为啥？
            if (getDialog().getWindow() != null && getDialog().getWindow().getDecorView() != null) {
                getDialog().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                getDialog().getWindow().setStatusBarColor(0x00000000);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_full_screen, null);
    }
}
