package ai;

import model.AbstractState;
import model.State;
import java.util.List;
import java.util.Random;

public class JedBurrows2048 extends AbstractPlayer {
    private int depth;
    private int iterations;
    private double bestScore;
    private AbstractState.MOVE bestMove;

    @Override
    public AbstractState.MOVE getMove(State game) {
        pause();
        iterations = 300;
        depth = 200;
        bestScore = Double.MIN_VALUE;
        List<AbstractState.MOVE> moves = game.getMoves();
        for (AbstractState.MOVE move : moves){
            double score = findBestMove(move, game);
            if(bestScore < score){
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private double findBestMove(AbstractState.MOVE move, State start) {
        int score = 0;
        for(int i = 0; i < iterations; i++){
            State copy = start.copy();
            copy.move(move);
            for(int ii=0; ii < depth && !copy.getMoves().isEmpty(); ii++){
                List<AbstractState.MOVE> moves = copy.getMoves();
                AbstractState.MOVE bestNextMove = moves.get(new Random().nextInt(moves.size()));
                copy.move(bestNextMove);
            }
            score += copy.getScore();
        }
        return (calcAvgScore(score));
    }

    private double calcAvgScore(double score){
        return score/iterations;
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