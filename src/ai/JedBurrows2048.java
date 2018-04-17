package ai;

import eval.BonusEvaluator;
import eval.Evaluator;
import model.AbstractState;
import model.State;
import java.util.List;

public class JedBurrows2048 extends AbstractPlayer {
    private int depth;
    private int iterations;
    private double bestScore;
    private AbstractState.MOVE bestMove;

    @Override
    public AbstractState.MOVE getMove(State game) {
        pause();
        iterations = 1000;
        bestScore = Double.NEGATIVE_INFINITY;
        List<AbstractState.MOVE> moves = game.getMoves();
        for (AbstractState.MOVE move : moves){
            double score = runSimulation(move, game);
            if(score > bestScore){
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    @Override
    public int studentID() {
        return 201502941;
    }

    @Override
    public String studentName() {
        return "Jed Burrows";
    }

    private double runSimulation(AbstractState.MOVE move, State node) {
        int score = 0;
        int currentDepth;
        for(int i = 0; i < iterations; i++){
            State copy = node.copy();
            copy.move(move);
            for(currentDepth = 0; currentDepth < depth && !copy.getMoves().isEmpty(); currentDepth++){
                double best = Double.NEGATIVE_INFINITY;
                List<AbstractState.MOVE> moves = copy.getMoves();
                AbstractState.MOVE bestNextMove = moves.get((int) Math.random() * moves.size());
                copy.move(bestNextMove);
            }
            score += copy.getScore();
            copy.undo();
        }

        return (score/iterations);
    }
}
