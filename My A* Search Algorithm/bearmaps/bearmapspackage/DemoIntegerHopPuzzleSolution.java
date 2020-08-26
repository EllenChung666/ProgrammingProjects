

/**
 * Showcases how the AStarSolver can be used for solving integer hop puzzles.
 * NOTE: YOU MUST REPLACE LazySolver WITH AStarSolver OR THIS DEMO WON'T WORK!
 * Created by hug.
 */
public class DemoIntegerHopPuzzleSolution {
    public static void main(String[] args) {
        int start = 1;
        int goal = 20;

        IntegerHopGraph ahg = new IntegerHopGraph();

        ShortestPathsSolver<Integer> solver = new AStarSolver<>(ahg, start, goal, 10000);
        SolutionPrinter.summarizeSolution(solver, " => ");





    }
}
