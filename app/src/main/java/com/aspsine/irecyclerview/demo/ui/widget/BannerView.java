package com.aspsine.irecyclerview.demo.ui.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.irecyclerview.demo.R;
import com.aspsine.irecyclerview.demo.model.Image;
import com.bumptech.glide.Glide;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aspsine on 16/4/5.
 */
public class BannerView extends FrameLayout {

    private ViewPager viewPager;

    private Adapter mAdapter;

    private CirclePageIndicator indicator;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.layout_banner, this);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);

        mAdapter = new Adapter();
        viewPager.setAdapter(mAdapter);

        indicator.setViewPager(viewPager);
    }

    public void setList(List<Image> images) {
        mAdapter.setList(images);
    }

    static class Adapter extends PagerAdapter {
        private List<Image> mImages;

        private boolean mNotify;

        public Adapter() {
            mImages = new ArrayList<>();
        }

        public void setList(List<Image> images) {
            mImages.clear();
            mImages.addAll(images);
            notifyDataSetChanged();
        }

        @Override
        public void notifyDataSetChanged() {
            mNotify = true;
            super.notifyDataSetChanged();
            mNotify = false;
        }

        @Override
        public int getCount() {
            return mImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = container.getContext();
            View view = LayoutInflater.from(context).inflate(R.layout.layout_banner_item, container, false);
            ImageView ivImage = (ImageView) view.findViewById(R.id.ivImage);
            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);

            Image image = mImages.get(position);
            view.setTag(image);
            Glide.with(context).load(image.image).into(ivImage);
//            tvTitle.setText(image.title);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            if (mNotify) {
                Image tag = (Image) ((View) object).getTag();

                for (int i = 0; i < mImages.size(); i++) {

                    Image image = mImages.get(i);

                    if (TextUtils.equals(image.title, tag.title)
                            && TextUtils.equals(image.image, tag.image)) {

                        return super.getItemPosition(object);
                    }
                }
                return PagerAdapter.POSITION_NONE;
            }

            return super.getItemPosition(object);
        }
    }
}
