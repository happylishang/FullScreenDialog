package com.snail.fullscreen.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.snail.fullscreen.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: hzlishang
 * Data: 16/9/27 下午10:36
 * Des:
 * version:
 */
public class FragmentOnViewFullScreen extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_full_screen, container);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        //  时机问题 有些是可以动态调整，有些不行。
//        dialog.getWindow().getAttributes().windowAnimations = R.style.animat1;
//        dialog.getWindow().getAttributes().windowAnimations = R.style.animat1;

//        此处设置无效

//        int width = ViewGroup.LayoutParams.MATCH_PARENT;
//        int height = ViewGroup.LayoutParams.MATCH_PARENT;
//        dialog.getWindow().setLayout(width, height);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));


        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        super.onViewCreated(view, savedInstanceState);
        rootView = view.findViewById(R.id.lv);
        content = view.findViewById(R.id.content);
        startAni();
    }

    @OnClick(R.id.button)
    void click() {

        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
    }

    private void startAni() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(rootView, "translationY", getActivity().getResources().getDisplayMetrics().heightPixels, 1.0f);
        objectAnimator.setDuration(400);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int h = rootView.getMeasuredHeight();
                valueAnimator.getAnimatedValue();
                Log.v("lishang", " ani" + valueAnimator.getAnimatedValue());
            }
        });
        ObjectAnimator objectAnimatora = ObjectAnimator.ofFloat(content, "alpha", 0.0f, 1.0f);
        objectAnimatora.setDuration(300);
        AnimatorSet set = new AnimatorSet();
        set.play(objectAnimator).before(objectAnimatora);

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int h = rootView.getMeasuredHeight();
                h = rootView.getMeasuredHeight();
            }
        });

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                int h = rootView.getMeasuredHeight();
                h = rootView.getMeasuredHeight();
                Log.v("lishang", "start");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                int h = rootView.getMeasuredHeight();
                h = rootView.getMeasuredHeight();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
    }

    View rootView;
    View content;

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));

        }
        int wina = dialog.getWindow().getAttributes().windowAnimations;
        wina = dialog.getWindow().getAttributes().windowAnimations;
//        时机问题
//        dialog.getWindow().getAttributes().windowAnimations = R.style.animat1;

    }
}
