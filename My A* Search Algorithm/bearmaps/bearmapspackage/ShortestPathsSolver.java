


import java.util.List;

/**
 * Interface for shortest path solvers.
 * Created by hug.
 */
public interface ShortestPathsSolver<Vertex> {
    List<Vertex> solution();
    double solutionWeight();
    int numStatesExplored();
    double explorationTime();
    SolverOutcome outcome();
}
