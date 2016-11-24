package chess.shy.chess;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    }

    private void initView() {
        mDragController = new DragController(this);
        mCb = (ChessBoard) findViewById(R.id.chess);
        mDragLayer = (DragLayer) findViewById(R.id.drag_layer);
        mDragLayer.setup(this,mDragController);
    }

    private void add(PieceInfo pi) {
        BubbleTextView bt = new BubbleTextView(getApplicationContext(),pi);
        ChessBoard.LayoutParams lp = new ChessBoard.LayoutParams(pi.cellX, pi.cellY, 1, 1);
        bt.setOnLongClickListener(this);
        mCb.addView(bt, lp);
    }

    private void initPiece() {
        PieceInfo redChariotLeft = new PieceInfo("車",0,0,0,true,null);
        add(redChariotLeft);
        PieceInfo redChariotRight = new PieceInfo("車",8,0,0,true,null);
        add(redChariotRight);
        PieceInfo blackChariotLeft = new PieceInfo("車",0,9,1,true,null);
        add(blackChariotLeft);
        PieceInfo blackChariotRight = new PieceInfo("車",8,9,1,true,null);
        add(blackChariotRight);
        PieceInfo redHorseLeft = new PieceInfo("馬",1,0,0,true,null);
        add(redHorseLeft);
        PieceInfo redHorseRight = new PieceInfo("馬",7,0,0,true,null);
        add(redHorseRight);
        PieceInfo blackHorseLeft = new PieceInfo("馬",1,9,1,true,null);
        add(blackHorseLeft);
        PieceInfo blackHorseRight = new PieceInfo("馬",7,9,1,true,null);
        add(blackHorseRight);
        PieceInfo redElephantLeft = new PieceInfo("相",2,0,0,true,null);
        add(redElephantLeft);
        PieceInfo redElephantRight = new PieceInfo("相",6,0,0,true,null);
        add(redElephantRight);
        PieceInfo blackElephantLeft = new PieceInfo("象",2,9,1,true,null);
        add(blackElephantLeft);
        PieceInfo blackElephantRight = new PieceInfo("象",6,9,1,true,null);
        add(blackElephantRight);
        PieceInfo redPaladinLeft = new PieceInfo("仕",3,0,0,true,null);
        add(redPaladinLeft);
        PieceInfo redPaladinRight = new PieceInfo("仕",5,0,0,true,null);
        add(redPaladinRight);
        PieceInfo blackPaladinLeft = new PieceInfo("仕",3,9,1,true,null);
        add(blackPaladinLeft);
        PieceInfo blackPaladinRight = new PieceInfo("仕",5,9,1,true,null);
        add(blackPaladinRight);
        PieceInfo redGunLeft = new PieceInfo("炮",1,2,0,true,null);
        add(redGunLeft);
        PieceInfo redGunRight = new PieceInfo("炮",7,2,0,true,null);
        add(redGunRight);
        PieceInfo blackGunLeft = new PieceInfo("炮",1,7,1,true,null);
        add(blackGunLeft);
        PieceInfo blackGunRight = new PieceInfo("炮",7,7,1,true,null);
        add(blackGunRight);
        PieceInfo redSoldier1 = new PieceInfo("兵",0,3,0,true,null);
        add(redSoldier1);
        PieceInfo redSoldier2 = new PieceInfo("兵",2,3,0,true,null);
        add(redSoldier2);
        PieceInfo redSoldier3 = new PieceInfo("兵",4,3,0,true,null);
        add(redSoldier3);
        PieceInfo redSoldier4 = new PieceInfo("兵",6,3,0,true,null);
        add(redSoldier4);
        PieceInfo redSoldier5 = new PieceInfo("兵",8,3,0,true,null);
        add(redSoldier5);
        PieceInfo blackSoldier1 = new PieceInfo("卒",0,6,1,true,null);
        add(blackSoldier1);
        PieceInfo blackSoldier2 = new PieceInfo("卒",2,6,1,true,null);
        add(blackSoldier2);
        PieceInfo blackSoldier3 = new PieceInfo("卒",4,6,1,true,null);
        add(blackSoldier3);
        PieceInfo blackSoldier4 = new PieceInfo("卒",6,6,1,true,null);
        add(blackSoldier4);
        PieceInfo blackSoldier5 = new PieceInfo("卒",8,6,1,true,null);
        add(blackSoldier5);
        PieceInfo blackGeneral = new PieceInfo("将",4,9,1,true,null);
        add(blackGeneral);
        PieceInfo redGeneral = new PieceInfo("帅",4,0,0,true,null);
        add(redGeneral);
    }
    public DragController getDragController() {
        return mDragController;
    }
    public DragLayer getDragLayer() {
        return mDragLayer;
    }

    @Override
    public boolean onLongClick(View v) {
        Log.e("11111111111111","v="+v.getTag());
        return false;
    }
}
