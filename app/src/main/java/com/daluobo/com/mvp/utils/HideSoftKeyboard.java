package com.daluobo.com.mvp.utils;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by chengmenghui on 2017/12/27.
 */

public class HideSoftKeyboard {

    public  static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left) || !(event.getX() < right)
                    || !(event.getY() > top) || !(event.getY() < bottom);
        }
        return false;
    }
}
