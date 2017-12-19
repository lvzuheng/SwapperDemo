package com.lzh.swapperdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lzh.swapper.controller.Swapper;
import com.lzh.swapper.widget.SwapperAnannotation;


/**
 * Created by lzh on 2017/12/8.
 */

@SwapperAnannotation(attr = Fragment.class, value = "FirstFragment")
public class FirstFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        Swapper swapper = new Swapper(rootView);
        return rootView;
    }

}
