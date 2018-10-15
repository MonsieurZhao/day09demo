package day09_demo1.zhaoyufeng.bwie.com.day09_demo1.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.WriterException;
import com.squareup.picasso.Picasso;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.android.PermissionUtils;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.encode.CodeCreator;

import java.util.ArrayList;
import java.util.List;

import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.R;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.MainActivity;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.model.BeanBanner;
import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.view.AppDeleage;
import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.net.HttpHelperUtils;

import static android.support.v4.app.ActivityCompat.startActivityForResult;


public class FristFragmentPresenter extends AppDeleage {
    private int SUCCESS=1000;
    private ViewPager banner;
    private LinearLayout mLayout;
    private EditText insert;
    private ImageView mImg;
    private final int SAOYISAO=100;

    @Override
    public int getLayouId() {
        return R.layout.fragment_frist;
    }

    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }
    private List<BeanBanner.DataBean> list = new ArrayList<>();
    @Override
    public void initData() {
        banner = get(R.id.vp_banner_frist);
        mLayout = get(R.id.ll_layout_frist);
        mImg = get(R.id.iv_img_frist);
        insert = get(R.id.ed_insert_frist);
        setList(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCreateImg(0);

            }
        },R.id.bt_create_frist);
        setList(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scan();


            }
        }, R.id.tv_scan_frist);
        doHttp();

    }

    private void Scan() {
        PermissionUtils.permission(context, new PermissionUtils.PermissionListener() {
            @Override
            public void success() {
                Intent intent = new Intent(context, CaptureActivity.class);
                ((MainActivity)context).startActivityForResult(intent,0);
            }
        });
    }


    private void mCreateImg(int i) {
        String result = insert.getText().toString().trim();
        if(TextUtils.isEmpty(result)){
            Toast.makeText(context,"请输入内容",Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap bitmap=BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher);
        if(i==0){
            bitmap=null;
        }
        try {
            Bitmap bitmap1 = CodeCreator.createQRCode(result, 200, 200, bitmap);
            mImg.setImageBitmap(bitmap1);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


    private void doHttp() {
        String url="https://www.zhaoapi.cn/ad/getAd";
        new HttpHelperUtils(new HttpHelperUtils.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                BeanBanner beanBanner = gson.fromJson(data, BeanBanner.class);
                List<BeanBanner.DataBean> data1 = beanBanner.getData();
                list.addAll(data1);
                handler.sendEmptyMessageDelayed(SUCCESS,2000);
                MyAdapterBanner myAdapterBanner = new MyAdapterBanner();
                banner.setAdapter(myAdapterBanner);
            }

            @Override
            public void error() {

            }
        }).get(url);
    }

    private class MyAdapterBanner extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            Picasso.with(context).load(list.get(position).getIcon()).fit().into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==SUCCESS){
                int currentItem = banner.getCurrentItem();
                currentItem++;
                if(currentItem>=list.size()){
                    currentItem=0;
                }
                banner.setCurrentItem(currentItem);
                handler.sendEmptyMessageDelayed(SUCCESS,2000);
            }
        }
    };


}
