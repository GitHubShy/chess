package chess.shy.chess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by shiyao on 16-11-22.
 */
public class ChessBoard extends ViewGroup {
    private int mWidth;
    private int mHeight;
    private int mCellWidth;
    private int mCellHeight;
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
            mCellHeight = mHeight / Config.CountY;
        }
        Log.e("111111111111111", "mWidth=" + mWidth + "mHeight=" + mHeight + "mCellWidth=" + mCellWidth);
        setMeasuredDimension(mWidth, mHeight);
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

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
