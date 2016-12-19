package com.aicibg.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class ClearEditText extends EditText implements TextWatcher,
        OnFocusChangeListener {

    /**
     * 左右两侧图片资源
     */
    private Drawable left, right;
    /**
     * 是否获取焦点，默认没有焦点
     */
    private boolean hasFocus = false;
    private boolean isPassword = false;
    /**
     * 手指抬起时的X坐标
     */
    private int xUp = 0;
    private int xDown;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWedgits(attrs);
    }

    /**
     * 初始化各组件
     *
     * @param attrs 属性集
     */
    private void initWedgits(AttributeSet attrs) {
        try {
            left = getCompoundDrawables()[0];
            right = getCompoundDrawables()[2];
            initDatas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        try {
            // 第一次显示，隐藏删除图标

            setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
            addListeners();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加事件监听
     */
    private void addListeners() {
        try {
            setOnFocusChangeListener(this);
            addTextChangedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        if (getText() != null) {
            setCompoundDrawablesWithIntrinsicBounds(left, null, right, null);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int after) {
        if (hasFocus) {
            if (TextUtils.isEmpty(s)) {
                // 如果为空，则不显示删除图标
                setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
            } else {
                // 如果非空，则要显示删除图标
                if (null == right) {
                    right = getCompoundDrawables()[2];
                }
                setCompoundDrawablesWithIntrinsicBounds(left, null, right, null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    xDown = (int) event.getX();
                    //点击位置是否在右侧图标上
                    if ((getWidth() - xDown) <= getCompoundPaddingRight()) {
                        //判断inputtype类型
                        if (getInputType() == (InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT)) {
                            setInputType(InputType.TYPE_CLASS_TEXT);
                            setSelection(getText().length());//设置光标显示在文本内容的末尾
                            isPassword = true;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    // 获取点击时手指抬起的X坐标
                    xUp = (int) event.getX();
                    //判断当前是否是密码输入
                    if (isPassword) {
                        setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                        isPassword = false;
                        setSelection(getText().length());
                    }
                    // 当点击的坐标到当前输入框右侧的距离小于等于getCompoundPaddingRight()的距离时，则认为是点击了删除图标
                    // getCompoundPaddingRight()的说明：Returns the right padding of the itemView, plus space for the right Drawable if any.
                    else if ((getWidth() - xUp) <= getCompoundPaddingRight()) {
                        Log.e("test", "inputType=" + getInputType());
                        if (!TextUtils.isEmpty(getText().toString())) {
                            setText("");
                            // 如果为空，则不显示删除图标
                            setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                        }
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        try {
            this.hasFocus = hasFocus;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
