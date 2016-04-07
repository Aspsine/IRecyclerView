package com.aspsine.irecyclerview.demo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aspsine.irecyclerview.IViewHolder;
import com.aspsine.irecyclerview.demo.R;
import com.aspsine.irecyclerview.demo.model.Image;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aspsine on 16/4/5.
 */
public class ImageAdapter extends RecyclerView.Adapter<IViewHolder> {

    private List<Image> mImages;

    private OnItemClickListener<Image> mOnItemClickListener;

    public ImageAdapter() {
        mImages = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener<Image> listener) {
        this.mOnItemClickListener = listener;
    }

    public void setList(List<Image> images) {
        mImages.clear();
        append(images);
    }

    public void append(List<Image> images) {
        int positionStart = mImages.size();
        int itemCount = images.size();
        mImages.addAll(images);
        if (positionStart > 0 && itemCount > 0) {
            notifyItemRangeInserted(positionStart, itemCount);
        } else {
            notifyDataSetChanged();
        }
    }

    public void clear(){
        mImages.clear();
        notifyDataSetChanged();
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image_item, parent, false);

        final ViewHolder holder = new ViewHolder(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Note:
                 * in order to get the right position, you must use the method with i- prefix in
                 * {@link IViewHolder} eg:
                 * {@code IViewHolder.getIPosition()}
                 * {@code IViewHolder.getILayoutPosition()}
                 * {@code IViewHolder.getIAdapterPosition()}
                 */
                final int position = holder.getIAdapterPosition();
                final Image image = mImages.get(position);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position, image, v);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, int position) {
        ImageView imageView = (ImageView) holder.itemView;
        Image image = mImages.get(position);
        Glide.with(imageView.getContext()).load(image.image).dontAnimate().into(imageView);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    static class ViewHolder extends IViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
