package chess.shy.chess.chess.shy.bean;

/**
 * Created by shiyao on 16-11-23.
 */
public class PieceInfo {
    /**
     * The chess name.
     */
    public CharSequence title;

    public int cellX;

    public int cellY;

    public int team;

    public boolean isSurvival = true;

    Object rule;

    public PieceInfo(CharSequence title, int cellX, int cellY, int team, boolean isSurvival, Object rule) {
        this.title = title;
        this.cellX = cellX;
        this.cellY = cellY;
        this.team = team;
        this.isSurvival = isSurvival;
        this.rule = rule;
    }
}
