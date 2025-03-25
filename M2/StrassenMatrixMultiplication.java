// Strassen's Matrix Multiplication Algorithm
// Time Complexity: O(n^log7) ~ O(n^2.81)
// Space Complexity: O(n^2)

import java.util.*;

public class StrassenMatrixMultiplication {
    public static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;
        if (n == 1) {
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }
        int newSize = n / 2;
        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];

        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];
        splitMatrix(A, A11, 0, 0);
        splitMatrix(A, A12, 0, newSize);
        splitMatrix(A, A21, newSize, 0);
        splitMatrix(A, A22, newSize, newSize);

        splitMatrix(B, B11, 0, 0);
        splitMatrix(B, B12, 0, newSize);
        splitMatrix(B, B21, newSize, 0);
        splitMatrix(B, B22, newSize, newSize);

        int[][] M1 = strassenMultiply(addMatrices(A11, A22), addMatrices(B11, B22));
        int[][] M2 = strassenMultiply(addMatrices(A21, A22), B11);                  
        int[][] M3 = strassenMultiply(A11, subtractMatrices(B12, B22));              
        int[][] M4 = strassenMultiply(A22, subtractMatrices(B21, B11));              
        int[][] M5 = strassenMultiply(addMatrices(A11, A12), B22);                   
        int[][] M6 = strassenMultiply(subtractMatrices(A21, A11), addMatrices(B11, B12)); 
        int[][] M7 = strassenMultiply(subtractMatrices(A12, A22), addMatrices(B21, B22));

        int[][] C11 = addMatrices(subtractMatrices(addMatrices(M1, M4), M5), M7);
        int[][] C12 = addMatrices(M3, M5);
        int[][] C21 = addMatrices(M2, M4);
        int[][] C22 = addMatrices(subtractMatrices(addMatrices(M1, M3), M2), M6);

        int[][] C = new int[n][n];
        joinMatrix(C11, C, 0, 0);
        joinMatrix(C12, C, 0, newSize);
        joinMatrix(C21, C, newSize, 0);
        joinMatrix(C22, C, newSize, newSize);

        return C;
    }

    public static void splitMatrix(int[][] parent, int[][] child, int row, int col) {
        int size = child.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                child[i][j] = parent[row + i][col + j];
            }
        }
    }

    public static void joinMatrix(int[][] child, int[][] parent, int row, int col) {
        int size = child.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                parent[row + i][col + j] = child[i][j];
            }
        }
    }

    public static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    public static int[][] subtractMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    public static void displayMatrix(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void inputMatrix(int[][] matrix){
        Scanner sc = new Scanner(System.in);
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the elements of the matrix:");
        int n = sc.nextInt();
        int[][] A = new int[n][n];
        int[][] B = new int[n][n];
        System.out.println("Enter elements of matrix A:");
        inputMatrix(A);
        System.out.println("Enter elements of matrix B:");
        inputMatrix(B);
        System.out.println("Matrix A:");
        displayMatrix(A);
        System.out.println("\nMatrix B:");
        displayMatrix(B);

        int[][] C = strassenMultiply(A, B);

        System.out.println("\nResultant Matrix C after Strassen's Multiplication:");
        displayMatrix(C);
    }
}
