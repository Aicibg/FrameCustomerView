package com.aicibg.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.aicibg.view.utils.DensityUtils;


/**
 * AddPopWindow Created on 2016/8/17-9:33
 * Description:PopWindow
 * Created by DongHao
 */

public class AddPopWindow extends PopupWindow {
    private int width = -1;
    private int mScreenWidth;
    private Activity mActivity;

    @Override
    public void setWidth(int width) {
        this.width = width;
        super.setWidth(this.width);
    }

    public AddPopWindow(Activity context, View contentView) {
        mActivity = context;
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        int height = metrics.heightPixels;
        this.setContentView(contentView);
        if (width == -1) {
            this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            this.setWidth(DensityUtils.dp2px(context, width));
        }
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setOutsideTouchable(true);
//        this.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams params=mActivity.getWindow().getAttributes();
//                params.alpha=1.0f;
//                mActivity.getWindow().setAttributes(params);
//            }
//        });
        this.setFocusable(true);

        ColorDrawable colorDrawable = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(colorDrawable);
        this.update();
    }

    /**
     * 显示PopWindow(靠右侧边框)
     *
     * @param view
     */
    public void showPopWindowRight(View view) {
        if (!this.isShowing()) {
            this.showAsDropDown(view, mScreenWidth / 3 * 2 - 15, 5);
        } else {
            this.dismiss();
        }
    }

    /**
     * 显示PopWindow,在控件正下方
     *
     * @param view
     */
    public void showPopWindow(View view) {
        this.setWidth(view.getMeasuredWidth());
        if (!this.isShowing()) {
            this.showAsDropDown(view, 0, 0);
        } else {
            this.dismiss();
        }
    }

    /**
     * 显示PopWindow(靠左侧边框)
     *
     * @param view
     */
    public void showPopWindowLeft(View view) {
        this.setWidth(width);
        if (!this.isShowing()) {
            this.showAsDropDown(view, 0, 0);
        } else {
            this.dismiss();
        }
    }

//    @Override
//    public void dismiss() {
//        super.dismiss();
//        WindowManager.LayoutParams params=mActivity.getWindow().getAttributes();
//        params.alpha=1.0f;
//        mActivity.getWindow().setAttributes(params);
//    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
        //设置所在activity的透明度
//        WindowManager.LayoutParams params=mActivity.getWindow().getAttributes();
//        params.alpha=0.7f;
//        mActivity.getWindow().setAttributes(params);
    }
}
