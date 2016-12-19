package com.aicibg.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by DongHao on 2016/12/19.
 * Description:显示键值对形式的TextView
 */

public class MapTextView extends TextView {
    private String keyText;
    private String valueText;
    private int keyTextColor;
    private int valueTextColor;
    private int keyTextSize;
    private int valueTextSize;

    public MapTextView(Context context) {
        this(context, null);
    }

    public MapTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MapTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public String getKeyText() {
        return keyText;
    }

    public void setKeyText(String keyText) {
        this.keyText = keyText;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public int getKeyTextColor() {
        return keyTextColor;
    }

    public void setKeyTextColor(int keyTextColor) {
        this.keyTextColor = keyTextColor;
    }

    public int getValueTextColor() {
        return valueTextColor;
    }

    public void setValueTextColor(int valueTextColor) {
        this.valueTextColor = valueTextColor;
    }

    public int getKeyTextSize() {
        return keyTextSize;
    }

    public void setKeyTextSize(int keyTextSize) {
        this.keyTextSize = keyTextSize;
    }

    public int getValueTextSize() {
        return valueTextSize;
    }

    public void setValueTextSize(int valueTextSize) {
        this.valueTextSize = valueTextSize;
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typeArray = getContext().obtainStyledAttributes(attrs, R.styleable.MapTextView);
            keyText = typeArray.getString(R.styleable.MapTextView_keyText);
            valueText = typeArray.getString(R.styleable.MapTextView_valueText);
            keyTextColor = typeArray.getColor(R.styleable.MapTextView_keyTextColor, Color.parseColor("#3F51B5"));
            valueTextColor = typeArray.getColor(R.styleable.MapTextView_valueTextColor, Color.parseColor("#3F51B5"));
            keyTextSize = (int) typeArray.getDimension(R.styleable.MapTextView_keyTextSize, dp2px(13));
            valueTextSize = (int) typeArray.getDimension(R.styleable.MapTextView_valueTextSize, dp2px(13));
        }
    }


    public void invalidateMapText() {
        setMapText();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setMapText();
    }

    public void setMapText() {
        SpannableStringBuilder spannableBuilder = new SpannableStringBuilder();
        spannableBuilder.append(keyText);
        spannableBuilder.append(valueText);
        ForegroundColorSpan groundKeyColor = new ForegroundColorSpan(keyTextColor);
        spannableBuilder.setSpan(groundKeyColor, 0, keyText.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        AbsoluteSizeSpan spanKeySize = new AbsoluteSizeSpan(keyTextSize);
        spannableBuilder.setSpan(spanKeySize, 0, keyText.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        ForegroundColorSpan groundValueColor = new ForegroundColorSpan(valueTextColor);
        spannableBuilder.setSpan(groundValueColor, keyText.length(), keyText.length() + valueText.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        AbsoluteSizeSpan spanValueSize = new AbsoluteSizeSpan(valueTextSize);
        spannableBuilder.setSpan(spanValueSize, keyText.length(), keyText.length() + valueText.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        super.setText(spannableBuilder);
    }

    /**
     * 将 dp 转换为 px
     *
     * @param dp
     * @return
     */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

}
