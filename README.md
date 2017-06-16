[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-IRecyclerView-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3403)
# IRecyclerView
IRecyclerView is a custom RecyclerView that supports pull-to-refresh, pull-to-loadmore, customize refresh header and loadmore footer, add header views and footer views.

### Features:
- pull-to-refresh
- pull-to-loadmore
- customize refresh header
- customize loadmore footer
- add multiple header view
- add multiple footer view
- support vertical LinearLayoutManager/GridLayoutManager/StaggeredGridLayoutManager

### Demo
[Download](https://github.com/Aspsine/IRecyclerView/blob/master/art/demo.apk?raw=true)

### Demo ScreenShot
- Bat Man vs Super Man Refresh header

![Bat Man vs Super Man](https://github.com/Aspsine/IRecyclerView/raw/master/art/bat_vs_supper_header.gif)

- Classic Refresh header

![classic](https://github.com/Aspsine/IRecyclerView/raw/master/art/class_header.gif)

- Load more footer

![load more](https://github.com/Aspsine/IRecyclerView/raw/master/art/load_more.gif)

### How to use
Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
Step 2. Add the dependency
```groovy
dependencies {
    compile 'com.github.Aspsine:IRecyclerView:0.0.5'
}
```
Step 3. Edit your Activity/Fragment content view layout
```xml
<com.aspsine.irecyclerview.IRecyclerView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/iRecyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:loadMoreEnabled="true"
    app:loadMoreFooterLayout="@layout/layout_irecyclerview_load_more_footer"
    app:refreshEnabled="true"
    app:refreshHeaderLayout="@layout/layout_irecyclerview_refresh_header"/>
```
Available attributes:
```xml
<declare-styleable name="IRecyclerView">
    <attr name="refreshHeaderLayout" format="reference" />  <!--refresh header layout res id-->
    <attr name="loadMoreFooterLayout" format="reference" /> <!--loadmore fotter layout res id-->
    <attr name="refreshEnabled" format="boolean" />
    <attr name="loadMoreEnabled" format="boolean" />
    <attr name="refreshFinalMoveOffset" format="dimension" />   <!--refresh header max move distance-->
</declare-styleable>
```

Then Editor your Activity/Fragment

```java
IRecyclerView iRecyclerView = (IRecyclerView) findViewById(R.id.iRecyclerView);

iRecyclerView.setLayoutManager(new LinearLayoutManager(this));

// an custom load more footer view, you can customize it yourself.
LoadMoreFooterView loadMoreFooterView = (LoadMoreFooterView) iRecyclerView.getLoadMoreFooterView();

// you can also add header and footer like this
// note: header and refresh header are different, footer and load more footer are different too. 
iRecyclerView.addHeaderView(headerView);
iRecyclerView.addFooterView(footerView);

// adapter
ImageAdapter mAdapter = new ImageAdapter();
// note: here use setIAdapter(...) method not setAdapter(...)
iRecyclerView.setIAdapter(mAdapter);

iRecyclerView.setOnRefreshListener(new OnRefreshListener() {
    @Override
    public void onRefresh() {
    
    }
});
iRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
    @Override
    public void onLoadMore(View loadMoreView) {

    }
});

// set auto refreshing
iRecyclerView.post(new Runnable() {
    @Override
   public void run() {
       iRecyclerView.setRefreshing(true);
   }
});

// stop refreshing
iRecyclerView.setRefreshing(false);
```

Please check out the demo code for more details .

### Contact Me
- Github: github.com/aspsine
- Email:  littleximail@gmail.com
- WeiBo:  [@Aspsine](http://weibo.com/wetze)

### License

    Copyright 2016 Aspsine. All rights reserved.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


