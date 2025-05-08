import java.util.*;

public class KnapsackMemory {
    private static int[][] memo;
    private static int[] weights;
    private static int[] values;
    private static int n, W;
    
    public static void printTable(int itemIndex, int weight, int value) {
        System.out.println("\nFilling cell [" + itemIndex + "][" + weight + "] = " + value);
        
        // Print weight headers
        System.out.print("i\\W");
        for(int w = 0; w <= W; w++) {
            System.out.printf("%4d", w);
        }
        System.out.println();
        System.out.println("   " + "----".repeat(W+1));
        
        // Print item rows
        for(int i = 0; i <= n; i++) {
            System.out.printf("%3d|", i);
            for(int w = 0; w <= W; w++) {
                if(memo[i][w] == -1) {
                    System.out.print("   -");
                } else {
                    System.out.printf("%4d", memo[i][w]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static int knapsack(int i, int w) {
        // Base case
        if(i == 0 || w == 0) {
            memo[i][w] = 0;
            printTable(i, w, 0);
            return 0;
        }
        
        // If already calculated
        if(memo[i][w] != -1) {
            return memo[i][w];
        }
        
        // If current item is too heavy
        if(weights[i] > w) {
            memo[i][w] = knapsack(i-1, w);
            printTable(i, w, memo[i][w]);
            return memo[i][w];
        }
        
        // Choose maximum of including and excluding current item
        int include = values[i] + knapsack(i-1, w - weights[i]);
        int exclude = knapsack(i-1, w);
        memo[i][w] = Math.max(include, exclude);
        
        printTable(i, w, memo[i][w]);
        return memo[i][w];
    }
    
    public static void printSelectedItems() {
        System.out.println("\nSelected items:");
        int w = W;
        for(int i = n; i > 0 && w > 0; i--) {
            if(memo[i][w] != memo[i-1][w]) {
                System.out.println("Item " + i + " (Weight: " + weights[i] + ", Value: " + values[i] + ")");
                w = w - weights[i];
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of items: ");
        n = sc.nextInt();
        
        System.out.print("Enter knapsack capacity: ");
        W = sc.nextInt();
        
        weights = new int[n + 1];
        values = new int[n + 1];
        memo = new int[n + 1][W + 1];
        
        // Initialize memo table with -1
        for(int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        System.out.println("Enter weights of items:");
        for(int i = 1; i <= n; i++) {
            weights[i] = sc.nextInt();
        }
        
        System.out.println("Enter values of items:");
        for(int i = 1; i <= n; i++) {
            values[i] = sc.nextInt();
        }
        
        System.out.println("\nInitial memo table ('-' represents unfilled cells):");
        printTable(0, 0, 0);
        
        int maxValue = knapsack(n, W);
        System.out.println("\nMaximum value possible: " + maxValue);
        printSelectedItems();
        
        sc.close();
    }
}