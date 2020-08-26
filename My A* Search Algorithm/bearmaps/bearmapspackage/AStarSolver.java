

import java.util.*;


public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {


    double explorationTime;
    SolverOutcome result;

    HashMap<Vertex, Double> dic; // Dictionary of distance from the origin;
    HashMap<Vertex, Vertex> pathDic;
    HashSet<Vertex> visited;
    ArrayHeapMinPQ<Vertex> PQ;
    double solutionWeight;
    List<Vertex> solution;

    private double distFromOrigin(Vertex u) {
        if (dic.containsKey(u)) {
            return dic.get(u);
        } else {
            return Double.MAX_VALUE;
        }
    }




    private void helpGetSolution(Vertex start, Vertex w) {
        if (w != start) {
            Vertex v = pathDic.get(w);
            solution.add(v);
            helpGetSolution(start, v);
        }
    }






    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        double beginTime = System.currentTimeMillis();
        PQ = new ArrayHeapMinPQ<>();
        dic = new HashMap<>();
        pathDic = new HashMap<>();
        visited = new HashSet<>();
        solution = new ArrayList<>();
        PQ.add(start, input.estimatedDistanceToGoal(start, end));
        dic.put(start, 0.0);


        while ((PQ.size() != 0) && (System.currentTimeMillis() - beginTime < timeout * 1000) && (PQ.getSmallest() != end)) {
            Vertex v = PQ.removeSmallest();
            visited.add(v);




            for (WeightedEdge<Vertex> e : input.neighbors(v)) {

                Vertex w = e.to();
                if (! visited.contains(w)) {
                    double x = e.weight();
                    double newDist = distFromOrigin(v) + x;
                    if (newDist < distFromOrigin(w)) {
                        dic.put(w, newDist);
                        pathDic.put(w, v);

                    }

                    if (PQ.contains(w)) {
                        PQ.changePriority(w, distFromOrigin(w) + input.estimatedDistanceToGoal(w,end));
                    } else {
                        PQ.add(w, distFromOrigin(w) + input.estimatedDistanceToGoal(w,end));
                    }


                }







            }
        }

        if (PQ.size() == 0) {
            result = SolverOutcome.UNSOLVABLE;
            explorationTime = (System.currentTimeMillis() - beginTime) / 1000;
        } else if (System.currentTimeMillis() - beginTime > 1000 * timeout) {
            result = SolverOutcome.TIMEOUT;
            explorationTime = (System.currentTimeMillis() - beginTime) / 1000;

        } else {
            result = SolverOutcome.SOLVED;
            explorationTime = (System.currentTimeMillis() - beginTime) / 1000;
            solutionWeight = distFromOrigin(end);
            solution.add(end);
            helpGetSolution(start, end);
            Collections.reverse(solution);
        }





    }





    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return visited.size();
    }

    @Override
    public double explorationTime() {
        return explorationTime;
    }

    @Override
    public SolverOutcome outcome() {
        return result;
    }
}

