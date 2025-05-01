// implementation of subset problem to get all possible combinations
// Time complexity: O(2^n)
// Space complexity: O(n)

import java.util.*;
public class Subset {
    void subset(int n, int d, int w[]){
        int k=1, flag=0, sum=0, i, check=0;
        int[] x  = new int[10];
        x[1]=1;
        for(i=2;i<=n;i++){
            x[i]=0;
        }
        while(true){
            if(k<=n && x[k]==1){
                if(sum+w[k]==d){
                    if(check==0){
                        System.out.println("The possible subsets are:");
                        check=1;
                    }
                    System.out.print("{ ");
                    for(i=1;i<=n;i++){
                        if(x[i]==1){
                            System.out.print(w[i]+" ");
                        }
                    }
                    System.out.println("}");
                    flag=1;
                    x[k]=0;
                } else if(sum+w[k]<d){
                    sum+=w[k];
                } else {
                    x[k]=0;
                }
            } else {
                k--;
                while(k>0 && x[k]==0){
                    k--;
                }
                if(k==0){
                    break;
                }
                x[k]=0;
                sum-=w[k];
            }
            k++;
            x[k]=1;
        }
        if(flag==0){
            System.out.println("No possible subsets found");
        }
    }

    public static void main(String[] args){
        int[] w = new int[20];
        int i,n,d;
        Subset s = new Subset();
        Scanner sc  = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        n = sc.nextInt();
        System.out.println("Enter the elements in increasing order: ");
        for(i=1;i<=n;i++){
            w[i] = sc.nextInt();
        }
        System.out.println("Enter the sum: ");
        d = sc.nextInt();
        s.subset(n, d, w);
    }
}