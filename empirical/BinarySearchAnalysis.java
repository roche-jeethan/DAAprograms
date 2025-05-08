import java.util.*;
import java.awt.*;
import javax.swing.*;

public class BinarySearchAnalysis extends JFrame {
    private static ArrayList<Integer> inputSizes = new ArrayList<>();
    private static ArrayList<Double> timeTaken = new ArrayList<>();
    
    public BinarySearchAnalysis() {
        setTitle("Binary Search Time Analysis - O(log n)");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Better quality rendering
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int padding = 70;
        int graphWidth = getWidth() - 2 * padding;
        int graphHeight = getHeight() - 2 * padding;
        
        // Draw axes
        g2.drawLine(padding, getHeight() - padding, padding, padding);
        g2.drawLine(padding, getHeight() - padding, getWidth() - padding, getHeight() - padding);
        
        // Find max values for scaling
        double maxTime = Collections.max(timeTaken);
        int maxInput = Collections.max(inputSizes);
        
        // Draw grid lines and labels
        for (int i = 0; i <= 10; i++) {
            int y = getHeight() - padding - (i * graphHeight / 10);
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(padding, y, getWidth() - padding, y);
            g2.setColor(Color.BLACK);
            g2.drawString(String.format("%.2f", (maxTime * i / 10)), padding - 60, y);
        }
        
        // Plot points and connect them
        g2.setColor(Color.BLUE);
        for (int i = 0; i < inputSizes.size(); i++) {
            // Use logarithmic scale for x-axis
            double logX = Math.log(inputSizes.get(i)) / Math.log(2); // log base 2
            int x = padding + (int)(logX * graphWidth / Math.log(maxInput));
            int y = getHeight() - padding - (int)(timeTaken.get(i) * graphHeight / maxTime);
            
            g2.fillOval(x-3, y-3, 6, 6);
            
            if (i > 0) {
                double prevLogX = Math.log(inputSizes.get(i-1)) / Math.log(2);
                int prevX = padding + (int)(prevLogX * graphWidth / Math.log(maxInput));
                int prevY = getHeight() - padding - (int)(timeTaken.get(i-1) * graphHeight / maxTime);
                g2.drawLine(prevX, prevY, x, y);
            }
        }
        
        // Draw logarithmic curve fit
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        
        // Calculate curve fit parameters using least squares
        double sumX = 0, sumY = 0, sumXY = 0, sumXX = 0;
        int n = inputSizes.size();
        
        for (int i = 0; i < n; i++) {
            double x = Math.log(inputSizes.get(i)) / Math.log(2);
            double y = timeTaken.get(i);
            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumXX += x * x;
        }
        
        double a = (n * sumXY - sumX * sumY) / (n * sumXX - sumX * sumX);
        double b = (sumY - a * sumX) / n;
        
        // Draw fitted curve
        int prevX = padding;
        int prevY = getHeight() - padding - (int)((a * Math.log(inputSizes.get(0)) / Math.log(2) + b) * graphHeight / maxTime);
        
        for (int px = padding + 1; px < getWidth() - padding; px++) {
            double x = Math.exp((px - padding) * Math.log(maxInput) / graphWidth * Math.log(2));
            double y = a * Math.log(x) / Math.log(2) + b;
            int py = getHeight() - padding - (int)(y * graphHeight / maxTime);
            
            if (py >= padding && py <= getHeight() - padding) {
                g2.drawLine(prevX, prevY, px, py);
            }
            prevX = px;
            prevY = py;
        }
        
        // Add legend
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.BLUE);
        g2.fillOval(getWidth() - 150, 50, 6, 6);
        g2.drawString("Actual measurements", getWidth() - 140, 55);
        
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        g2.drawLine(getWidth() - 150, 70, getWidth() - 130, 70);
        g2.drawString("Log fit curve", getWidth() - 140, 75);
        
        // Draw labels
        g2.setColor(Color.BLACK);
        g2.drawString("Input Size (log₂ n)", getWidth()/2 - 50, getHeight() - padding/2);
        g2.rotate(-Math.PI/2);
        g2.drawString("Average Time (microseconds)", -getHeight()/2 - 50, padding/2);
    }
    
    private static int binarySearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key) return mid;
            else if (arr[mid] > key) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        // Generate exponentially increasing sizes
        int[] sizes = new int[24];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = (int) Math.pow(2, i+1); // 2, 4, 8, 16, ..., 2^20
        }
        
        Random rand = new Random();
        
        for (int size : sizes) {
            // Create sorted array
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = i;
            }
            
            // Run multiple trials for each size to get average
            long totalTime = 0;
            int trials = 1000;
            
            for (int t = 0; t < trials; t++) {
                int key = rand.nextInt(size);
                long startTime = System.nanoTime();
                binarySearch(arr, key);
                totalTime += System.nanoTime() - startTime;
            }
            
            // Store average time in microseconds
            double averageTime = (totalTime / (double)trials) / 1000.0;
            inputSizes.add(size);
            timeTaken.add(averageTime);
            
            System.out.printf("n = %d, Average Time = %.2f μs\n", size, averageTime);
        }
        
        SwingUtilities.invokeLater(() -> {
            BinarySearchAnalysis graph = new BinarySearchAnalysis();
            graph.setVisible(true);
        });
    }
}