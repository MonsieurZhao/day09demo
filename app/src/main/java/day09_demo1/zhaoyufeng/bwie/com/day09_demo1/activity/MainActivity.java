package day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity;


import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import com.yzq.zxinglibrary.common.Constant;

import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.presenter.MainActivityPresenter;
import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.presenter.BaseActivityPresenter;

public class MainActivity extends BaseActivityPresenter<MainActivityPresenter> {

    @Override
    public Class<MainActivityPresenter> getClassActivityPrese() {
        return MainActivityPresenter.class;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&requestCode==RESULT_OK){
            String text = data.getStringExtra(Constant.CODED_CONTENT);
            FristFragment fristFragment = new FristFragment();
            Bundle bundle = new Bundle();
            bundle.putString("text",text);
            fristFragment.setArguments(bundle);
        }
    }
}
