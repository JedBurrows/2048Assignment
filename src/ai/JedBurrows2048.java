package ai;

import model.AbstractState;
import model.State;

import java.util.List;

public class JedBurrows2048 extends AbstractPlayer {
    private int alpha;
    private int beta;
    private int value;
    @Override
    public AbstractState.MOVE getMove(State game) {
        pause();

        List<AbstractState.MOVE> moves = game.getMoves();
        maxValue(game);
        AbstractState.MOVE bestMove = null;
        return bestMove;
    }

    private int maxValue(State game) {
        int v;
        if(game.getNumberOfEmptyCells() == 0){
            return 0;
        }
        value = Integer.MIN_VALUE;
        for(State s : game.nextFirstHalfMoveStates()){
            v = minValue(s);
            if(v > value){
                value = v;
            }
            if(v >= beta){
                return value;
            }
            if(v > alpha){
                alpha = v;
            }
        }
        return value;
    }

    private int minValue(State game){
        int v;
        if(game.getNumberOfEmptyCells() == 0){
            return 0;
        }
        value = Integer.MAX_VALUE;
        for(State s : game.nextFirstHalfMoveStates()){
            v = maxValue(s);
            if(v < value){
                value = v;
            }
            if(v <= alpha){
                return value;
            }
            if(v < beta){
                beta = v;
            }
        }
        return value;
    }

    @Override
    public int studentID() {
        return 201502941;
    }

    @Override
    public String studentName() {
        return "Jed Burrows";
    }
}