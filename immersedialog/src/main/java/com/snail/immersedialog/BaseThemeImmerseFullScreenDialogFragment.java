package com.snail.immersedialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: hzlishang
 * Data: 16/9/27 下午10:36
 * Des: 全屏DialogFragment
 * version:
 */
public class BaseThemeImmerseFullScreenDialogFragment extends DialogFragment {



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog_immerse_full_screen);
    }
}
