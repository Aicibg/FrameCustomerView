package com.aicibg.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by DongHao on 2016/11/10
 * Description:自定义弹窗，只需传入一个View作为Dialog显示样式，并可自定义弹窗的位置
 */

public class TDDialog extends DialogFragment {
    private View contentView;
    public int gravity;
    public int mWidth;
    public int mHeight;
    private boolean isShow = false;
    private static Activity mContext;
    private static TDDialog fragment;

    public enum GRAVITY_TYPE {
        CENTER, BOTTOM, TOP
    }

    public static TDDialog build(Activity context, View view) {
        if (fragment == null) {
            fragment = new TDDialog();
        }
        mContext = context;
        fragment.contentView = view;
        return fragment;
    }

    public TDDialog setStyle(GRAVITY_TYPE style) {
        switch (style) {
            case CENTER:
                gravity = Gravity.CENTER;
                break;
            case BOTTOM:
                gravity = Gravity.BOTTOM;
                break;
            case TOP:
                gravity = Gravity.TOP;
                break;
            default:
                break;
        }
        return this;
    }

    public TDDialog setWidth(int width) {
        mWidth = width;
        return this;
    }

    public TDDialog setHeight(int height) {
        mHeight = height;
        return this;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(R.color.colorTrans);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = gravity;
        lp.width = mWidth;
        lp.height = mHeight;

        window.setAttributes(lp);
        return dialog;
    }

    public void showDialog() {
        if (!isShow) {
            this.show(mContext.getFragmentManager(), "Tag");
            isShow = true;
        }
    }

    public void hideDialog() {
        if (isShow) {
            this.dismiss();
            isShow = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isShow = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isShow = false;
    }
}
