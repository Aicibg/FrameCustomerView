package com.aicibg.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/23.
 */

public class InputTextView extends LinearLayout {
    private Context mContext;
    private TextView leftTV;//左边textView
    private EditText rightEdit;//右边editView
    private LayoutParams leftTextParams, rightEditParams;
    private int leftWeight = 1;
    private int rightWeight = 1;
    private int leftTVColor= Color.parseColor("#FF4081");
    private int leftTVSize=16;
    private String leftTextString="姓名:";
    private boolean isSingLines = false;
    private String rightEditHint="请输入姓名";
    private int rightEditColor= Color.parseColor("#FF4081");
    private int rightEditSize=16;
    private int maxLines = 1;//最多几行    默认显示一行
    private int maxEms = 10;//最多几个字    默认显示10个汉子

    public InputTextView(Context context) {
        this(context, null);
    }

    public InputTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        getAttrs(attrs);
        initLayout();
    }

    private void getAttrs(AttributeSet attrs) {

    }

    private void initLayout() {
        initLeftText();
        initRightEdit();
    }

    private void initRightEdit() {
        rightEdit = new EditText(mContext);
        rightEditParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightEditParams.weight = rightWeight;
        rightEdit.setLayoutParams(rightEditParams);
        rightEdit.setHint(rightEditHint);

        setEditTextParams(rightEdit, isSingLines, maxLines, maxEms);
        rightEdit.setTextColor(rightEditColor);
        rightEdit.setTextSize(TypedValue.COMPLEX_UNIT_SP,rightEditSize);
        addView(rightEdit);
    }

    private void setEditTextParams(EditText editText, boolean isSingLines, int maxLines, int maxEms) {
        editText.setSingleLine(isSingLines);
        editText.setMaxLines(maxLines);
        editText.setMaxEms(maxEms);
        editText.setEllipsize(TextUtils.TruncateAt.END);
    }

    private void initLeftText() {
        leftTV = new TextView(mContext);
        leftTextParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //leftTextParams.weight = leftWeight;
        leftTV.setLayoutParams(leftTextParams);
        leftTV.setText(leftTextString);

        setTextViewParams(leftTV, isSingLines, maxLines, maxEms);

        setTextColor(leftTV, leftTVColor);
        setTextSize(leftTV, leftTVSize);
        addView(leftTV);
    }

    /**
     * 设置通用的textView显示效果属性
     *
     * @param textView    view
     * @param isSingLines 是否单行显示
     * @param maxLines    显示最大行
     * @param maxEms      最多显示多少个字
     */
    private void setTextViewParams(TextView textView, boolean isSingLines, int maxLines, int maxEms) {
        textView.setSingleLine(isSingLines);
        textView.setMaxLines(maxLines);
        textView.setMaxEms(maxEms);
        textView.setEllipsize(TextUtils.TruncateAt.END);
    }

    /**
     * 设置文字的字体大小
     *
     * @param textView textView对象
     * @param size     文字大小
     */
    private void setTextSize(TextView textView, int size) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    /**
     * 设置文字的颜色
     *
     * @param textView textView对象
     * @param color    文字颜色
     */
    private void setTextColor(TextView textView, int color) {
        textView.setTextColor(color);
    }
}
