package chess.shy.chess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;

/**
 * Created by shiyao on 16-11-22.
 */
public class ChessBoard extends ViewGroup {
    private int mWidth;
    private int mHeight;
    private int mCellWidth;
    private int mCellHeight;
    private int mWidhtGap;
    private int mHeightGap;
    private Paint mPaint;

    public ChessBoard(Context context) {
        super(context);
        init();
    }

    public ChessBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChessBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(60);
        mWidhtGap = 0;
        mHeightGap  = 0;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);

        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.UNSPECIFIED || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            throw new RuntimeException("CellLayout cannot have UNSPECIFIED dimensions");
        }
        if (mCellWidth == 0) {
            mCellWidth = mWidth / Config.CountX;
//            mCellHeight = mHeight / Config.CountY;
            mCellHeight = mCellWidth;
        }
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child);
        }
        Log.e("111111111111111", "mWidth=" + mWidth + "mHeight=" + mHeight + "mCellWidth=" + mCellWidth);
        setMeasuredDimension(mWidth, mHeight);
    }

    public void measureChild(View child) {
        final int cellWidth = mCellWidth;
        final int cellHeight = mCellHeight;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();

        lp.setup(cellWidth, cellHeight, mWidhtGap, mHeightGap);
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.EXACTLY);
        int childheightMeasureSpec = MeasureSpec.makeMeasureSpec(lp.height,
                MeasureSpec.EXACTLY);
        child.measure(childWidthMeasureSpec, childheightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();

                int childLeft = lp.x;
                int childTop = lp.y;
                child.layout(childLeft, childTop, childLeft + lp.width, childTop + lp.height);

            }
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < Config.CountY; i++) {//draw hor line
            canvas.drawLine(mCellWidth / 2, mCellHeight / 2 + i * mCellHeight, mWidth - mCellWidth / 2, mCellHeight / 2 + i * mCellHeight, mPaint);
        }
        for (int i = 0; i < Config.CountX; i++) {//draw ver line
            if (i == 0 || i == Config.CountX - 1) {
                canvas.drawLine(mCellWidth / 2 + i * mCellWidth, mCellHeight / 2, mCellWidth / 2 + i * mCellWidth, mHeight - mCellHeight / 2, mPaint);
            } else {
                canvas.drawLine(mCellWidth / 2 + i * mCellWidth, mCellHeight / 2, mCellWidth / 2 + i * mCellWidth, mCellHeight / 2 + (Config.CountY / 2 - 1) * mCellHeight, mPaint);
                canvas.drawLine(mCellWidth / 2 + i * mCellWidth, mCellHeight / 2 + mCellHeight * Config.CountY / 2, mCellWidth / 2 + i * mCellWidth, mCellHeight / 2 + (Config.CountY / 2 - 1) * mCellHeight + mCellHeight * Config.CountY / 2, mPaint);
            }
        }
        //draw top X
        canvas.drawLine(mCellWidth / 2 + 3 * mCellWidth, mCellHeight / 2, mCellWidth / 2 + 5 * mCellWidth, mCellHeight / 2 + mCellHeight * 2, mPaint);
        canvas.drawLine(mCellWidth / 2 + 3 * mCellWidth, mCellHeight / 2 + mCellHeight * 2, mCellWidth / 2 + 5 * mCellWidth, mCellHeight / 2, mPaint);
        //draw bottom X
        canvas.drawLine(mCellWidth / 2 + 3 * mCellWidth, mCellHeight / 2 + mCellHeight * 7, mCellWidth / 2 + 5 * mCellWidth, mCellHeight / 2 + mCellHeight * 9, mPaint);
        canvas.drawLine(mCellWidth / 2 + 3 * mCellWidth, mCellHeight / 2 + mCellHeight * 9, mCellWidth / 2 + 5 * mCellWidth, mCellHeight / 2 + mCellHeight * 7, mPaint);

        canvas.drawText("楚河", 2 * mCellWidth, 5 * mCellHeight + 20, mPaint);
        canvas.drawText("汉界", 6 * mCellWidth, 5 * mCellHeight + 20, mPaint);
    }
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        /**
         * Horizontal location of the item in the grid.
         */
        @ViewDebug.ExportedProperty
        public int cellX;

        /**
         * Vertical location of the item in the grid.
         */
        @ViewDebug.ExportedProperty
        public int cellY;

        /**
         * Temporary horizontal location of the item in the grid during reorder
         */
        public int tmpCellX;

        /**
         * Temporary vertical location of the item in the grid during reorder
         */
        public int tmpCellY;

        /**
         * Indicates that the temporary coordinates should be used to layout the items
         */
        public boolean useTmpCoords;

        /**
         * Number of cells spanned horizontally by the item.
         */
        @ViewDebug.ExportedProperty
        public int cellHSpan;

        /**
         * Number of cells spanned vertically by the item.
         */
        @ViewDebug.ExportedProperty
        public int cellVSpan;

        /**
         * Indicates whether the item will set its x, y, width and height parameters freely,
         * or whether these will be computed based on cellX, cellY, cellHSpan and cellVSpan.
         */
        public boolean isLockedToGrid = true;

        /**
         * Indicates whether this item can be reordered. Always true except in the case of the
         * the AllApps button.
         */
        public boolean canReorder = true;

        // X coordinate of the view in the layout.
        @ViewDebug.ExportedProperty
        int x;
        // Y coordinate of the view in the layout.
        @ViewDebug.ExportedProperty
        int y;

        boolean dropped;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            cellHSpan = 1;
            cellVSpan = 1;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            cellHSpan = 1;
            cellVSpan = 1;
        }

        public LayoutParams(LayoutParams source) {
            super(source);
            this.cellX = source.cellX;
            this.cellY = source.cellY;
            this.cellHSpan = source.cellHSpan;
            this.cellVSpan = source.cellVSpan;
        }

        public LayoutParams(int cellX, int cellY, int cellHSpan, int cellVSpan) {
            super(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            this.cellX = cellX;
            this.cellY = cellY;
            this.cellHSpan = cellHSpan;
            this.cellVSpan = cellVSpan;
        }

        public void setup(int cellWidth, int cellHeight, int widthGap, int heightGap) {
            if (isLockedToGrid) {
                final int myCellHSpan = cellHSpan;
                final int myCellVSpan = cellVSpan;
                final int myCellX = useTmpCoords ? tmpCellX : cellX;
                final int myCellY = useTmpCoords ? tmpCellY : cellY;

                width = myCellHSpan * cellWidth + ((myCellHSpan - 1) * widthGap) -
                        leftMargin - rightMargin;
                height = myCellVSpan * cellHeight + ((myCellVSpan - 1) * heightGap) -
                        topMargin - bottomMargin;
                x = (int) (myCellX * (cellWidth + widthGap) + leftMargin);
                y = (int) (myCellY * (cellHeight + heightGap) + topMargin);
            }
        }

        public String toString() {
            return "(" + this.cellX + ", " + this.cellY + ")";
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getWidth() {
            return width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getHeight() {
            return height;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getY() {
            return y;
        }
    }

    // This class stores info for two purposes:
    // 1. When dragging items (mDragInfo in Workspace), we store the View, its cellX & cellY,
    //    its spanX, spanY, and the screen it is on
    // 2. When long clicking on an empty cell in a CellLayout, we save information about the
    //    cellX and cellY coordinates and which page was clicked. We then set this as a tag on
    //    the CellLayout that was long clicked
    static final class CellInfo {
        View cell;
        int cellX = -1;
        int cellY = -1;
        int spanX;
        int spanY;
        int screen;
        long container;

        @Override
        public String toString() {
            return "Cell[view=" + (cell == null ? "null" : cell.getClass())
                    + ", x=" + cellX + ", y=" + cellY + "]";
        }
    }
}
