package com.lzh.swapper.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lzh.swapper.R;


/**
 * Created by lzh on 2017/12/11.
 */

@SuppressLint("AppCompatCustomView")
public class SwapperButton extends Button {

    private String swapper;
    private boolean firstDisplay;
    private OnClickListener onClick;


    public SwapperButton(Context context) {
        super(context);

    }

    public SwapperButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwapperButton);
        swapper = a.getString(R.styleable.SwapperButton_swapper);
        firstDisplay = a.getBoolean(R.styleable.SwapperButton_firstDisplay, false);
        a.recycle();
    }

    public SwapperButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwapperButton);
        swapper = a.getString(R.styleable.SwapperButton_swapper);
        firstDisplay = a.getBoolean(R.styleable.SwapperButton_firstDisplay, false);
        a.recycle();

    }


    public String getSwapper() {
        return swapper;
    }

    public boolean isFirstDisplay() {
        return firstDisplay;
    }

    @Override
    public void setOnClickListener(final OnClickListener l) {
        if(l!=null){
            this.onClick = l;
        }
        super.setOnClickListener(getOnClickListener());
    }


    private OnClickListener swapperListener;

    public void setOnSwapperListener(OnClickListener onClickListener) {
        this.swapperListener = onClickListener;
        setOnClickListener(null);
    }

    public boolean callOnSwapper() {
        if (swapperListener != null) {
            swapperListener.onClick(this);
            return true;
        }
        return false;
    }

    private OnClickListener getOnClickListener() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(v);
                }
                if (swapperListener != null) {
                    swapperListener.onClick(v);
                }
            }
        };
    }

}
