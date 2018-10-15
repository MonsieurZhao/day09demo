package day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity;

import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.presenter.LoginActivityPresenter;
import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.presenter.BaseActivityPresenter;

public class LoginActivity extends BaseActivityPresenter<LoginActivityPresenter> {
    @Override
    public Class<LoginActivityPresenter> getClassActivityPrese() {
        return LoginActivityPresenter.class;
    }
}
