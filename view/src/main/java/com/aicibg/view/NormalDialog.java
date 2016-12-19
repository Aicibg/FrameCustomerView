package com.aicibg.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.aicibg.view.utils.EmptyUtils;


/**
 * Created by DongHao on 2016/11/16.
 * Description:常用弹窗：标题，内容，确认和取消
 */

public class NormalDialog extends DialogFragment {
    private Activity mContext;
    private View contentView;
    private boolean isShow = false;
    private DialogListener mDialogListener;
    private TextView tvTitle, tvContent, btAccept, btCancel;
    private String title, content, acceptText, cancelText;
    private int titleColor, contentColor, acceptTextColor, cancelTextColor;
    private float mScreenDensity;
    private int mScreenHeight;
    private int mScreenWidth;

    public static NormalDialog newInstance(String title, String content) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("content", content);
        NormalDialog fragment = new NormalDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = (Activity) activity;
    }

    public interface DialogListener {
        void accept(View view);

        void cancel(View view);
    }

    public void setOnDialogListener(DialogListener dialogListener) {
        mDialogListener = dialogListener;
    }

    public NormalDialog() {
        super();
        initSetting();
    }

    private void initSetting() {
        titleColor = Color.parseColor("#000000");
        contentColor = Color.parseColor("#7d7e80");
        acceptTextColor = Color.parseColor("#289cdc");
        cancelTextColor = Color.parseColor("#289cdc");
    }


    public void setDialogTitle(String title) {
        this.title = title;
    }

    public void setDialogTitleSize(int size) {
        tvTitle.setTextSize(size);
    }

    public void setDialogContent(String content) {
        this.content = content;
    }

    public void setDialogContentSize(int size) {
        tvContent.setTextSize(size);
    }

    public void setAcceptText(String str) {
        acceptText = str;
    }

    public void setCancleText(String str) {
        cancelText = str;
    }

    public void setCancelTextColor(int cancelTextColor) {
        this.cancelTextColor = cancelTextColor;
    }

    public void setAcceptTextColor(int acceptTextColor) {
        this.acceptTextColor = acceptTextColor;
    }

    public void setContentColor(int contentColor) {
        this.contentColor = contentColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() != null) {
            title = getArguments().getString("title");
            content = getArguments().getString("content");
        }
        Dialog dialog = new Dialog(getActivity());
        contentView = View.inflate(getActivity(), R.layout.dialog_nomal, null);
        tvTitle = (TextView) contentView.findViewById(R.id.dialog_title);
        tvContent = (TextView) contentView.findViewById(R.id.dialog_content);
        btAccept = (TextView) contentView.findViewById(R.id.bt_accept);
        btCancel = (TextView) contentView.findViewById(R.id.bt_cancel);

        tvTitle.setTextColor(titleColor);
        tvContent.setTextColor(contentColor);
        btAccept.setTextColor(acceptTextColor);
        btCancel.setTextColor(cancelTextColor);

        if (!EmptyUtils.isEmpty(title)) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        }
        if (!EmptyUtils.isEmpty(content)) {
            tvContent.setText(content);
        }
        if (!EmptyUtils.isEmpty(acceptText)) {
            btAccept.setText(acceptText);
        }
        if (!EmptyUtils.isEmpty(cancelText)) {
            btCancel.setText(cancelText);
        }

        btAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogListener.accept(view);
                hideDialog();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogListener.cancel(view);
                hideDialog();
            }
        });

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(R.color.colorTrans);
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;
        lp.gravity = Gravity.CENTER;
        lp.width = mScreenWidth * 4 / 5;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        window.setAttributes(lp);
        return dialog;
    }

    public void showDialog(FragmentManager fragmentManager) {
        if (!isShow) {
            this.show(fragmentManager, "TAG");
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
