package day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity;

import android.os.Bundle;

import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.presenter.FristFragmentPresenter;
import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.presenter.BaseFragmentPresenter;

public class FristFragment extends BaseFragmentPresenter<FristFragmentPresenter> {
    @Override
    public Class<FristFragmentPresenter> getClassActivityPrese() {
        return FristFragmentPresenter.class;

    }

    public void getAug(){
        Bundle bundle = getArguments();
        String test = bundle.getString("test");
    }
}
