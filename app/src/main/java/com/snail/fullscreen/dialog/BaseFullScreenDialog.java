package com.snail.fullscreen.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.snail.fullscreen.R;

/**
 * Author: hzlishang
 * Data: 16/10/8 下午4:11
 * Des: 纯代码基类全屏对话框（非沉浸式）
 * version:
 */
public class BaseFullScreenDialog extends Dialog {
    public BaseFullScreenDialog(Context context) {
        super(context);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_full_screen, null);
        setContentView(view);
    }

    @Override
    protected void onStart() {
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        }
    }
}

