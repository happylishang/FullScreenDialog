package com.snail.fullscreen.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * Author: hzlishang
 * Data: 16/10/8 下午4:11
 * Des: 纯代码基类全屏 沉浸式对话框
 * version:
 */
public class BaseFullScreenImmerseDialog extends BaseFullScreenDialog {

    public BaseFullScreenImmerseDialog(Context context) {
        super(context);
    }

    //  沉浸式设置
    @Override
    public void show() {
        if (getWindow() != null && getWindow().getDecorView() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //这句比比添加 ，挺奇怪为啥？
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //   设置颜色
                getWindow().setStatusBarColor(0x00000000);
            }
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        super.show();
    }
}

