package com.bawei.danli;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.GridView;

import com.bawei.danli.base.BaseActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private GridView gridView;
    private int pager = 1;
    private boolean isloadMore;
    private ArrayList<StudentBean.ListdataBean> list = new ArrayList<>();

    @Override
    protected void initData() {

        String url = "";
        if (pager == 1) {
            url = "http://blog.zhaoliang5156.cn/api/news/lawyer.json";
        } else if (pager == 2) {
            url = "http://blog.zhaoliang5156.cn/api/news/lawyer1.json";
        } else if (pager == 3) {
            url = "http://blog.zhaoliang5156.cn/api/news/lawyer2.json";
        }
        Until.getInstance().doGet(url, new Until.MyCallback() {
            @Override
            public void onsuccess(String josn) {
               StudentBean studentBean = new Gson().fromJson(josn, StudentBean.class);
               List<StudentBean.ListdataBean> listdata = studentBean.getListdata();
               MyAdapter myAdapter = new MyAdapter(listdata);
               gridView.setAdapter(myAdapter);
            }

            @Override
            public void onphotosuccess(Bitmap bitmap) {

            }
        });

    }

    @Override
    protected void initView() {
        gridView = findViewById(R.id.gv);
    }

    @Override
    protected int LayoutID() {
        return R.layout.activity_main;
    }
}
