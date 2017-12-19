package com.lzh.swapperdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzh.swapper.widget.SwapperAnannotation;


/**
 * Created by lzh on 2017/12/11.
 */

@SwapperAnannotation(attr = Fragment.class,value = "ThirdFragment")
public class ThirdFragment extends Fragment{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = inflater.inflate(R.layout.fragment_third,container,false);
        return rootView;
    }


}
