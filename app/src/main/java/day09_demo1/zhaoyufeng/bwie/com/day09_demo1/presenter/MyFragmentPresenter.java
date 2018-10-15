package day09_demo1.zhaoyufeng.bwie.com.day09_demo1.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.R;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.LoginActivity;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.ShowActivity;
import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.view.AppDeleage;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragmentPresenter extends AppDeleage {

    private TextView textView;
    private CircleImageView imageView;
    private SharedPreferences sp;
    private String name;

    @Override
    public int getLayouId() {
        return R.layout.fragment_my;
    }

    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        imageView = get(R.id.iv_img_my);
        textView= get(R.id.tv_text_my);
        name = sp.getString("name", "");
        if(!TextUtils.isEmpty(name)){
            textView.setText(name);
        }else{
            textView.setText("登录");
        }
        setList(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        },R.id.tv_text_my);




    }

    private void login() {

        if(!TextUtils.isEmpty(name)){
//            if(!TextUtils.isEmpty(sp.getString("img",""))){
//                Picasso.with(context).load(sp.getString("img","")).fit().into(imageView);
//            }else{
//                imageView.setImageResource(R.mipmap.ic_launcher);
//            }
            Intent intent = new Intent(context, ShowActivity.class);
            context.startActivity(intent);

        }else{
            imageView.setImageResource(R.mipmap.ic_launcher);

            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }

    }
}
