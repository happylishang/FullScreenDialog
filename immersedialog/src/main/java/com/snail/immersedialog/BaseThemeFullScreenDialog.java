package com.snail.immersedialog;

import android.app.Dialog;
import android.content.Context;


/**
 * Author: hzlishang
 * Data: 16/10/8 下午4:11
 * Des: Theme基类全屏 沉浸式对话框
 * version:
 */
public class BaseThemeFullScreenDialog extends Dialog {

    public BaseThemeFullScreenDialog(Context context) {
        this(context, R.style.dialog_full_screen);
    }

    public BaseThemeFullScreenDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

}

