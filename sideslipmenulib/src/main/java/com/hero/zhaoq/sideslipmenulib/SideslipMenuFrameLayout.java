package com.hero.zhaoq.sideslipmenulib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.hero.zhaoq.sideslipmenulib.exception.ChildCountException;

/**
 * author: zhaoqiang
 * date:2017/10/20 / 09:08
 * zhaoqiang:zhaoq_hero@163.com
 * <p>
 * sideslipmenu  framlayout   :
 */
public class SideslipMenuFrameLayout extends FrameLayout {

    private String TAG = "info";
    private ViewDragHelper viewDragHelper;
    private View menuView;
    private View itemView;

    protected Point orginPosition = new Point();//record the origin x,y
    protected Point menuPosition = new Point();//record the menu  Position x,y

    private int dragLeftLength;  //record  left length
    private int menuWidth; //record Width

    public SideslipMenuFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public SideslipMenuFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideslipMenuFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //第二个参数就是滑动灵敏度的意思 可以随意设置
        viewDragHelper = ViewDragHelper.create(this, 1.2f, callBack);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        //get child count if  not equal 2  throw exception:
        new ChildCountException("SideslipMenuFrameLayout child count must be equal 2", childCount, 2);
        //init data：   if count  equal 2
        Log.i(TAG, "onFinishInflate:" + childCount);
        menuView = getChildAt(0);
        itemView = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        menuWidth = menuView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//        //relayout child view
//        Log.i(TAG, "left:" + itemView.getLeft() + "top:" + itemView.getTop() +
//                "right:" + itemView.getRight() + "bottom:" + itemView.getBottom());
//        Log.i(TAG, "left:" + menuView.getLeft() + "top:" + menuView.getTop() +
//                "right:" + menuView.getRight() + "bottom:" + menuView.getBottom());
        // origin position in parent
        orginPosition.x = itemView.getLeft();
        orginPosition.y = itemView.getTop();

        menuPosition.x = menuView.getLeft();
        menuPosition.y = menuView.getTop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }


    //TODO  handle the touchEvent callback：
    ViewDragHelper.Callback callBack = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(View view, int i) {
//            Log.i(TAG, "handle event");
            return view == itemView;
        }

        //handle the horizontal drag  只检测水平滑动：
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //drag right we can not handle   and   left > menu width we can not touch event
            if (left > 0) {
                dragLeftLength = 0;
                return dragLeftLength;
            }
            if (Math.abs(left) > menuWidth) {
                dragLeftLength = -menuWidth;
                return dragLeftLength;
            }
            dragLeftLength = left;
            return left;
        }

        //handle the vertical drag
//        @Override
//        public int clampViewPositionVertical(View child, int top, int dy) {
//            return top;
//        }

        //pinter release   手指释放后  处理   判断：
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
//            Log.i(TAG, xvel + "======================" + yvel);
            //release touch event:
            if (releasedChild == itemView) {
                //if drag length > menu's width * 0.6     itemview  Restore the original state
                //if drag length < menu's width * 0.4     itemview  scroll to rigth
//                Log.i("info",Math.abs(dragLeftLength) + "========" + menuWidth);
                if (Math.abs(dragLeftLength) > menuWidth * 0.6) {
                    //恢复位置：
                    viewDragHelper.settleCapturedViewAt(-menuWidth, orginPosition.y);
                    invalidate();
                } else {
                    //恢复位置：
                    viewDragHelper.settleCapturedViewAt(orginPosition.x, orginPosition.y);
                    invalidate();
                }
            }
        }
    };

    //平滑   滚动效果：：
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (viewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    //------------------------no use-------------------------------------------------------
//    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        int childCount = getChildCount();
//        Log.i(TAG, "onAttachedToWindow:" + childCount);
//    }
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        int childCount = getChildCount();
//        Log.i(TAG, "onDraw:" + childCount);
//    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        postInvalidate();
    }

}
