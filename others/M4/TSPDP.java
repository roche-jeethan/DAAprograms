import java.util.Scanner;

public class TSPDP {
    static int n; 
    static int[][] dist; 
    static int[][] dp;
    static int VISITED_ALL;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of vertices: ");
        n = sc.nextInt();
        VISITED_ALL = (1 << n) - 1;
        
        dist = new int[n][n];
        System.out.println("Enter the adjacency matrix:");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dist[i][j] = sc.nextInt();
            }
        }
        
        dp = new int[n][(1 << n)];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < (1 << n); j++) {
                dp[i][j] = -1;
            }
        }
        
        int minCost = tsp(0, 1);
        System.out.println("Minimum cost for TSP: " + minCost);
        
        sc.close();
    }
    
    static int tsp(int pos, int mask) {
        if(mask == VISITED_ALL) {
            return dist[pos][0];
        }
        
        if(dp[pos][mask] != -1) {
            return dp[pos][mask];
        }
        
        int ans = Integer.MAX_VALUE;
        
        for(int city = 0; city < n; city++) {
            if((mask & (1 << city)) == 0) { 
                int newAns = dist[pos][city] + tsp(city, mask | (1 << city));
                ans = Math.min(ans, newAns);
            }
        }
        
        return dp[pos][mask] = ans;
    }
}