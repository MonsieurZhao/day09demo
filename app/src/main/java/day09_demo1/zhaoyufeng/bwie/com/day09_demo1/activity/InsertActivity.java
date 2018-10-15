package day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity;

import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.presenter.InsertActivityPresenter;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.presenter.LoginActivityPresenter;
import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.presenter.BaseActivityPresenter;

public class InsertActivity extends BaseActivityPresenter<InsertActivityPresenter> {
    @Override
    public Class<InsertActivityPresenter> getClassActivityPrese() {
        return InsertActivityPresenter.class;
    }
}
