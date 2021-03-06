package com.snail.fullscreen.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.snail.fullscreen.R;
import com.snail.fullscreen.dialog.BaseFullScreenDialog;
import com.snail.fullscreen.dialog.BaseFullScreenDialogFragment;
import com.snail.fullscreen.dialog.BaseImmerseFullScreenDialog;
import com.snail.fullscreen.dialog.BaseImmerseFullScreenDialogFragment;
import com.snail.fullscreen.dialog.BaseThemeFullScreenDialog;
import com.snail.fullscreen.dialog.BaseThemeImmerseFullScreenDialog;
import com.snail.fullscreen.dialog.wrapgravity.BaseWrapContentDialog;
import com.snail.fullscreen.dialog.wrapgravity.ThemeFloatingAlertDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @OnClick(R.id.code_full_dialog_fragment)
    void code_full_dialog_fragment() {

        BaseFullScreenDialogFragment fullScreen = new BaseFullScreenDialogFragment();
        fullScreen.show(getSupportFragmentManager(), "");
    }

    @OnClick(R.id.code_full_immerse_dialog_fragment)
    void code_full_immerse_dialog_fragment() {

        android.support.v4.app.DialogFragment fullScreen = new BaseImmerseFullScreenDialogFragment();
        fullScreen.show(getSupportFragmentManager(), "");
    }


    @OnClick(R.id.alertdialog)
    void alertdialog() {
        ThemeFloatingAlertDialog dialog = new ThemeFloatingAlertDialog(this);
        dialog.show();
    }

    @OnClick(R.id.full_immerse)
    void full_immerse() {
        final BaseThemeImmerseFullScreenDialog dialog = new BaseThemeImmerseFullScreenDialog(this);
        dialog.setContentView(view = LayoutInflater.from(this).inflate(R.layout.dialog_full_screen, null));
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ret = dialog.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
                Log.v("", "v");
                dialog.cancel();
            }
        });
        dialog.show();
    }

    View view;

    @OnClick(R.id.BaseThemeFullScreenDialog)
    void BaseThemeFullScreenDialog() {

        final BaseThemeFullScreenDialog dialog = new BaseThemeFullScreenDialog(this);
        dialog.setContentView(view = LayoutInflater.from(this).inflate(R.layout.dialog_full_screen, null));

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ret = dialog.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;

                Log.v("", "v");
                dialog.cancel();
            }
        });

        dialog.show();
    }


    @OnClick(R.id.code_full_immerse)
    void code_full_immerse() {
        BaseImmerseFullScreenDialog dialog = new BaseImmerseFullScreenDialog(this);
        dialog.setContentView(LayoutInflater.from(this).inflate(R.layout.dialog_full_screen, null));
        dialog.show();
    }

    @OnClick(R.id.code_full_)
    void code_full_() {
        BaseFullScreenDialog dialog = new BaseFullScreenDialog(this);
        dialog.show();
    }

    @OnClick(R.id.BaseWrapcontentDialog)
    void BaseWrapcontentDialog() {
        Dialog dialog = new BaseWrapContentDialog(this);
        dialog.show();
    }

}
