package com.snail.fullscreen.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * Author: hzlishang
 * Data: 16/10/8 下午4:11
 * Des: 纯代码基类全屏 沉浸式对话框，并不可行，背景颜色，不可以改变，用来SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN之后，也不可控制 可能跟floadting = true 有关系
 * version:
 */
public class BaseImmerseFullScreenDialog extends BaseFullScreenDialog {

    public BaseImmerseFullScreenDialog(Context context) {
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
//                getWindow().setStatusBarColor(0xffff0000);
            }
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        super.show();
    }
}

//为什么必须设置FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS才行，因为不设置就一直是黑色
//    public static int calculateStatusBarColor(int flags, int semiTransparentStatusBarColor,
//                                              int statusBarColor) {
//        return (flags & FLAG_TRANSLUCENT_STATUS) != 0 ? semiTransparentStatusBarColor
//                : (flags & FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS) != 0 ? statusBarColor
//                : Color.BLACK;
//    }