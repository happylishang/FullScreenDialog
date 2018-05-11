package com.snail.labaffinity.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.snail.labaffinity.R;

/**
 * Author: hzlishang
 * Data: 16/10/8 下午4:11
 * Des:
 * version:
 */
public class FloationgFalseDialog extends Dialog {
    public FloationgFalseDialog(Context context) {
        this(context, R.style.dialog_fragment_full_screen);
    }

    public FloationgFalseDialog(Context context, int themeResId) {
        super(context, R.style.dialog_fragment_full_screen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // 其实这里都是View的布局参数，而不是 WindowManager.LayoutParams
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_full_screen, null);
        setContentView(view);
//        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setVisibility(View.VISIBLE);
            }
        });
        text = view.findViewById(R.id.test_are);
    }

    private View text;


    @Override
    public void show() {
        if (getWindow() != null && getWindow().getDecorView() != null) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        super.show();
    }
}

