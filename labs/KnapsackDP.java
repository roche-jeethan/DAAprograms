// Knapsack algorithm
// Time complexity: O(n * W)
// Space complexity: O(n * W)

import java.util.*;
public class KnapsackDP{
    public void calculate(int[] wt, int[] val, int w, int n){
        int[][] F = new int[n+1][w+1];
        int i,j;
        for(i=0;i<=n;i++){
            for(j=0;j<=n;j++){
                if(i==0 || j==0){
                    F[i][j] = 0;
                } else {
                    if(j<wt[i]){
                        F[i][j] = F[i-1][j];
                    } else {
                        F[i][j] = Math.max(F[i-1][j], val[i] + F[i-1][j-wt[i]]);
                    }
                }
            }
        }
        System.out.println("Optimal Solution: "+F[i-1][w]);
        if(F[n][w]==0){
            System.out.println("No optimal subset");
        } else {
            System.out.println("Optimal subset: ");
        }
        for(i=n;i>0 && F[i][w]>0;i--){
            if(F[i][w] != F[i-1][w]){
                System.out.println("Item " + i + " ");
                w = w - wt[i];
            }
        }
    }

    public static void main(String[] args){
        int i;
        Scanner sc = new Scanner(System.in);
        KnapsackDP ks = new KnapsackDP();
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        int[] wt = new int[n+1];
        int[] val = new int[n+1];
        System.out.print("Enter the weights of the items: ");
        for(i=1;i<=n;i++){
            wt[i] = sc.nextInt();
        }
        System.out.print("Enter Profit values for "+n+"items: ");
        for(i=1;i<=n;i++){
            val[i] = sc.nextInt();
        }
        System.out.print("Enter knapsack capacity: ");
        int w = sc.nextInt();
        ks.calculate(wt, val, w, n);
    }
}