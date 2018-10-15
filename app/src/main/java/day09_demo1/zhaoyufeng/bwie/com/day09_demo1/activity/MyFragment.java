package day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity;

import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.presenter.MyFragmentPresenter;
import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.presenter.BaseFragmentPresenter;

public class MyFragment extends BaseFragmentPresenter<MyFragmentPresenter> {
    @Override
    public Class<MyFragmentPresenter> getClassActivityPrese() {
        return MyFragmentPresenter.class;
    }
}
