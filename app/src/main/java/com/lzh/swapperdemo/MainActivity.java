package com.lzh.swapperdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lzh.swapper.controller.ClassUtils;
import com.lzh.swapper.controller.Swapper;
import com.lzh.swapper.widget.SwapperAnannotation;
import com.lzh.swapper.widget.SwapperButton;

@SwapperAnannotation(attr = Activity.class, value = "MainActivity")
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SwapperButton button = (SwapperButton) findViewById(R.id.main_btn_setting);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("lzh","onclick:"+button.getText());
            }
        });
        Swapper swapper = new Swapper(this, (FrameLayout) findViewById(R.id.main_fm));
    }
}
