package day09_demo1.zhaoyufeng.bwie.com.day09_demo1.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.R;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.LoginActivity;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.MainActivity;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.MyFragment;
import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.view.AppDeleage;

public class ShowActivityPresenter extends AppDeleage {

    private TextView mName,mNicname;
    private SharedPreferences sp;

    @Override
    public int getLayouId() {
        return R.layout.activity_show;
    }
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }
    @Override
    public void initData() {
        mName = get(R.id.tv_name_show);
        mNicname = get(R.id.tv_nicname_show);
        setList(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        },R.id.iv_img_show);
        setList(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        },R.id.bt_login_show);
        sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        mName.setText(sp.getString("name",""));
        mNicname.setText(sp.getString("nicname",""));
    }
}
