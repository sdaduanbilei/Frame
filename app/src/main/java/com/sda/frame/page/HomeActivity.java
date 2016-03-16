package com.sda.frame.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sda.frame.DataControl;
import com.sda.frame.MyApplication;
import com.sda.frame.R;
import com.sda.frame.model.VersionData;
import com.sda.frame.presenter.ArticleListPresenter;
import com.sda.frame.view.ArticleView;
import com.sda.lib.HttpCore.DataResponse;


public class HomeActivity extends AppCompatActivity implements ArticleView {

    Context context ;
    ArticleListPresenter articleListPresenter ;

//    @ViewInject(id = R.id.listview)
    TextView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context =HomeActivity.this ;
//        FinalActivity.initInjectedView(this);

//        articleListPresenter = new ArticleListCompl(this);
//        articleListPresenter.init(context,listView);

        loadData();

    }

    private void loadData() {
        MyApplication app = (MyApplication) getApplication();

        app.pdc.getArticleList2(context, new DataResponse() {
            @Override
            public void onSucc(Object response) {
//                VersionData data = (VersionData) response;
//                listView.setText(data.getDsc());
            }

            @Override
            public void onFail(String error) {

            }
        });

        app.pdc.getArticleList(context, new DataResponse() {
            @Override
            public void onSucc(Object response) {
                VersionData data = (VersionData) response;
                listView.setText(data.getDsc());
            }

            @Override
            public void onFail(String error) {

            }
        });

    }
}
