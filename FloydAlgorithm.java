// Floyd's Algorithm
// Find the shortest all pair distances
// time complexity: O(V^3)
// space complexity: O(V^2)

import java.util.Scanner;

public class FloydAlgorithm {
    public void floyd(int[][] graph, int n){
        int INF = 999;
        int[][] dist = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dist[i][j] = graph[i][j];
            }
        }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dist[i][k] != INF && dist[k][j] != INF && dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        printSolution(dist, n);
    }

    public void printSolution(int[][] dist, int n){
        System.out.println("Shortest distances between every pair of vertices:");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(dist[i][j] == INF){
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String args[]){
        FloydAlgorithm f = new FloydAlgorithm();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();
        int[][] graph = new int[n][n];
        System.out.println("Enter the adjacency matrix (0 for no edge): ");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                graph[i][j] = sc.nextInt();
            }
        }
        f.floyd(graph, n);
    }
}