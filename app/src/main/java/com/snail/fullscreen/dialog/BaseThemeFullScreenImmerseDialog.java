package com.snail.fullscreen.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.snail.fullscreen.R;

/**
 * Author: hzlishang
 * Data: 16/10/8 下午4:11
 * Des: Theme基类全屏 沉浸式对话框
 * version:
 */
public class BaseThemeFullScreenImmerseDialog extends Dialog {

    public BaseThemeFullScreenImmerseDialog(Context context) {
        this(context, R.style.dialog_fragment_full_screen_immerse);
    }

    public BaseThemeFullScreenImmerseDialog(Context context, int themeResId) {
        super(context, themeResId);
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

