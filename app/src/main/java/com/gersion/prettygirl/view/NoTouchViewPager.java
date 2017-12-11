  
package com.gersion.prettygirl.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoTouchViewPager extends ViewPager {

    private boolean isTouchable = false;
    public NoTouchViewPager(Context context) {
        super(context);
        
    }
    
    public NoTouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isTouchable) {
            return super.onTouchEvent(ev);
        }else{
            return false;
        }
        
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (isTouchable) {
            return super.onInterceptTouchEvent(arg0);
        }else{
            return false;
        }
        
    }

}
  