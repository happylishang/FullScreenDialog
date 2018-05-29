package com.snail.fullscreen.dialog.wrapgravity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.snail.fullscreen.R;

/**
 * Author: hzlishang
 * Data: 16/10/8 下午4:11
 * Des: 为什么AlertDialog 跟Dialog差别很大呢
 * version:
 */
public class ThemeFloatingAlertDialog {

    private Dialog dialog;

    public ThemeFloatingAlertDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.alertdialog_fragment_full_screen_immerse);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_full_screen, null);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ret = dialog.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
                Log.v("","v");
            }
        });
        builder.setView(view);
        dialog = builder.create();
    }

    //    沉浸式设置
    public void show() {
        dialog.show();
        if (dialog.getWindow() != null && dialog.getWindow().getDecorView() != null) {
            dialog.getWindow().setGravity(Gravity.TOP);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }

    }
}

