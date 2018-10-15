package day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.view.AppDeleage;

public abstract class BaseActivityPresenter<T extends AppDeleage> extends AppCompatActivity {

    private T delegate;

    public abstract Class<T> getClassActivityPrese();
    public BaseActivityPresenter(){
        try {
            delegate = getClassActivityPrese().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate.create(getLayoutInflater(),null,savedInstanceState);
        setContentView(delegate.view());
        delegate.getContext(this);
        delegate.initData();

    }
}
