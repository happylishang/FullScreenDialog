package com.snail.immersedialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * Author: hzlishang
 * Data: 16/10/8 下午4:11
 * Des: 纯代码基类全屏对话框（非沉浸式）  无法修改状态栏颜色
 * version:
 */
public class BaseWrapContentDialog extends Dialog {
    public BaseWrapContentDialog(Context context) {
        super(context);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }


    @Override
    protected void onStart() {
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            getWindow().setGravity(Gravity.TOP);
        }
    }
}

