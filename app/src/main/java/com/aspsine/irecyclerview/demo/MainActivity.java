package com.aspsine.irecyclerview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.demo.utils.ListUtils;
import com.aspsine.irecyclerview.demo.widget.BannerView;
import com.aspsine.irecyclerview.demo.widget.footer.LoadMoreFooterView;

import java.util.List;


public class MainActivity extends AppCompatActivity implements OnItemClickListener<Image>, OnRefreshListener, OnLoadMoreListener {

    private IRecyclerView iRecyclerView;
    private BannerView bannerView;
    private LoadMoreFooterView loadMoreFooterView;

    private ImageAdapter mAdapter;

    private int mPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iRecyclerView = (IRecyclerView) findViewById(R.id.iRecyclerView);
        iRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        loadMoreFooterView = (LoadMoreFooterView) iRecyclerView.getLoadMoreFooterView();

        mAdapter = new ImageAdapter();
        iRecyclerView.setIAdapter(mAdapter);

        iRecyclerView.setOnRefreshListener(this);
        iRecyclerView.setOnLoadMoreListener(this);

        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position, Image image, View v) {
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
//        loadBanner();
        refresh();
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        if (loadMoreFooterView.canLoadMore() && mAdapter.getItemCount() > 0) {
            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
            loadMore();
        }
    }

    private void loadBanner() {
        NetworkAPI.requestBanners(new NetworkAPI.Callback<List<Image>>() {
            @Override
            public void onSuccess(List<Image> images) {
                bannerView.setList(images);
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void refresh() {
        mPage = 1;
        NetworkAPI.requestImages(mPage, new NetworkAPI.Callback<List<Image>>() {
            @Override
            public void onSuccess(List<Image> images) {
                iRecyclerView.setRefreshing(false);
                if (ListUtils.isEmpty(images)) {
                    mAdapter.clear();
                } else {
                    mPage = 2;
                    mAdapter.setList(images);
                }
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                iRecyclerView.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMore() {
        NetworkAPI.requestImages(mPage, new NetworkAPI.Callback<List<Image>>() {
            @Override
            public void onSuccess(List<Image> images) {
                if (ListUtils.isEmpty(images)) {
                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
                } else {
                    mPage++;
                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
                    mAdapter.setList(images);
                }
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                loadMoreFooterView.setStatus(LoadMoreFooterView.Status.ERROR);
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
