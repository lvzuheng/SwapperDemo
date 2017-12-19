package com.lzh.swapperdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lzh.swapper.controller.Swapper;
import com.lzh.swapper.widget.SwapperAnannotation;

/**
 * Created by lzh on 2017/12/16.
 */

@SwapperAnannotation(attr = Activity.class, value = "SecondActivity")
public class SecondActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Swapper swapper = new Swapper(this);
    }
}
