package com.snail.fullscreen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.snail.fullscreen.R;
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

    @OnClick(R.id.first)
    void first() {

        FragmentFullScreen fullScreen = new FragmentFullScreen();
        fullScreen.show(getSupportFragmentManager(), "");
    }

    @OnClick(R.id.second)
    void second() {

        FragmentOnViewFullScreen fullScreen = new FragmentOnViewFullScreen();
        fullScreen.show(getSupportFragmentManager(), "");
    }

    @OnClick(R.id.windattr)
    void windattr() {
        int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        getWindow().setLayout(width, height);
//        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
    }

    @OnClick(R.id.alertdialog)
    void alertdialog() {
        ThemeFullScreenFitSystemWindowAlertDialog dialog = new ThemeFullScreenFitSystemWindowAlertDialog(this);
        dialog.show();
    }

    @OnClick(R.id.full_immerse)
    void full_immerse() {
        ThemeFullScreenFitSystemWindowDialog dialog = new ThemeFullScreenFitSystemWindowDialog(this);
        dialog.show();
    }
}
