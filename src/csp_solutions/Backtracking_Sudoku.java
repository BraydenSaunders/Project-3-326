package csp_solutions;
import core_algorithms.BacktrackingSearch;
import csp_problems.*;
import csp_problems.CSPProblem.Variable;

import java.util.Iterator;

public class Backtracking_Sudoku extends BacktrackingSearch<Square,Integer>{
    public Backtracking_Sudoku(Sudoku problem){
        super(problem);
    }
    /**
     * @param tail tail of the arc
     * @param head head of the arc
     * @return true if the tail has been revised (lost some values), false
    otherwise
     */
    public boolean revise(Square tail, Square head) {
    //TODO:
        boolean revise = false;
        Iterator<Integer> itr = getAllVariables().get(tail).domain().iterator();
        while(itr.hasNext()){
            //for each value in tail's domain, there must be a value in head's domain that's different
            //if not, delete the value from the tail's domain
            boolean hasSupport = false;
            for(int value : getAllVariables().get(tail).domain()){
                if(getAllVariables().get(head).domain().contains(value)){
                    hasSupport = true;
                    break;
                }
            }
            if (!hasSupport) {
                //there is no value at the head that supports this tail value
                //delete the value from the tail
                itr.remove();
                revise = true;
            }
        }
        return revise;
    }
    /**
     * Implementing the Minimum Remaining Values(MRV) ordering heuristic.
     * @return the variable with the smallest domain among all the unassigned
    variables;
     * null if all variables have been assigned
     */
    public Square selectUnassigned(){
    //TODO:
        int smallestDomain = 9;
        Variable<Square, Integer> mrv = null;
        for (Variable<Square, Integer> v : getAllVariables().values()){
            if(!assigned(v.name()) && v.domain().size() < smallestDomain){
                smallestDomain = v.domain().size();
                mrv = v;
            }
        }
        if(mrv != null){
            return mrv.name();
        }else{
            return null;
        }
    }
    /**
     * @param args (no command-line argument is needed to run this program)
     */
    public static void main(String[] args) {
        String filename = "/Users/braydensaunders/Downloads/Project-3-326/SudokuTestCases/TestCase1.txt";
        Sudoku problem = new Sudoku(filename);
        Backtracking_Sudoku agent = new Backtracking_Sudoku(problem);
        System.out.println("loading puzzle from " + filename + "...");
        problem.printPuzzle(problem.getAllVariables());
        if(agent.initAC3() && agent.search()){
            System.out.println("Solution found:");
            problem.printPuzzle(agent.getAllVariables());
        }else{
            System.out.println("Unable to find a solution.");
        }
    }
}

