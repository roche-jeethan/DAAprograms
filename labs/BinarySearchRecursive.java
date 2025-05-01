// Binary Search by calling recusrively
// Time Complexity: O(log n)
// Space Complexity: O(log n) for recursive stack space

import java.util.Scanner;
public class BinarySearchRecursive{
    int BS(int a[], int low, int high, int key){
        if (low <= high){
            int mid = (low + high) / 2;
            if (key == a[mid]){
                return mid;
            } else if (key < a[mid]){
                return BS(a, low, mid - 1, key);
            } else{
                return BS(a, mid + 1, high, key);
            }
        } else {
            return -1; 
        }
    }

    public static void main(String[] args){
        BinarySearchRecursive ob = new BinarySearchRecursive();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements in the array:");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.println("Enter the elements of the array:");
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        System.out.println("Enter the element to search:");
        int key = sc.nextInt();
        int low = 0, high = n - 1;
        int val = ob.BS(a, low, high, key);
        if(val >= 0){
            System.out.println("Element found at index: " + val);
        } else {
            System.out.println("Element not found in the array.");
        }
    }
}