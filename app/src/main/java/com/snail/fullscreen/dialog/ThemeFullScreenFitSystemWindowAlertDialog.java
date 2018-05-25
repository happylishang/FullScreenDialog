package com.snail.fullscreen.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.snail.fullscreen.R;

/**
 * Author: hzlishang
 * Data: 16/10/8 下午4:11
 * Des: 全屏沉浸式Dialog
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
            dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}

