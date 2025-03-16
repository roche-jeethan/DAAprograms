// Normal Binary Search
// Time Complexity: O(log n)
// Space Complexity: O(1)

import java.util.Scanner;

public class BinarySearch{
    public static void main(String[] args){
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
        int low = 0, high = n - 1, pos = -1;
        while (low<=high){
            int mid = (low + high) / 2;
            if (a[mid] == key){
                pos = mid;
                break;
            }
            else if (key < a[mid]){
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        if(pos >= 0){
            System.out.println("Element found at index: " + pos);
        } else {
            System.out.println("Element not found in the array.");
        }
    }
}