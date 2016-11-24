package chess.shy.chess;

import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import chess.shy.bean.PieceInfo;


public class Chess extends AppCompatActivity implements View.OnLongClickListener {
    private ChessBoard mCb;
    private DragController mDragController;
    private DragLayer mDragLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPiece();
        mCb.setOnDragListener(new myDragEventListener());

    }

    private void initView() {
        mDragController = new DragController(this);
        mCb = (ChessBoard) findViewById(R.id.chess);
        mDragLayer = (DragLayer) findViewById(R.id.drag_layer);
        mDragLayer.setup(this, mDragController);
    }

    private void add(PieceInfo pi) {
        BubbleTextView bt = new BubbleTextView(getApplicationContext(), pi);
        ChessBoard.LayoutParams lp = new ChessBoard.LayoutParams(pi.cellX, pi.cellY, 1, 1);
        bt.setOnLongClickListener(this);
        mCb.addView(bt, lp);
    }

    private void initPiece() {
        PieceInfo redChariotLeft = new PieceInfo("車", 0, 0, 0, true, null);
        add(redChariotLeft);
        PieceInfo redChariotRight = new PieceInfo("車", 8, 0, 0, true, null);
        add(redChariotRight);
        PieceInfo blackChariotLeft = new PieceInfo("車", 0, 9, 1, true, null);
        add(blackChariotLeft);
        PieceInfo blackChariotRight = new PieceInfo("車", 8, 9, 1, true, null);
        add(blackChariotRight);
        PieceInfo redHorseLeft = new PieceInfo("馬", 1, 0, 0, true, null);
        add(redHorseLeft);
        PieceInfo redHorseRight = new PieceInfo("馬", 7, 0, 0, true, null);
        add(redHorseRight);
        PieceInfo blackHorseLeft = new PieceInfo("馬", 1, 9, 1, true, null);
        add(blackHorseLeft);
        PieceInfo blackHorseRight = new PieceInfo("馬", 7, 9, 1, true, null);
        add(blackHorseRight);
        PieceInfo redElephantLeft = new PieceInfo("相", 2, 0, 0, true, null);
        add(redElephantLeft);
        PieceInfo redElephantRight = new PieceInfo("相", 6, 0, 0, true, null);
        add(redElephantRight);
        PieceInfo blackElephantLeft = new PieceInfo("象", 2, 9, 1, true, null);
        add(blackElephantLeft);
        PieceInfo blackElephantRight = new PieceInfo("象", 6, 9, 1, true, null);
        add(blackElephantRight);
        PieceInfo redPaladinLeft = new PieceInfo("仕", 3, 0, 0, true, null);
        add(redPaladinLeft);
        PieceInfo redPaladinRight = new PieceInfo("仕", 5, 0, 0, true, null);
        add(redPaladinRight);
        PieceInfo blackPaladinLeft = new PieceInfo("仕", 3, 9, 1, true, null);
        add(blackPaladinLeft);
        PieceInfo blackPaladinRight = new PieceInfo("仕", 5, 9, 1, true, null);
        add(blackPaladinRight);
        PieceInfo redGunLeft = new PieceInfo("炮", 1, 2, 0, true, null);
        add(redGunLeft);
        PieceInfo redGunRight = new PieceInfo("炮", 7, 2, 0, true, null);
        add(redGunRight);
        PieceInfo blackGunLeft = new PieceInfo("炮", 1, 7, 1, true, null);
        add(blackGunLeft);
        PieceInfo blackGunRight = new PieceInfo("炮", 7, 7, 1, true, null);
        add(blackGunRight);
        PieceInfo redSoldier1 = new PieceInfo("兵", 0, 3, 0, true, null);
        add(redSoldier1);
        PieceInfo redSoldier2 = new PieceInfo("兵", 2, 3, 0, true, null);
        add(redSoldier2);
        PieceInfo redSoldier3 = new PieceInfo("兵", 4, 3, 0, true, null);
        add(redSoldier3);
        PieceInfo redSoldier4 = new PieceInfo("兵", 6, 3, 0, true, null);
        add(redSoldier4);
        PieceInfo redSoldier5 = new PieceInfo("兵", 8, 3, 0, true, null);
        add(redSoldier5);
        PieceInfo blackSoldier1 = new PieceInfo("卒", 0, 6, 1, true, null);
        add(blackSoldier1);
        PieceInfo blackSoldier2 = new PieceInfo("卒", 2, 6, 1, true, null);
        add(blackSoldier2);
        PieceInfo blackSoldier3 = new PieceInfo("卒", 4, 6, 1, true, null);
        add(blackSoldier3);
        PieceInfo blackSoldier4 = new PieceInfo("卒", 6, 6, 1, true, null);
        add(blackSoldier4);
        PieceInfo blackSoldier5 = new PieceInfo("卒", 8, 6, 1, true, null);
        add(blackSoldier5);
        PieceInfo blackGeneral = new PieceInfo("将", 4, 9, 1, true, null);
        add(blackGeneral);
        PieceInfo redGeneral = new PieceInfo("帅", 4, 0, 0, true, null);
        add(redGeneral);
    }

    public DragController getDragController() {
        return mDragController;
    }

    public DragLayer getDragLayer() {
        return mDragLayer;
    }

    public ChessBoard getmCb() {
        return mCb;
    }

    @Override
    public boolean onLongClick(View v) {
        Log.e("11111111111111", "v=" + v.getTag());
        // Create a new ClipData.
        // This is done in two steps to provide clarity. The convenience method
        // ClipData.newPlainText() can create a plain text ClipData in one step.

        // Create a new ClipData.Item from the ImageView object's tag
        ClipData.Item item = new ClipData.Item("aa");

        // Create a new ClipData using the tag as a label, the plain text MIME type, and
        // the already-created item. This will create a new ClipDescription object within the
        // ClipData, and set its MIME type entry to "text/plain"
        String[] a = new String[11];
        ClipData dragData = new ClipData("a", a, item);

        // Instantiates the drag shadow builder.
        View.DragShadowBuilder myShadow = new MyDragShadowBuilder(v);

        // Starts the drag

        v.startDrag(dragData,  // the data to be dragged
                myShadow,  // the drag shadow builder
                null,      // no need to use local data
                0          // flags (not currently used, set to 0)
        );

        return false;
    }

    private static class MyDragShadowBuilder extends View.DragShadowBuilder {

        // The drag shadow image, defined as a drawable thing
        private static Drawable shadow;

        // Defines the constructor for myDragShadowBuilder
        public MyDragShadowBuilder(View v) {

            // Stores the View parameter passed to myDragShadowBuilder.
            super(v);

            // Creates a draggable image that will fill the Canvas provided by the system.
            shadow = new ColorDrawable(Color.BLACK);
        }

        // Defines a callback that sends the drag shadow dimensions and touch point back to the
        // system.
        @Override
        public void onProvideShadowMetrics(Point size, Point touch) {
            // Defines local variables
            int width, height;
            Log.e("222222222222", "size=" + size + "touch=" + touch);

            // Sets the width of the shadow to half the width of the original View
            width = 200;

            // Sets the height of the shadow to half the height of the original View
            height = 200;

            // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
            // Canvas that the system will provide. As a result, the drag shadow will fill the
            // Canvas.
            shadow.setBounds(0, 0, width, height);

            // Sets the size parameter's width and height values. These get back to the system
            // through the size parameter.
            size.set(width, height);

            // Sets the touch point's position to be in the middle of the drag shadow
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
        // from the dimensions passed in onProvideShadowMetrics().
        @Override
        public void onDrawShadow(Canvas canvas) {

            // Draws the ColorDrawable in the Canvas passed in from the system.
            shadow.draw(canvas);
        }
    }

    protected class myDragEventListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            // Defines a variable to store the action type for the incoming event
            final int action = event.getAction();
            Log.e("2222222222222222 ","="+event.getX());
            return true;
        }

    }
}
