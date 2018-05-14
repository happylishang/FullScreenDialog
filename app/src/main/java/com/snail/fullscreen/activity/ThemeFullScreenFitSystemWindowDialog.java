package com.snail.fullscreen.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.snail.fullscreen.R;

/**
 * Author: hzlishang
 * Data: 16/10/8 下午4:11
 * Des: 全屏沉浸式Dialog
 * version:
 */
public class ThemeFullScreenFitSystemWindowDialog extends Dialog {
    public ThemeFullScreenFitSystemWindowDialog(Context context) {
        this(context, R.style.dialog_fragment_full_screen_immerse);
    }

    public ThemeFullScreenFitSystemWindowDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // 其实这里都是View的布局参数，而不是 WindowManager.LayoutParams
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_full_screen, null);
        setContentView(view);
    }

    //    沉浸式设置
    @Override
    public void show() {
        if (getWindow() != null && getWindow().getDecorView() != null) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        super.show();
    }
}

