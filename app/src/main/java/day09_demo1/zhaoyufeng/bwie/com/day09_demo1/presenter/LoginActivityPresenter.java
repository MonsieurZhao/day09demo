package day09_demo1.zhaoyufeng.bwie.com.day09_demo1.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.R;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.InsertActivity;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.LoginActivity;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.MainActivity;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.MyFragment;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.ShowActivity;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.model.BeanLogin;
import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.view.AppDeleage;
import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.net.HttpHelperUtils;

public class LoginActivityPresenter extends AppDeleage {

    private EditText mName,mPwd;
    private SharedPreferences sp;
    private TextView insert;

    @Override
    public int getLayouId() {
        return R.layout.activity_login;
    }
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }
    @Override
    public void initData() {
        mName = get(R.id.et_name_login);
        mPwd = get(R.id.et_pwd_login);
        setList(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)context).finish();
            }
        },R.id.iv_img_login);
        setList(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        }, R.id.bt_login_login);
        setList(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InsertActivity.class);
                context.startActivity(intent);
            }
        },R.id.tv_insert_login);

        sp = context.getSharedPreferences("config", context.MODE_PRIVATE);

    }

    private void login() {

        String name = mName.getText().toString();
        String pwd = mPwd.getText().toString().trim();
        setList(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyFragment.class);
                context.startActivity(intent);
            }
        },R.id.iv_img_login);
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
            Toast.makeText(context,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        new HttpHelperUtils(new HttpHelperUtils.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                BeanLogin beanLogin = gson.fromJson(data, BeanLogin.class);
                if("0".equals(beanLogin.getCode())){
                    String mobile = beanLogin.getData().getMobile();
                    String password = beanLogin.getData().getPassword();
                    String nickname = (String) beanLogin.getData().getNickname();
                    sp.edit().putString("name",mobile).putString("pass",password).putString("nicname",nickname).commit();
                    Intent intent = new Intent(context, ShowActivity.class);
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context,beanLogin.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void error() {

            }
        }).get("https://www.zhaoapi.cn/user/login"+"?mobile="+name+"&password="+pwd);
    }
}
