package com.lzh.swapperdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzh.swapper.widget.SwapperAnannotation;


/**
 * Created by lzh on 2017/12/11.
 */

@SwapperAnannotation(attr = Fragment.class,value = "FourthFragment")
public class FourthFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fourth, container, false);
        return rootView;
    }
}
