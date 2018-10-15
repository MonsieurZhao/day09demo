package day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import day09_demo1.zhaoyufeng.bwie.com.mvplibrary.mvp.view.AppDeleage;

public abstract class BaseFragmentPresenter<T extends AppDeleage> extends Fragment {

    private T delegate;

    public abstract Class<T> getClassActivityPrese();
    public BaseFragmentPresenter(){
        try {
            delegate = getClassActivityPrese().newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        delegate.create(getLayoutInflater(),null,savedInstanceState);
        delegate.getContext(getActivity());
        delegate.initData();

        return delegate.view();
    }
}
