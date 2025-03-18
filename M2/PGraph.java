// Prim's Algorithm to find the minimum spanning tree of the graph
// time complexity: O(E log V)
// space complexity: O(V)

import java.util.Arrays;
import java.util.Scanner;

public class PGraph{
    public void Prim(int G[][], int v){
        int INF = 999;
        int edges = 0;
        int mincost = 0;
        boolean[] selected = new boolean[v];
        Arrays.fill(selected, false);
        selected[0] = true;
        System.out.println("Edge : Weight");
        while(edges<v-1){
            int min = INF;
            int x = 0,y=0;
            for(int i=0;i<v;i++){
                if(selected[i]==true){
                    for(int j=0;j<v;j++){
                        if(!selected[j] && G[i][j] != 0){
                            if(min > G[i][j]){
                                min = G[i][j];
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
            }
            if(x!=y){
                System.out.println(x + " - " + y + " : " + G[x][y]);
                mincost += G[x][y];
                edges++;
                selected[y] = true;
            } else {
                break;
            }
        }
        if(edges!=v-1){
            System.out.println("Minimum Spanning Tree not possible");
        } else {
            System.out.println("Minimum cost: " + mincost);
        }
    }

    public static void main(String args[]){
        PGraph p = new PGraph();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        int G[][] = new int[v][v];
        System.out.println("Enter the adjacency matrix (0 for no edge): ");
        for(int i=0;i<v;i++){
            for(int j=0;j<v;j++){
                G[i][j] = sc.nextInt();
            }
        }
        p.Prim(G, v);
    }
}