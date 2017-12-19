package com.lzh.swapper.controller;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lzh.swapper.widget.SwapperAnannotation;
import com.lzh.swapper.widget.SwapperButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by lzh on 2017/12/11.
 */

public class Swapper {


    private static Fragment fragmentOn;
    private static Map<String, Object> swapperMap = new HashMap<>();
    private Context context;
    private FrameLayout frameLayout;


    public Swapper(Context context, FrameLayout frameLayout) {
        this.context = context;
        this.frameLayout = frameLayout;
        View view = ((Activity) context).getWindow().getDecorView();
        loadAnannotation();
        initView(view);
    }

    public Swapper(Context context) {
        this(context, null);
    }

    public Swapper(View view) {
        this.context = view.getContext();
        loadAnannotation();
        initView(view);
    }

    public void transForm(@NonNull Class<Activity> activity) {

        context.startActivity(new Intent(context, activity));

    }

    public void transForm(String swapper) {
        Object o = swapperMap.get(swapper);
        if (o instanceof Fragment)
            transForm((Fragment) o);
    }

    public void tranForm(View view) {
        if (view instanceof SwapperButton) {
            SwapperButton swapperButton = (SwapperButton) view;
            if (swapperButton.getSwapper() != null) {
                Object o = swapperMap.get(swapperButton.getSwapper());
                if (o instanceof Fragment) {
                    transForm((Fragment) o);
                } else if (o instanceof Class<?>) {
                    transForm((Class<Activity>) o);
                }
            }
        }
    }

    public void transForm(@NonNull Fragment fragment) {
        if (frameLayout == null)
            return;

        FragmentTransaction fragmentTransaction = ((Activity) context).getFragmentManager().beginTransaction();
        if (fragmentOn != null) {
            fragmentTransaction.hide(fragmentOn);
        }
        try {
            if (!fragment.isAdded()) {
                fragmentTransaction.add(frameLayout.getId(), fragment).show(fragment).commit();
            } else {
                fragmentTransaction.show(fragment).commit(); // 隐藏当前的fragment，显示下一个
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragmentOn = fragment;
    }

    private void loadAnannotation() {
        try {
            List<Class> cList = ClassUtils.getAllClass(this.context);
            for (int i = 0; i < cList.size(); i++) {
                if (cList.get(i).isAnnotationPresent(SwapperAnannotation.class)) {
                    SwapperAnannotation sw = ((SwapperAnannotation) cList.get(i).getAnnotation(SwapperAnannotation.class));
                    if ((sw.attr().equals(Activity.class) && sw.value() != null)) {
                        swapperMap.put(sw.value(), cList.get(i));
                    } else if (sw.attr().equals(Fragment.class) && sw.value() != null) {
                        swapperMap.put(sw.value(), cList.get(i).newInstance());
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    private void initView(View view) {

        if (view instanceof ViewGroup) {

            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                if (viewchild instanceof SwapperButton) {

                    SwapperButton valueButton = (SwapperButton) viewchild;
                    valueButton.setOnSwapperListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Object o;

                            if ((o = swapperMap.get(((SwapperButton) v).getSwapper())) != null) {
                                if (o instanceof Fragment) {
                                    transForm((Fragment) o);
                                } else if (o instanceof Class) {
                                    transForm((Class<Activity>) o);
                                }
                            }
                        }
                    });
                    if (valueButton.isFirstDisplay() && swapperMap.get(((SwapperButton) valueButton).getSwapper()) instanceof Fragment) {
                        valueButton.callOnSwapper();
                    }
                } else if (viewchild instanceof ViewGroup) {
                    initView(viewchild);
                }
            }

        }

    }
}
