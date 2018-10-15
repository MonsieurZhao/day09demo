package day09_demo1.zhaoyufeng.bwie.com.day09_demo1.presenter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.FristFragment;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.MainActivity;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity.MyFragment;
import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.view.AppDeleage;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.R;
public class MainActivityPresenter extends AppDeleage implements View.OnClickListener{

    private ViewPager viewPager;
    private TextView frist;
    private TextView my;

    @Override
    public int getLayouId() {
        return R.layout.activity_main;
    }
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }
    private List<Fragment> list = new ArrayList<>();
    @Override
    public void initData() {
        viewPager = get(R.id.vp_view_main);
        frist = get(R.id.tv_frist_main);
        my = get(R.id.tv_my_main);
        setList(this,R.id.tv_frist_main);
        setList(this,R.id.tv_my_main);
        list.add(new FristFragment());
        list.add(new MyFragment());
        MyAdapterFrist myAdapterFrist = new MyAdapterFrist(((MainActivity) context).getSupportFragmentManager());
        viewPager.setAdapter(myAdapterFrist);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        frist.setTextColor(Color.parseColor("#d43c3c"));
                        my.setTextColor(Color.parseColor("#000000"));
                        break;
                    case 1:
                        frist.setTextColor(Color.parseColor("#000000"));
                        my.setTextColor(Color.parseColor("#d43c3c"));
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_frist_main:
                viewPager.setCurrentItem(0);
                frist.setTextColor(Color.parseColor("#d43c3c"));
                my.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.tv_my_main:
                viewPager.setCurrentItem(1);
                frist.setTextColor(Color.parseColor("#000000"));
                my.setTextColor(Color.parseColor("#d43c3c"));
                break;
        }
    }

    private class MyAdapterFrist extends FragmentPagerAdapter{


        public MyAdapterFrist(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

}
