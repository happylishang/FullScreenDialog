package com.snail.fullscreen.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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
public class ThemeFullScreenFitSystemWindowAlertDialog {

    private Dialog dialog;

    public ThemeFullScreenFitSystemWindowAlertDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.alertdialog_fragment_full_screen_immerse);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_full_screen, null);
        builder.setView(view);
        dialog = builder.create();
    }

    //    沉浸式设置
    public void show() {
        dialog.show();
        if (dialog.getWindow() != null && dialog.getWindow().getDecorView() != null) {
//            可以不改变floadting属性，通过gravity ，很简单控制Dialog属性，因为一般都是宽度有要求，高度没什么要求，灵活控制
//            dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }
}

