package ai;

import model.AbstractState;
import model.State;
import java.util.List;
import java.util.Random;

public class JedBurrows2048 extends AbstractPlayer {
    private double bestScore;
    private AbstractState.MOVE bestMove;

    @Override
    public AbstractState.MOVE getMove(State game) {
        pause();
        int iterations = 300;
        int depth = 200;
        bestScore = Double.MIN_VALUE;
        List<AbstractState.MOVE> moves = game.getMoves();
        for (AbstractState.MOVE move : moves){
            double score = findBestMove(move, game, iterations, depth);
            if(bestScore < score){
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private double findBestMove(AbstractState.MOVE move, State start,int iterations, int depth) {
        int score = 0;
        for(int i = 0; i < iterations; i++){
            State copy = start.copy();
            copy.move(move);
            for(int ii=0; ii < depth && !isTerminal(copy); ii++){
                List<AbstractState.MOVE> moves = copy.getMoves();
                AbstractState.MOVE bestNextMove = moves.get(new Random().nextInt(moves.size()));
                copy.move(bestNextMove);
            }
            score += copy.getScore();
        }
        return (calcAvgScore(score, iterations));
    }

    private double calcAvgScore(double score, int iterations){
        return score/iterations;
    }

    private boolean isTerminal(State state){
        return state.getMoves().isEmpty();
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