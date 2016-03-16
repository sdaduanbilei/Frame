//package com.sda.frame.presenter;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.google.gson.Gson;
//import com.joanzapata.android.BaseAdapterHelper;
//import com.sda.frame.DataControl;
//import com.sda.frame.R;
//import com.sda.frame.model.NewsData;
//import com.sda.frame.view.ArticleView;
//import com.sda.lib.HttpCore.DataResponse;
//import com.sda.lib.util.AsyncImage;
//import com.sda.lib.util.Tools;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by scorpio on 16/3/2.
// */
//public class ArticleListCompl implements ArticleListPresenter {
//
//    ArticleView articleView;
//    ListView listView;
//    List<NewsData> list;
//    MyAdapter adapter;
//    Context context;
//
//    public ArticleListCompl(ArticleView articleView) {
//        this.articleView = articleView;
//        this.list = new ArrayList<NewsData>();
//    }
//
//    @Override
//    public void init(Context context, final ListView listView) {
//        this.listView = listView;
//        this.context = context;
//        DataControl.getArticleList(context, new DataResponse() {
//            @Override
//            public void onSucc(Object response) {
//                JSONArray jsonArray = JSON.parseArray(response.toString());
//                for (int i = 0; i < jsonArray.size(); i++) {
//                    NewsData data = new Gson().fromJson(jsonArray.getJSONObject(i).toString(), NewsData.class);
//                    list.add(data);
//                }
//                setData();
//            }
//
//            @Override
//            public void onFail(String error) {
//
//            }
//        });
//    }
//
//    @Override
//    public void setData() {
//        adapter = new MyAdapter();
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Tools.toast(context,"click");
//
//            }
//
//
//        });
//    }
//
//
//    private class MyAdapter extends BaseAdapter {
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return list.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            BaseAdapterHelper helper = BaseAdapterHelper.get(context, convertView, parent, R.layout.list_item_for_news);
//            helper.setText(R.id.title, list.get(position).getTitle());
//            helper.setText(R.id.sub_title, list.get(position).getIntro());
//            ImageView pic = helper.getView(R.id.pic);
//            String url = list.get(position).getTopic();
//            if (url.startsWith("http")) {
//                AsyncImage.display(pic, url);
//            } else {
//                AsyncImage.display(pic, "http:" + url);
//            }
//            return helper.getView();
//        }
//    }
//}
