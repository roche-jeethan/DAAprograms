// Merge Sort 
// Time Complexity: O(n log n)
// Space Complexity: O(n) for temporary array

import java.util.*;
public class MergeSort{
    void mergeSort(int a[], int n){
        int b[] = new int[n/2];
        int c[] = new int[(n/2)+1];
        int j=0,k=0;
        if(n>1){
            for(int i = 0; i<n/2;i++){
                b[j++] = a[i];
            }
            for(int i = n/2; i<n;i++){
                c[k++] = a[i];
            }
            mergeSort(b,j);
            mergeSort(c,k);
            merge(b,c,a,j,k);
        }
    }

    void merge(int b[], int c[], int a[], int p, int q){
        int i=0, j=0, k=0;
        while(i<p && j<q){
            if(b[i] <= c[j]){
                a[k++] = b[i++];
            } else {
                a[k++] = c[j++];
            }
        }
        if(i==p){
            for(;j<q;j++){
                a[k++] = c[j];
            }
        } else {
            for(;i<p;i++){
                a[k++] = b[i];
            }
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
            a[i] = generator.nextInt(10000);
        System.out.println("The array before sorting is:");
        for(i=0;i<n;i++)
            System.out.print(a[i] + " ");
        MergeSort m = new MergeSort();
        long startTime = System.currentTimeMillis();
        m.mergeSort(a,n);    
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("\nThe array after sorting is:");
        for(i=0;i<n;i++)
            System.out.print(a[i] + " ");
        System.out.println("\nTime taken to sort the array is: " + elapsedTime + " milliseconds");
            
    }
}