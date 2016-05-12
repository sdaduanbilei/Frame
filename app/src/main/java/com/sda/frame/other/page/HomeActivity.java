package com.sda.frame.other.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alipay.euler.andfix.patch.PatchManager;
import com.sda.frame.MyApplication;
import com.sda.frame.R;
import com.sda.frame.model.VersionData;
import com.sda.lib.HttpCore.DataResponse;
import com.sda.lib.util.FileUtil;
import com.sda.lib.util.FixUtil;
import com.sda.lib.util.Tools;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import java.io.IOException;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    Context context ;

    TextView listView ;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context =HomeActivity.this ;

        listView = (TextView) findViewById(R.id.listview);
        btn = (Button) findViewById(R.id.btn_ok);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok:
                listView.setText("I am new");
                Tools.msgbox(context,"I am new");
                break;
        }
    }
}
