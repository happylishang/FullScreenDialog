package com.snail.fullscreen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.snail.fullscreen.R;
import com.snail.fullscreen.dialog.BaseFullScreenDialog;
import com.snail.fullscreen.dialog.BaseFullScreenDialogFragment;
import com.snail.fullscreen.dialog.BaseFullScreenImmerseDialog;
import com.snail.fullscreen.dialog.BaseImmerseFullScreenDialogFragment;
import com.snail.fullscreen.dialog.BaseThemeFullScreenImmerseDialog;
import com.snail.fullscreen.dialog.ThemeFullScreenFitSystemWindowAlertDialog;
import com.snail.fullscreen.service.BackGroundService;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        Intent intent = new Intent(MainActivity.this, BackGroundService.class);
        startService(intent);

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
        ThemeFullScreenFitSystemWindowAlertDialog dialog = new ThemeFullScreenFitSystemWindowAlertDialog(this);
        dialog.show();
    }

    @OnClick(R.id.full_immerse)
    void full_immerse() {
        BaseThemeFullScreenImmerseDialog dialog = new BaseThemeFullScreenImmerseDialog(this);
        dialog.setContentView(LayoutInflater.from(this).inflate(R.layout.dialog_full_screen, null));
        dialog.show();
    }

    @OnClick(R.id.code_full_immerse)
    void code_full_immerse() {
        BaseFullScreenImmerseDialog dialog = new BaseFullScreenImmerseDialog(this);
        dialog.setContentView(LayoutInflater.from(this).inflate(R.layout.dialog_full_screen, null));
        dialog.show();
    }

    @OnClick(R.id.code_full_)
    void code_full_() {
        BaseFullScreenDialog dialog = new BaseFullScreenDialog(this);
        dialog.show();
    }

}
