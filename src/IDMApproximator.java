import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Kyle on 12/10/2016.
 */
public class IDMApproximator {
    public static void main(String[] args){
        final double[][] adjMatrix = {
                { 0.0, 0.4, 0.5, 0.0 },
                { 0.0, 0.0, 0.3, 0.2 },
                { 0.0, 0.0, 0.0, 0.6 },
                { 0.0, 0.0, 0.0, 0.0 }
        };

        int[] seedSet = { 0 };

        int[] resultArray = new int[20000];
        double result = 0;

        for (int i = 0; i < 20000; i++){
            resultArray[i] = doIteration(adjMatrix, seedSet);
        }

        for (int iterResult : resultArray){
            result += iterResult;
        }

        System.out.println(result / 20000);

    }
    public static int doIteration(double[][] adjMatrix, int[] seedSet){
        ArrayList<Integer> newActNodes = new ArrayList<Integer>();
        ArrayList<Integer> nextActNodes = new ArrayList<Integer>();
        ArrayList<Integer> activatedNodes = new ArrayList<Integer>();

        for (int seed : seedSet){
            newActNodes.add(seed);
            activatedNodes.add(seed);
        }

        double randomNum;
        while (!newActNodes.isEmpty()) {
            for (Integer node : newActNodes) {
                for (int col = 0; col < adjMatrix.length; col++) {
                    if (adjMatrix[node][col] != 0.0) {
                        randomNum = Math.random() * 1 + 0;
                        if (randomNum >= adjMatrix[node][col] && notAnActivatedNode(activatedNodes, col)) {
                            nextActNodes.add(col);
                            activatedNodes.add(col);
                        }
                    }
                }
            }

            newActNodes.clear();
            for (Integer node : nextActNodes){
                newActNodes.add(node);
            }
            nextActNodes.clear();
        }
        return activatedNodes.size();
    }

    public static boolean notAnActivatedNode(ArrayList<Integer> activatedNodes, int thisNode){
        for (Integer node : activatedNodes){
            if ((int) node == thisNode){
                return false;
            }
        }
        return true;
    }
}
