package com.aspsine.irecyclerview.demo.ui.widget.header;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aspsine.irecyclerview.RefreshTrigger;
import com.aspsine.irecyclerview.demo.R;

/**
 * Created by aspsine on 16/4/7.
 */
public class BatVsSupperHeaderView extends FrameLayout implements RefreshTrigger {

    private ImageView ivBatMan;

    private ImageView ivSuperMan;

    private ImageView ivVs;

    private int mHeight;

    private Animator mRotationAnimator;

    public BatVsSupperHeaderView(Context context) {
        this(context, null);
    }

    public BatVsSupperHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BatVsSupperHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_irecyclerview_bat_vs_supper_refresh_header_view, this);
        ivBatMan = (ImageView) findViewById(R.id.ivBatMan);
        ivSuperMan = (ImageView) findViewById(R.id.ivSuperMan);
        ivVs = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public void onStart(boolean automatic, int headerHeight, int finalHeight) {
        mHeight = headerHeight;
    }

    @Override
    public void onMove(boolean finished, boolean automatic, int moved) {
        if (!finished) {
            ivVs.setRotationY(moved / (float) mHeight * 360);
        } else {
            ivVs.setRotationY(moved / (float) mHeight * 360);
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {
        ivVs.setRotationY(0);
    }
}
