package ai;

import model.AbstractState;
import model.BinaryState;
import model.State;

import java.util.List;

public class JedBurrows2048 extends AbstractPlayer {
    private int alpha;
    private int beta;
    private AbstractState.MOVE bestMove;
    @Override
    public AbstractState.MOVE getMove(State game) {
        bestMove = null;
        pause();
        minimax(game,false);
        return bestMove;
    }
    private int minimax(State game,boolean isAI){
        int bestScore;
        List<AbstractState.MOVE> moves = game.getMoves();
        if(moves.isEmpty()){
            return game.getScore();
        }
        else{
            if(!isAI){
                bestScore = Integer.MIN_VALUE;
                State[] states = game.nextFirstHalfMoveStates();
                for(int i=0;i<states.length;i++){
                    int currentResult = minimax(states[i],true);
                    if(currentResult > bestScore){
                        bestScore = currentResult;
                        bestMove = moveFromState(i);
                    }
                }
            }
            else{
                bestScore = Integer.MAX_VALUE;
                if(game.getNumberOfEmptyCells() == 0){
                    bestScore = 0;
                }
                for(State state : game.nextSecondHalfMoveStates()){
                    int currentResult = minimax(state,false);
                    if(currentResult < bestScore){
                        bestScore = currentResult;
                    }
                }
            }
        }
        return bestScore;
    }

    private AbstractState.MOVE moveFromState(int i){
        switch (i){
            case 0:
                return AbstractState.MOVE.UP;
            case 1:
                return AbstractState.MOVE.RIGHT;
            case 2:
                return AbstractState.MOVE.LEFT;
            case 3:
                return AbstractState.MOVE.DOWN;
        }
        return AbstractState.MOVE.LEFT;
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