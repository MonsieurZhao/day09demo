package day09_demo1.zhaoyufeng.bwie.com.day09_demo1.activity;

import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.presenter.LoginActivityPresenter;
import day09_demo1.zhaoyufeng.bwie.com.day09_demo1.presenter.ShowActivityPresenter;
import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.presenter.BaseActivityPresenter;

public class ShowActivity extends BaseActivityPresenter<ShowActivityPresenter> {
    @Override
    public Class<ShowActivityPresenter> getClassActivityPrese() {
        return ShowActivityPresenter.class;
    }
}
