package ai;

import model.AbstractState;
import model.State;

import java.util.List;

public class JedBurrows2048 extends AbstractPlayer {
    private int alpha;
    private int beta;
    private int bestScore;
    private AbstractState.MOVE bestMove;
    @Override
    public AbstractState.MOVE getMove(State game) {
        bestScore = 0;
        bestMove = null;
        pause();
        maxValue(game);
        return bestMove;
    }

    private int maxValue(State game) {
        int v;
        if(game.getNumberOfEmptyCells() == 0){
            return bestScore;
        }
        System.out.println("Empty cells: " + game.getNumberOfEmptyCells());
        bestScore = Integer.MIN_VALUE;
        for(AbstractState.MOVE move : game.getMoves()){
            v = minValue(game);
            if(v > bestScore){
                bestScore = v;
                bestMove = move;
            }
        }
        return bestScore;
    }

    private int minValue(State game){
        int v;
        if(game.getNumberOfEmptyCells() == 0){
            return bestScore;
        }
        bestScore = Integer.MAX_VALUE;
        for(State s : game.nextFirstHalfMoveStates()){
            v = maxValue(s);
            if(v < bestScore){
                bestScore = v;
            }
            if(v <= alpha){
                return bestScore;
            }
            if(v < beta){
                beta = v;
            }
        }
        return bestScore;
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