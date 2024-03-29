package com.exciter.androidbasic.widget.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomLinearItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 纵向布局
     */
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    /**
     * 横向布局
     */
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    private final Drawable mDivider;
    private int mOrientation;
    private final int mIndent;

    /**
     * 构造方法
     *
     * @param context     上下文
     * @param orientation 列表方向
     * @param drawable    分割线样式
     * @param indent      缩进值
     */
    public CustomLinearItemDecoration(Context context, int orientation, int drawable, int indent) {
        mDivider = context.getResources().getDrawable(drawable);
        this.mIndent = indent;
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != VERTICAL_LIST && orientation != HORIZONTAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }

    /**
     * 绘制纵向列表的分割线
     *
     * @param c      画布
     * @param parent 画笔
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(mIndent > 0 ? left + mIndent : left, top, mIndent > 0 ? right - mIndent : right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 绘制横向列表的分割线
     *
     * @param c      画布
     * @param parent 画笔
     */
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, mIndent > 0 ? top + mIndent : top, right, mIndent > 0 ? bottom - mIndent : bottom);
            mDivider.draw(c);
        }
    }
}
