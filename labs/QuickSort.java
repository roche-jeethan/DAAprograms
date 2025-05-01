// Quick Sort for n integers
// Time Complexity: O(n log n)
// Space Complexity: O(log n) for recursive stack

import java.util.Random;
import java.util.Scanner;

public class QuickSort{
    int partition(int[] a, int low, int high){
        int p,i,j,temp;
        p=a[low];
        i=low+1;
        j=high;
        while(low<high){
            while(a[i]<=p && i<high){
                i++;
            }
            while(a[j]>p){
                j--;
            }
            if(i<j){
                temp=a[i];
                a[i]=a[j];
                a[j]=temp;
            } else {
                temp=a[low];
                a[low]=a[j];
                a[j]=temp;
                return j;
            }
        }
        return j;
    }
    void qsort(int[] a,int low, int high){
        if(low<high){
            int j=partition(a,low,high);
            qsort(a,low,j-1);
            qsort(a,j+1,high);
        }
    }
    public static void main(String[] args){
        int[] a;
        int i;
        System.out.println("Enter the number of elements in the array:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        a = new int[n];
        Random generator = new Random();
        for(i=0;i<n;i++)
            a[i] = generator.nextInt(100);
        System.out.println("The array before sorting is:");
        for(i=0;i<n;i++)
            System.out.print(a[i] + " ");
        QuickSort m = new QuickSort();
        long startTime = System.currentTimeMillis();
        m.qsort(a,0,n-1);
        long stopTime = System.currentTimeMillis();
        System.out.println("\nThe array after sorting is:");
        for(i=0;i<n;i++)
            System.out.print(a[i] + " ");
        System.out.println("\nTime taken to sort the array is: " + (stopTime - startTime) + " milliseconds");    
    }
}