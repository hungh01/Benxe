package com.example.BenXe.Controller.Xe;
import java.util.Arrays;
import java.util.Arrays;

public class TSPSolver {

    static int n = 4;
    static int[][] dist = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
    };

    static int[][] dp;
    static int[][] path;

    public static int tsp(int mask, int pos) {
        if (mask == (1 << n) - 1) {
            // All nodes have been visited, return to the starting node
            return dist[pos][0];
        }

        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int result = Integer.MAX_VALUE;

        for (int next = 0; next < n; next++) {
            if ((mask & (1 << next)) == 0) {
                int newResult = dist[pos][next] + tsp(mask | (1 << next), next);
                if (newResult < result) {
                    result = newResult;
                    path[mask][pos] = next;
                }
            }
        }

        return dp[mask][pos] = result;
    }

    public static void printPath(int mask, int pos) {
        if (mask == (1 << n) - 1) {
            System.out.println(" -> 0");
            return;
        }

        int nextPos = path[mask][pos];
        System.out.print(" -> " + nextPos);
        printPath(mask | (1 << nextPos), nextPos);
    }

    public static void main(String[] args) {
        dp = new int[1 << n][n];
        path = new int[1 << n][n];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int result = tsp(1, 0); // Start from node 0
        System.out.println("The cost of the most efficient tour = " + result);

        System.out.print("The optimal path: 0");
        printPath(1, 0);
    }
}
