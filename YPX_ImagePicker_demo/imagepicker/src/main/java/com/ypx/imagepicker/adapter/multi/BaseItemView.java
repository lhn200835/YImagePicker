package com.ypx.imagepicker.adapter.multi;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.bean.PickerSelectConfig;
import com.ypx.imagepicker.bean.PickerUiConfig;
import com.ypx.imagepicker.presenter.IMultiPickerBindPresenter;

import java.util.ArrayList;

/**
 * Time: 2019/8/8 15:42
 * Author:ypx
 * Description:
 */
public abstract class BaseItemView extends LinearLayout {
    protected View view;

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void bindData(ImageItem imageItem,
                                     RecyclerView.Adapter adapter,
                                     int position,
                                     ArrayList<ImageItem> selectImageList,
                                     MultiGridAdapter.OnActionResult result);


    protected BaseItemView(Context context) {
        super(context);
        init();
    }

    private void init() {
        view = LayoutInflater.from(getContext()).inflate(getLayoutId(), this, true);
        initView(view);
    }

    public void initData(PickerSelectConfig selectConfig, IMultiPickerBindPresenter presenter,PickerUiConfig uiConfig) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.height = getScreenWidth() / selectConfig.getColumnCount() - dp(2);
        setLayoutParams(params);
        view.setLayoutParams(params);
    }


    protected void setLayoutParams(RelativeLayout.LayoutParams params) {
        params.leftMargin = dp(1);
        params.topMargin = dp(1);
        params.rightMargin = dp(1);
        params.bottomMargin = dp(1);
    }

    protected int dp(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                 dp, getContext().getResources().getDisplayMetrics());
    }

    protected int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        assert wm != null;
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
