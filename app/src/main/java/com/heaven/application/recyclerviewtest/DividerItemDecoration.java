package com.heaven.application.recyclerviewtest;

    import android.content.Context;
    import android.content.res.TypedArray;
    import android.graphics.Canvas;
    import android.graphics.Rect;
    import android.graphics.drawable.Drawable;
    import android.support.v7.widget.RecyclerView;
    import android.view.View;

    /**
     * ItemDecoration implementation that applies and inset margin
     * around each child of the RecyclerView. It also draws item dividers
     * that are expected from a vertical list implementation, such as
     * ListView.
     */
    public class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private static final int[] ATTRS = { android.R.attr.listDivider };

        private Drawable mDivider;
        private int mInsets;

        public DividerItemDecoration(Context context) {
            TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();

            mInsets = 20;
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            drawVertical(c, parent);
        }

        /** Draw dividers underneath each child view */
        public void drawVertical(Canvas c, RecyclerView parent) {

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();

                int left = child.getLeft() - params.leftMargin - mInsets;
                int right = child.getRight() + params.rightMargin + mInsets;

                int bottom = child.getBottom() + params.bottomMargin + mInsets;
                int top = bottom - mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);

                top = child.getTop() - params.topMargin - mInsets;
                bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);

                top = child.getTop() - params.topMargin - mInsets;
                bottom = child.getBottom() + params.bottomMargin + mInsets;

                left = child.getLeft() - params.leftMargin - mInsets;
                right = left + mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);

                right = child.getRight() + params.rightMargin + mInsets;
                left = right - mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //We can supply forced insets for each item view here in the Rect
            outRect.set(mInsets, mInsets, mInsets, mInsets);
        }
    }