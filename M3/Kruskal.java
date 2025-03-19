// Kruskal Algorithm
// Time Complexity: O(E log E)
// Space Complexity: O(V + E)

import java.util.*;
public class Kruskal{
    int find(int v, int[] parent){
        while(parent[v] != v){
            v = parent[v];
        }
        return v;
    }

    void union(int i,int j,int[] parent){
        if(i<j){
            parent[j] = i;
        } else {
            parent[i] = j;
        }
    }
    void Kruskal(int n,int[][] cost){
        int[] parent = new int[n];
        int[][] t = new int[10][20];
        int k=0,count=0,mincost=0;
        int i,j,min,u=0,v=0;
        for(i=1;i<=n;i++)
            parent[i]=i;
        while(count<n){
            min = 999;
            for(i=1;i<=n;i++){
                for(j=1;j<=n;j++){
                    if(cost[i][j]<min && cost[i][j]!=0){
                        min = cost[i][j];
                        u = i;
                        v = j;
                    }
                }
            }
            if(min==999)
                break;
            i = find(u,parent);
            j = find(v,parent);
            if(i!=j){
                t[k][0] = u;
                t[k][1] = v;
                k++;
                count++;
                mincost += min;
                union(i,j,parent);
            }
            cost[u][v] = cost[v][u] = 999;
        }
        if(count==n-1){
            System.out.println("Minimum cost is: " + mincost);
            System.out.println("Edges in the minimum spanning tree are:");
            for(i=0;i<=n-2;i++){
                System.out.println(t[i][0] + " " + t[i][1]);
            }
        } else {
            System.out.println("No spanning tree exists");
        }
    }

    public static void main(String[] args){
        int[][] cost = new int[10][20];
        int n,i,j;
        Kruskal k = new Kruskal();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices:");
        n = sc.nextInt();
        System.out.println("Enter cost matrix:");
        for(i=1;i<=n;i++){
            for(j=1;j<=n;j++){
                cost[i][j] = sc.nextInt();
            }
        }
        k.Kruskal(n,cost);
    }
}