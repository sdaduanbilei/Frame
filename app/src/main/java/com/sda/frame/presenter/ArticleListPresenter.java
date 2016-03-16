package com.sda.frame.presenter;

import android.content.Context;
import android.widget.ListView;

/**
 * Created by scorpio on 16/3/2.
 */
public interface ArticleListPresenter {
    void init(Context context, ListView listView);
    void setData();

}
