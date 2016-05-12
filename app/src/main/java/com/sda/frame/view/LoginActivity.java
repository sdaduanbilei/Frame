package com.sda.frame.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.sda.frame.R;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by scorpio on 16/3/17.
 */
public class LoginActivity extends AppCompatActivity {

    @ViewInject(id = R.id.et_name)
    EditText et_name ;
    @ViewInject(id = R.id.et_pwd)
    EditText et_pwd ;
    @ViewInject(id =R.id.btn_login)
    Button btn_login ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FinalActivity.initInjectedView(this);
    }
}
